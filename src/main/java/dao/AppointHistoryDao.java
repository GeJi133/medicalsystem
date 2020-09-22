package dao;

import entity.AppointHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointHistoryDao {
    public static String tr(String str, String... args) {
        String ans = new String();
        ans = str.replaceFirst("%", args[0]);
        for (int i = 1; i < args.length; i++)
            ans = ans.replaceFirst("%", args[i]);
        System.out.println(ans);
        return ans;
    }

    public int checkAppointMent(){
        //返回状态信息，其中0表示信息无误，1表示预约时间人数过多，更换预约时间，2表示预约时间不合适
        int status=0;

        return status;
    }

    //获取历史预约
    public ArrayList<AppointHistory> selectAppointByDepartment(String depName){
        System.out.println ("查找历史预约");
        ArrayList<AppointHistory> history = new ArrayList<AppointHistory> ();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from appointhistory where appDec = ?";
            ps= conn.prepareStatement(sql);
            ps.setString (1,depName);
            System.out.println (ps.toString ());
            rs = ps.executeQuery();
            while (rs.next()){
                AppointHistory temp = new AppointHistory();
                temp.setAppDate(rs.getString("appDate"));
                temp.setAppDec(rs.getString("appDec"));
                temp.setPatId2 (rs.getInt ("patId2"));
                temp.setAppDoc(rs.getString("appDoc"));
                temp.setAppMoney(rs.getInt("appMoney"));
                temp.setAppointDate (String.valueOf (rs.getDate ("appointDate")));
                temp.setAppointTime (rs.getString ("appointTime"));
                history.add(temp);
                System.out.print(temp.getPatId2 ());
            }
            BaseDao.closeAll(conn,ps,rs);
            return history;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void insertAppoint(AppointHistory appointHistory){
        Connection conn = BaseDao.getconn();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        appointHistory.setAppDate (String.valueOf (date));
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = tr("insert into appointhistory (appdate,appdec,appdoc,appmoney,patId2,appointDate,appointTime,status) values ('%','%','%','%','%','%','%',%)",appointHistory.getAppDate(),appointHistory.getAppDec(),appointHistory.getAppDoc(),String.valueOf(appointHistory.getAppMoney()),String.valueOf(appointHistory.getPatId2()),appointHistory.getAppointDate (),appointHistory.getAppointTime (),"0");
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
