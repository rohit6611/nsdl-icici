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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.altruist.nsdl.model.CustomerDetailModel;
import com.altruist.response.CustomerDetailListResponse;
import com.altruist.response.CustomerDetailResponse;
import com.altruist.response.DashboardResponse;
import com.altruist.response.GenericResponse;
import com.altruist.response.StatusDescription;
import com.altruist.service.CustomerService;
import com.altruist.service.JwtFactoryService;
import com.altruist.utils.ConstantManager;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/customer/")
public class CustomerController {
	
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
	CustomerService customerService;
	
	@PostMapping("/v1/request")
	public ResponseEntity<CustomerDetailResponse> sendProposalRequest(@RequestHeader("Authorization") String token,
			@RequestHeader("agentCode") String agentCode, HttpServletRequest req,@RequestBody CustomerDetailModel customerDetailModel) {
		CustomerDetailResponse response = new CustomerDetailResponse();
		boolean isValid = jwtFactoryService.validateToken(senderJwk, token, agentCode, "WEB", "", "", req);
		//boolean isValid = true;
		if (isValid) {
			response = customerService.sendCustomerRequest(customerDetailModel,agentCode);
		} else {
			StatusDescription status = new StatusDescription();
			status.setStatusCode(ConstantManager.Token_expired.getStatusCode());
			status.setStatusDescription(ConstantManager.Token_expired.getDescription());
			response.setStatusDescription(status);
		}
		return new ResponseEntity<CustomerDetailResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping("/v1/request/upload")
	public ResponseEntity<CustomerDetailResponse> sendProposalRequest(@RequestHeader("Authorization") String token,
			@RequestHeader("agentCode") String agentCode, HttpServletRequest req,
			@RequestPart(value = "aadharCard", required = false) MultipartFile aadharCard,
			@RequestPart(value = "panCard", required = false) MultipartFile panCard,
			@RequestParam("customerMsisdn") String customerMsisdn) {
		CustomerDetailResponse response = new CustomerDetailResponse();
		boolean isValid = jwtFactoryService.validateToken(senderJwk, token, agentCode, "WEB", "", "", req);
		//boolean isValid = true;
		if (isValid) {
			response = customerService.sendCustomerRequestUpload(aadharCard,panCard,customerMsisdn);
		} else {
			StatusDescription status = new StatusDescription();
			status.setStatusCode(ConstantManager.Token_expired.getStatusCode());
			status.setStatusDescription(ConstantManager.Token_expired.getDescription());
			response.setStatusDescription(status);
		}
		return new ResponseEntity<CustomerDetailResponse>(response, HttpStatus.OK);
	}
	
	@GetMapping("/v1/pending/list")
	public ResponseEntity<CustomerDetailListResponse> fetchPendingList(@RequestHeader("Authorization") String token,
			@RequestHeader("agentCode") String agentCode, HttpServletRequest req) {
		CustomerDetailListResponse response = new CustomerDetailListResponse();
		boolean isValid = jwtFactoryService.validateToken(senderJwk, token, agentCode, "WEB", "", "", req);
		//boolean isValid = true;
		if (isValid) {
			response = customerService.fetchCustomerDetailList(agentCode);
		} else {
			StatusDescription status = new StatusDescription();
			status.setStatusCode(ConstantManager.Token_expired.getStatusCode());
			status.setStatusDescription(ConstantManager.Token_expired.getDescription());
			response.setStatusDescription(status);
		}
		return new ResponseEntity<CustomerDetailListResponse>(response, HttpStatus.OK);
	}
	
	@GetMapping("/v1/sold/list")
	public ResponseEntity<CustomerDetailListResponse> sendProposalRequest(@RequestHeader("Authorization") String token,
			@RequestHeader("agentCode") String agentCode, HttpServletRequest req) {
		CustomerDetailListResponse response = new CustomerDetailListResponse();
		boolean isValid = jwtFactoryService.validateToken(senderJwk, token, agentCode, "WEB", "", "", req);
		//boolean isValid = true;
		if (isValid) {
			response = customerService.fetchSoldCustomerDetailList(agentCode);
		} else {
			StatusDescription status = new StatusDescription();
			status.setStatusCode(ConstantManager.Token_expired.getStatusCode());
			status.setStatusDescription(ConstantManager.Token_expired.getDescription());
			response.setStatusDescription(status);
		}
		return new ResponseEntity<CustomerDetailListResponse>(response, HttpStatus.OK);
	}
	
	@GetMapping("/v1/dashboard/detail")
	public ResponseEntity<DashboardResponse> fetchDashboardDetail(@RequestHeader("Authorization") String token,
			@RequestHeader("agentCode") String agentCode, HttpServletRequest req) {
		DashboardResponse response = new DashboardResponse();
		boolean isValid = jwtFactoryService.validateToken(senderJwk, token, agentCode, "WEB", "", "", req);
		//boolean isValid = true;
		if (isValid) {
			response = customerService.fetchDashboardDetail(agentCode);
		} else {
			StatusDescription status = new StatusDescription();
			status.setStatusCode(ConstantManager.Token_expired.getStatusCode());
			status.setStatusDescription(ConstantManager.Token_expired.getDescription());
			response.setStatusDescription(status);
		}
		return new ResponseEntity<DashboardResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping("/v1/remove/detail")
	public ResponseEntity<GenericResponse> removeCustomerDetail(@RequestHeader("Authorization") String token,
			@RequestHeader("agentCode") String agentCode, HttpServletRequest req,@RequestBody CustomerDetailModel customerDetailModel) {
		GenericResponse response = new GenericResponse();
		boolean isValid = jwtFactoryService.validateToken(senderJwk, token, agentCode, "WEB", "", "", req);
		//boolean isValid = true;
		if (isValid) {
			response = customerService.removeCustomerDetail(customerDetailModel);
		} else {
			StatusDescription status = new StatusDescription();
			status.setStatusCode(ConstantManager.Token_expired.getStatusCode());
			status.setStatusDescription(ConstantManager.Token_expired.getDescription());
			response.setStatusDescription(status);
		}
		return new ResponseEntity<GenericResponse>(response, HttpStatus.OK);
	}
	
	
}
