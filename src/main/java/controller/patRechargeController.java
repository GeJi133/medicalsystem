package controller;

import dao.PatientInfoDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.AlipayConfigUtil;
import utils.HttpTest;

import java.io.IOException;

public class patRechargeController {
    @FXML private TextField cardRemain;
    @FXML private TextField amount;
    @FXML private ComboBox payMethod;
    @FXML private ImageView image;
    //患者充值界面初始化
    public void Init(){
        cardRemain.setText(String.valueOf(PatientInfoDao.patInfo.getPatDeposit()));
    }
    //充值按钮响应函数
    @FXML
    public void Recharge(MouseEvent mouseEvent) {
        if (Double.parseDouble(amount.getText()) < 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("失败");
            alert.setHeaderText("充值失败！");
            alert.setContentText("请输入正确的充值金额");
            alert.show();
            amount.clear();
        }
        else {
            PatientInfoDao patientdao = new PatientInfoDao();
            int count = patientdao.patRecharge(Double.parseDouble(amount.getText()));
            if(count > 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("成功");
                alert.setHeaderText("充值成功！");
                alert.setContentText("您已充值成功，目前卡内余额为："+ (PatientInfoDao.patInfo.getPatDeposit() + Double.parseDouble(amount.getText())));
                alert.show();
                PatientInfoDao.patInfo.setPatDeposit(Float.parseFloat(String.valueOf(PatientInfoDao.patInfo.getPatDeposit() + Double.parseDouble(amount.getText()))));
                cardRemain.setText(String.valueOf(PatientInfoDao.patInfo.getPatDeposit()));
                amount.clear();
            }
        }
    }
    //返回按钮响应函数
    @FXML
    public void RechargeRet(MouseEvent mouseEvent) {
        try {
            Stage patStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/ui/patOptionFXML.fxml"));
            patStage.setTitle("患者选择界面");
            patStage.setScene(new Scene(root, 600, 400));
            patStage.show();
            ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //选择充值方式
    public void MethodChose(ActionEvent actionEvent) throws IOException {
        String method = (String) payMethod.getValue();
        Image img;
//        if (method.equals("微信")) {
//            img = new Image("image/WeChat.png");
//
//
//        }
        if(method.equals("支付宝")){
            //支付宝支付

            img = new Image("image/AliPay.png");
            int success=HttpTest.waitForResponse ();
            if(success==1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("成功");
                alert.setHeaderText("充值成功！");
                alert.setContentText("您已充值成功，目前卡内余额为："+ (PatientInfoDao.patInfo.getPatDeposit() + Double.parseDouble(amount.getText())));
                alert.show();
            }
            if(success==1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("失败");
                alert.setHeaderText("充值失败，请稍后重试！");
                alert.setContentText("您已充值成功，目前卡内余额为："+ (PatientInfoDao.patInfo.getPatDeposit() + Double.parseDouble(amount.getText())));
                alert.show();

            }
        }
        else {
            img = new Image("image/empty.png");
        }
        image.setImage(img);
    }
}
