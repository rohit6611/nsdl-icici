package com.altruist.nsdl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.altruist.nsdl.model.StateModel;

@Repository
@Transactional
public interface StateRepos extends JpaRepository<StateModel, Long>{

	@Query(value="select * from icici_state_list order by state_name asc",nativeQuery = true)
	List<StateModel> fetchStateName();

}
