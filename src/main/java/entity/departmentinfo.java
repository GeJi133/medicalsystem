package entity;

public class departmentinfo {

    private int depId;
    private String depName;
    private int depDoctorNumber;
    private int depBedNumber;

    public departmentinfo(){}

    public  departmentinfo(int depId,String depName,int depDoctorNumber,int depBedNumber){
        this.depId=depId;
        this.depName=depName;
        this.depBedNumber=depBedNumber;
        this.depDoctorNumber=depDoctorNumber;
    }
    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public int getDepDoctorNumber() {
        return depDoctorNumber;
    }

    public void setDepDoctorNumber(int depDoctorNumber) {
        this.depDoctorNumber = depDoctorNumber;
    }

    public int getDepBedNumber() {
        return depBedNumber;
    }

    public void setDepBedNumber(int depBedNumber) {
        this.depBedNumber = depBedNumber;
    }

}
