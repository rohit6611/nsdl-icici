package com.altruist.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.altruist.nsdl.dao.AgentReportHourlyRepos;
import com.altruist.nsdl.dao.AgentReportRepos;
import com.altruist.nsdl.dao.CustomerDetailRepos;
import com.altruist.nsdl.dao.ReportRepos;
import com.altruist.nsdl.dao.UsersRepos;
import com.altruist.nsdl.model.AgentReportHourlyModel;
import com.altruist.nsdl.model.AgentReportModel;
import com.altruist.nsdl.model.CustomerDetailModel;
import com.altruist.nsdl.model.ReportModel;
import com.altruist.nsdl.model.UsersModel;
import com.altruist.response.StatusDescription;
import com.altruist.service.ReportService;
import com.altruist.utils.Attachment;
import com.altruist.utils.ConstantManager;
import com.altruist.utils.Emailer;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepos reportRepos;

	@Autowired
	private CustomerDetailRepos customerDetailRepos;

	@Autowired
	private AgentReportRepos agentReportRepos;
	
	@Autowired
	private AgentReportHourlyRepos agentReportHourlyRepos;

	@Autowired
	private UsersRepos usersRepos;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.properties.mail.smtp.socketfactory.port}")
	private String port;

	@Override
	public StatusDescription fetchReport() {
		StatusDescription response = new StatusDescription();
		String month = "";
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String previousDate = sdf.format(cal.getTime());

		try {
			if("01".equalsIgnoreCase(sdfDate.format(date))) {
				Calendar cal1 = Calendar.getInstance();
				cal1.add(Calendar.DATE, -1);
				  
				 month= sdfMonth.format(cal.getTime());
			}else {
				month = sdfMonth.format(date);
			}
			List<ReportModel> reportList = reportRepos.findByDate(month, sdfYear.format(date));
			List<String> customerList = customerDetailRepos.findByDateTime();
			if (customerList.size() > 0) {
				for (String agentCode : customerList) {
					UsersModel users = usersRepos.findByAgentCode(agentCode);
					if (users != null) {

						AgentReportModel agentReportModel = new AgentReportModel();
						agentReportModel.setAgentUsername(users.getUsername());
						Integer incompleteApplication = customerDetailRepos.findByAgentCodeAndInsuranceStatus(agentCode,
								"0");
						agentReportModel.setApplicationIncomplete(incompleteApplication);
						Integer completeApplication = customerDetailRepos.findByAgentCodeAndInsuranceStatus(agentCode,
								"1");
						agentReportModel.setApplicationComplete(completeApplication);
						String totalRevenue = customerDetailRepos.findByTotalPremium(agentCode, "1");
						agentReportModel.setTotalRevenue(Double.valueOf(totalRevenue));

						agentReportModel.setDate(previousDate);

						agentReportRepos.save(agentReportModel);
					}
				}
			}
			
			if("01".equalsIgnoreCase(sdfDate.format(date))) {
				Calendar cal1 = Calendar.getInstance();
				cal1.add(Calendar.DATE, -1);
				  
				 month= sdfMonth.format(cal.getTime());
			}else {
				month = sdfMonth.format(date);
			}
			
			List<AgentReportModel> agentReportList = agentReportRepos.findByDate(month,
					sdfYear.format(date));
			Attachment.createExcelSheet(agentReportList);

			Emailer.getEmailer(reportList, username, password, host, port);

			response.setStatusCode(ConstantManager.Success.getStatusCode());
			response.setStatusDescription(ConstantManager.Success.getDescription());

		} catch (Exception e) {
			response.setStatusCode(ConstantManager.ApiResponseError.getStatusCode());
			response.setStatusDescription(ConstantManager.ApiResponseError.getDescription());
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public StatusDescription fetchReportHourly() {
		StatusDescription response = new StatusDescription();
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DATE, 0);
//		String sameDate = sdf.format(cal.getTime());
		
		try {
			
			List<AgentReportHourlyModel> reportHourList = agentReportHourlyRepos.findByDate(sdfYear.format(date),sdfMonth.format(date));
			if(reportHourList.size()>0) {
				for(AgentReportHourlyModel reportHourModel : reportHourList) {
					List<String> customerList = customerDetailRepos.fetchByDateTime();
					if (customerList.size() > 0) {
						for (String agentCode : customerList) {
							UsersModel users = usersRepos.findByAgentCode(agentCode);
							if (users != null) {

								AgentReportHourlyModel hourModel = agentReportHourlyRepos.findByAgentUsername(users.getUsername());
								
								if(hourModel!=null) {
									Integer incompleteApplication = customerDetailRepos.fetchByAgentCodeAndInsuranceStatus(agentCode,
											"0");
									hourModel.setApplicationIncomplete(incompleteApplication);
									
									Integer completeApplication = customerDetailRepos.fetchByAgentCodeAndInsuranceStatus(agentCode,
											"1");
									hourModel.setApplicationComplete(completeApplication);
									
									String totalRevenue = customerDetailRepos.fetchByTotalPremium(agentCode, "1");
									hourModel.setTotalRevenue(Double.valueOf(totalRevenue));

									hourModel.setDate(new Date());

									agentReportHourlyRepos.save(hourModel);
								}else {
									hourModel = new AgentReportHourlyModel();
									hourModel.setAgentUsername(users.getUsername());
									
									Integer incompleteApplication = customerDetailRepos.fetchByAgentCodeAndInsuranceStatus(agentCode,
											"0");
									hourModel.setApplicationIncomplete(incompleteApplication);
									
									Integer completeApplication = customerDetailRepos.fetchByAgentCodeAndInsuranceStatus(agentCode,
											"1");
									hourModel.setApplicationComplete(completeApplication);
									
									String totalRevenue = customerDetailRepos.fetchByTotalPremium(agentCode, "1");
									hourModel.setTotalRevenue(Double.valueOf(totalRevenue));

									hourModel.setDate(new Date());

									agentReportHourlyRepos.save(hourModel);
								}
							}
						}
					}
				}
			}else {
				List<String> customerList = customerDetailRepos.fetchByDateTime();
				if (customerList.size() > 0) {
					for (String agentCode : customerList) {
						UsersModel users = usersRepos.findByAgentCode(agentCode);
						if (users != null) {

							AgentReportHourlyModel agentReportModel = new AgentReportHourlyModel();
							agentReportModel.setAgentUsername(users.getUsername());
							
							Integer incompleteApplication = customerDetailRepos.fetchByAgentCodeAndInsuranceStatus(agentCode,
									"0");
							agentReportModel.setApplicationIncomplete(incompleteApplication);
							
							Integer completeApplication = customerDetailRepos.fetchByAgentCodeAndInsuranceStatus(agentCode,
									"1");
							agentReportModel.setApplicationComplete(completeApplication);
							
							String totalRevenue = customerDetailRepos.fetchByTotalPremium(agentCode, "1");
							agentReportModel.setTotalRevenue(Double.valueOf(totalRevenue));

							agentReportModel.setDate(new Date());

							agentReportHourlyRepos.save(agentReportModel);
						}
					}
				}
				
			}
			
			List<AgentReportHourlyModel> agentReportHourList = agentReportHourlyRepos.findByDate(sdfMonth.format(date),
					sdfYear.format(date));
			
			Emailer.getHourlyEmailer(agentReportHourList, username, password, host, port);
			
			response.setStatusCode(ConstantManager.Success.getStatusCode());
			response.setStatusDescription(ConstantManager.Success.getDescription());
			
		} catch (Exception e) {
			response.setStatusCode(ConstantManager.ApiResponseError.getStatusCode());
			response.setStatusDescription(ConstantManager.ApiResponseError.getDescription());
			e.printStackTrace();
		}

		return response;
	}

}
