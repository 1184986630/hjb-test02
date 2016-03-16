package com.test1.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerUtils {
    
    /**
	 * 生成静�?页面主方�?
	 * 
	 * @param context
	 *            ServletContext
	 * @param data
	 *            �?��Map的数据结果集
	 * @param templatePath
	 *            ftl模版路径
	 * @param targetHtmlPath
	 *            生成静�?页面的路�?
	 */

	public static void crateHTML(ServletContext context, Map<String, Object> data, String templatePath, String targetHtmlPath) {
		Configuration freemarkerCfg = new Configuration();
		// 加载模版
		freemarkerCfg.setServletContextForTemplateLoading(context, "/template");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
		try {
			// 指定模版目录
			Template template = freemarkerCfg.getTemplate(templatePath, "UTF-8");
			template.setEncoding("UTF-8");
			// 静�?页面路径
			String htmlPath = context.getRealPath("/WEB-INF/views/index") + "/" + targetHtmlPath;
			File htmlFile = new File(htmlPath);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
			// 处理模版
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}