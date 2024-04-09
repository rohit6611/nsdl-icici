package com.altruist.serviceimpl;

import java.io.FileWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.altruist.nsdl.dao.CustomerDetailRepos;
import com.altruist.nsdl.dao.QuoteRequestRepos;
import com.altruist.nsdl.dao.TokenRepos;
import com.altruist.nsdl.model.CustomerDetailModel;
import com.altruist.nsdl.model.QuoteRequestModel;
import com.altruist.nsdl.model.TokenModel;
import com.altruist.response.CustomerDetailResponse;
import com.altruist.response.StatusDescription;
import com.altruist.service.CertificateService;
import com.altruist.utils.ConstantManager;
import com.altruist.utils.HttpRequest;

@Service
public class CertificateServiceImpl implements CertificateService {

	@Value("${CertificateRequest}")
	private String CertificateRequest;

	@Value("${CertificateAPI}")
	private String CertificateAPI;

	@Autowired
	private TokenRepos tokenDao;

	@Autowired
	private CustomerDetailRepos customerDetailRepos;

	@Autowired
	private QuoteRequestRepos quoteRequestRepos;

	@Override
	public CustomerDetailResponse downloadCertificate(String customerMsisdn) {
		CustomerDetailResponse response = new CustomerDetailResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);
		try {

			CustomerDetailModel customerModel = customerDetailRepos.findByCustomerMsisdn(customerMsisdn);

			if (customerModel != null) {

				QuoteRequestModel quoteModel = quoteRequestRepos.findByMsisdn(customerMsisdn);

				if (quoteModel != null) {

					String request = CertificateRequest;
					request = request.replace("#corelationId#", quoteModel.getCorrelatorId());
					request = request.replace("#policyNo#", customerModel.getPolicyNumber());
					request = request.replace("#customerId#", customerModel.getCustomerId());

					List<TokenModel> tokenModel = tokenDao.findByAccessType("esbpolicypdf");

					String url = CertificateAPI;

					System.out.println("Certificate Request::" + request + "::Url ::" + url);

					String link = HttpRequest.sendPolicyCertificateRequest(request, url, tokenModel.get(0),customerModel);

					customerModel.setPolicyCertificate(link);

					customerModel = customerDetailRepos.save(customerModel);

					response.setCustomerModel(customerModel);
					status.setStatusCode(ConstantManager.Success.getStatusCode());
					status.setStatusDescription(ConstantManager.Success.getDescription());

				} else {
					status.setStatusCode(ConstantManager.CustomerDetailsNotFound.getStatusCode());
					status.setStatusDescription(ConstantManager.CustomerDetailsNotFound.getDescription());
				}

			} else {
				status.setStatusCode(ConstantManager.CustomerDetailsNotFound.getStatusCode());
				status.setStatusDescription(ConstantManager.CustomerDetailsNotFound.getDescription());
			}

		} catch (Exception e) {
			status.setStatusCode(ConstantManager.Server_Resource_Busy.getStatusCode());
			status.setStatusDescription(ConstantManager.Server_Resource_Busy.getDescription());
			e.printStackTrace();
		}

		return response;
	}

}
