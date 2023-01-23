/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.controller;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.appdriver.agentfx.ShowMessageFX;
import org.rmj.marketplace.base.LTransaction;
import org.rmj.marketplace.base.OrderList;
import org.rmj.marketplace.model.OrderModel;
import org.rmj.marketplace.model.ScreenInterface;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ReportsController implements Initializable, ScreenInterface{
    
    private GRider oApp;
    private OrderList oTrans;
    private LTransaction oListener;
    
    private final boolean pbLoaded = false;
    
    private JasperPrint jasperPrint;
    private JasperReport jasperReport;
    private JRViewer jrViewer;
    public String reportCategory;
    private ToggleGroup rbGroup;
    private String sDateFrom = "";
    private String sDateThru = "";
//    private JasperPreview jasperPreview;
    
    private boolean running = false;
    final static int interval = 100;
    private Timeline timeline;
    private Integer timeSeconds = 3;
    @FXML
    private Button btnGenerate;
    @FXML
    private AnchorPane reportPane, AnchorReport;
    @FXML
    private RadioButton rbDetailed;
    @FXML
    private RadioButton rbSummarized;
    @FXML
    private DatePicker dpFrom,dpThru;
    @FXML
    private VBox vbProgress;
    @FXML
    private VBox vbContainer;
    
    private ObservableList<OrderModel> inc_detail = FXCollections.observableArrayList();
    private final ObservableList<OrderModel> inc_data = FXCollections.observableArrayList();
    public void setReportCategory(String foValue){
        reportCategory = foValue;
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnGenerate.setOnAction(this::cmdButton_Click);
       
       
        oListener = new LTransaction() {
            @Override
            public void MasterRetreive(int fnIndex, Object foValue) {
                switch (fnIndex) {
                    case 18:
                        break;
                }
            }
         };
//          ResultSet name;
//        String lsQuery = "SELECT b.sCompnyNm " +
//                            " FROM xxxSysUser a" +
//                            " LEFT JOIN Client_Master b" +  
//                                " ON a.sEmployNo  = b.sClientID" +
//                            " WHERE a.sUserIDxx = " + SQLUtil.toSQL(oApp.getUserID());
//        name = oApp.executeQuery(lsQuery);
//        try {
//            if(name.next()){
//               
//                System.setProperty("user.name", name.getString("sCompnyNm"));   
//            }             
//        } catch (SQLException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        oTrans  = new OrderList(oApp, oApp.getBranchCode(), false);
        oTrans.setListener(oListener);
        oTrans.setTranStat(12);
        oTrans.setWithUI(true);
        initToggleGroup();
        initDatePicker();
        initFields();
    }  
    private Stage getStage(){
	return (Stage) AnchorReport.getScene().getWindow();
    }
    private void initToggleGroup(){
        rbGroup = new ToggleGroup();
        rbDetailed.setToggleGroup(rbGroup);
        rbSummarized.setToggleGroup(rbGroup);
        rbDetailed.setSelected(true);
    }
    private void initDatePicker(){
        dpFrom.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                //get current date
                LocalDate today = LocalDate.now();
                //check current date is greather than now then disable
                setDisable(empty || date.compareTo(today) > 0);
                getFormattedDateFromDatePicker(dpFrom,"dpFrom");
            }
        });
        
        dpThru.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty); 
                //get current date
                LocalDate today = LocalDate.now();
                //check current date is greather than now then disable
                //check previous date is lesser than date from value then disable
                if(dpFrom.getValue() != null){
                    setDisable(empty || date.compareTo(dpFrom.getValue()) < 0 || date.compareTo(today) > 0);
                    getFormattedDateFromDatePicker(dpThru,"dpThru");
                }else{
                    ShowMessageFX.Warning(getStage(), "Report date from must not be empty","Warning", null);
                }
            }
        });
        
    }
    private void hideReport(){
//        jasperPrint = null;
        jrViewer =  new JRViewer(null);
        reportPane.getChildren().clear();
//        JasperViewer.viewReport(jasperPrint, false);
         
        jrViewer.setVisible(false);
        running = false;
        reportPane.setVisible(false);
        
        timeline.stop();
//        vbProgress.setVisible(true);
    }    
    private void showReport(){
        
        vbProgress.setVisible(false);
        jrViewer =  new JRViewer(jasperPrint);
//        JasperViewer.viewReport(jasperPrint, false);
         
        SwingNode swingNode = new SwingNode();
        jrViewer.setOpaque(true);
        jrViewer.setVisible(true);
        jrViewer.setFitPageZoomRatio();
            
        swingNode.setContent(jrViewer);
        swingNode.setVisible(true);
//        reportPane.getChildren().clear();
        reportPane.setTopAnchor(swingNode,0.0);
        reportPane.setBottomAnchor(swingNode,0.0);
        reportPane.setLeftAnchor(swingNode,0.0);
        reportPane.setRightAnchor(swingNode,0.0);
        reportPane.getChildren().add(swingNode);
        running = true;
        reportPane.setVisible(true);
        timeline.stop();
    }  
 
    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue;
    }
    
     
    private void initFields(){
        timeline = new Timeline();
//        lblReportsTitle.setText(reportCategory + " REPORT");
//        if(reportCategory.equalsIgnoreCase("STANDARD")){
//              gpIndex02.setManaged(false);
//              gpIndex02.setVisible(false);
//              gpIndex03.setManaged(false);
//              gpIndex03.setVisible(false);
//        }else if(reportCategory.equalsIgnoreCase("AUDIT")){
//              gpIndex03.setManaged(false);
//              gpIndex03.setVisible(false);
//              gpIndex04.setManaged(false);
//              gpIndex04.setVisible(false);
//        }else if(reportCategory.equalsIgnoreCase("PAYROLL")){
//              gpIndex04.setManaged(false);
//              gpIndex05.setManaged(false);
//              gpIndex04.setVisible(false);
//              gpIndex05.setVisible(false);
//        }
    }
    public static String dateToWord (String dtransact) {
       
        try {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = (Date)formatter.parse(dtransact);  
            String todayStr = CommonUtils.xsDateMedium(date);
            return todayStr;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    private String getFormattedDateFromDatePicker(DatePicker datePicker, String lsValue) {
        //Get the selected date
        if(datePicker.getValue() != null){
            LocalDate selectedDate = datePicker.getValue();
            
        //Create DateTimeFormatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //Convert LocalDate to formatted String
            if(lsValue.equalsIgnoreCase("dpThru")){
                sDateThru = selectedDate.format(formatter);
            }else{
                sDateFrom = selectedDate.format(formatter);
                
            }
            return selectedDate.format(formatter);
        }else{
            return "";
        }
        
    }
    private void cmdButton_Click(ActionEvent event) {
            String lsButton = ((Button)event.getSource()).getId();
            switch (lsButton){
                case "btnGenerate":
                    
                    vbProgress.setVisible(true);
                    if (rbDetailed.isSelected()){
                        if(dpFrom.getValue() == null){
                          sDateFrom = "";
                          ShowMessageFX.Warning(getStage(), "Report date from must not be empty","Warning", null);
                          vbProgress.setVisible(false);
                          hideReport();
                       }else if(dpThru.getValue() == null){
                          sDateFrom = "";
                          ShowMessageFX.Warning(getStage(), "Report date thru must not be empty","Warning", null);
                          vbProgress.setVisible(false);
                          hideReport();
                       } else {
                           generateReport();
                       }
                    } else {
                        generateReport();
                    }
                        
                   
                    
                    
                break;
            }
    } 
    private void generateReport(){
        hideReport();
        if(!running){
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(1), (ActionEvent event1) -> {
                        timeSeconds--;
                        // update timerLabel
                        if(timeSeconds <= 0){
                           timeSeconds = 0 ; 
                        }
                        if (timeSeconds == 0) {

                            loadReport();
                        }
                } // KeyFrame event handler
            ));
            timeline.playFromStart();
        }
    }
    private boolean loadReport(){
         //Create the parameter
            Map<String, Object> params = new HashMap<>();
//                params.put("sReportNm", "Department Incentive Report");
            params.put("sPrintdBy", System.getProperty("user.name"));
            params.put("sReportDt", dateToWord(sDateFrom)  + " - " +dateToWord(sDateThru));
            params.put("sCompnyNm", "Guanzon Group of Companies");
            params.put("sBranchNm", oApp.getBranchName());
            params.put("sAddressx", oApp.getAddress());
            params.put("sReportNm", "ECommerce Order Report");
        
        try{
            if (rbDetailed.isSelected()){
                if(oTrans.LoadListReport(sDateFrom, sDateThru)){ 
                    inc_detail.clear();
                    for (int x = 1; x <= oTrans.getItemCount(); x++){
                        String stats = "";
                        if(oTrans.getDetail(x, "cTranStat").toString().equalsIgnoreCase("0")){
                            stats = "OPEN";
                        }else if(oTrans.getDetail(x, "cTranStat").toString().equalsIgnoreCase("1")){
                            stats = "CLOSED";
                        }else if(oTrans.getDetail(x, "cTranStat").toString().equalsIgnoreCase("2")){
                            stats = "POSTED";
                        }
                        inc_detail.add(new OrderModel(
                            String.valueOf(x),
                            oTrans.getDetail(x, "sCompnyNm").toString(),    
                            oTrans.getDetail(x, "nTranTotl").toString(),
                            oTrans.getDetail(x, "nAmtPaidx").toString(),
                            stats,
                            dateToWord(oTrans.getDetail(x, "dTransact").toString())));
                    }
//                      
                    
                    
                
                String sourceFileName = 
                "D://GGC_Java_Systems/reports/ECommerceOrder.jasper";
                String printFileName = null;
                JRBeanCollectionDataSource beanColDataSource1 = new JRBeanCollectionDataSource(inc_detail);

                    try {
                         jasperPrint =JasperFillManager.fillReport(
                                sourceFileName, params, beanColDataSource1);

                        printFileName = jasperPrint.toString();
                        if(printFileName != null){
                            showReport();
                         }
                        }
                     catch (JRException ex) {
                        running = false;
                        vbProgress.setVisible(false);
                        timeline.stop();
                    }
            }else{
                ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                running = false;
                vbProgress.setVisible(false);
                timeline.stop();
                }
            
        }
        else if(rbSummarized.isSelected()){
//            params.put("sReportNm", "Department Incentive Summary Report");
            if(oTrans.OpenTransaction(sDateFrom)){ 
            inc_data.clear();
            for (int x = 1; x <= oTrans.getItemCount(); x++){
                String stats = "";
                if(oTrans.getDetail(x, "sCompnyNm").toString().equalsIgnoreCase("0")){
                    stats = "OPEN";
                }else if(oTrans.getDetail(x, "sCompnyNm").toString().equalsIgnoreCase("1")){
                    stats = "CLOSED";
                }else if(oTrans.getDetail(x, "sCompnyNm").toString().equalsIgnoreCase("2")){
                    stats = "POSTED";
                }
                inc_detail.add(new OrderModel(
                    String.valueOf(x),
                    oTrans.getDetail(x, "sCompnyNm").toString(),    
                    oTrans.getDetail(x, "nTranTotl").toString(),
                    oTrans.getDetail(x, "nAmtPaidx").toString(),
                    stats,
                    dateToWord(oTrans.getDetail(x, "dTransact").toString())));
            }
            String sourceFileName = 
            "D://GGC_Java_Systems/reports/ECommerceOrder.jasper";
            String printFileName = null;
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(inc_data);
            try {
                 jasperPrint =JasperFillManager.fillReport(
                        sourceFileName, params, beanColDataSource);
               
                printFileName = jasperPrint.toString();
                if(printFileName != null){
                    showReport();
                 }
            } catch (JRException ex) {
                running = false;
                vbProgress.setVisible(false);
                timeline.stop();
            }
        } 
    }
    }catch(SQLException e){}
        running = false;
        vbProgress.setVisible(false);
        timeline.stop();
        return true;
    }
}
 
    