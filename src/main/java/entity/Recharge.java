package entity;

import java.sql.Date;

public class Recharge {
    private int rid;
    private int patid;
    private String date;
    private float amount;

    public Recharge(int patid, float amount) {
        this.patid = patid;
        this.amount = amount;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getPatid() {
        return patid;
    }

    public void setPatid(int patid) {
        this.patid = patid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
