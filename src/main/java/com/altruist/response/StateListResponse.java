package com.altruist.response;

import java.util.List;

import com.altruist.nsdl.model.StateModel;

public class StateListResponse {

	private StatusDescription statusDescription;
	private List<StateModel> stateList;

	public List<StateModel> getStateList() {
		return stateList;
	}

	public void setStateList(List<StateModel> stateList) {
		this.stateList = stateList;
	}

	public StatusDescription getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(StatusDescription statusDescription) {
		this.statusDescription = statusDescription;
	}

	@Override
	public String toString() {
		return "StateListResponse [statusDescription=" + statusDescription + ", stateList=" + stateList + "]";
	}

}
