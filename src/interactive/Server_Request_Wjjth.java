package interactive;

public class Server_Request_Wjjth {
     private String Request;     //����Ψһ��ʶ
     private String MD5;         //ѹ�����ļ�MD5 ������֤�ļ��Ƿ���ȷ
     private String FileSize;    //ѹ�����ļ���С��������֤�ļ��Ƿ���ȷ�ͽ����ļ���
     private String FileDir;     //�ļ��д����ĸ�Ŀ¼
     private String FileDirName;     //�ļ�������
     private String	 FileZipName;    //����ѹ�������� ������չ��
     private String WhiteFiles;    //�Ի���+��ʾ����(������)���ַ��� ����ǰ���ļ���ԭ

	public String getRequest() {
		return Request;
	}
	public void setRequest(String request) {
		Request = request;
	}
	public String getMD5() {
		return MD5;
	}
	public void setMD5(String md5) {
		MD5 = md5;
	}
	public String getFileSize() {
		return FileSize;
	}
	public void setFileSize(String fileSize) {
		FileSize = fileSize;
	}
	public String getFileDir() {
		return FileDir;
	}
	public void setFileDir(String fileDir) {
		FileDir = fileDir;
	}
	public String getFileDirName() {
		return FileDirName;
	}
	public void setFileDirName(String fileDirName) {
		FileDirName = fileDirName;
	}
	public String getFileZipName() {
		return FileZipName;
	}
	public void setFileZipName(String fileZipName) {
		FileZipName = fileZipName;
	}
	public String getWhiteFiles() {
		return WhiteFiles;
	}
	public void setWhiteFiles(String whiteFiles) {
		WhiteFiles = whiteFiles;
	}


	   
}
