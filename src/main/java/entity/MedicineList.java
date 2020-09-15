package entity;

public class MedicineList {
    private int medicineListNumber;
    private int medicalRecordsNumber;
    private int medicineNumber;
    private String medicineAmountl;
    private String medicineUsage;

    public MedicineList(){}

    public MedicineList(int medicineListNumber,int medicalRecordsNumber,int medicineNumber,String medicineAmountl,String medicineUsage){
        this.medicineListNumber=medicineListNumber;
        this.medicalRecordsNumber=medicalRecordsNumber;
        this.medicineNumber=medicineNumber;
        this.medicineAmountl=medicineAmountl;
        this.medicineUsage=medicineUsage;
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

    public String getMedicineAmountl() {
        return medicineAmountl;
    }

    public void setMedicineAmountl(String medicineAmountl) {
        this.medicineAmountl = medicineAmountl;
    }

    public String getMedicineUsage() {
        return medicineUsage;
    }

    public void setMedicineUsage(String medicineUsage) {
        this.medicineUsage = medicineUsage;
    }


}
