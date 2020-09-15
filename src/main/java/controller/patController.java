package controller;


import dao.PatientInfoDao;
import entity.PatientInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class patController {
    @FXML private TextField patName;
    @FXML private TextField patGender;
    @FXML private TextField patAge;
    @FXML private TextField patId;
    @FXML private TextField patDeposit;
    @FXML private TextField patDate;
    @FXML private TextField patTel;
    @FXML private Label enterNewPassword;
    @FXML private Label confirmNewPassword;
    @FXML private PasswordField newPassword;
    @FXML private PasswordField conNewPassword;
    @FXML private Button ConfirmChange;
    @FXML private Button changeInfo;
    //患者基本信息界面响应函数
    public void Init(){
        PatientInfo patientinfo = PatientInfoDao.patInfo;
        patName.setText(patientinfo.getPatName());
        patGender.setText(patientinfo.getPatGender());
        patAge.setText(String.valueOf(patientinfo.getPatAge()));
        patId.setText(String.valueOf(patientinfo.getPatId()));
        patDeposit.setText(String.valueOf(patientinfo.getPatDeposit()));
        patDate.setText(patientinfo.getPatDate());
        patTel.setText(patientinfo.getPatTel());
        enterNewPassword.setVisible(false);
        confirmNewPassword.setVisible(false);
        newPassword.setVisible(false);
        conNewPassword.setVisible(false);
        ConfirmChange.setVisible(false);
    }
    //返回响应函数
    @FXML
    public void infoRet(MouseEvent mouseEvent) {
        try {
            Stage patStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/ui/patOptionFXML.fxml"));
            patStage.setTitle("患者选择界面");
            patStage.setScene(new Scene(root, 600, 400));
            patStage.show();
            ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //修改信息响应函数
    @FXML
    public void patChangeInfo(MouseEvent mouseEvent) {
        patName.setEditable(true);
        patGender.setEditable(true);
        patAge.setEditable(true);
        patTel.setEditable(true);
        enterNewPassword.setVisible(true);
        confirmNewPassword.setVisible(true);
        newPassword.setVisible(true);
        conNewPassword.setVisible(true);
        newPassword.setEditable(true);
        conNewPassword.setEditable(true);
        ConfirmChange.setVisible(true);
        changeInfo.setVisible(false);
        newPassword.clear();
        conNewPassword.clear();
    }
    //确认修改响应函数
    @FXML
    public void patConfirm(MouseEvent mouseEvent) {
        if ((newPassword.getText().isEmpty() && conNewPassword.getText().isEmpty()) || newPassword.getText().equals(conNewPassword.getText()))
        {
            PatientInfoDao patientdao = new PatientInfoDao();
            PatientInfo patientinfo = new PatientInfo();
            patientinfo.setPatId(PatientInfoDao.patInfo.getPatId());
            patientinfo.setPatName(patName.getText());
            patientinfo.setPatGender(patGender.getText());
            patientinfo.setPatAge(Integer.parseInt(patAge.getText()));
            patientinfo.setPatTel(patTel.getText());
            if (newPassword.getText().isEmpty())
            patientinfo.setPatPwd(PatientInfoDao.patInfo.getPatPwd());
            else patientinfo.setPatPwd(newPassword.getText());
            int  success = patientdao.updatePatInfo(patientinfo);
            if (success > 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("成功");
                alert.setHeaderText("修改成功");
                alert.setContentText("恭喜您，信息修改成功！");
                alert.show();
                PatientInfoDao.patInfo.setPatName(patientinfo.getPatName());
                PatientInfoDao.patInfo.setPatGender(patientinfo.getPatGender());
                PatientInfoDao.patInfo.setPatAge(patientinfo.getPatAge());
                PatientInfoDao.patInfo.setPatTel(patientinfo.getPatTel());
                patName.setEditable(false);
                patGender.setEditable(false);
                patAge.setEditable(false);
                patTel.setEditable(false);
                enterNewPassword.setVisible(false);
                confirmNewPassword.setVisible(false);
                newPassword.setVisible(false);
                conNewPassword.setVisible(false);
                ConfirmChange.setVisible(false);
                changeInfo.setVisible(true);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("失败");
                alert.setHeaderText("修改失败");
                alert.setContentText("请输入正确的信息后重新提交！");
                alert.show();
            }
        }
        else if (!(newPassword.getText().equals(conNewPassword.getText())))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("失败");
            alert.setHeaderText("修改失败");
            alert.setContentText("两次输入的密码不相同，请重新输入！");
            newPassword.clear();
            conNewPassword.clear();
            alert.show();
        }
    }
}
