package com.altruist.controller;

import javax.servlet.http.HttpServletRequest;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altruist.response.CityListResponse;
import com.altruist.response.CustomerDetailListResponse;
import com.altruist.response.PinCodeResponse;
import com.altruist.response.StateListResponse;
import com.altruist.response.StatusDescription;
import com.altruist.service.CountryService;
import com.altruist.service.JwtFactoryService;
import com.altruist.utils.ConstantManager;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/country/")
public class CountryController {
	
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
	CountryService countryService;
	
	@GetMapping("/v1/state/list")
	public ResponseEntity<StateListResponse> fetchStateList(@RequestHeader("Authorization") String token,
			@RequestHeader("agentCode") String agentCode, HttpServletRequest req) {
		StateListResponse response = new StateListResponse();
		boolean isValid = jwtFactoryService.validateToken(senderJwk, token, agentCode, "WEB", "", "", req);
		//boolean isValid = true;
		if (isValid) {
			response = countryService.fetchStateList();
		} else {
			StatusDescription status = new StatusDescription();
			status.setStatusCode(ConstantManager.Token_expired.getStatusCode());
			status.setStatusDescription(ConstantManager.Token_expired.getDescription());
			response.setStatusDescription(status);
		}
		return new ResponseEntity<StateListResponse>(response, HttpStatus.OK);
	}
	
	@GetMapping("/v1/city/list/{stateCode}")
	public ResponseEntity<CityListResponse> fetchCityList(@RequestHeader("Authorization") String token,
			@RequestHeader("agentCode") String agentCode, HttpServletRequest req,@PathVariable("stateCode") String stateCode) {
		CityListResponse response = new CityListResponse();
		boolean isValid = jwtFactoryService.validateToken(senderJwk, token, agentCode, "WEB", "", "", req);
		//boolean isValid = true;
		if (isValid) {
			response = countryService.fetchCityList(stateCode);
		} else {
			StatusDescription status = new StatusDescription();
			status.setStatusCode(ConstantManager.Token_expired.getStatusCode());
			status.setStatusDescription(ConstantManager.Token_expired.getDescription());
			response.setStatusDescription(status);
		}
		return new ResponseEntity<CityListResponse>(response, HttpStatus.OK);
	}
	
	@GetMapping("/v1/pinCodelist/{cityCode}")
	public ResponseEntity<PinCodeResponse> fetchPincodeList(@RequestHeader("Authorization") String token,
			@RequestHeader("agentCode") String agentCode, HttpServletRequest req,@PathVariable("cityCode") String cityCode) {
		PinCodeResponse response = new PinCodeResponse();
		boolean isValid = jwtFactoryService.validateToken(senderJwk, token, agentCode, "WEB", "", "", req);
		//boolean isValid = true;
		if (isValid) {
			response = countryService.fetchPincodeList(cityCode);
		} else {
			StatusDescription status = new StatusDescription();
			status.setStatusCode(ConstantManager.Token_expired.getStatusCode());
			status.setStatusDescription(ConstantManager.Token_expired.getDescription());
			response.setStatusDescription(status);
		}
		return new ResponseEntity<PinCodeResponse>(response, HttpStatus.OK);
	}
	
}
