package edu.swust.goods.utils;


import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;
/**
 * 负责邮件发送
 * @author hanpeng
 *
 */
public class MailUtil {
    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）, 
    //     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
    private static final String MY_EMAIL_ACCOUNT = "qishatu@163.com";
    private static final String MY_EMAIL_PASSWORDS = "929845mail";
    private static final String CHARSET = "UTF-8";
    private static final String SMTP = "smtp";
    private static final String FROM_NAME = "西南科技大学RFID实验室二手交易平台";
    private static final String TITLE = "西南科技大学二手交易平台账号验证码";
    private static final String USER = "亲爱的用户";
    private static final String MY_EMAIL_SMTP_HOST = "smtp.163.com";
    private static final String SMTP_HOST = "mail.smtp.host";
    private static final String EMAIL_HEAD= "亲爱的用户你好, 你的验证码是：";
    private static final String EMAIL_TAIL = "。温馨提示，验证码有效时间是5分钟。";
    private static final String EMAIL_HTML = "text/html;charset=UTF-8";
    private static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    private static final String MAIL_STMP_AUTH = "mail.smtp.auth";
    private static final String MAIL_TRUE = "true";
    private static Properties props = new Properties();
    private static Session session;
    static {
    	props.setProperty(MAIL_TRANSPORT_PROTOCOL, SMTP);   // 使用的协议（JavaMail规范要求）
        props.setProperty(SMTP_HOST, MY_EMAIL_SMTP_HOST);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty(MAIL_STMP_AUTH, MAIL_TRUE);            // 需要请求认证
        session = Session.getInstance(props);
    }
    
    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
    

    // 收件人邮箱（替换为自己知道的有效邮箱）
    public static String receiveMailAccount = "1220123963@qq.com";

    public static void main(String[] args) throws Exception {
        
    	String receiveMailAccount = "1220123963@qq.com";
    	System.out.println(sendEmail(receiveMailAccount, "123456"));
    	System.out.println(sendEmail("15892499063@163.com", "654321"));
    }

    
    public static boolean sendEmail(String receiveMailAccount, String reqCode) {
    	Transport transport = null;
    	boolean result = false;
    	try {
    		// 3. 创建一封邮件
            MimeMessage message = createMimeMessage(session, MY_EMAIL_ACCOUNT, receiveMailAccount, reqCode);

            // 4. 根据 Session 获取邮件传输对象
            transport = session.getTransport();

            // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
            transport.connect(MY_EMAIL_ACCOUNT, MY_EMAIL_PASSWORDS);

            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());

            result = true;
            
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			// 7. 关闭连接
			if (transport != null && transport.isConnected()) {
				try {
					transport.close();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
    	return result;
    	
	}
    
    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String reqCode) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(sendMail, FROM_NAME, CHARSET));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, USER, CHARSET));

        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject(TITLE, CHARSET);

        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        message.setContent(EMAIL_HEAD + reqCode + EMAIL_TAIL, EMAIL_HTML);

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

}
