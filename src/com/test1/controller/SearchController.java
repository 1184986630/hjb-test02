package com.test1.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Aspect 
@Controller
@RequestMapping("/Search")
public class SearchController {
	@Autowired
	private HttpServletRequest request;
	/**
	 * Excel中数据导入数据库
	 * @author dawn
	 */
	@RequestMapping(value = "/test" , method = RequestMethod.GET)
	@ResponseBody	
	public Map<String,Object> statementwasdf() throws UnsupportedEncodingException{
		//获取浏览器信息
		System.out.println(request.getRequestURI()); //：获得发出请求字符串的客户端地址
		System.out.println(request.getServletPath()); //：获得客户端所请求的脚本文件的文件路径
		
	//获取浏览器信息
		byte[] name = null;
		name = request.getParameter("name").getBytes();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			 String s = new String(name, "utf-8");
			System.out.println(s);
	        System.out.println("Excel中数据导入数据库：百度上有！");
			map.put("code", "100");
			map.put("remark", "生成开发者结算汇总数据表--[时数据]生成成功!");
		} catch (Exception e) {
			map.put("code", "201");
			map.put("remark", "生成开发者结算汇总数据表--[时数据]生成失败!");
		}
		return map;
	}
}
