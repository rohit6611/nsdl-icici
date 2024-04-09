package com.altruist.nsdl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.altruist.nsdl.model.UserTokenDetailModel;

@Repository
@Transactional
public interface UserTokenDetailRepos extends JpaRepository<UserTokenDetailModel, Long>{

	List<UserTokenDetailModel> findByUid(String userId);

}
