package com.altruist.service;

import javax.servlet.http.HttpServletRequest;

import org.jose4j.jwk.RsaJsonWebKey;

import com.altruist.request.JwtModel;

public interface JwtFactoryService {

	JwtModel generateJWT(String username, String transactionId, RsaJsonWebKey senderJwk);

	JwtModel generateRefreshJWT(String username, String transactionId, RsaJsonWebKey senderJwk);

	boolean validateToken(RsaJsonWebKey senderJwk, String token, String agentCode,String source,String resource,String ip,HttpServletRequest req);

}
