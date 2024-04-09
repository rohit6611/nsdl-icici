package com.altruist.response;

import com.altruist.wallet.model.AgentMasterModel;

public class WalletBalanceResponse {

	private StatusDescription statusDescription;
	private AgentMasterModel agentModel;

	public StatusDescription getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(StatusDescription statusDescription) {
		this.statusDescription = statusDescription;
	}

	public AgentMasterModel getAgentModel() {
		return agentModel;
	}

	public void setAgentModel(AgentMasterModel agentModel) {
		this.agentModel = agentModel;
	}

	@Override
	public String toString() {
		return "WalletBalanceResponse [statusDescription=" + statusDescription + ", agentModel=" + agentModel + "]";
	}

}
