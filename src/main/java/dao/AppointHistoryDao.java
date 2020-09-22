package dao;

import entity.AppointHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointHistoryDao {
    public static String tr(String str, String... args) {
        String ans = new String();
        ans = str.replaceFirst("%", args[0]);
        for (int i = 1; i < args.length; i++)
            ans = ans.replaceFirst("%", args[i]);
        System.out.println(ans);
        return ans;
    }

    public void insertAppoint(AppointHistory appointHistory){
        Connection conn = BaseDao.getconn();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        appointHistory.setAppDate (String.valueOf (date));
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = tr("insert into appointhistory (appdate,appdec,appdoc,appmoney,patId2,appointDate,appointTime) values ('%','%','%','%','%','%','%')",appointHistory.getAppDate(),appointHistory.getAppDec(),appointHistory.getAppDoc(),String.valueOf(appointHistory.getAppMoney()),String.valueOf(appointHistory.getPatId2()),appointHistory.getAppointDate (),appointHistory.getAppointTime ());
           ps = conn.prepareStatement(sql);
           ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeAll(conn,ps,rs);
        }
    }
    public int getAppointTotal() {
        int total = 0;
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from appointhistory where appointId=(select max(appointId) from appointhistory)";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            total = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return total;
    }

}