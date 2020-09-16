package dao;

import entity.departmentinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class departmentinfoDao {
    public static int getDepId(departmentinfo departmentinfo1){
        int id=0;
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from departmentinfo where depName=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,departmentinfo1.getDepName());
            rs=ps.executeQuery();
            if (rs.next())
                id=rs.getInt(1);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return id;
    }
}
