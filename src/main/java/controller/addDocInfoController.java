package controller;

import dao.DoctorinfoDao;
import entity.Data;
import entity.Doctorinfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class addDocInfoController {
    public @FXML Spinner<Integer> doc_age;
    public @FXML ComboBox<String> doc_gender;
    public @FXML ComboBox<String> doc_rank;
    public @FXML ComboBox<String> doc_department;
    @FXML ComboBox<String> on_duty;
    @FXML ComboBox<String> off_duty;

    @FXML TextField doc_name;
    @FXML TextField doc_tel;
    public static Doctorinfo doc;

    public void init()
    {
        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,120);
        this.doc_age.setValueFactory(svf);
        this.doc_gender.getItems().addAll("男","女");
        this.doc_rank.getItems().addAll("住院医师","主治医师","副主任医师","主任医师");
        this.on_duty.getItems().addAll("7:00 am","7:30 am","8:00 am","8:30 am","9:00 am");
        this.off_duty.getItems().addAll("16:00 pm","16:30 pm","17:00 pm","17:30 pm","18:00 pm");

        ObservableList<String> department = FXCollections.observableArrayList();
        ResultSet rs = DoctorinfoDao.docDepartmentFillTable();
        try {
            while (rs.next())
            {
                department.add(rs.getString(2));
            }
            doc_department.setItems(department);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void OnCancelDocAdd(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void OnOkDocAdd(ActionEvent actionEvent) {
        if (!doc_name.getText().isEmpty() && !doc_tel.getText().isEmpty() && !doc_department.getValue().isEmpty() && !doc_gender.getValue().isEmpty() && !doc_rank.getValue().isEmpty() && !off_duty.getValue().isEmpty() && !off_duty.getValue().isEmpty() )
        {
            doc = new Doctorinfo(doc_name.getText(),doc_gender.getValue(),doc_age.getValue(),"0000",doc_department.getValue(),doc_rank.getValue(),doc_tel.getText(),on_duty.getValue(),off_duty.getValue());
            DoctorinfoDao doctorinfoDao = new DoctorinfoDao();
            if (doctorinfoDao.docSearchNoRepeat(doc))
            {
                doctorinfoDao.addDoctor(doc);
                Alert alert_3 = new Alert(Alert.AlertType.INFORMATION,"添加成功！");
                alert_3.show();
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
            else
            {
                Alert alert_2 = new Alert(Alert.AlertType.WARNING,"该医生已存在！");
                alert_2.show();
            }
        }
        else {
            Alert alert_1 = new Alert(Alert.AlertType.WARNING,"请补充医生信息！");
            alert_1.show();
        }
    }
}
