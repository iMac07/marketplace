/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.marketplace.model.OrderModel;
import org.rmj.marketplace.model.ScreenInterface;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ReportsController implements Initializable, ScreenInterface{
    private GRider oApp;
    private JasperPrint jasperPrint;
    private ToggleGroup rbGroup;
//    private JasperPreview jasperPreview;
    @FXML
    private Button btnGenerate;
    @FXML
    private AnchorPane reportPane;
    @FXML
    private RadioButton rbDetailed;
    @FXML
    private RadioButton rbSummarized;
    @FXML
    private RadioButton rbGlobal;
    @FXML
    private RadioButton rbChart;
    @FXML
    private DatePicker dpFrom;
    @FXML
    private DatePicker dpThru;
    @FXML
    private VBox vbProgress;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    private final ArrayList<OrderModel> pmodel = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        btnGenerate.setOnAction(e -> {
            showProgress();
            String sourceFileName = 
          "D://GGC_Java_Systems/reports/sample_order_report.jasper";
         String printFileName = null;
        pmodel.clear();
        String barcode, serial,model, color;
        for(int x = 1; x <= 10; x++){
            int min = 10;
            int max = 100;
            barcode = "123456789" + (x-1);
            
            if((x * 2)>= 10){
                serial = "24681012" + (x*2);
            }else{
                serial = "246810121" + (x*2);
            }
            if ( x % 2 == 0 ){
                color = "black";
            }else{
                color = "white";
            }
            model = "model " + x;
            Random rand = new Random();
            double n = rand.nextDouble();
            
            int nn = rand.nextInt(10);
//            OrderModel models = new OrderModel(String.valueOf(x),
//                            serial,
//                            barcode,
//                            model,
//                            color,
//                            String.valueOf(nn + 1),
//                            nextDoubleBetween4(100.0d,999.0d));
//            pmodel.add(models);
        }
        
    

        JRBeanCollectionDataSource beanColDataSource = new 
           JRBeanCollectionDataSource(pmodel);
        Map parameters = new HashMap();
            try {
                jasperPrint =JasperFillManager.fillReport(
                        sourceFileName, parameters, beanColDataSource);
               
                printFileName = jasperPrint.toString();
                if(printFileName != null){
                    
                    reportPane.requestFocus();
                    showReport();
                    
                 }
            } catch (JRException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        initToggleGroup();
        initDatePicker();
    }   
    public static String nextDoubleBetween4(double min, double max) {
        return  CommonUtils.NumberFormat((Number) ((Math.random() * (max - min)) + (max/2)) , "#,##0.00");
    }
    private void showReport(){
       
        SwingNode swingNode = new SwingNode();
        JRViewer jrViewer =  new JRViewer(jasperPrint);
        jrViewer.setOpaque(true);
        jrViewer.setVisible(true);
//        jrViewer.setZoomRatio((float) 0.9655);
        swingNode.setContent(jrViewer);
        swingNode.setVisible(true);
        
        reportPane.setTopAnchor(swingNode,0.0);
        reportPane.setBottomAnchor(swingNode,0.0);
        reportPane.setLeftAnchor(swingNode,0.0);
        reportPane.setRightAnchor(swingNode,0.0);
        reportPane.getChildren().add(swingNode);
        
        hideProgress();
    }    
    private void initToggleGroup(){
        rbGroup = new ToggleGroup();
        rbDetailed.setToggleGroup(rbGroup);
        rbSummarized.setToggleGroup(rbGroup);
        rbGlobal.setToggleGroup(rbGroup);
        rbChart.setToggleGroup(rbGroup);
        rbDetailed.setSelected(true);
    }
    private void initDatePicker(){
        dpFrom.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if(dpThru.getValue() != null){
                    if (date != null && !empty) {
                        setDisable(empty || date.compareTo(LocalDate.now()) > 0 || date.compareTo(dpThru.getValue()) > 0);
                    }
                }else{
                    setDisable(empty || date.compareTo(LocalDate.now()) > 0);
                }
            }
        });
        dpThru.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if(dpFrom.getValue() != null){
                    if (date != null && !empty) {
                        setDisable(empty || date.compareTo(LocalDate.now()) > 0 || date.compareTo(dpFrom.getValue()) < 0);
                    }
                }else{
                    setDisable(empty || date.compareTo(LocalDate.now()) > 0);
                }
                
            }
        });
    }
    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue;
    }
    
    
    private void showProgress(){
        vbProgress.getChildren().clear();
        vbProgress.setAlignment(Pos.CENTER);
        ProgressIndicator pi = new ProgressIndicator();
        pi.setOpacity(1.0);
        pi.setStyle(" -fx-progress-color: #0086b3;");

        VBox box = new VBox(pi);
        box.setAlignment(Pos.CENTER);

        // Grey Background
        vbProgress.getChildren().add(box);
    }
    private void hideProgress(){
        vbProgress.getChildren().clear();
    }
}