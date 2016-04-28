package com.test1.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test1.service.uploadService;

@Controller
@RequestMapping("/upload")
public class uploadController {
	@Autowired
	private HttpServletRequest req;
	private HttpServletResponse resp;
	@Autowired
	private uploadService ups;
	/**
	 * �ϴ��ļ�
	 * @author dawn
	 * @throws Exception 
	 */
	@RequestMapping(value = "/test" , method = RequestMethod.GET)
	@ResponseBody	
	public Map<String,Object> uploadTest() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//�ļ�����
		FileItem fileItem = ups.parsedFile(req, resp);
		if (fileItem != null && !fileItem.isFormField()) {
			//��֤
			if (ups.vertifyFile(fileItem.getName())) {
				//д�ļ�
				String uploadFilePath = ups.writeFile(fileItem, "D://image");
				//���ļ���������
				//adEffectDataService.readFile(uploadFilePath,getUserName(req));
			}
		}
		try {
			map.put("code", "100");
			map.put("remark", "�ɹ�!");
		} catch (Exception e) {
			map.put("code", "201");
			map.put("remark", "ʧ��!");
		}
		return map;
	}
}
