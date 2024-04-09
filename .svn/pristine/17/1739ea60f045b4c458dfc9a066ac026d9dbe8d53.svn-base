package com.altruist.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.altruist.nsdl.dao.BranchRepos;
import com.altruist.nsdl.dao.UsersRepos;
import com.altruist.nsdl.model.BranchModel;
import com.altruist.nsdl.model.UsersModel;
import com.altruist.response.StatusDescription;
import com.altruist.response.WalletBalanceResponse;
import com.altruist.response.WalletRechargeResponse;
import com.altruist.service.WalletService;
import com.altruist.utils.ConstantManager;
import com.altruist.wallet.dao.AgentMasterRepos;
import com.altruist.wallet.dao.WalletRepos;
import com.altruist.wallet.model.AgentMasterModel;
import com.altruist.wallet.model.WalletModel;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepos wallRepos;

	@Autowired
	private AgentMasterRepos agentMasterRepos;

	@Autowired
	private UsersRepos usersRepos;

	@Autowired
	private BranchRepos branchRepos;

	@Value("${qr.actual.path}")
	private String QrActualPath;

	@Override
	public WalletBalanceResponse fetchUserWalletBalance(String agentCode) {
		WalletBalanceResponse response = new WalletBalanceResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);

		try {

			AgentMasterModel agentModels = agentMasterRepos.findByAgentCode(agentCode);
			if (agentModels != null) {
				
				AgentMasterModel agentModel = agentMasterRepos.fetchByBrandCode(agentModels.getBranchCode());
				
				if(agentModel!=null) {
					response.setAgentModel(agentModel);
					status.setStatusCode(ConstantManager.Success.getStatusCode());
					status.setStatusDescription(ConstantManager.Success.getDescription());
				}
				
			} else {
				status.setStatusCode(ConstantManager.Agent_code_not_found.getStatusCode());
				status.setStatusDescription(ConstantManager.Agent_code_not_found.getDescription());
			}

		} catch (Exception e) {
			status.setStatusCode(ConstantManager.Server_Resource_Busy.getStatusCode());
			status.setStatusDescription(ConstantManager.Server_Resource_Busy.getDescription());
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public WalletRechargeResponse getUserQrCode(String agentCode) {
		WalletRechargeResponse response = new WalletRechargeResponse();
		StatusDescription status = new StatusDescription();
		response.setStatusDescription(status);

		try {

			UsersModel usersModel = usersRepos.findByAgentCode(agentCode);

			if (usersModel != null) {

				BranchModel branchModel = branchRepos.findByCode(usersModel.getBranchCode());

				if (branchModel != null) {

					if ((branchModel.getQrCode() == null) || ("".equalsIgnoreCase(branchModel.getQrCode()))
							|| (branchModel.getQrCode() == "")) {

						String qrPath = QrActualPath;
						String directoryName = qrPath.concat(branchModel.getCode());
						
						File directory = new File(directoryName);

						if (!directory.exists()) {
							directory.mkdir();

							getQRCode(branchModel.getCode(),
									directoryName + "/" + "QRCode" + branchModel.getCode() + ".png");
							String qrUrl = "https://pan.altruistindia.com/virtual/" + directoryName + "/" + "QRCode"
									+ branchModel.getCode() + ".png";

							branchModel.setQrCode(qrUrl);

							branchRepos.save(branchModel);

							response.setBranchModel(branchModel);
							status.setStatusCode(ConstantManager.Success.getStatusCode());
							status.setStatusDescription(ConstantManager.Success.getDescription());
						}

					} else {
						response.setBranchModel(branchModel);
						status.setStatusCode(ConstantManager.Success.getStatusCode());
						status.setStatusDescription(ConstantManager.Success.getDescription());
					}

				} else {
					status.setStatusCode(ConstantManager.BranchNotFound.getStatusCode());
					status.setStatusDescription(ConstantManager.BranchNotFound.getDescription());
				}

			} else {
				status.setStatusCode(ConstantManager.UserNotFound.getStatusCode());
				status.setStatusDescription(ConstantManager.UserNotFound.getDescription());
			}

		} catch (Exception e) {
			status.setStatusCode(ConstantManager.Server_Resource_Busy.getStatusCode());
			status.setStatusDescription(ConstantManager.Server_Resource_Busy.getDescription());
			e.printStackTrace();
		}

		return response;
	}

	public void getQRCode(String branchcode, String filepath) {
		String upiId = "upi://pay?pa=ALTECH" + branchcode + "@yesbankltd" + "&pn=Altruist Technologies Pvt. Ltd.&tn="
				+ branchcode + "&tr=" + branchcode + "&am=&cu=INR&purpose=Branch Recharge";

		byte[] image = new byte[0];
		try {

			generateQRCodeImage(upiId, 250, 250, filepath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void generateQRCodeImage(String text, int width, int height, String filePath)
			throws WriterException, IOException {

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

		Path path = FileSystems.getDefault().getPath(filePath);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	}

}
