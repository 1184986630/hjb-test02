package com.test2;

import java.io.*;

public class CopyFileToAnotherFile {
    /** 
     * ����һ��Ŀ¼������Ŀ¼���ļ�������һ��Ŀ¼ 
     * @author dawn
     * @throws IOException 
     */  
    public void copyFolder(File src, File dest) throws IOException {  
        if (src.isDirectory()) {  
            if (!dest.exists()) {  
                dest.mkdir();  
            }  
            String files[] = src.list();  
            for (String file : files) {  
                File srcFile = new File(src, file);  
                File destFile = new File(dest, file);  
                // �ݹ鸴��  
                copyFolder(srcFile, destFile);  
            }  
        } else {  
            InputStream in = new FileInputStream(src);  
            OutputStream out = new FileOutputStream(dest);  
      
            byte[] buffer = new byte[1024];  
      
            int length;  
              
            while ((length = in.read(buffer)) > 0) {  
                out.write(buffer, 0, length);  
            }  
            in.close();  
            out.close();  
        }  
    }  

	public static void main(String[] args) {
		CopyFileToAnotherFile test = new CopyFileToAnotherFile();
		File srcFile = new File("D:\\file");  
        File destFile = new File("D:\\file2");  
		try {
			test.copyFolder(srcFile,destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
