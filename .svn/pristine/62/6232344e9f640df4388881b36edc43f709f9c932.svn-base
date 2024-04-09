package com.altruist.nsdl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.altruist.nsdl.model.ReportModel;

@Repository
@Transactional
public interface ReportRepos extends JpaRepository<ReportModel, Long>{

	@Query(value="select * from icici_report where month(date)=:month and year(date)=:year",nativeQuery=true)
	List<ReportModel> findByDate(String month,String year);

}
