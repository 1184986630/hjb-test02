package com.test2.IoReplaceContentFromFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class FC {
	
	/*
	 * 3:把init.txt和ad.txt里面的内容追加到指定目录下面的类文件中
	 */
	public static String initAdManifest(String path,String newkey) throws UnsupportedEncodingException{
		String key=getkeyword(new File(path+"/"+F.manifest),F.k1);
		String key2=key.replace(".","/");
		String newkey2=newkey.replace(".","/");
		replaceFile(new File(path+"/"+F.manifest),key,newkey);
		replaceFile(new File(path+"/"+F.init),key2,newkey2);
		replaceFile(new File(path+"/"+F.adin),key2,newkey2);
		replaceFile(new File(path+"/"+F.adall),key2,newkey2);
		replaceFile(new File(path+"/"+F.adout),key2,newkey2);
		//replaceFile(new File(path+"/"+F.manifest2d),key,newkey);
		return key2;
	}
	/**
	 * 获取manifest.txt文件下面要修改包名的关键字
	 * @param file
	 * @param keyword
	 * @return
	 */
	public static String getkeyword(File file, String keyword) {  
        BufferedReader br = null;  
        BufferedWriter bw = null;  
        String getkey="";
        try {  
            // 读取文件  
            br = new BufferedReader(new FileReader(file));  
            String lineStr = "";  
            String key="";
            while ((lineStr = br.readLine()) != null) {  
                if(lineStr.contains(keyword)){
                	key=lineStr;
                	break;
                }
            }  
            key=key.substring(key.indexOf(keyword)+keyword.length());
            String[] keys=key.split("\\.");
            for(int i=2;i<4;i++){
            	if(i<3){
            		getkey+=keys[i]+".";
            	}else{
            		getkey+=keys[i];
            	}
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (null != bw) {  
                    bw.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            try {  
                if (null != br) {  
                    br.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        } 
        return getkey;
    }
	/**
	 * 用新的包名替换旧的包名
	 * @param file
	 * @param keyWord
	 * @param newword
	 */
	public static void replaceFile(File file,String keyWord,String newword) {  
		String stringBuilder = new String("");  
        BufferedReader br = null;  
        BufferedWriter bw = null;  
        boolean flag = true;  
        try {  
            // 读取文件  
            br = new BufferedReader(new FileReader(file));  
            String lineStr = getFileToByte(file);
            /*while ((lineStr = br.readLine()) != null) {  
            	stringBuilder+=stringBuilder.concat(lineStr+"\n"); 
                flag=true;
            }  */
            if (flag) { 
            	stringBuilder=lineStr.replace(keyWord, newword);
                // 写文件  
                bw = new BufferedWriter(new FileWriter(file));  
                //这里用了一个字符转换的类  
                bw.write(new CC().toGBK(stringBuilder.toString()));  
            }  
            
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (null != bw) {  
                    bw.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            try {  
                if (null != br) {  
                    br.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        } 
    }
	
	//读取指定文件中的内容--返回字符串
	public static String getFileToByte(File file) throws UnsupportedEncodingException { 
		byte[] by = new byte[(int) file.length()]; 
		try { 
			@SuppressWarnings("resource")
			InputStream is = new FileInputStream(file); 
			ByteArrayOutputStream bytestream = new ByteArrayOutputStream(); 
			byte[] bb = new byte[2048]; 
			int ch; 
			ch = is.read(bb); 
			while (ch != -1) { 
				bytestream.write(bb, 0, ch); 
				ch = is.read(bb); 
			} 
			by = bytestream.toByteArray(); 
		} catch (Exception ex) { 
			ex.printStackTrace(); 
		}
		return new String(by,"GBK"); 
	}
	
}
