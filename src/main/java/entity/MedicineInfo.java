package entity;

public class MedicineInfo {

    private int medNumber;
    private String medName;
    private double medPrice;
    private String medCategory;
    private int medStore;
    private  int medAmount;

    public int getMedAmount() {
        return medAmount;
    }

    public void setMedAmount(int medAmount) {
        this.medAmount = medAmount;
    }

    public MedicineInfo(){}

    public MedicineInfo(int medNumber,String medName,double medPrice,String medCategory,int medStore){
        this.medNumber=medNumber;
        this.medName=medName;
        this.medPrice=medPrice;
        this.medCategory=medCategory;
        this.medStore=medStore;
    }

    public MedicineInfo(String medName,double medPrice,String medCategory,int medStore){
        this.medName=medName;
        this.medPrice=medPrice;
        this.medCategory=medCategory;
        this.medStore=medStore;
    }

    public int getMedNumber() {
        return medNumber;
    }

    public void setMedNumber(int medNumber) {
        this.medNumber = medNumber;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public double getMedPrice() {
        return medPrice;
    }

    public void setMedPrice(double medPrice) {
        this.medPrice = medPrice;
    }

    public String getMedCategory() {
        return medCategory;
    }

    public void setMedCategory(String medCategory) {
        this.medCategory = medCategory;
    }

    public int getMedStore() {
        return medStore;
    }

    public void setMedStore(int medStore) {
        this.medStore = medStore;
    }


}
