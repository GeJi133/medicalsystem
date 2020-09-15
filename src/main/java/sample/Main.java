package sample;

import controller.adminManageController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/ui/MainLoginFXML.fxml"));
        Scene scene = new Scene(root,900,550);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(
                getClass().getResource("/ui/MainDocStyle.css").toExternalForm());
        primaryStage.setTitle("医疗管理系统");
        primaryStage.show();
    }


public void close(ActionEvent event) {
    ((Node) (event.getSource())).getScene().getWindow().hide();
}
    public static void main(String[] args) {
        launch(args);
    }
}
