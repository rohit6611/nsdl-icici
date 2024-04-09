package com.altruist.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
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
import com.altruist.response.GenericResponse;
import com.altruist.response.PaymentResponse;
import com.altruist.response.StatusDescription;
import com.altruist.service.PaymentService;
import com.altruist.utils.ConstantManager;
import com.altruist.utils.HttpRequest;
import com.altruist.wallet.dao.AgentMasterRepos;
import com.altruist.wallet.dao.TransactionDetailRepos;
import com.altruist.wallet.model.AgentMasterModel;
import com.altruist.wallet.model.TransactionDetailModel;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Value("${PaymentRequest}")
	private String PaymentRequest;

	@Value("${PaymentAPI}")
	private String PaymentAPI;

	@Autowired
	private CustomerDetailRepos customerDetailRepos;

	@Autowired
	private QuoteRequestRepos quoteRequestRepos;

	@Autowired
	private TokenRepos tokenDao;

	@Autowired
	private AgentMasterRepos agentMasterRepos;
	
	@Autowired
	private TransactionDetailRepos transactionDetailRepos;

	@Override
	public PaymentResponse sendPaymentRequest(CustomerDetailModel customerDetailModel, String agentCode) {
		PaymentResponse response = new PaymentResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		try {

			CustomerDetailModel customerModel = customerDetailRepos
					.findByCustomerMsisdn(customerDetailModel.getCustomerMsisdn());
			if (customerModel != null) {

				QuoteRequestModel quoteRequest = quoteRequestRepos
						.findByMsisdn(customerModel.getCustomerMsisdn());

				AgentMasterModel agentModels = agentMasterRepos.findByAgentCode(agentCode);

				if (agentModels != null) {
					
					AgentMasterModel agentModel = agentMasterRepos.fetchByBrandCode(agentModels.getBranchCode());
					
					if(agentModel!=null) {
						if(agentModel.getAgentBalance()>=Double.parseDouble(customerModel.getGcTotalPremium())) {

							Double balanceLeft = agentModel.getAgentBalance() - Double.parseDouble(customerModel.getGcTotalPremium());
							agentModel.setAgentBalance(balanceLeft);
							
							agentMasterRepos.save(agentModel);
							
							if (quoteRequest != null) {
								
								
								String request = PaymentRequest;
								request = request.replace("#corelatorId#", quoteRequest.getCorrelatorId());
								request = request.replace("#proposalNumber#", customerModel.getProposalNumber());

								String url = PaymentAPI;

								List<TokenModel> tokenModel = tokenDao.findByAccessType("esbpayment");

								System.out.println("Payment Request::" + request + "::Payment Url ::" + url);

								String paymentResponse = HttpRequest.sendProposalRequest(request, url, tokenModel.get(0));

								System.out.println("Payment Response::" + paymentResponse);

								JSONObject jsonObject = new JSONObject(paymentResponse);
								//String statusMessage = jsonObject.getString("statusMessage");
								
								JSONObject cdbgResponse =  jsonObject.getJSONObject("cdbgResponse");
								JSONArray cdbgResponseList =  cdbgResponse.getJSONArray("cdbgResponseList");
								JSONObject cdbgResponse1 = cdbgResponseList.getJSONObject(0);
								
								String transStatus = cdbgResponse1.getString("transStatus");
								
								if("Success".equalsIgnoreCase(transStatus)) {
									
									
									String policyNo = cdbgResponse1.getString("policyNo");
									String coverNoteNo = cdbgResponse1.getString("coverNoteNo");
									
									customerModel.setPolicyNumber(policyNo);
									customerModel.setCoverNoteNumber(coverNoteNo);
									customerModel.setUpdateDateTime(new Date());
									customerModel.setInsuranceStatus("1");
									
									customerModel = customerDetailRepos.save(customerModel);
									
									TransactionDetailModel transactionModel = new TransactionDetailModel();
									transactionModel.setAgentCode(customerModel.getAgentCode());
									transactionModel.setAgentName(agentModel.getAgentName());
									transactionModel.setCircle(agentModel.getCircle());
									transactionModel.setRefId(quoteRequest.getCorrelatorId());
									transactionModel.setCustomerMsisdn(quoteRequest.getMsisdn());
									transactionModel.setConsumerNumber(customerModel.getProposalNumber());
									transactionModel.setStatus(transStatus);
									transactionModel.setDescription("E_Bill Transaction Done");
									transactionModel.setValue(Double.valueOf(customerModel.getGcTotalPremium()));
									transactionModel.setWalletDeduction(Double.valueOf(customerModel.getGcTotalPremium()));
									transactionModel.setCommission(0.0000);
									transactionModel.setTds(0.0000);
									transactionModel.setClosingBalance(balanceLeft);
									transactionModel.setServiceType("Hospicash");
									transactionModel.setPolicyNumber(policyNo);
									transactionModel.setPolicyStatus("Success");
									transactionModel.setDistributerCommission(0.0000);
									transactionModel.setDistributerTds(0.0000);
									transactionModel.setRequestType("");
									
									transactionDetailRepos.save(transactionModel);
									
									response.setCustomerModel(customerModel);
									status.setStatusCode(ConstantManager.Success.getStatusCode());
									status.setStatusDescription(ConstantManager.Success.getDescription());
									
								}else {
									agentModel = agentMasterRepos.findByAgentCode(agentCode);
									
									Double balanceAdd = agentModel.getAgentBalance() + Double.parseDouble(customerModel.getGcTotalPremium());
									agentModel.setAgentBalance(balanceAdd);
									
									agentMasterRepos.save(agentModel);
									
									status.setStatusCode(ConstantManager.PaymentFailed.getStatusCode());
									status.setStatusDescription(ConstantManager.PaymentFailed.getDescription());
								}

							} else {
								status.setStatusCode(ConstantManager.CustomerDetailsNotFound.getStatusCode());
								status.setStatusDescription(ConstantManager.CustomerDetailsNotFound.getDescription());
							}
						}else {
							status.setStatusCode(ConstantManager.InsufficientBalance.getStatusCode());
							status.setStatusDescription(ConstantManager.InsufficientBalance.getDescription());
						}
					}
					
				} else {
					status.setStatusCode(ConstantManager.wallet_not_found.getStatusCode());
					status.setStatusDescription(ConstantManager.wallet_not_found.getDescription());
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
