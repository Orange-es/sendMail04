/**
 * @author JingBoWen
 * @date 2022/3/29 22:16
 * @annotation：
 */
public class success {
    public static void main(String[] args) throws Exception {
        String mailAccount = "786788651@qq.com";
        System.out.println( "Hello World!" );
        SendMail sendMail = new SendMail();
        String s = sendMail.send(mailAccount, "plxloplyaicobebi", mailAccount, "测试！", "发邮件！");
        System.out.println(s);
    }
}
