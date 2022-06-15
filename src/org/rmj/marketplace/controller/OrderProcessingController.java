/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this torderlate file, choose Tools | Torderlates
 * and open the torderlate in the editor.
 */
package org.rmj.marketplace.controller;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import org.rmj.marketplace.model.ScreenInterface;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
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
import org.rmj.marketplace.base.SalesOrder;
import org.rmj.marketplace.model.OrderDetailModel;
import java.util.Date;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.rmj.appdriver.agentfx.ShowMessageFX;
import org.rmj.marketplace.base.LResult;
import org.rmj.marketplace.model.OrderPaymentTaggingModel;


/**
 * FXML Controller class
 *
 * @author user
 */
public class OrderProcessingController implements Initializable, ScreenInterface {
    
    private GRider oApp;
    private SalesOrder oTrans;
    private LResult oListener;
    private boolean pbLoaded = false;
    private int pnRow = -1;
    private int pnRow1 = -1;
    private int oldPnRow = -1;
    private int pnEditMode;
    private String oldTransNox = "";
    
    double xOffset = 0;
    double yOffset = 0;
   @FXML
    private AnchorPane MainOrderProcessing;

    @FXML
    private TableView tblClients;

    @FXML
    private TableColumn clientsIndex01;

    @FXML
    private TableColumn clientsIndex02;

    @FXML
    private TableColumn clientsIndex03;

    @FXML
    private TableColumn clientsIndex04;

    @FXML
    private AnchorPane searchBar1;

    @FXML
    private TextField txtField01;

    @FXML
    private Label label01;

    @FXML
    private TextField txtField02;

    @FXML
    private Label label03;

    @FXML
    private Label label02;

    @FXML
    private Label label04;

    @FXML
    private Label label05;

    @FXML
    private Label label07;

    @FXML
    private Label label08;

    @FXML
    private Label label06;

    @FXML
    private Label lblOrder02;

    @FXML
    private Label lblOrder01;

    @FXML
    private TableView tblOrders;

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
    private TableView tblPaymenttype;

    @FXML
    private TableColumn paymentIndex01;

    @FXML
    private TableColumn paymentIndex02;

    @FXML
    private TableColumn paymentIndex03;

    @FXML
    private TableColumn paymentIndex04;

    @FXML
    private TableColumn paymentIndex05;

    @FXML
    private TableColumn paymentIndex06;
    @FXML
    private AnchorPane searchBar11;

    @FXML
    private TableView tblissueditems;

    @FXML
    private TableColumn issuedIndex01;

    @FXML
    private TableColumn issuedIndex02;

    @FXML
    private TableColumn issuedIndex03;

    @FXML
    private TableColumn issuedIndex04;

    @FXML
    private TableColumn issuedIndex05;

    @FXML
    private TableColumn issuedIndex06;

    @FXML
    private TextField txtFieldDet01;

    @FXML
    private TextField txtFieldDet02;

    @FXML
    private TextField txtFieldDet03;

    @FXML
    private TextField txtFieldDet04;

    @FXML
    private TextField txtFieldDet05;

    @FXML
    private CheckBox CheckBoxAG01;

    @FXML
    private TextField txtFieldDet06;

    @FXML
    private Label lblDetail01;

    @FXML
    private Label lblDetail02;

    @FXML
    private Label lblDetail03;

    @FXML
    private HBox hbButtons;

    @FXML
    private Button btnBrowse;

    @FXML
    private Button btnApproved;

    @FXML
    private Button btnDisapproved;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnUpdate1;

    @FXML
    private FontAwesomeIconView btnPrintOrder;

    @FXML
    private Button btnPrintIssuance;

    @FXML
    private AnchorPane searchBar;

    @FXML
    private DatePicker DateSeeks01;
    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtSeeks01;
       
    private ObservableList<OrderModel> data = FXCollections.observableArrayList();
    private ObservableList<OrderDetailModel> data1 = FXCollections.observableArrayList();
    private ObservableList<OrderDetailModel> data2 = FXCollections.observableArrayList();
    private ObservableList<OrderPaymentTaggingModel> data3 = FXCollections.observableArrayList();
    private FilteredList<OrderModel> filteredData;

    private Stage getStage(){
	return (Stage) txtField01.getScene().getWindow();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    oListener = new LResult() {
             @Override
             public void OnSave(String message) {
                System.out.println("OnSave = " + message);
                oTrans = new SalesOrder(oApp, oApp.getBranchCode(), false);
                oTrans.setListener(oListener);
                oTrans.setTranStat(10234);
                oTrans.setWithUI(true);
                pbLoaded = true;
                 loadOrders();
                 pnRow1 = -1;
                 loadOrderDetail(oldTransNox);
                 tblClients.getSelectionModel().select(oldPnRow);
             }
             @Override
             public void OnCancel(String message) {
                 pnRow1 = -1;
                 loadOrderDetail(oldTransNox);
                System.out.println("OnCancel = " + message);
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
            
            txtField02.setText(dateToWord(filteredData.get(pnRow).getOrderIndex03()));
            txtField01.setText(filteredData.get(pnRow).getOrderIndex02());
            label08.setText(filteredData.get(pnRow).getOrderIndex05());
            label02.setText(filteredData.get(pnRow).getOrderIndex15());
            label07.setText(filteredData.get(pnRow).getOrderIndex09());
            label03.setText(filteredData.get(pnRow).getOrderIndex14());
            label06.setText(priceWithDecimal(Double.valueOf(filteredData.get(pnRow).getOrderIndex05().replaceAll("[ |₱|,]+", "")) + Double.valueOf(filteredData.get(pnRow).getOrderIndex09().replaceAll("[₱ ,]+", ""))));
            label04.setText(filteredData.get(pnRow).getOrderIndex12());
            label05.setText(filteredData.get(pnRow).getOrderIndex04());
            label01.setText(filteredData.get(pnRow).getOrderIndex13());
            oldPnRow = pnRow;
             loadOrderDetail(filteredData.get(pnRow).getOrderIndex02());
        }    

        
        
        
    }
        private void loadMaster() throws SQLException {
        
        if (oTrans.getMaster(11).toString().equalsIgnoreCase("0")){
            lblStatus.setVisible(true);
            lblStatus.setText("OPEN");
        }else if(oTrans.getMaster(11).toString().equalsIgnoreCase("1")){
            lblStatus.setVisible(true);
            lblStatus.setText("CLOSED");
        }else if(oTrans.getMaster(11).toString().equalsIgnoreCase("2")){
            lblStatus.setVisible(true);
            lblStatus.setText("POSTED");
        }else if(oTrans.getMaster(11).toString().equalsIgnoreCase("3")){
            lblStatus.setVisible(true);
            lblStatus.setText("CANCELLED");
        }else{
            lblStatus.setVisible(false);
        }
        lblOrder01.setText(lblStatus.getText());
                    
        lblOrder02.setText(lblStatus.getText());
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
    private void loadOrderDetail(String  transNox){
        int lnCtr;
        try {
            data1.clear();
            oldTransNox = transNox;
            System.out.println(oldTransNox);
            System.out.println(oldPnRow);
            if (oTrans.OpenTransaction(transNox)){//true if by barcode; false if by description
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
                loadMaster(); 
                loadPaymentTagging();  
                IssuedOrderDetail();  
                
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
    private void IssuedOrderDetail(){
        int lnCtr;
        try {
            data2.clear();
            if (oTrans.OpenTransaction((String)oTrans.getMaster("sTransNox"))){//true if by barcode; false if by description
                for (lnCtr = 1; lnCtr <= oTrans.getItemCount(); lnCtr++){
                    data2.add(new OrderDetailModel(String.valueOf(lnCtr),
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
                initGrid2();
                
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
        private void loadPaymentTagging() {
       try {
            int lnCtr;
        
            data3.clear();
                 for (lnCtr = 1; lnCtr <= oTrans.getPaymentItemCount(); lnCtr++){
                    data3.add(new OrderPaymentTaggingModel(String.valueOf(lnCtr),
                            (String) oTrans.getPayment(lnCtr, "sTransNox"),
                            (String) oTrans.getPayment(lnCtr, "dTransact"),
                            (String) oTrans.getPayment(lnCtr, "sReferCde"),
                            (String) oTrans.getPayment(lnCtr, "sReferNox"),
                            (String) oTrans.getPayment(lnCtr, "nAmountxx"),                            
                            (String) oTrans.getPayment(lnCtr, "sSourceNo"),
                            (String) oTrans.getPayment(lnCtr, "cTranStat")               
                          
                    ));
                  
                }
                initGrid3();           
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
           if(oldPnRow >= 0){
           tblClients.getSelectionModel().select(oldPnRow);
       }
        
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
        orderIndex01.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex08"));
        orderIndex02.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex03"));
        orderIndex03.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex04"));
        orderIndex04.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex05"));
        orderIndex05.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex06"));
        orderIndex06.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex07"));
        orderIndex07.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex02"));
        orderIndex08.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex12"));   
        orderIndex09.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex12"));   
   

        // 5. Add sorted (and filtered) data to the table.
       tblOrders.setItems(data1);
        tblOrders.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblOrders.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
       
        
    }

    private void initGrid2() {
       
        issuedIndex01.setStyle("-fx-alignment: CENTER;");
        issuedIndex02.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        issuedIndex03.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        issuedIndex04.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        issuedIndex05.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        issuedIndex06.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        issuedIndex01.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex08"));
        issuedIndex02.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex05"));
        issuedIndex03.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex04"));
        issuedIndex04.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex05"));
        issuedIndex05.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex06"));
        issuedIndex06.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex07"));
        
   

        // 5. Add sorted (and filtered) data to the table.
       tblissueditems.setItems(data1);
        tblissueditems.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblissueditems.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
 
    }
    private void initGrid3() {
       
        paymentIndex01.setStyle("-fx-alignment: CENTER;");
        paymentIndex02.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        paymentIndex03.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        paymentIndex04.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        paymentIndex05.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        paymentIndex06.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        paymentIndex01.setCellValueFactory(new PropertyValueFactory<>("paymentIndex01"));
        paymentIndex02.setCellValueFactory(new PropertyValueFactory<>("paymentIndex02"));
        paymentIndex03.setCellValueFactory(new PropertyValueFactory<>("paymentIndex03"));
        paymentIndex04.setCellValueFactory(new PropertyValueFactory<>("paymentIndex04"));
        paymentIndex05.setCellValueFactory(new PropertyValueFactory<>("paymentIndex05"));
        paymentIndex06.setCellValueFactory(new PropertyValueFactory<>("paymentIndex06"));
        
   

        // 5. Add sorted (and filtered) data to the table.
       tblPaymenttype.setItems(data3);
        tblPaymenttype.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblPaymenttype.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
 
    }
            @FXML
            private void tblPaymenttype_Click (MouseEvent event) {
                    try {
                        pnRow1 = tblPaymenttype.getSelectionModel().getSelectedIndex(); 
                        System.out.println("pnRow1 = " + pnRow1);
                         loadPaymentDetail(data3.get(pnRow1).getPaymentIndex02(), pnRow1 ); 
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            ShowMessageFX.Warning(getStage(),ex.getMessage(), "Warning", null);
        }
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
//    private void unloadForm(){
 //       StackPane myBox = (StackPane) AnchorMain.getParent();
  //      myBox.getChildren().clear();
  //      myBox.getChildren().add(getScene("MainScreenBG.fxml"));
  //  }
       private void loadPaymentDetail(String fsCode, int fnRow) throws SQLException{
        try {
            Stage stage = new Stage();
            if(oTrans.UpdateTransaction()){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/org/rmj/marketplace/view/OrderPaymentTagging.fxml"));

                OrderPaymentTaggingController loControl = new OrderPaymentTaggingController();
                loControl.setGRider(oApp);
                loControl.setSalesOrder(oTrans);
                loControl.setPaymentCode(fsCode);
                loControl.setListener(oListener);
                loControl.setEditMode(EditMode.UPDATE);
                loControl.setTableRow(fnRow + 1);

                fxmlLoader.setController(loControl);

                //load the main interface
                Parent parent = fxmlLoader.load();

                parent.setOnMousePressed(new EventHandler<MouseEvent>() {
                   @Override
                    public void handle(MouseEvent event) {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    }
                });
                parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        stage.setX(event.getScreenX() - xOffset);
                        stage.setY(event.getScreenY() - yOffset);
                    }
               });

                //set the main interface as the scene
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("");
                stage.showAndWait();
            }
            
            
            loadOrders();
           
        } catch (IOException e) {
            e.printStackTrace();
        //    ShowMessageFX.Warning(getStage(),e.getMessage(), "Warning", null);
            System.exit(1);
        }
    }
    private AnchorPane setScene(){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("OrderPaymentTagging.fxml"));

            OrderPaymentTaggingController loControl = new OrderPaymentTaggingController();
           // loControl.setGRider(oApp);
           // loControl.setTransaction(oTransnox);
            fxmlLoader.setController(loControl);
            
            //load the main interface
                
          AnchorPane root;
        try {
            root = (AnchorPane) fxmlLoader.load();
            FadeTransition ft = new FadeTransition(Duration.millis(1500));
            ft.setNode(root);
            ft.setFromValue(1);
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();

            return root;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
        return null;
    }
}