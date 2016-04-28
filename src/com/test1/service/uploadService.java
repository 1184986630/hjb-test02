package com.test1.service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;



@Service
public class uploadService {
	private final static Logger LOG = LoggerFactory.getLogger(uploadService.class);
	public FileItem parsedFile(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException, FileUploadException {
		File file = null;
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		// Create a factory for disk-based file items
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
		diskFileItemFactory.setRepository(file); // 设置缓冲区目录
		// Create a new file upload handler
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		// Set overall request size constraint设置最大文件尺寸，这里是4MB
		servletFileUpload.setSizeMax(4 * 1024 * 1024);
		FileItem fileItem = (FileItem) servletFileUpload.parseRequest(req).get(0);
		return fileItem;
	}

	public boolean vertifyFile(String fileName) {
		// verify file args
		if ((fileName == null || fileName.equals("") || fileName.length() > 255)) {
			LOG.warn("广告主效果数据-文件参数验证失败-文件名: " + fileName);
		}
		// verify file extension
		if (fileName.endsWith(".xls")) {
			LOG.info("广告主效果数据-文件格式验证成功-文件名: " + fileName);
			return true;
		} else {
			LOG.warn("广告主效果数据-文件格式验证失败-文件名: " + fileName);
		}
		return false;
	}

	public String writeFile(FileItem fileItem, String uploadPath) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String uploadFilePath = uploadPath + simpleDateFormat.format(new Date()) + ".xls";
		LOG.info("广告主效果数据-上传文件路径" + uploadFilePath);
		fileItem.write(new File(uploadFilePath));
		LOG.info("广告主效果数据-写文件成功");
		return uploadFilePath;
	}

	public void readFile(String uploadFilePath,String username) throws BiffException, IOException {
		Workbook book = null;
		book = Workbook.getWorkbook(new File(uploadFilePath));
		Sheet sheet = book.getSheet(0);
		int rows = sheet.getRows();
		/*
		List<AdEffectData> data = new ArrayList<AdEffectData>();
		for (int i = 0; i < rows; i++) {
			if (i == 0) continue; // 首行不导入
			if (sheet.getCell(0, i).getContents().equals("")) break; //首列空则停止
			data.add(importData(sheet, i,username));
		}
		LOG.info("广告主效果数据-读取数据成功");
		for (AdEffectData adEffectData : data) {
			try {
				adEffectDataDao.addAdEffectData(adEffectData);
			} catch (DuplicateKeyException e) {
				throw new EffectDataDuplicateKeyException();
			}
		}
		LOG.info("广告主效果数据-插入数据成功");*/
		book.close();
	}

	/*private AdEffectData importData(Sheet sheet, int i,String username) {
		String effectDate = getExcelDate(sheet.getCell(0, i));
//		String adAPPTitle = sheet.getCell(1, i).getContents().trim();
		int adAPPId = Integer.parseInt(sheet.getCell(2, i).getContents().trim());
		int effectNum = Integer.parseInt(sheet.getCell(3, i).getContents().trim());
		// 验证 adappid
		AdApp adApp = adAppService.getAdAPPById(adAPPId);
		if (adApp == null) {
			LOG.error("验证adAppId失败");
			throw new EffectDataContentVertifyException();
		}
		// 验证 datadate
		if (!isValidDate(effectDate)) {
			LOG.error("验证时间日期失败");
			throw new EffectDataContentVertifyException();
		}
		// 验证 effectnum
		if (!isNumeric(String.valueOf(effectNum))){
			LOG.error("验证数字失败");
			throw new EffectDataContentVertifyException();
		}
		float price = adApp.getApp_price();		//结算单价		
		float income = Float.parseFloat(MathUtil.mul(String.valueOf(price),String.valueOf(effectNum)));		//收入金额		
		String importUser = username;		//导入人		
		AdEffectData adEffectData = new AdEffectData();
		adEffectData.setEffectdate(DateUtils.parseToDate(effectDate, "yyyy-MM-dd"));
		adEffectData.setEffectnum(effectNum);
		adEffectData.setAdappid(adAPPId);
		adEffectData.setPrice(price);
		adEffectData.setIncome(income);
		adEffectData.setImportuser(importUser);
		return adEffectData;
	}*/

	
	private String getExcelDate(Cell cell){
		String result="";
//		if(cell.getType() == CellType.DATE){
		DateCell dc = (DateCell)cell;
		Date date = dc.getDate();
		SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
		result = ds.format(date);
//		}else{
//			throw new EffectDataContentVertifyException();
//		}
		return result;
	}
	
	
	private boolean isValidDate(String datadate) {
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(datadate);
		} catch (ParseException e) {
			convertSuccess = false;
		}
		return convertSuccess;
	}

	private boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
}
