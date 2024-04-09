package com.altruist.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altruist.nsdl.dao.CityRepos;
import com.altruist.nsdl.dao.LocalityRepos;
import com.altruist.nsdl.dao.StateRepos;
import com.altruist.nsdl.model.CityModel;
import com.altruist.nsdl.model.LocalityModel;
import com.altruist.nsdl.model.StateModel;
import com.altruist.response.CityListResponse;
import com.altruist.response.PinCodeResponse;
import com.altruist.response.StateListResponse;
import com.altruist.response.StatusDescription;
import com.altruist.service.CountryService;
import com.altruist.utils.ConstantManager;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private StateRepos stateRepos;

	@Autowired
	private CityRepos cityRepos;
	
	@Autowired
	private LocalityRepos localityRepos;

	@Override
	public StateListResponse fetchStateList() {
		StateListResponse response = new StateListResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);
		try {

			List<StateModel> stateList = stateRepos.fetchStateName();
			response.setStateList(stateList);

			status.setStatusCode(ConstantManager.Success.getStatusCode());
			status.setStatusDescription(ConstantManager.Success.getDescription());

		} catch (Exception e) {
			status.setStatusCode(ConstantManager.Server_Resource_Busy.getStatusCode());
			status.setStatusDescription(ConstantManager.Server_Resource_Busy.getDescription());
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public CityListResponse fetchCityList(String stateCode) {
		CityListResponse response = new CityListResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);
		try {
			List<CityModel> cityList = cityRepos.findByStateCodeOrderByCityNameAsc(stateCode);
			response.setCityList(cityList);

			status.setStatusCode(ConstantManager.Success.getStatusCode());
			status.setStatusDescription(ConstantManager.Success.getDescription());

		} catch (Exception e) {
			status.setStatusCode(ConstantManager.Server_Resource_Busy.getStatusCode());
			status.setStatusDescription(ConstantManager.Server_Resource_Busy.getDescription());
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public PinCodeResponse fetchPincodeList(String cityCode) {
		PinCodeResponse response = new PinCodeResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);
		try {
			
			List<String> localityList = localityRepos.findByCityCode(cityCode);
			if(localityList.size()>0) {
				
				response.setLocalityList(localityList);
				status.setStatusCode(ConstantManager.Success.getStatusCode());
				status.setStatusDescription(ConstantManager.Success.getDescription());
				
			}else {
				status.setStatusCode(ConstantManager.NoRecordFound.getStatusCode());
				status.setStatusDescription(ConstantManager.NoRecordFound.getDescription());
			}
			
		}catch(Exception e) {
			status.setStatusCode(ConstantManager.Server_Resource_Busy.getStatusCode());
			status.setStatusDescription(ConstantManager.Server_Resource_Busy.getDescription());
			e.printStackTrace();
		}
		
		return response;
	}

}
