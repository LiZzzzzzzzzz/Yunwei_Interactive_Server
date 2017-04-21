package interactive;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

//文件操作类

public class Server_FileOperation {
	
	
	private File file;       //文件信息
	private String filedir;   //文件所在文件夹
	private String filename;  //文件名
	private String filepath;  //文件完整路径
	private int filetype;   //文件夹or文件
	//初始化文件信息 判断文件是否存在等  FileType 0代表文件 1代表文件夹
	public boolean FileInit(String FileDir,String FileName,int FileType)
	{
		filetype=FileType;
		filedir=FileDir;
		filename=FileName;
		if(filedir.length()!=0) filedir=File.separator+filedir;
		
		
		filepath=Server.FileHomeDir+filedir+File.separator+filename;
		file=new File(filepath);    
		if(FileType==0)
		{
			if(!file.exists()||file.isDirectory())    
			{    
				LOG.ReleaseLogger("文件不存在！"+filepath);
			    return false;
			}  
			
		}
		else 
		if(FileType==1)
		{
			
			if  (!file.exists() ||file.isFile())      
			{
				LOG.ReleaseLogger("文件夹不存在！"+filepath);
				return false;
			}
			
		}
		return true;
	}
	
	
	
	
	//文件删除操作 
	public boolean FileDelete()
	{
		String bakFileDir=AddBakFileDir();
		if(filetype==0)
		{
			LOG.ReleaseLogger("开始进行文件删除操作!");
			return moveFile(filepath,bakFileDir);
		}
		LOG.ReleaseLogger("开始进行文件夹删除操作!");
		return moveDirectory(filepath,bakFileDir);
		
	}

	//文件重命名操作 
	public boolean FileReName(String NewName)
	{
		File newFile=new File(Server.FileHomeDir+filedir+File.separator+NewName);
		if(filetype==0)
		{
			if(!newFile.exists())    
			{    
				LOG.ReleaseLogger("开始进行文件重命名操作!");
		        return file.renameTo(newFile);  
			}
			else
			return false;
			
		}
		
		LOG.ReleaseLogger("开始进行文件夹重命名操作!");
		if  (!newFile.exists()  && !file .isDirectory())      
		{
			 return file.renameTo(newFile);  
		}
		else
		return false;
	}
	
	
	
	//文件替换操作
	public boolean FileReplace(String NewFilePath)
	{
			File newFile=new File(NewFilePath);
			if(filetype==0)
			{
				if(!newFile.exists())    
				{    
					LOG.ReleaseLogger("文件:"+NewFilePath+"不存在！");
					return false;
				}
				else
				{    
					LOG.ReleaseLogger("开始进行文件重命名操作!");
					AddBakFile(filepath);
			        return file.renameTo(newFile);  
				}
				
			}
		    if(filetype==1)
			LOG.ReleaseLogger("文件夹类型不允许替换！");
			return false;
			
			
	}
	
	
	//文件夹解压操作
	public boolean FileZipReplace(String ZipFilePath,String WhiteFiles)
	{
			
			    
				File ZipFile=new File(ZipFilePath);
				if(filetype==1)
				{
					if(!ZipFile.exists())    
					{    
						LOG.ReleaseLogger("文件:"+ZipFilePath+"不存在！");
						return false;
					}
					else
					{    
						LOG.ReleaseLogger("开始进行文件解压操作!");
						String BakDirName=AddBakFile(filepath);;
						if(MyZip.unzip(ZipFilePath, filepath))
						{
							WhiteFilesMove(WhiteFiles,BakDirName);
						}
						else
						{
						LOG.ReleaseLogger("文件解压失败!");
						return false;
						}
					}
					
				}

				return false;
				
				
	}
	
	
	public boolean WhiteFilesExist(String WhiteFiles)
	{
		
		String[] tmpWhiteFile = WhiteFiles.split(";;;");
        String tmpfilepath;
 
	    for (int i = 0 ; i <tmpWhiteFile.length ; i++ ) {
	    	tmpfilepath=filepath+File.separator+tmpWhiteFile[i];
	    	if  (!new File(tmpfilepath).exists() ||new File(tmpfilepath).isDirectory())      
			{
				LOG.ReleaseLogger("白名单文件不存在！"+tmpfilepath);
				return false;
			}

	    } 
		return true;
	}
	
	void WhiteFilesMove(String WhiteFiles,String OldFileName)
	{
		LOG.ReleaseLogger("开始替换白名单文件，本次白名单字符串为:"+WhiteFiles);
		String[] tmpWhiteFile = WhiteFiles.split(";;;");
        String newfilepath;   
        String oldfilepath;
        File newFile;
        File oldFile;
	    for (int i = 0 ; i <tmpWhiteFile.length ; i++ ) {
	    	newfilepath=filepath+File.separator+tmpWhiteFile[i];
	    	oldfilepath=Server.FileHomeDir+filedir+File.separator+OldFileName+tmpWhiteFile[i];
	    	
	    	newFile=new File(newfilepath);
	    	oldFile=new File(oldfilepath);
	    	
	    	if(newFile.isDirectory())
	    	{
	    		LOG.ReleaseLogger("检测到本条白名单文件为目录，取消替换！"+newfilepath);
	    		continue;
	    	}
	    	else
	    	if(!newFile.exists())
	    	{
	    		AddBakFile(newfilepath);
	    	}
	    	oldFile.renameTo(newFile);
	    } 
	}
	
	
	//正常文件操作用， 备份当前文件到同一个目录
	String AddBakFile(String FilePath)
	{
		int randsum=0;
		String tmpbakpath ;
		File tmpfile;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		
		
		if(filetype==0)
		{
		    while (true)
		    {
		     tmpbakpath=FilePath+".bak."+df.format(new Date())+"_"+randsum;
		     tmpfile=new File(tmpbakpath);
		     if (!tmpfile .exists()  && !tmpfile .isDirectory())      
		     {       
		 	    LOG.ReleaseLogger("创建备份文件："+tmpbakpath);  
			    file.renameTo(tmpfile);
			    break;
		     } 
		     randsum++;
		     }
		}
		else 
		{
			while (true)
			{
			tmpbakpath=FilePath+"_bak_"+df.format(new Date())+"_"+randsum;
			tmpfile=new File(tmpbakpath);
			if  (!tmpfile .exists()  && !tmpfile .isDirectory())      
			{       
				    LOG.ReleaseLogger("创建备份文件夹："+tmpbakpath);  
				    moveDirectory(filepath,tmpbakpath); 
				    break;
			} 
			randsum++;
			}
			
		}
		return tmpbakpath;
	}
	
	
	
    //删除文件用 生成自动存储文件夹
	String AddBakFileDir() {
		File tmpfile=new File("bak");
		
		if  (!tmpfile .exists()  && !tmpfile .isDirectory())      
		{       
			    LOG.ReleaseLogger("bak目录不存在，创建bak目录");  
			    tmpfile .mkdir();    
		} 
		else   
		{  
			   LOG.DebugLogger("bak目录存在!");  
		}  
		
		int randsum=0;
		String tmpbakname;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH");//设置日期格式
		
		while (true)
		{
		tmpbakname="bak/"+df.format(new Date())+"_"+randsum;
		tmpfile=new File(tmpbakname);
		if  (!tmpfile .exists()  && !tmpfile .isDirectory())      
		{       
			    LOG.ReleaseLogger("创建备份目录："+tmpbakname);  
			    tmpfile.mkdirs();   
			    break;
		} 
		randsum++;
		}

		return tmpbakname;
		
	}
	
	
	
	
	
	
	
	
	
	
	/**  
	* 移动文件  
	* @param srcFileName    源文件完整路径 
	* @param destDirName    目的目录完整路径 
	* @return 文件移动成功返回true，否则返回false  
	*/    
	boolean moveFile(String srcFileName, String destDirName) {  
	      
	    File srcFile = new File(srcFileName);  
	    if(!srcFile.exists() || !srcFile.isFile())   
	        return false;  
	      
	    File destDir = new File(destDirName);  
	    if (!destDir.exists())  
	        destDir.mkdirs();  
	      
	    return srcFile.renameTo(new File(destDirName + File.separator + srcFile.getName()));  
	}  
	
	
	
	
	/**  
	* 移动目录  
	* @param srcDirName     源目录完整路径 
	* @param destDirName    目的目录完整路径 
	* @return 目录移动成功返回true，否则返回false  
	*/    
	boolean moveDirectory(String srcDirName, String destDirName) {  
	      
	    File srcDir = new File(srcDirName);  
	    if(!srcDir.exists() || !srcDir.isDirectory())    
	        return false;    
	     
	   File destDir = new File(destDirName);  
	   if(!destDir.exists())  
	       destDir.mkdirs();  
	     
	   /** 
	    * 如果是文件则移动，否则递归移动文件夹。删除最终的空源文件夹 
	    * 注意移动文件夹时保持文件夹的树状结构 
	    */  
	   File[] sourceFiles = srcDir.listFiles();  
	   for (File sourceFile : sourceFiles) {  
	       if (sourceFile.isFile())  
	           moveFile(sourceFile.getAbsolutePath(), destDir.getAbsolutePath());  
	       else if (sourceFile.isDirectory())  
	           moveDirectory(sourceFile.getAbsolutePath(),   
	                   destDir.getAbsolutePath() + File.separator + sourceFile.getName());  
	       else  
	           ;  
	   }  
	   return srcDir.delete();  
	}  
	
	
}
