package com.altruist.nsdl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.altruist.nsdl.model.LocalityModel;

@Repository
@Transactional
public interface LocalityRepos extends JpaRepository<LocalityModel, Long>{

	@Query(value="select distinct(locality_code) from icici_locality_list where city_code=:cityCode",nativeQuery=true)
	List<String> findByCityCode(String cityCode);
	
}
