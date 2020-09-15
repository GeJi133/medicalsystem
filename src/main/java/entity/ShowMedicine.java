package entity;

import javafx.scene.control.Label;

public class ShowMedicine {

    private Label medName;
    private Label useWay;
    private Label amount;
    private Label price;

    public Label getMedName() {
        return medName;
    }

    public void setMedName(Label medName) {
        this.medName = medName;
    }

    public Label getUseWay() {
        return useWay;
    }

    public void setUseWay(Label useWay) {
        this.useWay = useWay;
    }

    public Label getAmount() {
        return amount;
    }

    public void setAmount(Label amount) {
        this.amount = amount;
    }

    public Label getPrice() {
        return price;
    }

    public void setPrice(Label price) {
        this.price = price;
    }

}
