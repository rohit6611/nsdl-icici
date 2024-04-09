package com.altruist.response;

public class StatusDescription {

	private Integer statusCode;
	private String statusDescription;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	@Override
	public String toString() {
		return "StatusDescription [statusCode=" + statusCode + ", statusDescription=" + statusDescription + "]";
	}

}
