package interactive;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;



public class LOG {
	
	  static String filepath;
	  static String logString;
	  static BufferedWriter writer;
	  static int Loglv;
      public static void Init(String dir,int lv)
      {
    	  Loglv=lv;
    	  filepath=dir;
    	  File file=new File(dir);    
    	  if  (!file .exists()  && !file .isDirectory())      
    	  {       
    		file.mkdirs();    
    	  } 
    	  
    	  
    	  try {
    			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
    			
    			logString=filepath+"//"+"log_"+df.format(new Date())+".txt";
    			File f = new File(logString);      
    		    if (!f.exists())   
    		    {       
    		       
    					f.createNewFile();
    				
    		    }    
    		    writer = new BufferedWriter(new FileWriter(new File(logString),true));
 
    			} catch (IOException e) {
    				// TODO 自动生成的 catch 块
    				e.printStackTrace();
    			}      
    	  
    	  
      }
	
	
      static void setpath()
      {
    	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			
		  String tmpString=filepath+"//"+"log_"+df.format(new Date())+".txt";
		  if(!tmpString.equals(logString))
		  {
			try {
			logString=tmpString;
			File f = new File(logString);      
  		    if (!f.exists())   
  		    {       
  		       
  					f.createNewFile();
  				
  		    }  
  		    BufferedWriter tmpwrite=writer;
  		    writer = new BufferedWriter(new FileWriter(new File(logString),true));
			tmpwrite.close();
		    } catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		     }
		  }
			
      }
      
	  public static void DebugLogger(String text)
	  {
		  if(Loglv>0)
		  try {
			    setpath();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");//设置日期格式
				//System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
				String logtext;
				
				logtext=df.format(new Date())+text;
			    System.out.println(logtext);
			    writer.write(logtext+"\r\n");      
			    writer.flush();
				} catch (IOException e) {
				
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}      
	  }
	
	  public static void ReleaseLogger(String text)
	  {
		  //if(Loglv==0)
		  try {
			    setpath();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");//设置日期格式
				//System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
				String logtext;
				
				logtext=df.format(new Date())+text;
			    System.out.println(logtext);
			    writer.write(logtext+"\r\n");      
			    writer.flush();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					
					e.printStackTrace(); 
				
				}      
	  }
	
	  public static String getTrace(Throwable t) {   
		  t.printStackTrace();
		  StringWriter stringWriter= new StringWriter();   
		  PrintWriter writer= new PrintWriter(stringWriter);   
		  t.printStackTrace(writer);   
		  StringBuffer buffer= stringWriter.getBuffer();   
		  return buffer.toString();   
		  }   
	  
	  public static void CloseLog()
	  {
		  try {
			writer.close();
		} catch (IOException e) {

			// TODO 自动生成的 catch 块
			e.printStackTrace(); 
		
		}
	  }
	
}
