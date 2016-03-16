package com.test1.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Pattern;


public class StringUtils  {
	public static String getMatchExp(String[] arr) {
		StringBuilder sb = new StringBuilder();
		if (arr != null && arr.length > 0) {
			for (String s : arr) {
				sb.append(",").append(s);
			}
			sb.append(",");
		}
		return sb.toString();
	}

	public static boolean hasContain(String str1, String str2) {
		if (StringUtils.isNull(str1) || StringUtils.isNull(str2))
			return false;
		String[] ss = str1.split(",");
		for (String s : ss) {
			if (s.equals(str2))
				return true;
		}
		return false;
	}

	public static boolean isNull(String s) {
		return s == null || s.trim().length() == 0;
	}

	// INT转换, 非数字则�?
	public static int parseInt(String num) {
		if (isNull(num))
			return 0;
		Pattern pattern = Pattern.compile("[0-9]*");
		if (pattern.matcher(num).matches()) {
			return Integer.parseInt(num);
		} else {
			return 0;
		}
	}

	public static String formatNormal(String date) {
		if (date == null)
			return "";
		if (date != null && date.length() > 19)
			return date.substring(0, 19);
		return date;
	}
	
	public static String formatNormalBylength(String date,int length) {
		if (date == null)
			return "";
		if (date != null && date.length() > length+1)
			return date.substring(0, length);
		return date;
	}
	
	public static String formatNormal(String date, int length) {
		if (date == null)
			return "";
		else
			return date.substring(0, length);
	}

	public static boolean isEmail(String str) {
		if (isNull(str))
			return false;
		String regex = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?$";
		return Pattern.compile(regex).matcher(str).matches();
	}

	/**
	 * 获取远程URL连接响应文本, 响应超时�?0�?
	 * @param url	远程响应URL
	 * @return	响应文本, 超时返回内容�?"
	 */
	public static String getUrlTxt(String url) {
		return getUrlTxt(url, 60);
	}

	public static String getUrlTxt(String url, int timeout) {
		String sLine = "";
		String sText = "";
		try {
			URL l_url = new URL(url);
			HttpURLConnection l_connection = (HttpURLConnection) l_url.openConnection();
			l_connection.setConnectTimeout(timeout * 1000);
			l_connection.setReadTimeout(timeout * 1000);
			l_connection.connect();
			InputStream l_urlStream = l_connection.getInputStream();
			BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
			while ((sLine = l_reader.readLine()) != null) {
				sText = sText + sLine;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sText;
	}

	public static String genRandomNum(int pwd_len) {
		//35是因为数组是�?�?��的，26个字�?10个数�?
		final int maxNum = 36;
		int i; //生成的随机数
		int count = 0; //生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			//生成随机数，取绝对�?，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); //生成的数�?���?6-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	public static String formatString(String text) {
		if (text == null) {
			return "";
		}
		return text;
	}

	/**
	 * 获取类似URL传参的�?
	 * @param urlstr	�?��类似URL传参的名值对, eg:parm1=1&parm2=2&parm3=3
	 * @param name	要获取的参数, eg:parm1
	 * @return	对应的�?, eg:1
	 */
	public static String getParm(String urlstr, String name) {
		if (urlstr.indexOf("?") < 0) {
			urlstr = "?" + urlstr;
		}
		String[] sar = urlstr.replace("?", "?&").split("&");
		String re = null;
		for (int i = 1; i < sar.length; i++)
			if (sar[i].indexOf(name + "=") == 0)
				re = sar[i].replace(name + "=", "");
		return re;
	}

	/**
	 * 使用正则验证有效�?
	 * @param str	待验证字符串
	 * @param regx	正则表达�?
	 * @return 字符串为空时返回false
	 */
	public static boolean verifyRegex(String str, String regx) {
		if (regx == null)
			return false;
		return Pattern.compile(regx).matcher(str).find();
	}

	public static String getJSAlert(String msg) {
		return "<script type=\"text/javascript\">alert('" + msg + "');</script>";
	}

	public static String getJSAlert(String msg, String url) {
		return "<script type=\"text/javascript\">alert('" + msg + "');window.location.href='" + url + "';</script>";
	}

	
	/***
	 * 
	 * @param content 内容String
	 * @param p	>0 .位数
	 * @return @tale:
	 * @purpose：得到相应位数已过滤html、script、style  标签的内�?内容结尾 �?..
	 * @author：Simon - 赵振�?
	 * @CreationTime：Aug 25, 2010 11:07:06 AM
	 */
	public static String getNoHTMLString(String content,int p){
		
			if(null==content) return "";
			if(0==p) return "";
		
			java.util.regex.Pattern p_script; 
	        java.util.regex.Matcher m_script; 
	        java.util.regex.Pattern p_style; 
	        java.util.regex.Matcher m_style; 
	        java.util.regex.Pattern p_html; 
	        java.util.regex.Matcher m_html; 
        
		   try { 
			   	  String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			   		//定义script的正则表达式{�?script[^>]*?>[\\s\\S]*?<\\/script> } 
			   	  String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; 
	           		//定义style的正则表达式{�?style[^>]*?>[\\s\\S]*?<\\/style> } 
	              String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式 
	          
	              p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
	              m_script = p_script.matcher(content); 
	              content = m_script.replaceAll(""); //过滤script标签 

	              p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
	              m_style = p_style.matcher(content); 
	              content = m_style.replaceAll(""); //过滤style标签 
	          
	              p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	              m_html = p_html.matcher(content); 
	              
	              content = m_html.replaceAll(""); //过滤html标签 
	          }catch(Exception e) { 
	                  return "";
	          } 
		
	          if(content.length()>p){
	        	  content = content.substring(0, p)+"...";
	          }
		
		
		
		return content;
	}
	
	public static float getFloatNum(int a, int b) {
		if(b==0){
			return 0;
		}
		float num= (float)a/b;
		DecimalFormat df = new DecimalFormat("0.00");//格式化小�?
		String s = df.format(num);//返回的是String类型
		return Float.valueOf(s);
	}
	
	public static float getFloatNum(float a, int b) {
		float num= (float)a/b;
		DecimalFormat df = new DecimalFormat("0.00");//格式化小�?
		String s = df.format(num);//返回的是String类型
		return Float.valueOf(s);
	}
	public static float getFloatNum(float a, float b) {
		float num= (float)a/b;
		DecimalFormat df = new DecimalFormat("0.00");//格式化小�?
		String s = df.format(num);//返回的是String类型
		return Float.valueOf(s);
	}
	
	
	public static void main(String[] args) {
		//String s = StringUtils.getNoHTMLString("<span class=\"l_1\"><b>minglove1986</b></span></h2>", 6);
		
		//System.err.println(s);
	}

}
