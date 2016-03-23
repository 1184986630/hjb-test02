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
	 * Excel中数据导入数据库
	 * @author dawn
	 */
	@RequestMapping(value = "/test" , method = RequestMethod.POST)
	@ResponseBody	
	public Map<String,Object> statement(){
		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
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
