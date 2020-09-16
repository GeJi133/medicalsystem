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
import utils.AlipayConfigUtil;
import utils.AliyunConfig;
import utils.HttpTest;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/ui/MainLoginFXML.fxml"));
//        Scene scene = new Scene(root,900,550);
//        primaryStage.setScene(scene);
//        scene.getStylesheets().add(
//                getClass().getResource("/ui/MainDocStyle.css").toExternalForm());
//        primaryStage.setTitle("医疗管理系统");
//        primaryStage.show();
//


        //testalipay
        String WIDout_trade_no = "11";
        String WIDsubject = "1111";
        String WIDtotal_amount = "555";
        String WIDbody = "111";

        String passback_params2="kk";

        String payables = WIDout_trade_no;
        // 订单名称，必填(必须是数字)
        String subject = WIDsubject;
        // 付款金额，必填
        String total_fee = WIDtotal_amount;
        // 商品描述，可空
        String body = WIDbody;

        // 获得初始化的AlipayClient

        AlipayClient alipayClient = new DefaultAlipayClient (AlipayConfigUtil.gatewayUrl, AlipayConfigUtil.app_id,
                AlipayConfigUtil.merchant_private_key, "json", AlipayConfigUtil.charset,
                AlipayConfigUtil.alipay_public_key, AlipayConfigUtil.sign_type);
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfigUtil.return_url);
        alipayRequest.setNotifyUrl(AlipayConfigUtil.notify_url);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = sdf.format(new Date ());
        // 付款金额，必填
        String total_amount = payables.replace(",", "");
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," +
                "\"total_amount\":\"" + total_amount + "\"," +
                "\"subject\":\"" + subject + "\"," +
                "\"body\":\"" + body + "\","+
                "\"passback_params\":\""+passback_params2+"\"," +
                "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        // 请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        System.out.println (result);
        // System.out.println(result);
        //EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
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
