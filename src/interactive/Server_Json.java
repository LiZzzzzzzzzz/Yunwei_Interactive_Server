package interactive;



import org.json.JSONException;
import org.json.JSONObject;


public class Server_Json {
	private String ResponseString;
	
	public String getResponseString() {
		return ResponseString;
	}


	public void setResponseString(String responseString) {
		ResponseString = responseString;
	}
	
	//��������json
	public boolean resolvejson(String RequestString)
	{
		       LOG.ReleaseLogger("���յ���Ӧ��:"+RequestString);
		       //ת����json
			   JSONObject json;
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
			   else
			   if(!json.has("Date")||!json.has("key"))
			   {
				 
					Responseqscs(json.getString("Request"));
					return false;
			   }
			   
			   //ɾ������json��key
			   jsonnokey.remove("key");
			   
			   
			   
			   //�������key�Ƿ���ȷ
			   if(!json.getString("key").equals((Server.getMD5(jsonnokey.toString()+Server.md5key))))
			   {
				   Responsejmcw(json.getString("Request"));
				   return false;
			   }
			   
			   
			   
			   
			   }
			   catch (JSONException e) {
				   LOG.ReleaseLogger("json�������");
				   LOG.getTrace(e);
				   Responsebzqxy();
				   return false;
				}
			   
		       return true;
				
	}
	

	//996 ȱ�ٲ���
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


		
}
