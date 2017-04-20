package interactive;

import org.json.JSONException;
import org.json.JSONObject;

public class Server_Response {
	private String Response="";
	private String Message="";
	private String Result="";
	public String getResponse() {
		return Response;
	}
	public void setResponse(String response) {
		Response = response;
	}
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getResponseJson()
	{
		JSONObject json = new JSONObject();
		String ResponseString="";
		try {
			json.put("Response", Response);
			json.put("Message", Message);
		    json.put("Result", Result);
		
		} catch (JSONException e) {
			// TODO �Զ����ɵ� catch ��
			LOG.ReleaseLogger("����json������!");
			LOG.getTrace(e);
			
		}
		return ResponseString;
		
	}
}
