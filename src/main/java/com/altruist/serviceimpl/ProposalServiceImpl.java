package com.altruist.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.altruist.nsdl.dao.CustomerDetailRepos;
import com.altruist.nsdl.dao.QuoteRequestRepos;
import com.altruist.nsdl.dao.TokenRepos;
import com.altruist.nsdl.model.CustomerDetailModel;
import com.altruist.nsdl.model.QuoteRequestModel;
import com.altruist.nsdl.model.TokenModel;
import com.altruist.request.ProposalRequest;
import com.altruist.response.ProposalResponse;
import com.altruist.response.StatusDescription;
import com.altruist.service.ProposalService;
import com.altruist.utils.ConstantManager;
import com.altruist.utils.HttpRequest;

@Service
public class ProposalServiceImpl implements ProposalService {

	@Value("${ProposalRequest}")
	private String ProposalRequest;

	@Value("${ProposalAPI}")
	private String ProposalAPI;

	@Autowired
	private CustomerDetailRepos customerDetailRepos;

	@Autowired
	private TokenRepos tokenDao;

	@Autowired
	private QuoteRequestRepos quoteRequestRepos;

	@Override
	public ProposalResponse sendProposalRequest(ProposalRequest proposalRequest) {
		ProposalResponse response = new ProposalResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long requestId = (long) (Math.random() * 100000000000000L);
		try {

			CustomerDetailModel customerDetail = customerDetailRepos
					.fetchByCustomerMsisdn(proposalRequest.getMobileNumber());
			if (customerDetail != null) {

				QuoteRequestModel quoteRequest = quoteRequestRepos.findByMsisdn(customerDetail.getCustomerMsisdn());
				if (quoteRequest != null) {
					String request = ProposalRequest;
					request = request.replace("#policyStartDate#",
							"" + sdf.format(customerDetail.getInsuranceStartDate()));
					request = request.replace("#polictEndDate#", "" + sdf.format(customerDetail.getInsuranceEndDate()));
					request = request.replace("#inwardNumber#", customerDetail.getInwardNumber());
					request = request.replace("#hspValue#", customerDetail.getHspValue());
					request = request.replace("#employeeValue#", customerDetail.getEmployeeValue());
					request = request.replace("#fundedValue#", customerDetail.getFundedValue());
					request = request.replace("#dob#", "" + sdf.format(customerDetail.getCustomerDob()));
					request = request.replace("#todayDate#", "" + sdf.format(customerDetail.getDateTime()));
					request = request.replace("#nomineeName#", customerDetail.getNomineeName());
					request = request.replace("#nomineeRelation#", customerDetail.getNomineeRelation().toUpperCase());
					request = request.replace("#nomineeDob#", "" + sdf.format(customerDetail.getNomineeDob()));
					request = request.replace("#customerName#", customerDetail.getCustomerName());
					request = request.replace("#customerRelation#", customerDetail.getCustomerRelation().toUpperCase());
					request = request.replace("#customerDob#", "" + sdf.format(customerDetail.getCustomerDob()));
					request = request.replace("#pincode#", customerDetail.getCustomerPincode());
					request = request.replace("#panCardNo#", customerDetail.getCustomerPan());
					request = request.replace("#email#", customerDetail.getCustomerEmail());
					request = request.replace("#msisdn#", customerDetail.getCustomerMsisdn().substring(2));
					request = request.replace("#address#", customerDetail.getCustomerAddress());
					request = request.replace("#stateCode#", customerDetail.getStateCode());
					request = request.replace("#cityCode#", customerDetail.getCityCode());
					request = request.replace("#customerGender#", customerDetail.getCustomerGender());
					//request = request.replace("#customerId#", ""+requestId);
					//request = request.replace("#gstNumber#", customerDetail.getGstinNumber());
					//request = request.replace("#gstState#", customerDetail.getGstinState());
					request = request.replace("#aadharCardNo#", customerDetail.getCustomerAadhar());
					request = request.replace("#correlateId#", quoteRequest.getCorrelatorId());

					List<TokenModel> tokenModel = tokenDao.findByAccessType("esbzerotat");

					String url = ProposalAPI;

					System.out.println("Proposal Request::" + request+"::Url ::"+url);

					String proposalResponse = HttpRequest.sendProposalRequest(request, url, tokenModel.get(0));

					System.out.println("Proposal Response::" + proposalResponse);

					JSONObject jsonObject = new JSONObject(proposalResponse);
					String statusMessage = jsonObject.getString("statusMessage");

					if ("Success".equalsIgnoreCase(statusMessage)) {
						
						String gcTotalPremium = jsonObject.getString("gctotalpremium");
						String quoteProposalNo = jsonObject.getString("quoteproposalno");
						String gcReferenceNo = jsonObject.getString("gcreferenceno");
						String proposalNumber = jsonObject.getString("proposalNumber");
						String customerId = jsonObject.getString("customerId");
						
						customerDetail.setQuoteProposalNo(quoteProposalNo);
						customerDetail.setGcReferenceNo(gcReferenceNo);
						customerDetail.setUpdateDateTime(new Date());
						customerDetail.setProposalNumber(proposalNumber);
						customerDetail.setCustomerId(customerId);
						customerDetail.setGcTotalPremium(gcTotalPremium);
						
						customerDetail = customerDetailRepos.save(customerDetail);
						
						response.setCustomerModel(customerDetail);
						status.setStatusCode(ConstantManager.Success.getStatusCode());
						status.setStatusDescription(ConstantManager.Success.getDescription());
					} else {

						String message = jsonObject.getString("message");

						status.setStatusCode(ConstantManager.ApiResponseError.getStatusCode());
						status.setStatusDescription(message);
					}
				} else {
					status.setStatusCode(ConstantManager.QuotationNotReceived.getStatusCode());
					status.setStatusDescription(ConstantManager.QuotationNotReceived.getDescription());
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
