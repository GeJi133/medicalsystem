package utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class AlipayConfigUtil {
    // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id="2016102400751952";//例：2016082600317257
    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCAp0Ty1Noycs22DHDP23LPqP4lbTTplqpc++qDKWHT9YxUwuc+/PMFyEtRPURdh30ViKK5rV8+H3bQKvx4iSCXud8/SYnY8ocuAF0CkIHrF6cWfZE9SNsRJZRl6UqGtp4JEZUKZGSKf87HxOwcqFFQJtFdjquelHpgRNj3QBx4VVppK4CfbSZBPXZblX0c6UN8BHq9NaGNI1RHdYKThX5QbzOhEP34kFAATH3q2IyyjMzDvmcnD4xQLKar74KLdEq/Wje/JmAGEHB6nmfb1lNXXzo3OfxKSCAT2Qm3NvgwgiOh6vuk6eMccSR9yeOeNniZhN7sD6/A1ZTYcKgXGxG/AgMBAAECggEAXMtJOfU45IZVxec3P1VDfHChzxDzxz/65VJ2leA1RZKog0KJVK9SAmuzl4cfVm6xU+TXqiFYvxAmvIMk0zPlJErWsWkzWh2kTxPIAl+EtbK7u8caCSARkk6OuOsJ7Byv5E6gN3yt4A/Lb9+jhhAJhkxZWqfbNWhW1DUUYVIGtuuV7rEE77ElBqmhDbTr/BJWbNcmUXIw4bVGmAVlwh8sTZ3b/CqZLGU78cz8LbQjjuVmxzVgEw6KL9y/ECLXMvpvYUsxKdsh6UGr831yvFenez4m5g4r51lF8UoaTndw5haMOXsb58USHVhbYwVtRKiztyYTu7mmRlQx343S0P+asQKBgQD6Y5zrD2fmGzBpBP8hLi2xs/L6GcHCvTrJZpoAly+ZB8NUC8amM2xJRfgoKVJMLQU1p7/c8WNxsgAtA4PG/hfCecSKYd4sBk6nmTDOQt1jZ+0ldGLHXrT72mvfQsbrWq6VmqJjniyv69TVqRqDnAmNfSpGynZOtnlbKgTqYCs/swKBgQCDiU4NhDP5K+sFem2150+6uogLu2XoK8eG2lZFn/tlpHdw1P9cVeZtIhFHJskQZyc5oZDzo94YTwCEM/DHOrsRGG+zKhHR6Gggb25TEIyEFRCm5asqn0Wljxd4VFh8BaZlo1XHa3R0+j7JJGvfSamu5lxKPmc1QpVe43a+Z0I/xQKBgDaFBh/wpNDnzqyvV9YjtfNhmWB3x0bd6TT9UD8crFKH7DIatlBuFgAGajhkdvT7QI0kT5SOQvb4qS0j/MeRgB7qYK85DCVN4kaABq6CxK3mbS6gnvmGV6YXMzHT2nhYsHcGyqEiSdysXxV+0rUhfrr+K/xqDUSqWtZM2fEmq4QdAoGASEKO2YgOEU2aMAlA6hRnzXoZsPBDHJeoZiHeESPBNOZ0I00v9+bMzplPipn5DQTOHK/wdJhKM2p7KyVpmdle2RZmjrOG1JzrTx5IKmPF/e2glz9GmASlez0vtJAJiCCGeAGR5xrWAElVYqSaijDqgcXVX4gGpNCuRFGLczBAyc0CgYBru/fgXTTbtZufBk+o+CMzXJYw3BMLkFscL4SS3W+julXyVVbWv2+siiFbxfESKYEZjku2DT+bwYheBxZ1rpD45AHnOGDcyvGjP4gjk4U9XTYuFvCqpscO9CiQ+2ii1veT4DCFYdUTKoKzdk6CF3vzl/UbcGZFrtFc/aFX38AdeQ==";
    public static String alipay_public_key ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy6MTP7oCy3A7Bl89IQwybd77Kg7D3Ed46oZoqL8AlooWs5sP+v26GK9ZUlE5M3qyqU5KjLsqIfm1wkGxsEvCm441wcyl9JtFs6rk5sTRmVRbeSnI02EbIWIKyyn8ICt+R3FuphF3QGnKszgFH9rpf7YfxWQRrYA/eGEv2+znyiTUEwQAmxq62rxIwMDPA9pyRgJY9jvmM1EFi2Jwn1RSBKTmFhjy2RWCMNc6Gh9Xj6bk9UvX5WekldL36/3Wi89IHqZ91goY8YR9isZgvtqXz1Mz9qVUPjYYbF0mPeIZKPjheyqLAV+w4+je3gj4PSljSZUc6u8JyJ5enpb6jHEkbwIDAQAB";
     /* 返回的时候此页面不会返回到用户页面，只会执行你写到控制器里的地址
     */
    public static String notify_url=" http://javafx.free.idcfengye.com/notify";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    
    /**
     * 此页面是同步返回用户页面，也就是用户支付后看到的页面，上面的notify_url是异步返回商家操作，谢谢
     * 要是看不懂就找度娘，或者多读几遍，或者去看支付宝第三方接口API，不看API直接拿去就用，遇坑不怪别人,要使用外网能访问的ip,建议使用花生壳,内网穿透
     */
    public static String return_url = "http://javafx.free.idcfengye.com/payback";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";
    // 支付宝网关
    public static String gatewayUrl="https://openapi.alipaydev.com/gateway.do";
    // 日志地址,这里在d盘下要创建这个文件,不然会报错
    public static String log_path = "D:/logs/";
    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord
     *            要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_"
                    + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//
//    public static boolean checkSign(HttpServletRequest req) {
//        Map<String, String[]> requestMap = req.getParameterMap();
//        Map<String, String> paramsMap = new HashMap<> ();
//        requestMap.forEach((key, values) -> {
//            String strs = "";
//            for(String value : values) {
//                strs = strs + value;
//            }
//            System.out.println(("key值为"+key+"value为："+strs));
//            paramsMap.put(key, strs);
//        });
//
//        //调用SDK验证签名
//        try {
//            return  AlipaySignature.rsaCheckV1(paramsMap, alipay_public_key,charset, sign_type);
//        } catch (AlipayApiException e) {
//             TODO Auto-generated catch block
//            e.printStackTrace();
//            System.out.println("*********************验签失败********************");
//            return false;
//        }
//    }
//    public static Map<String, String> turnToHash(HttpServletRequest req){
//        Map<String, String[]> requestMap = req.getParameterMap();
//        Map<String, String> paramsMap = new HashMap<> ();
//        requestMap.forEach((key, values) -> {
//            String strs = "";
//            for(String value : values) {
//                strs = strs + value;
//            }
//            System.out.println(("key值为"+key+"value为："+strs));
//            paramsMap.put(key, strs);
//        });
//        return paramsMap;
//    }

}

