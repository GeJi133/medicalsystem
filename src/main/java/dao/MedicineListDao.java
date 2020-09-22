package dao;

import entity.MedicineList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MedicineListDao {
    public void addMedicineList(MedicineList medicineList){

        System.out.println (medicineList.toString ());

        Connection conn=BaseDao.getconn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {

            String sql= MedicalRecordDao.tr("insert into medicinelist (medicalRecordsNumber,medicineNumber,medicineAmount,medicineUsage,medicineDosage,medicineFrequency) values (%,%,%,'%',%,%)",String.valueOf(medicineList.getMedicalRecordsNumber()),String.valueOf(medicineList.getMedicineNumber()),String.valueOf (medicineList.getMedicineAmountl()),medicineList.getMedicineUsage(),String.valueOf (medicineList.getMedicineDosage ()),String.valueOf (medicineList.getMedicineFrequency ()));

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


    public int checkMedicine(MedicineList medicineList){
        int status=0;
        int days=medicineList.getMedicineAmountl ()/(medicineList.getMedicineFrequency ()*medicineList.getMedicineDosage ());
        if (days>=30)
            status=1;
        else if(medicineList.getMedicineFrequency ()>=5)
            status=2;
        return  status;
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

                medicineList.setMedicineAmountl(rs.getInt (4));

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
