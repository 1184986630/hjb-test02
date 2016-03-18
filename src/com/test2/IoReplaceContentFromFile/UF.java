package com.test2.IoReplaceContentFromFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class UF {
	
	public static void updatefolder(String path,String oldkey,String newkey){
		try {
			//替换SDK文件内容
			updateFolderContent(new File(path),oldkey,newkey);
			//修改包名
			String[] oldfolder=oldkey.split("/");
			String[] newfolder=newkey.split("/");
			for(int i=0;i<oldfolder.length;i++){
				oldfolderUpdateNewfolder(path,
						oldfolder[i],newfolder[i]);
				path=path+"/"+newfolder[i];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改包名：新的包名替换老的包名
	 * @param path
	 * @param url
	 * @param index
	 */
	public static void oldfolderUpdateNewfolder(String path,String url, String index){
    	File file = new File(path);
    	//判断文件目录是否存在，且是文件目录，非文件
    	if(file.exists() && file.isDirectory()){
    		File[] childFiles = file.listFiles();
    		for(File childFile : childFiles){
    			//如果是文件
    			if(childFile.isDirectory()){
    				String pathName=childFile.getAbsolutePath();
    				if(pathName.substring(pathName.lastIndexOf("\\")+1).equals(url)){
    					childFile.renameTo(new File(pathName.replaceAll(url, index)));
    					return;
    				}
    			}
    		}
    	}
	}
	
	/**
	 * 修改所有文件里面的路径名称：循环找出每个包下面的文件
	 * @param src
	 * @param keyWord
	 * @param newword
	 * @throws IOException
	 */
	public  static void updateFolderContent(File src,String keyWord,String newword ) throws IOException {  
	    if (src.isDirectory()) {//判断源地址是否是一个文件夹  
	        String files[] = src.list();  
	        for (String file : files) {
	        	if(file.contains(F.ad)||file.contains(F.init)||file.contains(F.manifest)){
	        		
	        	}else{	
		        	File srcFile = new File(src, file);  
		            //递归复制  
		        	updateFolderContent(srcFile,keyWord,newword); 
	        	}
	        }  
	    } else {
	    	replaceFile(src,keyWord,newword); 
	    }  
	} 
	/**
	 * 读取每个包下面的文件
	 * @param file
	 * @param keyWord
	 * @param newword
	 */
	public static void replaceFile(File file,String keyWord,String newword) {  
		String stringBuilder = new String("");  
        BufferedReader br = null;  
        BufferedWriter bw = null;  
        boolean flag = false;  
        try {  
            // 读取文件  
            br = new BufferedReader(new FileReader(file));  
            String lineStr = "";  
            while ((lineStr = br.readLine()) != null) {  
            	stringBuilder=stringBuilder.concat(lineStr+"\n"); 
                flag=true;
            }  
            if (flag) { 
            	stringBuilder=stringBuilder.replace(keyWord, newword);
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
}
