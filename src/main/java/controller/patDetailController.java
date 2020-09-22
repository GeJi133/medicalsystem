package controller;

import dao.PatientInfoDao;

import entity.AppointHistory;

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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;

public class patDetailController {
    @FXML private TableView recordTable;
    @FXML private TableView<Medicalrecordsinfo> payTable;

    @FXML private TableView appTable;

    @FXML private TableColumn payDateCol;
    @FXML private TableColumn payAmountCol;
    @FXML private TableColumn dateCol;
    @FXML private TableColumn depCol;
    @FXML private TableColumn docCol;
    @FXML private TableColumn rsCol;

    @FXML private TableColumn appointDate;
    @FXML private TableColumn appointDec;
    @FXML private TableColumn appointDoc;
    @FXML private TableColumn appointMoney;

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

        appointDate.setCellValueFactory(new PropertyValueFactory<AppointHistory,String>("appDate"));
        appointDec.setCellValueFactory(new PropertyValueFactory<AppointHistory,String>("appDec"));
        appointDoc.setCellValueFactory(new PropertyValueFactory<AppointHistory,String>("appDoc"));
        appointMoney.setCellValueFactory(new PropertyValueFactory<AppointHistory,String>("appMoney"));
        List<AppointHistory> list3 = patientdao.selectAppointHistory(PatientInfoDao.patInfo.getPatId());
        ObservableList<AppointHistory> olist3 = FXCollections.observableList(list3);
        appTable.setItems(olist3);

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


    public void printMedicalRecords(MouseEvent mouseEvent) throws IOException {
        HSSFWorkbook work = new HSSFWorkbook();
        HSSFSheet sheet = work.createSheet("就诊记录表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("日期");
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("科室");
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("医生");
        HSSFCell cell3 = row.createCell(3);
        cell3.setCellValue("诊断结果");
        PatientInfoDao patientdao = new PatientInfoDao();
        List<Medicalrecordsinfo> list1 = patientdao.selectDiagnosisRecords(PatientInfoDao.patInfo.getPatId());
        for (int i=1;i<list1.size()+1;i++){
            HSSFRow row1 = sheet.createRow(i);
            HSSFCell cell4 = row1.createCell(0);
            cell4.setCellValue(list1.get(i-1).getCreateDate());
            HSSFCell cell5 = row1.createCell(1);
            cell5.setCellValue(list1.get(i-1).getCurDepartment());
            HSSFCell cell6 = row1.createCell(2);
            cell6.setCellValue(list1.get(i-1).getCurDoctor());
            HSSFCell cell7 = row1.createCell(3);
            cell7.setCellValue(list1.get(i-1).getResult());
        }
        String str = JOptionPane.showInputDialog("请输入要存入到文件名称：");
        FileOutputStream out =new FileOutputStream("E:/"+str+".xls");
        work.write(out);
        out.flush();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("成功");
        alert.setHeaderText("成功！");
        alert.setContentText("打印成功！");
        alert.show();
    }



    public void printPaymentRecords(MouseEvent mouseEvent) throws IOException {
        HSSFWorkbook work = new HSSFWorkbook();
        HSSFSheet sheet = work.createSheet("消费记录表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("日期");
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("消费金额");
        PatientInfoDao patientdao = new PatientInfoDao();
        List<Medicalrecordsinfo> list2 = patientdao.selectPaymentRecords(PatientInfoDao.patInfo.getPatId());
        for (int i=1;i<list2.size()+1;i++){
            HSSFRow row1 = sheet.createRow(i);
            HSSFCell cell4 = row1.createCell(0);
            cell4.setCellValue(list2.get(i-1).getCreateDate());
            HSSFCell cell5 = row1.createCell(1);
            cell5.setCellValue(list2.get(i-1).getPatConsumption());
        }
        String str = JOptionPane.showInputDialog("请输入要存入到文件名称");
        FileOutputStream  out =new FileOutputStream("c:/"+str+".xls");
        work.write(out);
        out.flush();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("成功");
        alert.setHeaderText("成功！");
        alert.setContentText("打印成功！");
        alert.show();
    }
    public void printHistoryRecords(MouseEvent mouseEvent) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("历史预约表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("预约时间");
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("预约科室");
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("预约医生");
        HSSFCell cell3 = row.createCell(3);
        cell3.setCellValue("病人");
        PatientInfoDao patientdao = new PatientInfoDao();
        List<AppointHistory> list3 = patientdao.selectAppointHistory(PatientInfoDao.patInfo.getPatId());
        for (int i=1;i<list3.size()+1;i++){
            HSSFRow row1 = sheet.createRow(i);
            HSSFCell cell4 = row1.createCell(0);
            cell4.setCellValue(list3.get(i-1).getAppDate());
            HSSFCell cell5 = row1.createCell(1);
            cell5.setCellValue(list3.get(i-1).getAppDec());
            HSSFCell cell6 = row1.createCell(2);
            cell6.setCellValue(list3.get(i-1).getAppDoc());
            HSSFCell cell7 = row1.createCell(3);
            cell7.setCellValue(String.valueOf(list3.get(i-1).getAppMoney()));
        }
        String str = JOptionPane.showInputDialog("请输入要存入的文件名称：");
        FileOutputStream out = new FileOutputStream("c:/"+str+".xls");
        workbook.write(out);
        out.flush();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("成功");
        alert.setHeaderText("成功！");
        alert.setContentText("打印成功！");
        alert.show();
    }
}
