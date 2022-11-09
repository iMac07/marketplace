/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this torderlate file, choose Tools | Torderlates
 * and open the torderlate in the editor.
 */
package org.rmj.marketplace.controller;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.rmj.appdriver.agentfx.ShowMessageFX;
import org.rmj.marketplace.base.LResult;
import org.rmj.marketplace.base.OrderList;
import org.rmj.marketplace.model.ClientInfoModel;
import org.rmj.marketplace.model.IssuedItemModel;
import org.rmj.marketplace.model.OrderPaymentTaggingModel;
import org.rmj.marketplace.model.ProductModel;


/**
 * FXML Controller class
 *
 * @author user
 */
public class OrderProcessingController implements Initializable, ScreenInterface {
    private final String pxeModuleName = "Order Processing";
    private GRider oApp;
    private OrderList oTrans;
    private LResult oListener;
    private boolean pbLoaded = false;
    private int pnRow = -1;
    private int pnRow1 = -1;
    private int oldPnRow = -1;
        private int pagecounter;
    private int pnEditMode;
    private String oldTransNox = "";
    private String oldPOSNox = "";
    private String transNox = "";
    double xOffset = 0;
    double yOffset = 0;
    private boolean state = false;

    @FXML
    private AnchorPane MainOrderProcessing;
    @FXML
    private Pagination pagination;
    @FXML
    private AnchorPane searchBar1;
    @FXML
    private Label label01,label02,label03,label04,
                    label05,label06,label07,label08,label09,
                   lblOrder02,lblOrder01,
                   lblDetail01,lblDetail02,lblDetail03,
                   lblStatus;
    @FXML
    private TableView tblOrders,tblPaymenttype,tblClients;
    @FXML
    private TableColumn clientsIndex01,clientsIndex02,
                      clientsIndex03,clientsIndex04;
    @FXML
    private TableColumn orderIndex01,orderIndex02,orderIndex03,
                         orderIndex04,orderIndex05,orderIndex06,
                         orderIndex07,orderIndex08,orderIndex09;
    @FXML
    private TableColumn paymentIndex01,paymentIndex02,paymentIndex03,
                        paymentIndex04,paymentIndex05,paymentIndex06;
  
    @FXML
    private AnchorPane searchBar11;

    @FXML
    private TableView tblissueditems;

    @FXML
    private TableColumn issuedIndex01,issuedIndex02,issuedIndex03,
                        issuedIndex04,issuedIndex05,issuedIndex06,issuedIndex07;
    @FXML
    private TextField txtFieldDet01,txtFieldDet02,txtFieldDet03,
                      txtFieldDet04,txtFieldDet05,txtFieldDet06;
    @FXML
    private CheckBox CheckBoxAG01;
    @FXML
    private HBox hbButtons;
    @FXML
    private AnchorPane searchBar;
    @FXML
    private DatePicker DateSeeks01;
    @FXML
    private TextField txtSeeks98,txtField01,txtField02;
    
    private static final int ROWS_PER_PAGE = 30; 
  
    private ObservableList<OrderModel> data = FXCollections.observableArrayList();
    private ObservableList<OrderDetailModel> data1 = FXCollections.observableArrayList();
    private ObservableList<IssuedItemModel> data2 = FXCollections.observableArrayList();
    private ObservableList<OrderPaymentTaggingModel> data3 = FXCollections.observableArrayList();
    private FilteredList<OrderModel> filteredData;
    private FilteredList<OrderDetailModel> filteredOrderDetData;
    private FilteredList<IssuedItemModel> filteredIssuedItem;

    public void setTransaction(String fsValue){
        transNox = fsValue;
    }
    private Stage getStage(){
	return (Stage) txtField01.getScene().getWindow();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    oListener = new LResult() {
             @Override
             public void OnSave(String message) {
                System.out.println("OnSave = " + message);
//                oTrans = new OrderList(oApp, oApp.getBranchCode(), false);
//                oTrans.setListener(oListener);
//                oTrans.setTranStat(012);
//                oTrans.setWithUI(true);
//                pbLoaded = true;
                loadOrders();
             }
             @Override
             public void OnCancel(String message) {
                System.out.println("OnCancel = " + message);
                
                loadOrders();
//                pnRow1 = -1;
//                loadOrderDetail(oldTransNox);
//                loadPaymentTagging(oldTransNox);
//                IssuedOrderDetail(oldPOSNox);
//                tblClients.getSelectionModel().select(oldPnRow);
                initButton(pnEditMode);
             }

        @Override
        public void MasterRetreive(int i, Object o) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
       
        };
        oTrans = new OrderList(oApp, oApp.getBranchCode(), false);
        oTrans.setListener(oListener);
        oTrans.setTranStat(102);
        oTrans.setWithUI(true);
                loadOrders();
        pnEditMode = EditMode.UNKNOWN;
        pagination.setPageFactory(this::createPage); 
        pbLoaded = true;
        
    }    
    public String getTransNox(){
        return transNox;
  }
    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, data.size());
        if(data.size()>0){
           tblClients.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex))); 
        }
        return tblClients;
    }
    
    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue; //To change body of generated methods, choose Tools | Torderlates.
    }

    @FXML
    private void tblClients_Clicked(MouseEvent event) {
            pnRow = tblClients.getSelectionModel().getSelectedIndex();
            pagecounter = pnRow + pagination.getCurrentPageIndex() * ROWS_PER_PAGE;
    if (pnRow >= 0){
        oldPnRow = pagecounter;
        if(event.getClickCount() > 0){
            if(!tblClients.getItems().isEmpty()){
            
                txtField02.setText(dateToWord(filteredData.get(pagecounter).getOrderIndex03()));
                txtField01.setText(filteredData.get(pagecounter).getOrderIndex02());
                label08.setText(filteredData.get(pagecounter).getOrderIndex05());
                label02.setText(filteredData.get(pagecounter).getOrderIndex15());
                label07.setText(filteredData.get(pagecounter).getOrderIndex09());
                label03.setText(filteredData.get(pagecounter).getOrderIndex14());
                label06.setText(priceWithDecimal(Double.valueOf(filteredData.get(pagecounter).getOrderIndex05().replaceAll("[ |₱|,]+", "")) + Double.valueOf(filteredData.get(pnRow).getOrderIndex09().replaceAll("[ |₱|,]+", ""))));
                label04.setText(filteredData.get(pagecounter).getOrderIndex12());
                label05.setText(filteredData.get(pagecounter).getOrderIndex04());
                label01.setText(filteredData.get(pagecounter).getOrderIndex13());
                oldPnRow = pagecounter;
                loadOrderDetail(filteredData.get(pagecounter).getOrderIndex02());
                loadPaymentTagging(filteredData.get(pagecounter).getOrderIndex02()); 
                IssuedOrderDetail(filteredData.get(pagecounter).getOrderIndex16());  
               } 
            }            
       }        
    }
    private void loadMaster() throws SQLException {
        
        if (oTrans.getMaster(11).toString().equalsIgnoreCase("0")){
            lblOrder02.setVisible(true);
            lblOrder02.setText("OPEN");
        }else if(oTrans.getMaster(11).toString().equalsIgnoreCase("1")){
            lblOrder02.setVisible(true);
            lblOrder02.setText("CLOSED");
        }else if(oTrans.getMaster(11).toString().equalsIgnoreCase("2")){
            lblOrder02.setVisible(true);
            lblOrder02.setText("POSTED");
        }else if(oTrans.getMaster(11).toString().equalsIgnoreCase("3")){
            lblOrder02.setVisible(true);
            lblOrder02.setText("CANCELLED");
        }else{
            lblStatus.setVisible(false);
        }

   }
 
    private void loadOrders(){
        int lnCtr;
        try {
            data.clear();
            if (oTrans.LoadList("", true)){//true if by barcode; false if by description
//                oTrans.displayMasFields();
                for (lnCtr = 1; lnCtr <= oTrans.getItemCount(); lnCtr++){
                    
                    String adrress1 = (String) oTrans.getDetail(lnCtr, "sHouseNo1") +  " " +
                            (String) oTrans.getDetail(lnCtr, "sAddress1") + " " +
                            (String) oTrans.getDetail(lnCtr, "sBrgyNme1") + ", " +
                            (String) oTrans.getDetail(lnCtr, "sTownNme1") + ", " +
                            (String) oTrans.getDetail(lnCtr, "sProvNme1");
                    String adrress2 = (String) oTrans.getDetail(lnCtr, "sHouseNo2") +  " " +
                            (String) oTrans.getDetail(lnCtr, "sAddress2") + " " +
                            (String) oTrans.getDetail(lnCtr, "sBrgyNme2") + ", " +
                            (String) oTrans.getDetail(lnCtr, "sTownNme2") + ", " +
                            (String) oTrans.getDetail(lnCtr, "sProvNme2");
                    
                    data.add(new OrderModel(String.valueOf(lnCtr),
                            (String) oTrans.getDetail(lnCtr, "sTransNox"),
                            oTrans.getDetail(lnCtr, "dTransact").toString(),
                            (String) oTrans.getDetail(lnCtr, "sTermName"),
                            priceWithDecimal(Double.valueOf(oTrans.getDetail(lnCtr, "nTranTotl").toString())),
                            priceWithDecimal(Double.valueOf(oTrans.getDetail(lnCtr, "nVATRatex").toString())),
                            priceWithDecimal(Double.valueOf(oTrans.getDetail(lnCtr, "nDiscount").toString())),
                            priceWithDecimal(Double.valueOf(oTrans.getDetail(lnCtr, "nAddDiscx").toString())),
                            priceWithDecimal(Double.valueOf(oTrans.getDetail(lnCtr, "nFreightx").toString())),
                            priceWithDecimal(Double.valueOf(oTrans.getDetail(lnCtr, "nAmtPaidx").toString())),
                            (String) oTrans.getDetail(lnCtr, "cTranStat"),
                            (String) oTrans.getDetail(lnCtr, "sRemarksx"),
                            (String) oTrans.getDetail(lnCtr, "sCompnyNm"),
                            adrress1,
                            adrress2,
                            (String) oTrans.getDetail(lnCtr, "sPOSNoxxx")));
                  
                }
                
                initGrid();
                loadTab();
                
            }    
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException " + ex.getMessage());
        } catch (DateTimeException ex) {
            System.out.println("DateTimeException" + ex.getMessage());
        } 

    }
    private void loadOrderDetail(String  transNox){
        int lnCtr;
        try {
            data1.clear();
            oldTransNox = transNox;
            if (oTrans.LoadOrderDetail(oldTransNox,true)){//true if by barcode; false if by description
                for (lnCtr = 1; lnCtr <= oTrans.getDetailItemCount(); lnCtr++){
                    data1.add(new OrderDetailModel(String.valueOf(lnCtr),
                            (String) oTrans.getDetailItem(lnCtr, "sTransNox"),
                            (String) oTrans.getDetailItem(lnCtr, "xBarCodex"),
                            (String) oTrans.getDetailItem(lnCtr, "xBrandNme"),
                            (String) oTrans.getDetailItem(lnCtr, "xModelNme"),
                            (String) oTrans.getDetailItem(lnCtr, "xColorNme"),
                            oTrans.getDetailItem(lnCtr, "nEntryNox").toString(),
                            oTrans.getDetailItem(lnCtr, "nQuantity").toString(),
                            priceWithDecimal(Double.valueOf(oTrans.getDetailItem(lnCtr, "nUnitPrce").toString())),
                            (String) oTrans.getDetailItem(lnCtr, "sReferNox"),
                            "",
                            ""
                    )); 
                }
                initGrid1();
                loadMaster(); 
                
                
            }  else{
                MsgBox.showOk(oTrans.getMessage());
            }  
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException loadOrderDetail "  + ex.getMessage());
        } catch (DateTimeException ex) {
//            MsgBox.showOk(ex.getMessage());
            System.out.println("DateTimeException" + ex.getMessage());
        } 
    }
    private void IssuedOrderDetail(String transNox ){
                int lnCtr;
        try {
            data2.clear();
            oldPOSNox = transNox;
            if (oTrans.LoadIssuedItem(transNox)){
                for (lnCtr = 1; lnCtr <= oTrans.getIssuedItemCount(); lnCtr++){
                    data2.add(new IssuedItemModel(String.valueOf(lnCtr),
                            (String) oTrans.getIssued(lnCtr, "sSerialID"),
                            (String) oTrans.getIssued(lnCtr, "xBarCodex"),
                            (String) oTrans.getIssued(lnCtr, "xModelNme"),
                            (String) oTrans.getIssued(lnCtr, "xColorNme"),
                            oTrans.getIssued(lnCtr, "xQuantity").toString(),
                            priceWithDecimal(Double.parseDouble(oTrans.getIssued(lnCtr, "nSelPrice").toString()))
                    ));
                  
                }
                initGrid2();
                
            }else{
                System.out.println(oTrans.getMessage());
            } 
        } catch (SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException = " + ex.getMessage());
        } catch (DateTimeException ex) {
//            MsgBox.showOk(ex.getMessage());
            System.out.println("DateTimeException " + ex.getMessage());
        } 
}
        private void loadPaymentTagging(String sTransNox) {
       try {
            int lnCtr;
        
            data3.clear();
            if(oTrans.LoadPayment(sTransNox)){
                for (lnCtr = 1; lnCtr <= oTrans.getPaymentItemCount(); lnCtr++){
                    data3.add(new OrderPaymentTaggingModel(String.valueOf(lnCtr),
                            (String) oTrans.getPayment(lnCtr, "sTransNox"),
                            oTrans.getPayment(lnCtr, "sReferNox").toString(),
                            (String) oTrans.getPayment(lnCtr, "sTermName"),
                            priceWithDecimal(Double.valueOf(oTrans.getPayment(lnCtr, "nAmtPaidx").toString())),     
                            priceWithDecimal(Double.valueOf(oTrans.getPayment(lnCtr, "nTotlAmnt").toString())),                           
                            (String) oTrans.getPayment(lnCtr, "sSourceNo"),
                            (String) oTrans.getPayment(lnCtr, "cTranStat")               
                          
                    ));
                  
                }
            }
                 
                initGrid3();           
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException payment " + ex.getMessage());
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
        autoSearch(txtSeeks98);
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
            header.setDisable(true);
        });
        if(oldPnRow >= 0){
//           tblClients.getSelectionModel().select(oldPnRow);
           
            pnRow1 = -1;
            loadOrderDetail(oldTransNox);
            loadPaymentTagging(oldTransNox);
            IssuedOrderDetail(oldPOSNox);
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
        orderIndex01.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex01"));
        orderIndex02.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex03"));
        orderIndex03.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex04"));
        orderIndex04.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex05"));
        orderIndex05.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex06"));
        orderIndex06.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex08"));

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
        issuedIndex06.setStyle("-fx-alignment: CENTER-RIGHT;-fx-padding: 0 5 0 0;");
        issuedIndex07.setStyle("-fx-alignment: CENTER-RIGHT;-fx-padding: 0 5 0 0;");
        issuedIndex01.setCellValueFactory(new PropertyValueFactory<>("index01"));
        issuedIndex02.setCellValueFactory(new PropertyValueFactory<>("index02"));
        issuedIndex03.setCellValueFactory(new PropertyValueFactory<>("index03"));
        issuedIndex04.setCellValueFactory(new PropertyValueFactory<>("index04"));
        issuedIndex05.setCellValueFactory(new PropertyValueFactory<>("index05"));
        issuedIndex06.setCellValueFactory(new PropertyValueFactory<>("index06"));
        issuedIndex07.setCellValueFactory(new PropertyValueFactory<>("index07"));
        
       tblissueditems.setItems(data2);
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
        paymentIndex05.setStyle("-fx-alignment: CENTER-RIGHT;-fx-padding: 0 5 0 0;");
        paymentIndex06.setStyle("-fx-alignment: CENTER-RIGHT;-fx-padding: 0 5 0 0;");
        paymentIndex01.setCellValueFactory(new PropertyValueFactory<>("paymentIndex01"));
        paymentIndex02.setCellValueFactory(new PropertyValueFactory<>("paymentIndex02"));
        paymentIndex03.setCellValueFactory(new PropertyValueFactory<>("paymentIndex03"));
        paymentIndex04.setCellValueFactory(new PropertyValueFactory<>("paymentIndex04"));
        paymentIndex05.setCellValueFactory(new PropertyValueFactory<>("paymentIndex05"));
        paymentIndex06.setCellValueFactory(new PropertyValueFactory<>("paymentIndex06"));

   

        // 5. Add sorted (and filtered) data to the table.
        tblPaymenttype.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblPaymenttype.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
       tblPaymenttype.setItems(data3);
 
    }
//    private void autoSearch(TextField txtField){
//        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
//        boolean fsCode = true;
//        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(clients-> {
//                // If filter text is empty, display all persons.
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                // Compare first name and last name of every person with filter text.
//                String lowerCaseFilter = newValue.toLowerCase();
//                if(lnIndex == 98){
//                    return (clients.getOrderIndex02().toLowerCase().contains(lowerCaseFilter)); // Does not match.   
//                }else {
//                    return (clients.getOrderIndex02().toLowerCase().contains(lowerCaseFilter)); // Does not match.
//                }
//            });
//            changeTableView(0, ROWS_PER_PAGE);
//        });
//        loadTab();
//} 
    
    private void autoSearch(TextField txtField){
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        boolean fsCode = true;
        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(orders-> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare order no. and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if(lnIndex == 98){
                    return (orders.getOrderIndex02().toLowerCase().contains(lowerCaseFilter)); // Does not match.   
                }else {
                    return (orders.getOrderIndex02().toLowerCase().contains(lowerCaseFilter)); // Does not match.
                }
            });
            changeTableView(0, ROWS_PER_PAGE);
        });
        loadTab();
    } 
    private void loadTab(){

                int totalPage = (int) (Math.ceil(data.size() * 1.0 / ROWS_PER_PAGE));
                pagination.setPageCount(totalPage);
                pagination.setCurrentPageIndex(0);
                changeTableView(0, ROWS_PER_PAGE);
                pagination.currentPageIndexProperty().addListener(
                        (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));
            
    } 
    private void changeTableView(int index, int limit) {
        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, data.size());
        

            int minIndex = Math.min(toIndex, filteredData.size());
            SortedList<OrderModel> sortedData = new SortedList<>(
                    FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
            sortedData.comparatorProperty().bind(tblClients.comparatorProperty());
            tblClients.setItems(sortedData);
                
    }

    @FXML
    private void tblPaymenttype_Click (MouseEvent event) {
       
        pnRow1 = tblPaymenttype.getSelectionModel().getSelectedIndex(); 
        if (pnRow1 >= 0){ 
            if(event.getClickCount()>0){
                System.out.println("event click count = " + event.getClickCount());
                if(!tblPaymenttype.getItems().isEmpty()){
                    try {
                        pnRow1 = tblPaymenttype.getSelectionModel().getSelectedIndex(); 
                        loadPaymentDetail(data3.get(pnRow1).getPaymentIndex02(), pnRow1); 

                    } catch (SQLException ex) {
                      ex.printStackTrace();
                      ShowMessageFX.Warning(getStage(),ex.getMessage(), "Warning", null);
                    }
                }
            }
        }      
    }
    private void initButton(int fnValue){
         boolean lbShow = (fnValue == EditMode.ADDNEW || fnValue == EditMode.UPDATE);
                
                tblOrders.setDisable(!lbShow);
//                tblPaymenttype.setDisable(lbShow);
                tblissueditems.setDisable(!lbShow);

    }
    public static String priceWithDecimal (Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(price);
    }
    public static String dateToWord (String dtransact) {
       
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date = new Date();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = (Date)formatter.parse(dtransact);  
            SimpleDateFormat fmt = new SimpleDateFormat("MMM dd, yyyy");
            String todayStr = fmt.format(date);
            
            return todayStr;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
public static String dateToWord1 (String dtransact) {
       
        SimpleDateFormat dateParser1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
//            Date date = dateParser.parse("2022-04-29 16:59:13");
//            SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
//             String str_date="2012-08-11+05:30";
//           
            Date date = new Date();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = (Date)formatter.parse(dtransact);  
            SimpleDateFormat fmt = new SimpleDateFormat("MMM dd, yyyy");
            String todayStr = fmt.format(date);
            
            return todayStr;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

       private void loadPaymentDetail(String psCode, int fnRow) throws SQLException{
        try {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/org/rmj/marketplace/view/OrderPaymentTagging.fxml"));
                  
                OrderPaymentTaggingController loControl = new OrderPaymentTaggingController();
                loControl.setGRider(oApp);
                loControl.setSalesOrder(oTrans);
                loControl.setPaymentCode(psCode);
                loControl.setListener(oListener);
                loControl.setEditMode(EditMode.UPDATE);
                loControl.setTableRow(fnRow + 1);
                loControl.setDateTransact(txtField02.getText().toString());

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