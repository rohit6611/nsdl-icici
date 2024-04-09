package com.altruist.response;

import com.altruist.nsdl.model.CustomerDetailModel;

public class CustomerDetailResponse {

	private StatusDescription statusDescription;
	private CustomerDetailModel customerModel;

	public StatusDescription getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(StatusDescription statusDescription) {
		this.statusDescription = statusDescription;
	}

	public CustomerDetailModel getCustomerModel() {
		return customerModel;
	}

	public void setCustomerModel(CustomerDetailModel customerModel) {
		this.customerModel = customerModel;
	}

	@Override
	public String toString() {
		return "CustomerDetailResponse [statusDescription=" + statusDescription + ", customerModel=" + customerModel
				+ "]";
	}

}
