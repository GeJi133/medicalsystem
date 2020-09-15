package entity;

public class PatientInfo {
    private int patId;
    private String patName;
    private String patGender;
    private int patAge;
    private String patPwd;
    private float patDeposit;
    private String patDate;
    private String patTel;

    public PatientInfo(){}

    public PatientInfo(String patName,String patGender,int patAge,String patPwd,float patDeposit,String patDate,String patTel){
        this.patName=patName;
        this.patGender=patGender;
        this.patAge=patAge;
        this.patPwd=patPwd;
        this.patDeposit=patDeposit;
        this.patDate=patDate;
        this.patTel=patTel;
    }

    public PatientInfo(int patId,String patName,String patGender,int patAge,String patPwd,float patDeposit,String patDate,String patTel){
        this.patId=patId;
        this.patName=patName;
        this.patGender=patGender;
        this.patAge=patAge;
        this.patPwd=patPwd;
        this.patDeposit=patDeposit;
        this.patDate=patDate;
        this.patTel=patTel;
    }

    public int getPatId() {
        return patId;
    }

    public void setPatId(int patId) {
        this.patId = patId;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getPatGender() {
        return patGender;
    }

    public void setPatGender(String patGender) {
        this.patGender = patGender;
    }

    public int getPatAge() {
        return patAge;
    }

    public void setPatAge(int patAge) {
        this.patAge = patAge;
    }

    public String getPatPwd() {
        return patPwd;
    }

    public void setPatPwd(String patPwd) {
        this.patPwd = patPwd;
    }

    public float getPatDeposit() {
        return patDeposit;
    }

    public void setPatDeposit(float patDeposit) {
        this.patDeposit = patDeposit;
    }

    public String getPatDate() {
        return patDate;
    }

    public void setPatDate(String patDate) {
        this.patDate = patDate;
    }

    public String getPatTel() {
        return patTel;
    }

    public void setPatTel(String patTel) {
        this.patTel = patTel;
    }

}
