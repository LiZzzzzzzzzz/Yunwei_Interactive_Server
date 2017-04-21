package interactive;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;


public class Server_Json {
	private String ResponseString;
	private String Request; //功能唯一标示
	private String Date;    //日期参数
	private String Key;     //
	
	public static final SimpleDateFormat datedf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置数据库日期格式
		
	
	public String getResponseString() {
		return ResponseString;
	}


	public void setResponseString(String responseString) {
		ResponseString = responseString;
	}
	
	//初步解析json
	public boolean resolvejson(String RequestString,JSONObject json)
	{
		       LOG.ReleaseLogger("接收到响应串:"+RequestString);
		       //转换成json
			   //JSONObject json;
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
			   
			   setRequest(json.getString("Request"));
			   if(!json.has("Date")||!json.has("Key"))
			   {
				 
					Responseqscs(getRequest());
					return false;
			   }
			   
			   setRequest(json.getString("Date"));
			   setRequest(json.getString("Key"));
			   //删除解析json的key
			   jsonnokey.remove("Key");
			    
			   //判断时间是否再3600内的差距
			   if(Math.abs(new Date().getTime()-datedf.parse(getDate()).getTime())/1000>3600)
			   {
				   Responsesjcw(getRequest());
				   return false;
			   }
			   
			   
			   //处理加密key是否正确
			   if(!getKey().equals((Server.getMD5(jsonnokey.toString()+Server.md5key))))
			   {
				   Responsejmcw(getRequest());
				   return false;
			   }
			   
			   
			   
			   
			   }
			   catch (JSONException e) {
				   LOG.ReleaseLogger("json处理错误！");
				   LOG.ReleaseLogger(LOG.getTrace(e));
				   Responsebzqxy();
				   return false;
				} catch (ParseException e) {
					LOG.ReleaseLogger("时间参数错误！");
					LOG.ReleaseLogger(LOG.getTrace(e));
				    Responsesjcw(getRequest());
				    return false;
			}
			   
		       return true;
				
	}
	
	
	//995 时间错误
	public void Responsesjcw(String Request)
	{
		Server_Response ResponseString=new Server_Response();
		if(Request==null)
		{
			ResponseString.setMessage("错误的时间参数，请求失败！");
			ResponseString.setResponse("999995");
		}
		else 
		{
			ResponseString.setMessage("错误的时间参数，请求失败！");
			ResponseString.setResponse(Request+"995");
		}
		
		this.setResponseString(ResponseString.getResponseJson());
	}
	
	
	
	//996 串错误
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
