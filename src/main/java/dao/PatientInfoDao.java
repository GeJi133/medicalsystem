package dao;

import controller.DoctorLoginController;
import entity.AppointHistory;
import entity.Doctorinfo;
import entity.Medicalrecordsinfo;
import entity.PatientInfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PatientInfoDao {
    public static void changePatInfo(PatientInfo patientInfo){
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        try {
            String sql = "update patientinfo set patName=?,patGender=?,patAge =?,patPwd=?,patTel=? where PatId = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,patientInfo.getPatName());
            ps.setString(2,patientInfo.getPatGender());
            ps.setInt(3,patientInfo.getPatAge());
            ps.setString(4,patientInfo.getPatPwd());
            ps.setString(5,patientInfo.getPatTel());
            ps.setInt(6,patientInfo.getPatId());
            ps.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static PatientInfo searchPatient(String id)
    {
        PatientInfo patientInfo = new PatientInfo();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            String sql = "select * from patientinfo where patId = " + id;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next())
            {
                patientInfo = new PatientInfo(rs.getInt("patId"),rs.getString("patName"),rs.getString("patGender"),
                        rs.getInt("patAge"),rs.getString("patPwd"),rs.getFloat("patDeposit"),rs.getString("patDate"),rs.getString("patTel"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  patientInfo;
    }

    public static void delPatient(PatientInfo patientInfo)
    {
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        try {
            String sql = "delete from patientinfo where patId = " + patientInfo.getPatId();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static public ResultSet patientFillTable()
    {
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from patientinfo";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet selectPatFromId(String id)
    {
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from patientinfo where patId like \'%"+ id +"%\'";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet selectPatFromName(String name)
    {
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from patientinfo where patName like \'%"+ name +"%\'";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet selectPatFromTel(String tel)
    {
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from patientinfo where patTel like \'%"+ tel +"%\'";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    //合并内容

    public static PatientInfo patInfo = new PatientInfo();


    public int patLoginByPhone(String strPhone) {
        int count = 0;
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from patientInfo where phonenumber = ?";
            ps = conn.prepareStatement(query);
            ps.setString (1,strPhone);
            rs = ps.executeQuery();
            while(rs.next())
                count += 1;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return count;
    }

    //患者登录确认
    public int patLogin(PatientInfo patientinfo){
        int count = 0;
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from patientInfo where patId = ? and patPwd = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1,patientinfo.getPatId());
            ps.setString(2,patientinfo.getPatPwd());
            rs = ps.executeQuery();
            while(rs.next())
                count += 1;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return count;
    }
    //患者注册确认
    public int patRegister(PatientInfo patientinfo){
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "select * from patientInfo";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            rs.last();
            int patId;
            if(rs.wasNull())
                patId = 100000;
            else patId = rs.getInt("patId") + 1;
            String strName = patientinfo.getPatName();
            String strGender = patientinfo.getPatGender();
            int strAge = patientinfo.getPatAge();
            String strPassword = patientinfo.getPatPwd();
            double strDeposit = 0;
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(date);
            System.out.println(strDate);
            String strTel = patientinfo.getPatTel();
            String insert = "insert into patientInfo values (?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(insert);
            ps.setInt(1,patId);
            ps.setString(2,strName);
            ps.setString(3,strGender);
            ps.setInt(4,strAge);
            ps.setString(5,strPassword);
            ps.setDouble(6,strDeposit);
            ps.setString(7,strDate);
            ps.setString(8,strTel);
            ps.executeUpdate();
            return patId;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 100000;
    }
    //获取患者信息
    public PatientInfo selectPatInfo(int patId){
        Connection conn = BaseDao.getconn();
        PatientInfo patientinfo =  new PatientInfo();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String query = "select * from patientInfo where patId = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1,patId);
            rs = ps.executeQuery();
            rs.next();
            patientinfo.setPatId(patId);
            patientinfo.setPatName(rs.getString("patName"));
            patientinfo.setPatGender(rs.getString("patGender"));
            patientinfo.setPatAge(rs.getInt("patAge"));
            patientinfo.setPatDeposit(rs.getFloat("patDeposit"));
            patientinfo.setPatDate(rs.getString("patDate"));
            patientinfo.setPatTel(rs.getString("patTel"));
            patientinfo.setPatPwd(rs.getString("patPwd"));
        } catch (Exception e){
            e.printStackTrace();
        }
        return patientinfo;
    }

    //通过手机号获取患者信息
    public PatientInfo selectPatInfoByPhone(String phonenumber){
        Connection conn = BaseDao.getconn();
        PatientInfo patientinfo =  new PatientInfo();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String query = "select * from patientInfo where phonenumber = ?";
            ps = conn.prepareStatement(query);
            ps.setString (1,phonenumber);
            rs = ps.executeQuery();
            rs.next();
            patientinfo.setPhonenumber (phonenumber);
            patientinfo.setPatId(rs.getInt ("patId"));
            patientinfo.setPatName(rs.getString("patName"));
            patientinfo.setPatGender(rs.getString("patGender"));
            patientinfo.setPatAge(rs.getInt("patAge"));
            patientinfo.setPatDeposit(rs.getFloat("patDeposit"));
            patientinfo.setPatDate(rs.getString("patDate"));
            patientinfo.setPatTel(rs.getString("patTel"));
            patientinfo.setPatPwd(rs.getString("patPwd"));
        } catch (Exception e){
            e.printStackTrace();
        }
        return patientinfo;
    }
    //获取历史预约
    public List<AppointHistory> selectAppointHistory(int patId){
        List<AppointHistory> history = new ArrayList<AppointHistory>();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from appointhistory where patId2 = ?";
            ps= conn.prepareStatement(sql);
            ps.setInt(1,patId);
            rs = ps.executeQuery();
            while (rs.next()){
                AppointHistory temp = new AppointHistory();
                temp.setAppDate(rs.getString("appDate"));
                temp.setAppDec(rs.getString("appDec"));
                temp.setAppDoc(rs.getString("appDoc"));
                temp.setAppMoney(rs.getInt("appMoney"));
                history.add(temp);
                System.out.print(temp);
            }
            BaseDao.closeAll(conn,ps,rs);
            return history;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //获取消费记录
    public List<Medicalrecordsinfo> selectPaymentRecords(int patId){
        List<Medicalrecordsinfo> record = new ArrayList<Medicalrecordsinfo>();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String query = "select * from medicalRecordsInfo where patId1 = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1,patId);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Medicalrecordsinfo temp = new Medicalrecordsinfo();
                temp.setCreateDate(rs.getString("createDate"));
                temp.setPatConsumption(rs.getDouble("patConsumption"));
                record.add(temp);
            }
            System.out.println(record);
            BaseDao.closeAll(conn,ps,rs);
            return record;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //更新患者信息
    public int updatePatInfo(PatientInfo patientinfo){
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        int count = 0;
        try{
            String update = "update PatientInfo set patName = ?, patAge = ?, patTel = ?, patGender = ?, patPwd = ? where patId = ?";
            ps = conn.prepareStatement(update);
            ps.setString(1,patientinfo.getPatName());
            ps.setInt(2,patientinfo.getPatAge());
            ps.setString(3,patientinfo.getPatTel());
            ps.setString(4,patientinfo.getPatGender());
            ps.setString(5,patientinfo.getPatPwd());
            ps.setInt(6,patientinfo.getPatId());
            count = ps.executeUpdate();
            BaseDao.closeAll(conn,ps,null);
            System.out.println(count);
            BaseDao.closeAll(conn,ps,null);
            return count;
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    //患者充值
    public int patRecharge(Double rechargeAmount){
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        int count = 0;
        try{
            String update = "update PatientInfo set patDeposit = ? where patId = ?";
            ps = conn.prepareStatement(update);
            ps.setDouble(1, PatientInfoDao.patInfo.getPatDeposit() + rechargeAmount);
            ps.setInt(2, PatientInfoDao.patInfo.getPatId());
            count = ps.executeUpdate();
            BaseDao.closeAll(conn,ps,null);
            System.out.println(count);
            return count;
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    //获取科室医生
    public List<String> selectDepDoc(String depName){
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> doc = new ArrayList<String>();
        try{
            String query = "select * from doctorInfo where docDepartment = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1,depName);
            rs = ps.executeQuery();
            while (rs.next())
                doc.add(rs.getString("docName"));
            return doc;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //获取病历信息
    public List<Medicalrecordsinfo> selectDiagnosisRecords(int patId){
        List<Medicalrecordsinfo> record = new ArrayList<Medicalrecordsinfo>();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String query = "select * from medicalRecordsInfo where patId1 = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1,patId);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Medicalrecordsinfo temp = new Medicalrecordsinfo();
                temp.setCreateDate(rs.getString("createDate"));
                temp.setCurDepartment(rs.getString("curDepartment"));
                temp.setCurDoctor(rs.getString("curDoctor"));
                temp.setResult(rs.getString("Result"));
                record.add(temp);
            }
            BaseDao.closeAll(conn,ps,rs);
            return record;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public int checkPatient(PatientInfo patientInfo){
        int count=0;
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from patientinfo where patId=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,patientInfo.getPatId());
            rs=ps.executeQuery();
            if (rs.next())
                count=1;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return count;
    }
    public PatientInfo fixAddPat(PatientInfo patientInfo) throws IOException {

        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from patientinfo where patId=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,patientInfo.getPatId());
            rs=ps.executeQuery();
            if (rs.next()){
                patientInfo.setPatAge(rs.getInt(4));
                patientInfo.setPatDate(rs.getString(6));
                patientInfo.setPatGender(rs.getString(3));
                patientInfo.setPatName(rs.getString(2));
                patientInfo.setPatTel(rs.getString(8));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return patientInfo;
    }
    public static ArrayList<String> getPatName(){
        ArrayList<String> list=new ArrayList<>();
        ArrayList<Integer> patIdList=new ArrayList<>();
        MedicalRecordDao medicalRecordDao =new MedicalRecordDao();
        Doctorinfo doctorinfo=new Doctorinfo();
        doctorinfo= DoctorLoginController.getDoctorinfo();
        patIdList= medicalRecordDao.getPatId(doctorinfo);
        for (Integer patId: patIdList){
            Connection conn=BaseDao.getconn();
            PreparedStatement ps=null;
            ResultSet rs=null;
            try {
                String sql="select * from patientinfo where patId=?";
                ps=conn.prepareStatement(sql);
                ps.setInt(1,patId);
                rs=ps.executeQuery();
                if (rs.next()){
                    list.add(rs.getString(2));
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                BaseDao.closeAll(conn,ps,rs);
            }
        }
        return list;
    }
    public boolean checkpat2(PatientInfo patientInfo){
        boolean check=false;
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from patientinfo where patId=? and patName=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,patientInfo.getPatId());
            ps.setString(2,patientInfo.getPatName());
            rs=ps.executeQuery();
            if (rs.next())
                check=true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return check;
    }
    public static void patCost(int patId,double cost){
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from patientinfo where patId=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,patId);
            rs=ps.executeQuery();
            if (rs.next()){
                double balance=rs.getDouble(6);
                balance-=cost;
                String sql2="update patientinfo set patDeposit=? where patId=?";
                ps=conn.prepareStatement(sql2);
                ps.setDouble(1,balance);
                ps.setInt(2,patId);
                ps.executeUpdate();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeAll(conn,ps,rs);
        }
    }
    //
    public void chargeAppoinment(){
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        try {
            String update="update patientInfo set patDeposit = ? where patId = ?";
            ps=conn.prepareStatement(update);
            PatientInfoDao.patInfo.setPatDeposit(PatientInfoDao.patInfo.getPatDeposit() - 20);
            ps.setDouble(1,PatientInfoDao.patInfo.getPatDeposit());
            ps.setInt(2,PatientInfoDao.patInfo.getPatId());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeAll(conn,ps,null);
        }
    }

}
