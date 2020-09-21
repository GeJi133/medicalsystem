package controller;

import dao.MedicineDao;
import entity.MedicineInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;


public class addMedInfoController {
    public static MedicineInfo med;

    @FXML TextField med_name;
    @FXML TextField med_price;
    @FXML Spinner<Integer> med_store;
    @FXML ComboBox<String> med_sort;

    public void CancelAdd(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void OkAdd(ActionEvent actionEvent) {
        if (!med_name.getText().isEmpty() && !med_price.getText().isEmpty() && !med_sort.getSelectionModel().isEmpty()){
            med = new MedicineInfo(med_name.getText(),Double.parseDouble(med_price.getText()),med_sort.getValue(),med_store.getValue());
            if (MedicineDao.medSearchNoRepeat(med))
            {
                MedicineDao.addMedicine(med);
                Alert alert_3 = new Alert(Alert.AlertType.INFORMATION,"添加成功！");
                alert_3.show();
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
            else {
                Alert alert_2 = new Alert(Alert.AlertType.WARNING,"该药品已存在！");
                alert_2.show();
            }
        }
        else {
            Alert alert_1 = new Alert(Alert.AlertType.WARNING,"请补全药品信息！");
            alert_1.show();
        }
    }
}
