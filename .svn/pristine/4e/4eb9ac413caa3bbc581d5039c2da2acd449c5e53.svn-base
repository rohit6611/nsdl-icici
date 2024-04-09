package com.altruist.nsdl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.altruist.nsdl.model.AgentReportHourlyModel;

@Repository
@Transactional
public interface AgentReportHourlyRepos extends JpaRepository<AgentReportHourlyModel, Long>{

	@Query(value="select * from icici_agent_report_hourly where date(date)=date(subdate(now(),0)) order by date desc",nativeQuery=true)
	List<AgentReportHourlyModel> findByDate(String year, String month);

	@Query(value="select * from icici_agent_report_hourly where date(date)=date(subdate(now(),0)) and agent_username=:agentUsername",nativeQuery=true)
	AgentReportHourlyModel findByAgentUsername(String agentUsername);

}
