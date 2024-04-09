package com.altruist.wallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.altruist.wallet.model.WalletModel;

@Repository
@Transactional
public interface WalletRepos extends JpaRepository<WalletModel, Long>{

	WalletModel findByAgentCode(String agentCode);

}
