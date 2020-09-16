package controller;

import com.aliyuncs.exceptions.ClientException;
import dao.DoctorinfoDao;
import dao.PatientInfoDao;
import entity.Doctorinfo;
import entity.PatientInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.AliyunConfig;

import java.io.IOException;
import java.net.URL;

public class DoctorLoginByPhoneController {

    @FXML
    private TextField phonenumber;
    @FXML
    private TextField vcode;
    private String smsCode;

    //注册成功初始化函数
    public void Init(){
//        phonenumber.setText(String.valueOf(DoctorinfoDao.doc.getPatId()));
    }
    //登录响应函数
    @FXML
    public void patLogin(MouseEvent mouseEvent) {
        String strPhone = phonenumber.getText();
        String strVcode = vcode.getText();
        Doctorinfo doctorinfo = new Doctorinfo ();
        if(strPhone.isEmpty() || strVcode.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("警告");
            alert.setContentText("手机号和验证码不能为空！");
            alert.show();
        }
        else {
            if(strVcode.equals (smsCode))
            {
                System.out.println ("验证码正确");
                try{
                    DoctorinfoDao doctorinfoDao=new DoctorinfoDao ();
                    doctorinfo=doctorinfoDao.selectDoctorInfoByPhone(strPhone);
                    Stage mainStage=new Stage();
                    URL location = getClass().getResource("/ui/MainDocFXML.fxml");
                    FXMLLoader fxmlLoader=new FXMLLoader();
                    fxmlLoader.setLocation(location);
                    fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory ());
                    Parent root = fxmlLoader.load();
                    MainDocController control=(MainDocController) fxmlLoader.getController();
                    control.initDocName(doctorinfo);

                    mainStage.setTitle("医生管理系统");
                    mainStage.setScene(new Scene(root, 1500, 950));
                    mainStage.show();

                }
                catch (Exception e){
                    e.printStackTrace();
                }
                ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
            }
            else {
                System.out.println ("验证码"+vcode+"发送验证码"+smsCode);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("用户名或密码不正确，请重新输入");
                alert.show();
                vcode.clear();
            }
        }
    }
    //注册响应函数
    public void docRet(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/ui/MainLoginFXML.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("医生登录");
        stage.show();
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void toPatLoginByPhone(MouseEvent mouseEvent) {
        try {
            Stage adminLoginStage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/ui/PatLoginByPhone.fxml"));
            adminLoginStage.setTitle("患者登录");
            adminLoginStage.setScene(new Scene(root, 600, 400));
            adminLoginStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
//        SortEvent<Object> mouseEvent;
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();

    }

    public void sendSms(MouseEvent mouseEvent) throws ClientException {
        String strPhone=phonenumber.getText ();

        DoctorinfoDao doctorinfoDao = new DoctorinfoDao ();
        System.out.println ("手机号"+strPhone);
        int count = doctorinfoDao.selectDoctorByPhone (strPhone);
        if(count <= 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("手机号不存在");
            alert.show();
            vcode.clear();
        }
        else {
            smsCode = AliyunConfig.sendSms (strPhone);
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setHeaderText ("验证码发送成功");
            alert.show ();
            vcode.clear ();
        }
    }
}
