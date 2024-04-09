package com.altruist.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.altruist.nsdl.model.AgentReportHourlyModel;
import com.altruist.nsdl.model.ReportModel;

public class Emailer {
	
	public static void getEmailer(List<ReportModel> reportList,String username,String password,String host,String port) {
		Double totalAmount = 0.00;
		Integer incompleteApplication = 0,completeApplication = 0,totalApplication = 0;
		
		try {
			
//			String to = "siddhant.johri@altruistindia.com";
			File file=new File("/opt/icici_insurance/shfiles/Recipient.txt");
			Scanner sc=new Scanner(file);
			String to ="";
			while(sc.hasNextLine()){
			 to=to+sc.nextLine();
			}
			
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
			
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setSubject("Hospi Cash MIS");
			BodyPart messageBodyPart = new MimeBodyPart();
			
			String body = "";
			String footerBody = "";
			
			String msg = "<html>" + "<i>Dear All,</i><br><br>" + "<i>Please find attached the MIS for Hospi Cash Service :-</i><br>";
			
			String table = "<table border=1 bordercolor=black border=1 cellpadding=0 cellspacing=0 style=\"width:1000px\"> "
					+ "<tr bgcolor=\"#D3D3D3\">"
					+ "<td style=\"width:20%;text-align=center;padding:10px\"><b>Date</b></td>"
					+ "<td style=\"width:30%;text-align=center;padding:10px\"><b>Application Incomplete</b></td>"
					+ "<td style=\"width:30%;text-align=center;padding:10px\"><b>Application Completed</b></td>"
					+ "<td style=\"width:30%;text-align=center;padding:10px\"><b>Total Application</b></td>"
					+ "<td style=\"width:30%;text-align=center;padding:10px\"><b>Total Revenue</b></td></tr>";
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			for(ReportModel reportModel : reportList) {
				body = body + "<tr style=\"width:1200px\"><td>" + sdf.format(reportModel.getDate()) + "</td>"
						+ "<td align=center>" + reportModel.getApplicationPending() + "</td>"
						+ "<td align=center>" +reportModel.getApplicationCompleted()+"</td>"
						+ "<td align=center>" +(reportModel.getApplicationPending()+ reportModel.getApplicationCompleted())+"</td>"
						+ "<td align=center>" + reportModel.getAmountCollected() + "</td></tr>";
				incompleteApplication = incompleteApplication + reportModel.getApplicationPending();
				completeApplication = completeApplication + reportModel.getApplicationCompleted();
				totalApplication = incompleteApplication + completeApplication;
				totalAmount = totalAmount + reportModel.getAmountCollected();
			}
			totalAmount = (double) Math.round(totalAmount);
			footerBody = footerBody + "<tr style=\"width:1200px\" bgcolor=\"#D3D3D3\"><td><b>Total</b></td>"
					+ "<td align=center>" + incompleteApplication + "</td>"
					+ "<td align=center>" + completeApplication + "</td>"
					+ "<td align=center>" + totalApplication + "</td>"
					+ "<td align=center>" +totalAmount + "</td></tr>";
			messageBodyPart.setContent(msg + "<br>" + table + body + footerBody+"</table>", "text/html");

			Multipart multipart = new MimeMultipart();
			
			multipart.addBodyPart(messageBodyPart);
			
			messageBodyPart = new MimeBodyPart();
			
			String filename = "HospiCash.xlsx";

			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			
			messageBodyPart.setFileName("HospiCash.xlsx");
			multipart.addBodyPart(messageBodyPart);
			
			message.setContent(multipart);
			
			System.out.println(message.getContent().toString());
			Transport.send(message);

			System.out.println("Sent message successfully....");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getHourlyEmailer(List<AgentReportHourlyModel> reportList,String username,String password,String host,String port) {
		Double totalAmount = 0.00;
		Integer incompleteApplication = 0,completeApplication = 0,totalApplication = 0;
		
		try {
			
			//String to = "siddhant.johri@altruistindia.com";
			File file=new File("/opt/icici_insurance/shfiles/Recipient_Hourly.txt");
			Scanner sc=new Scanner(file);
			String to ="";
			while(sc.hasNextLine()){
			 to=to+sc.nextLine();
			}
			
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
			
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setSubject("Hourly Hospi Cash MIS");
			BodyPart messageBodyPart = new MimeBodyPart();
			
			String body = "";
			String footerBody = "";
			
			String msg = "<html>" + "<i>Dear All,</i><br><br>" + "<i>Please find attached the MIS for Hospi Cash Service :-</i><br>";
			
			String table = "<table border=1 bordercolor=black border=1 cellpadding=0 cellspacing=0 style=\"width:1000px\"> "
					+ "<tr bgcolor=\"#D3D3D3\">"
					+ "<td style=\"width:20%;text-align=center;padding:10px\"><b>Date</b></td>"
					+ "<td style=\"width:20%;text-align=center;padding:10px\"><b>Agent Username</b></td>"
					+ "<td style=\"width:30%;text-align=center;padding:10px\"><b>Application Incomplete</b></td>"
					+ "<td style=\"width:30%;text-align=center;padding:10px\"><b>Application Completed</b></td>"
					+ "<td style=\"width:30%;text-align=center;padding:10px\"><b>Total Application</b></td>"
					+ "<td style=\"width:30%;text-align=center;padding:10px\"><b>Total Revenue</b></td></tr>";
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			for(AgentReportHourlyModel reportModel : reportList) {
				body = body + "<tr style=\"width:1200px\"><td>" + sdf.format(reportModel.getDate()) + "</td>"
						+ "<td align=center>" + reportModel.getAgentUsername() + "</td>"
						+ "<td align=center>" + reportModel.getApplicationIncomplete() + "</td>"
						+ "<td align=center>" +reportModel.getApplicationComplete()+"</td>"
						+ "<td align=center>" +(reportModel.getApplicationIncomplete()+ reportModel.getApplicationComplete())+"</td>"
						+ "<td align=center>" + reportModel.getTotalRevenue() + "</td></tr>";
				incompleteApplication = incompleteApplication + reportModel.getApplicationIncomplete();
				completeApplication = completeApplication + reportModel.getApplicationComplete();
				totalApplication = incompleteApplication + completeApplication;
				totalAmount = totalAmount + reportModel.getTotalRevenue();
			}
			totalAmount = (double) Math.round(totalAmount);
			footerBody = footerBody + "<tr style=\"width:1200px\" bgcolor=\"#D3D3D3\"><td><b>Total</b></td>"
					+ "<td align=center></td>"
					+ "<td align=center>" + incompleteApplication + "</td>"
					+ "<td align=center>" + completeApplication + "</td>"
					+ "<td align=center>" + totalApplication + "</td>"
					+ "<td align=center>" +totalAmount + "</td></tr>";
			messageBodyPart.setContent(msg + "<br>" + table + body + footerBody+"</table>", "text/html");

			Multipart multipart = new MimeMultipart();
			
			multipart.addBodyPart(messageBodyPart);
			
//			messageBodyPart = new MimeBodyPart();
			
//			String filename = "HospiCash.xlsx";
//
//			DataSource source = new FileDataSource(filename);
//			messageBodyPart.setDataHandler(new DataHandler(source));
//			
//			messageBodyPart.setFileName("HospiCash.xlsx");
//			multipart.addBodyPart(messageBodyPart);
			
			message.setContent(multipart);
			
			System.out.println(message.getContent().toString());
			Transport.send(message);

			System.out.println("Sent message successfully....");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
