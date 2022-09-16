/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.controller;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.net.URL;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.rmj.marketplace.model.ScreenInterface;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agent.MsgBox;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.appdriver.agentfx.ShowMessageFX;
import org.rmj.appdriver.constants.EditMode;
import org.rmj.marketplace.base.CPEcommerce;
import org.rmj.marketplace.base.LResult;
import org.rmj.marketplace.base.LTransaction;
import org.rmj.marketplace.base.ProductReviews;
import org.rmj.marketplace.base.WayBill;
import static org.rmj.marketplace.controller.OrderProcessingController.dateToWord;
import static org.rmj.marketplace.controller.OrderProcessingController.priceWithDecimal;
import org.rmj.marketplace.model.ClientInfoModel;
import org.rmj.marketplace.model.OrderDetailModel;
import org.rmj.marketplace.model.OrderModel;
import org.rmj.marketplace.model.OrderPaymentTaggingModel;
import org.rmj.marketplace.model.ProductModel;
import org.rmj.marketplace.model.RatingsReviewModel;

/**
 * FXML Controller class
 *
 * @author user
 */
public class WayBillController implements Initializable, ScreenInterface {
    private String recdstat;
    private GRider oApp;
    private WayBill oTrans;
    private LTransaction  oListener;
    private boolean pbLoaded = false;
    private int pnRow = -1;
    private int pnRow1 = -1;
    private int oldPnRow = -1;
        private int pagecounter;
    private int pnEditMode;
    private String oldTransNox = "";
    
    private ToggleGroup rbCCGroup;
    private ToggleGroup rbSAGroup;
    private ToggleGroup rbBillGroup;
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private Pagination pagination;
    @FXML
    private RadioButton rbCCRegular;
    @FXML
    private RadioButton rbCCPerishable;
    @FXML
    private RadioButton rbCCDG;
    @FXML
    private RadioButton rbCCHV;
    @FXML
    private RadioButton rbCCHeavyWeight;
    @FXML
    private RadioButton rbSAStore;
    @FXML
    private RadioButton rbSAPickup;
    @FXML
    private RadioButton rbBillShipper;
    @FXML
    private RadioButton rbBillConsignee;
    @FXML
    private RadioButton rbBillThirdParty;
    @FXML
    private TextField txtField01;
    @FXML
    private TextField txtField02;
    @FXML
    private TextField txtField03;
    @FXML
    private TextField txtField04;
    @FXML
    private TextField txtField05;
    @FXML
    private TextField txtField06;
    @FXML
    private TextField txtField07;
    @FXML
    private TextField txtField08;
    @FXML
    private TextField txtField09;
    @FXML
    private TextField txtField10;
    @FXML
    private TextField txtSeeks98;
    @FXML
    private TextField txtSeeks99;
    @FXML
    private Label lblDetail01;
    @FXML
    private Label lblDetail02;
    @FXML
    private Label lblDetail03;
    @FXML
    private Label lblDetail04;
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
    private TableView tblOrderDetail;
    @FXML
    private TableColumn orderDetailIndex01;
    @FXML
    private TableColumn orderDetailIndex02;
    @FXML
    private TableColumn orderDetailIndex03;
    @FXML
    private TableColumn orderDetailIndex04;
    @FXML
    private TableColumn orderDetailIndex05;
    @FXML
    private TableColumn orderDetailIndex06;
    @FXML
    private TableColumn orderDetailIndex07;
    @FXML
    private TableColumn orderDetailIndex08;
    @FXML
    private TableColumn orderDetailIndex09;
    
    private static final int ROWS_PER_PAGE = 30;

    private final ObservableList<OrderModel> order_data = FXCollections.observableArrayList();
    private ObservableList<OrderDetailModel> order_detail = FXCollections.observableArrayList();
    private ObservableList<OrderPaymentTaggingModel> data3 = FXCollections.observableArrayList();
    private FilteredList<OrderModel> filteredData;
    private FilteredList<OrderDetailModel> filteredOrderDetData;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oListener = new LTransaction(){
            @Override
            public void MasterRetreive(int fnIndex, Object foValue) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
            
        };
             
        oTrans = new WayBill(oApp, oApp.getBranchCode(), false);
        oTrans.setListener(oListener);
        oTrans.setTranStat(12);
        oTrans.setWithUI(true);
        pbLoaded = true;
        loadOrders();
        pnEditMode = EditMode.UNKNOWN;
        
        pagination.setPageFactory(this::createPage); 
        txtField02.setOnKeyPressed(this::txtField_KeyPressed); 
//        pagination.setPageFactory(this::createPage); 
    }    

    private Stage getStage(){
	return (Stage) txtField02.getScene().getWindow();
    }
    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue;
    }
    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, order_data.size());
        if(order_data.size()>0){
            tblOrders.setItems(FXCollections.observableArrayList(order_data.subList(fromIndex, toIndex)));
        }
       
        return tblOrders;

    }
    private void initToggleGroup(){
        //COMMODITY CLASS
        rbCCGroup = new ToggleGroup();
        rbCCRegular.setToggleGroup(rbCCGroup);
        rbCCPerishable.setToggleGroup(rbCCGroup);
        rbCCDG .setToggleGroup(rbCCGroup);
        rbCCHV.setToggleGroup(rbCCGroup);
        rbCCHeavyWeight.setToggleGroup(rbCCGroup);
        rbCCHeavyWeight.setSelected(true);
        
        //SHIPMENT ACCEPTANCE
        rbSAGroup = new ToggleGroup();
        rbSAStore.setToggleGroup(rbSAGroup);
        rbSAPickup.setToggleGroup(rbSAGroup);
        //PAYMENT
        rbBillGroup = new ToggleGroup();
        rbBillShipper.setToggleGroup(rbBillGroup);
        rbBillConsignee.setToggleGroup(rbBillGroup);
        rbBillThirdParty.setToggleGroup(rbBillGroup);
    }
    private void loadOrders(){
        int lnCtr;
        try {
            order_data.clear();
            if (oTrans.LoadOrder("")){//true if by barcode; false if by description
               
                for (lnCtr = 1; lnCtr <= oTrans.getOrderItemCount(); lnCtr++){
                    order_data.add(new OrderModel(String.valueOf(lnCtr),
                            (String) oTrans.getOrder(lnCtr, "sTransNox"),
                            oTrans.getOrder(lnCtr, "dTransact").toString(),
                            (String) oTrans.getOrder(lnCtr, "sTermIDxx"),
                            priceWithDecimal(Double.valueOf(oTrans.getOrder(lnCtr, "nTranTotl").toString())),
                            priceWithDecimal(Double.valueOf(oTrans.getOrder(lnCtr, "nVATRatex").toString())),
                            priceWithDecimal(Double.valueOf(oTrans.getOrder(lnCtr, "nDiscount").toString())),
                            priceWithDecimal(Double.valueOf(oTrans.getOrder(lnCtr, "nAddDiscx").toString())),
                            priceWithDecimal(Double.valueOf(oTrans.getOrder(lnCtr, "nFreightx").toString())),
                            priceWithDecimal(Double.valueOf(oTrans.getOrder(lnCtr, "nAmtPaidx").toString())),
                            (String) oTrans.getOrder(lnCtr, "cTranStat"),
                            (String) oTrans.getOrder(lnCtr, "sRemarksx"),
                            (String) oTrans.getOrder(lnCtr, "sCompnyNm"),
                            (String) oTrans.getOrder(lnCtr, "sAddressx"),
                            (String) oTrans.getOrder(lnCtr, "sTownName"),
                            (String) oTrans.getOrder(lnCtr, "sMobileNo"),
                            (String) oTrans.getOrder(lnCtr, "sEmailAdd"),
                            (String) oTrans.getOrder(lnCtr, "sWaybilNo")));
                  
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
            order_detail.clear();
            oldTransNox = transNox;
            if (oTrans.LoadOrderDetail(transNox,true)){//true if by barcode; false if by description
                for (lnCtr = 1; lnCtr <= oTrans.getDetailItemCount(); lnCtr++){
                    order_detail.add(new OrderDetailModel(String.valueOf(lnCtr),
                            (String) oTrans.getDetailItem(lnCtr, "sTransNox"),
                            (String) oTrans.getDetailItem(lnCtr, "xBarCodex"),
                            (String) oTrans.getDetailItem(lnCtr, "xBrandNme"),
                            (String) oTrans.getDetailItem(lnCtr, "xModelNme"),
                            (String) oTrans.getDetailItem(lnCtr, "xColorNme"),
                            (String) oTrans.getDetailItem(lnCtr, "nEntryNox").toString(),
                            (String) oTrans.getDetailItem(lnCtr, "nQuantity").toString(),
                            priceWithDecimal(Double.valueOf((String) oTrans.getDetailItem(lnCtr, "nUnitPrce"))),
                            (String) oTrans.getDetailItem(lnCtr, "sReferNox"),
                            "",
                            ""
                    ));
//                lblOrder01.setText(filteredData.get(oldPnRow).getOrderIndex02());  
                }
                initGrid1();
//                loadMaster(); 
//                loadPaymentTagging();  
//                IssuedOrderDetail(filteredData.get(pnRow).getOrderIndex02());  
                
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
    private void loadWayBill(String tansNox){
        try{
            int lnCtr;
            System.out.println("LoadWayBill = " + tansNox);
            if(oTrans.LoadWayBill(tansNox)){
                for (lnCtr = 1; lnCtr <= oTrans.getDetailItemCount(); lnCtr++){
                    initToggleGroup();
                    txtField01.setText((String)oTrans.getMaster("sTransNox"));
                    txtField02.setText((String)oTrans.getMaster("sPackngDs"));
                    txtField03.setText((String)oTrans.getMaster("sTrackrNo"));
                    txtField04.setText(oTrans.getMaster("nTotlWght").toString());
                    txtField05.setText(oTrans.getMaster("nTotlPckg").toString());
                    txtField06.setText(oTrans.getMaster("sClientRf").toString());
                    txtField08.setText(oTrans.getMaster("nDimnsnLx").toString());
                    txtField09.setText(oTrans.getMaster("nDimnsnWx").toString());
                    txtField10.setText(oTrans.getMaster("nDimnsnHx").toString());
                    txtField07.setText((String)oTrans.getMaster("sAir21Str"));
                    int lnValue = Integer.parseInt(oTrans.getMaster(10).toString());
                    ((RadioButton)rbCCGroup.getToggles().get(lnValue)).setSelected(true);
                    int lnValue1 = Integer.parseInt(oTrans.getMaster(12).toString());
                    ((RadioButton)rbSAGroup.getToggles().get(lnValue1)).setSelected(true);
                    int lnValue2 = Integer.parseInt(oTrans.getMaster(14).toString());
                    ((RadioButton)rbBillGroup.getToggles().get(lnValue2)).setSelected(true);
                }
            }else{
                System.out.println("SQLException" + oTrans.getMessage());
            }
           
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException " + ex.getMessage());
        } catch (DateTimeException ex) {
//            MsgBox.showOk(ex.getMessage());
            System.out.println("DateTimeException " + ex.getMessage());
        } 
    }
    private void initGrid() {
        orderIndex01.setStyle("-fx-alignment: CENTER;");
        orderIndex02.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderIndex03.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderIndex04.setStyle("-fx-alignment: CENTER-RIGHT;-fx-padding: 0 10 0 0;");
        orderIndex01.setCellValueFactory(new PropertyValueFactory<>("orderIndex01"));
        orderIndex02.setCellValueFactory(new PropertyValueFactory<>("orderIndex02"));
        orderIndex03.setCellValueFactory(new PropertyValueFactory<>("orderIndex13"));
        orderIndex04.setCellValueFactory(new PropertyValueFactory<>("orderIndex05"));
       
        filteredData = new FilteredList<>(order_data, b -> true);
        autoSearch(txtSeeks98);
        autoSearch(txtSeeks99);
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<OrderModel> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tblOrders.comparatorProperty());
      
        
        // 5. Add sorted (and filtered) data to the table.
       tblOrders.setItems(sortedData);
       tblOrders.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblOrders.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
           if(oldPnRow >= 0){
           tblOrders.getSelectionModel().select(oldPnRow);
       }
        
    }
    private void autoSearch(TextField txtField){
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        boolean fsCode = true;
        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(clients-> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if(lnIndex == 98){
                    return (clients.getOrderIndex02().toLowerCase().contains(lowerCaseFilter)); // Does not match.   
                }else {
                    return (clients.getOrderIndex13().toLowerCase().contains(lowerCaseFilter)); // Does not match.
                }
            });
            changeTableView(0, ROWS_PER_PAGE);
        });
        loadTab();
    }  
    private void loadTab(){
                int totalPage = (int) (Math.ceil(order_data.size() * 1.0 / ROWS_PER_PAGE));
                pagination.setPageCount(totalPage);
                pagination.setCurrentPageIndex(0);
                changeTableView(0, ROWS_PER_PAGE);
                pagination.currentPageIndexProperty().addListener(
                        (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));
            
    }   
    private void changeTableView(int index, int limit) {
        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, order_data.size());

            int minIndex = Math.min(toIndex, filteredData.size());
            SortedList<OrderModel> sortedData = new SortedList<>(
                    FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
            sortedData.comparatorProperty().bind(tblOrders.comparatorProperty());
            tblOrders.setItems(sortedData); 
    }
    private void initGrid1() {
        orderDetailIndex01.setStyle("-fx-alignment: CENTER;");
        orderDetailIndex02.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderDetailIndex03.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderDetailIndex04.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderDetailIndex05.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderDetailIndex06.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderDetailIndex01.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex01"));
        orderDetailIndex02.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex03"));
        orderDetailIndex03.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex04"));
        orderDetailIndex04.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex05"));
        orderDetailIndex05.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex06"));
        orderDetailIndex06.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex08"));   
   

        // 5. Add sorted (and filtered) data to the table.
       tblOrderDetail.setItems(order_detail);
        tblOrderDetail.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblOrderDetail.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
       
        
    }
    @FXML
    private void tblOrders_Clicked(MouseEvent event) {
        pnRow = tblOrders.getSelectionModel().getSelectedIndex();
        pagecounter = pnRow + pagination.getCurrentPageIndex() * ROWS_PER_PAGE;   
        if (pagecounter >= 0){
            
            if(!tblOrders.getItems().isEmpty()){
                lblDetail01.setText(filteredData.get(pagecounter).getOrderIndex13());
                lblDetail02.setText(filteredData.get(pagecounter).getOrderIndex16());
                lblDetail03.setText(filteredData.get(pagecounter).getOrderIndex17());
                lblDetail04.setText(filteredData.get(pagecounter).getOrderIndex14() +", " + filteredData.get(pagecounter).getOrderIndex15());
                oldPnRow = pagecounter;
                loadOrderDetail(filteredData.get(pagecounter).getOrderIndex02());
                loadWayBill(filteredData.get(pagecounter).getOrderIndex18());
            }
        } 

        
        

    }
    
    private void txtField_KeyPressed(KeyEvent event){
        TextField txtField = (TextField)event.getSource();        
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        try{
           switch (event.getCode()){
            case F3:
            case ENTER:
                switch (lnIndex){
                    case 2: /*Search*/
                        
                        if (oTrans.SearchPacking(txtField02.getText(),false)){
//                               
                            txtField02.setText((String) oTrans.getMaster(16));
                                
                                pnEditMode = oTrans.getEditMode();
                            } else 
                                ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                        break;
                    
                }
            
        } 
        }catch(SQLException e){
                ShowMessageFX.Warning(getStage(),e.getMessage(), "Warning", null);
        }
        switch (event.getCode()){
        case ENTER:
        case DOWN:
            CommonUtils.SetNextFocus(txtField);
            break;
        case UP:
            CommonUtils.SetPreviousFocus(txtField);
        }
        
    }
}
