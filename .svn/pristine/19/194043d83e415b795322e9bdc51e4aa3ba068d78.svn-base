package com.altruist.service;

import org.jose4j.jwk.RsaJsonWebKey;

import com.altruist.response.GenericResponse;
import com.altruist.response.LoginResponse;

public interface AuthService {

	LoginResponse validateAgentDetail(String agentCode,RsaJsonWebKey senderJwk);

	GenericResponse logoutUser(String agentCode, RsaJsonWebKey senderJwk);

}
