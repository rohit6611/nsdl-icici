package com.altruist.controller;

import javax.servlet.http.HttpServletRequest;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altruist.nsdl.model.CustomerDetailModel;
import com.altruist.response.CustomerDetailResponse;
import com.altruist.response.GenericResponse;
import com.altruist.response.PaymentResponse;
import com.altruist.response.StatusDescription;
import com.altruist.service.JwtFactoryService;
import com.altruist.service.PaymentService;
import com.altruist.utils.ConstantManager;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/payment/")
public class PaymentController {
	
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
	PaymentService paymentService;
	
	@PostMapping("/v1/request")
	public ResponseEntity<PaymentResponse> sendProposalRequest(@RequestHeader("Authorization") String token,
			@RequestHeader("agentCode") String agentCode, HttpServletRequest req,@RequestBody CustomerDetailModel customerDetailModel) {
		PaymentResponse response = new PaymentResponse();
		boolean isValid = jwtFactoryService.validateToken(senderJwk, token, agentCode, "WEB", "", "", req);
		//boolean isValid = true;
		if (isValid) {
			response = paymentService.sendPaymentRequest(customerDetailModel,agentCode);
		} else {
			StatusDescription status = new StatusDescription();
			status.setStatusCode(ConstantManager.Token_expired.getStatusCode());
			status.setStatusDescription(ConstantManager.Token_expired.getDescription());
			response.setStatusDescription(status);
		}
		return new ResponseEntity<PaymentResponse>(response, HttpStatus.OK);
	}
	
}
