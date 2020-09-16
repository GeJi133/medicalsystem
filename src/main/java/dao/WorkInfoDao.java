package dao;

import entity.WorkInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WorkInfoDao {
    public static ArrayList<WorkInfo> getWorkInfo(WorkInfo workInfo){
        ArrayList<WorkInfo>workInfos=new ArrayList<>();
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from workinfo where depNumber=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,workInfo.getDepNumber());
            rs=ps.executeQuery();
            while(rs.next()){
                WorkInfo workInfo1=new WorkInfo();
                workInfo1.setDocname(DoctorinfoDao.getDocName(rs.getInt(1)));
                workInfo1.setMonday(rs.getString(3));
                workInfo1.setTuesday(rs.getString(4));
                workInfo1.setWensday(rs.getString(5));
                workInfo1.setThursday(rs.getString(6));
                workInfo1.setFriday(rs.getString(7));
                workInfo1.setSaturday(rs.getString(8));
                workInfo1.setSunday(rs.getString(9));
                workInfos.add(workInfo1);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return workInfos;
    }
}
