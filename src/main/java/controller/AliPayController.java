package controller;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import sample.Main;
import sample.testHtml;


public class AliPayController  implements Initializable {


    @FXML
    private WebView webView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

        final WebEngine webengine = webView.getEngine();
        String url = Main.class.getResource("/ui/alipay.html").toExternalForm();
        webengine.load(url);
    }

    public void backToRecharge(ActionEvent actionEvent) {
        try {
            Stage patStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/ui/patOptionFXML.fxml"));
            patStage.setTitle("患者选择界面");
            patStage.setScene(new Scene (root, 1080, 720));
            patStage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
