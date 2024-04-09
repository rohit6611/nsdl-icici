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

import com.altruist.response.GenericResponse;
import com.altruist.response.LoginResponse;
import com.altruist.response.StatusDescription;
import com.altruist.service.AuthService;
import com.altruist.service.JwtFactoryService;
import com.altruist.utils.ConstantManager;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/auth/")
public class AuthController {
	
	static RsaJsonWebKey senderJwk = null;

	static {
		try {
			senderJwk = RsaJwkGenerator.generateJwk(2048);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private final JwtFactoryService jwtFactoryService;
	
	 private final AuthService authService;

	    @Autowired
	    public AuthController(AuthService authService,JwtFactoryService jwtFactoryService) {
	        this.authService = authService;
	        this.jwtFactoryService=jwtFactoryService;
	    }
	
	@GetMapping("/v1/login/{agentCode}")
	public ResponseEntity<LoginResponse> fetchUserList(@PathVariable("agentCode") String agentCode) {
		
		LoginResponse response = authService.validateAgentDetail(agentCode,senderJwk);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/v1/logout")
	public ResponseEntity<GenericResponse> userLogout(@RequestHeader("Authorization") String token,
			@RequestHeader("agentCode") String agentCode, HttpServletRequest req) {
		GenericResponse response = new GenericResponse();
		boolean isValid = jwtFactoryService.validateToken(senderJwk, token, agentCode, "WEB", "", "", req);
		if (isValid) {
			response = authService.logoutUser(agentCode,senderJwk);
		}else {
			StatusDescription status = new StatusDescription();
			status.setStatusCode(ConstantManager.Token_expired.getStatusCode());
			status.setStatusDescription(ConstantManager.Token_expired.getDescription());
			response.setStatusDescription(status);
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
