package controller;

import dao.AppointHistoryDao;
import dao.PatientInfoDao;
import entity.AppointHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class patAppoinmentController {
    @FXML ComboBox department;
    @FXML ComboBox doctor;
    @FXML TextField patId4;

    //刷新医生列表
    @FXML
    public void refreshDoc(ActionEvent actionEvent) {
        String dep = ((String) department.getValue()).trim();
        System.out.println(dep);
        PatientInfoDao patientdao = new PatientInfoDao();
        List<String> doc = patientdao.selectDepDoc(dep);
        ObservableList<String> oList = FXCollections.observableList(doc);
        try {
            doctor.setItems(oList);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }
    //返回按钮响应函数
    @FXML
    public void appRet(MouseEvent mouseEvent) {
        try {
            Stage patStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/ui/patOptionFXML.fxml"));
            patStage.setTitle("患者选择界面");
            patStage.setScene(new Scene(root, 1080, 720));
            patStage.show();
            ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Confirm(MouseEvent mouseEvent) {
        AppointHistory appointHistory = new AppointHistory();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        appointHistory.setAppDate(df.format(new Date()));
        appointHistory.setAppDec(((String) department.getValue()).trim());
        appointHistory.setAppDoc(((String)doctor.getValue()).trim());
        appointHistory.setAppMoney(20);
        appointHistory.setPatId2(Integer.parseInt(patId4.getText()));
        AppointHistoryDao appointHistoryDao = new AppointHistoryDao();
        appointHistoryDao.insertAppoint(appointHistory);
        PatientInfoDao patientinfodao = new PatientInfoDao();
        patientinfodao.chargeAppoinment();
        double deposit = PatientInfoDao.patInfo.getPatDeposit();
        DecimalFormat dcmFmt = new DecimalFormat("0.00");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("成功");
        alert.setHeaderText("挂号成功！");
        alert.setContentText("恭喜您，挂号成功!您的挂号费为20元，目前卡内余额为" + dcmFmt.format(deposit) + "元");
        alert.show();
    }
}
