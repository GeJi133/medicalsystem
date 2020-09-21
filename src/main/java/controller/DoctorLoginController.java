package controller;

import dao.DoctorinfoDao;
import entity.Doctorinfo;
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
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.net.URL;


public class DoctorLoginController {

    @FXML private TextField docId;
    @FXML private PasswordField docPwd;

    @FXML
    public static Doctorinfo doctorinfo;

    public static Doctorinfo getDoctorinfo(){
        return doctorinfo;
    }
    public void toDoctorLoginByPhone(MouseEvent mouseEvent) {
        try {
            Stage docLoginStage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/ui/DoctorLoginByPhone.fxml"));
            docLoginStage.setTitle("医生管理系统");
            docLoginStage.setScene(new Scene(root, 1080, 720));
            docLoginStage.show();
//            new Main ().close(actionEvent);
//
//            Stage adminLoginStage=new Stage();
//            Parent root = FXMLLoader.load(getClass().getResource("/ui/PatLoginByPhone.fxml"));
//            adminLoginStage.setTitle("患者登录");
//            adminLoginStage.setScene(new Scene(root, 600, 400));
//            adminLoginStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
//        SortEvent<Object> mouseEvent;
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();

    }
    public void doctorLogin(ActionEvent event){

        String strName=docId.getText();
        String strPwd=docPwd.getText();
        doctorinfo=new Doctorinfo();
        doctorinfo.setDocId(Integer.parseInt(strName));
        doctorinfo.setDocPwd(strPwd);
        DoctorinfoDao doctorinfoDao=new DoctorinfoDao();
        int count=doctorinfoDao.selectDoctor(doctorinfo);
        if (count>0){
            try {
                Stage mainStage=new Stage();
                URL location = getClass().getResource("/ui/MainDocFXML.fxml");
                FXMLLoader fxmlLoader=new FXMLLoader();
                fxmlLoader.setLocation(location);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
                Parent root = fxmlLoader.load();
                MainDocController control=(MainDocController) fxmlLoader.getController();
                control.initDocName(doctorinfo);

                mainStage.setTitle("医生管理系统");
                mainStage.setScene(new Scene(root, 1080, 720));
                mainStage.show();
                new MainLoginController().close(event);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.headerTextProperty().set("用户名或密码不正确，请重新输入");
            alert.show();
        }
    }

    public void docRet(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/ui/MainLoginFXML.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("医生登录");
        stage.show();
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
