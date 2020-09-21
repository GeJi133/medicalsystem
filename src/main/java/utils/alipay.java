package utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import controller.adminManageController;
import entity.Recharge;

import java.text.SimpleDateFormat;
import java.util.Date;

public class alipay {

    public static String pay(Recharge recharge) throws AlipayApiException {
                String WIDout_trade_no = String.valueOf (recharge.getRid ());
        String WIDsubject = "patient recharge";
        System.out.println (recharge.getAmount ());
        System.out.println (String.valueOf (recharge.getAmount ()));
        String WIDtotal_amount =String.valueOf (recharge.getAmount ()) ;
        String WIDbody = String.valueOf (recharge.getAmount ());
        String passback_params2=String.valueOf (recharge.getRid ());

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
        String total_amount = total_fee.replace(",", "");
        String biz_content="{\"out_trade_no\":\"" + out_trade_no + "\"," +
                "\"total_amount\":\"" + total_fee + "\"," +
                "\"subject\":\"" + subject + "\"," +
                "\"body\":\"" + body + "\","+
                "\"passback_params\":\""+passback_params2+"\"," +
                "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";
        alipayRequest.setBizContent(biz_content);
        System.out.println ("发送参数"+biz_content);

        // 请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        return result;
    }

}
