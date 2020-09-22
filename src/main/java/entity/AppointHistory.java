package entity;

import java.sql.Date;
import java.sql.Time;

public class AppointHistory {

    private String appDate;
    private String appDec;
    private String appDoc;
    private int appMoney;
    private int patId2;
    private String appointTime;
    private String appointDate;

    public String getAppointDate() {
        return appointDate;
    }

    public void setAppointDate(String appointDate) {
        this.appointDate = appointDate;
    }

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
    }

    public AppointHistory(){}
    public AppointHistory(String appDate, String appDec, String appDoc, int appMoney, int patId2){

        this.appDate = appDate;
        this.appDec = appDec;
        this.appDoc = appDoc;
        this.appMoney = appMoney;
        this.patId2 = patId2;
    }

    public String getAppDate(){
        return appDate;
    }
    public void setAppDate(String appDate){
        this.appDate=appDate;
    }
    public String getAppDec(){
        return appDec;
    }
    public void setAppDec(String appDec){
        this.appDec =appDec;
    }
    public String getAppDoc(){
        return appDoc;
    }
    public void setAppDoc(String appDoc){
        this.appDoc=appDoc;
    }
    public int getAppMoney(){
        return appMoney;
    }
    public void setAppMoney(int appMoney){
        this.appMoney=appMoney;
    }
    public int getPatId2(){
        return patId2;
    }
    public void setPatId2(int patId2){
        this.patId2 = patId2;
    }
}
