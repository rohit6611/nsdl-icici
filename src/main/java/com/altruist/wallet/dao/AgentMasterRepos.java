package com.altruist.wallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.altruist.wallet.model.AgentMasterModel;

@Repository
@Transactional
public interface AgentMasterRepos extends JpaRepository<AgentMasterModel, Long>{

	AgentMasterModel findByAgentCode(String agentCode);

	@Query(value="select * from agent_master where branch_code=:branchCode limit 1",nativeQuery=true)
	AgentMasterModel fetchByBrandCode(String branchCode);

}
