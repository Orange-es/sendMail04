import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @author JingBoWen
 * @date 2022/3/29 22:15
 * @annotation：
 */
public class SendMail {
    public String send(String sendAccount, String sendPwd, String addressee, String subject, String conText) throws Exception {

        Properties properties = new Properties();
        //设置发送协议
        properties.put("mail.transport.protocol", "smtp");
        //设置发送邮件的主机名
        properties.put("mail.smtp.host", "smtp.qq.com");
        //创建一个session对象  （桥梁）
        Session session = Session.getDefaultInstance(properties);
        //创建一个transport对象  （理解为发送邮件的输出流）
        Transport ts = session.getTransport();
        ts.connect(sendAccount, sendPwd);

        MimeMessage mimeMessage = creatMessage(session, sendAccount, addressee, subject, conText);
        //直接发送
        ts.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        //关闭流操作
        ts.close();
        return "发送完成";
    }


    //帮创建一个邮件对象
    private static MimeMessage creatMessage(Session session, String sendAccount, String addressee, String subject, String conText) throws MessagingException {
        //创建一个对象
        MimeMessage mimeMessage = new MimeMessage(session);
        //邮件对象是空对象
        //邮件发件人
        mimeMessage.setFrom(new InternetAddress(sendAccount));
        //To：发送人  cc 抄送人  bcc 暗送人
        //单人
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(addressee));
        //多人群发
//        InternetAddress address1  = new InternetAddress("xxxxx@qq.com");
//        InternetAddress address2 = new InternetAddress("xxxxx@qq.com");
//        InternetAddress address3  = new InternetAddress("xxxxx@qq.com");
        //mimeMessage.setRecipients(MimeMessage.RecipientType.TO,new InternetAddress[]{address1,address2,address3});
        //发送时间 不写默认是现在的时间
        //mimeMessage.setSentDate(new Date());
        //发送主题
        mimeMessage.setSubject(subject);
        //发送内容
        mimeMessage.setText(conText + "\n现在的时间是：" + new Date().toString());
        //上面的操作得保存才可以生效
        mimeMessage.saveChanges();
        //返回
        return mimeMessage;
    }
}