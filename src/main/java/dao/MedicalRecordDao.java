package dao;

import entity.Doctorinfo;
import entity.Medicalrecordsinfo;
import entity.PatientInfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicalRecordDao {
    public static String tr(String str, String... args) {
        String ans = new String();
        ans = str.replaceFirst("%", args[0]);
        for (int i = 1; i < args.length; i++)
            ans = ans.replaceFirst("%", args[i]);
        System.out.println(ans);
        return ans;
    }

    public void insertMedicalRecord(Medicalrecordsinfo medicalrecordsinfo) {
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = tr("insert into medicalrecordsinfo (createDate,curDepartment,curDoctor,patDemands,patId1,Result,patConsumption,isInHospital,inDate,outDate,roomId,bedId) values ('%','%','%','%',%,'%',%,'%','%','%','%','%')", medicalrecordsinfo.getCreateDate(), medicalrecordsinfo.getCurDepartment(),
                    medicalrecordsinfo.getCurDoctor(), medicalrecordsinfo.getPatDemands(), String.valueOf(medicalrecordsinfo.getPatId1()), medicalrecordsinfo.getResult(), String.valueOf(medicalrecordsinfo.getPatConsumption()), medicalrecordsinfo.getIsInHospital(), medicalrecordsinfo.getInDate(), medicalrecordsinfo.getOutDate(), medicalrecordsinfo.getRoomId(), medicalrecordsinfo.getBedId());
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
    }

    public int getMedRecTotal() {
        int total = 0;
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from medicalrecordsinfo where recordId=(select max(recordId) from medicalrecordsinfo)";
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

    public static ArrayList<Integer> getRecordId(Doctorinfo doctorinfo) {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from medicalrecordsinfo where curDoctor=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, doctorinfo.getDocName());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return list;
    }

    public static ArrayList<Integer> getPatId(Doctorinfo doctorinfo) {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT patId1 FROM medicalrecordsinfo where curDoctor=? GROUP BY patId1";
            ps = conn.prepareStatement(sql);
            ps.setString(1, doctorinfo.getDocName());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return list;
    }

    public static ArrayList<String> getCreateDate(Doctorinfo doctorinfo) {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT createDate FROM medicalrecordsinfo where curDoctor=? GROUP BY createDate";
            ps = conn.prepareStatement(sql);
            ps.setString(1, doctorinfo.getDocName());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return list;
    }

    public static ArrayList<Medicalrecordsinfo> getMedicalRecords(Doctorinfo doctorinfo) {
        ArrayList<Medicalrecordsinfo> list = new ArrayList<>();

        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM medicalrecordsinfo WHERE curDoctor=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, doctorinfo.getDocName());
            rs = ps.executeQuery();
            while (rs.next()) {
                Medicalrecordsinfo medicalrecordsinfo = new Medicalrecordsinfo();
                medicalrecordsinfo.setRecordId(rs.getInt(1));
                medicalrecordsinfo.setCreateDate(rs.getString(2));
                medicalrecordsinfo.setCurDepartment(rs.getString(3));
                medicalrecordsinfo.setCurDoctor(rs.getString(4));
                medicalrecordsinfo.setPatDemands(rs.getString(5));
                medicalrecordsinfo.setPatId1(rs.getInt(6));
                medicalrecordsinfo.setResult(rs.getString(7));
                medicalrecordsinfo.setPatConsumption(rs.getDouble(8));
                medicalrecordsinfo.setIsInHospital(rs.getString(9));
                medicalrecordsinfo.setInDate(rs.getString(10));
                medicalrecordsinfo.setOutDate(rs.getString(11));
                medicalrecordsinfo.setRoomId(rs.getString(12));
                medicalrecordsinfo.setBedId(rs.getString(13));
                PatientInfo patientInfo = new PatientInfo();
                patientInfo.setPatId(rs.getInt(6));
                medicalrecordsinfo.setPatName(new PatientInfoDao().fixAddPat(patientInfo).getPatName());
                list.add(medicalrecordsinfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return list;
    }

    public static ArrayList<Medicalrecordsinfo> getMedicalRecords(String patCardID, String medCorDate) {
        ArrayList<Medicalrecordsinfo> list = new ArrayList<>();
        Medicalrecordsinfo medicalrecordsinfo = new Medicalrecordsinfo();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM medicalrecordsinfo where patId1=? and createDate=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, patCardID);
            ps.setString(2, medCorDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                medicalrecordsinfo.setRecordId(rs.getInt(1));
                medicalrecordsinfo.setCreateDate(rs.getString(2));
                medicalrecordsinfo.setCurDepartment(rs.getString(3));
                medicalrecordsinfo.setCurDoctor(rs.getString(4));
                medicalrecordsinfo.setPatDemands(rs.getString(5));
                medicalrecordsinfo.setPatId1(rs.getInt(6));
                medicalrecordsinfo.setResult(rs.getString(7));
                medicalrecordsinfo.setPatConsumption(rs.getDouble(8));
                medicalrecordsinfo.setIsInHospital(rs.getString(9));
                medicalrecordsinfo.setInDate(rs.getString(10));
                medicalrecordsinfo.setOutDate(rs.getString(11));
                medicalrecordsinfo.setRoomId(rs.getString(12));
                medicalrecordsinfo.setBedId(rs.getString(13));

                list.add(medicalrecordsinfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return list;
    }
    public static ArrayList<Medicalrecordsinfo> getMedicalRecords(Medicalrecordsinfo medicalrecordsinfo1) {
        ArrayList<Medicalrecordsinfo> list = new ArrayList<>();

        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM medicalrecordsinfo WHERE curDoctor=? and recordId=? and createDate=? and patId1=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, medicalrecordsinfo1.getCurDoctor());
            ps.setInt(2,medicalrecordsinfo1.getRecordId());
            ps.setString(3,medicalrecordsinfo1.getCreateDate());
            ps.setInt(4,medicalrecordsinfo1.getPatId1());
            rs = ps.executeQuery();
            if (rs.next()) {
                initMedRecord1(medicalrecordsinfo1,rs);
                list.add(medicalrecordsinfo1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return list;
    }
    public static ArrayList<Medicalrecordsinfo> getMedicalRecords2(Medicalrecordsinfo medicalrecordsinfo1) {
        ArrayList<Medicalrecordsinfo> list = new ArrayList<>();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM medicalrecordsinfo WHERE curDoctor=? and patId1=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, medicalrecordsinfo1.getCurDoctor());
            ps.setInt(2,medicalrecordsinfo1.getPatId1());
            rs = ps.executeQuery();
            while (rs.next()) {
                Medicalrecordsinfo medicalrecordsinfo=new Medicalrecordsinfo();
                medicalrecordsinfo.setCurDoctor(medicalrecordsinfo.getCurDoctor());

                initMedRecord1(medicalrecordsinfo,rs);
                medicalrecordsinfo.setRecordId(rs.getInt(1));
                medicalrecordsinfo.setCreateDate(rs.getString(2));
                medicalrecordsinfo.setPatId1(rs.getInt(6));
                list.add(medicalrecordsinfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return list;
    }
    public static void initMedRecord1(Medicalrecordsinfo medicalrecordsinfo1, ResultSet rs) throws SQLException, IOException {
        medicalrecordsinfo1.setCurDepartment(rs.getString(3));
        medicalrecordsinfo1.setPatDemands(rs.getString(5));
        medicalrecordsinfo1.setResult(rs.getString(7));
        medicalrecordsinfo1.setPatConsumption(rs.getDouble(8));
        medicalrecordsinfo1.setIsInHospital(rs.getString(9));
        medicalrecordsinfo1.setInDate(rs.getString(10));
        medicalrecordsinfo1.setOutDate(rs.getString(11));
        medicalrecordsinfo1.setRoomId(rs.getString(12));
        medicalrecordsinfo1.setBedId(rs.getString(13));
        PatientInfo patientInfo = new PatientInfo();
        patientInfo.setPatId(rs.getInt(6));
        medicalrecordsinfo1.setPatName(new PatientInfoDao().fixAddPat(patientInfo).getPatName());
    }
    public static ArrayList<String> getIllHis(int patId){
        ArrayList<String> illHis=new ArrayList<>();
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            String sql="select * from medicalrecordsinfo where patId1=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,patId);
            rs=ps.executeQuery();
            while(rs.next()){
                illHis.add(rs.getString(7)+"("+rs.getString(2)+")"+"       ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return illHis;
    }

    public static ResultSet MedicalRecordFillTable()
    {
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from medicalrecordsinfo";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }


    public static ResultSet selectFromId(String id)
    {
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from medicalrecordsinfo where recordId like \'%"+ id +"%\'";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet selectFromDepart(String depart)
    {
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from medicalrecordsinfo where curDepartment like \'%"+ depart +"%\'";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet selectFromDoc(String doc)
    {
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from medicalrecordsinfo where curDoctor like \'%"+ doc +"%\'";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet selectFromPat(String Pat)
    {
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from medicalrecordsinfo where patId1 like \'%"+ Pat +"%\'";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
}
