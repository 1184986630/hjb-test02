package com.test2;

import java.io.*;

public class ReNameFile {
	/** */
	/**
	 * 文件重命名
	 * 
	 * @param path
	 *            文件目录
	 * @param oldname2
	 *            原来的文件名
	 * @param newname2
	 *            新文件名
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
		if (!oldname.equals(newname)) {// 新的文件名和以前文件名不同时,才有必要进行重命名
			File oldfile = new File(oldname);
			File newfile = new File(newname);
			if (!oldfile.exists()) {
				return;// 重命名文件不存在
			}
			if (newfile.exists())// 若在该目录下已经有一个文件和新文件名相同，则不允许重命名
				System.out.println(newname + "已经存在！");
			else {
				oldfile.renameTo(newfile);
			}
		} else {
			System.out.println("新文件名和旧文件名相同...");
		}
	}
}
