package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class MainLoginController {
    public void enterDocLogin(ActionEvent actionEvent) throws IOException {
        Stage docLoginStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/ui/DoctorLoginFXML.fxml"));
        docLoginStage.setTitle("医生管理系统");
        docLoginStage.setScene(new Scene(root, 600, 400));
        docLoginStage.show();
        new Main ().close(actionEvent);
    }
    public void enterAdminLogin(ActionEvent actionEvent) throws IOException  {
        Stage adminLoginStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/ui/adminLogin.fxml"));
        adminLoginStage.setTitle("管理员登录");
        adminLoginStage.setScene(new Scene(root, 600, 400));
        adminLoginStage.show();
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void toPatLogin(MouseEvent mouseEvent) throws IOException {
        Stage adminLoginStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/ui/patLoginFXML.fxml"));
        adminLoginStage.setTitle("患者登录");
        adminLoginStage.setScene(new Scene(root, 600, 400));
        adminLoginStage.show();
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
    }

    public void close(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}

