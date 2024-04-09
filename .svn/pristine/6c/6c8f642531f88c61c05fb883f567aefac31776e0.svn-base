package com.altruist.nsdl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.altruist.nsdl.model.UsersModel;

@Repository
@Transactional
public interface UsersRepos extends JpaRepository<UsersModel, Long>{

	UsersModel findByAgentCode(String agentCode);

}
