package com.altruist.nsdl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.altruist.nsdl.model.AgentReportModel;

@Repository
@Transactional
public interface AgentReportRepos extends JpaRepository<AgentReportModel, Long>{

	@Query(value="select * from icici_agent_report where month(date)=:month and year(date)=:year order by date desc",nativeQuery=true)
	List<AgentReportModel> findByDate(String month, String year);

	@Query(value="select * from icici_agent_report where agent_username=:username and date(date)=:previousDate",nativeQuery=true)
	AgentReportModel findByAgentUsernameAndDate(String username, String previousDate);

}
