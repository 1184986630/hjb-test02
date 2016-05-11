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
	 * Excel�����ݵ������ݿ�
	 * @author dawn
	 */
	@RequestMapping(value = "/test" , method = RequestMethod.GET)
	@ResponseBody	
	public Map<String,Object> statementwasdf() throws UnsupportedEncodingException{
		//��ȡ�������Ϣ
		System.out.println(request.getRequestURI()); //����÷��������ַ����Ŀͻ��˵�ַ
		System.out.println(request.getServletPath()); //����ÿͻ���������Ľű��ļ����ļ�·��
		
	//��ȡ�������Ϣ
		byte[] name = null;
		name = request.getParameter("name").getBytes();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			 String s = new String(name, "utf-8");
			System.out.println(s);
	        System.out.println("Excel�����ݵ������ݿ⣺�ٶ����У�");
			map.put("code", "100");
			map.put("remark", "���ɿ����߽���������ݱ�--[ʱ����]���ɳɹ�!");
		} catch (Exception e) {
			map.put("code", "201");
			map.put("remark", "���ɿ����߽���������ݱ�--[ʱ����]����ʧ��!");
		}
		return map;
	}
}
