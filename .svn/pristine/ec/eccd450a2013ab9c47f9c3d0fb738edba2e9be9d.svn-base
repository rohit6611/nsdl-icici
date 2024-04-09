package com.altruist.nsdl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "icici_report")
public class ReportModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "date")
	private Date date;

	@Column(name = "application_completed")
	private Integer applicationCompleted;

	@Column(name = "application_pending")
	private Integer applicationPending;

	@Column(name = "amount_collected")
	private Double amountCollected;

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public Integer getApplicationCompleted() {
		return applicationCompleted;
	}

	public Integer getApplicationPending() {
		return applicationPending;
	}

	public Double getAmountCollected() {
		return amountCollected;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setApplicationCompleted(Integer applicationCompleted) {
		this.applicationCompleted = applicationCompleted;
	}

	public void setApplicationPending(Integer applicationPending) {
		this.applicationPending = applicationPending;
	}

	public void setAmountCollected(Double amountCollected) {
		this.amountCollected = amountCollected;
	}

	@Override
	public String toString() {
		return "ReportModel [id=" + id + ", date=" + date + ", applicationCompleted=" + applicationCompleted
				+ ", applicationPending=" + applicationPending + ", amountCollected=" + amountCollected + "]";
	}

}
