package com.altruist.request;

public class ProposalRequest {

	private String mobileNumber;

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "ProposalRequest [mobileNumber=" + mobileNumber + "]";
	}

}
