package entity;

public class Admininfo {
    private int adminAccount;
    private String adminPassword;
    private String phonenumber;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Admininfo(int adminAccount, String adminPassword){
        this.adminAccount=adminAccount;
        this.adminPassword=adminPassword;
    }
    public Admininfo(){}
    public int getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(int adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

}
