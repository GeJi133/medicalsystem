package dao;

import entity.MedicineInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MedicineInfoDao {

    public int getMedicineId(MedicineInfo medicineInfo) {
        int medId = 0;
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from medicineinfo where medName=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, medicineInfo.getMedName());
            rs = ps.executeQuery();
            rs.next();
            medicineInfo.setMedNumber(rs.getInt(1));
            medicineInfo.setMedCategory(rs.getString(4));
            medicineInfo.setMedPrice(rs.getDouble(3));
            medicineInfo.setMedStore(rs.getInt(5));
            medId = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return medId;
    }
    public ArrayList<String> getMediList(){
        ArrayList <String> medList=new ArrayList<>();
        Connection conn=BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from medicineinfo";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                medList.add(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return medList;
    }
    public static double getMedcienPrice(MedicineInfo medicineInfo){
        double price=0;
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            String sql="select * from medicineinfo where medName=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,medicineInfo.getMedName());
            rs=ps.executeQuery();
            if (rs.next())
                price=rs.getDouble(3);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return price;
    }
    public static String getMedName(int medId){
        String medName = null;
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            String sql="select * from medicineinfo where medNumber=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,medId);
            rs=ps.executeQuery();
            if (rs.next())
                medName=rs.getString(2);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return medName;
    }
    public static boolean medCost(String medname,int num){
        boolean costSucc=false;
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from medicineinfo where medName=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,medname);
            rs=ps.executeQuery();
            if (rs.next()){
                int remain=rs.getInt(5);
                remain-=num;
                if (remain>0) {
                    String sql2 = "update medicineinfo set medStore=? where medName=?";
                    ps = conn.prepareStatement(sql2);
                    ps.setInt(1, remain);
                    ps.setString(2, medname);
                    ps.executeUpdate();
                    costSucc=true;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return costSucc;
    }
}
