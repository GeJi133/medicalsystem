package controller;


import dao.*;
import entity.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainDocController {


    @FXML
    private Pane addSucPane;
    @FXML
    private Pane addpane;
    @FXML
    private Pane checkpatient;
    @FXML
    private Pane menupane;
    @FXML
    private TextField patientId;
    @FXML
    private Pane addpathis;
    @FXML
    private Label pathisId2;
    @FXML
    private Label patId2;
    @FXML
    private Label patName2;
    @FXML
    private Label patAge2;
    @FXML
    private Label patSex2;
    @FXML
    private Label patPhone2;
    @FXML
    private Label pathis2;
    @FXML
    private Label docName2;
    @FXML
    private TextField patDemand;
    @FXML
    private TextField patResult;
    @FXML
    private Pane inHospitalPane;
    @FXML
    private CheckBox inHosptalChoose;
    @FXML
    private CheckBox isMenzhen;
    @FXML
    private Label inDate;
    @FXML
    private ComboBox outDate;
    @FXML
    private ComboBox RoomId;
    @FXML
    private ChoiceBox BedId;
    @FXML
    private ComboBox medCorId;
    @FXML
    private ComboBox patCardID;
    @FXML
    private ComboBox patName3;
    @FXML
    private ComboBox medCorDate;
    @FXML
    private Label medHisId4, patId4, curRoom4, createDate4, patName4, age4, sex4, roomId4, bedId4, outDate4, patDemand4, patHis4, result4;
    @FXML
    private Pane medAllPane;
    @FXML
    private TableView<Medicalrecordsinfo> medAllView;
    @FXML
    private TableColumn<Medicalrecordsinfo, Integer> medRecCol;
    @FXML
    private TableColumn<Medicalrecordsinfo, Integer> patIdCol;
    @FXML
    private TableColumn<Medicalrecordsinfo, String> PatNameCol;
    @FXML
    private TableColumn<Medicalrecordsinfo, String> dateCol;
    @FXML
    private TableView<AddMedcine> tabAddMedcine;
    @FXML
    private TableColumn<AddMedcine, ComboBox> medNameCol;
    @FXML
    private TableColumn<AddMedcine, TextField> useWayCol;
    @FXML
    private TableColumn<AddMedcine, ComboBox> amountCol;
    @FXML
    private TableColumn<AddMedcine, ComboBox> dosageCol;
    @FXML
    private TableColumn<AddMedcine, ComboBox> frequencyCol;
    @FXML
    private TableColumn<AddMedcine, Label> priceCol;
    @FXML
    private TableView<ShowMedicine> tabMedList;
    @FXML
    private TableColumn<ShowMedicine, Label> medNameCol2;
    @FXML
    private TableColumn<ShowMedicine, Label> useWayCol2;
    @FXML
    private TableColumn<ShowMedicine, Label> amountCol2;
    @FXML
    private TableColumn<ShowMedicine, Label> dosageCol2;
    @FXML
    private TableColumn<ShowMedicine, Label> frequencyCol2;
    @FXML
    private TableColumn<ShowMedicine, Label> priceCol2;

    @FXML
    private Label costAll;
    @FXML
    private Pane perInfoPane;
    @FXML
    private Label fixpwdlab;
    @FXML
    private Label conpwdlab;
    @FXML
    private Button confix;
    @FXML
    private TextField fixpwd;
    @FXML
    private TextField conpwd;
    @FXML
    private TextField doctel;
    @FXML
    private Button butFix;
    @FXML
    private Button cancelFix;
    @FXML
    private Label docNameLab;
    @FXML
    private Label docAgeLab;
    @FXML
    private Label docSexLab;
    @FXML
    private Label docIdLab;
    @FXML
    private Label docDepLab;
    @FXML
    private Label docJobLab;
    @FXML
    private Pane dutyPane;
    @FXML
    private TableView tabDuty;
    @FXML
    private TableColumn<WorkInfo, String> docNameCol;
    @FXML
    private TableColumn<WorkInfo, String> Monday;
    @FXML
    private TableColumn<WorkInfo, String> Tuesday;
    @FXML
    private TableColumn<WorkInfo, String> Wensday;
    @FXML
    private TableColumn<WorkInfo, String> Thursday;
    @FXML
    private TableColumn<WorkInfo, String> Friday;
    @FXML
    private TableColumn<WorkInfo, String> Saturday;
    @FXML
    private TableColumn<WorkInfo, String> Sunday;
    @FXML
    private ComboBox selectDep;
    @FXML private Label hospitalFare;
    @FXML private Pane operationPane;
    @FXML private TextField Name7;
    @FXML private TextField Id7;
    @FXML private TextField opName7;
    @FXML private TextField time7;
    @FXML private TextField opRoom7;
    @FXML private Pane welcomePane;
    @FXML private ComboBox sss;
    @FXML private DatePicker shijian;
    List<String> bed= new ArrayList<String>();
    private final ObservableList<Medicalrecordsinfo> cellData = FXCollections.observableArrayList();
    private final ObservableList<AddMedcine> medData = FXCollections.observableArrayList();
    private final ObservableList<ShowMedicine> medShowData = FXCollections.observableArrayList();
    private final ObservableList<WorkInfo> dutyData = FXCollections.observableArrayList();

    public static Medicalrecordsinfo medicalrecordsinfo = new Medicalrecordsinfo();
    public static Doctorinfo doctorinfo = DoctorLoginController.getDoctorinfo();

    public void initDocName(Doctorinfo doctorinfo) {
        docName2.setText(doctorinfo.getDocName());
        hospitalFare.setText("");
        for(int i = 1; i <= 20 ; i++)
            bed.add(String.valueOf(i));
    }

    public void AddPatient(ActionEvent actionEvent) {

        addpane.setVisible(true);
        checkpatient.setVisible(false);
        menupane.setVisible(false);
        addpathis.setVisible(false);
        addSucPane.setVisible(false);
        medAllPane.setVisible(false);
        perInfoPane.setVisible(false);
        dutyPane.setVisible(false);
        operationPane.setVisible(false);
        welcomePane.setVisible(false);
        patientId.clear();
    }

    public void checkPat(ActionEvent actionEvent) throws IOException {
        PatientInfo patientInfo = new PatientInfo();
        String strPatientId = patientId.getText();
        patientInfo.setPatId(Integer.parseInt(strPatientId));
        PatientInfoDao patinetInfoDao = new PatientInfoDao();
        int count = patinetInfoDao.checkPatient(patientInfo);
        if (count == 1) {
            patDemand.clear();
            patResult.clear();
            isMenzhen.setSelected(false);
            inHosptalChoose.setSelected(false);
            tabAddMedcine.getItems().clear();

            ComboBox comboBox1 = new ComboBox();
            ComboBox comboBox2 = new ComboBox();
            ComboBox comboBox3=new ComboBox ();
            ComboBox comboBox4=new ComboBox ();
            comboBox1.setPrefWidth(450);
            comboBox1.setPrefWidth(450);
            comboBox2.setPrefWidth(450);
            comboBox3.setPrefWidth(450);
            comboBox4.setPrefWidth(450);

            medicalrecordsinfo.setCurDoctor(doctorinfo.getDocName());
            medicalrecordsinfo.setCurDepartment(doctorinfo.getDocDepartment());
            medicalrecordsinfo.setIsInHospital("否");
            medicalrecordsinfo.setBedId("无");
            medicalrecordsinfo.setRoomId("无");
            medicalrecordsinfo.setInDate("无");
            medicalrecordsinfo.setOutDate("无");

            ArrayList<String> chooseAmount = new ArrayList<>();
            ArrayList<String> dosage= new ArrayList<> ();
            ArrayList<String> frequency=new ArrayList<> ();

            for (int i = 1; i <= 8; i++) {
                chooseAmount.add(String.valueOf (i));
            }
            for (int i = 1; i <= 5; i++) {
                dosage.add(String.valueOf (i));
            }
            for (int i = 1; i <= 5; i++) {
                frequency.add(String.valueOf (i));
            }
            comboBox2.getItems().addAll(chooseAmount);
            comboBox3.getItems ().addAll (dosage);
            comboBox4.getItems ().addAll (frequency);

            MedicalRecordDao medicalrecordsinfoDao = new MedicalRecordDao();
            pathisId2.setText(String.valueOf(medicalrecordsinfoDao.getMedRecTotal() + 1));
            patientInfo = patinetInfoDao.fixAddPat(patientInfo);
            patAge2.setText("年  龄： " + patientInfo.getPatAge());
            patName2.setText("姓  名： " + patientInfo.getPatName());
            patId2.setText(String.valueOf(patientInfo.getPatId()));
            medicalrecordsinfo.setPatId1(patientInfo.getPatId());
            patPhone2.setText("联系电话： " + patientInfo.getPatTel());
            patSex2.setText("性  别： " + patientInfo.getPatGender());
            ArrayList<String> illhis=new ArrayList<>();
            illhis=MedicalRecordDao.getIllHis(patientInfo.getPatId());
            if (illhis.size()==0){
                pathis2.setText("无");
            }
            else{
                String illAll="";
                for (String list:illhis){
                    illAll=illAll+list;
                }
                pathis2.setWrapText(true);
                pathis2.setText(illAll);
            }

            AutoCompleteComboBoxListener auto6=new AutoCompleteComboBoxListener(RoomId);

            MedicineInfoDao medicineInfoDao = new MedicineInfoDao();
            ArrayList<String> medlist = new ArrayList<>();
            medlist = medicineInfoDao.getMediList();
            for (String list : medlist) {
                comboBox1.getItems().add(list);
            }
            AutoCompleteComboBoxListener auto = new AutoCompleteComboBoxListener<>(comboBox1);
            AddMedcine addMedcine = new AddMedcine();
            addMedcine.setMedName(comboBox1);
            addMedcine.setAmount(comboBox2);
            addMedcine.setDosage (comboBox3);
            addMedcine.setFrequency (comboBox4);
            addMedcine.setUseWay(new TextField());

            //初始化价钱
            Label priceLabel = new Label();
            MedicineInfo medicineInfo1 = new MedicineInfo();
            comboBox1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override

                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    medicineInfo1.setMedName((String) comboBox1.getValue());
                }
            });
            comboBox2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    double price;
                    int strAmount = Integer.valueOf (comboBox2.getValue().toString ()) ;
                    double amountAll = strAmount;
                    price = MedicineInfoDao.getMedcienPrice(medicineInfo1) * amountAll;
                    priceLabel.setText(String.valueOf(price));
                }
            });

            addMedcine.setPrice(priceLabel);
            medData.add(addMedcine);
            tabAddMedcine.setItems(medData);

            medNameCol.setCellValueFactory(new PropertyValueFactory<AddMedcine, ComboBox>("medName"));
            priceCol.setCellValueFactory(new PropertyValueFactory<AddMedcine, Label>("price"));
            useWayCol.setCellValueFactory(new PropertyValueFactory<AddMedcine, TextField>("useWay"));
            amountCol.setCellValueFactory(new PropertyValueFactory<AddMedcine, ComboBox>("amount"));
            dosageCol.setCellValueFactory (new PropertyValueFactory<AddMedcine,ComboBox> ("dosage"));
            frequencyCol.setCellValueFactory (new PropertyValueFactory<AddMedcine,ComboBox> ("frequency"));
            medAllView.setItems(cellData);//将集合的值 存储到tableView里


            inHosptalChoose.setSelected(false);
            addpathis.setVisible(true);
            addpane.setVisible(false);
            checkpatient.setVisible(false);
            menupane.setVisible(false);
            inHospitalPane.setVisible(false);
            addSucPane.setVisible(false);
            perInfoPane.setVisible(false);
            dutyPane.setVisible(false);
            operationPane.setVisible(false);
            welcomePane.setVisible(false);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.headerTextProperty().set("不存在该卡号患者");
            alert.show();
        }
    }

    public void checkPatHis(ActionEvent actionEvent) throws IOException {
        medCorId.getItems().clear();
        patCardID.getItems().clear();
        patName3.getItems().clear();
        medCorDate.getItems().clear();
        ArrayList<Medicalrecordsinfo> medicalrecordsinfos = new ArrayList<>();
        medicalrecordsinfos = MedicalRecordDao.getMedicalRecords(doctorinfo);
        if (medicalrecordsinfos.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.headerTextProperty().setValue("暂无患者病历");
            alert.show();
        } else {
//            initcheckPat(medicalrecordsinfos.get(medicalrecordsinfos.size() - 1));
            initTable(medicalrecordsinfos);
            medAllView.setRowFactory(tv -> {
                TableRow<Medicalrecordsinfo> row = new TableRow<Medicalrecordsinfo>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        Medicalrecordsinfo medicalrecordsinfo1 = row.getItem();
                        try {
                            tabMedList.getItems().clear();
                            initcheckPat(medicalrecordsinfo1);
                            initTabMedList(medicalrecordsinfo1.getRecordId());
                            checkpatient.setVisible(true);
                            medAllPane.setVisible(false);
                            menupane.setVisible(false);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return row;
            });
            ArrayList<Integer> medid = new ArrayList<>();
            medid = MedicalRecordDao.getRecordId(doctorinfo);
            for (Integer list : medid) {
                medCorId.getItems().add(list);
            }
            AutoCompleteComboBoxListener auto1 = new AutoCompleteComboBoxListener<>(medCorId);

            ArrayList<Integer> patid = new ArrayList<>();
            patid = MedicalRecordDao.getPatId(doctorinfo);
            for (Integer list : patid) {
                patCardID.getItems().add(list);
            }
            AutoCompleteComboBoxListener auto2 = new AutoCompleteComboBoxListener<>(patCardID);
            ArrayList<String> patname = new ArrayList<>();
            patname = PatientInfoDao.getPatName();
            for (String list : patname) {
                patName3.getItems().add(list);
            }
            AutoCompleteComboBoxListener auto3 = new AutoCompleteComboBoxListener<>(patName3);
            ArrayList<String> addDate = new ArrayList<>();
            addDate = MedicalRecordDao.getCreateDate(doctorinfo);
            for (String list : addDate) {
                medCorDate.getItems().add(list);
            }
            AutoCompleteComboBoxListener auto4 = new AutoCompleteComboBoxListener<>(medCorDate);

            medCorDate.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                }

            });


            medAllPane.setVisible(true);
            addpane.setVisible(false);
            checkpatient.setVisible(false);
            menupane.setVisible(true);
            addpathis.setVisible(false);
            addSucPane.setVisible(false);
            perInfoPane.setVisible(false);
            dutyPane.setVisible(false);
            operationPane.setVisible(false);
            welcomePane.setVisible(false);
        }

    }

    public void addPatCon(ActionEvent actionEvent) throws ParseException {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        medicalrecordsinfo.setCreateDate(df.format(new Date()));
        medicalrecordsinfo.setPatDemands(patDemand.getText());
        medicalrecordsinfo.setResult(patResult.getText());
        medicalrecordsinfo.setRecordId(Integer.parseInt(pathisId2.getText()));
        MedicalRecordDao medicalrecordsinfoDao = new MedicalRecordDao();

        double costAll=0;
        ArrayList<MedicineList> medicineLists = new ArrayList<>();
        ObservableList<AddMedcine> medData2 = FXCollections.observableArrayList();
        medData2 = tabAddMedcine.getItems();
        for (AddMedcine list : medData2) {
            MedicineList medicineList = new MedicineList();
            medicineList.setMedicineUsage(list.getUseWay().getText());
            medicineList.setMedicineAmountl( (Integer.valueOf ((String)list.getAmount().getValue())));
            medicineList.setMedicalRecordsNumber(medicalrecordsinfo.getRecordId());
            MedicineInfo medicineInfo = new MedicineInfo();
            medicineInfo.setMedName((String) list.getMedName().getValue());
            if(!MedicineInfoDao.medCost((String) list.getMedName().getValue(),((String) list.getAmount().getValue()).charAt(0)-'0')){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.headerTextProperty().setValue((String) list.getMedName().getValue()+"货存不足");
                alert.show();
                return;
            }
            medicineList.setMedicineNumber(new MedicineInfoDao().getMedicineId(medicineInfo));
            medicineLists.add(medicineList);
            costAll+=Double.parseDouble(list.getPrice().getText());
        }

        for (MedicineList list : medicineLists) {
            MedicineListDao medicineListDao = new MedicineListDao();
            medicineListDao.addMedicineList(list);
        }


        if (inHosptalChoose.isSelected()) {
            medicalrecordsinfo.setInDate(df.format(new Date()));
            medicalrecordsinfo.setOutDate((String) outDate.getValue());
            medicalrecordsinfo.setRoomId((String) RoomId.getValue());
            medicalrecordsinfo.setBedId((String) BedId.getValue());
            bed.remove((String) BedId.getValue());

        } else if (!inHosptalChoose.isSelected()) {
            medicalrecordsinfo.setRoomId("无");
            medicalrecordsinfo.setInDate("无");
            medicalrecordsinfo.setOutDate("无");
            medicalrecordsinfo.setIsInHospital("否");
            medicalrecordsinfo.setBedId("无");
        }
        if (hospitalFare.getText()!="")
            costAll+=Integer.parseInt(hospitalFare.getText());
        medicalrecordsinfo.setPatConsumption(costAll);
        medicalrecordsinfoDao.insertMedicalRecord(medicalrecordsinfo);
        PatientInfoDao.patCost(Integer.parseInt(patId2.getText()),costAll);


        addSucPane.setVisible(true);
        checkpatient.setVisible(false);
        menupane.setVisible(false);
        addpathis.setVisible(false);
        addpane.setVisible(false);
        medAllPane.setVisible(false);
        perInfoPane.setVisible(false);
        dutyPane.setVisible(false);
        operationPane.setVisible(false);
        welcomePane.setVisible(false);
    }

    public void inHospital(ActionEvent actionEvent) {
        if (inHosptalChoose.isSelected()) {
            RoomId.getItems().clear();
            outDate.getItems().clear();
            BedId.getItems().clear();

            String []depart={"急诊科","儿科","眼科","口腔科","皮肤科","妇产科","内分泌科" ,"骨科","肝胆外科","泌尿外科","耳鼻喉科","心血管内科","神经内科","肛肠外科","乳腺甲状腺外科","整形、激光美容外科","神经外科" ,"心胸外科","消化内科","呼吸内科","肿瘤科","中医科","肾内科","检验科","病理科","放射科","药剂科","麻醉科"};
            RoomId.getItems().addAll(depart);
            BedId.getItems().addAll(bed);

            DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            inDate.setText(df.format(new Date()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i=1;i<=20;i++){
                Date d1 = new Date();
                d1.setTime(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * i);
                outDate.getItems().add(sdf.format(d1));
            }
            AutoCompleteComboBoxListener auto5=new AutoCompleteComboBoxListener(outDate);
            hospitalFare.setText("");
            hospitalFare.setAlignment(Pos.CENTER);
            outDate.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    DateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");

                    try {
                        Date d = sdf.parse(dfs.format(new Date()));
                        Date day=sdf.parse(String.valueOf(outDate.getValue()));
                        int daynum = (int) (( day.getTime()- d.getTime()) / (1000 * 60 * 60 * 24)); // 计算天
                        hospitalFare.setText(String.valueOf(daynum*200));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            });
            inHospitalPane.setVisible(true);
            medicalrecordsinfo.setIsInHospital("是");

        } else {
            inHospitalPane.setVisible(false);
            medicalrecordsinfo.setRoomId("无");
            medicalrecordsinfo.setInDate("无");
            medicalrecordsinfo.setOutDate("无");
            medicalrecordsinfo.setIsInHospital("否");
            medicalrecordsinfo.setBedId("无");
        }
    }

    public void initcheckPat(Medicalrecordsinfo medicalRecordsinfo2) throws IOException {
        PatientInfo patientInfo = new PatientInfo();
        patientInfo.setPatId(medicalRecordsinfo2.getPatId1());
        patientInfo = new PatientInfoDao().fixAddPat(patientInfo);
        medHisId4.setText(String.valueOf(medicalRecordsinfo2.getRecordId()));
        patId4.setText(String.valueOf(medicalRecordsinfo2.getPatId1()));
        curRoom4.setText(doctorinfo.getDocDepartment());
        createDate4.setText(medicalRecordsinfo2.getCreateDate());
        patName4.setText(patientInfo.getPatName());
        age4.setText(String.valueOf(patientInfo.getPatAge()));
        sex4.setText(patientInfo.getPatGender());
        roomId4.setText(medicalRecordsinfo2.getRoomId());
        bedId4.setText(medicalRecordsinfo2.getBedId());
        outDate4.setText(medicalRecordsinfo2.getOutDate());
        patDemand4.setText(medicalRecordsinfo2.getPatDemands());
        ArrayList<String> illhis=new ArrayList<>();
        illhis=MedicalRecordDao.getIllHis(medicalRecordsinfo2.getPatId1());
        if (illhis.size()==0){
            patHis4.setText("无");
        }
        else{
            String illAll="";
            for (String list:illhis){
                illAll=illAll+list;
            }
            patHis4.setWrapText(true);
            patHis4.setText(illAll);
        }


        result4.setText(medicalRecordsinfo2.getResult());

    }

    public void backToMain(ActionEvent actionEvent) {
        patientId.clear();
        addSucPane.setVisible(false);
        checkpatient.setVisible(false);
        menupane.setVisible(false);
        addpathis.setVisible(false);
        addpane.setVisible(false);
        medAllPane.setVisible(false);
        perInfoPane.setVisible(false);
        dutyPane.setVisible(false);
        operationPane.setVisible(false);
        welcomePane.setVisible(true);
    }

    public void backToCheck(ActionEvent actionEvent) throws IOException {
        checkPatHis(actionEvent);
    }

    public void checkCon(ActionEvent actionEvent) throws IOException {
        Medicalrecordsinfo medicalrecordsinfo = new Medicalrecordsinfo();
        ArrayList<Medicalrecordsinfo> medicalrecordsinfoArrayList = new ArrayList<>();
        if ((!medCorId.getSelectionModel().isEmpty() || !medCorId.getEditor().getText().isEmpty()) && (!patCardID.getSelectionModel().isEmpty() || !patCardID.getEditor().getText().isEmpty()) && (!patName3.getSelectionModel().isEmpty() || !patName3.getEditor().getText().isEmpty()) && (!medCorDate.getSelectionModel().isEmpty() || !medCorDate.getEditor().getText().isEmpty())) {

            medicalrecordsinfo.setRecordId(Integer.parseInt((String) (medCorId.getValue())));
            medicalrecordsinfo.setPatId1(Integer.parseInt((String) (patCardID.getValue())));
            medicalrecordsinfo.setPatName((String) patName3.getValue());
            medicalrecordsinfo.setCreateDate((String) medCorDate.getValue());
            medicalrecordsinfo.setCurDoctor(doctorinfo.getDocName());
            PatientInfo patientInfo = new PatientInfo();
            patientInfo.setPatId(Integer.parseInt((String) patCardID.getValue()));
            patientInfo.setPatName((String) patName3.getValue());
            if (new PatientInfoDao().checkpat2(patientInfo)) {
                medicalrecordsinfoArrayList = MedicalRecordDao.getMedicalRecords(medicalrecordsinfo);
                if (medicalrecordsinfoArrayList.size() == 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.headerTextProperty().set("不存在该患者！！");
                    alert.show();
                } else {
                    initTable(medicalrecordsinfoArrayList);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.headerTextProperty().set("不存在该患者！！");
                alert.show();
            }
        }
        if ((!patCardID.getSelectionModel().isEmpty() || !patCardID.getEditor().getText().isEmpty()) && medCorId.getEditor().getText().isEmpty() && medCorId.getSelectionModel().isEmpty() && patName3.getEditor().getText().isEmpty() && patName3.getSelectionModel().isEmpty() && medCorDate.getEditor().getText().isEmpty() && medCorDate.getSelectionModel().isEmpty()) {
            if (!patCardID.getEditor().getText().isEmpty())
                medicalrecordsinfo.setPatId1(Integer.parseInt(patCardID.getEditor().getText()));
            else
                medicalrecordsinfo.setPatId1(Integer.parseInt((String) patCardID.getValue()));
            medicalrecordsinfo.setCurDoctor(doctorinfo.getDocName());
            medicalrecordsinfoArrayList = MedicalRecordDao.getMedicalRecords2(medicalrecordsinfo);
            if (medicalrecordsinfoArrayList.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.headerTextProperty().set("不存在该患者！！");
                alert.show();
            } else {
                initTable(medicalrecordsinfoArrayList);
            }
        }
//        if (!patName3.getSelectionModel().isEmpty()){
//            medicalrecordsinfo.setPatName((String) patName3.getValue());
//        }
//        if (!medCorDate.getSelectionModel().isEmpty()){
//            medicalrecordsinfo.setCreateDate((String) medCorDate.getValue());
//        }

    }

    public void initTable(ArrayList<Medicalrecordsinfo> medicalrecordsinfos) {
        medAllView.getItems().clear();
        cellData.addAll(medicalrecordsinfos);
        medRecCol.setCellValueFactory(new PropertyValueFactory<Medicalrecordsinfo, Integer>("recordId"));
        patIdCol.setCellValueFactory(new PropertyValueFactory<Medicalrecordsinfo, Integer>("patId1"));
        PatNameCol.setCellValueFactory(new PropertyValueFactory<Medicalrecordsinfo, String>("patName"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Medicalrecordsinfo, String>("createDate"));
        medAllView.setItems(cellData);//将集合的值 存储到tableView里
    }

    public void continueAdd(ActionEvent actionEvent) {
        ComboBox comboBox1 = new ComboBox();
        ComboBox comboBox2 = new ComboBox();
        ComboBox comboBox3=new ComboBox ();
        ComboBox comboBox4=new ComboBox ();
        comboBox1.setPrefWidth(450);
        comboBox2.setPrefWidth(450);
        comboBox3.setPrefWidth(450);
        comboBox4.setPrefWidth(450);


        ArrayList<String> chooseAmount = new ArrayList<>();
        ArrayList<String> dosage=new ArrayList<> ();
        ArrayList<String> frequency=new ArrayList<> ();
        for (int i = 1; i <= 8; i++) {
            chooseAmount.add(String.valueOf (i));
        }
        for (int i = 1; i <= 8; i++) {
            dosage.add(String.valueOf (i));
        }
        for (int i = 1; i <= 8; i++) {
            frequency.add(String.valueOf (i));
        }

        comboBox2.getItems().addAll(chooseAmount);
        comboBox3.getItems ().addAll (dosage);
        comboBox4.getItems ().addAll (frequency);

        MedicineInfoDao medicineInfoDao = new MedicineInfoDao();
        ArrayList<String> medlist = new ArrayList<>();
        medlist = medicineInfoDao.getMediList();
        for (String list : medlist) {
            comboBox1.getItems().add(list);
        }
        AutoCompleteComboBoxListener auto = new AutoCompleteComboBoxListener<>(comboBox1);

        AddMedcine addMedcine = new AddMedcine();
        addMedcine.setMedName(comboBox1);
        addMedcine.setAmount(comboBox2);
        addMedcine.setUseWay(new TextField());
        addMedcine.setDosage (comboBox3);
        addMedcine.setFrequency (comboBox4);

        //初始化价钱
        Label priceLabel = new Label();
        MedicineInfo medicineInfo1 = new MedicineInfo();
        comboBox1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override

            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                medicineInfo1.setMedName((String) comboBox1.getValue());
            }
        });

        comboBox2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double price;
                String strAmount = (String) comboBox2.getValue();
                double amountAll = strAmount.charAt(0) - '0';
                price = MedicineInfoDao.getMedcienPrice(medicineInfo1) * amountAll;
                priceLabel.setText(String.valueOf(price));
            }
        });
//        comboBox3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//            @Override
//
//            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
//                medicineInfo1.setMedName((String) comboBox1.getValue());
//            }
//        });
        addMedcine.setPrice(priceLabel);
        medData.add(addMedcine);
        tabAddMedcine.setItems(medData);
    }

    public void deleteMed(ActionEvent actionEvent) {
        int index = tabAddMedcine.getSelectionModel().getSelectedIndex();
        tabAddMedcine.getItems().remove(index);
    }

    public void initTabMedList(int medListId) {
        ArrayList<MedicineList> medicineLists = new ArrayList<>();
        medicineLists = MedicineListDao.getMedList(medListId);
        double cost = 0;
        for (MedicineList list : medicineLists) {
            Label[] labels = new Label[4];
            for (int i = 0; i < 4; i++) {
                labels[i] = new Label();
            }
            int amount = list.getMedicineAmountl();
            MedicineInfo medicineInfo = new MedicineInfo();
            medicineInfo.setMedName(MedicineInfoDao.getMedName(list.getMedicineNumber()));
            double price = MedicineInfoDao.getMedcienPrice(medicineInfo) * amount;
            cost += price;
            labels[0].setText(MedicineInfoDao.getMedName(list.getMedicineNumber()));
            labels[1].setText(list.getMedicineUsage());
            labels[2].setText(String.valueOf (list.getMedicineAmountl()));
            labels[3].setText(String.valueOf(price));
            ShowMedicine showMedicine = new ShowMedicine();
            showMedicine.setMedName(labels[0]);
            showMedicine.setUseWay(labels[1]);
            showMedicine.setAmount(labels[2]);
            showMedicine.setPrice(labels[3]);
            medShowData.add(showMedicine);
        }

        if (!outDate4.getText().equals("无")){

            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");

            try {
                Date d = sdf.parse(outDate4.getText());
                Date day=sdf.parse(createDate4.getText());
                int daynum = (int) (( d.getTime()- day.getTime()) / (1000 * 60 * 60 * 24)); // 计算天
                cost+=200*daynum;
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        costAll.setAlignment(Pos.CENTER);
        costAll.setText(String.valueOf(cost));
        medNameCol2.setCellValueFactory(new PropertyValueFactory<ShowMedicine, Label>("medName"));
        useWayCol2.setCellValueFactory(new PropertyValueFactory<ShowMedicine, Label>("useWay"));
        amountCol2.setCellValueFactory(new PropertyValueFactory<ShowMedicine, Label>("amount"));
        dosageCol2.setCellValueFactory(new PropertyValueFactory<ShowMedicine, Label>("dosage"));
        frequencyCol2.setCellValueFactory (new PropertyValueFactory<ShowMedicine,Label> ("frequency"));
        priceCol2.setCellValueFactory(new PropertyValueFactory<ShowMedicine, Label>("price"));

        tabMedList.setItems(medShowData);
    }

    public void fixPerInfo(ActionEvent actionEvent) {

        docNameLab.setText(doctorinfo.getDocName());
        docIdLab.setText(String.valueOf(doctorinfo.getDocId()));
        docAgeLab.setText(String.valueOf(doctorinfo.getDocAge()));
        docSexLab.setText(doctorinfo.getDocGender());
        docDepLab.setText(doctorinfo.getDocDepartment());
        docJobLab.setText(doctorinfo.getDocRank());
        doctel.setText(doctorinfo.getDocTel());


        addpane.setVisible(false);
        checkpatient.setVisible(false);
        menupane.setVisible(false);
        addpathis.setVisible(false);
        addSucPane.setVisible(false);
        medAllPane.setVisible(false);
        perInfoPane.setVisible(true);
        dutyPane.setVisible(false);
        operationPane.setVisible(false);
        welcomePane.setVisible(false);
        confix.setVisible(false);
        conpwd.setVisible(false);
        conpwdlab.setVisible(false);
        fixpwd.setVisible(false);
        fixpwdlab.setVisible(false);
        doctel.setDisable(true);
        butFix.setVisible(true);
        cancelFix.setVisible(false);

    }

    public void fixPerInfoclick(ActionEvent actionEvent) {

        confix.setVisible(true);
        conpwd.clear();
        conpwd.setVisible(true);
        conpwdlab.setVisible(true);
        fixpwd.clear();
        fixpwd.setVisible(true);
        fixpwdlab.setVisible(true);
        doctel.setDisable(false);
        butFix.setVisible(false);
        cancelFix.setVisible(true);
    }

    public void cancelFixBut(ActionEvent actionEvent) {
        fixPerInfo(actionEvent);

    }

    public void conFix(ActionEvent actionEvent) {

        doctorinfo.setDocId(doctorinfo.getDocId());
        if (fixpwd.getText().isEmpty() && conpwd.getText().isEmpty() && doctel.getText().isEmpty()) {
            if (doctorinfo.getDocTel().equals(doctel.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.headerTextProperty().setValue("您未修改任何信息");
                alert.show();
            } else {
                doctorinfo.setDocTel("");
                DoctorinfoDao doctorinfoDao = new DoctorinfoDao();
                if (doctorinfoDao.fixDocTel(doctorinfo)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.headerTextProperty().setValue("修改成功");
                    alert.show();
                    fixPerInfo(actionEvent);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.headerTextProperty().setValue("修改失败");
                    alert.show();
                }
            }
        } else if (fixpwd.getText().isEmpty() && conpwd.getText().isEmpty() && doctel.getText().equals(doctorinfo.getDocTel())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.headerTextProperty().setValue("您未修改任何信息");
            alert.show();
        } else if (fixpwd.getText().isEmpty() && conpwd.getText().isEmpty()) {
            if (doctel.getText().length() != 11) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.headerTextProperty().setValue("请输入正确的电话号码");
                alert.show();
            } else {
                doctorinfo.setDocTel(doctel.getText());
                DoctorinfoDao doctorinfoDao = new DoctorinfoDao();
                if (doctorinfoDao.fixDocTel(doctorinfo)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.headerTextProperty().setValue("修改成功");
                    alert.show();
                    doctel.setText(doctorinfo.getDocTel());
                    fixPerInfo(actionEvent);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.headerTextProperty().setValue("修改失败");
                    alert.show();
                }
            }
        } else if (!fixpwd.getText().isEmpty() && !conpwd.getText().isEmpty()) {
            if (!fixpwd.getText().equals(conpwd.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.headerTextProperty().setValue("两次输入的密码不同");
                alert.show();
            } else if (!doctel.getText().isEmpty()) {
                if (doctel.getText().length() != 11) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.headerTextProperty().setValue("请输入正确的电话号码");
                    alert.show();
                } else {
                    doctorinfo.setDocTel(doctel.getText());
                    doctorinfo.setDocPwd(fixpwd.getText());
                    DoctorinfoDao doctorinfoDao = new DoctorinfoDao();
                    if (doctorinfoDao.fixDocTel(doctorinfo) && doctorinfoDao.fixDocPwd(doctorinfo)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.headerTextProperty().setValue("修改成功");
                        alert.show();
                        fixPerInfo(actionEvent);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.headerTextProperty().setValue("修改失败");
                        alert.show();
                    }
                }
            } else {
                doctorinfo.setDocTel("");
                doctorinfo.setDocPwd(fixpwd.getText());
                DoctorinfoDao doctorinfoDao = new DoctorinfoDao();
                if (doctorinfoDao.fixDocTel(doctorinfo) && doctorinfoDao.fixDocPwd(doctorinfo)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.headerTextProperty().setValue("修改成功");
                    alert.show();
                    fixPerInfo(actionEvent);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.headerTextProperty().setValue("修改失败");
                    alert.show();
                }
            }
        }

    }

    public void exitSys(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void checkDuty(ActionEvent actionEvent) {

        dutyPane.setVisible(true);
        addpane.setVisible(false);
        checkpatient.setVisible(false);
        menupane.setVisible(false);
        addpathis.setVisible(false);
        addSucPane.setVisible(false);
        medAllPane.setVisible(false);
        perInfoPane.setVisible(false);
        operationPane.setVisible(false);
        welcomePane.setVisible(false);

    }

    public void checkDutyBtn(ActionEvent actionEvent) {
        String strdep = (String) selectDep.getValue();
        departmentinfo departmentinfo1 = new departmentinfo();
        departmentinfo1.setDepName(strdep);
        int getdepId = departmentinfoDao.getDepId(departmentinfo1);
        WorkInfo workInfo=new WorkInfo();
        workInfo.setDepNumber(getdepId);
        ArrayList<WorkInfo> workInfos=new ArrayList<>();
        workInfos=WorkInfoDao.getWorkInfo(workInfo);

        dutyData.addAll(workInfos);
        docNameCol.setCellValueFactory(new PropertyValueFactory<WorkInfo, String>("docname"));
        Monday.setCellValueFactory(new PropertyValueFactory<WorkInfo, String>("Monday"));
        Tuesday.setCellValueFactory(new PropertyValueFactory<WorkInfo, String>("Tuesday"));
        Wensday.setCellValueFactory(new PropertyValueFactory<WorkInfo, String>("Wensday"));
        Thursday.setCellValueFactory(new PropertyValueFactory<WorkInfo, String>("Thursday"));
        Friday.setCellValueFactory(new PropertyValueFactory<WorkInfo, String>("Friday"));
        Saturday.setCellValueFactory(new PropertyValueFactory<WorkInfo, String>("Saturday"));
        Sunday.setCellValueFactory(new PropertyValueFactory<WorkInfo, String>("Sunday"));
        tabDuty.setItems(dutyData);

    }

    public void operation(ActionEvent actionEvent) {
        shijian.setVisible(true);
        sss.setVisible(true);
        Name7.clear();
        Id7.clear();
        opName7.clear();
        shijian.setValue(null);
        sss.getSelectionModel().clearSelection();
        operationPane.setVisible(true);
        dutyPane.setVisible(false);
        addpane.setVisible(false);
        checkpatient.setVisible(false);
        menupane.setVisible(false);
        addpathis.setVisible(false);
        addSucPane.setVisible(false);
        medAllPane.setVisible(false);
        perInfoPane.setVisible(false);
        welcomePane.setVisible(false);
    }

    public void conOpration(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.headerTextProperty().setValue("已为"+Name7.getText()+"安排手术成功！");
        alert.show();
        Name7.clear();
        Id7.clear();
        opName7.clear();
        welcomePane.setVisible(true);
        operationPane.setVisible(false);
    }

    public void Change(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/ui/MainLoginFXML.fxml"));
        Scene scene = new Scene(root,900,550);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(
                getClass().getResource("/ui/MainDocStyle.css").toExternalForm());
        primaryStage.setTitle("医疗管理系统");
        primaryStage.show();
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}