package com.altruist.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.altruist.response.StatusDescription;
import com.altruist.service.ReportService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/report/")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/v1/fetch")
	public ResponseEntity<StatusDescription> fetchReport() {
		StatusDescription response = new StatusDescription();
		response = reportService.fetchReport();
		return new ResponseEntity<StatusDescription>(response, HttpStatus.OK);
	}
	
	@GetMapping("/v1/fetch/hourly")
	public ResponseEntity<StatusDescription> fetchReportHourly() {
		StatusDescription response = new StatusDescription();
		response = reportService.fetchReportHourly();
		return new ResponseEntity<StatusDescription>(response, HttpStatus.OK);
	}
	
}
