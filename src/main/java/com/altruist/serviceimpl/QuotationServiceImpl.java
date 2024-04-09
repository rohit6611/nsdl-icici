package com.altruist.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.altruist.nsdl.dao.QuoteRequestRepos;
import com.altruist.nsdl.dao.TokenRepos;
import com.altruist.nsdl.model.QuoteRequestModel;
import com.altruist.nsdl.model.TokenModel;
import com.altruist.request.QuotationRequest;
import com.altruist.response.QuotationResponse;
import com.altruist.response.StatusDescription;
import com.altruist.service.QuotationService;
import com.altruist.utils.ConstantManager;
import com.altruist.utils.HttpRequest;

@Service
public class QuotationServiceImpl implements QuotationService {

	@Value("${QuotationRequest}")
	private String QuotationRequest;

	@Value("${QuotationAPI}")
	private String QuotationAPI;

	@Autowired
	private TokenRepos tokenDao;

	@Autowired
	private QuoteRequestRepos quoteRequestRepos;

	@Override
	public QuotationResponse fetchQuotation(QuotationRequest quotationRequest) {
		QuotationResponse response = new QuotationResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfHour = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 365);

		try {

			String correlationId = java.util.UUID.randomUUID().toString();

			String request = QuotationRequest;
			request = request.replace("#policyStartDate#", sdf.format(date) + "T" + sdfHour.format(date));
			request = request.replace("#policyEndDate#", sdf.format(cal.getTime()) + "T" + sdfHour.format(date));
			request = request.replace("#dob#", sdf.format(quotationRequest.getDateOfBirth()) + "T00:00:00");
			request = request.replace("#partyStateName#", "HARYANA");
			request = request.replace("#correlationId#", correlationId);
			System.out.println("QuotationRequest::" + request);

			String url = QuotationAPI;
			System.out.println("QuotationAPI::" + url);

			List<TokenModel> tokenModel = tokenDao.findByAccessType("esbzerotat");

			String quoteResponse = HttpRequest.sendQuoteRequest(request, url, tokenModel.get(0));
			System.out.println("quoteResponse::" + quoteResponse);
			JSONObject jsonObj = new JSONObject(quoteResponse);
			Boolean quoteStatus = jsonObj.getBoolean("status");
			if (quoteStatus) {
				String quoteStatusMessage = jsonObj.getString("statusMessage");
				if ("Success".equalsIgnoreCase(quoteStatusMessage)) {
					String basicPremium = jsonObj.getString("basicPremium");
					String totalTax = jsonObj.getString("totalTax");
					String totalPremium = jsonObj.getString("totalPremium");

					QuoteRequestModel quoteRequest = quoteRequestRepos.findByMsisdn(quotationRequest.getMsisdn());
					if (quoteRequest == null) {
						saveQuoteRequest(request, quoteResponse, basicPremium, totalTax, totalPremium, correlationId,
								quotationRequest);
					} else {
						quoteRequest.setBasicPremium(basicPremium);
						quoteRequest.setCorrelatorId(correlationId);
						quoteRequest.setDateTime(new Date());
						quoteRequest.setMsisdn(quotationRequest.getMsisdn());
						quoteRequest.setRequest(request);
						quoteRequest.setResponse(quoteResponse);
						quoteRequest.setTotalPremium(totalPremium);
						quoteRequest.setTotalTax(totalTax);

						quoteRequestRepos.save(quoteRequest);
					}

					response.setBasicPremium(basicPremium);
					response.setTotalPremium(totalPremium);
					response.setTotalTax(totalTax);

					status.setStatusCode(ConstantManager.Success.getStatusCode());
					status.setStatusDescription(ConstantManager.Success.getDescription());
				} else {

					String errorMsg = jsonObj.getString("message");

					status.setStatusCode(ConstantManager.ApiResponseError.getStatusCode());
					status.setStatusDescription(errorMsg);
				}
			}

		} catch (Exception e) {
			status.setStatusCode(ConstantManager.Server_Resource_Busy.getStatusCode());
			status.setStatusDescription(ConstantManager.Server_Resource_Busy.getDescription());
			e.printStackTrace();
		}

		return response;
	}

	private void saveQuoteRequest(String request, String quoteResponse, String basicPremium, String totalTax,
			String totalPremium, String correlationId, QuotationRequest quotationRequest) {
		QuoteRequestModel quoteRequest = new QuoteRequestModel();
		try {
			quoteRequest.setBasicPremium(basicPremium);
			quoteRequest.setCorrelatorId(correlationId);
			quoteRequest.setDateTime(new Date());
			quoteRequest.setMsisdn(quotationRequest.getMsisdn());
			quoteRequest.setRequest(request);
			quoteRequest.setResponse(quoteResponse);
			quoteRequest.setTotalPremium(totalPremium);
			quoteRequest.setTotalTax(totalTax);

			quoteRequestRepos.save(quoteRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
