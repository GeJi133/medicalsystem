package controller;

import dao.RechargeDao;
import entity.Recharge;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.HttpTest;

import java.io.IOException;

public class WaitForResponse extends Thread {
    private  Recharge recharge;
    WaitForResponse(Recharge recharge){
        this.recharge=recharge;
    }

    public void run()  {
        System.out.println ("waitingforresponse");

        try {
            HttpTest.waitForResponse ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        RechargeDao.changeDeposit (recharge);
        RechargeDao.newRecharge (recharge);

        try {
            Stage patStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/ui/patRechargeFXML.fxml"));
            patStage.setTitle("患者选择界面");
            patStage.setScene(new Scene (root, 1080, 720));
            patStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
