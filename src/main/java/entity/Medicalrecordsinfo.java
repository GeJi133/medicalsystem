package entity;

public class Medicalrecordsinfo {
    private int recordId;
    private String createDate;
    private String curDepartment;
    private String curDoctor;
    private String patDemands;
    private int patId1;
    private String Result;
    private double patConsumption;
    private String isInHospital;
    private String inDate;
    private String outDate;
    private String patName;
    private String roomId;
    private String bedId;

    public Medicalrecordsinfo(){}
    public Medicalrecordsinfo(int recordId,String createDate,String curDepartment,String curDoctor,String patDemands,int patId1,String Result,double patConsumption,String isInHospital,String inDate,String offDate){
        this.recordId=recordId;
        this.createDate=createDate;
        this.curDepartment=curDepartment;
        this.curDoctor=curDoctor;
        this.patDemands=patDemands;
        this.patId1=patId1;
        this.Result=Result;
        this.patConsumption=patConsumption;
        this.isInHospital=isInHospital;
        this.inDate=inDate;
        this.outDate=offDate;
    }
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCurDepartment() {
        return curDepartment;
    }

    public void setCurDepartment(String curDepartment) {
        this.curDepartment = curDepartment;
    }

    public String getCurDoctor() {
        return curDoctor;
    }

    public void setCurDoctor(String curDoctor) {
        this.curDoctor = curDoctor;
    }

    public String getPatDemands() {
        return patDemands;
    }

    public void setPatDemands(String patDemands) {
        this.patDemands = patDemands;
    }

    public int getPatId1() {
        return patId1;
    }

    public void setPatId1(int patId1) {
        this.patId1 = patId1;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public double getPatConsumption() {
        return patConsumption;
    }

    public void setPatConsumption(double patConsumption) {
        this.patConsumption = patConsumption;
    }

    public String getIsInHospital() {
        return isInHospital;
    }

    public void setIsInHospital(String isInHospital) {
        this.isInHospital = isInHospital;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
    }

}
