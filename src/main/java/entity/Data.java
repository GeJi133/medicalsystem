package entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Data {

    public final class Admin{
        private final SimpleIntegerProperty adminAccount = new SimpleIntegerProperty();
        private final SimpleStringProperty adminPassword = new SimpleStringProperty();

        public Admin(){}
        public int getAdminAccount() {
            return adminAccount.get();
        }

        public void setAdminAccount(int account){
            this.adminAccount.set(account);
        }

        public SimpleIntegerProperty adminAccountProperty(){
            return adminAccount;
        }

        public String getAdminPassword() {
            return adminPassword.get();
        }
        public void setAdminPassword(String password){
            this.adminPassword.set(password);
        }

        public SimpleStringProperty adminPasswordProperty(){
            return adminPassword;
        }

        public void setAll(Admininfo admin)
        {
            this.setAdminAccount(admin.getAdminAccount());
            this.setAdminPassword(admin.getAdminPassword());
        }
    }

    public final class Doctor{
        private final SimpleIntegerProperty docId = new SimpleIntegerProperty();
        private final SimpleStringProperty docName = new SimpleStringProperty();
        private final SimpleStringProperty docGender = new SimpleStringProperty();
        private final SimpleIntegerProperty docAge = new SimpleIntegerProperty();
        private final SimpleStringProperty docPwd = new SimpleStringProperty();
        private final SimpleStringProperty docDepartment = new SimpleStringProperty();
        private final SimpleStringProperty docRank = new SimpleStringProperty();
        private final SimpleStringProperty docTel = new SimpleStringProperty();
        private final SimpleStringProperty onDuty = new SimpleStringProperty();
        private final SimpleStringProperty offDuty = new SimpleStringProperty();

        public int getDocId() {
            return docId.get();
        }

        public SimpleIntegerProperty docIdProperty() {
            return docId;
        }

        public SimpleIntegerProperty docAgeProperty() {
            return docAge;
        }

        public SimpleStringProperty docDepartmentProperty() {
            return docDepartment;
        }

        public SimpleStringProperty docGenderProperty() {
            return docGender;
        }

        public int getDocAge() {
            return docAge.get();
        }

        public SimpleStringProperty docNameProperty() {
            return docName;
        }

        public SimpleStringProperty docPwdProperty() {
            return docPwd;
        }

        public SimpleStringProperty docRankProperty() {
            return docRank;
        }

        public SimpleStringProperty docTelProperty() {
            return docTel;
        }

        public SimpleStringProperty offDutyProperty() {
            return offDuty;
        }

        public SimpleStringProperty onDutyProperty() {
            return onDuty;
        }

        public String getDocDepartment() {
            return docDepartment.get();
        }

        public String getDocGender() {
            return docGender.get();
        }

        public String getDocName() {
            return docName.get();
        }

        public String getDocPwd() {
            return docPwd.get();
        }

        public String getDocRank() {
            return docRank.get();
        }

        public String getDocTel() {
            return docTel.get();
        }

        public String getOffDuty() {
            return offDuty.get();
        }

        public String getOnDuty() {
            return onDuty.get();
        }

        public void setDocId(int id){this.docId.set(id);}
        public void setDocName(String name){this.docName.set(name);}
        public void setDocGender(String gender){this.docGender.set(gender);}
        public void setDocAge(int age){this.docAge.set(age);}
        public void setDocPwd(String pwd){this.docPwd.set(pwd);}
        public void setDocDepartment(String department){this.docDepartment.set(department);}
        public void setDocRank(String rank){this.docRank.set(rank);}
        public void setDocTel(String tel){this.docTel.set(tel);}
        public void setOnDuty(String onDuty){this.onDuty.set(onDuty);}
        public void setOffDuty(String offDuty){this.offDuty.set(offDuty);}

        public void setAll(Doctorinfo doctorinfo)
        {
            this.setDocName(doctorinfo.getDocName());
            this.setDocAge(doctorinfo.getDocAge());
            this.setDocDepartment(doctorinfo.getDocDepartment());
            this.setDocGender(doctorinfo.getDocGender());
            this.setDocId(doctorinfo.getDocId());
            this.setDocPwd(doctorinfo.getDocPwd());
            this.setDocRank(doctorinfo.getDocRank());
            this.setDocTel(doctorinfo.getDocTel());
            this.setOffDuty(doctorinfo.getOffDuty());
            this.setOnDuty(doctorinfo.getOnDuty());
        }
    }

    public final class Patient{

        private final SimpleIntegerProperty patId = new SimpleIntegerProperty();
        private final SimpleStringProperty patName = new SimpleStringProperty();
        private final SimpleStringProperty patGender = new SimpleStringProperty();
        private final SimpleIntegerProperty patAge = new SimpleIntegerProperty();
        private final SimpleStringProperty patPwd = new SimpleStringProperty();
        private final SimpleFloatProperty patDeposit = new SimpleFloatProperty();
        private final SimpleStringProperty patDate = new SimpleStringProperty();
        private final SimpleStringProperty patTel = new SimpleStringProperty();

        public SimpleIntegerProperty patIdProperty() {
            return patId;
        }

        public SimpleStringProperty patGenderProperty() {
            return patGender;
        }

        public SimpleStringProperty patNameProperty() {
            return patName;
        }

        public SimpleFloatProperty patDepositProperty() {
            return patDeposit;
        }

        public SimpleIntegerProperty patAgeProperty() {
            return patAge;
        }

        public SimpleStringProperty patDateProperty() {
            return patDate;
        }

        public SimpleStringProperty patPwdProperty() {
            return patPwd;
        }

        public SimpleStringProperty patTelProperty() {
            return patTel;
        }

        public int getPatAge() {
            return patAge.get();
        }

        public int getPatId() {
            return patId.get();
        }

        public String getPatName() {
            return patName.get();
        }

        public float getPatDeposit() {
            return patDeposit.get();
        }

        public String getPatDate() {
            return patDate.get();
        }

        public String getPatPwd() {
            return patPwd.get();
        }

        public String getPatTel() {
            return patTel.get();
        }

        public void setPatId(int id){this.patId.set(id);}
        public void setPatName(String name){this.patName.set(name);}
        public void setPatGender(String gender){this.patGender.set(gender);}
        public void setPatAge(int age){this.patAge.set(age);}
        public void setPatPwd(String pwd){this.patPwd.set(pwd);}
        public void setPatDeposit(float deposit){this.patDeposit.set(deposit);}
        public void setPatDate(String date){this.patDate.set(date);}
        public void setPatTel(String tel){this.patTel.set(tel);}

        public void setAll(PatientInfo patientInfo)
        {
            this.setPatAge(patientInfo.getPatAge());
            this.setPatDate(patientInfo.getPatDate());
            this.setPatDeposit(patientInfo.getPatDeposit());
            this.setPatGender(patientInfo.getPatGender());
            this.setPatId(patientInfo.getPatId());
            this.setPatTel(patientInfo.getPatTel());
            this.setPatPwd(patientInfo.getPatPwd());
            this.setPatName(patientInfo.getPatName());
        }
    }

    public final class MedicalRecord{
        private final SimpleIntegerProperty recordId = new SimpleIntegerProperty();
        private final SimpleStringProperty createDate = new SimpleStringProperty();
        private final SimpleStringProperty curDepartment = new SimpleStringProperty();
        private final SimpleStringProperty curDoctor = new SimpleStringProperty();
        private final SimpleStringProperty patDemands = new SimpleStringProperty();
        private final SimpleIntegerProperty patId1 = new SimpleIntegerProperty();
        private final SimpleStringProperty Result = new SimpleStringProperty();
        private final SimpleDoubleProperty patConsumption = new SimpleDoubleProperty();
        private final SimpleStringProperty isInHospital = new SimpleStringProperty();
        private final SimpleStringProperty inDate = new SimpleStringProperty();
        private final SimpleStringProperty outDate = new SimpleStringProperty();

        public void setrecordId(int id){this.recordId.set(id);}
        public void setcreateDate(String createDate){this.createDate.set(createDate);}
        public void setcurDepartment(String curDepartment){this.curDepartment.set(curDepartment);}
        public void setcurDoctor(String curDoctor){this.curDoctor.set(curDoctor);}
        public void setpatDemands(String patDemands){this.patDemands.set(patDemands);}
        public void setpatId1(int id){this.patId1.set(id);}
        public void setResult(String Result1){this.Result.set(Result1);}
        public void setpatConsumption(double deposit){this.patConsumption.set(deposit);}
        public void setisInHospital(String isInHospital){this.isInHospital.set(isInHospital);}
        public void setinDate(String inDate){this.inDate.set(inDate);}
        public void setoutDate(String outDate){this.outDate.set(outDate);}


        public void setAll(Medicalrecordsinfo medicalrecordsinfo)
        {
            this.setrecordId(medicalrecordsinfo.getRecordId());
            this.setcreateDate(medicalrecordsinfo.getCreateDate());
            this.setcurDepartment(medicalrecordsinfo.getCurDepartment());
            this.setcurDoctor(medicalrecordsinfo.getCurDoctor());
            this.setpatDemands(medicalrecordsinfo.getPatDemands());
            this.setpatId1(medicalrecordsinfo.getPatId1());
            this.setResult(medicalrecordsinfo.getResult());
            this.setpatConsumption(medicalrecordsinfo.getPatConsumption());
            this.setisInHospital(medicalrecordsinfo.getIsInHospital());
            this.setinDate(medicalrecordsinfo.getInDate());
            this.setoutDate(medicalrecordsinfo.getOutDate());
        }

        public SimpleIntegerProperty recordIdProperty() {
            return recordId;
        }

        public SimpleStringProperty createDateProperty() {
            return createDate;
        }

        public SimpleStringProperty curDepartmentProperty() {
            return curDepartment;
        }

        public SimpleStringProperty curDoctorProperty() {
            return curDoctor;
        }

        public SimpleDoubleProperty patConsumptionProperty() {
            return patConsumption;
        }

        public SimpleIntegerProperty patId1Property() {
            return patId1;
        }

        public SimpleStringProperty inDateProperty() {
            return inDate;
        }

        public SimpleStringProperty isInHospitalProperty() {
            return isInHospital;
        }

        public SimpleStringProperty outDateProperty() {
            return outDate;
        }

        public SimpleStringProperty patDemandsProperty() {
            return patDemands;
        }

        public SimpleStringProperty resultProperty() {
            return Result;
        }

        public int getRecordId() {
            return recordId.get();
        }

        public String getCreateDate() {
            return createDate.get();
        }

        public String getCurDepartment() {
            return curDepartment.get();
        }

        public double getPatConsumption() {
            return patConsumption.get();
        }

        public int getPatId1() {
            return patId1.get();
        }

        public String getCurDoctor() {
            return curDoctor.get();
        }

        public String getInDate() {
            return inDate.get();
        }

        public String getIsInHospital() {
            return isInHospital.get();
        }

        public String getOutDate() {
            return outDate.get();
        }

        public String getPatDemands() {
            return patDemands.get();
        }

        public String getResult() {
            return Result.get();
        }
    }

    public final class Medicine{
        private final SimpleIntegerProperty medNumber = new SimpleIntegerProperty();
        private final SimpleStringProperty medName = new SimpleStringProperty();
        private final SimpleDoubleProperty medPrice = new SimpleDoubleProperty();
        private final SimpleStringProperty medCategory = new SimpleStringProperty();
        private final SimpleIntegerProperty medStore = new SimpleIntegerProperty();

        public int getMedNumber() {
            return medNumber.get();
        }

        public double getMedPrice() {
            return medPrice.get();
        }

        public int getMedStore() {
            return medStore.get();
        }

        public String getMedName() {
            return medName.get();
        }

        public String getMedCategory() {
            return medCategory.get();
        }

        public SimpleIntegerProperty medNumberProperty() {
            return medNumber;
        }

        public SimpleStringProperty medNameProperty() {
            return medName;
        }

        public SimpleDoubleProperty medPriceProperty() {
            return medPrice;
        }

        public SimpleIntegerProperty medStoreProperty() {
            return medStore;
        }

        public SimpleStringProperty medCategoryProperty() {
            return medCategory;
        }
        public void setMedNumber(int medNumber){this.medNumber.set(medNumber);}
        public void setMedName(String medName){this.medName.set(medName);}
        public void setMedPrice(double medPrice) {this.medPrice.set(medPrice);}
        public void setMedCategory(String medCategory){this.medCategory.set(medCategory);}
        public void setMedStore(int medStore){this.medStore.set(medStore);}

        public void setAll(MedicineInfo medicineInfo){
            this.setMedCategory(medicineInfo.getMedCategory());
            this.setMedName(medicineInfo.getMedName());
            this.setMedNumber(medicineInfo.getMedNumber());
            this.setMedPrice(medicineInfo.getMedPrice());
            this.setMedStore(medicineInfo.getMedStore());
        }
    }
}
