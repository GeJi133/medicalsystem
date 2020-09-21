package entity;

public class MedicineList {
    private int medicineListNumber;
    private int medicalRecordsNumber;
    private int medicineNumber;
    private int medicineAmountl;
    private String medicineUsage;
    private int medicineDosage;
    private int medicineFrequency;

    public MedicineList(){}

    public MedicineList(int medicineListNumber,int medicalRecordsNumber,int medicineNumber,int medicineAmountl,String medicineUsage){
        this.medicineListNumber=medicineListNumber;
        this.medicalRecordsNumber=medicalRecordsNumber;
        this.medicineNumber=medicineNumber;
        this.medicineAmountl=medicineAmountl;
        this.medicineUsage=medicineUsage;
    }

    public int getMedicineDosage() {
        return medicineDosage;
    }

    public void setMedicineDosage(int medicineDosage) {
        this.medicineDosage = medicineDosage;
    }

    public int getMedicineFrequency() {
        return medicineFrequency;
    }

    public void setMedicineFrequency(int medicineFrequency) {
        this.medicineFrequency = medicineFrequency;
    }

    public int getMedicineListNumber() {
        return medicineListNumber;
    }

    public void setMedicineListNumber(int medicineListNumber) {
        this.medicineListNumber = medicineListNumber;
    }

    public int getMedicalRecordsNumber() {
        return medicalRecordsNumber;
    }

    public void setMedicalRecordsNumber(int medicalRecordsNumber) {
        this.medicalRecordsNumber = medicalRecordsNumber;
    }

    public int getMedicineNumber() {
        return medicineNumber;
    }

    public void setMedicineNumber(int medicineNumber) {
        this.medicineNumber = medicineNumber;
    }

    public int getMedicineAmountl() {
        return medicineAmountl;
    }

    public void setMedicineAmountl(int medicineAmountl) {
        this.medicineAmountl = medicineAmountl;
    }

    public String getMedicineUsage() {
        return medicineUsage;
    }

    public void setMedicineUsage(String medicineUsage) {
        this.medicineUsage = medicineUsage;
    }


}
