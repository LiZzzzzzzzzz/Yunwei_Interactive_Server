package interactive;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import sun.nio.cs.ext.ISCII91;


public class Server_Request_Wjth {
     private String Request;     //����Ψһ��ʶ
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
         
         
         
		 
		 if(!server_json.resolvejson(RequestString))
		 {
			 LOG.ReleaseLogger("����json���󣬽����������ӣ�");
			 os.write(server_json.getResponseString().getBytes());
             os.flush();
             is.close();
             os.close(); 
             return false;
		 }
		 
		//ת����json
		JSONObject json=new JSONObject(RequestString);
		 
		 
		 
		} catch (IOException e) {
			LOG.ReleaseLogger("���������շ����쳣��");    
			LOG.getTrace(e);
			return false;
		
	    } catch (JSONException e) {
	    	LOG.ReleaseLogger("jsonת�����󣬴�������󴮣�");
			LOG.getTrace(e);
			server_json.Responsebzqxy();
			return false;
		} finally {    
         if (clientSocket != null) {    
             try {    
            	 clientSocket.close();    
             } catch (Exception e) {    
            	 clientSocket = null;    
                 LOG.ReleaseLogger("�������ر��쳣��");    
                 LOG.getTrace(e);
             }    
         }    
     }   
	 return true;
	 }
	
	
	
	
	
	
	
	
	   
}
