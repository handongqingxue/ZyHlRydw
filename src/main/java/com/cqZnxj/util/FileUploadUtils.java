package com.cqZnxj.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.SocketException;

/**
 * �ļ��ϴ�������
 *
 * @author lenovo
 */
public class FileUploadUtils {

	private static Class<? extends Object> cls = FileUploadUtils.class;
	
	//��Ѷ�����ϴ����ļ�(by ʯ��)
	public static String appUploadFile(MultipartFile myFile, String folder) throws Exception {
		try {
			//�����ļ���
			long time = System.currentTimeMillis();
			String timeStr = String.valueOf(time);
			String[] originalFileName = myFile.getOriginalFilename().split("\\.");
			String fileName = timeStr + "." + originalFileName[1];
			String avaPath ="/CqZnxj/upload/"+folder+fileName;
			String realPath="D:\\resource\\CqZnxj\\"+folder+"\\";
			System.out.println(avaPath);
			File storeFile =  new File(realPath, fileName);
			FileUtils.copyInputStreamToFile(myFile.getInputStream(),storeFile );
			
			JSONObject map = new JSONObject();
			map.put("code", 0);
			map.put("msg", "�ɹ�");
			JSONObject js = new JSONObject();
			js.put("src", avaPath);
			map.put("data", js);
			return map.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("");
		}
	}
	

	//��ȡftp���ӳɹ���������ʧ�����׳��쳣
	public static FTPClient getFTPClient(String ftpHost, Integer ftpPort, String userName, String password) throws Exception {
		try {
			//����ftp����
			FTPClient ftpClient = new FTPClient();
			int port = ftpPort == null ? 21 : ftpPort;
			//���������Ͷ˿ڽ�������
			ftpClient.connect(ftpHost, port);
			//�û����������½
			ftpClient.login(userName, password);
			;
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				ftpClient.disconnect();
				throw new Exception(ftpClient.getReplyString());
			}
			return ftpClient;
		} catch (SocketException e1) {
			throw new SocketException(e1.getMessage());
		} catch (IOException e2) {
			throw new IOException(e2.getMessage());
		} catch (Exception e3) {
			throw new Exception(e3.getMessage());
		}
	}

	//��ȡftp���������ļ�����
	public static String readFileForFTP(FTPClient ftpClient, String ftpPath, String fileName) {
		StringBuffer resultBuffer = new StringBuffer();
		FileInputStream inFile = null;
		InputStream in = null;
		try {
			//���ñ���
			ftpClient.setControlEncoding("UTF-8");
			//�ļ�����
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			//
			ftpClient.enterLocalPassiveMode();
			//�ı���ʵ�ftp������Ŀ¼
			ftpClient.changeWorkingDirectory(ftpPath);
			//���ݵ�ǰ�ļ��µ��ļ��������ļ�
			in = ftpClient.retrieveFileStream(fileName);
		} catch (FileNotFoundException e1) {
			return "���������ļ�ʧ�ܣ�����ϵ����Ա.";
		} catch (SocketException e2) {
		} catch (IOException e3) {
			return "�����ļ���ȡʧ�ܣ�����ϵ����Ա.";
		}
		//�������յ���������
		if (in != null) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
			String data = null;
			try {
				while ((data = bufferedReader.readLine()) != null) {
					resultBuffer.append(data).append("\n");
				}
			} catch (IOException e1) {
			} finally {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
				}
			}
		} else {
			return null;
		}
		return resultBuffer.toString();
	}


	//�ϴ���ftp�������ļ�����
	public static void uploadFileForFTP(FTPClient ftpClient, String ftpFileName, String writeTempFilePath, String operatePath) throws Exception {
		try {
			//����passiveMode����
			ftpClient.enterLocalPassiveMode();
			//���ô��䷽ʽ
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			//��Զ��Ŀ¼�Ĵ���
			String remoteFileName = ftpFileName;
			//�ı����Ŀ¼
			ftpClient.changeWorkingDirectory(operatePath);
			//����д��ɹ�
			File file = new File(writeTempFilePath);
			InputStream in = new FileInputStream(file);
			ftpClient.storeFile(remoteFileName, in);
			in.close();
			file.delete();
			return;
		} catch (Exception e) {
			ftpClient.disconnect();
			throw new Exception("�ϴ�ͼƬ��������ʧ��");
		} finally {
			try {
				ftpClient.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static String getRootDirectory() {
		return "http://120.27.5.36:8500/";
	}


}