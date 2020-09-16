package entity;

public class Doctorinfo {
    private int docId;
    private String docName;
    private String docGender;
    private int docAge;
    private String docPwd="123456";
    private String docDepartment;
    private String docRank;
    private String docTel;
    private String onDuty;
    private String offDuty;
    private String phonenumber;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Doctorinfo(){}
    public Doctorinfo(int id,String docName,String docGender,int docAge,String docPwd,String docDepartment,String docRank,String docTel,String onDuty,String offDuty){
        this.docId =id;
        this.docName=docName;
        this.docGender=docGender;
        this.docAge=docAge;
        this.docPwd=docPwd;
        this.docDepartment=docDepartment;
        this.docRank=docRank;
        this.docTel=docTel;
        this.onDuty=onDuty;
        this.offDuty=offDuty;
    }

    public Doctorinfo(String docName,String docGender,int docAge,String docPwd,String docDepartment,String docRank,String docTel,String onDuty,String offDuty){
        this.docName=docName;
        this.docGender=docGender;
        this.docAge=docAge;
        this.docPwd=docPwd;
        this.docDepartment=docDepartment;
        this.docRank=docRank;
        this.docTel=docTel;
        this.onDuty=onDuty;
        this.offDuty=offDuty;
    }
    public void setDocId(int docId){this.docId = docId;}

    public int getDocId(){ return docId; }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocGender() {
        return docGender;
    }

    public void setDocGender(String docGender) {
        this.docGender = docGender;
    }

    public int getDocAge() {
        return docAge;
    }

    public void setDocAge(int docAge) {
        this.docAge = docAge;
    }

    public String getDocPwd() {
        return docPwd;
    }

    public void setDocPwd(String docPwd) {
        this.docPwd = docPwd;
    }

    public String getDocDepartment() {
        return docDepartment;
    }

    public void setDocDepartment(String docDepartment) {
        this.docDepartment = docDepartment;
    }

    public String getDocRank() {
        return docRank;
    }

    public void setDocRank(String docRank) {
        this.docRank = docRank;
    }

    public String getDocTel() {
        return docTel;
    }

    public void setDocTel(String docTel) {
        this.docTel = docTel;
    }

    public String getOnDuty() {
        return onDuty;
    }

    public void setOnDuty(String onDuty) {
        this.onDuty = onDuty;
    }

    public String getOffDuty() {
        return offDuty;
    }

    public void setOffDuty(String offDuty) {
        this.offDuty = offDuty;
    }


}
