package com.altruist.request;

import java.util.Date;

public class QuotationRequest {

	private Date dateOfBirth;
	private String msisdn;

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	@Override
	public String toString() {
		return "QuotationRequest [dateOfBirth=" + dateOfBirth + ", msisdn=" + msisdn + "]";
	}

}
