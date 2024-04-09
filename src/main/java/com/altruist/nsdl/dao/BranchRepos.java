package com.altruist.nsdl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.altruist.nsdl.model.BranchModel;

@Repository
@Transactional
public interface BranchRepos extends JpaRepository<BranchModel, Long>{

	BranchModel findByCode(String branchCode);

}
