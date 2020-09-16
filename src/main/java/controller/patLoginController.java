package controller;

import dao.PatientInfoDao;
import entity.PatientInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;

public class patLoginController {
    @FXML private TextField patId;
    @FXML private PasswordField patPassword;
    //注册成功初始化函数
    public void Init(){
        patId.setText(String.valueOf(PatientInfoDao.patInfo.getPatId()));
    }
    //登录响应函数
    @FXML
    public void patLogin(MouseEvent mouseEvent) {
        String strPatId = patId.getText();
        String strPatPwd = patPassword.getText();
        PatientInfo patientinfo = new PatientInfo();
        if(strPatId.isEmpty() || strPatPwd.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("警告");
            alert.setContentText("用户名和密码均不能为空！");
            alert.show();
        }
        else {
            patientinfo.setPatId(Integer.parseInt(strPatId));
            patientinfo.setPatPwd(strPatPwd);
            PatientInfoDao patientdao = new PatientInfoDao();
            int count = patientdao.patLogin(patientinfo);
            if(count > 0){
                try{
                    PatientInfoDao init = new PatientInfoDao();
                    PatientInfoDao.patInfo = init.selectPatInfo(Integer.parseInt(strPatId));
                    Stage patStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/ui/patOptionFXML.fxml"));
                    patStage.setTitle("患者选择界面");
                    patStage.setScene(new Scene(root, 600, 400));
                    patStage.show();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("成功");
                    alert.setContentText("登录成功！");
                    alert.show();
                    ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("用户名或密码不正确，请重新输入");
                alert.show();
                patPassword.clear();
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
}
