package interactive;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;


public class Server {

	public static String md5key = "liuzhen@..11ZYJ!";    //请求配置秘钥
	public static final String aesrequestkey="liu.request1ZYJ!"; //第一次请求秘钥  写入程序 固定秘钥

	
	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException{
		
		// TODO 自动生成的方法存根
		  
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
           //32位加密  
           return buf.toString();  
           // 16位的加密  
           //return buf.toString().substring(8, 24);  
       } catch (NoSuchAlgorithmException e) {  
           LOG.ReleaseLogger("MD5加密错误！"); 
           LOG.ReleaseLogger(LOG.getTrace(e));
           return "";  
      }  
 
}  


}
