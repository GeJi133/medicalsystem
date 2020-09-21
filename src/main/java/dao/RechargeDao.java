package dao;

import entity.PatientInfo;
import entity.Recharge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RechargeDao {
    public static void changeDeposit(Recharge recharge){
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        try {
            String sql = "update patientinfo set patDeposit=patDeposit+? where PatId = ?";
            ps = conn.prepareStatement(sql);
            ps.setFloat (1,recharge.getAmount ());

            ps.setInt(2,recharge.getPatid ());
            ps.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Recharge newRecharge(Recharge recharge)
    {
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from recharge";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            rs.last();
            int rid;
            if(rs.wasNull())
                rid = 100;
            else rid = rs.getInt("rid") + 1;

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(date);
            System.out.println(strDate);
            recharge.setDate (strDate);
            recharge.setRid (rid);

            String insert = "insert into recharge values (?,?,?,?,0)";
            ps = conn.prepareStatement(insert);
            ps.setInt (1,rid);
            ps.setInt(2,recharge.getPatid ());
            ps.setString(3,strDate);
            ps.setFloat (4,recharge.getAmount ());
            ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return recharge;
    }

}
