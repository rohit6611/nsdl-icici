package com.altruist.serviceimpl;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jose4j.base64url.Base64;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.InvalidJwtSignatureException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altruist.nsdl.dao.UserTokenDetailRepos;
import com.altruist.nsdl.model.UserTokenDetailModel;
import com.altruist.request.JwtModel;
import com.altruist.service.JwtFactoryService;
import com.altruist.utils.Configurator;

@Service
public class JwtFactoryServiceImpl implements JwtFactoryService {

	private Logger logger = LogManager.getLogger(JwtFactoryServiceImpl.class);

	private final String JWT_ISSUER_NEW_TOKEN = "Alt_Secure_Validate_Token";
	private final String JWT_ISSUER_REFERESH_TOKEN = "Alt_Secure_Referesh_Token";
	private float JWT_EXPIRY_TIME = 525610;
	private float JWT_REFRESH_EXPIRY_TIME = 525610;
	private Configurator config = Configurator.getInstance();

	@Autowired
	private UserTokenDetailRepos userTokenDetailRepos;

	@Override
	public JwtModel generateJWT(String username, String transactionId, RsaJsonWebKey senderJwk) {
		String jwt = "";
		JwtModel jwtReqInstance = null;
		try {

			// System.out.println(msisdn +" transactionId: "+transactionId +" senderJwk:
			// "+senderJwk.getRsaPrivateKey());
			JwtClaims claims = new JwtClaims();
			claims.setIssuer(JWT_ISSUER_NEW_TOKEN);
			claims.setExpirationTimeMinutesInTheFuture(JWT_EXPIRY_TIME);
			claims.setGeneratedJwtId();
			claims.setIssuedAtToNow();
			claims.setNotBeforeMinutesInThePast(2);
			claims.setSubject(username);

			// Creates the JKT Signature
			JsonWebSignature jws = new JsonWebSignature();

			// Header for creating signature
			jws.setHeader("type", "JWT");
			jws.setHeader("kid", transactionId);

			// payload for creating signature
			jws.setPayload(claims.toJson());

			// secret key for creating signature
			jws.setKey(senderJwk.getRsaPrivateKey());

			// algorithm type to create the final signature
			jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA512);

			// creates the final key separated by .(DOT)
			// HEADER.PAYLOAD.SIGNATURE
			jwt = jws.getCompactSerialization();

			Date expiryDate = new Date(claims.getExpirationTime().getValueInMillis());

			String key = Base64.encode(senderJwk.getRsaPublicKey().getEncoded());

			jwtReqInstance = new JwtModel();
			jwtReqInstance.setUsername(username);
			jwtReqInstance.setTransactionId(transactionId);
			jwtReqInstance.setExpiryDate(expiryDate);
			jwtReqInstance.setJwtToken(jwt);
			jwtReqInstance.setTokenKey(key);

			return jwtReqInstance;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public JwtModel generateRefreshJWT(String username, String transactionId, RsaJsonWebKey senderJwk) {
		String jwt = "";
		JwtModel jwtReqInstance = null;
		try {

			// Adds values to JWT Payload
			JwtClaims claims = new JwtClaims();
			claims.setIssuer(JWT_ISSUER_REFERESH_TOKEN);
			claims.setExpirationTimeMinutesInTheFuture(JWT_REFRESH_EXPIRY_TIME);
			claims.setGeneratedJwtId();
			claims.setIssuedAtToNow();
			claims.setNotBeforeMinutesInThePast(2);
			claims.setSubject(username);

			// Creates the JKT Signature
			JsonWebSignature jws = new JsonWebSignature();

			// Header for creating signature
			jws.setHeader("type", "JWT");
			jws.setHeader("kid", transactionId);

			// payload for creating signature
			jws.setPayload(claims.toJson());

			// secret key for creating signature
			jws.setKey(senderJwk.getRsaPrivateKey());

			// algorithm type to create the final signature
			jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA512);

			// creates the final key separated by .(DOT)
			// HEADER.PAYLOAD.SIGNATURE
			jwt = jws.getCompactSerialization();

			Date refreshExpiryDate = new Date(claims.getExpirationTime().getValueInMillis());

			String refreshKey = Base64.encode(senderJwk.getRsaPublicKey().getEncoded());

			// Set Properties Here
			jwtReqInstance = new JwtModel();
			jwtReqInstance.setUsername(username);
			;
			jwtReqInstance.setTransactionId(transactionId);
			jwtReqInstance.setRefreshExpiryDate(refreshExpiryDate);
			jwtReqInstance.setJwtRefreshToken(jwt);
			jwtReqInstance.setRefreshKey(refreshKey);
			return jwtReqInstance;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean validateToken(RsaJsonWebKey senderJwk, String token, String userId, String source, String resource,
			String ip, HttpServletRequest req) {

		boolean isValidToken = false, isAllowed = true;
		try {
			long startTime = System.currentTimeMillis();

			if (isAllowed) {

				RsaJsonWebKey validateJwk = validateInDBToken(senderJwk, token, userId, source);
				if (validateJwk.getAlgorithm().contains("Token Verified Successfully")) {
					isValidToken = true;
				} else {
					// requestsInterceptorsService.save(userId, token, source);
				}
			}
			long duration = (System.currentTimeMillis() - startTime);

			//logger.info("ExecTime in Token Verification:[User: " + userId + "][Duration:" + duration + "]");

		} catch (Exception e) {
			// e.printStackTrace();
			// System.out.println("****** Exception while Validating Token
			// "+e.getMessage());
			// requestsInterceptorsService.save(userId, token, source);
		}
		return isValidToken;
	}

	public boolean filterIP(HttpServletRequest req) {
		boolean isAllowed = false;
		try {
			String allowedIPs = config.getProperty("allowedIPs");

			String ipInHeader = req.getHeader("x-forwarded-for");

			String ipInRequest = req.getRemoteAddr();

			if (allowedIPs.contains(ipInRequest)) {
				isAllowed = true;
			}

			if (ipInHeader != null) {

				if (allowedIPs.contains(ipInHeader.trim())) {
					isAllowed = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAllowed;
	}

	private RsaJsonWebKey validateInMemoryToken(RsaJsonWebKey senderJwk, String token, String id) {

		JwtConsumer jwtConsumer = null;
		JwtClaims jwtClaims = null;

		try {
			System.out.println("in try.......");
			jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime().setAllowedClockSkewInSeconds(30)
					.setRequireSubject().setExpectedIssuer(JWT_ISSUER_NEW_TOKEN)
					.setVerificationKey(senderJwk.getRsaPublicKey()).build();

			jwtClaims = jwtConsumer.processToClaims(token);

			if (!jwtClaims.getIssuer().equalsIgnoreCase("")) {
				senderJwk.setAlgorithm("JWT Token Verified Successfully !!");
			}

			System.out.println("Verification Successfull ::-->> " + jwtClaims);
		} catch (InvalidJwtSignatureException sigException) {
			System.out.println("in catch");
			senderJwk = validateInDBToken(senderJwk, token, id, "WEB");
		} catch (InvalidJwtException invalidJwtException) {

			// For handling jwt token expiry time
			if (invalidJwtException.getMessage().contains("The JWT is no longer valid - the evaluation time")) {
				senderJwk.setAlgorithm("JWT Token is no longer valid !!");
			}
		} catch (Exception ex) {

			ex.printStackTrace();
			senderJwk.setAlgorithm(ex.getMessage());
			return senderJwk;
		}
		return senderJwk;
	}

	private final RsaJsonWebKey validateInDBToken(RsaJsonWebKey senderJwk, String token, String userId, String source) {

		JwtConsumer jwtConsumer = null;
		JwtClaims jwtClaims = null;

		try {
			List<UserTokenDetailModel> userTokenDetails = userTokenDetailRepos.findByUid(userId);
			//System.out.println(userTokenDetails);
			if (userTokenDetails != null && userTokenDetails.size() > 0) {

				for (UserTokenDetailModel userToken : userTokenDetails) {
					if (userToken.getJwtToken().equals(token)) {

						X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(Base64.decode(userToken.getTokenKey()));
						KeyFactory keyFactory = KeyFactory.getInstance("RSA");
						RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);

						senderJwk = new RsaJsonWebKey(publicKey);
						jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime()
								.setAllowedClockSkewInSeconds(1).setRequireSubject()
								.setExpectedIssuer(JWT_ISSUER_NEW_TOKEN).setVerificationKey(senderJwk.getRsaPublicKey())
								.build();
						jwtClaims = jwtConsumer.processToClaims(token);

						//System.out.println("jwtClaims.getIssuer(): " + jwtClaims.getIssuer());

						if (!jwtClaims.getIssuer().equalsIgnoreCase("")) {
							//System.out.println("Inside Block ");

							senderJwk.setAlgorithm("JWT Token Verified Successfully !!");
						}

						//System.out.println("Verification Successful Catc :: " + jwtClaims);
					} else {
						senderJwk.setAlgorithm("The JWT is no longer valid");
					}
				}
			}

		} catch (InvalidJwtException invalidJwtException) {
			//System.out.println(invalidJwtException.getMessage());
			if (invalidJwtException.getMessage().contains("The JWT is no longer valid - the evaluation time")
					|| invalidJwtException.getMessage().contains("JWS signature is invalid")
					|| invalidJwtException.getMessage().contains("rejected due to invalid claims")) {
				senderJwk.setAlgorithm("JWT Token is no longer valid");
			}

		} catch (Exception exdb) {

			exdb.printStackTrace();
			senderJwk.setAlgorithm(exdb.getMessage());
		}

		return senderJwk;

	}

}
