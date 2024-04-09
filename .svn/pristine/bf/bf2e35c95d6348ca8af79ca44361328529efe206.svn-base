package com.altruist.nsdl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.altruist.nsdl.model.QuoteRequestModel;

@Repository
@Transactional
public interface QuoteRequestRepos extends JpaRepository<QuoteRequestModel, Long>{

	QuoteRequestModel findByMsisdn(String customerMsisdn);

}
