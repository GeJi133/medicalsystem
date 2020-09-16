package dao;

import entity.MedicineInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MedicineDao {

    public static ResultSet MedicineFillTable()
    {
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from medicineinfo";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }

    public static boolean medSearchNoRepeat(MedicineInfo medicineInfo)
    {
        boolean flag = false;
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from medicineinfo where  medName = \'"+medicineInfo.getMedName()+"\'";
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

    public static ResultSet selectMedFromId(String id)
    {
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from medicineinfo where medName like \'%"+ id +"%\'";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet selectMedFromCategory(String Category)
    {
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from medicineinfo where medCategory like \'%"+ Category +"%\'";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet selectMedFromName(String id)
    {
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from medicineinfo where medName like \'%"+ id +"%\'";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static void changeMedInfo(MedicineInfo medicineInfo){
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        try {
            String sql = "update medicineinfo set medName ="+"\'" + medicineInfo.getMedName() + "\'"+",medPrice=?,medCategory="+ "\'" + medicineInfo.getMedCategory() + "\'"+",medStore=? where medNumber =" + medicineInfo.getMedNumber();
            ps = conn.prepareStatement(sql);
//            ps.setString(1,"\'" + medicineInfo.getMedName() + "\'");
            ps.setDouble(1,medicineInfo.getMedPrice());
//            ps.setString(3,"\'" + medicineInfo.getMedCategory() + "\'");
            ps.setInt(2,medicineInfo.getMedStore());
            ps.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void delMedicine(MedicineInfo medicineInfo)
    {
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        try {
            String sql = "delete from medicineinfo where medNumber = " + medicineInfo.getMedNumber();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static boolean addMedicine(MedicineInfo medicineInfo)
    {
        boolean flag = false;
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql="insert into medicineinfo(medName,medPrice,medCategory,medStore) values (?,?,?,?)" ;
            ps=conn.prepareStatement(sql);
            ps.setString(1,medicineInfo.getMedName());
            ps.setDouble(2,medicineInfo.getMedPrice());
            ps.setString(3,medicineInfo.getMedCategory());
            ps.setInt(4,medicineInfo.getMedStore());
            if (ps.execute())
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

    public static MedicineInfo searchMed(String id)
    {
        MedicineInfo medicineInfo = new MedicineInfo();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            String sql = "select * from medicineinfo where medNumber = " + id;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next())
            {
                medicineInfo = new MedicineInfo(rs.getInt("medNumber"),rs.getString("medName"),rs.getDouble("medPrice"), rs.getString("medCategory"),rs.getInt("medStore"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  medicineInfo;
    }
}
