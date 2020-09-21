package controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;


public class giveAdviceController {

    public void exit(ActionEvent actionEvent)
    {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void submit(ActionEvent actionEvent)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"提交成功，感谢您的反馈！");
        alert.show();
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

}
