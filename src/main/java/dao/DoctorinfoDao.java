package dao;

import entity.Doctorinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DoctorinfoDao {
    public Doctorinfo selectDoctorInfoByPhone(String phonenumber){
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        Doctorinfo doctorinfo=new Doctorinfo ();
        try {
            String sql="select * from doctorinfo where phonenumber=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,phonenumber);
            rs=ps.executeQuery();
            while(rs.next ()) {
                doctorinfo.setPhonenumber (phonenumber);
                doctorinfo.setDocId (rs.getInt (1));
                doctorinfo.setDocName (rs.getString (2));
                doctorinfo.setDocAge (rs.getInt (4));
                doctorinfo.setDocDepartment (rs.getString (6));
                doctorinfo.setDocGender (rs.getString (3));
                doctorinfo.setDocRank (rs.getString (7));
                doctorinfo.setDocTel (rs.getString (8));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return doctorinfo;
    }


    public int selectDoctorByPhone(String phonenumber){
        int count=0;
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        Doctorinfo doctorinfo=new Doctorinfo ();
        try {
            String sql="select * from doctorinfo where phonenumber=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,phonenumber);
            rs=ps.executeQuery();
            if (rs.next())
                count=1;
            doctorinfo.setPhonenumber (phonenumber);
            doctorinfo.setDocName(rs.getString(2));
            doctorinfo.setDocAge(rs.getInt(4));
            doctorinfo.setDocDepartment(rs.getString(6));
            doctorinfo.setDocGender(rs.getString(3));
            doctorinfo.setDocRank(rs.getString(7));
            doctorinfo.setDocTel(rs.getString(8));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return count;
    }

    public int selectDoctor(Doctorinfo doctorinfo){
        int count=0;
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from doctorinfo where docId=? and docPwd=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,doctorinfo.getDocId());
            ps.setString(2,doctorinfo.getDocPwd());
            rs=ps.executeQuery();
            if (rs.next())
                count=1;
            doctorinfo.setDocName(rs.getString(2));
            doctorinfo.setDocAge(rs.getInt(4));
            doctorinfo.setDocDepartment(rs.getString(6));
            doctorinfo.setDocGender(rs.getString(3));
            doctorinfo.setDocRank(rs.getString(7));
            doctorinfo.setDocTel(rs.getString(8));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return count;
    }

    public static void changeDocInfo(Doctorinfo doctorinfo){
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        try {
            String sql = "update doctorinfo set docAge =?,docPwd=?,docDepartment=?,docRank=?,onDuty=?,offDuty=? where docId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,doctorinfo.getDocAge());
            ps.setString(2,doctorinfo.getDocPwd());
            ps.setString(3,doctorinfo.getDocDepartment());
            ps.setString(4,doctorinfo.getDocRank());
            ps.setString(5,doctorinfo.getOnDuty());
            ps.setString(6,doctorinfo.getOffDuty());
            ps.setInt(7,doctorinfo.getDocId());
            ps.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void delDoctor(Doctorinfo doctorinfo)
    {
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        try {
            String sql = "delete from doctorinfo where docId = " + doctorinfo.getDocId();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean addDoctor(Doctorinfo doctorinfo)
    {
        boolean flag = false;
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql="insert into doctorinfo(docName,docGender,docAge,docPwd,docDepartment,docRank,docTel,onDuty,offDuty) values (?,?,?,?,?,?,?,?,?)" ;
            ps=conn.prepareStatement(sql);
            ps.setString(1,doctorinfo.getDocName());
            ps.setString(2,doctorinfo.getDocGender());
            ps.setInt(3,doctorinfo.getDocAge());
            ps.setString(4,doctorinfo.getDocPwd());
            ps.setString(5,doctorinfo.getDocDepartment());
            ps.setString(6,doctorinfo.getDocRank());
            ps.setString(7,doctorinfo.getDocTel());
            ps.setString(8,doctorinfo.getOnDuty());
            ps.setString(9,doctorinfo.getOffDuty());
            if (ps.execute())
            {
                flag = true;
                conn.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }

    public static Doctorinfo searchDoctor(String id)
    {
        Doctorinfo doctorinfo =new Doctorinfo();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            String sql = "select * from doctorinfo where docId = " + id;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next())
            {
                doctorinfo = new Doctorinfo(rs.getInt("docId"),rs.getString("docName"),rs.getString("docGender"),
                        rs.getInt("docAge"),rs.getString("docPwd"),rs.getString("docDepartment"),rs.getString("docRank"),rs.getString("docTel"),rs.getString("onDuty"),rs.getString("offDuty"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  doctorinfo;
    }

    static public ResultSet docDepartmentFillTable()
    {
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from departmentinfo";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean docSearchNoRepeat(Doctorinfo doctorinfo)
    {
        boolean flag = false;
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from doctorinfo where  docName = \'"+doctorinfo.getDocName()+"\' and docTel = \'" + doctorinfo.getDocTel() +"\'";
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

    static public ResultSet DoctorFillTable()
    {
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from doctorinfo";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet selectDocFromId(String id)
    {
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from doctorinfo where docId like \'%"+ id +"%\'";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet selectDocFromName(String name)
    {
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from doctorinfo where docName like \'%"+ name +"%\'";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet selectDocFromRank(String rank)
    {
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from doctorinfo where docRank like \'%"+ rank +"%\'";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet selectDocFromDepartment(String department)
    {
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from doctorinfo where docDepartment like \'%"+ department +"%\'";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    public boolean fixDocTel(Doctorinfo doctorinfo){
        boolean fix=false;
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="update doctorinfo set docTel=? where docId=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,doctorinfo.getDocTel());
            ps.setInt(2,doctorinfo.getDocId());
            ps.executeUpdate();
            fix=true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return fix;
    }
    public boolean fixDocPwd(Doctorinfo doctorinfo){
        boolean fix=false;
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="update doctorinfo set docPwd=? where docId=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,doctorinfo.getDocPwd());
            ps.setInt(2,doctorinfo.getDocId());
            ps.executeUpdate();
            fix=true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return fix;
    }
    public static String getDocName(int DocId){
        String name="";
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from doctorinfo where docId=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,DocId);
            rs=ps.executeQuery();
            if (rs.next())
                name=rs.getString(2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return name;
    }
}
