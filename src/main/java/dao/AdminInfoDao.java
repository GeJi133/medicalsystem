package dao;

import entity.Admininfo;
import entity.DrugSales;
import entity.depDiagnosisNumber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class AdminInfoDao {

    public int AdminLogin(Admininfo admin){
        int count = 0;
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from admininfo where adminAccount = ? and adminPassWord = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1,admin.getAdminAccount());
            ps.setString(2,admin.getAdminPassword());
            rs = ps.executeQuery();
            while(rs.next())
                count = 1;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return count;
    }

    public void AdminInsert(Admininfo admin)
    {
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            String query = "insert into admininfo(adminAccount,adminPassword) values (?,?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1,admin.getAdminAccount());
            ps.setString(2,admin.getAdminPassword());
            ps.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
    }

    public void AdminPwdChange(Admininfo admin)
    {
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        try {
            String query = "update admininfo set adminPassword = "+"\'" + admin.getAdminPassword() + "\'"+" where adminAccount =" + String.valueOf(admin.getAdminAccount());
            ps = conn.prepareStatement(query);
            ps.executeUpdate(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void AdminDel(Admininfo admin)
    {
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        try {
            String query = "delete from admininfo where adminAccount = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1,admin.getAdminAccount());
            ps.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean AdminSearchNoRepeat(Admininfo admin)
    {
        boolean flag = false;
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from admininfo where adminAccount =" + String.valueOf(admin.getAdminAccount());
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (!rs.next())
            {
                flag = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }

    static public ResultSet AdminFillTable()
    {
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from admininfo";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }
  //合并内容
  //获取药品信息
  public List<String> selectMedicineInfo(){
      Connection conn = BaseDao.getconn();
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<String> list = new ArrayList<String>();
      try {
          String query = "select * from MedicineInfo";
          ps = conn.prepareStatement(query);
          rs = ps.executeQuery();
          while (rs.next())
          {
              String temp = rs.getString("medName");
              list.add(temp);
          }
          BaseDao.closeAll(conn,ps,rs);
          return list;
      }
      catch (Exception e){
          e.printStackTrace();
      }
      return null;
  }

    //获取药品销量
    public List<DrugSales> selectDrugSales(List<String> drugList){
        Connection conn = BaseDao.getconn();
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        List<DrugSales> list = new ArrayList<DrugSales>();
        try {
            for(int i = 0; i < drugList.size(); i++)
            {
                int sale = 0;
                String query1 = "select * from MedicineInfo where medName = ?";
                ps1 = conn.prepareStatement(query1);
                ps1.setString(1,drugList.get(i));
                rs1 = ps1.executeQuery();
                rs1.next();
                String query2 = "select * from MedicineList where medicineNumber = ?";
                ps2 = conn.prepareStatement(query2);
                ps2.setInt(1,rs1.getInt("medNumber"));
                rs2 = ps2.executeQuery();
                while (rs2.next())
                    sale += rs2.getString("medicineAmount").charAt(0) - '0';
                DrugSales drugsale = new DrugSales();
                drugsale.setMedicineName(drugList.get(i));
                drugsale.setSale(sale);
                list.add(drugsale);
            }
            BaseDao.closeAll(conn,ps1,rs1);
            BaseDao.closeAll(null,ps2,rs2);
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //获取科室诊断数
    public List<depDiagnosisNumber> selectDepDiagnosisNumber(List<String> depList){
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<depDiagnosisNumber> list = new ArrayList<depDiagnosisNumber>();
        try {
            for(int i = 0; i < depList.size(); i++)
            {
                int count = 0;
                String query2 = "select * from medicalRecordsInfo where curDepartment = ?";
                ps = conn.prepareStatement(query2);
                ps.setString(1,depList.get(i));
                rs = ps.executeQuery();
                while (rs.next())
                    count++;
                depDiagnosisNumber depdiagnosisnumber = new depDiagnosisNumber();
                depdiagnosisnumber.setDepName(depList.get(i));
                depdiagnosisnumber.setDiagnosisNumber(count);
                list.add(depdiagnosisnumber);
                System.out.println(count);
            }
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //获取科室信息
    public List<String> selectDepartmentInfo(){
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<String>();
        try {
            String query = "select * from departmentInfo";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next())
            {
                String temp = rs.getString("depName");
                list.add(temp);
            }
            BaseDao.closeAll(conn,ps,rs);
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
