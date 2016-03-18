package com.test2.IoReplaceContentFromFile;

public class Main {
	public static void main(String[] args){
		FC adm=new FC();
		@SuppressWarnings("unused")
		UF fc=new UF();
		try {
			//3：把目标文件里面的内容追加到源文件的指定的位置
			String path=new Main().getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			int jarposition=path.lastIndexOf("/");
			//获取运行时jar的绝对路径
			path=path.substring(1, jarposition);//C:\Users\Administrator\Desktop\test\advert
			int beforeposition=path.lastIndexOf("/");
			//获取运行时jar的前一级绝对路径
			@SuppressWarnings("unused")
			String beforepath=path.substring(0, beforeposition);//C:\Users\Administrator\Desktop\test
			//获取运行时jar的前一级绝对路径
			String afterpath=path+"/SDK";//C:\Users\Administrator\Desktop\test\advert\SDK
			//args[0]==com.androd.framwork.lsyqq
			//param==com/androd/framwork/lsyqq
			String param=args[0].replace(".", "/");
			@SuppressWarnings("static-access")
			String key=adm.initAdManifest(afterpath,args[0]);
			UF.updatefolder(afterpath+"/com/uiwin",key,param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
