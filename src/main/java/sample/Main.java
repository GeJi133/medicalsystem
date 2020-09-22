package sample;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import controller.adminManageController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.commons.lang.StringEscapeUtils;
import utils.*;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/ui/MainLoginFXML.fxml"));
        Scene scene = new Scene(root,1080,720);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(
                getClass().getResource("/ui/MainDocStyle.css").toExternalForm());
        primaryStage.setTitle("医疗管理系统");
        primaryStage.show();

        //发送post请求
//        Map<String,String> params=new HashMap<String, String> ();
//        String biz_content= "{&quot;out_trade_no&quot;:&quot;20200917100234779&quot;,&quot;total_amount&quot;:&quot;5&quot;,&quot;subject&quot;:&quot;String.valueOf (order.getOrderId ())&quot;,&quot;body&quot;:&quot;order.getOrderDate ().toString ()&quot;,&quot;passback_params&quot;:&quot;33ECFEF918DB62F08D907076B4CDA963&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;}";
//        biz_content= StringEscapeUtils.unescapeHtml (biz_content);
//
////        biz_content="llll";
//        System.out.println (biz_content);
//        params.put("biz_content",biz_content);
//        String url="https://openapi.alipaydev.com/gateway.do?charset=utf-8&method=alipay.trade.page.pay&sign=j8N%2B3d9Zs%2BeHByMEwAqZOrStdXMcCGOFSZhR1dkExX50aDEjYjZi3lwBy%2FY8mx2%2FXEMYcv4xifmoA6%2Ff6zLZkxEyEk2jkvk9gJFWGZS4vsFy7hfxIF3B2uVuiwBT3Z78PY9fUzgKAXbG4A1udCMKoPqlZ8CH6GsHZvM92wOzCbT1kQXrWt40V4wJDrqeyW8LHkCiIrk%2BAyEGaA8lxpW0CXGsLOqVJ4r7pMuiWBDWs%2FyKb2NCwsRrEggFgwX%2Begu%2Fgv8Nar4%2FK%2BUZPciGG4Zs%2BBE7cu5nNlrPuqyI0Rry2CAVKW%2B0TReBRpc%2BaBdpcbjeUIwrfZKgm3AwNAgifX8nMQ%3D%3D&return_url=http%3A%2F%2F39.98.45.50%2Falipay%2FaliCallback&notify_url=http%3A%2F%2F39.98.45.50%2Falipay%2Fnotify_url&version=1.0&app_id=2016102400751952&sign_type=RSA2&timestamp=2020-09-17+10%3A02%3A35&alipay_sdk=alipay-sdk-java-dynamicVersionNo&format=json";
//        System.out.println (HttpModelFormRequest.sendPostLikeForm (url,params));
//        System.out.println (HttpUtil.http (url,params));
        //testalipay
//        String WIDout_trade_no = "11";
//        String WIDsubject = "1111";
//        String WIDtotal_amount = "555";
//        String WIDbody = "111";
//
//        String passback_params2="kk";
//
//        String payables = WIDout_trade_no;
//        // 订单名称，必填(必须是数字)
//        String subject = WIDsubject;
//        // 付款金额，必填
//        String total_fee = WIDtotal_amount;
//        // 商品描述，可空
//        String body = WIDbody;
//
//        // 获得初始化的AlipayClient
//
//        AlipayClient alipayClient = new DefaultAlipayClient (AlipayConfigUtil.gatewayUrl, AlipayConfigUtil.app_id,
//                AlipayConfigUtil.merchant_private_key, "json", AlipayConfigUtil.charset,
//                AlipayConfigUtil.alipay_public_key, AlipayConfigUtil.sign_type);
//        // 设置请求参数
//        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//        alipayRequest.setReturnUrl(AlipayConfigUtil.return_url);
//        alipayRequest.setNotifyUrl(AlipayConfigUtil.notify_url);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        // 商户订单号，商户网站订单系统中唯一订单号，必填
//        String out_trade_no = sdf.format(new Date ());
//        // 付款金额，必填
//        String total_amount = payables.replace(",", "");
//        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," +
//                "\"total_amount\":\"" + total_amount + "\"," +
//                "\"subject\":\"" + subject + "\"," +
//                "\"body\":\"" + body + "\","+
//                "\"passback_params\":\""+passback_params2+"\"," +
//                "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
//        // 请求
//        String result = alipayClient.pageExecute(alipayRequest).getBody();
//        ALipayFileChange.changeFile (result);
//
//        Parent root = FXMLLoader.load(getClass().getResource("/ui/alipay.fxml"));
//        primaryStage.setTitle("JAVAFX嵌入html测试");
//        primaryStage.setScene(new Scene(root, 600, 600));
//        primaryStage.show();




//        System.out.println (result);
//         System.out.println(result);
//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
//        AlipayConfigUtil.logResult(result);// 记录支付日志
//        response.setContentType("text/html; charset=gbk");
//        PrintWriter out = response.getWriter();
//        out.print(result);
//        return null;
//        AliyunConfig.sendSms ("18956778818");


    }


public void close(ActionEvent event) {
    ((Node) (event.getSource())).getScene().getWindow().hide();
}
    public static void main(String[] args) {
        launch(args);
    }
}
