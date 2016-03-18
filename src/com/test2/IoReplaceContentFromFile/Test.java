package com.test2.IoReplaceContentFromFile;

public class Test {
	public static void main(String[] args){
		FC adm=new FC();
		@SuppressWarnings("unused")
		UF fc=new UF();
		try {
			
			//获取运行时jar的前一级绝对路径
			String afterpath="D:\\test\\SDK\\sdk2.2.9\\src\\main\\java";//C:\Users\Administrator\Desktop\test\advert\SDK
			//args[0]==com.androd.framwork.lsyqq
			//param==com/androd/framwork/lsyqq
			String param=args[0].replace(".", "/");
			@SuppressWarnings("static-access")
			String key=adm.initAdManifest(afterpath,args[0]);
			UF.updatefolder(afterpath+"/com/uiwin",key,param);
			UF.oldfolderUpdateNewfolder("D:\\file/proguard-rules.pro","hjb.cww.123","hjb.cww.124");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
