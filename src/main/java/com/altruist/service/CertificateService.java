package com.altruist.service;

import com.altruist.response.CustomerDetailResponse;

public interface CertificateService {

	CustomerDetailResponse downloadCertificate(String customerMsisdn);

}
