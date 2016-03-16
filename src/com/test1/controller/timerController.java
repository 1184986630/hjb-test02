package com.test1.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.test1.model.AdReceivable;
import com.test1.service.TestService;

/**
 * 定时任务
 * @author dawn
 *
 */
@Component 
public class timerController {
	@Value("${dawn}")
	private  String dawn;
	@Autowired
	private TestService testService;
	/**
	 * 定时打印日期
	 */
	@Scheduled(cron = "${first_timer}")
	public void procduce(){
		List<AdReceivable> list = testService.getList();
		if(list.size()>0){
			for(AdReceivable ar:list){
				System.out.println(ar.getAd_owner_id()+"--"+ar.getAccount_moeny()+"--"+ar.getFinder());
			}
		}
	}
}
