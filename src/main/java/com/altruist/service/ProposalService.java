package com.altruist.service;

import com.altruist.request.ProposalRequest;
import com.altruist.response.ProposalResponse;

public interface ProposalService {

	ProposalResponse sendProposalRequest(ProposalRequest proposalRequest);

}
