package com.test2;

import java.io.*;

import com.test2.IoReplaceContentFromFile.UF;

public class ReplaceContentFromFile {
	// Ŀ��Ŀ¼
	private static String mPath = "D:\\file\\SDK\\sdk2.2.9\\src\\main\\java";
	// ��Ҫ�޸ĵ��ֶ�
	private static String mKeyWord = "fgds.hjghj.bncfvd";
	// ָ�����ֶ�
	private static String mKeyWordDest = "hjb.cww.123";

	public static void main(String[] args) {

		// �滻�ļ��е�ָ���ַ�
		UF.updatefolder("D:\\file\\SDK\\sdk2.2.9\\proguard-rules.pro", mKeyWord, mKeyWordDest);
		// �滻һ��Ŀ¼�������е��ļ����������ļ���ָ���ַ�
		try {
			UF.updateFolderContent(new File(mPath), mKeyWord, mKeyWordDest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�滻һ��Ŀ¼�������е��ļ����������ļ���ָ���ַ�
		//UF.updatefolder("D:\\file\\SDK\\sdk2.2.9\\src\\main\\java",mKeyWord, mKeyWordDest);
	}
}
