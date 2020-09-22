package controller;

import dao.AppointHistoryDao;
import dao.PatientInfoDao;
import entity.AppointHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.text.DecimalFormat;
import java.util.List;

public class patAppoinmentController {
    @FXML DatePicker appointDate;
    @FXML ComboBox department;
    @FXML ComboBox doctor;
    @FXML ComboBox appointTime;
    //刷新医生列表
    public patAppoinmentController(){
        initUI ();
    }

    private void initUI() {
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(vbox, 400, 400);
        String pattern = "yyyy-MM-dd";
//        stage.setScene(scene);
        appointDate = new DatePicker();
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern(pattern);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        appointDate.setConverter(converter);
        appointDate.setPromptText(pattern.toLowerCase());
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Label checkInlabel = new Label("Check-In Date:");
        gridPane.add(checkInlabel, 0, 0);
        GridPane.setHalignment(checkInlabel, HPos.LEFT);
        gridPane.add(appointDate, 0, 1);
        vbox.getChildren().add(gridPane);
        appointDate.requestFocus();
    }

    @FXML
    public void refreshDoc(ActionEvent actionEvent) {
        String dep = ((String) department.getValue()).trim();
        System.out.println(dep);
        PatientInfoDao patientdao = new PatientInfoDao();
        List<String> doc = patientdao.selectDepDoc(dep);
        List<String> time = new ArrayList<> ();
        for (int i=8;i<10;i++){
            time.add ("0"+String.valueOf (i)+":00:00");
            time.add ("0"+String.valueOf (i)+":30:00");
        }
        for (int i=10;i<11;i++){
            time.add ("0"+String.valueOf (i)+":00:00");
            time.add ("0"+String.valueOf (i)+":30:00");
        }
        for (int i=14;i<16;i++){
            time.add ("0"+String.valueOf (i)+":00:00");
            time.add ("0"+String.valueOf (i)+":30:00");
        }
        ObservableList<String> oList = FXCollections.observableList(doc);
        ObservableList<String> timeList = FXCollections.observableList(time);

        try {
            doctor.setItems(oList);
            appointTime.setItems (timeList);
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
        AppointHistory appointHistory=new AppointHistory ();

        appointHistory.setAppDate (String.valueOf (new Date ()));
        appointHistory.setAppDec (String.valueOf (department.getValue ()));
        appointHistory.setAppDoc (String.valueOf (doctor.getValue ()));
        appointHistory.setAppMoney (20);
        appointHistory.setPatId2 (PatientInfoDao.patInfo.getPatId ());
        appointHistory.setAppointTime (String.valueOf (appointTime.getValue ()));
        System.out.println ("time"+appointDate. getValue ());
        appointHistory.setAppointDate (String.valueOf (appointDate. getValue ()));

        AppointHistoryDao appointHistoryDao=new AppointHistoryDao ();
        appointHistoryDao.insertAppoint (appointHistory);
//        PatientInfoDao patientinfodao = new PatientInfoDao();
//        patientinfodao.chargeAppoinment();
        double deposit = PatientInfoDao.patInfo.getPatDeposit();
        DecimalFormat dcmFmt = new DecimalFormat("0.00");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("成功");
        alert.setHeaderText("挂号成功！");
        alert.setContentText("恭喜您，挂号成功!您的挂号费为20元，目前卡内余额为" + dcmFmt.format(deposit) + "元");
        alert.show();
    }
}
