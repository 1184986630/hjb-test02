package com.test1.log;

import java.io.UnsupportedEncodingException;

import org.aspectj.lang.JoinPoint;

public class MyLog {
	//��������д������������ʫ��������ġ��˴����ñ�׼��before��after����ʾ  
    //�˴���JoinPoint����Ի�ȡ��action���е����������Ϣ��request�����ö���  
  public void before(JoinPoint joinpoint){  
            joinpoint.getArgs();// �˷������ص���һ�����飬�����а���request�Լ�ActionCofig�������  
            //ѭ����ӡ������Ĳ���,��������
            for (int i = 0; i < joinpoint.getArgs().length; i++) {  
            	try {
				String name = new String(((String) joinpoint.getArgs()[i]).getBytes("utf-8"));
				  System.out.println(name);  
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              
            }  
             // ��ȡ����ķ�������
            System.out.println(joinpoint.getSignature().getName());  
  }  
  public void after(JoinPoint joinpoint){  
              System.out.println("�����ط�������֮����ô˷�������������");  
  }  
}
