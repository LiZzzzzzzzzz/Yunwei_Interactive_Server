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
     * 解压缩zip包 
     * @param zipFilePath zip文件的全路径 
     * @param unzipFilePath 解压后的文件保存的路径 
     * @param includeZipFileName 解压后的文件保存的路径是否包含压缩文件的文件名。true-包含；false-不包含 
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

    	// 判断路径是否存在,不存在则创建文件路径

    	File file = new File(outPath.substring(0,outPath.lastIndexOf('/')));

    	if (!file.exists())

    	{

    	file.mkdirs();

    	}
        
    	// 判断文件全路径是否为文件夹,如果是上面已经创建,不需要解压

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

        LOG.ReleaseLogger("******************解压完毕********************");
       
    	}

    	catch (IOException e)

    	{

    	pathFile.delete();

    	LOG.ReleaseLogger("******************解压失败********************");

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
    	LOG.ReleaseLogger("******************关闭压缩文件失败********************");
    	LOG.ReleaseLogger(LOG.getTrace(e));
         
    	}
       
    	}
		
    	return true;
    }
    
	
}
