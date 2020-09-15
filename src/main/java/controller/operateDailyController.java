package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;

public class operateDailyController {

    @FXML private ListView<String> list;

    public void OnOK(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    void init(int account, String time){
        ObservableList<String> strList = FXCollections.observableArrayList();
        strList.add("管理员 "+ String.valueOf(account) + " 在 " + time + " 登录系统");
        list.setItems(strList);
    }
}
