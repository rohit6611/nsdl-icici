package com.altruist.utils;

public final class ConstantManager {
	
	private Integer statusCode;
	private String description;
		
	//GENERAL ConstantManager
	public final static ConstantManager Success = new ConstantManager ( 200, "Success" );
	public final static ConstantManager Agent_code_not_found = new ConstantManager ( 201, "Agent Detail Not Found..!!" );
	public final static ConstantManager wallet_not_found = new ConstantManager ( 201, "Seems you don't have wallet..!!" );
	public final static ConstantManager Token_expired = new ConstantManager ( 400, "Session has been expired..!!" );
	public final static ConstantManager UserNotFound = new ConstantManager ( 202, "User Not found..!!" );
	public final static ConstantManager UserNotActive = new ConstantManager ( 202, "User not active..!!" );
	public final static ConstantManager BranchNotFound = new ConstantManager ( 210, "Branch Code not found..!!" );
	public final static ConstantManager NoRecordFound = new ConstantManager ( 211, "No record found..!!" );
	
	public final static ConstantManager Server_Resource_Busy = new ConstantManager ( 500, "Resources are busy right now. Please try after some time" );
	public final static ConstantManager ApiResponseError = new ConstantManager ( 205, "" );
	public final static ConstantManager InsuranceAlreadyPurchase = new ConstantManager ( 206, "Customer Already purchase the insurance..!!" );
	public final static ConstantManager CustomerDetailsNotFound = new ConstantManager ( 207, "Customer detail not found..!!" );
	public final static ConstantManager QuotationNotReceived = new ConstantManager ( 208, "We haven't receive Quotation for this customer." );
	public final static ConstantManager PaymentFailed = new ConstantManager ( 251, "Your payment has been failed . Please try again after some time." );
	
	public final static ConstantManager InsufficientBalance = new ConstantManager ( 251, "Seems you don't have sufficient balance to proceed for payment. Kindly recharge your wallet." );
	
	private ConstantManager(Integer statusCode, String description) {
		 this.setStatusCode(statusCode);
		 this.setDescription(description);
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
