/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this torderlate file, choose Tools | Torderlates
 * and open the torderlate in the editor.
 */
package org.rmj.marketplace.controller;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.rmj.marketplace.model.ScreenInterface;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.rmj.marketplace.model.OrderModel;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agent.MsgBox;
import org.rmj.appdriver.constants.EditMode;
import org.rmj.marketplace.base.LTransaction;
import org.rmj.marketplace.base.ProductReviews;
import org.rmj.marketplace.base.SalesOrder;
import static org.rmj.marketplace.controller.ItemManagementController.priceWithDecimal;
import org.rmj.marketplace.model.OrderDetailModel;
import java.time.LocalDateTime;  // Import the LocalDateTime class
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.imageio.ImageIO;
import org.rmj.appdriver.agentfx.ShowMessageFX;
import static org.rmj.marketplace.controller.ItemManagementController.listingEnd;
import static org.rmj.marketplace.controller.ItemManagementController.listingStart;
import org.rmj.marketplace.model.ImageModel;
import org.rmj.marketplace.model.ItemDescriptionModel;
import org.rmj.marketplace.model.RatingsReviewModel;

/**
 * FXML Controller class
 *
 * @author user
 */
public class OrderProcessingController implements Initializable, ScreenInterface {
    
    private GRider oApp;
    private SalesOrder oTrans;
    private LTransaction  oListener;
    private boolean pbLoaded = false;
    private int pnRow = -1;
    private int pnEditMode;
    
    @FXML
    private Button btnBrowse;
    @FXML
    private TableView tblorders;
    @FXML
    private TableColumn orderIndex01;
    @FXML
    private TableColumn orderIndex02;
    @FXML
    private TableColumn orderIndex03;
    @FXML
    private TableColumn orderIndex04;
    @FXML
    private TableColumn orderIndex05;
    @FXML
    private TableColumn orderIndex06;
    @FXML
    private TableColumn orderIndex07;
    @FXML
    private TableColumn orderIndex08;
    @FXML
    private TableColumn orderIndex09;
    
    @FXML 
    private TableView<OrderModel> tblClients;
    @FXML
    private TableColumn clientsIndex01;
    @FXML
    private TableColumn clientsIndex02;
    @FXML
    private TableColumn clientsIndex03;
    @FXML
    private TableColumn clientsIndex04;
    @FXML
    private Label label01;
    @FXML
    private Label label02;
    @FXML
    private Label label03;
    @FXML
    private Label label04;
    @FXML
    private Label label05;
    @FXML
    private Label label06;
    @FXML
    private Label label07;
    @FXML
    private Label label08;
    @FXML
    private Label label09;
    @FXML
    private TextField txtSeeks99;
//    
    @FXML
    private AnchorPane MainOrderProcessing;
    private double total = 0.0;

    private ObservableList<OrderModel> data = FXCollections.observableArrayList();
    private ObservableList<OrderDetailModel> data1 = FXCollections.observableArrayList();
    
    private FilteredList<OrderModel> filteredData;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oListener = new LTransaction() {
             @Override
             public void MasterRetreive(int fnIndex, Object foValue) {
                 System.out.println("fnIndex = " + fnIndex);
                 System.out.println("foValue = " + (String) foValue);
             }
         };
        oTrans = new SalesOrder(oApp, oApp.getBranchCode(), false);
        oTrans.setListener(oListener);
        oTrans.setTranStat(10234);
        oTrans.setWithUI(true);
        pbLoaded = true;
        loadOrders();
        btnBrowse.setOnAction(this::cmdButton_Click);
        pnEditMode = EditMode.UNKNOWN;
        
    }    

    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue; //To change body of generated methods, choose Tools | Torderlates.
    }
    @FXML
    private void tblClients_Clicked(MouseEvent event) {
        
        if(event.getClickCount() == 2){
            pnRow = tblClients.getSelectionModel().getSelectedIndex();
            
            label01.setText(dateToWord(data.get(pnRow).getOrderIndex03()));
            label02.setText(data.get(pnRow).getOrderIndex13());
            label03.setText(data.get(pnRow).getOrderIndex05());
            label04.setText(data.get(pnRow).getOrderIndex15());
            label05.setText(data.get(pnRow).getOrderIndex09());
            label06.setText(data.get(pnRow).getOrderIndex14());
            label07.setText(priceWithDecimal(Double.valueOf(data.get(pnRow).getOrderIndex05().replaceAll("[ |₱|,]+", "")) + Double.valueOf(data.get(pnRow).getOrderIndex09().replaceAll("[₱ ,]+", ""))));
            label08.setText(data.get(pnRow).getOrderIndex12());
            label09.setText(data.get(pnRow).getOrderIndex04());
            loadOrderDetail();
        }
        
    }
    private void loadOrders(){
        int lnCtr;
        try {
            data.clear();
            if (oTrans.LoadList("", true)){//true if by barcode; false if by description
                oTrans.displayMasFields();
                for (lnCtr = 1; lnCtr <= oTrans.getItemCount(); lnCtr++){
                    data.add(new OrderModel(String.valueOf(lnCtr),
                            (String) oTrans.getDetail(lnCtr, "sTransNox"),
                            oTrans.getDetail(lnCtr, "dTransact").toString(),
                            (String) oTrans.getDetail(lnCtr, "sTermCode"),
                            priceWithDecimal(Double.valueOf(oTrans.getDetail(lnCtr, "nTranTotl").toString())),
                            priceWithDecimal(Double.valueOf(oTrans.getDetail(lnCtr, "nVATRatex").toString())),
                            priceWithDecimal(Double.valueOf(oTrans.getDetail(lnCtr, "nDiscount").toString())),
                            priceWithDecimal(Double.valueOf(oTrans.getDetail(lnCtr, "nAddDiscx").toString())),
                            priceWithDecimal(Double.valueOf(oTrans.getDetail(lnCtr, "nFreightx").toString())),
                            priceWithDecimal(Double.valueOf(oTrans.getDetail(lnCtr, "nAmtPaidx").toString())),
                            (String) oTrans.getDetail(lnCtr, "cTranStat"),
                            (String) oTrans.getDetail(lnCtr, "sRemarksx"),
                            (String) oTrans.getDetail(lnCtr, "sCompnyNm"),
                            (String) oTrans.getDetail(lnCtr, "sAddressx"),
                            (String) oTrans.getDetail(lnCtr, "sTownName")
                    ));
                  
                }
                initGrid();
            }    
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException" + ex.getMessage());
        } catch (DateTimeException ex) {
//            MsgBox.showOk(ex.getMessage());
            System.out.println("DateTimeException" + ex.getMessage());
        } 
    }
    private void loadOrderDetail(){
        int lnCtr;
        try {
            data1.clear();
            if (oTrans.OpenTransaction((String)oTrans.getMaster("sTransNox"))){//true if by barcode; false if by description
                
                for (lnCtr = 1; lnCtr <= oTrans.getItemCount(); lnCtr++){
                    data1.add(new OrderDetailModel(String.valueOf(lnCtr),
                            (String) oTrans.getDetail(lnCtr, "sTransNox"),
                            (String) oTrans.getDetail(lnCtr, "xBarCodex"),
                            (String) oTrans.getDetail(lnCtr, "xDescript"),
                            (String) oTrans.getDetail(lnCtr, "xBrandNme"),
                            (String) oTrans.getDetail(lnCtr, "xModelNme"),
                            (String) oTrans.getDetail(lnCtr, "xColorNme"),
                            oTrans.getDetail(lnCtr, "nEntryNox").toString(),
                            oTrans.getDetail(lnCtr, "nQuantity").toString(),
                            priceWithDecimal(Double.valueOf(oTrans.getDetail(lnCtr, "nUnitPrce").toString())),
                            (String) oTrans.getDetail(lnCtr, "sReferNox"),
                            oTrans.getDetail(lnCtr, "nIssuedxx").toString()
                    ));
                  
                }
                initGrid1();
            }  else{
                MsgBox.showOk(oTrans.getMessage());
            }  
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException" + ex.getMessage());
        } catch (DateTimeException ex) {
//            MsgBox.showOk(ex.getMessage());
            System.out.println("DateTimeException" + ex.getMessage());
        } 
    }
    private void initGrid() {
        clientsIndex01.setStyle("-fx-alignment: CENTER;");
        clientsIndex02.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        clientsIndex03.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        clientsIndex04.setStyle("-fx-alignment: CENTER-RIGHT;-fx-padding: 0 10 0 0;");
        clientsIndex01.setCellValueFactory(new PropertyValueFactory<>("orderIndex01"));
        clientsIndex02.setCellValueFactory(new PropertyValueFactory<>("orderIndex02"));
        clientsIndex03.setCellValueFactory(new PropertyValueFactory<>("orderIndex13"));
        clientsIndex04.setCellValueFactory(new PropertyValueFactory<>("orderIndex05"));
        
        filteredData = new FilteredList<>(data, b -> true);
//        autoSearch(txtSeeks98);
//        autoSearch(txtSeeks99);
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<OrderModel> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tblClients.comparatorProperty());
        
        
        // 5. Add sorted (and filtered) data to the table.
       tblClients.setItems(sortedData);
       tblClients.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblClients.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
        
    }
    private void initGrid1() {
        orderIndex01.setStyle("-fx-alignment: CENTER;");
        orderIndex02.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderIndex03.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderIndex04.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderIndex05.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderIndex06.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderIndex07.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderIndex08.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderIndex09.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderIndex01.setCellValueFactory(new PropertyValueFactory<>("orderIndex01"));
        orderIndex02.setCellValueFactory(new PropertyValueFactory<>("orderIndex03"));
        orderIndex03.setCellValueFactory(new PropertyValueFactory<>("orderIndex04"));
        orderIndex04.setCellValueFactory(new PropertyValueFactory<>("orderIndex05"));
        orderIndex05.setCellValueFactory(new PropertyValueFactory<>("orderIndex06"));
        orderIndex06.setCellValueFactory(new PropertyValueFactory<>("orderIndex07"));
        orderIndex07.setCellValueFactory(new PropertyValueFactory<>("orderIndex11"));
        orderIndex08.setCellValueFactory(new PropertyValueFactory<>("orderIndex11"));   
        orderIndex09.setCellValueFactory(new PropertyValueFactory<>("orderIndex12"));   
   
       
        
        
       
        // 5. Add sorted (and filtered) data to the table.
       tblorders.setItems(data1);
       tblorders.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblorders.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
        
    }
    private void initButton(int fnValue){
        boolean lbShow = (fnValue == EditMode.ADDNEW || fnValue == EditMode.UPDATE);

    }
    private void cmdButton_Click(ActionEvent event) {
        String lsButton = ((Button)event.getSource()).getId();
//        try {
            switch (lsButton){
                case "btnBrowse":
//                    if(oTrans.SearchTransaction(txtSeeks99.getText(), true)){
//                        loadOrderDetail();
//                    }else{
//                        MsgBox.showOk(oTrans.getMessage());
//                    }
                    break;
            }
            initButton(pnEditMode);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            MsgBox.showOk(e.getMessage());
//        } 
    } 
    public static String priceWithDecimal (Double price) {
        DecimalFormat formatter = new DecimalFormat("₱ ###,###,##0.00");
        return formatter.format(price);
    }
    public static String dateToWord (String dtransact) {
       
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
//            Date date = dateParser.parse("2022-04-29 16:59:13");
//            SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
//             String str_date="2012-08-11+05:30";
//           
            Date date = new Date();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            date = (Date)formatter.parse(dtransact);  
            SimpleDateFormat fmt = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
            String todayStr = fmt.format(date);
            
            return todayStr;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
