package com.altruist.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jose4j.jwk.RsaJsonWebKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altruist.nsdl.dao.TokenRepos;
import com.altruist.nsdl.dao.UserTokenDetailRepos;
import com.altruist.nsdl.dao.UsersRepos;
import com.altruist.nsdl.model.UserTokenDetailModel;
import com.altruist.nsdl.model.UsersModel;
import com.altruist.request.JwtModel;
import com.altruist.response.GenericResponse;
import com.altruist.response.LoginResponse;
import com.altruist.response.StatusDescription;
import com.altruist.service.AuthService;
import com.altruist.service.JwtFactoryService;
import com.altruist.utils.ConstantManager;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UsersRepos usersRepos;
	
	@Autowired
	JwtFactoryService jwtFactoryService;
	
	@Autowired
	UserTokenDetailRepos userTokenDetailRepos;

	@Override
	public LoginResponse validateAgentDetail(String agentCode,RsaJsonWebKey senderJwk) {
		LoginResponse response = new LoginResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);
		try {

			UsersModel usersModel = usersRepos.findByAgentCode(agentCode);
			
			if (usersModel != null) {

				if (usersModel.getEnabled()) {
					
					String transactionId = generateTransactionId();
					JwtModel jwtNewToken = jwtFactoryService.generateJWT(usersModel.getUsername(),
							transactionId, senderJwk);
					// Generate Referesh Token
					JwtModel jwtRefereshToken = jwtFactoryService
							.generateRefreshJWT(usersModel.getUsername(), transactionId, senderJwk);
					UserTokenDetailModel tokenModel = updateTokenDetail(usersModel,transactionId,jwtNewToken,jwtRefereshToken);
					response.setUserTokenDetail(tokenModel);
					
					status.setStatusCode(ConstantManager.Success.getStatusCode());
					status.setStatusDescription(ConstantManager.Success.getDescription());
					
				} else {
					status.setStatusCode(ConstantManager.UserNotActive.getStatusCode());
					status.setStatusDescription(ConstantManager.UserNotActive.getDescription());
				}

			} else {
				status.setStatusCode(ConstantManager.UserNotFound.getStatusCode());
				status.setStatusDescription(ConstantManager.UserNotFound.getDescription());
			}

		} catch (Exception e) {
			status.setStatusCode(ConstantManager.Server_Resource_Busy.getStatusCode());
			status.setStatusDescription(ConstantManager.Server_Resource_Busy.getDescription());
			e.printStackTrace();
		}
		return response;
	}
	
	private UserTokenDetailModel updateTokenDetail(UsersModel usersModel, String transactionId, JwtModel jwtNewToken,
			JwtModel jwtRefereshToken) { 
		List<UserTokenDetailModel> tokenList = new ArrayList<>();
		try {
			
			tokenList = userTokenDetailRepos.findByUid(usersModel.getAgentCode());
			if(!tokenList.isEmpty()) {
				for(UserTokenDetailModel tokenModel:tokenList) {
					tokenModel.setCurrentDate1(new Date());
					tokenModel.setJwtRefreshToken(jwtRefereshToken.getJwtRefreshToken());
					tokenModel.setRefreshExpiryDate(jwtRefereshToken.getRefreshExpiryDate());
					tokenModel.setRefreshCurrentDate(new Date());
					tokenModel.setJwtToken(jwtNewToken.getJwtToken());
					tokenModel.setExpiryDate(jwtNewToken.getExpiryDate());
					tokenModel.setTokenKey(jwtNewToken.getTokenKey());
					tokenModel.setUid(usersModel.getAgentCode());
					tokenModel.setExpireDate(jwtNewToken.getExpiryDate());
					tokenModel.setSource("1");
					tokenModel.setRefreshKey(jwtRefereshToken.getRefreshKey());
					
					userTokenDetailRepos.save(tokenModel);
					tokenList.add(tokenModel);
				}
				
			}else {
				UserTokenDetailModel tokenModel = new UserTokenDetailModel();
				tokenModel.setCurrentDate1(new Date());
				tokenModel.setJwtRefreshToken(jwtRefereshToken.getJwtRefreshToken());
				tokenModel.setRefreshExpiryDate(jwtRefereshToken.getRefreshExpiryDate());
				tokenModel.setRefreshCurrentDate(new Date());
				tokenModel.setJwtToken(jwtNewToken.getJwtToken());
				tokenModel.setExpiryDate(jwtNewToken.getExpiryDate());
				tokenModel.setUid(usersModel.getAgentCode());
				tokenModel.setTokenKey(jwtNewToken.getTokenKey());
				tokenModel.setExpireDate(jwtNewToken.getExpiryDate());
				tokenModel.setSource("1");
				tokenModel.setRefreshKey(jwtRefereshToken.getRefreshKey());
				
				userTokenDetailRepos.save(tokenModel);
				tokenList.add(tokenModel);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return tokenList.get(0);
	}

	public String generateTransactionId() {
		int otp = 0;
		try {
			otp = (int) (Math.random() * 9000) + 12163;

		} catch (Exception e) {
			e.printStackTrace();
		}
		Long txnid = System.currentTimeMillis() + otp;
		return "" + txnid;
	}

	@Override
	public GenericResponse logoutUser(String agentCode, RsaJsonWebKey senderJwk) {
		GenericResponse response = new GenericResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);
		try {
			
			UsersModel userModel = usersRepos.findByAgentCode(agentCode);
			if(userModel!=null) {
				
				List<UserTokenDetailModel> tokenDetailList = userTokenDetailRepos.findByUid(agentCode);
				if(!tokenDetailList.isEmpty()) {
					
					UserTokenDetailModel tokenDetail = tokenDetailList.get(0);
					userTokenDetailRepos.delete(tokenDetail);
					
					status.setStatusCode(ConstantManager.Success.getStatusCode());
					status.setStatusDescription(ConstantManager.Success.getDescription());
				}else {
					status.setStatusCode(ConstantManager.Success.getStatusCode());
					status.setStatusDescription(ConstantManager.Success.getDescription());
				}
				
			}else {
				status.setStatusCode(ConstantManager.UserNotFound.getStatusCode());
				status.setStatusDescription(ConstantManager.UserNotFound.getDescription());
			}
			
		}catch(Exception e) {
			status.setStatusCode(ConstantManager.Server_Resource_Busy.getStatusCode());
			status.setStatusDescription(ConstantManager.Server_Resource_Busy.getDescription());
			e.printStackTrace();
		}
		
		return response;
	}

}
