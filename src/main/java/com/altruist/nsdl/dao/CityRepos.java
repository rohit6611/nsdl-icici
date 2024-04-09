package com.altruist.nsdl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.altruist.nsdl.model.CityModel;

@Repository
@Transactional
public interface CityRepos extends JpaRepository<CityModel, Long>{

	List<CityModel> findByStateCode(String stateCode);

	List<CityModel> findByStateCodeOrderByCityNameAsc(String stateCode);

}
