package com.test1.log;

import java.io.UnsupportedEncodingException;

import org.aspectj.lang.JoinPoint;

public class MyLog {
	//在类里面写方法，方法名诗可以任意的。此处我用标准的before和after来表示  
    //此处的JoinPoint类可以获取，action所有的相关配置信息和request等内置对象。  
  public void before(JoinPoint joinpoint){  
            joinpoint.getArgs();// 此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象  
            //循环打印出输入的参数,中文乱码
            for (int i = 0; i < joinpoint.getArgs().length; i++) {  
            	try {
				String name = new String(((String) joinpoint.getArgs()[i]).getBytes("utf-8"));
				  System.out.println(name);  
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              
            }  
             // 获取请求的方法名称
            System.out.println(joinpoint.getSignature().getName());  
  }  
  public void after(JoinPoint joinpoint){  
              System.out.println("被拦截方法调用之后调用此方法，输出此语句");  
  }  
}
