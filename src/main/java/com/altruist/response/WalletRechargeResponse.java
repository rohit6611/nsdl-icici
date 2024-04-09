package com.altruist.response;

import com.altruist.nsdl.model.BranchModel;

public class WalletRechargeResponse {

	private StatusDescription statusDescription;
	private BranchModel branchModel;

	public StatusDescription getStatusDescription() {
		return statusDescription;
	}

	public BranchModel getBranchModel() {
		return branchModel;
	}

	public void setStatusDescription(StatusDescription statusDescription) {
		this.statusDescription = statusDescription;
	}

	public void setBranchModel(BranchModel branchModel) {
		this.branchModel = branchModel;
	}

	@Override
	public String toString() {
		return "WalletRechargeResponse [statusDescription=" + statusDescription + ", branchModel=" + branchModel + "]";
	}

}
