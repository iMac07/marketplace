/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.rmj.marketplace.controller;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.rmj.marketplace.model.ScreenInterface;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.MiscUtil;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.appdriver.constants.EditMode;
import org.rmj.marketplace.base.LTransaction;
import org.rmj.marketplace.base.Pickup;
import org.rmj.marketplace.base.WayBill;
import static org.rmj.marketplace.controller.OrderProcessingController.dateToWord;
import static org.rmj.marketplace.controller.OrderProcessingController.dateToWord1;
import static org.rmj.marketplace.controller.OrderProcessingController.priceWithDecimal;
import org.rmj.marketplace.model.OrderDetailModel;
import org.rmj.marketplace.model.OrderModel;
import org.rmj.marketplace.model.OrderPaymentTaggingModel;
import org.rmj.marketplace.model.PickupModel;
import org.rmj.marketplace.model.WaybillModel;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PickupController implements Initializable, ScreenInterface {

    private String recdstat;
    private GRider oApp;
    private Pickup oTrans;
    private LTransaction  oListener;
    private boolean pbLoaded = false;
    private int pnRow = -1;
    private int pnRow1 = -1;
    private int oldPnRow = -1;
        private int pagecounter;
    private int pnEditMode;
    private String oldTransNox = "";
    
    @FXML
    private Pagination pagination;
    @FXML
    private AnchorPane PickUpMain;
    @FXML
    private AnchorPane searchBar;
    @FXML
    private TextField txtField01;
    @FXML
    private TextField txtField02;
    @FXML
    private TextArea txtField03;
    @FXML
    private TextField txtField04;
    @FXML
    private TextField txtField05;
    @FXML
    private TableView<?> tblClients;
    @FXML
    private TableColumn clientsIndex01;
    @FXML
    private TableColumn clientsIndex02;
    @FXML
    private TableColumn clientsIndex03;
    @FXML
    private TableColumn clientsIndex04;
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
    private AnchorPane searchBar1;
    @FXML
    private AnchorPane searchBar11;
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
    private TableView tblWaybill;
    @FXML
    private TableColumn billIndex01;
    @FXML
    private TableColumn billIndex02;
    @FXML
    private TableColumn billIndex03;
    @FXML
    private TableColumn billIndex04;
    @FXML
    private TableColumn billIndex05;
    @FXML
    private TableColumn billIndex06;
    @FXML
    private TableColumn billIndex07;
    @FXML
    private TableColumn billIndex08;
    @FXML
    private TextField txtSeeks98;
    @FXML
    private TextField txtSeeks99;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    private static final int ROWS_PER_PAGE = 50;

    private final ObservableList<PickupModel> data = FXCollections.observableArrayList();
    private ObservableList<WaybillModel> waybill_data = FXCollections.observableArrayList();
    private FilteredList<PickupModel> filteredData;
    private FilteredList<WaybillModel> filteredWaybillData;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         oListener = new LTransaction(){
            @Override
            public void MasterRetreive(int fnIndex, Object foValue) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
            
        };
             
        oTrans = new Pickup(oApp, oApp.getBranchCode(), false);
        oTrans.setListener(oListener);
        oTrans.setTranStat(012);
        oTrans.setWithUI(true);
        pbLoaded = true;
        loadList();
        pnEditMode = EditMode.UNKNOWN;
        
        pagination.setPageFactory(this::createPage); 
        
    }    
    
    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue;
    }
    
    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, data.size());
        if(data.size()>0){
            tblOrders.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
        }
        return tblOrders;

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
                    return (clients.getIndex02().toLowerCase().contains(lowerCaseFilter)); // Does not match.   
                }else {
                    return (clients.getIndex03().toLowerCase().contains(lowerCaseFilter)); // Does not match.
                }
            });
            changeTableView(0, ROWS_PER_PAGE);
        });
        loadTab();
    }  
    private void loadTab(){
        int totalPage = (int) (Math.ceil(data.size() * 1.0 / ROWS_PER_PAGE));
       
        if(data.size()>0){
            pagination.setPageCount(totalPage);
        }else{
            pagination.setPageCount(1);
        }
        
        pagination.setCurrentPageIndex(0);
        changeTableView(0, ROWS_PER_PAGE);
        pagination.currentPageIndexProperty().addListener(
                (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));
            
    } 
    private void changeTableView(int index, int limit) {
        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, data.size());
       
        int minIndex = Math.min(toIndex, filteredData.size());
         System.out.println(toIndex);
        SortedList<PickupModel> sortedData = new SortedList<>(
                FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
        sortedData.comparatorProperty().bind(tblOrders.comparatorProperty());
        tblOrders.setItems(sortedData); 
    }
    private void loadList(){
        int lnCtr;
        try {
            data.clear();
            if (oTrans.LoadList()){//true if by barcode; false if by description
               
                for (lnCtr = 1; lnCtr <= oTrans.getItemCount(); lnCtr++){
                    data.add(new PickupModel(String.valueOf(lnCtr),
                            (String) oTrans.getDetail(lnCtr, "sBatchNox"),
                            (dateToWord(oTrans.getDetail(lnCtr, "dTransact").toString())),
                            (String) oTrans.getDetail(lnCtr, "sRemarksx"),
                             dateToWord(oTrans.getDetail(lnCtr, "dPickedUp").toString()),
                            (String) oTrans.getDetail(lnCtr, "sPickedBy"),
                            (String) oTrans.getDetail(lnCtr, "cTranStat")));
                  
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
    private void initGrid() {
        orderIndex01.setStyle("-fx-alignment: CENTER;");
        orderIndex02.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderIndex03.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderIndex04.setStyle("-fx-alignment: CENTER-RIGHT;-fx-padding: 0 10 0 0;");
        orderIndex01.setCellValueFactory(new PropertyValueFactory<>("index01"));
        orderIndex02.setCellValueFactory(new PropertyValueFactory<>("index02"));
        orderIndex03.setCellValueFactory(new PropertyValueFactory<>("index03"));
        orderIndex04.setCellValueFactory(new PropertyValueFactory<>("index04"));
        
       filteredData = new FilteredList<>(data, b -> true);
        autoSearch(txtSeeks98);
        autoSearch(txtSeeks99);
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<PickupModel> sortedData = new SortedList<>(filteredData);

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
    private void initGrid1() {
        billIndex01.setStyle("-fx-alignment: CENTER;");
        billIndex02.setStyle("-fx-alignment: CENTER-LEFT;");
        billIndex03.setStyle("-fx-alignment: CENTER-LEFT;");
        billIndex04.setStyle("-fx-alignment: CENTER;");
        billIndex05.setStyle("-fx-alignment: CENTER;");
        billIndex06.setStyle("-fx-alignment: CENTER;");
        billIndex07.setStyle("-fx-alignment: CENTER;");
        billIndex08.setStyle("-fx-alignment: CENTER;");
        billIndex01.setCellValueFactory(new PropertyValueFactory<>("index01"));
        billIndex02.setCellValueFactory(new PropertyValueFactory<>("index02"));
        billIndex03.setCellValueFactory(new PropertyValueFactory<>("index03"));
        billIndex04.setCellValueFactory(new PropertyValueFactory<>("index04"));
        billIndex05.setCellValueFactory(new PropertyValueFactory<>("index05"));
        billIndex06.setCellValueFactory(new PropertyValueFactory<>("index06"));
        billIndex07.setCellValueFactory(new PropertyValueFactory<>("index07"));
        billIndex08.setCellValueFactory(new PropertyValueFactory<>("index09"));
        
       filteredWaybillData = new FilteredList<>(waybill_data, b -> true);
       
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<WaybillModel> sortedData = new SortedList<>(filteredWaybillData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tblOrders.comparatorProperty());
      
        
        // 5. Add sorted (and filtered) data to the table.
       tblWaybill.setItems(sortedData);
       tblWaybill.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblWaybill.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
          
       
        
    }    
    
    @FXML
    private void tblOrders_Clicked(MouseEvent event) {
        pnRow = tblOrders.getSelectionModel().getSelectedIndex();
        pagecounter = pnRow + pagination.getCurrentPageIndex() * ROWS_PER_PAGE; 
        if(pagecounter>=0){
            txtField01.setText(filteredData.get(pagecounter).getIndex02());
            txtField02.setText(filteredData.get(pagecounter).getIndex03());
            txtField03.setText(filteredData.get(pagecounter).getIndex04());
            txtField04.setText(filteredData.get(pagecounter).getIndex06());
            txtField05.setText(filteredData.get(pagecounter).getIndex05());
            try {
                
                waybill_data.clear();
                if(oTrans.OpenTransaction(filteredData.get(pagecounter).getIndex02())){
                    for(int lnCtr = 1; lnCtr <= oTrans.getItemCountWaybill(); lnCtr++){
                        String isPick = "NO";
                        if(!oTrans.getWaybill(lnCtr, "sBatchNox").toString().isEmpty()){
                            isPick = "YES";
                        }
                        waybill_data.add(new WaybillModel(
                                String.valueOf(lnCtr), 
                                (String)oTrans.getWaybill(lnCtr, "sTransNox"),
                                (String)oTrans.getWaybill(lnCtr, "sCompnyNm"), 
                                (String)oTrans.getWaybill(lnCtr, "sOrderNox"), 
                                (String)oTrans.getWaybill(lnCtr, "sTrackrNo"), 
                                (String)oTrans.getWaybill(lnCtr, "sPackngDs"), 
                                dateToWord(oTrans.getWaybill(lnCtr, "dTransact").toString()),
                                (String)oTrans.getWaybill(lnCtr, "sBatchNox"),
                                isPick));
                    }
                    initGrid1();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PickupController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

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
    
    
}
