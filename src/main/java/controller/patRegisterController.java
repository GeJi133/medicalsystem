package controller;

import dao.PatientInfoDao;
import entity.PatientInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;

public class patRegisterController {
    @FXML private TextField patName;
    @FXML TextField patAge;
    @FXML ComboBox patGender;
    @FXML PasswordField patPassword;
    @FXML PasswordField ConfirmPassword;
    @FXML TextField patTel;
    //注册按钮响应函数
    @FXML
    public void patRegister(MouseEvent mouseEvent){
        String strName = patName.getText();
        String strAge = patAge.getText();
        String strGender = (String) patGender.getValue();
        String strPassWord = patPassword.getText();
        String strConfirmPassword = ConfirmPassword.getText();
        String strTel = patTel.getText();
        if(strName.isEmpty() || strAge.isEmpty() || strGender.isEmpty()
                || strPassWord.isEmpty() || strConfirmPassword.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("未完善信息");
            alert.setHeaderText("警告");
            alert.setContentText("请输入完整的信息后提交！");
            alert.show();
        }
        else if (!strPassWord.equals(strConfirmPassword)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("错误");
            alert.setHeaderText("错误");
            alert.setContentText("两次输入的密码不相同，请重新输入！");
            alert.show();
            patPassword.clear();
            ConfirmPassword.clear();
        }
        else if(!strGender.trim().equals("男") && !strGender.trim().equals("女")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("错误");
            alert.setHeaderText("错误");
            alert.setContentText("请输入正确的性别！");
            alert.show();
            patPassword.clear();
            ConfirmPassword.clear();
        } else {
            if (strTel.isEmpty()) strTel = "null";
            PatientInfo patientinfo = new PatientInfo();
            patientinfo.setPatName(strName);
            patientinfo.setPatGender(strGender);
            patientinfo.setPatAge(Integer.parseInt(strAge));
            patientinfo.setPatPwd(strPassWord);
            patientinfo.setPatTel(strTel);
            PatientInfoDao patientdao = new PatientInfoDao();
            int newId = patientdao.patRegister(patientinfo);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("成功");
            alert.setHeaderText("注册成功！");
            alert.setContentText("您的卡号为：" + newId + "  请牢记该卡号，这将作为您登录的凭证！");
            patPassword.clear();
            ConfirmPassword.clear();
            PatientInfoDao.patInfo.setPatId(newId);
            try {
                Stage primaryStage = new Stage();
                URL location = getClass().getResource("/ui/patLoginFXML.fxml");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(location);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
                Parent root = fxmlLoader.load();
                primaryStage.setTitle("患者登录");
                Scene scene = new Scene(root, 600, 400);
                primaryStage.setScene(scene);
                patLoginController controller = fxmlLoader.getController();   //获取Controller的实例对象
                controller.Init();
                primaryStage.show();
                ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            alert.show();
        }
        return ;
    }
    //返回按钮响应函数
    public void patRegisterRet(MouseEvent mouseEvent) {
        try {
            Stage patStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/ui/patLoginFXML.fxml"));
            patStage.setTitle("患者登录");
            patStage.setScene(new Scene(root, 600, 400));
            patStage.show();
            ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
