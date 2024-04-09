package com.altruist.wallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.altruist.wallet.model.TransactionDetailModel;

@Repository
@Transactional
public interface TransactionDetailRepos extends JpaRepository<TransactionDetailModel, Long>{

}
