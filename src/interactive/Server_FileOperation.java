package interactive;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

//�ļ�������

public class Server_FileOperation {
	
	
	private File file;       //�ļ���Ϣ
	private String filedir;   //�ļ������ļ���
	private String filename;  //�ļ���
	private String filepath;  //�ļ�����·��
	private int filetype;   //�ļ���or�ļ�
	//��ʼ���ļ���Ϣ �ж��ļ��Ƿ���ڵ�  FileType 0�����ļ� 1�����ļ���
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
				LOG.ReleaseLogger("�ļ������ڣ�"+filepath);
			    return false;
			}  
			
		}
		else 
		if(FileType==1)
		{
			
			if  (!file.exists() ||file.isFile())      
			{
				LOG.ReleaseLogger("�ļ��в����ڣ�"+filepath);
				return false;
			}
			
		}
		return true;
	}
	
	
	
	
	//�ļ�ɾ������ 
	public boolean FileDelete()
	{
		String bakFileDir=AddBakFileDir();
		if(filetype==0)
		{
			LOG.ReleaseLogger("��ʼ�����ļ�ɾ������!");
			return moveFile(filepath,bakFileDir);
		}
		LOG.ReleaseLogger("��ʼ�����ļ���ɾ������!");
		return moveDirectory(filepath,bakFileDir);
		
	}

	//�ļ����������� 
	public boolean FileReName(String NewName)
	{
		File newFile=new File(Server.FileHomeDir+filedir+File.separator+NewName);
		if(filetype==0)
		{
			if(!newFile.exists())    
			{    
				LOG.ReleaseLogger("��ʼ�����ļ�����������!");
		        return file.renameTo(newFile);  
			}
			else
			return false;
			
		}
		
		LOG.ReleaseLogger("��ʼ�����ļ�������������!");
		if  (!newFile.exists()  && !file .isDirectory())      
		{
			 return file.renameTo(newFile);  
		}
		else
		return false;
	}
	
	
	
	//�ļ��滻����
	public boolean FileReplace(String NewFilePath)
	{
			File newFile=new File(NewFilePath);
			if(filetype==0)
			{
				if(!newFile.exists())    
				{    
					LOG.ReleaseLogger("�ļ�:"+NewFilePath+"�����ڣ�");
					return false;
				}
				else
				{    
					LOG.ReleaseLogger("��ʼ�����ļ�����������!");
					AddBakFile(filepath);
			        return file.renameTo(newFile);  
				}
				
			}
		    if(filetype==1)
			LOG.ReleaseLogger("�ļ������Ͳ������滻��");
			return false;
			
			
	}
	
	
	//�ļ��н�ѹ����
	public boolean FileZipReplace(String ZipFilePath,String WhiteFiles)
	{
			
			    
				File ZipFile=new File(ZipFilePath);
				if(filetype==1)
				{
					if(!ZipFile.exists())    
					{    
						LOG.ReleaseLogger("�ļ�:"+ZipFilePath+"�����ڣ�");
						return false;
					}
					else
					{    
						LOG.ReleaseLogger("��ʼ�����ļ���ѹ����!");
						String BakDirName=AddBakFile(filepath);;
						if(MyZip.unzip(ZipFilePath, filepath))
						{
							WhiteFilesMove(WhiteFiles,BakDirName);
						}
						else
						{
						LOG.ReleaseLogger("�ļ���ѹʧ��!");
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
				LOG.ReleaseLogger("�������ļ������ڣ�"+tmpfilepath);
				return false;
			}

	    } 
		return true;
	}
	
	void WhiteFilesMove(String WhiteFiles,String OldFileName)
	{
		LOG.ReleaseLogger("��ʼ�滻�������ļ������ΰ������ַ���Ϊ:"+WhiteFiles);
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
	    		LOG.ReleaseLogger("��⵽�����������ļ�ΪĿ¼��ȡ���滻��"+newfilepath);
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
	
	
	//�����ļ������ã� ���ݵ�ǰ�ļ���ͬһ��Ŀ¼
	String AddBakFile(String FilePath)
	{
		int randsum=0;
		String tmpbakpath ;
		File tmpfile;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
		
		
		if(filetype==0)
		{
		    while (true)
		    {
		     tmpbakpath=FilePath+".bak."+df.format(new Date())+"_"+randsum;
		     tmpfile=new File(tmpbakpath);
		     if (!tmpfile .exists()  && !tmpfile .isDirectory())      
		     {       
		 	    LOG.ReleaseLogger("���������ļ���"+tmpbakpath);  
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
				    LOG.ReleaseLogger("���������ļ��У�"+tmpbakpath);  
				    moveDirectory(filepath,tmpbakpath); 
				    break;
			} 
			randsum++;
			}
			
		}
		return tmpbakpath;
	}
	
	
	
    //ɾ���ļ��� �����Զ��洢�ļ���
	String AddBakFileDir() {
		File tmpfile=new File("bak");
		
		if  (!tmpfile .exists()  && !tmpfile .isDirectory())      
		{       
			    LOG.ReleaseLogger("bakĿ¼�����ڣ�����bakĿ¼");  
			    tmpfile .mkdir();    
		} 
		else   
		{  
			   LOG.DebugLogger("bakĿ¼����!");  
		}  
		
		int randsum=0;
		String tmpbakname;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH");//�������ڸ�ʽ
		
		while (true)
		{
		tmpbakname="bak/"+df.format(new Date())+"_"+randsum;
		tmpfile=new File(tmpbakname);
		if  (!tmpfile .exists()  && !tmpfile .isDirectory())      
		{       
			    LOG.ReleaseLogger("��������Ŀ¼��"+tmpbakname);  
			    tmpfile.mkdirs();   
			    break;
		} 
		randsum++;
		}

		return tmpbakname;
		
	}
	
	
	
	
	
	
	
	
	
	
	/**  
	* �ƶ��ļ�  
	* @param srcFileName    Դ�ļ�����·�� 
	* @param destDirName    Ŀ��Ŀ¼����·�� 
	* @return �ļ��ƶ��ɹ�����true�����򷵻�false  
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
	* �ƶ�Ŀ¼  
	* @param srcDirName     ԴĿ¼����·�� 
	* @param destDirName    Ŀ��Ŀ¼����·�� 
	* @return Ŀ¼�ƶ��ɹ�����true�����򷵻�false  
	*/    
	boolean moveDirectory(String srcDirName, String destDirName) {  
	      
	    File srcDir = new File(srcDirName);  
	    if(!srcDir.exists() || !srcDir.isDirectory())    
	        return false;    
	     
	   File destDir = new File(destDirName);  
	   if(!destDir.exists())  
	       destDir.mkdirs();  
	     
	   /** 
	    * ������ļ����ƶ�������ݹ��ƶ��ļ��С�ɾ�����յĿ�Դ�ļ��� 
	    * ע���ƶ��ļ���ʱ�����ļ��е���״�ṹ 
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
