package entity;

public class WorkInfo {

    private int WorkNumber;
    private int depNumber;
    private String Monday;
    private String Tuesday;
    private String Wensday;
    private String Thursday;
    private String Friday;
    private String Saturday;
    private String Sunday;
    private String docname;

    public WorkInfo(){

    }

    public WorkInfo(int WorkNumber,int depNumber,String Monday,String Tuesday,String Wensday,String Thursday,String Friday, String Saturday,String Sunday) {
        this.WorkNumber=WorkNumber;
        this.depNumber=depNumber;
        this.Monday=Monday;
        this.Thursday=Thursday;
        this.Tuesday=Tuesday;
        this.Wensday=Wensday;
        this.Friday=Friday;
        this.Saturday=Saturday;
        this.Sunday=Sunday;
    }


    public int getWorkNumber() {
        return WorkNumber;
    }

    public void setWorkNumber(int workNumber) {
        WorkNumber = workNumber;
    }

    public int getDepNumber() {
        return depNumber;
    }

    public void setDepNumber(int depNumber) {
        this.depNumber = depNumber;
    }

    public String getMonday() {
        return Monday;
    }

    public void setMonday(String monday) {
        Monday = monday;
    }

    public String getTuesday() {
        return Tuesday;
    }

    public void setTuesday(String tuesday) {
        Tuesday = tuesday;
    }

    public String getWensday() {
        return Wensday;
    }

    public void setWensday(String wensday) {
        Wensday = wensday;
    }

    public String getThursday() {
        return Thursday;
    }

    public void setThursday(String thursday) {
        Thursday = thursday;
    }

    public String getFriday() {
        return Friday;
    }

    public void setFriday(String friday) {
        Friday = friday;
    }

    public String getSaturday() {
        return Saturday;
    }

    public void setSaturday(String saturday) {
        Saturday = saturday;
    }

    public String getSunday() {
        return Sunday;
    }

    public void setSunday(String sunday) {
        Sunday = sunday;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }
}
