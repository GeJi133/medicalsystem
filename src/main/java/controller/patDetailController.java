package controller;

import dao.PatientInfoDao;
import entity.Medicalrecordsinfo;
import entity.MedicineInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.List;

public class patDetailController {
    @FXML private TableView recordTable;
    @FXML private TableView<Medicalrecordsinfo> payTable;
    @FXML private TableColumn payDateCol;
    @FXML private TableColumn payAmountCol;
    @FXML private TableColumn dateCol;
    @FXML private TableColumn depCol;
    @FXML private TableColumn docCol;
    @FXML private TableColumn rsCol;
    //初始化详细信息界面
    public void Init(){
        PatientInfoDao patientdao = new PatientInfoDao();
        dateCol.setCellValueFactory(new PropertyValueFactory<Medicalrecordsinfo,String>("createDate"));
        depCol.setCellValueFactory(new PropertyValueFactory<Medicalrecordsinfo,String>("curDepartment"));
        docCol.setCellValueFactory(new PropertyValueFactory<Medicalrecordsinfo,String>("curDoctor"));
        rsCol.setCellValueFactory(new PropertyValueFactory<Medicalrecordsinfo,String>("result"));
        List<Medicalrecordsinfo> list1 = patientdao.selectDiagnosisRecords(PatientInfoDao.patInfo.getPatId());
        ObservableList<Medicalrecordsinfo> oList1 = FXCollections.observableList(list1);
        recordTable.setItems(oList1);
        payDateCol.setCellValueFactory( new PropertyValueFactory<MedicineInfo,String>("createDate"));
        payAmountCol.setCellValueFactory(new PropertyValueFactory<Medicalrecordsinfo,Double>("patConsumption"));
        List<Medicalrecordsinfo> list2 = patientdao.selectPaymentRecords(PatientInfoDao.patInfo.getPatId());
        ObservableList<Medicalrecordsinfo> oList2 = FXCollections.observableList(list2);
        payTable.setItems(oList2);
    }
    //返回响应函数
    @FXML
    public void DetailRet(MouseEvent mouseEvent) {
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

    public void printMedicalRecords(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("成功");
        alert.setHeaderText("成功！");
        alert.setContentText("打印成功！");
        alert.show();
    }

    public void printPaymentRecords(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("成功");
        alert.setHeaderText("成功！");
        alert.setContentText("打印成功！");
        alert.show();
    }
}
