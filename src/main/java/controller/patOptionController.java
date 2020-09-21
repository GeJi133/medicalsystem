package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;

public class patOptionController {
    //跳转至患者基本信息
    @FXML
    public void toPatInfo(MouseEvent mouseEvent) {
        try {
            Stage patStage = new Stage();
            URL location = getClass().getResource("/ui/patFXML.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent root = fxmlLoader.load();
            patStage.setTitle("患者基本信息");
            Scene scene = new Scene(root, 1080, 720);
            patStage.setScene(scene);
            patController controller = fxmlLoader.getController();   //获取Controller的实例对象
            controller.Init();
            patStage.show();
            ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //跳转至预约挂号界面
    @FXML
    public void toPatAppoinment(MouseEvent mouseEvent) {
        try {
            Stage patStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/ui/patAppointmentFXML.fxml"));
            patStage.setTitle("患者预约挂号");
            patStage.setScene(new Scene(root, 1080, 720));
            patStage.show();
            ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //跳转至患者充值界面
    @FXML
    public void toPatRecharge(MouseEvent mouseEvent) {
        try {
            Stage patStage = new Stage();
            URL location = getClass().getResource("/ui/patRechargeFXML.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent root = fxmlLoader.load();
            patStage.setTitle("患者充值");
            Scene scene = new Scene(root, 1080, 720);
            patStage.setScene(scene);
            patRechargeController controller = fxmlLoader.getController();   //获取Controller的实例对象
            controller.Init();
            patStage.show();
            ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //跳转至患者详细信息界面
    @FXML
    public void toPatDetailInfo(MouseEvent mouseEvent) {
        try {
            Stage patStage = new Stage();
            URL location = getClass().getResource("/ui/patDetailFXML.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent root = fxmlLoader.load();
            patStage.setTitle("患者详细信息");
            Scene scene = new Scene(root, 1080, 720);
            patStage.setScene(scene);
            patDetailController controller = fxmlLoader.getController();   //获取Controller的实例对象
            controller.Init();
            patStage.show();
            ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //注销账号响应函数
    @FXML
    public void patLogout(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("确定注销");
        alert.setTitle("确认注销");
        alert.setContentText("您确定要注销账号吗？");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
            try {
                Stage patStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/ui/patLoginFXML.fxml"));
                patStage.setTitle("患者登录");
                patStage.setScene(new Scene(root, 1080, 720));
                patStage.show();
                ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
