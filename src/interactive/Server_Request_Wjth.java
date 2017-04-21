package interactive;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;


public class Server_Request_Wjth {
     private String MD5;         //�ļ�MD5 ������֤�ļ��Ƿ���ȷ
     private String FileSize;    //�ļ���С��������֤�ļ��Ƿ���ȷ�ͽ����ļ���
     private String FileDir;     //�ļ������ĸ�Ŀ¼
     private String FileName;    //�ļ��� ������չ��
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
         LOG.ReleaseLogger("��ʼ���������̣߳�");
         
         
         
         JSONObject json = null;
         //����json
		 if(!server_json.resolvejson(RequestString,json))
		 {
			 LOG.ReleaseLogger("����json���󣬽����������ӣ�");
			 os.write(server_json.getResponseString().getBytes());
             os.flush();
             is.close();
             os.close(); 
             return false;
		 }
		 
		 //��jsonֵת�Ƶ�����
		 if(!MakeParameter(server_json, json))
		 {
			LOG.ReleaseLogger("����json���󣬽����������ӣ�");
			os.write(server_json.getResponseString().getBytes());
            os.flush();
            is.close();
            os.close(); 
            return false;
			
		 }
		 
		 
		 
		} catch (IOException e) {
			LOG.ReleaseLogger("���������շ����쳣��");    
			LOG.getTrace(e);
			return false;
		
	    }  finally {    
         if (clientSocket != null) {    
             try {    
            	 clientSocket.close();    
             } catch (Exception e) {    
            	 clientSocket = null;    
                 LOG.ReleaseLogger("�������ر��쳣��");    
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
				// TODO �Զ����ɵ� catch ��
			   LOG.ReleaseLogger("json�������");
			   LOG.ReleaseLogger(LOG.getTrace(e));
			   server_json.Responsebzqxy();
			   return false;
			}
		return true;
	 }
	
	   
}
