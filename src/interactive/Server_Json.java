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
	
	//初步解析json
	public boolean resolvejson(String RequestString)
	{
		       LOG.ReleaseLogger("接收到响应串:"+RequestString);
		       //转换成json
			   JSONObject json;
			   JSONObject jsonnokey;
			   
			   
			   //检查是否能转换json
			   try {
				json = new JSONObject(RequestString);
				jsonnokey= new JSONObject(RequestString);
			   } 
			   catch (JSONException e) {
				   LOG.ReleaseLogger("json转换错误，错误的请求串！");
				   LOG.getTrace(e);
				   Responsebzqxy();
				   return false;
			   }  
			   
			   
			   try {
			   //判断json否则有基本参数
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
			   
			   //删除解析json的key
			   jsonnokey.remove("key");
			   
			   
			   
			   //处理加密key是否正确
			   if(!json.getString("key").equals((Server.getMD5(jsonnokey.toString()+Server.md5key))))
			   {
				   Responsejmcw(json.getString("Request"));
				   return false;
			   }
			   
			   
			   
			   
			   }
			   catch (JSONException e) {
				   LOG.ReleaseLogger("json处理错误！");
				   LOG.getTrace(e);
				   Responsebzqxy();
				   return false;
				}
			   
		       return true;
				
	}
	

	//996 缺少参数
	public void Responsebzqxy()
	{
		Server_Response ResponseString=new Server_Response();
		{
			ResponseString.setMessage("错误的请求串！");
			ResponseString.setResponse("999996");
		}
		this.setResponseString(ResponseString.getResponseJson());
	}
	
	//997 缺少参数
	public void Responseqscs(String Request)
	{
		Server_Response ResponseString=new Server_Response();
		if(Request==null)
		{
			ResponseString.setMessage("缺少参数！");
			ResponseString.setResponse("999997");
		}
		else 
		{
			ResponseString.setMessage("缺少参数！");
			ResponseString.setResponse(Request+"997");
		}
		
		this.setResponseString(ResponseString.getResponseJson());
	}
	
	
	//998 缺少参数
	public void Responsecscw(String Request)
	{
		Server_Response ResponseString=new Server_Response();
		if(Request==null)
		{
			ResponseString.setMessage("参数错误！");
			ResponseString.setResponse("999998");
		}
		else 
		{
			ResponseString.setMessage("参数错误！");
			ResponseString.setResponse(Request+"998");
		}
		this.setResponseString(ResponseString.getResponseJson());
	}
	
	
	//999 加密串错误
	public void Responsejmcw(String Request)
	{
		Server_Response ResponseString=new Server_Response();
		ResponseString.setMessage("加密串错误！");
		ResponseString.setResponse(Request+"999");
		this.setResponseString(ResponseString.getResponseJson());
	}
	
	
	
	
	
	//生成json
		public String buildjson(String str) throws JSONException
		{
			    
			   JSONObject json = new JSONObject(str);  //在这里转换。
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
