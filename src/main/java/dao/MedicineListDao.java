package dao;

import entity.MedicineList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MedicineListDao {
    public void addMedicineList(MedicineList medicineList){
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql= MedicalRecordDao.tr("insert into medicinelist (medicalRecordsNumber,medicineNumber,medicineAmount,medicineUsage) values (%,%,'%','%')",String.valueOf(medicineList.getMedicalRecordsNumber()),String.valueOf(medicineList.getMedicineNumber()),medicineList.getMedicineAmountl(),medicineList.getMedicineUsage());
            ps=conn.prepareStatement(sql);
            ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
    }
    public static ArrayList<MedicineList> getMedList(int medListId){
        ArrayList<MedicineList> medicineLists=new ArrayList<>();
        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from medicinelist where medicalRecordsNumber=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,medListId);
            rs=ps.executeQuery();
            while(rs.next()){
                MedicineList medicineList=new MedicineList();
                medicineList.setMedicineAmountl(rs.getString(4));
                medicineList.setMedicineUsage(rs.getString(5));
                medicineList.setMedicineNumber(rs.getInt(3));
                medicineLists.add(medicineList);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            BaseDao.closeAll(conn,ps,rs);
        }
        return medicineLists;
    }
}
