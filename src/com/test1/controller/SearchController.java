package com.test1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Search")
public class SearchController {
	/**
	 * Excel�����ݵ������ݿ�
	 * @author dawn
	 */
	@RequestMapping(value = "/test" , method = RequestMethod.POST)
	@ResponseBody	
	public Map<String,Object> statement(){
		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
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
