package com.altruist.serviceimpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.altruist.nsdl.dao.CustomerDetailRepos;
import com.altruist.nsdl.dao.CustomerHistoryDetailRepos;
import com.altruist.nsdl.model.CustomerDetailModel;
import com.altruist.nsdl.model.CustomerHistoryDetailModel;
import com.altruist.response.CustomerDetailListResponse;
import com.altruist.response.CustomerDetailResponse;
import com.altruist.response.DashboardResponse;
import com.altruist.response.GenericResponse;
import com.altruist.response.StatusDescription;
import com.altruist.service.CustomerService;
import com.altruist.utils.ConstantManager;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDetailRepos customerDetailRepos;

	@Autowired
	private CustomerHistoryDetailRepos customerHistoryDetailRepos;

	@Override
	public CustomerDetailResponse sendCustomerRequest(CustomerDetailModel customerDetailModel, String agentCode) {
		CustomerDetailResponse response = new CustomerDetailResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);

		try {

			CustomerDetailModel customerModel = customerDetailRepos
					.findByCustomerMsisdn(customerDetailModel.getCustomerMsisdn());
			if (customerModel != null) {

				if ("0".equalsIgnoreCase(customerModel.getInsuranceStatus())) {


					if (customerModel.getCustomerDetailEntryLevel() == 3) {
						if(customerDetailModel.getCustomerName()!="") {
							customerModel.setCustomerName(customerDetailModel.getCustomerName());
						}
						if(customerDetailModel.getCustomerDob()!=null) {
							customerModel.setCustomerDob(customerDetailModel.getCustomerDob());
						}
						if(customerDetailModel.getCustomerGender()!="") {
							customerModel.setCustomerGender(customerDetailModel.getCustomerGender());
						}
						if(customerDetailModel.getCustomerEmail()!="") {
							customerModel.setCustomerEmail(customerDetailModel.getCustomerEmail());
						}
						if(customerDetailModel.getCustomerCity()!="") {
							customerModel.setCustomerCity(customerDetailModel.getCustomerCity());
						}
						if(customerDetailModel.getCustomerRelation()!="") {
							customerModel.setCustomerRelation(customerDetailModel.getCustomerRelation());
						}
						if(customerDetailModel.getCityCode()!="") {
							customerModel.setCityCode(customerDetailModel.getCityCode());
						}
						if(customerDetailModel.getCustomerState()!="") {
							customerModel.setCustomerState(customerDetailModel.getCustomerState());
						}
						if(customerDetailModel.getStateCode()!="") {
							customerModel.setStateCode(customerDetailModel.getStateCode());
						}
						if(customerDetailModel.getCustomerPincode()!="") {
							customerModel.setCustomerPincode(customerDetailModel.getCustomerPincode());
						}
						if(customerDetailModel.getCustomerAadhar()!="") {
							customerModel.setCustomerAadhar(customerDetailModel.getCustomerAadhar());
						}
						if(customerDetailModel.getCustomerPan()!="") {
							customerModel.setCustomerPan(customerDetailModel.getCustomerPan());
						}
						if(customerDetailModel.getCustomerAddress()!="") {
							customerModel.setCustomerAddress(customerDetailModel.getCustomerAddress());
						}
						if(customerDetailModel.getInsuranceStartDate()!=null) {
							customerModel.setInsuranceStartDate(customerDetailModel.getInsuranceStartDate());
						}
						if(customerDetailModel.getInsuranceEndDate()!=null) {
							customerModel.setInsuranceEndDate(customerDetailModel.getInsuranceEndDate());
						}
						if(customerDetailModel.getNomineeName()!="") {
							customerModel.setNomineeName(customerDetailModel.getNomineeName());
						}
						if(customerDetailModel.getNomineeRelation()!="") {
							customerModel.setNomineeRelation(customerDetailModel.getNomineeRelation());
						}
						if(customerDetailModel.getNomineeOccupation()!="") {
							customerModel.setNomineeOccupation(customerDetailModel.getNomineeOccupation());
						}
						if(customerDetailModel.getNomineeDob()!=null) {
							customerModel.setNomineeDob(customerDetailModel.getNomineeDob());
						}
						if(customerDetailModel.getNomineeGender()!="") {
							customerModel.setNomineeGender(customerDetailModel.getNomineeGender());
						}
						if(customerDetailModel.getBasicPremium()!="") {
							customerModel.setBasicPremium(customerDetailModel.getBasicPremium());
						}
						if(customerDetailModel.getGst()!="") {
							customerModel.setGst(customerDetailModel.getGst());
						}
						if(customerDetailModel.getTotalPremium()!="") {
							customerModel.setTotalPremium(customerDetailModel.getTotalPremium());
						}
						if(customerDetailModel.getInsuranceStartDate()!=null) {
							customerModel.setInsuranceStartDate(customerDetailModel.getInsuranceStartDate());
						}
						if(customerDetailModel.getInsuranceEndDate()!=null) {
							customerModel.setInsuranceEndDate(customerDetailModel.getInsuranceEndDate());
						}
						if(customerDetailModel.getTenure()!=null) {
							customerModel.setTenure(customerDetailModel.getTenure());
						}
						if(customerDetailModel.getSumInsured()!="") {
							customerModel.setSumInsured(customerDetailModel.getSumInsured());
						}
						
						customerModel.setUpdateDateTime(new Date());
						customerModel = customerDetailRepos.save(customerModel);
					}

					
					if (customerModel.getCustomerDetailEntryLevel() == 2) {
						if(customerDetailModel.getNomineeName()!="") {
							customerModel.setNomineeName(customerDetailModel.getNomineeName());
						}
						if(customerDetailModel.getNomineeRelation()!="") {
							customerModel.setNomineeRelation(customerDetailModel.getNomineeRelation());
						}
						if(customerDetailModel.getNomineeOccupation()!="") {
							customerModel.setNomineeOccupation(customerDetailModel.getNomineeOccupation());
						}
						if(customerDetailModel.getNomineeDob()!=null) {
							customerModel.setNomineeDob(customerDetailModel.getNomineeDob());
						}
						if(customerDetailModel.getNomineeGender()!="") {
							customerModel.setNomineeGender(customerDetailModel.getNomineeGender());
						}
						
						customerModel.setCustomerDetailEntryLevel(3);
						customerModel.setUpdateDateTime(new Date());

						customerModel = customerDetailRepos.save(customerModel);
					}

					if (customerModel.getCustomerDetailEntryLevel() == 1) {
						if(customerDetailModel.getInsuranceStartDate()!=null) {
							customerModel.setInsuranceStartDate(customerDetailModel.getInsuranceStartDate());
						}
						if(customerDetailModel.getInsuranceEndDate()!=null) {
							customerModel.setInsuranceEndDate(customerDetailModel.getInsuranceEndDate());
						}
						if(customerDetailModel.getTenure()!=null) {
							customerModel.setTenure(customerDetailModel.getTenure());
						}
						if(customerDetailModel.getSumInsured()!="") {
							customerModel.setSumInsured(customerDetailModel.getSumInsured());
						}
						if(customerDetailModel.getBasicPremium()!="") {
							customerModel.setBasicPremium(customerDetailModel.getBasicPremium());
						}
						if(customerDetailModel.getGst()!="") {
							customerModel.setGst(customerDetailModel.getGst());
						}
						if(customerDetailModel.getTotalPremium()!="") {
							customerModel.setTotalPremium(customerDetailModel.getTotalPremium());
						}
						
						customerModel.setCustomerDetailEntryLevel(2);
						customerModel.setUpdateDateTime(new Date());

						customerModel = customerDetailRepos.save(customerModel);
					}

					response.setCustomerModel(customerModel);
					status.setStatusCode(ConstantManager.Success.getStatusCode());
					status.setStatusDescription(ConstantManager.Success.getDescription());

				} else if ("1".equalsIgnoreCase(customerModel.getInsuranceStatus())) {
					status.setStatusCode(ConstantManager.InsuranceAlreadyPurchase.getStatusCode());
					status.setStatusDescription(ConstantManager.InsuranceAlreadyPurchase.getDescription());
				}

			} else {
				long requestId = (long) (Math.random() * 100000000000L);

				customerDetailModel.setCustomerDetailEntryLevel(1);
				customerDetailModel.setDateTime(new Date());
				customerDetailModel.setUpdateDateTime(new Date());
				customerDetailModel.setInsuranceStatus("0");
				customerDetailModel.setAgentCode(agentCode);
				customerDetailModel.setInwardNumber(String.valueOf(requestId));

				customerDetailModel = customerDetailRepos.save(customerDetailModel);

				response.setCustomerModel(customerDetailModel);
				status.setStatusCode(ConstantManager.Success.getStatusCode());
				status.setStatusDescription(ConstantManager.Success.getDescription());
			}

		} catch (Exception e) {
			status.setStatusCode(ConstantManager.Server_Resource_Busy.getStatusCode());
			status.setStatusDescription(ConstantManager.Server_Resource_Busy.getDescription());
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public CustomerDetailResponse sendCustomerRequestUpload(MultipartFile aadharCard, MultipartFile panCard,
			String customerMsisdn) {
		CustomerDetailResponse response = new CustomerDetailResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);

		String aadharCardUploadImage = null, panCardUploadImage = null;

		try {

			CustomerDetailModel customerModel = customerDetailRepos.findByCustomerMsisdn(customerMsisdn);
			if (customerModel != null) {

				if (aadharCard != null && !aadharCard.isEmpty()) {
					String fileName = aadharCard.getOriginalFilename();
					fileName = fileName.replaceAll("\\s", "_");

					long time = System.currentTimeMillis();
					fileName = "aadharCard_" + time + "_" + fileName;

					String fileUplaodBasePathAadharCard = "/opt/icici_insurance/uploads/";

					if (fileUplaodBasePathAadharCard != null) {
						fileUplaodBasePathAadharCard = fileUplaodBasePathAadharCard + "/"
								+ customerModel.getCustomerMsisdn() + "/aadharCard/";
						String fileUploadStatus = uploadFile(fileUplaodBasePathAadharCard, fileName, aadharCard);
						System.out.println(fileUploadStatus);
						if (fileUploadStatus != null) {
							aadharCardUploadImage = "/" + customerModel.getCustomerMsisdn() + "/aadharCard/" + fileName;
						}

					}
					customerModel
							.setCustomerAadharPath("https://insurance-api.altruistindia.com" + aadharCardUploadImage);
				}

				if (panCard != null && !panCard.isEmpty()) {
					String fileName = panCard.getOriginalFilename();
					fileName = fileName.replaceAll("\\s", "_");

					long time = System.currentTimeMillis();
					fileName = "panCard_" + time + "_" + fileName;

					String fileUplaodBasePathPanCard = "/opt/icici_insurance/uploads/";

					if (fileUplaodBasePathPanCard != null) {
						fileUplaodBasePathPanCard = fileUplaodBasePathPanCard + "/" + customerModel.getCustomerMsisdn()
								+ "/panCard/";
						String fileUploadStatus = uploadFile(fileUplaodBasePathPanCard, fileName, panCard);

						if (fileUploadStatus != null) {
							panCardUploadImage = "/" + customerModel.getCustomerMsisdn() + "/panCard/" + fileName;
						}

					}
					customerModel.setCustomerPanPath("https://insurance-api.altruistindia.com" + panCardUploadImage);
				}

				customerModel = customerDetailRepos.save(customerModel);

				response.setCustomerModel(customerModel);
				status.setStatusCode(ConstantManager.Success.getStatusCode());
				status.setStatusDescription(ConstantManager.Success.getDescription());

			} else {
				status.setStatusCode(ConstantManager.InsuranceAlreadyPurchase.getStatusCode());
				status.setStatusDescription(ConstantManager.InsuranceAlreadyPurchase.getDescription());
			}

		} catch (Exception e) {
			status.setStatusCode(ConstantManager.Server_Resource_Busy.getStatusCode());
			status.setStatusDescription(ConstantManager.Server_Resource_Busy.getDescription());
			e.printStackTrace();
		}

		return response;
	}

	private String uploadFile(final String finalUploadFolder, final String finalFileName, final MultipartFile file) {
		String uploadStatus = null;
		try {

			if (file != null && !file.isEmpty()) {

				File dir = new File(finalUploadFolder);
				if (!dir.isDirectory()) {
					dir.mkdirs();
				}
				byte[] bytes = file.getBytes();
				String finalFile = finalUploadFolder + "/" + finalFileName;
				Path path = Paths.get(finalFile);
				Files.write(path, bytes);
				uploadStatus = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return uploadStatus;
	}

	@Override
	public CustomerDetailListResponse fetchCustomerDetailList(String agentCode) {
		CustomerDetailListResponse response = new CustomerDetailListResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);
		try {

			List<CustomerDetailModel> customerList = customerDetailRepos.findByInsuranceStatusAndAgentCodeOrderByDateTimeDesc("0",
					agentCode);

			response.setCustomerList(customerList);
			status.setStatusCode(ConstantManager.Success.getStatusCode());
			status.setStatusDescription(ConstantManager.Success.getDescription());

		} catch (Exception e) {
			status.setStatusCode(ConstantManager.Server_Resource_Busy.getStatusCode());
			status.setStatusDescription(ConstantManager.Server_Resource_Busy.getDescription());
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public CustomerDetailListResponse fetchSoldCustomerDetailList(String agentCode) {
		CustomerDetailListResponse response = new CustomerDetailListResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);
		try {

			List<CustomerDetailModel> customerList = customerDetailRepos.findByInsuranceStatusAndAgentCodeOrderByDateTimeDesc("1",
					agentCode);

			response.setCustomerList(customerList);
			status.setStatusCode(ConstantManager.Success.getStatusCode());
			status.setStatusDescription(ConstantManager.Success.getDescription());

		} catch (Exception e) {
			status.setStatusCode(ConstantManager.Server_Resource_Busy.getStatusCode());
			status.setStatusDescription(ConstantManager.Server_Resource_Busy.getDescription());
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public DashboardResponse fetchDashboardDetail(String agentCode) {
		DashboardResponse response = new DashboardResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);
		try {

			Integer pendingCount = customerDetailRepos.fetchByInsuranceStatusAndAgentCode("0", agentCode);
			Integer soldCount = customerDetailRepos.fetchByInsuranceStatusAndAgentCode("1", agentCode);
			Double commission = customerDetailRepos.fetchByAgentCode(agentCode);
			if (commission == null) {
				commission = 0.0;
			}

			response.setCommsionAmount(commission);
			response.setPendingCount(pendingCount);
			response.setSoldCount(soldCount);

			status.setStatusCode(ConstantManager.Success.getStatusCode());
			status.setStatusDescription(ConstantManager.Success.getDescription());

		} catch (Exception e) {
			status.setStatusCode(ConstantManager.Server_Resource_Busy.getStatusCode());
			status.setStatusDescription(ConstantManager.Server_Resource_Busy.getDescription());
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public GenericResponse removeCustomerDetail(CustomerDetailModel customerDetailModel) {
		GenericResponse response = new GenericResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);
		try {

			Optional<CustomerDetailModel> customerDetails = customerDetailRepos.findById(customerDetailModel.getId());
			if (customerDetails.isPresent()) {
				CustomerDetailModel customerDetail = customerDetails.get();
				customerDetailRepos.delete(customerDetail);

				sendRecordInHistory(customerDetail);

				status.setStatusCode(ConstantManager.Success.getStatusCode());
				status.setStatusDescription(ConstantManager.Success.getDescription());
			} else {
				status.setStatusCode(ConstantManager.CustomerDetailsNotFound.getStatusCode());
				status.setStatusDescription(ConstantManager.CustomerDetailsNotFound.getDescription());
			}

		} catch (Exception e) {
			status.setStatusCode(ConstantManager.Server_Resource_Busy.getStatusCode());
			status.setStatusDescription(ConstantManager.Server_Resource_Busy.getDescription());
			e.printStackTrace();
		}

		return response;
	}

	private void sendRecordInHistory(CustomerDetailModel customerDetail) {
		CustomerHistoryDetailModel customerHistoryDetail = new CustomerHistoryDetailModel();
		try {
			customerHistoryDetail.setAgentCashback(customerDetail.getAgentCashback());
			customerHistoryDetail.setAgentCode(customerDetail.getAgentCode());
			customerHistoryDetail.setBasicPremium(customerDetail.getBasicPremium());
			customerHistoryDetail.setCityCode(customerDetail.getCityCode());
			customerHistoryDetail.setCustomerAadhar(customerDetail.getCustomerAadhar());
			customerHistoryDetail.setCustomerAadharPath(customerDetail.getCustomerAadharPath());
			customerHistoryDetail.setCustomerAddress(customerDetail.getCustomerAddress());
			customerHistoryDetail.setCustomerCity(customerDetail.getCustomerCity());
			customerHistoryDetail.setCustomerDetailEntryLevel(customerDetail.getCustomerDetailEntryLevel());
			customerHistoryDetail.setCustomerDob(customerDetail.getCustomerDob());
			customerHistoryDetail.setCustomerEmail(customerDetail.getCustomerEmail());
			customerHistoryDetail.setCustomerGender(customerDetail.getCustomerGender());
			customerHistoryDetail.setCustomerId(customerDetail.getCustomerId());
			customerHistoryDetail.setCustomerMsisdn(customerDetail.getCustomerMsisdn());
			customerHistoryDetail.setCustomerName(customerDetail.getCustomerName());
			customerHistoryDetail.setCustomerOccupation(customerDetail.getCustomerOccupation());
			customerHistoryDetail.setCustomerPan(customerDetail.getCustomerPan());
			customerHistoryDetail.setCustomerPanPath(customerDetail.getCustomerPanPath());
			customerHistoryDetail.setCustomerPincode(customerDetail.getCustomerPincode());
			customerHistoryDetail.setCustomerRelation(customerDetail.getCustomerRelation());
			customerHistoryDetail.setCustomerState(customerDetail.getCustomerState());
			customerHistoryDetail.setDateTime(customerDetail.getDateTime());
			customerHistoryDetail.setEmployeeValue(customerDetail.getEmployeeValue());
			customerHistoryDetail.setFundedValue(customerDetail.getFundedValue());
			customerHistoryDetail.setGcReferenceNo(customerDetail.getGcReferenceNo());
			customerHistoryDetail.setGst(customerDetail.getGst());
			customerHistoryDetail.setGstinNumber(customerDetail.getGstinNumber());
			customerHistoryDetail.setGstinState(customerHistoryDetail.getGstinState());
			customerHistoryDetail.setHspValue(customerDetail.getHspValue());
			customerHistoryDetail.setInsuranceEndDate(customerDetail.getInsuranceEndDate());
			customerHistoryDetail.setInsuranceStartDate(customerDetail.getInsuranceStartDate());
			customerHistoryDetail.setInwardNumber(customerDetail.getInwardNumber());
			customerHistoryDetail.setLanNo(customerDetail.getLanNo());
			customerHistoryDetail.setNomineeDob(customerDetail.getNomineeDob());
			customerHistoryDetail.setNomineeGender(customerDetail.getNomineeGender());
			customerHistoryDetail.setNomineeName(customerDetail.getNomineeName());
			customerHistoryDetail.setNomineeOccupation(customerDetail.getNomineeOccupation());
			customerHistoryDetail.setNomineeRelation(customerDetail.getNomineeRelation());
			customerHistoryDetail.setProposalNumber(customerDetail.getProposalNumber());
			customerHistoryDetail.setQuoteProposalNo(customerDetail.getQuoteProposalNo());
			customerHistoryDetail.setRemoveDateTime(new Date());
			customerHistoryDetail.setStateCode(customerDetail.getStateCode());
			customerHistoryDetail.setSumInsured(customerDetail.getSumInsured());
			customerHistoryDetail.setTenure(customerDetail.getTenure());
			customerHistoryDetail.setTotalPremium(customerDetail.getTotalPremium());

			customerHistoryDetailRepos.save(customerHistoryDetail);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
