package com.altruist.nsdl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.altruist.nsdl.model.CustomerDetailModel;

@Repository
@Transactional
public interface CustomerDetailRepos extends JpaRepository<CustomerDetailModel, Long>{

	@Query(value="select * from icici_customer_details where customer_msisdn=:customerMsisdn and insurance_status in ('0','1')",nativeQuery=true)
	CustomerDetailModel findByCustomerMsisdn(String customerMsisdn);

	List<CustomerDetailModel> findByInsuranceStatusAndAgentCodeOrderByDateTimeDesc(String insuranceStatus, String agentCode);

	@Query(value="select * from icici_customer_details where customer_msisdn=:mobileNumber and insurance_status in ('0')",nativeQuery=true)
	CustomerDetailModel fetchByCustomerMsisdn(String mobileNumber);

	@Query(value="select count(1) from icici_customer_details where agent_code=:agentCode and insurance_status=:insuranceStatus",nativeQuery=true)
	Integer fetchByInsuranceStatusAndAgentCode(String insuranceStatus, String agentCode);

	@Query(value="select sum(agent_cashback) from icici_customer_details where agent_code=:agentCode and insurance_status in ('1')",nativeQuery=true)
	Double fetchByAgentCode(String agentCode);

	@Query(value="select distinct(agent_code) from icici_customer_details where date(update_datetime)=date(subdate(now(),1))",nativeQuery=true)
	List<String> findByDateTime();
	
	@Query(value="select distinct(agent_code) from icici_customer_details where date(update_datetime)=date(subdate(now(),0))",nativeQuery=true)
	List<String> fetchByDateTime();

	@Query(value="select count(1) from icici_customer_details where agent_code=:agentCode and insurance_status=:insuranceStatus and date(update_datetime)=date(subdate(now(),1))",nativeQuery=true)
	Integer findByAgentCodeAndInsuranceStatus(String agentCode, String insuranceStatus);
	
	@Query(value="select count(1) from icici_customer_details where agent_code=:agentCode and insurance_status=:insuranceStatus and date(update_datetime)=date(subdate(now(),0))",nativeQuery=true)
	Integer fetchByAgentCodeAndInsuranceStatus(String agentCode, String insuranceStatus);

	@Query(value="select ifnull(sum(total_premium),0) from icici_customer_details where agent_code=:agentCode and insurance_status=:insuranceStatus and date(update_datetime)=date(subdate(now(),1))",nativeQuery=true)
	String findByTotalPremium(String agentCode, String insuranceStatus);
	
	@Query(value="select ifnull(sum(total_premium),0) from icici_customer_details where agent_code=:agentCode and insurance_status=:insuranceStatus and date(update_datetime)=date(subdate(now(),0))",nativeQuery=true)
	String fetchByTotalPremium(String agentCode, String insuranceStatus);

}
