package controller;

import com.aliyuncs.exceptions.ClientException;
import dao.AdminInfoDao;
import dao.PatientInfoDao;
import entity.Admininfo;
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

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminLoginByPhoneController {

    @FXML
    private TextField phonenumber;
    @FXML
    private TextField vcode;
    private String smsCode;

    //注册成功初始化函数
    public void Init(){
        phonenumber.setText(String.valueOf(PatientInfoDao.patInfo.getPatId()));
    }
    //登录响应函数
    @FXML
    public void adminLogin(MouseEvent mouseEvent) {
        String strPhone = phonenumber.getText();
        String strVcode = vcode.getText();
        PatientInfo patientinfo = new PatientInfo();
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
                    Stage patStage = new Stage();
                    URL location = getClass().getResource("/ui/adminManage.fxml");
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(location);
                    fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory ());
                    Parent root = fxmlLoader.load();
                    patStage.setTitle("医疗管理系统");
                    Scene scene = new Scene(root, 1500, 950);
                    patStage.setScene(scene);
                    adminManageController controller = fxmlLoader.getController();   //获取Controller的实例对象

                    DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");

                    controller.logintime = df.format(new Date ());
                    AdminInfoDao adminInfoDao=new AdminInfoDao ();

                    Admininfo admininfo=adminInfoDao.getAdminInfo (strPhone);

                    controller.setInfo(admininfo.getAdminAccount (),admininfo.getAdminPassword ());
                    controller.init();
                    patStage.show();
                    ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
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
    @FXML
    public void toPatRegister(MouseEvent mouseEvent) {
        try {
            Stage patStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/ui/patRegisterFXML.fxml"));
            patStage.setTitle("患者选择界面");
            patStage.setScene(new Scene(root, 600, 400));
            patStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
    }
    //跳转至报表统计界面
    public void toNew(MouseEvent mouseEvent) {
//        try {
//            Stage patStage = new Stage();
//            URL location = getClass().getResource("/ui/baobiaotongjiFXML.fxml");
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(location);
//            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
//            Parent root = fxmlLoader.load();
//            patStage.setTitle("患者基本信息");
//            Scene scene = new Scene(root, 1200, 900);
//            patStage.setScene(scene);
//            baobiaotongjiController controller = fxmlLoader.getController();   //获取Controller的实例对象
//            controller.Init();
//            patStage.show();
//            ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
    }

    public void patLoginRet(MouseEvent mouseEvent) {
        try {
            Stage patStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/ui/MainLoginFXML.fxml"));
            patStage.setTitle("欢迎使用医疗管理信息系统！");
            patStage.setScene(new Scene(root, 900, 550));
            patStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
    }

    public void loginByPhone(ActionEvent actionEvent) {

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

        AdminInfoDao adminInfoDao = new AdminInfoDao ();
        System.out.println ("手机号"+strPhone);
        int count = adminInfoDao.adminLoginByPhone (strPhone);
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

    public void returnToMain(ActionEvent actionEvent) {

        Stage adminManage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ui/MainLoginFXML.fxml"));
            adminManage.setTitle("医疗管理系统");
            adminManage.setScene(new Scene(root, 900, 550));
            adminManage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
