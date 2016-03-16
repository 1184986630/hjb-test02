package com.test1.util;

import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


public class MailUtils {


	String to = "";// 收件�?
	String from = "";// 发件�?
	String host = "";// smtp主机
	String username = "";
	String password = "";
	String filename = "";// 附件文件�?
	String subject = "";// 邮件主题
	String content = "";// 邮件正文
	Vector file = new Vector();// 附件文件集合

	/**
	 * <br>
	 * 方法说明：默认构造器 <br>
	 * 输入参数�?<br>
	 * 返回类型�?
	 */
	public MailUtils() {
	}

	/**
	 * <br>
	 * 方法说明：构造器，提供直接的参数传入 <br>
	 * 输入参数�?<br>
	 * 返回类型�?
	 */
	public MailUtils(String to, String from, String smtpServer,
			String username, String password, String subject, String content) {
		this.to = to;
		this.from = from;
		this.host = smtpServer;
		this.username = username;
		this.password = password;
		this.subject = subject;
		this.content = content;
	}

	/**
	 * <br>
	 * 方法说明：设置邮件服务器地址 <br>
	 * 输入参数：String host 邮件服务器地�?���?<br>
	 * 返回类型�?
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * <br>
	 * 方法说明：设置登录服务器校验密码 <br>
	 * 输入参数�?<br>
	 * 返回类型�?
	 */
	public void setPassWord(String pwd) {
		this.password = pwd;
	}

	/**
	 * <br>
	 * 方法说明：设置登录服务器校验用户 <br>
	 * 输入参数�?<br>
	 * 返回类型�?
	 */
	public void setUserName(String usn) {
		this.username = usn;
	}

	/**
	 * <br>
	 * 方法说明：设置邮件发送目的邮�?<br>
	 * 输入参数�?<br>
	 * 返回类型�?
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * <br>
	 * 方法说明：设置邮件发送源邮箱 <br>
	 * 输入参数�?<br>
	 * 返回类型�?
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * <br>
	 * 方法说明：设置邮件主�?<br>
	 * 输入参数�?<br>
	 * 返回类型�?
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * <br>
	 * 方法说明：设置邮件内�?<br>
	 * 输入参数�?<br>
	 * 返回类型�?
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * <br>
	 * 方法说明：把主题转换为中�?<br>
	 * 输入参数：String strText <br>
	 * 返回类型�?
	 */
	public String transferChinese(String strText) {
		try {
			strText = MimeUtility.encodeText(new String(strText.getBytes(),
					"GB2312"), "GB2312", "B");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strText;
	}

	/**
	 * <br>
	 * 方法说明：往附件组合中添加附�?<br>
	 * 输入参数�?<br>
	 * 返回类型�?
	 */
	public void attachfile(String fname) {
		file.addElement(fname);
	}

	/**
	 * <br>
	 * 方法说明：发送邮�?<br>
	 * 输入参数�?<br>
	 * 返回类型：boolean 成功为true，反之为false
	 */
	public boolean sendMail() {
		// 构�?mail session
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		//props.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(props,
				new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		// Session session = Session.getDefaultInstance(props);
		// Session session = Session.getDefaultInstance(props, null);

		try {
			// 构�?MimeMessage 并设定基本的�?
			MimeMessage msg = new MimeMessage(session);
			// MimeMessage msg = new MimeMessage();
			msg.setFrom(new InternetAddress(from));

			// msg.addRecipients(Message.RecipientType.TO, address);
			// //这个只能是给�?��人发送email
			msg.setRecipients(Message.RecipientType.BCC,
					InternetAddress.parse(to));
			subject = transferChinese(subject);
			msg.setSubject(subject);

			// 构�?Multipart
			Multipart mp = new MimeMultipart();

			// 向Multipart添加正文
			MimeBodyPart mbpContent = new MimeBodyPart();
			mbpContent.setContent(content, "text/html;charset=gb2312");

			// 向MimeMessage添加（Multipart代表正文�?
			mp.addBodyPart(mbpContent);

			// 向Multipart添加附件
			Enumeration efile = file.elements();
			while (efile.hasMoreElements()) {

				MimeBodyPart mbpFile = new MimeBodyPart();
				filename = efile.nextElement().toString();
				FileDataSource fds = new FileDataSource(filename);
				mbpFile.setDataHandler(new DataHandler(fds));
				// <span style="color: #ff0000;">//这个方法可以解决附件乱码问题�?/span>
				String filename = new String(fds.getName().getBytes(),
						"ISO-8859-1");

				mbpFile.setFileName(filename);
				// 向MimeMessage添加（Multipart代表附件�?
				mp.addBodyPart(mbpFile);

			}

			file.removeAllElements();
			// 向Multipart添加MimeMessage
			msg.setContent(mp);
			msg.setSentDate(new Date());
			msg.saveChanges();
			// 发�?邮件

			Transport transport = session.getTransport("smtp");
			transport.connect(host, username, password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (Exception mex) {
			mex.printStackTrace();
			// Exception ex = null;
			// if ((ex = mex.getNextException()) != null) {
			// ex.printStackTrace();
			// }
			return false;
		}
		return true;
	}

	/**
	 * <br>
	 * 方法说明：主方法，用于测�?<br>
	 * 输入参数�?<br>
	 * 返回类型�?
	 */
	/*public static void main(String[] args) {
		MailUtils sendmail = new MailUtils();
		sendmail.setHost("smtp.qq.com");
		sendmail.setUserName("marketing_platform@dxt.cn");
		sendmail.setPassWord("123!@#DXTxsd");
		sendmail.setTo("dawn@dxt.cn");
		sendmail.setFrom("marketing_platform@dxt.cn");
		sendmail.setSubject("银行付款录入信息�?);
		sendmail.setContent("你好，这是一个带附件的邮件！");
		// Mail sendmail = new
		// Mail("dujiang@sricnet.com","du_jiang@sohu.com","smtp.sohu.com","du_jiang","31415926","你好","胃，你好吗？");
		sendmail.attachfile("c:\\银行转账.xls");
//		sendmail.attachfile("d:\\jhjl.rar");
		System.out.println(sendmail.sendMail());

	}*/

}
