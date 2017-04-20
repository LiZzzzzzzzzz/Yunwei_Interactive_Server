package interactive;

public class Server_Request_Wjjth {
     private String Request;     //功能唯一标识
     private String MD5;         //压缩包文件MD5 用于验证文件是否正确
     private String FileSize;    //压缩包文件大小，用于验证文件是否正确和接收文件。
     private String FileDir;     //文件夹处于哪个目录
     private String FileDirName;     //文件夹名字
     private String	 FileZipName;    //传输压缩包名字 包括扩展名
     private String WhiteFiles;    //以换行+标示区分(解析①)的字符串 将以前的文件还原

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
