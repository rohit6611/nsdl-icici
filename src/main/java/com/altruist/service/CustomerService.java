package com.altruist.service;

import org.springframework.web.multipart.MultipartFile;

import com.altruist.nsdl.model.CustomerDetailModel;
import com.altruist.response.CustomerDetailListResponse;
import com.altruist.response.CustomerDetailResponse;
import com.altruist.response.DashboardResponse;
import com.altruist.response.GenericResponse;

public interface CustomerService {

	CustomerDetailResponse sendCustomerRequest(CustomerDetailModel customerDetailModel,String agentCode);

	CustomerDetailResponse sendCustomerRequestUpload(MultipartFile aadharCard, MultipartFile panCard,
			String customerMsisdn);

	CustomerDetailListResponse fetchCustomerDetailList(String agentCode);

	CustomerDetailListResponse fetchSoldCustomerDetailList(String agentCode);

	DashboardResponse fetchDashboardDetail(String agentCode);

	GenericResponse removeCustomerDetail(CustomerDetailModel customerDetailModel);
	
}
