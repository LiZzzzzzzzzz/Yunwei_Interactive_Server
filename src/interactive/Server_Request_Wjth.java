package interactive;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import sun.nio.cs.ext.ISCII91;


public class Server_Request_Wjth {
     private String Request;     //功能唯一标识
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
         
         
         
		 
		 if(!server_json.resolvejson(RequestString))
		 {
			 LOG.ReleaseLogger("解析json错误，结束本次连接！");
			 os.write(server_json.getResponseString().getBytes());
             os.flush();
             is.close();
             os.close(); 
             return false;
		 }
		 
		//转换成json
		JSONObject json=new JSONObject(RequestString);
		 
		 
		 
		} catch (IOException e) {
			LOG.ReleaseLogger("服务器接收发送异常！");    
			LOG.getTrace(e);
			return false;
		
	    } catch (JSONException e) {
	    	LOG.ReleaseLogger("json转换错误，错误的请求串！");
			LOG.getTrace(e);
			server_json.Responsebzqxy();
			return false;
		} finally {    
         if (clientSocket != null) {    
             try {    
            	 clientSocket.close();    
             } catch (Exception e) {    
            	 clientSocket = null;    
                 LOG.ReleaseLogger("服务器关闭异常！");    
                 LOG.getTrace(e);
             }    
         }    
     }   
	 return true;
	 }
	
	
	
	
	
	
	
	
	   
}
