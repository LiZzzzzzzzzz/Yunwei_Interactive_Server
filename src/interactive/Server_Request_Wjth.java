package interactive;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;


public class Server_Request_Wjth {
     private String MD5;         //文件MD5 用于验证文件是否正确
     private String FileSize;    //文件大小，用于验证文件是否正确和接收文件。
     private String FileDir;     //文件处于哪个目录
     private String FileName;    //文件名 包括扩展名
     private Socket clientSocket;
     public Server_Request_Wjth(Socket client)
     {
     	this.clientSocket=client;
     }
	
	 public boolean DealWith(String RequestString)
	 {
		Server_Json server_json=new Server_Json();
		try {
		 InputStream is=clientSocket.getInputStream();
   	     OutputStream os= clientSocket.getOutputStream(); 
         LOG.ReleaseLogger("开始接收数据线程！");
         
         
         
         JSONObject json = null;
         //解析json
		 if(!server_json.resolvejson(RequestString,json))
		 {
			 LOG.ReleaseLogger("解析json错误，结束本次连接！");
			 os.write(server_json.getResponseString().getBytes());
             os.flush();
             is.close();
             os.close(); 
             return false;
		 }
		 
		 //将json值转移到变量
		 if(!MakeParameter(server_json, json))
		 {
			LOG.ReleaseLogger("解析json错误，结束本次连接！");
			os.write(server_json.getResponseString().getBytes());
            os.flush();
            is.close();
            os.close(); 
            return false;
			
		 }
		 
		 
		 
		} catch (IOException e) {
			LOG.ReleaseLogger("服务器接收发送异常！");    
			LOG.getTrace(e);
			return false;
		
	    }  finally {    
         if (clientSocket != null) {    
             try {    
            	 clientSocket.close();    
             } catch (Exception e) {    
            	 clientSocket = null;    
                 LOG.ReleaseLogger("服务器关闭异常！");    
                 LOG.ReleaseLogger(LOG.getTrace(e));
             }    
         }    
     }   
	 return true;
	 }
	
	
	 public boolean MakeParameter(Server_Json server_json,JSONObject json)
	 {
		 
		 if(!json.has("MD5")||!json.has("FileSize")||!json.has("FileDir")||!json.has("FileName"))
		 {
			 server_json.Responseqscs(server_json.getRequest());
			 return false;
		 }
		 try {
		 MD5=json.getString("MD5");
		 FileSize=json.getString("FileSize");
		 FileDir=json.getString("FileDir");
		 FileName=json.getString("FileName");
		 } catch (JSONException e) {
				// TODO 自动生成的 catch 块
			   LOG.ReleaseLogger("json处理错误！");
			   LOG.ReleaseLogger(LOG.getTrace(e));
			   server_json.Responsebzqxy();
			   return false;
			}
		return true;
	 }
	
	   
}
