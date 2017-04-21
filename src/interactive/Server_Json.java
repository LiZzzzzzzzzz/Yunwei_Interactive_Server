package interactive;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;


public class Server_Json {
	private String ResponseString;
	private String Request; //����Ψһ��ʾ
	private String Date;    //���ڲ���
	private String Key;     //
	
	public static final SimpleDateFormat datedf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ݿ����ڸ�ʽ
		
	
	public String getResponseString() {
		return ResponseString;
	}


	public void setResponseString(String responseString) {
		ResponseString = responseString;
	}
	
	//��������json
	public boolean resolvejson(String RequestString,JSONObject json)
	{
		       LOG.ReleaseLogger("���յ���Ӧ��:"+RequestString);
		       //ת����json
			   //JSONObject json;
			   JSONObject jsonnokey;
			   
			   
			   //����Ƿ���ת��json
			   try {
				json = new JSONObject(RequestString);
				jsonnokey= new JSONObject(RequestString);
			   } 
			   catch (JSONException e) {
				   LOG.ReleaseLogger("jsonת�����󣬴�������󴮣�");
				   LOG.getTrace(e);
				   Responsebzqxy();
				   return false;
			   }  
			   
			   
			   try {
			   //�ж�json�����л�������
			   if(!json.has("Request"))
			   {
				   Responseqscs(null);
				   return false;
			   }
			   
			   setRequest(json.getString("Request"));
			   if(!json.has("Date")||!json.has("Key"))
			   {
				 
					Responseqscs(getRequest());
					return false;
			   }
			   
			   setRequest(json.getString("Date"));
			   setRequest(json.getString("Key"));
			   //ɾ������json��key
			   jsonnokey.remove("Key");
			    
			   //�ж�ʱ���Ƿ���3600�ڵĲ��
			   if(Math.abs(new Date().getTime()-datedf.parse(getDate()).getTime())/1000>3600)
			   {
				   Responsesjcw(getRequest());
				   return false;
			   }
			   
			   
			   //�������key�Ƿ���ȷ
			   if(!getKey().equals((Server.getMD5(jsonnokey.toString()+Server.md5key))))
			   {
				   Responsejmcw(getRequest());
				   return false;
			   }
			   
			   
			   
			   
			   }
			   catch (JSONException e) {
				   LOG.ReleaseLogger("json�������");
				   LOG.ReleaseLogger(LOG.getTrace(e));
				   Responsebzqxy();
				   return false;
				} catch (ParseException e) {
					LOG.ReleaseLogger("ʱ���������");
					LOG.ReleaseLogger(LOG.getTrace(e));
				    Responsesjcw(getRequest());
				    return false;
			}
			   
		       return true;
				
	}
	
	
	//995 ʱ�����
	public void Responsesjcw(String Request)
	{
		Server_Response ResponseString=new Server_Response();
		if(Request==null)
		{
			ResponseString.setMessage("�����ʱ�����������ʧ�ܣ�");
			ResponseString.setResponse("999995");
		}
		else 
		{
			ResponseString.setMessage("�����ʱ�����������ʧ�ܣ�");
			ResponseString.setResponse(Request+"995");
		}
		
		this.setResponseString(ResponseString.getResponseJson());
	}
	
	
	
	//996 ������
	public void Responsebzqxy()
	{
		Server_Response ResponseString=new Server_Response();
		{
			ResponseString.setMessage("��������󴮣�");
			ResponseString.setResponse("999996");
		}
		this.setResponseString(ResponseString.getResponseJson());
	}
	
	//997 ȱ�ٲ���
	public void Responseqscs(String Request)
	{
		Server_Response ResponseString=new Server_Response();
		if(Request==null)
		{
			ResponseString.setMessage("ȱ�ٲ�����");
			ResponseString.setResponse("999997");
		}
		else 
		{
			ResponseString.setMessage("ȱ�ٲ�����");
			ResponseString.setResponse(Request+"997");
		}
		
		this.setResponseString(ResponseString.getResponseJson());
	}
	
	
	//998 ȱ�ٲ���
	public void Responsecscw(String Request)
	{
		Server_Response ResponseString=new Server_Response();
		if(Request==null)
		{
			ResponseString.setMessage("��������");
			ResponseString.setResponse("999998");
		}
		else 
		{
			ResponseString.setMessage("��������");
			ResponseString.setResponse(Request+"998");
		}
		this.setResponseString(ResponseString.getResponseJson());
	}
	
	
	//999 ���ܴ�����
	public void Responsejmcw(String Request)
	{
		Server_Response ResponseString=new Server_Response();
		ResponseString.setMessage("���ܴ�����");
		ResponseString.setResponse(Request+"999");
		this.setResponseString(ResponseString.getResponseJson());
	}
	
	
	
	
	
	//����json
		public String buildjson(String str) throws JSONException
		{
			    
			   JSONObject json = new JSONObject(str);  //������ת����
			   JSONObject json2= new JSONObject(str);
			   json2.remove("result");
			   if(json.has("s"))
		        System.out.println(json.getString("s"));
			   else {
				   System.out.println(json.getString("result"));
			}
				return str;  
		}


		public String getRequest() {
			return Request;
		}


		public void setRequest(String request) {
			Request = request;
		}


		public String getDate() {
			return Date;
		}


		public void setDate(String date) {
			Date = date;
		}


		public String getKey() {
			return Key;
		}


		public void setKey(String key) {
			this.Key = key;
		}


		
}
