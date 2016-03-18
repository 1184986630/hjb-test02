package com.test2;

import java.io.*;

public class ReNameFile {
	/** */
	/**
	 * �ļ�������
	 * 
	 * @param path
	 *            �ļ�Ŀ¼
	 * @param oldname2
	 *            ԭ�����ļ���
	 * @param newname2
	 *            ���ļ���
	 * @author dawn
	 */
	private static String newname2 = "dawn.dxt.cn";
	private static String oldname2 = "fgds.hjghj.bncfvd";
	private static String path = "D:\\file\\SDK\\sdk2.2.9\\src\\main\\java";

	public static void main(String[] args) {
		String newnames[] = newname2.split("\\.");
		String oldnames[] = oldname2.split("\\.");
		String oldpath = path;
		String newpath = path;
		for (int i = 0; i < newnames.length; i++) {
			 oldpath += "\\" + oldnames[i];
			 newpath += "\\" + newnames[i];
			renameFile(oldpath, newpath);
			oldpath = oldpath.replace(oldnames[i], newnames[i]);
			
		}

	}

	public static void renameFile(String oldname, String newname) {
		if (!oldname.equals(newname)) {// �µ��ļ�������ǰ�ļ�����ͬʱ,���б�Ҫ����������
			File oldfile = new File(oldname);
			File newfile = new File(newname);
			if (!oldfile.exists()) {
				return;// �������ļ�������
			}
			if (newfile.exists())// ���ڸ�Ŀ¼���Ѿ���һ���ļ������ļ�����ͬ��������������
				System.out.println(newname + "�Ѿ����ڣ�");
			else {
				oldfile.renameTo(newfile);
			}
		} else {
			System.out.println("���ļ����;��ļ�����ͬ...");
		}
	}
}
