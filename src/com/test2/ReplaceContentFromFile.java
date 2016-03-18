package com.test2;

import java.io.*;

import com.test2.IoReplaceContentFromFile.UF;

public class ReplaceContentFromFile {
	// 目标目录
	private static String mPath = "D:\\file\\SDK\\sdk2.2.9\\src\\main\\java";
	// 需要修改的字段
	private static String mKeyWord = "fgds.hjghj.bncfvd";
	// 指定新字段
	private static String mKeyWordDest = "hjb.cww.123";

	public static void main(String[] args) {

		// 替换文件中的指定字符
		UF.updatefolder("D:\\file\\SDK\\sdk2.2.9\\proguard-rules.pro", mKeyWord, mKeyWordDest);
		// 替换一个目录下面所有的文件夹中所有文件的指定字符
		try {
			UF.updateFolderContent(new File(mPath), mKeyWord, mKeyWordDest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//替换一个目录下面所有的文件夹中所有文件的指定字符
		//UF.updatefolder("D:\\file\\SDK\\sdk2.2.9\\src\\main\\java",mKeyWord, mKeyWordDest);
	}
}
