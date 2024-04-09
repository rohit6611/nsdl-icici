package com.altruist.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.altruist.nsdl.dao.TokenRepos;
import com.altruist.nsdl.model.TokenModel;
import com.altruist.service.TokenService;
import com.altruist.utils.HttpRequest;

@Service
public class TokenServiceImpl implements TokenService {

	@Value("${TokenGrantType}")
	private String TokenGrantType;

	@Value("${TokenUsername}")
	private String TokenUsername;

	@Value("${TokenPassword}")
	private String TokenPassword;

	@Value("${TokenScope}")
	private String TokenScope;

	@Value("${TokenPaymentScope}")
	private String TokenPaymentScope;
	
	@Value("${TokenGenericScope}")
	private String TokenGenericScope;

	@Value("${TokenClientId}")
	private String TokenClientId;

	@Value("${TokenClientSecret}")
	private String TokenClientSecret;

	@Value("${TokenUrl}")
	private String TokenUrl;

	@Autowired
	private TokenRepos tokenDao;

	@Override
	@Async("Execute")
	@Scheduled(fixedRate = 2000000)
	public void fetchToken() {
		HttpRequest httpRequest = new HttpRequest();
		try {

			List<TokenModel> tokenList = tokenDao.findByAccessType(TokenScope);
			if (tokenList.size() > 0) {
				for (TokenModel tokenModel : tokenList) {

					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
					String s1 = sdf.format(new Date());
					Date d1 = sdf.parse(s1);
					String s2 = sdf.format(tokenModel.getDateTime());
					Date d2 = sdf.parse(s2);

					System.out.println("d1::" + d1);
					System.out.println("d2::" + d2);
					long difference_In_Time = d1.getTime() - d2.getTime();
					// long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
					System.out.println("difference_In_Time::" + difference_In_Time);
					long difference_In_Minutes = TimeUnit.MILLISECONDS.toMinutes(difference_In_Time);
					System.out.println("difference_In_Minutes::" + difference_In_Minutes);

					if (difference_In_Minutes > 45) {
						String response = httpRequest.sendTokenRequest(TokenGrantType, TokenUsername, TokenPassword,
								TokenScope, TokenClientId, TokenClientSecret, TokenUrl);
						System.out.println("Token Response::" + response);

						JSONObject jsonObj = new JSONObject(response);
						String accessToken = jsonObj.getString("access_token");
						Integer expiresIn = jsonObj.getInt("expires_in");
						String tokenType = jsonObj.getString("token_type");

						tokenModel.setAccessToken(accessToken);
						tokenModel.setDateTime(new Date());
						tokenModel.setExpiresIn(expiresIn);
						tokenModel.setTokenType(tokenType);
						tokenModel.setAccessType(TokenScope);

						tokenDao.save(tokenModel);
					}

				}
			} else {
				System.out.println("TokenScope::"+TokenScope);
				String response = httpRequest.sendTokenRequest(TokenGrantType, TokenUsername, TokenPassword, TokenScope,
						TokenClientId, TokenClientSecret, TokenUrl);
				System.out.println("Token Response::" + response);
				if (response != null) {

					JSONObject jsonObj = new JSONObject(response);
					String accessToken = jsonObj.getString("access_token");
					Integer expiresIn = jsonObj.getInt("expires_in");
					String tokenType = jsonObj.getString("token_type");

					TokenModel tokenModel = new TokenModel();

					tokenModel.setAccessToken(accessToken);
					tokenModel.setDateTime(new Date());
					tokenModel.setExpiresIn(expiresIn);
					tokenModel.setTokenType(tokenType);
					tokenModel.setAccessType(TokenScope);

					tokenDao.save(tokenModel);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	@Async("ExecutePayment")
	@Scheduled(fixedRate = 2000000)
	public void fetchPaymentToken() {
		HttpRequest httpRequest = new HttpRequest();
		try {
			
			List<TokenModel> tokenList = tokenDao.findByAccessType(TokenPaymentScope);
			if (tokenList.size() > 0) {
				for (TokenModel tokenModel : tokenList) {

					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
					String s1 = sdf.format(new Date());
					Date d1 = sdf.parse(s1);
					String s2 = sdf.format(tokenModel.getDateTime());
					Date d2 = sdf.parse(s2);

					System.out.println("d1::" + d1);
					System.out.println("d2::" + d2);
					long difference_In_Time = d1.getTime() - d2.getTime();
					// long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
					System.out.println("difference_In_Time::" + difference_In_Time);
					long difference_In_Minutes = TimeUnit.MILLISECONDS.toMinutes(difference_In_Time);
					System.out.println("difference_In_Minutes::" + difference_In_Minutes);

					if (difference_In_Minutes > 45) {
						String response = httpRequest.sendTokenRequest(TokenGrantType, TokenUsername, TokenPassword,
								TokenPaymentScope, TokenClientId, TokenClientSecret, TokenUrl);
						System.out.println("Token Response::" + response);

						JSONObject jsonObj = new JSONObject(response);
						String accessToken = jsonObj.getString("access_token");
						Integer expiresIn = jsonObj.getInt("expires_in");
						String tokenType = jsonObj.getString("token_type");

						tokenModel.setAccessToken(accessToken);
						tokenModel.setDateTime(new Date());
						tokenModel.setExpiresIn(expiresIn);
						tokenModel.setTokenType(tokenType);
						tokenModel.setAccessType(TokenPaymentScope);

						tokenDao.save(tokenModel);
					}

				}
			} else {
				
				String response = httpRequest.sendTokenRequest(TokenGrantType, TokenUsername, TokenPassword, TokenPaymentScope,
						TokenClientId, TokenClientSecret, TokenUrl);
				System.out.println("Token Response::" + response);
				if (response != null) {

					JSONObject jsonObj = new JSONObject(response);
					String accessToken = jsonObj.getString("access_token");
					Integer expiresIn = jsonObj.getInt("expires_in");
					String tokenType = jsonObj.getString("token_type");

					TokenModel tokenModel = new TokenModel();

					tokenModel.setAccessToken(accessToken);
					tokenModel.setDateTime(new Date());
					tokenModel.setExpiresIn(expiresIn);
					tokenModel.setTokenType(tokenType);
					tokenModel.setAccessType(TokenPaymentScope);

					tokenDao.save(tokenModel);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	@Async("ExecutePDF")
	@Scheduled(fixedRate = 2000000)
	public void fetchGenericToken() {
		HttpRequest httpRequest = new HttpRequest();
		try {
			
			List<TokenModel> tokenList = tokenDao.findByAccessType(TokenGenericScope);
			if (tokenList.size() > 0) {
				for (TokenModel tokenModel : tokenList) {

					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
					String s1 = sdf.format(new Date());
					Date d1 = sdf.parse(s1);
					String s2 = sdf.format(tokenModel.getDateTime());
					Date d2 = sdf.parse(s2);

					System.out.println("d1::" + d1);
					System.out.println("d2::" + d2);
					long difference_In_Time = d1.getTime() - d2.getTime();
					// long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
					System.out.println("difference_In_Time::" + difference_In_Time);
					long difference_In_Minutes = TimeUnit.MILLISECONDS.toMinutes(difference_In_Time);
					System.out.println("difference_In_Minutes::" + difference_In_Minutes);

					if (difference_In_Minutes > 45) {
						String response = httpRequest.sendTokenRequest(TokenGrantType, TokenUsername, TokenPassword,
								TokenGenericScope, TokenClientId, TokenClientSecret, TokenUrl);
						System.out.println("Token Response::" + response);

						JSONObject jsonObj = new JSONObject(response);
						String accessToken = jsonObj.getString("access_token");
						Integer expiresIn = jsonObj.getInt("expires_in");
						String tokenType = jsonObj.getString("token_type");

						tokenModel.setAccessToken(accessToken);
						tokenModel.setDateTime(new Date());
						tokenModel.setExpiresIn(expiresIn);
						tokenModel.setTokenType(tokenType);
						tokenModel.setAccessType(TokenGenericScope);

						tokenDao.save(tokenModel);
					}

				}
			} else {
				
				System.out.println(TokenGenericScope);
				String response = httpRequest.sendTokenRequest(TokenGrantType, TokenUsername, TokenPassword, TokenGenericScope,
						TokenClientId, TokenClientSecret, TokenUrl);
				System.out.println("Token Response::" + response);
				if (response != null) {

					JSONObject jsonObj = new JSONObject(response);
					String accessToken = jsonObj.getString("access_token");
					Integer expiresIn = jsonObj.getInt("expires_in");
					String tokenType = jsonObj.getString("token_type");

					TokenModel tokenModel = new TokenModel();

					tokenModel.setAccessToken(accessToken);
					tokenModel.setDateTime(new Date());
					tokenModel.setExpiresIn(expiresIn);
					tokenModel.setTokenType(tokenType);
					tokenModel.setAccessType(TokenGenericScope);

					tokenDao.save(tokenModel);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
