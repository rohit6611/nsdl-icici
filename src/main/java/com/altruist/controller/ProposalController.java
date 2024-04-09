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

import com.altruist.request.ProposalRequest;
import com.altruist.request.QuotationRequest;
import com.altruist.response.ProposalResponse;
import com.altruist.response.QuotationResponse;
import com.altruist.response.StatusDescription;
import com.altruist.service.JwtFactoryService;
import com.altruist.service.ProposalService;
import com.altruist.utils.ConstantManager;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/proposal/")
public class ProposalController {
	
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
	ProposalService proposalService;
	
	@PostMapping("/v1/request")
	public ResponseEntity<ProposalResponse> sendProposalRequest(@RequestHeader("Authorization") String token,
			@RequestHeader("agentCode") String agentCode, HttpServletRequest req,@RequestBody ProposalRequest proposalRequest) {
		ProposalResponse response = new ProposalResponse();
		boolean isValid = jwtFactoryService.validateToken(senderJwk, token, agentCode, "WEB", "", "", req);
		//boolean isValid = true;
		if (isValid) {
			response = proposalService.sendProposalRequest(proposalRequest);
		} else {
			StatusDescription status = new StatusDescription();
			status.setStatusCode(ConstantManager.Token_expired.getStatusCode());
			status.setStatusDescription(ConstantManager.Token_expired.getDescription());
			response.setStatusDescription(status);
		}
		return new ResponseEntity<ProposalResponse>(response, HttpStatus.OK);
	}
	
}
