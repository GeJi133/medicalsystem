package controller;

//import com.sun.xml.internal.bind.v2.TODO;
import dao.*;
import entity.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import controller.addDocInfoController;

public class adminManageController {

    @FXML ComboBox<String> medicine_combobox;
    @FXML TextField pat_text;
    @FXML ComboBox<String> pat_choice;
    @FXML TextField medicine_text;
    @FXML TextField mrsearch;
    @FXML ChoiceBox<String> mr_choice;
    @FXML Tab pat_info;
    @FXML Tab doc_info;
    @FXML Tab record_info;

    @FXML HBox edit_doc_pane;
    @FXML Pane doc_func_pane;
    @FXML HBox edit_pat_pane;
    @FXML Pane pat_func_pane;

    @FXML TableView table_pat;
    @FXML
    Button info_search;
    @FXML
    Button admin_manage;
    @FXML
    Pane admin_manage_pane;
    @FXML
    TabPane info_pane;
    @FXML
    TableView admin_list;
    @FXML
    TableView table_doc;
    @FXML
    GridPane change_pwd_pane;
    @FXML
    Pane changeandview_pane;
    @FXML
    GridPane add_pane;
    @FXML
    TextField new_admin_account;
    @FXML
    PasswordField new_admin_pwd;
    @FXML
    Label admin_id;
    @FXML
    TextField old_pwd;
    @FXML
    PasswordField new_pwd;
    @FXML
    ChoiceBox<String> choice_search;
    @FXML
    TextField searchfield_doc;

    int admin_account;
    String admin_pwd;
    public String logintime;

    @FXML Spinner<Integer> age_again;
    @FXML TextField pwd_again;
    @FXML ComboBox<String> depart_again;
    @FXML ComboBox<String> rank_again;
    @FXML ComboBox<String> on_duty_again;
    @FXML ComboBox<String> off_duty_again;

    @FXML Spinner<Integer> pat_age_again;
    @FXML ComboBox<String> pat_gender_again;
    @FXML TextField pat_pwd_again;
    @FXML TextField pat_tel_again;
    @FXML TextField pat_name_again;
    @FXML TableView table_mr;

    @FXML TableView table_medicine;
    @FXML GridPane systemsetting_pane;
    @FXML private TabPane chart_pane;
    @FXML private ComboBox chooseMedicine;
    @FXML private ComboBox chooseMethod;
    @FXML private ListView medicineList;
    @FXML private PieChart medPieChart;
    @FXML private BarChart medBarChart;
    @FXML private ComboBox chooseDepartment;
    @FXML private ComboBox chooseMethod1;
    @FXML private ListView departmentList;
    @FXML private PieChart depPieChart;
    @FXML private BarChart depBarChart;
    PatientInfo patientInfo = new PatientInfo();
    Doctorinfo doctorinfo = new Doctorinfo();
    MedicineInfo medicineInfo = new MedicineInfo();
    private List<String> medChooseList  = new ArrayList<String>();
    private List<String> depChooseList = new ArrayList<String>();
    @FXML Label tip;

    public void init(){

        tip.setText("欢迎您！ 管理员： " + admin_account + "       " + logintime);
        showMR();
        showMedicine();
        showDoc();
        showPat();
        admin_manage_pane.setVisible(true);
        info_pane.setVisible(false);
        chart_pane.setVisible(false);
        systemsetting_pane.setVisible(false);
        final ObservableList<Data.Admin> admin_data = FXCollections.observableArrayList();
        ObservableList<TableColumn> admin_data_listColumns = admin_list.getColumns();

        AdminInfoDao adminInfoDao = new AdminInfoDao();
        ResultSet rs = adminInfoDao.AdminFillTable();
        try {
            while (rs.next()) {
                Data a = new Data();
                Data.Admin b = a.new Admin();
                b.setAdminAccount(rs.getInt(1));
                b.setAdminPassword(rs.getString(2));
                admin_data.add(b);
                admin_data_listColumns.get(0).setCellValueFactory(new PropertyValueFactory("adminAccount"));
                admin_data_listColumns.get(1).setCellValueFactory(new PropertyValueFactory("adminPassword"));
                admin_list.setItems(admin_data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdminInfoDao admindao = new AdminInfoDao();
        List<String> methodList = new ArrayList<String>();
        methodList.add("柱状图");
        methodList.add("饼状图");
        ObservableList<String> oMethodList = FXCollections.observableList(methodList);
        //销量统计界面初始化
        List<String> list1 = admindao.selectMedicineInfo();
        ObservableList<String> oList1 = FXCollections.observableList(list1);
        try {
            chooseMedicine.setItems(oList1);
            chooseMethod.setItems(oMethodList);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        medPieChart.setTitle("各药品销量对比");
        medBarChart.setTitle("各药品销量对比");
        medBarChart.setVisible(false);
        medPieChart.setVisible(false);
        //科室就诊数界面初始化
        List<String> list2 = admindao.selectDepartmentInfo();
        ObservableList oList2 = FXCollections.observableList(list2);
        try {
            chooseDepartment.setItems(oList2);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error");
        }
        chooseMethod1.setItems(oMethodList);
        depPieChart.setTitle("各科室就诊数对比");
        depPieChart.setVisible(false);
        depBarChart.setVisible(false);
    }

    public void OnInitDataBase(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"确认初始化数据库？",ButtonType.YES,ButtonType.NO);
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION,"初始化数据库成功！");
            alert1.show();
        }
    }

    public static void showTimedDialog(long time, String message) {
        Stage popup = new Stage();
        popup.setAlwaysOnTop(true);
        popup.initModality(Modality.APPLICATION_MODAL);
        Button closeBtn = new Button("知道了");
        closeBtn.setOnAction(e -> {
            popup.close();
        });
        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setSpacing(20);
        root.getChildren().addAll(new Label(message), closeBtn);
        Scene scene = new Scene(root);
        popup.setScene(scene);
        popup.setTitle("提示信息");
        popup.show();

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(time);
                if (popup.isShowing()) {
                    Platform.runLater(() -> popup.close());
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void OnRecoverDatabase(ActionEvent actionEvent){
        //TODO:数据库备份
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"确认备份数据库？",ButtonType.YES,ButtonType.NO);
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            showTimedDialog(2000,"正在备份，请稍后");

            String desPathStr = "C:\\Users\\ZZY\\Desktop"; //目标文件地址
            String srcPathStr = "D:\\MS\\medicalsystem.sql"; //源文件地址
            copyFile(srcPathStr, desPathStr);
        }
    }

    private static void copyFile(String srcPathStr, String desPathStr) {
        //1.获取源文件的名称
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("\\")+1); //目标文件地址
        System.out.println(newFileName);
        desPathStr = desPathStr + File.separator + newFileName; //源文件地址
        System.out.println(desPathStr);

        try{
            //2.创建输入输出流对象
            FileInputStream fis = new FileInputStream(srcPathStr);
            FileOutputStream fos = new FileOutputStream(desPathStr);

            //创建搬运工具
            byte datas[] = new byte[1024*8];
            //创建长度
            int len = 0;
            //循环读取数据
            while((len = fis.read(datas))!=-1){
                fos.write(datas,0,len);
            }
            //3.释放资源
            fis.close();
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void OnClickSystemSetting(ActionEvent actionEvent){
        systemsetting_pane.setVisible(true);
        info_pane.setVisible(false);
        admin_manage_pane.setVisible(false);
        chart_pane.setVisible(false);
    }

    public void OnClickOperation(ActionEvent actionEvent) {
        //TODO:点击操作日志
        try {
            Stage patStage = new Stage();
            URL location = getClass().getResource("/ui/operateDaily.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent root = fxmlLoader.load();
            patStage.setTitle("操作日志");
            Scene scene = new Scene(root);
            patStage.setScene(scene);
            operateDailyController controller = fxmlLoader.getController();   //获取Controller的实例对象
            controller.init(admin_account,logintime);
            patStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
//        Stage daily = new Stage();
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/operateDaily.fxml"));
//            operateDailyController a = new operateDailyController();
//            a.init(admin_account,logintime);
//            loader.setController(a);
//            Pane root = (Pane)loader.load();
//            daily.setTitle("操作日志");
//            daily.setScene(new Scene(root));
//            daily.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void OnLookHospital(ActionEvent actionEvent){
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://www.fjxiehe.com/article/single.jsp?mid=185&parentmid=184");
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

    public void OnLookHowToUse(ActionEvent actionEvent){
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler https://www.yiibai.com/javafx/javafx-tutorial-for-beginners.html");
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

    public void OnGiveAdvice(ActionEvent actionEvent) throws java.io.IOException{
        Stage advice = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/ui/giveAdvice.fxml"));
        advice.setScene(new Scene(root));
        advice.setTitle("意见反馈");
        advice.show();
    }

    public void OnClickEditDoc(ActionEvent actionEvent){
        if (table_doc.getSelectionModel().isEmpty())
        {
            Alert alert_1 = new Alert(Alert.AlertType.WARNING,"请选择要修改的信息！");
            alert_1.show();
        }
        else {
            doc_func_pane.setVisible(false);
            edit_doc_pane.setVisible(true);

            int index = table_doc.getSelectionModel().getSelectedIndex();
            String docid = String.valueOf(((Data.Doctor)table_doc.getItems().get(index)).getDocId());
            doctorinfo = DoctorinfoDao.searchDoctor(docid);
            age_again.increment(doctorinfo.getDocAge()-1);
            pwd_again.setText(doctorinfo.getDocPwd());
            depart_again.getSelectionModel().select(doctorinfo.getDocDepartment());
            rank_again.getSelectionModel().select(doctorinfo.getDocRank());
            on_duty_again.getSelectionModel().select(doctorinfo.getOnDuty());
            off_duty_again.getSelectionModel().select(doctorinfo.getOffDuty());
        }
    }

    @FXML HBox edit_medicine_pane;
    @FXML Pane med_func_pane;
    @FXML TextField med_name_edit;
    @FXML TextField med_price_edit;
    @FXML ComboBox<String> med_sort_edit;
    @FXML Spinner<Integer> med_store_edit;

    public void OnClickEditMedicine(ActionEvent actionEvent){
        if (table_medicine.getSelectionModel().isEmpty())
        {
            Alert alert_1 = new Alert(Alert.AlertType.WARNING,"请选择要修改的信息！");
            alert_1.show();
        }
        else
        {
            med_func_pane.setVisible(false);
            edit_medicine_pane.setVisible(true);

            int index = table_medicine.getSelectionModel().getSelectedIndex();
            String medid= String.valueOf(((Data.Medicine)table_medicine.getItems().get(index)).getMedNumber());
            medicineInfo = MedicineDao.searchMed(medid);

            med_name_edit.setText(medicineInfo.getMedName());
            med_price_edit.setText(String.valueOf(medicineInfo.getMedPrice()));
            med_sort_edit.getSelectionModel().select(medicineInfo.getMedCategory());
            med_store_edit.increment(medicineInfo.getMedStore()-1);
        }
    }

    public void OnClickEditPat(ActionEvent actionEvent){

        if (table_pat.getSelectionModel().isEmpty())
        {
            Alert alert_1 = new Alert(Alert.AlertType.WARNING,"请选择要修改的信息！");
            alert_1.show();
        }
        else{
            pat_func_pane.setVisible(false);
            edit_pat_pane.setVisible(true);

            int index = table_pat.getSelectionModel().getSelectedIndex();
            String patid = String.valueOf(((Data.Patient)table_pat.getItems().get(index)).getPatId());
            patientInfo = PatientInfoDao.searchPatient(patid);
            pat_pwd_again.setText(patientInfo.getPatPwd());
            pat_tel_again.setText(patientInfo.getPatTel());
            pat_name_again.setText(patientInfo.getPatName());
            pat_gender_again.getSelectionModel().select(patientInfo.getPatGender());
            pat_age_again.increment(patientInfo.getPatAge()-1);
        }
    }

    public void OnRefreshAll(ActionEvent actionEvent){
        showDoc();
        showPat();
        showMR();
        showMedicine();
    }

    public void OnOkEditMed(ActionEvent actionEvent){//TODO:修改药品
        if(!med_name_edit.getText().equals(medicineInfo.getMedName()))
        {
            medicineInfo.setMedName(med_name_edit.getText());
        }
        if (!med_price_edit.getText().equals(String.valueOf(medicineInfo.getMedPrice())))
        {
            medicineInfo.setMedPrice(Double.parseDouble(med_price_edit.getText()));
        }
        if (! (med_store_edit.getValue() == medicineInfo.getMedStore()))
        {
            medicineInfo.setMedStore(med_store_edit.getValue());
        }
        if (!med_sort_edit.getValue().equals(medicineInfo.getMedCategory()))
        {
            medicineInfo.setMedCategory(med_sort_edit.getValue());
        }
        MedicineDao.changeMedInfo(medicineInfo);
        Alert alert_2 = new Alert(Alert.AlertType.INFORMATION,"修改成功！");
        alert_2.show();
        showMedicine();
        med_func_pane.setVisible(true);
        edit_medicine_pane.setVisible(false);
        med_sort_edit.getSelectionModel().clearSelection();
        med_price_edit.clear();
        med_name_edit.clear();
        med_store_edit.decrement(med_store_edit.getValue()-1);
    }

    public void OnEditPatAgain(ActionEvent actionEvent){
            if (!pat_pwd_again.getText().equals(patientInfo.getPatPwd()))
            {
                patientInfo.setPatPwd(pat_pwd_again.getText());
            }

            if (!pat_gender_again.getValue().equals(patientInfo.getPatGender()))
            {
                patientInfo.setPatGender(pat_gender_again.getValue());
            }

            if (!pat_tel_again.getText().equals(patientInfo.getPatTel()))
            {
                patientInfo.setPatTel(pat_tel_again.getText());
            }
            if (!pat_name_again.getText().equals(patientInfo.getPatName()))
            {
                patientInfo.setPatName(pat_name_again.getText());
            }
            if (!pat_age_again.getValue().equals(patientInfo.getPatAge()))
            {
                patientInfo.setPatAge(pat_age_again.getValue());
            }

            PatientInfoDao.changePatInfo(patientInfo);
            Alert alert_2 = new Alert(Alert.AlertType.INFORMATION,"修改成功！");
            alert_2.show();
            pat_name_again.clear();
            pat_tel_again.clear();
            pat_gender_again.getSelectionModel().clearSelection();
            pat_pwd_again.clear();
            pat_age_again.decrement(pat_age_again.getValue()-1);
            showPat();

            pat_func_pane.setVisible(true);
            edit_pat_pane.setVisible(false);
        }


    public void OnEditDocAgain(ActionEvent actionEvent){

            if (!pwd_again.getText().equals(doctorinfo.getDocPwd()))
            {
                doctorinfo.setDocPwd(pwd_again.getText());
            }
            if (!depart_again.getValue().equals(doctorinfo.getDocDepartment()))
            {
                doctorinfo.setDocDepartment(depart_again.getValue());
            }
            if (!rank_again.getValue().equals(doctorinfo.getDocRank()))
            {
                doctorinfo.setDocRank(rank_again.getValue());
            }
            if (!on_duty_again.getValue().equals(doctorinfo.getOnDuty()))
            {
                doctorinfo.setOnDuty(on_duty_again.getValue());
            }
            if (!off_duty_again.getValue().equals(doctorinfo.getOffDuty()))
            {
                doctorinfo.setOffDuty(off_duty_again.getValue());
            }


            DoctorinfoDao.changeDocInfo(doctorinfo);
            Alert alert_2 = new Alert(Alert.AlertType.INFORMATION,"修改成功！");
            alert_2.show();
            age_again.decrement(age_again.getValue()-1);
            pwd_again.clear();
            depart_again.getSelectionModel().clearSelection();
            rank_again.getSelectionModel().clearSelection();
            on_duty_again.getSelectionModel().clearSelection();
            off_duty_again.getSelectionModel().clearSelection();
            showDoc();
            doc_func_pane.setVisible(true);
            edit_doc_pane.setVisible(false);
    }

    public void OnEditCancelPat(ActionEvent actionEvent){
        pat_func_pane.setVisible(true);
        edit_pat_pane.setVisible(false);
        systemsetting_pane.setVisible(false);
    }

    public void OnCancelEdit(ActionEvent actionEvent)
    {
        doc_func_pane.setVisible(true);
        edit_doc_pane.setVisible(false);
    }

    public void setInfo(int a, String b) {
        admin_account = a;
        admin_pwd = b;
    }

    public void SearchInfo(ActionEvent actionEvent) {
        info_search.setVisible(true);
    }

    public void adminManageExit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "确认退出系统？", ButtonType.YES, ButtonType.NO);
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }
    }

    public void OnAdminManagePane(ActionEvent actionEvent) {
        admin_manage_pane.setVisible(true);
        info_pane.setVisible(false);
        chart_pane.setVisible(false);
        systemsetting_pane.setVisible(false);
    }

    public void OnSearchInfoPane(ActionEvent actionEvent) {//TODO:修改tabpane
        admin_manage_pane.setVisible(false);
        info_pane.setVisible(true);
        chart_pane.setVisible(false);
        systemsetting_pane.setVisible(false);
        if (pat_info.isSelected()) { showPat(); }
        else if (doc_info.isSelected()) { showDoc(); }
    }

    public void OnChangePwd(ActionEvent actionEvent) {
        add_pane.setVisible(false);
        changeandview_pane.setVisible(false);
        change_pwd_pane.setVisible(true);
        admin_id.setText(String.valueOf(admin_account));
    }

    public void OnAddAdmin(ActionEvent actionEvent) {
        add_pane.setVisible(true);
        changeandview_pane.setVisible(false);
        change_pwd_pane.setVisible(false);
    }

    public void OnExitAdmin(ActionEvent actionEvent) throws java.io.IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "确定退出该账号？", ButtonType.YES, ButtonType.NO);
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            Stage mainLogin = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/ui/MainLoginFXML.fxml"));
            mainLogin.setTitle("医疗管理信息系统");
            mainLogin.setScene(new Scene(root, 1080, 720));
            mainLogin.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }
    }

    public void OnOkChangePwd(ActionEvent actionEvent) {
        if (!old_pwd.getText().isEmpty() && !new_pwd.getText().isEmpty()) {
            //原始账号和密码登录时已经传入
            if (old_pwd.getText().equals(admin_pwd)) {
                if (!old_pwd.getText().equals(new_pwd.getText())) {
                    Admininfo admininfo = new Admininfo(admin_account, new_pwd.getText());
                    AdminInfoDao adminInfoDao = new AdminInfoDao();
                    adminInfoDao.AdminPwdChange(admininfo);
                    Alert alert_1 = new Alert(Alert.AlertType.INFORMATION, "修改成功！");
                    admin_pwd = new_pwd.getText();
                    changeandview_pane.setVisible(true);
                    change_pwd_pane.setVisible(false);
                    alert_1.show();
                    old_pwd.clear();
                    new_pwd.clear();

                    final ObservableList<Data.Admin> admin_data = FXCollections.observableArrayList();
                    ObservableList<TableColumn> admin_data_listColumns = admin_list.getColumns();

                    ResultSet rs = AdminInfoDao.AdminFillTable();
                    try {
                        while (rs.next()) {
                            Data a = new Data();
                            Data.Admin b = a.new Admin();
                            b.setAdminAccount(rs.getInt(1));
                            b.setAdminPassword(rs.getString(2));
                            admin_data.add(b);
                            admin_data_listColumns.get(0).setCellValueFactory(new PropertyValueFactory("adminAccount"));
                            admin_data_listColumns.get(1).setCellValueFactory(new PropertyValueFactory("adminPassword"));
                            admin_list.setItems(admin_data);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    Alert alert_3 = new Alert(Alert.AlertType.WARNING, "与原始密码重复！");
                    alert_3.show();
                }
            } else {
                Alert alert_2 = new Alert(Alert.AlertType.WARNING, "原始密码错误！");
                alert_2.show();
                old_pwd.clear();
                new_pwd.clear();
            }
        } else {
            Alert alert_3 = new Alert(Alert.AlertType.WARNING, "密码均不能为空！");
            alert_3.show();
            old_pwd.clear();
            new_pwd.clear();
        }
    }

    public void OnCancelAdmin(ActionEvent actionEvent) {
        add_pane.setVisible(false);
        changeandview_pane.setVisible(true);
        change_pwd_pane.setVisible(false);
    }

    public void OnDelAdmin(ActionEvent actionEvent) {

        Alert alert_1 = new Alert(Alert.AlertType.CONFIRMATION, "确认注销账号？", ButtonType.YES, ButtonType.NO);
        Optional result = alert_1.showAndWait();
        if (result.get() == ButtonType.YES) {
            AdminInfoDao adminInfoDao = new AdminInfoDao();
            Admininfo admin = new Admininfo(admin_account, admin_pwd);
            adminInfoDao.AdminDel(admin);
            Alert alert_2 = new Alert(Alert.AlertType.INFORMATION, "注销成功！");
            alert_2.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }
    }

    public void OnAddNewAdmin(ActionEvent actionEvent) {
        if (!new_admin_account.getText().isEmpty() && !new_admin_pwd.getText().isEmpty()) {
            Admininfo admin = new Admininfo(Integer.parseInt(new_admin_account.getText()), new_admin_pwd.getText());
            AdminInfoDao adminInfoDao = new AdminInfoDao();

            if (adminInfoDao.AdminSearchNoRepeat(admin)) {
                adminInfoDao.AdminInsert(admin);
                Alert alert_1 = new Alert(Alert.AlertType.INFORMATION, "添加成功！");
                final ObservableList<Data.Admin> admin_data = FXCollections.observableArrayList();
                ObservableList<TableColumn> admin_data_listColumns = admin_list.getColumns();

                ResultSet rs = adminInfoDao.AdminFillTable();
                try {
                    while (rs.next()) {
                        Data a = new Data();
                        Data.Admin b = a.new Admin();
                        b.setAdminAccount(rs.getInt(1));
                        b.setAdminPassword(rs.getString(2));
                        admin_data.add(b);
                        admin_data_listColumns.get(0).setCellValueFactory(new PropertyValueFactory("adminAccount"));
                        admin_data_listColumns.get(1).setCellValueFactory(new PropertyValueFactory("adminPassword"));
                        admin_list.setItems(admin_data);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                changeandview_pane.setVisible(true);
                add_pane.setVisible(false);
                alert_1.show();new_admin_account.clear();new_admin_pwd.clear();

            } else {
                Alert alert_2 = new Alert(Alert.AlertType.WARNING, "账号已存在！不能重复添加！");
                alert_2.show();
                new_admin_account.clear();new_admin_pwd.clear();
            }
        } else {
            Alert alert_3 = new Alert(Alert.AlertType.WARNING, "账号密码均不能为空！");
            alert_3.show();
            new_admin_account.clear();new_admin_pwd.clear();
        }
    }

    public void OnAddNewDoc(ActionEvent actionEvent) {
        try {
            Stage doc_add = new Stage();
            URL location = getClass().getResource("/ui/addDocInfo.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent root = fxmlLoader.load();
            addDocInfoController controller = fxmlLoader.getController();
            controller.init();

            doc_add.setScene(new Scene(root));
            doc_add.setTitle("添加新医生");
            doc_add.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OnAddNewMedicine(ActionEvent actionEvent) throws java.io.IOException {
            Stage add_med = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/ui/addMedInfo.fxml"));
            add_med.setScene(new Scene(root));
            add_med.setTitle("添加药品");
            add_med.show();
    }

    public void OnViewAdmin(ActionEvent actionEvent) {
        add_pane.setVisible(false);
        changeandview_pane.setVisible(true);
        change_pwd_pane.setVisible(false);

        final ObservableList<Data.Admin> admin_data = FXCollections.observableArrayList();
        ObservableList<TableColumn> admin_data_listColumns = admin_list.getColumns();

        AdminInfoDao adminInfoDao = new AdminInfoDao();
        ResultSet rs = adminInfoDao.AdminFillTable();
        try {
            while (rs.next()) {
                Data a = new Data();
                Data.Admin b = a.new Admin();
                b.setAdminAccount(rs.getInt(1));
                b.setAdminPassword(rs.getString(2));
                admin_data.add(b);
                admin_data_listColumns.get(0).setCellValueFactory(new PropertyValueFactory("adminAccount"));
                admin_data_listColumns.get(1).setCellValueFactory(new PropertyValueFactory("adminPassword"));
                admin_list.setItems(admin_data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMedicine(){
        ResultSet rs = MedicineDao.MedicineFillTable();
        final ObservableList<Data.Medicine> medicine_data = FXCollections.observableArrayList();
        ObservableList<TableColumn> medicine_data_listColumn = table_medicine.getColumns();
        try {
            while (rs.next()){
                MedicineInfo medicineInfo = new MedicineInfo(rs.getInt(1),rs.getString(2)
                        ,rs.getDouble(3),rs.getNString(4),rs.getInt(5));
                Data a = new Data();
                Data.Medicine b = a.new Medicine();
                b.setAll(medicineInfo);
                medicine_data.add(b);
                medicine_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("medNumber"));
                medicine_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("medName"));
                medicine_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("medPrice"));
                medicine_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("medCategory"));
                medicine_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("medStore"));
                table_medicine.setItems(medicine_data);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showDoc() {
        ResultSet rs = DoctorinfoDao.DoctorFillTable();
        final ObservableList<Data.Doctor> doc_data = FXCollections.observableArrayList();
        ObservableList<TableColumn> doc_data_listColumn = table_doc.getColumns();

        try {
            while (rs.next()) {
                Doctorinfo docinfo = new Doctorinfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                Data a = new Data();
                Data.Doctor b = a.new Doctor();
                b.setAll(docinfo);
                doc_data.add(b);
                doc_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("docId"));
                doc_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("docName"));
                doc_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("docGender"));
                doc_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("docAge"));
                doc_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("docPwd"));
                doc_data_listColumn.get(5).setCellValueFactory(new PropertyValueFactory("docDepartment"));
                doc_data_listColumn.get(6).setCellValueFactory(new PropertyValueFactory("docRank"));
                doc_data_listColumn.get(7).setCellValueFactory(new PropertyValueFactory("docTel"));
                doc_data_listColumn.get(8).setCellValueFactory(new PropertyValueFactory("onDuty"));
                doc_data_listColumn.get(9).setCellValueFactory(new PropertyValueFactory("offDuty"));
                table_doc.setItems(doc_data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OnDelNewDoc(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "是否注销该医生？", ButtonType.YES, ButtonType.NO);
        Optional result = alert.showAndWait();

        if (result.get() == ButtonType.YES) {
            int index = table_doc.getSelectionModel().getSelectedIndex();
            Doctorinfo doctorinfo = new Doctorinfo();
            doctorinfo.setDocId(((Data.Doctor)table_doc.getItems().get(index)).getDocId());
            DoctorinfoDao.delDoctor(doctorinfo);
            table_doc.getItems().remove(index);
        }
    }

    public void OnDelMed(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "是否删除该药品？", ButtonType.YES, ButtonType.NO);
        Optional result = alert.showAndWait();

        if (result.get() == ButtonType.YES)
        {
            int index = table_medicine.getSelectionModel().getSelectedIndex();
            MedicineInfo medicineInfo = new MedicineInfo();
            medicineInfo.setMedNumber(((Data.Medicine)table_medicine.getItems().get(index)).getMedNumber());
            MedicineDao.delMedicine(medicineInfo);
            table_medicine.getItems().remove(index);
        }
    }

    public void OnDelPat(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "是否注销该患者？", ButtonType.YES, ButtonType.NO);
        Optional result = alert.showAndWait();

        if (result.get() == ButtonType.YES)
        {
            int index = table_pat.getSelectionModel().getSelectedIndex();
            System.out.println(index);
            PatientInfo a = new PatientInfo();
            String patid = String.valueOf(((Data.Patient)table_pat.getItems().get(index)).getPatId());
            System.out.println(patid);
            a.setPatId(((Data.Patient)table_pat.getItems().get(index)).getPatId());
            PatientInfoDao.delPatient(a);
            table_pat.getItems().remove(index);
        }
    }
    public void OnFreshDoc(ActionEvent actionEvent){ showDoc();searchfield_doc.clear(); }
    public void OnFreshPat(ActionEvent actionEvent){ showPat(); pat_text.clear();}
    public void OnFreshMR(ActionEvent actionEvent){ showMR();mrsearch.clear();}
    public void OnFreshMedicine(ActionEvent actionEvent){ showMedicine(); medicine_text.clear();}

    @FXML CheckBox chinese;
    @FXML CheckBox western;
    public void OnSearchMedicine(ActionEvent actionEvent){
        String keyword = medicine_combobox.getValue();
        if (!medicine_combobox.getSelectionModel().isEmpty() || !medicine_text.getText().isEmpty())
        {
            if (keyword.equals("药品编号"))
            {
                ResultSet rs = MedicineDao.selectMedFromId(medicine_text.getText());
                final ObservableList<Data.Medicine> medicine_data = FXCollections.observableArrayList();
                ObservableList<TableColumn> medicine_data_listColumn = table_medicine.getColumns();
                try {
                    if (rs.next())
                    {
                      while (rs.next()){
                        MedicineInfo medicineInfo = new MedicineInfo(rs.getInt(1),rs.getString(2)
                                ,rs.getDouble(3),rs.getNString(4),rs.getInt(5));
                        Data a = new Data();
                        Data.Medicine b = a.new Medicine();
                        b.setAll(medicineInfo);
                        medicine_data.add(b);
                        medicine_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("medNumber"));
                        medicine_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("medName"));
                        medicine_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("medPrice"));
                        medicine_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("medCategory"));
                        medicine_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("medStore"));
                        table_medicine.setItems(medicine_data);
                    }}
                    else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "不存在该药品！");
                        alert.show();medicine_text.clear();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
            else if (keyword.equals("药品名称"))
            {
                ResultSet rs = MedicineDao.selectMedFromName(medicine_text.getText());
                final ObservableList<Data.Medicine> medicine_data = FXCollections.observableArrayList();
                ObservableList<TableColumn> medicine_data_listColumn = table_medicine.getColumns();
                try {
                    if (rs.next())
                    {
                    while (rs.next()){
                        MedicineInfo medicineInfo = new MedicineInfo(rs.getInt(1),rs.getString(2)
                                ,rs.getDouble(3),rs.getNString(4),rs.getInt(5));
                        Data a = new Data();
                        Data.Medicine b = a.new Medicine();
                        b.setAll(medicineInfo);
                        medicine_data.add(b);
                        medicine_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("medNumber"));
                        medicine_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("medName"));
                        medicine_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("medPrice"));
                        medicine_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("medCategory"));
                        medicine_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("medStore"));
                        table_medicine.setItems(medicine_data);
                    }}
                    else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "不存在该药品！");
                        alert.show();medicine_text.clear();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else {
                if (western.isSelected() &&!chinese.isSelected())
                {
                    ResultSet rs = MedicineDao.selectMedFromCategory("西药");
                    final ObservableList<Data.Medicine> medicine_data = FXCollections.observableArrayList();
                    ObservableList<TableColumn> medicine_data_listColumn = table_medicine.getColumns();
                    try {
                        while (rs.next()){
                            MedicineInfo medicineInfo = new MedicineInfo(rs.getInt(1),rs.getString(2)
                                    ,rs.getDouble(3),rs.getNString(4),rs.getInt(5));
                            Data a = new Data();
                            Data.Medicine b = a.new Medicine();
                            b.setAll(medicineInfo);
                            medicine_data.add(b);
                            medicine_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("medNumber"));
                            medicine_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("medName"));
                            medicine_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("medPrice"));
                            medicine_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("medCategory"));
                            medicine_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("medStore"));
                            table_medicine.setItems(medicine_data);
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                else if (chinese.isSelected() && !western.isSelected())
                {
                    ResultSet rs = MedicineDao.selectMedFromCategory("中成药");
                    final ObservableList<Data.Medicine> medicine_data = FXCollections.observableArrayList();
                    ObservableList<TableColumn> medicine_data_listColumn = table_medicine.getColumns();
                    try {
                        while (rs.next()){
                            MedicineInfo medicineInfo = new MedicineInfo(rs.getInt(1),rs.getString(2)
                                    ,rs.getDouble(3),rs.getNString(4),rs.getInt(5));
                            Data a = new Data();
                            Data.Medicine b = a.new Medicine();
                            b.setAll(medicineInfo);
                            medicine_data.add(b);
                            medicine_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("medNumber"));
                            medicine_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("medName"));
                            medicine_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("medPrice"));
                            medicine_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("medCategory"));
                            medicine_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("medStore"));
                            table_medicine.setItems(medicine_data);
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                else
                {
                    showMedicine();
                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "请补全关键字进行查询！");
            alert.show();
        }
    }

    public void ChooseSort(ActionEvent actionEvent){
        if (medicine_combobox.getSelectionModel().isSelected(2))
        {
        medicine_text.setVisible(false);
        chinese.setVisible(true);
        western.setVisible(true);
       }
    }

    public void OnSearchPat(ActionEvent actionEvent){
        String keyword = pat_choice.getValue();
        if (!pat_text.getText().isEmpty() && !pat_choice.getSelectionModel().isEmpty())
        {
            if (keyword.equals("卡号"))
            {
                ResultSet rs = PatientInfoDao.selectPatFromId(pat_text.getText());
                final ObservableList<Data.Patient> pat_data = FXCollections.observableArrayList();
                ObservableList<TableColumn> pat_data_listColumn = table_pat.getColumns();
                try {
                    if (rs.next()){
                    while (rs.next()) {
                        PatientInfo patientInfo = new PatientInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getFloat(6), rs.getString(7), rs.getString(8));
                        Data a = new Data();
                        Data.Patient b = a.new Patient();
                        b.setAll(patientInfo);
                        pat_data.add(b);
                        pat_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("patId"));
                        pat_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("patName"));
                        pat_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("patGender"));
                        pat_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("patAge"));
                        pat_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("patPwd"));
                        pat_data_listColumn.get(5).setCellValueFactory(new PropertyValueFactory("patDeposit"));
                        pat_data_listColumn.get(6).setCellValueFactory(new PropertyValueFactory("patDate"));
                        pat_data_listColumn.get(7).setCellValueFactory(new PropertyValueFactory("patTel"));
                        table_pat.setItems(pat_data);
                    }}
                    else {
                        Alert alert_1 = new Alert(Alert.AlertType.WARNING, "不存在该卡号的患者！");
                        alert_1.show();pat_text.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (keyword.equals("姓名"))
            {
                ResultSet rs = PatientInfoDao.selectPatFromName(pat_text.getText());
                final ObservableList<Data.Patient> pat_data = FXCollections.observableArrayList();
                ObservableList<TableColumn> pat_data_listColumn = table_pat.getColumns();
                try {
                    if (rs.next()){
                    while (rs.next()) {
                        PatientInfo patientInfo = new PatientInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getFloat(6), rs.getString(7), rs.getString(8));
                        Data a = new Data();
                        Data.Patient b = a.new Patient();
                        b.setAll(patientInfo);
                        pat_data.add(b);
                        pat_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("patId"));
                        pat_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("patName"));
                        pat_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("patGender"));
                        pat_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("patAge"));
                        pat_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("patPwd"));
                        pat_data_listColumn.get(5).setCellValueFactory(new PropertyValueFactory("patDeposit"));
                        pat_data_listColumn.get(6).setCellValueFactory(new PropertyValueFactory("patDate"));
                        pat_data_listColumn.get(7).setCellValueFactory(new PropertyValueFactory("patTel"));
                        table_pat.setItems(pat_data);
                    }}
                    else {
                        Alert alert_1 = new Alert(Alert.AlertType.WARNING, "不存在该姓名的患者！");
                        alert_1.show();pat_text.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                ResultSet rs = PatientInfoDao.selectPatFromTel(pat_text.getText());
                final ObservableList<Data.Patient> pat_data = FXCollections.observableArrayList();
                ObservableList<TableColumn> pat_data_listColumn = table_pat.getColumns();
                try {
                    if (rs.next()){
                    while (rs.next()) {
                        PatientInfo patientInfo = new PatientInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getFloat(6), rs.getString(7), rs.getString(8));
                        Data a = new Data();
                        Data.Patient b = a.new Patient();
                        b.setAll(patientInfo);
                        pat_data.add(b);
                        pat_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("patId"));
                        pat_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("patName"));
                        pat_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("patGender"));
                        pat_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("patAge"));
                        pat_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("patPwd"));
                        pat_data_listColumn.get(5).setCellValueFactory(new PropertyValueFactory("patDeposit"));
                        pat_data_listColumn.get(6).setCellValueFactory(new PropertyValueFactory("patDate"));
                        pat_data_listColumn.get(7).setCellValueFactory(new PropertyValueFactory("patTel"));
                        table_pat.setItems(pat_data);
                    }}
                    else {
                        Alert alert_1 = new Alert(Alert.AlertType.WARNING, "不存在该联系方式的患者！");
                        alert_1.show();pat_text.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "请补全关键字进行查询！");
            alert.show();
        }
    }

    public void OnSearchMRBtn(ActionEvent actionEvent){
        String keyword = mr_choice.getValue();
        if (!mrsearch.getText().isEmpty()  && !mr_choice.getSelectionModel().isEmpty()){
            if (keyword.equals("病历编号"))
            {
                ResultSet rs = MedicalRecordDao.selectFromId(mrsearch.getText());
                final ObservableList<Data.MedicalRecord> mr_data = FXCollections.observableArrayList();
                ObservableList<TableColumn> mr_data_listColumn = table_mr.getColumns();
                try {
                    if (rs.next()){
                    while (rs.next()) {
                        Medicalrecordsinfo medicalrecordsinfo = new Medicalrecordsinfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getDouble(8),rs.getString(9),rs.getString(10),rs.getString(11));
                        Data a = new Data();
                        Data.MedicalRecord b = a.new MedicalRecord();
                        b.setAll(medicalrecordsinfo);
                        mr_data.add(b);
                        mr_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("recordId"));
                        mr_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("createDate"));
                        mr_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("curDepartment"));
                        mr_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("curDoctor"));
                        mr_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("patId1"));
                        mr_data_listColumn.get(5).setCellValueFactory(new PropertyValueFactory("Result"));
                        mr_data_listColumn.get(6).setCellValueFactory(new PropertyValueFactory("patConsumption"));
                        mr_data_listColumn.get(7).setCellValueFactory(new PropertyValueFactory("isInHospital"));
                        mr_data_listColumn.get(8).setCellValueFactory(new PropertyValueFactory("inDate"));
                        mr_data_listColumn.get(9).setCellValueFactory(new PropertyValueFactory("outDate"));
                        table_mr.setItems(mr_data);
                    }}
                    else {
                        Alert alert_1 = new Alert(Alert.AlertType.WARNING, "不存在该病历号！");
                        alert_1.show();mrsearch.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (keyword.equals("就诊科室")){
                ResultSet rs = MedicalRecordDao.selectFromDepart(mrsearch.getText());
                final ObservableList<Data.MedicalRecord> mr_data = FXCollections.observableArrayList();
                ObservableList<TableColumn> mr_data_listColumn = table_mr.getColumns();
                try {
                    if (rs.next()){
                    while (rs.next()) {
                        Medicalrecordsinfo medicalrecordsinfo = new Medicalrecordsinfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getDouble(8),rs.getString(9),rs.getString(10),rs.getString(11));
                        Data a = new Data();
                        Data.MedicalRecord b = a.new MedicalRecord();
                        b.setAll(medicalrecordsinfo);
                        mr_data.add(b);
                        mr_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("recordId"));
                        mr_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("createDate"));
                        mr_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("curDepartment"));
                        mr_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("curDoctor"));
                        mr_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("patId1"));
                        mr_data_listColumn.get(5).setCellValueFactory(new PropertyValueFactory("Result"));
                        mr_data_listColumn.get(6).setCellValueFactory(new PropertyValueFactory("patConsumption"));
                        mr_data_listColumn.get(7).setCellValueFactory(new PropertyValueFactory("isInHospital"));
                        mr_data_listColumn.get(8).setCellValueFactory(new PropertyValueFactory("inDate"));
                        mr_data_listColumn.get(9).setCellValueFactory(new PropertyValueFactory("outDate"));
                        table_mr.setItems(mr_data);
                    }}
                    else {
                        Alert alert_1 = new Alert(Alert.AlertType.WARNING, "不存在该诊室！！");
                        alert_1.show();mrsearch.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (keyword.equals("就诊医生"))
            {
                ResultSet rs = MedicalRecordDao.selectFromDoc(mrsearch.getText());
                final ObservableList<Data.MedicalRecord> mr_data = FXCollections.observableArrayList();
                ObservableList<TableColumn> mr_data_listColumn = table_mr.getColumns();
                try {
                    if (rs.next()){
                    while (rs.next()) {
                        Medicalrecordsinfo medicalrecordsinfo = new Medicalrecordsinfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getDouble(8),rs.getString(9),rs.getString(10),rs.getString(11));
                        Data a = new Data();
                        Data.MedicalRecord b = a.new MedicalRecord();
                        b.setAll(medicalrecordsinfo);
                        mr_data.add(b);
                        mr_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("recordId"));
                        mr_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("createDate"));
                        mr_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("curDepartment"));
                        mr_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("curDoctor"));
                        mr_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("patId1"));
                        mr_data_listColumn.get(5).setCellValueFactory(new PropertyValueFactory("Result"));
                        mr_data_listColumn.get(6).setCellValueFactory(new PropertyValueFactory("patConsumption"));
                        mr_data_listColumn.get(7).setCellValueFactory(new PropertyValueFactory("isInHospital"));
                        mr_data_listColumn.get(8).setCellValueFactory(new PropertyValueFactory("inDate"));
                        mr_data_listColumn.get(9).setCellValueFactory(new PropertyValueFactory("outDate"));
                        table_mr.setItems(mr_data);
                    }}
                    else {
                        Alert alert_1 = new Alert(Alert.AlertType.WARNING, "不存在该医生！");
                        alert_1.show();mrsearch.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
            {
                ResultSet rs = MedicalRecordDao.selectFromPat(mrsearch.getText());
                final ObservableList<Data.MedicalRecord> mr_data = FXCollections.observableArrayList();
                ObservableList<TableColumn> mr_data_listColumn = table_mr.getColumns();
                try {
                    if (rs.next()){
                    while (rs.next()) {
                        Medicalrecordsinfo medicalrecordsinfo = new Medicalrecordsinfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getDouble(8),rs.getString(9),rs.getString(10),rs.getString(11));
                        Data a = new Data();
                        Data.MedicalRecord b = a.new MedicalRecord();
                        b.setAll(medicalrecordsinfo);
                        mr_data.add(b);
                        mr_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("recordId"));
                        mr_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("createDate"));
                        mr_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("curDepartment"));
                        mr_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("curDoctor"));
                        mr_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("patId1"));
                        mr_data_listColumn.get(5).setCellValueFactory(new PropertyValueFactory("Result"));
                        mr_data_listColumn.get(6).setCellValueFactory(new PropertyValueFactory("patConsumption"));
                        mr_data_listColumn.get(7).setCellValueFactory(new PropertyValueFactory("isInHospital"));
                        mr_data_listColumn.get(8).setCellValueFactory(new PropertyValueFactory("inDate"));
                        mr_data_listColumn.get(9).setCellValueFactory(new PropertyValueFactory("outDate"));
                        table_mr.setItems(mr_data);
                    }}
                    else {
                        Alert alert_1 = new Alert(Alert.AlertType.WARNING, "不存在该医生！");
                        alert_1.show();mrsearch.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "请补全关键字进行查询！");
            alert.show();
        }
    }

    public void OnSearchBtn(ActionEvent actionEvent) {
        String keyword = choice_search.getValue();
        if (!searchfield_doc.getText().isEmpty() && !choice_search.getSelectionModel().isEmpty()) {
            if (keyword.equals("工号")) {
                ResultSet rs = DoctorinfoDao.selectDocFromId(searchfield_doc.getText());
                final ObservableList<Data.Doctor> doc_data = FXCollections.observableArrayList();
                ObservableList<TableColumn> doc_data_listColumn = table_doc.getColumns();
                try {
                    if (rs.next()){
                    while (rs.next()) {
                        Doctorinfo docinfo = new Doctorinfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                        Data a = new Data();
                        Data.Doctor b = a.new Doctor();
                        b.setAll(docinfo);
                        doc_data.add(b);
                        doc_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("docId"));
                        doc_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("docName"));
                        doc_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("docGender"));
                        doc_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("docAge"));
                        doc_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("docPwd"));
                        doc_data_listColumn.get(5).setCellValueFactory(new PropertyValueFactory("docDepartment"));
                        doc_data_listColumn.get(6).setCellValueFactory(new PropertyValueFactory("docRank"));
                        doc_data_listColumn.get(7).setCellValueFactory(new PropertyValueFactory("docTel"));
                        doc_data_listColumn.get(8).setCellValueFactory(new PropertyValueFactory("onDuty"));
                        doc_data_listColumn.get(9).setCellValueFactory(new PropertyValueFactory("offDuty"));
                        table_doc.setItems(doc_data);
                    }}
                    else {
                        Alert alert_1 = new Alert(Alert.AlertType.WARNING, "不存在该工号的医生！");
                        alert_1.show();searchfield_doc.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (keyword.equals("姓名")) {
                ResultSet rs = DoctorinfoDao.selectDocFromName(searchfield_doc.getText());
                final ObservableList<Data.Doctor> doc_data = FXCollections.observableArrayList();
                ObservableList<TableColumn> doc_data_listColumn = table_doc.getColumns();

                try {
                    if (rs.next()){
                    while (rs.next()) {
                        Doctorinfo docinfo = new Doctorinfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                        Data a = new Data();
                        Data.Doctor b = a.new Doctor();
                        b.setAll(docinfo);
                        doc_data.add(b);
                        doc_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("docId"));
                        doc_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("docName"));
                        doc_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("docGender"));
                        doc_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("docAge"));
                        doc_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("docPwd"));
                        doc_data_listColumn.get(5).setCellValueFactory(new PropertyValueFactory("docDepartment"));
                        doc_data_listColumn.get(6).setCellValueFactory(new PropertyValueFactory("docRank"));
                        doc_data_listColumn.get(7).setCellValueFactory(new PropertyValueFactory("docTel"));
                        doc_data_listColumn.get(8).setCellValueFactory(new PropertyValueFactory("onDuty"));
                        doc_data_listColumn.get(9).setCellValueFactory(new PropertyValueFactory("offDuty"));
                        table_doc.setItems(doc_data);
                    }}
                    else {
                        Alert alert_1 = new Alert(Alert.AlertType.WARNING, "不存在该姓名的医生！");
                        alert_1.show();searchfield_doc.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (keyword.equals("科室")) {
                ResultSet rs = DoctorinfoDao.selectDocFromDepartment(searchfield_doc.getText());
                final ObservableList<Data.Doctor> doc_data = FXCollections.observableArrayList();
                ObservableList<TableColumn> doc_data_listColumn = table_doc.getColumns();

                try {
                    if (rs.next()){
                    while (rs.next()) {
                        Doctorinfo docinfo = new Doctorinfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                        Data a = new Data();
                        Data.Doctor b = a.new Doctor();
                        b.setAll(docinfo);
                        doc_data.add(b);
                        doc_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("docId"));
                        doc_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("docName"));
                        doc_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("docGender"));
                        doc_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("docAge"));
                        doc_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("docPwd"));
                        doc_data_listColumn.get(5).setCellValueFactory(new PropertyValueFactory("docDepartment"));
                        doc_data_listColumn.get(6).setCellValueFactory(new PropertyValueFactory("docRank"));
                        doc_data_listColumn.get(7).setCellValueFactory(new PropertyValueFactory("docTel"));
                        doc_data_listColumn.get(8).setCellValueFactory(new PropertyValueFactory("onDuty"));
                        doc_data_listColumn.get(9).setCellValueFactory(new PropertyValueFactory("offDuty"));
                        table_doc.setItems(doc_data);
                    }}
                    else {
                        Alert alert_1 = new Alert(Alert.AlertType.WARNING, "不存在该科室！");
                        alert_1.show();searchfield_doc.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                ResultSet rs = DoctorinfoDao.selectDocFromRank(searchfield_doc.getText());
                final ObservableList<Data.Doctor> doc_data = FXCollections.observableArrayList();
                ObservableList<TableColumn> doc_data_listColumn = table_doc.getColumns();
                try {
                    if (rs.next()){
                    while (rs.next()) {
                        Doctorinfo docinfo = new Doctorinfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                        Data a = new Data();
                        Data.Doctor b = a.new Doctor();
                        b.setAll(docinfo);
                        doc_data.add(b);
                        doc_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("docId"));
                        doc_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("docName"));
                        doc_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("docGender"));
                        doc_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("docAge"));
                        doc_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("docPwd"));
                        doc_data_listColumn.get(5).setCellValueFactory(new PropertyValueFactory("docDepartment"));
                        doc_data_listColumn.get(6).setCellValueFactory(new PropertyValueFactory("docRank"));
                        doc_data_listColumn.get(7).setCellValueFactory(new PropertyValueFactory("docTel"));
                        doc_data_listColumn.get(8).setCellValueFactory(new PropertyValueFactory("onDuty"));
                        doc_data_listColumn.get(9).setCellValueFactory(new PropertyValueFactory("offDuty"));
                        table_doc.setItems(doc_data);
                    }}
                    else{
                        Alert alert_1 = new Alert(Alert.AlertType.WARNING, "不存在该职称！");
                        alert_1.show();searchfield_doc.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                choice_search.getSelectionModel().clearSelection();
                searchfield_doc.clear();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "请补全关键字进行查询！");
            alert.show();
        }
    }

    public void showPat() {
        ResultSet rs = PatientInfoDao.patientFillTable();
    final ObservableList<Data.Patient> pat_data = FXCollections.observableArrayList();
    ObservableList<TableColumn> pat_data_listColumn = table_pat.getColumns();
        try {
        while (rs.next()) {
            PatientInfo patientInfo = new PatientInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getFloat(6), rs.getString(7), rs.getString(8));
            Data a = new Data();
            Data.Patient b = a.new Patient();
            b.setAll(patientInfo);
            pat_data.add(b);
            pat_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("patId"));
            pat_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("patName"));
            pat_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("patGender"));
            pat_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("patAge"));
            pat_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("patPwd"));
            pat_data_listColumn.get(5).setCellValueFactory(new PropertyValueFactory("patDeposit"));
            pat_data_listColumn.get(6).setCellValueFactory(new PropertyValueFactory("patDate"));
            pat_data_listColumn.get(7).setCellValueFactory(new PropertyValueFactory("patTel"));
            table_pat.setItems(pat_data);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void showMR()//TODO:修改病历
    {
        ResultSet rs = MedicalRecordDao.MedicalRecordFillTable();
        final ObservableList<Data.MedicalRecord> mr_data = FXCollections.observableArrayList();
        ObservableList<TableColumn> mr_data_listColumn = table_mr.getColumns();
        try {
            while (rs.next()) {
                Medicalrecordsinfo medicalrecordsinfo = new Medicalrecordsinfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getDouble(8),rs.getString(9),rs.getString(10),rs.getString(11));
                Data a = new Data();
                Data.MedicalRecord b = a.new MedicalRecord();
                b.setAll(medicalrecordsinfo);
                mr_data.add(b);
                mr_data_listColumn.get(0).setCellValueFactory(new PropertyValueFactory("recordId"));
                mr_data_listColumn.get(1).setCellValueFactory(new PropertyValueFactory("createDate"));
                mr_data_listColumn.get(2).setCellValueFactory(new PropertyValueFactory("curDepartment"));
                mr_data_listColumn.get(3).setCellValueFactory(new PropertyValueFactory("curDoctor"));
                mr_data_listColumn.get(4).setCellValueFactory(new PropertyValueFactory("patDemands"));
                mr_data_listColumn.get(5).setCellValueFactory(new PropertyValueFactory("patId1"));
                mr_data_listColumn.get(6).setCellValueFactory(new PropertyValueFactory("Result"));
                mr_data_listColumn.get(7).setCellValueFactory(new PropertyValueFactory("patConsumption"));
                mr_data_listColumn.get(8).setCellValueFactory(new PropertyValueFactory("isInHospital"));
                mr_data_listColumn.get(9).setCellValueFactory(new PropertyValueFactory("inDate"));
                mr_data_listColumn.get(10).setCellValueFactory(new PropertyValueFactory("outDate"));
                table_mr.setItems(mr_data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OnReportSta(MouseEvent mouseEvent) {
        systemsetting_pane.setVisible(false);
        info_pane.setVisible(false);
        admin_manage_pane.setVisible(false);
        chart_pane.setVisible(true);
    }
    @FXML
    public void addMedicine(MouseEvent mouseEvent) {
        medChooseList.add((String) chooseMedicine.getValue());
        ObservableList<String> strList = FXCollections.observableArrayList((String) chooseMedicine.getValue());
        medicineList.getItems().add(strList);
    }


    public void deleteMedicine(MouseEvent mouseEvent) {
        medChooseList.remove(medicineList.getSelectionModel().getSelectedIndex());
        medicineList.getItems().remove(medicineList.getSelectionModel().getSelectedIndex());
    }


    public void medQuery(MouseEvent mouseEvent) {
        AdminInfoDao admindao = new AdminInfoDao();
        List<DrugSales> list = admindao.selectDrugSales(medChooseList);
        DrugSales temp;
        if (medChooseList.size() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText("信息未完善");
            alert.setContentText("请选择需要添加的药品!");
        }
        if(((String) chooseMethod.getValue()).equals("饼状图")){
            medPieChart.setVisible(true);
            medBarChart.setVisible(false);
            List<PieChart.Data> pieChartData = new ArrayList<PieChart.Data>();
            for(int i = 0; i < medChooseList.size() ; i++)
            {
                temp = list.get(i);
                pieChartData.add(new PieChart.Data(temp.getMedicineName(),Double.valueOf(temp.getSale())));
            }
            ObservableList<PieChart.Data> oPieChartData = FXCollections.observableArrayList(pieChartData);
            medPieChart.setData(oPieChartData);
        }
        else if(((String) chooseMethod.getValue()).equals("柱状图")){
            medPieChart.setVisible(false);
            medBarChart.setVisible(true);
            XYChart.Series<String, Number> barChartData = new XYChart.Series<>();
            for(int i = 0; i < medChooseList.size() ; i++)
            {
                temp = list.get(i);
                barChartData.getData().add(new XYChart.Data<>(temp.getMedicineName(),Double.valueOf(temp.getSale())));
            }
            medBarChart.getData().setAll(barChartData);
            medBarChart.setPrefWidth(medChooseList.size() * 70);
            medBarChart.setCategoryGap(10);
        }


    }

    public void addDepartment(MouseEvent mouseEvent) {
        depChooseList.add((String) chooseDepartment.getValue());
        ObservableList<String> strList = FXCollections.observableArrayList((String) chooseDepartment.getValue());
        departmentList.getItems().add(strList);
    }


    public void deleteDepartment(MouseEvent mouseEvent) {
        depChooseList.remove(departmentList.getSelectionModel().getSelectedIndex());
        departmentList.getItems().remove(departmentList.getSelectionModel().getSelectedIndex());
    }

    public void depQuery(MouseEvent mouseEvent) {
        AdminInfoDao admindao = new AdminInfoDao();
        List<depDiagnosisNumber> list = admindao.selectDepDiagnosisNumber(depChooseList);
        depDiagnosisNumber temp;
        if (((String)chooseMethod1.getValue()).equals("饼状图")){
            depPieChart.setVisible(true);
            depBarChart.setVisible(false);
            List<PieChart.Data> pieChartData = new ArrayList<PieChart.Data>();
            for(int i = 0; i < depChooseList.size() ; i++)
            {
                temp = list.get(i);
                pieChartData.add(new PieChart.Data(temp.getDepName(),Double.valueOf(temp.getDiagnosisNumber())));
            }
            ObservableList<PieChart.Data> oPieChartData = FXCollections.observableArrayList(pieChartData);
            depPieChart.setData(oPieChartData);
        }else if (((String)chooseMethod1.getValue()).equals("柱状图")){
            depBarChart.setVisible(true);
            depPieChart.setVisible(false);
            XYChart.Series<String, Number> barChartData = new XYChart.Series<>();
            for(int i = 0; i < depChooseList.size() ; i++)
            {
                temp = list.get(i);
                barChartData.getData().add(new XYChart.Data<>(temp.getDepName(),Double.valueOf(temp.getDiagnosisNumber())));
            }
            depBarChart.setPrefWidth(depChooseList.size() * 70);
            depBarChart.setCategoryGap(10);
            depBarChart.getData().setAll(barChartData);
        }
    }
}
