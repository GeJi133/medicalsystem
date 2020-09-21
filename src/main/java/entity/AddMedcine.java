package entity;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AddMedcine {
    public ComboBox getMedName() {
        return medName;
    }

    public void setMedName(ComboBox medName) {
        this.medName = medName;
    }

    public TextField getUseWay() {
        return useWay;
    }

    public void setUseWay(TextField useWay) {
        this.useWay = useWay;
    }

    public ComboBox getAmount() {
        return amount;
    }

    public void setAmount(ComboBox amount) {
        this.amount = amount;
    }

    public Label getPrice() {
        return price;
    }

    public void setPrice(Label price) {
        this.price = price;
    }

    public ComboBox getDosage() {
        return dosage;
    }

    public void setDosage(ComboBox dosage) {
        this.dosage = dosage;
    }

    public ComboBox getFrequency() {
        return frequency;
    }

    public void setFrequency(ComboBox frequency) {
        this.frequency = frequency;
    }


    private ComboBox medName;
    private TextField useWay;
    private ComboBox amount;
    private Label price;
    private ComboBox dosage;
    private ComboBox frequency;

}
