package interactive;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class MyZip{
    /** 
     * ��ѹ��zip�� 
     * @param zipFilePath zip�ļ���ȫ·�� 
     * @param unzipFilePath ��ѹ����ļ������·�� 
     * @param includeZipFileName ��ѹ����ļ������·���Ƿ����ѹ���ļ����ļ�����true-������false-������ 
     */  
	public static boolean unzip(String zipFilePath, String descDir)  
    {  
    	File zipFile = new File(zipFilePath);

    	File pathFile = new File(descDir);

    	if (!pathFile.exists())

    	{

    	pathFile.mkdirs();

    	}

    	ZipFile zip = null;

    	InputStream in = null;

    	OutputStream out = null;

    	try

    	{

    	zip = new ZipFile(zipFile);

    	for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();)

    	{

    	ZipEntry entry = entries.nextElement();

    	String zipEntryName = entry.getName();

    	in = zip.getInputStream(entry);

    	String outPath = (descDir + "/" + zipEntryName).replaceAll("\\*", "/");;

    	// �ж�·���Ƿ����,�������򴴽��ļ�·��

    	File file = new File(outPath.substring(0,outPath.lastIndexOf('/')));

    	if (!file.exists())

    	{

    	file.mkdirs();

    	}
        
    	// �ж��ļ�ȫ·���Ƿ�Ϊ�ļ���,����������Ѿ�����,����Ҫ��ѹ

    	if (new File(outPath).isDirectory())

    	{

    	continue;

    	}
    	
    	out = new FileOutputStream(outPath);

    	byte[] buf1 = new byte[4 * 1024];

    	int len;

    	while ((len = in.read(buf1)) > 0)

    	{

    	out.write(buf1, 0, len);

    	}

    	in.close();

    	out.close();

    	}

        LOG.ReleaseLogger("******************��ѹ���********************");
       
    	}

    	catch (IOException e)

    	{

    	pathFile.delete();

    	LOG.ReleaseLogger("******************��ѹʧ��********************");

    	LOG.ReleaseLogger(LOG.getTrace(e));
        return false;
    	}

    	finally

    	{

    	try

    	{

    	if (zip != null)

    	{

    	zip.close();

    	}

    	if (in != null)

    	{

    	in.close();

    	}

    	if (out != null)

    	{

    	out.close();

    	}

    	}

    	catch (IOException e)

    	{
    	LOG.ReleaseLogger("******************�ر�ѹ���ļ�ʧ��********************");
    	LOG.ReleaseLogger(LOG.getTrace(e));
         
    	}
       
    	}
		
    	return true;
    }
    
	
}
