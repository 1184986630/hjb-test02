package com.test1.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * 扩展spring的FreemarkerView
 * 
 * 支持jsp标签，Application、Session、Request、RequestParameters属�?
 * 
 * @author jasswin
 * 
 */
public class RichFreeMarkerView extends FreeMarkerView {

	/**
	 * 在model中增加部署路径base，方便处理部署路径问题�?
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void exposeHelpers(Map model, HttpServletRequest request)
			throws Exception {
		super.exposeHelpers(model, request);
	}
}