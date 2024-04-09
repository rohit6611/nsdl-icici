package com.altruist.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.altruist.nsdl.model.CustomerDetailModel;
import com.altruist.nsdl.model.TokenModel;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequest {

	public String sendTokenRequest(String tokenGrantType, String tokenUsername, String tokenPassword, String tokenScope,
			String tokenClientId, String tokenClientSecret, String tokenUrl) {
		String response = "";
		try {

			OkHttpClient client = new OkHttpClient().newBuilder().build();
			MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
			RequestBody body = RequestBody.create(mediaType,
					"grant_type=" + tokenGrantType + "&username=" + tokenUsername + "&password=" + tokenPassword
							+ "&scope=" + tokenScope + "&client_id=" + tokenClientId + "&client_secret="
							+ tokenClientSecret + "");
			Request request = new Request.Builder().url(tokenUrl).method("POST", body)
					.addHeader("Content-Type", "application/x-www-form-urlencoded").build();
			Response apiResponse = client.newCall(request).execute();
			response = apiResponse.body().string();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public static String sendQuoteRequest(String requestBody, String url, TokenModel tokenModel) {

		try {
			OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(60, TimeUnit.MINUTES).build();
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, requestBody);
			Request request = new Request.Builder().url(url).method("POST", body)
					.addHeader("Authorization", "Bearer " + tokenModel.getAccessToken())
					.addHeader("Content-Type", "application/json").build();
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String sendProposalRequest(String requestBody, String url, TokenModel tokenModel) {

		String urlresp = "";
		try {
			HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());

			connection.setDoOutput(true);
			connection.setDoInput(true);

			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer " + tokenModel.getAccessToken());
			OutputStream out = connection.getOutputStream();
			OutputStreamWriter wout = new OutputStreamWriter(out, "UTF-8");
			wout.write(requestBody);
			wout.flush();

			try {
				InputStream i = null;

				if (connection.getResponseCode() == 200) {
					i = connection.getInputStream();
				} else {
					i = connection.getErrorStream();
				}

				int c = 0;

				while ((c = i.read()) != -1) {
					urlresp += (char) c;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlresp;
	}

	public static String sendPolicyCertificateRequest(String requestBody, String url, TokenModel tokenModel,
			CustomerDetailModel customerModel) {
		String link = "";
		String urlresp = "";
		byte[] buffer = new byte[1024];
		double TotalDownload = 0.00;
		int readbyte = 0; // Stores the number of bytes written in each iteration.
		double percentOfDownload = 0.00;
		try {
			HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());

			connection.setDoOutput(true);
			connection.setDoInput(true);

			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer " + tokenModel.getAccessToken());
			//double filesize = (double) connection.getContentLengthLong();
			OutputStream out = connection.getOutputStream();
			OutputStreamWriter wout = new OutputStreamWriter(out, "UTF-8");
			wout.write(requestBody);
			wout.flush();

			try {
				InputStream i = null;

				if (connection.getResponseCode() == 200) {
					i = connection.getInputStream();
				} else {
					i = connection.getErrorStream();
				}

				link = "https://insurance-api.altruistindia.com/" + customerModel.getCustomerMsisdn()
						+ "/policyCertificate.pdf";

				String fileLoc = "/opt/icici_insurance/uploads/" + customerModel.getCustomerMsisdn()
						+ "/policyCertificate.pdf";
				//String fileLoc = "D:\\policyCertificate.pdf";

				BufferedInputStream input = new BufferedInputStream(i);
				FileOutputStream ouputfile = new FileOutputStream(fileLoc);
				BufferedOutputStream bufferOut = new BufferedOutputStream(ouputfile, 1024);

				while ((readbyte = input.read(buffer, 0, 1024)) >= 0) {
					// Writing the content onto the file.
					bufferOut.write(buffer, 0, readbyte);
					// TotalDownload is the total bytes written onto the file.
					TotalDownload += readbyte;
					// Calculating the percentage of download.
//					percentOfDownload = (TotalDownload * 100) / filesize;
//					// Formatting the percentage up to 2 decimal points.
//					String percent = String.format("%.2f", percentOfDownload);
//					System.out.println("Downloaded " + percent + "%");
				}

				System.out.println("Your download is now complete.");
				bufferOut.close();
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return link;
	}

	public static String sendCertificateRequest(String requestBody, String requestUrl, TokenModel tokenModel,
			CustomerDetailModel customerModel) {
		String link = "";
		try {
			byte[] buffer = new byte[1024];
			double TotalDownload = 0.00;
			int readbyte = 0; // Stores the number of bytes written in each iteration.
			double percentOfDownload = 0.00;

			URL url = new URL(requestUrl);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestProperty("Content-Type", "application/json");
			http.setRequestMethod("POST");
			http.setRequestProperty("Authorization", "Bearer " + tokenModel.getAccessToken());
			double filesize = (double) http.getContentLengthLong();

			link = "https://insurance-api.altruistindia.com/" + customerModel.getCustomerMsisdn()
					+ "/policyCertificate.pdf";

			String fileLoc = "/opt/icici_insurance/uploads/" + customerModel.getCustomerMsisdn()
					+ "/policyCertificate.pdf";

			BufferedInputStream input = new BufferedInputStream(http.getInputStream());
			FileOutputStream ouputfile = new FileOutputStream(fileLoc);
			BufferedOutputStream bufferOut = new BufferedOutputStream(ouputfile, 1024);

			while ((readbyte = input.read(buffer, 0, 1024)) >= 0) {
				// Writing the content onto the file.
				bufferOut.write(buffer, 0, readbyte);
				// TotalDownload is the total bytes written onto the file.
				TotalDownload += readbyte;
				// Calculating the percentage of download.
				percentOfDownload = (TotalDownload * 100) / filesize;
				// Formatting the percentage up to 2 decimal points.
				String percent = String.format("%.2f", percentOfDownload);
				System.out.println("Downloaded " + percent + "%");
			}

			System.out.println("Your download is now complete.");
			bufferOut.close();
			input.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return link;
	}

}
