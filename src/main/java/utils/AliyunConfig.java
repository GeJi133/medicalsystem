package utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.Random;

/**
 * @Description: 阿里云短信接口配置类
 * @author: yangxf
 * @date: 2019/4/11 15:01
 */
public class AliyunConfig {

    /* 短信API产品名称（短信产品名固定，无需修改） */
    private static final String product = "Dysmsapi";

    /* 短信API产品域名，接口地址固定，无需修改 */
    private static final String domain = "dysmsapi.aliyuncs.com";

    /* 此处需要替换成开发者自己的accessKeyId和accessKeySecret(在阿里云访问控制台寻找) */
    private static final String accessKeyId = "LTAI4G6Yv9TBUjSYx5JMt3jz"; //TODO: 这里要写成你自己生成的
    private static final String accessKeySecret = "OLVQMaqWrq1h6NVEvCkBIMf5M5QhFh";//TODO: 这里要写成你自己生成的

    /* 短信发送 */
    public static String sendSms(String phone) throws ClientException {

        /* 超时时间，可自主调整 */
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        /* 初始化acsClient,暂不支持region化 */
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        /* 组装请求对象-具体描述见控制台-文档部分内容 */
        SendSmsRequest request = new SendSmsRequest();
        /* 必填:待发送手机号 */
        request.setPhoneNumbers(phone);
        /* 必填:短信签名-可在短信控制台中找到 */
        request.setSignName("CSUDazz宠物商店"); //TODO: 这里是你短信签名
        /* 必填:短信模板code-可在短信控制台中找到 */
        request.setTemplateCode("SMS_187271386"); //TODO: 这里是你的模板code
        /* 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为 */
        String msgCode=getMsgCode ();
        request.setTemplateParam("{\"code\":\"" + msgCode + "\"}");

        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        System.out.println (sendSmsResponse);
        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
            System.out.println("短信发送成功！验证码：" + msgCode);
        }
        else {
            System.out.println("短信发送失败！");
        }
        return msgCode;

    }

    /**
     * @Function: 生成验证码
     * @author:   yangxf
     * @Date:     2019/4/11 15:30
     */
    private static String getMsgCode() {
        int n = 6;
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            code.append(Integer.valueOf(ran.nextInt(10)).toString());
        }
        return code.toString();
    }
}
