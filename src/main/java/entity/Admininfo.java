package entity;

public class Admininfo {
    private int adminAccount;
    private String adminPassword;

    public Admininfo(int adminAccount,String adminPassword){
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
