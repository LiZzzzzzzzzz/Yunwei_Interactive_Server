package interactive;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;


public class Server {

	public static String md5key = "liuzhen@..11ZYJ!";    //����������Կ
	public static final String aesrequestkey="liu.request1ZYJ!"; //��һ��������Կ  д����� �̶���Կ

	
	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException{
		
		// TODO �Զ����ɵķ������
		  
		LOG.Init("log", 1);
		
			   LOG.ReleaseLogger("aa");
		   
			
	}

	
	
	
	
	
	
	
public static String getMD5(String plainText) {  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
          md.update(plainText.getBytes());  
           byte b[] = md.digest();  
 
           int i;  
 
           StringBuffer buf = new StringBuffer("");  
           for (int offset = 0; offset < b.length; offset++) {  
               i = b[offset];  
               if (i < 0)  
                   i += 256;  
               if (i < 16)  
                   buf.append("0");  
               buf.append(Integer.toHexString(i));  
           }  
           //32λ����  
           return buf.toString();  
           // 16λ�ļ���  
           //return buf.toString().substring(8, 24);  
       } catch (NoSuchAlgorithmException e) {  
           LOG.ReleaseLogger("MD5���ܴ���"); 
           LOG.ReleaseLogger(LOG.getTrace(e));
           return "";  
      }  
 
}  


}
