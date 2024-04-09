package com.altruist.controller;

import javax.servlet.http.HttpServletRequest;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altruist.request.QuotationRequest;
import com.altruist.response.QuotationResponse;
import com.altruist.response.StatusDescription;
import com.altruist.response.WalletBalanceResponse;
import com.altruist.service.JwtFactoryService;
import com.altruist.service.QuotationService;
import com.altruist.utils.ConstantManager;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/quotation/")
public class QuatationController {
	
	static RsaJsonWebKey senderJwk = null;

	static {
		try {
			senderJwk = RsaJwkGenerator.generateJwk(2048);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	JwtFactoryService jwtFactoryService;
	
	@Autowired
	QuotationService quotationService;
	
	@PostMapping("/v1/fetch")
	public ResponseEntity<QuotationResponse> claimEligibilityCheck(@RequestHeader("Authorization") String token,
			@RequestHeader("agentCode") String agentCode, HttpServletRequest req,@RequestBody QuotationRequest quotationRequest) {
		QuotationResponse response = new QuotationResponse();
		boolean isValid = jwtFactoryService.validateToken(senderJwk, token, agentCode, "WEB", "", "", req);
		//boolean isValid = true;
		if (isValid) {
			response = quotationService.fetchQuotation(quotationRequest);
		} else {
			StatusDescription status = new StatusDescription();
			status.setStatusCode(ConstantManager.Token_expired.getStatusCode());
			status.setStatusDescription(ConstantManager.Token_expired.getDescription());
			response.setStatusDescription(status);
		}
		return new ResponseEntity<QuotationResponse>(response, HttpStatus.OK);
	}
}
