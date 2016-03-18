package com.test2;

import java.io.*;
import java.util.Date;

import com.test1.util.DateUtils;

/*
 * 创建文件夹
 */
public class CreatFile {
	public static void main(String[] args) {
		Date date = new Date();
		String dateStr = DateUtils.formatShortDate(date);
		int hour = date.getHours();
		long ms = System.currentTimeMillis()/1000;
		  File folder = new File("D:\\"+dateStr);
	      folder.mkdirs();
	      String filestr = "D:\\"+dateStr+"\\"+hour+"-"+ms;
	      File folder2 = new File(filestr);
	      folder2.mkdirs();
	}
	

}