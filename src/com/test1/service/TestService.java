package com.test1.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test1.dao.TestDao;
import com.test1.model.AdReceivable;
import com.test1.util.DateUtils;

@Service
public class TestService {
	private final static Logger LOG = LoggerFactory
			.getLogger(TestService.class);
	@Autowired
	private TestDao testDao;
	     /**
		 * test01
		 * @author dawn
		 */
		public List<AdReceivable> getList(){
			  List<AdReceivable> list = new ArrayList<AdReceivable>();
			try {
				list = testDao.getAdReceivableList();
				LOG.info(this.getClass() + "¿ªÊ¼");		
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			LOG.info(this.getClass() + "½áÊø");	
			return list;
		}
}
