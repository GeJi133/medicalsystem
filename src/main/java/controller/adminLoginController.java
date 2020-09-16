package controller;

import dao.AdminInfoDao;
import entity.Admininfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class adminLoginController {
    @FXML private TextField account;
    @FXML private PasswordField pwd;

    @FXML
    public void adminlogin(ActionEvent actionEvent) {

        int admin_acc = Integer.parseInt(account.getText());
        String admin_pwd = pwd.getText();

        Admininfo admin = new Admininfo(admin_acc,admin_pwd);
        AdminInfoDao admindao = new AdminInfoDao();
        int count = admindao.AdminLogin(admin);

        if (count > 0)
        {
            try {
                Stage patStage = new Stage();
                URL location = getClass().getResource("/ui/adminManage.fxml");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(location);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
                Parent root = fxmlLoader.load();
                patStage.setTitle("医疗管理系统");
                Scene scene = new Scene(root, 1500, 950);
                patStage.setScene(scene);
                adminManageController controller = fxmlLoader.getController();   //获取Controller的实例对象

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                controller.logintime = df.format(new Date());
                controller.setInfo(admin_acc,admin_pwd);
                controller.init();
                patStage.show();
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else
        {
            account.clear();
            pwd.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"账号或密码错误！");
            alert.show();
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


    public void loginByPhone(MouseEvent mouseEvent) throws IOException {

        Stage adminLoginStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/ui/AdminLoginByPhone.fxml"));
        adminLoginStage.setTitle("管理员登录");
        adminLoginStage.setScene(new Scene(root, 600, 400));
        adminLoginStage.show();
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
    }
}

