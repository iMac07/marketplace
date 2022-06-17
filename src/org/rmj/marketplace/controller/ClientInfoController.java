    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.controller;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.io.IOException;
import org.rmj.marketplace.model.ScreenInterface;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static junit.framework.Assert.fail;
import org.rmj.marketplace.model.OrderModel;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agent.MsgBox;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.marketplace.base.Clients;
import org.rmj.marketplace.base.LTransaction;
import org.rmj.marketplace.model.ClientInfoModel;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ClientInfoController implements Initializable, ScreenInterface {
    
    private GRider oApp;
    private int orderRow = 0;
    private int clienrRow = 0;
    private int dataSize;
    private final String pxeModuleName = "Client Information";
    private double xOffset = 0;
    private double yOffset = 0;
    
    private LTransaction  oListener;
    private Clients oTrans;
    private int pagecounter;
    
    private boolean pbLoaded = false;
    private int pnRow = -1;
    private int pnRow1 = -1;
    private String category;
    
    @FXML
    private Pagination tblPagination;
    @FXML
    private AnchorPane AnchorClient;
    @FXML
     private TableView tblClients;
    @FXML
    private TableColumn<?, ?> clientIndex01;
    @FXML
    private TableColumn<?, ?> clientIndex03;
    @FXML
    private TableColumn<?, ?> clientIndex02;
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
    private TextField txtField06;
    @FXML
    private TextField txtField07;
    @FXML
    private TextField txtField08;
    @FXML
    private TextField txtField09;
    @FXML
    private TableView<OrderModel> tblOrders;
    @FXML
    private TableColumn<?, ?> orderIndex01;
    @FXML
    private TableColumn<?, ?> orderIndex02;
    @FXML
    private TableColumn<?, ?> orderIndex03;
    @FXML
    private TableColumn<?, ?> orderIndex04;
    @FXML
    private TableColumn<?, ?> orderIndex05;
    @FXML
    private AnchorPane searchBar;
    @FXML
    private TextField txtSeeks10;
    @FXML
    private TextField txtSeeks11;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnReset;
    @FXML
    private Pagination pagination;
    
    private static final int ROWS_PER_PAGE = 10;
    
//    private final TableView<ClientInfoModel> table = createTable();
//    private final List<ClientInfoModel> data = createData();     
    
    private final ObservableList<OrderModel> order_data = FXCollections.observableArrayList();
    
    private final ObservableList<ClientInfoModel> client_data = FXCollections.observableArrayList();
    private FilteredList<ClientInfoModel> filteredData;
    private void setCategory(String val){
        this.category = val;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        oListener = (int fnIndex, Object foValue) -> {
            System.out.println(fnIndex + "-->" + foValue);
        };
        
        oTrans = new Clients(oApp, oApp.getBranchCode(), false);
        oTrans.setListener(oListener);
        oTrans.setWithUI(true);
//        txtSeeks10.setOnKeyPressed(this::txtField_KeyPressed);
//        txtSeeks11.setOnKeyPressed(this::txtField_KeyPressed);
        
        loadClient();
        pagination.setPageFactory(this::createPage); 
        btnRefresh.setOnAction(this::cmdButton_Click);    
            
    } 
    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, client_data.size());
        
        tblClients.setItems(FXCollections.observableArrayList(client_data.subList(fromIndex, toIndex)));
        return tblClients;
    }
    private void txtField_KeyPressed(KeyEvent event){

            TextField txtField = (TextField)event.getSource();
            int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
            switch (event.getCode()){
                case F3:
                    switch (lnIndex){
                    }
                    break;
                case ENTER:
                        switch (lnIndex){
                        case 10: /*Search*/
                            
                            break;
                        case 11: /*Search*/
                            
                            break;
                    }
                    break;
                case DOWN:
                    CommonUtils.SetNextFocus(txtField);break;
                case UP:
                    CommonUtils.SetPreviousFocus(txtField);break;
            }

    }
    private void loadClient(){
        try {
            client_data.clear();
            if (oTrans.LoadList("")){      
                oTrans.displayMasFields();
                System.out.println("List count -->" + oTrans.getItemCount());
                for (int lnCtr = 1; lnCtr <= oTrans.getItemCount(); lnCtr++){
                    client_data.add(new ClientInfoModel((String) oTrans.getDetail(lnCtr, 1),
                            (String) oTrans.getDetail(lnCtr, 19),
                            (String) oTrans.getDetail(lnCtr, 16),
                            (String) oTrans.getDetail(lnCtr, 17),
                            (String) oTrans.getDetail(lnCtr, "sHouseNox") +  ", " + oTrans.getDetail(lnCtr, "sAddressx") +  ", " + oTrans.getDetail(lnCtr, "xTownName"),
                            (String) oTrans.getDetail(lnCtr, "cGenderCd"),
                            (String) oTrans.getDetail(lnCtr, "xNational"),
                            (String) oTrans.getDetail(lnCtr, 2),
                            (CommonUtils.xsDateLong((Date) oTrans.getDetail(lnCtr, "dBirthDte"))),
                            (String) oTrans.getDetail(lnCtr, "xBirthPlc"),
                            String.valueOf(lnCtr)));
                    
                }
                tblClients.getSelectionModel().select(clienrRow - 1);
                initClientsGrid();
            } else
                MsgBox.showOk(oTrans.getMessage());
        } catch (SQLException e) {
            fail(e.getMessage());
        }   
    }
    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue; //To change body of generated methods, choose Tools | Templates.
    }
    
    private void loadOrders(String transNox){
        int lnCtr;
        try {
            order_data.clear();
            if (oTrans.LoadOrder(transNox)){
                //true if by barcode; false if by description
                oTrans.displayMasFields();
                for (lnCtr = 1; lnCtr <= oTrans.getOrderItemCount(); lnCtr++){
                    order_data.add(new OrderModel(String.valueOf(lnCtr),
                            (String) oTrans.getOrder(lnCtr, "sTransNox"),
                            (CommonUtils.xsDateLong((Date) oTrans.getOrder(lnCtr, "dTransact"))),
                            (String) oTrans.getOrder(lnCtr, "sTermCode"),
                            oTrans.getOrder(lnCtr, "nTranTotl").toString(),
                            oTrans.getOrder(lnCtr, "nVATRatex").toString(),
                            oTrans.getOrder(lnCtr, "nDiscount").toString(),
                            oTrans.getOrder(lnCtr, "nAddDiscx").toString(),
                            oTrans.getOrder(lnCtr, "nFreightx").toString(),
                            oTrans.getOrder(lnCtr, "nAmtPaidx").toString(),
                            oTrans.getOrder(lnCtr, "cTranStat").toString(),
                            (String) oTrans.getOrder(lnCtr, "sRemarksx"),
                            (String) oTrans.getOrder(lnCtr, "sCompnyNm"),
                            (String) oTrans.getOrder(lnCtr, "sAddressx"),
                            (String) oTrans.getOrder(lnCtr, "sTownName")));
                            System.out.println(oTrans.getOrder(lnCtr, "sTransNox"));
                            System.out.println(oTrans.getOrder(lnCtr, "nTranTotl"));
                }   
                initOrderGrid();
            } else {//true if by barcode; false if by description
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
    
    public void initClientsGrid() { 
        clientIndex01.setStyle("-fx-alignment: CENTER-LEFT; -fx-padding: 0 0 0 5;");
        clientIndex02.setStyle("-fx-alignment: CENTER-LEFT; -fx-padding: 0 0 0 5;");
        clientIndex03.setStyle("-fx-alignment: CENTER-LEFT; -fx-padding: 0 0 0 5;");
        
        clientIndex01.setCellValueFactory(new PropertyValueFactory<>("clientIndex11"));
        clientIndex02.setCellValueFactory(new PropertyValueFactory<>("clientIndex02"));
        clientIndex03.setCellValueFactory(new PropertyValueFactory<>("clientIndex03"));
        tblClients.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblClients.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
        filteredData = new FilteredList<>(client_data, b -> true);
        autoSearch(txtSeeks10);
        autoSearch(txtSeeks11);
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ClientInfoModel> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tblClients.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tblClients.setItems(sortedData);
        
    }
     public void initOrderGrid() { 
        orderIndex01.setStyle("-fx-alignment: CENTER-LEFT; -fx-padding: 0 0 0 5;");
        orderIndex02.setStyle("-fx-alignment: CENTER-LEFT; -fx-padding: 0 0 0 5;");
        orderIndex03.setStyle("-fx-alignment: CENTER-LEFT; -fx-padding: 0 0 0 5;");
        orderIndex04.setStyle("-fx-alignment: CENTER-LEFT; -fx-padding: 0 0 0 5;");
        orderIndex05.setStyle("-fx-alignment: CENTER-LEFT; -fx-padding: 0 0 0 5;");
        
        orderIndex01.setCellValueFactory(new PropertyValueFactory<>("orderIndex01"));
        orderIndex02.setCellValueFactory(new PropertyValueFactory<>("orderIndex02"));
        orderIndex03.setCellValueFactory(new PropertyValueFactory<>("orderIndex05"));
        orderIndex04.setCellValueFactory(new PropertyValueFactory<>("orderIndex03"));
        orderIndex05.setCellValueFactory(new PropertyValueFactory<>("orderIndex04"));
        tblOrders.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblOrders.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
        tblOrders.setItems(order_data);
//        filteredData = new FilteredList<>(order_data, b -> true);
//        autoSearch(txtSeeks10);
//        autoSearch(txtSeeks11);
//        // 3. Wrap the FilteredList in a SortedList. 
//        SortedList<ClientInfoModel> sortedData = new SortedList<>(filteredData);
//
//        // 4. Bind the SortedList comparator to the TableView comparator.
//        // 	  Otherwise, sorting the TableView would have no effect.
//        sortedData.comparatorProperty().bind(tblClients.comparatorProperty());
//
//        // 5. Add sorted (and filtered) data to the table.
//        tblClients.setItems(sortedData);
        
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
                if(lnIndex == 10){
                    return (clients.getClientIndex01().toLowerCase().contains(lowerCaseFilter)); // Does not match.   
                }else {
                    return (clients.getClientIndex02().toLowerCase().contains(lowerCaseFilter)); // Does not match.
                }
            });
            changeTableView(0, ROWS_PER_PAGE);
        });
        if(lnIndex == 98){  
        }
        if(lnIndex == 99){  
        }
        int totalPage = (int) (Math.ceil(client_data.size() * 1.0 / ROWS_PER_PAGE));
        pagination.setPageCount(totalPage);
        pagination.setCurrentPageIndex(0);
        changeTableView(0, ROWS_PER_PAGE);
        pagination.currentPageIndexProperty().addListener(
                (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));
    } 
    
    private void changeTableView(int index, int limit) {
        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, client_data.size());

        int minIndex = Math.min(toIndex, filteredData.size());
        SortedList<ClientInfoModel> sortedData = new SortedList<>(
                FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
        sortedData.comparatorProperty().bind(tblClients.comparatorProperty());
        tblClients.setItems(sortedData);
    }
    
    @FXML
    private void tblClients_Clicked() {
        pnRow = tblClients.getSelectionModel().getSelectedIndex(); 
        pagecounter = pnRow + pagination.getCurrentPageIndex() * ROWS_PER_PAGE;
        getSelectedItem();
        loadOrders(filteredData.get(pagecounter).getClientIndex08());
        
      
        
        tblClients.setOnKeyReleased((KeyEvent t)-> {
                KeyCode key = t.getCode();
                switch (key){
                    case DOWN:
                        pnRow = tblClients.getSelectionModel().getSelectedIndex();
                        pagecounter = pnRow + pagination.getCurrentPageIndex() * ROWS_PER_PAGE;
                        loadOrders(filteredData.get(pagecounter).getClientIndex07());
                        if (pagecounter == tblClients.getItems().size()) {
                            pagecounter = tblClients.getItems().size();
                            getSelectedItem();
                        }else {
//                            int y = 1;
//                            pnRow = pnRow + y;
                            getSelectedItem();
                        }
                        break;
                    case UP:
                        int pnRows = 0;
                        
                        pnRows = tblClients.getSelectionModel().getSelectedIndex();
                        pagecounter = pnRow + pagination.getCurrentPageIndex() * ROWS_PER_PAGE;
                        loadOrders(filteredData.get(pagecounter).getClientIndex07());
                            pnRow = pnRows; 
                            getSelectedItem();
                        break;
                    default:
                        return; 
                }
            });
    }
    
    private void getSelectedItem(){
        int genval = Integer.parseInt((filteredData.get(pagecounter).getClientIndex06()));
        txtField01.setText(filteredData.get(pagecounter).getClientIndex08());
        txtField02.setText(filteredData.get(pagecounter).getClientIndex02());
        txtField03.setText(filteredData.get(pagecounter).getClientIndex05());
        txtField04.setText(filteredData.get(pagecounter).getClientIndex04()); 
        txtField05.setText(filteredData.get(pagecounter).getClientIndex03()); 
        switch (genval) {
            case 1:
                txtField06.setText("Female");
                break;
            case 0:
                txtField06.setText("Male");
                break;
            default:
                txtField06.setText("LGBTQ");
                break;
        }
        txtField07.setText(filteredData.get(pagecounter).getClientIndex07()); 
        txtField08.setText(filteredData.get(pagecounter).getClientIndex09()); 
        txtField09.setText(filteredData.get(pagecounter).getClientIndex10());      
    } 
    
    private void cmdButton_Click(ActionEvent event) {
     
            String lsButton = ((Button)event.getSource()).getId();
             switch (lsButton){
               case "btnRefresh":
                    {
                        client_data.clear();
                        loadClient();
                    }
                    break;
                }
    } 
    @FXML
    private void tblOrderDetail_Clicked() {
        try { 
            pnRow1 = tblOrders.getSelectionModel().getSelectedIndex();
            System.out.print(pnRow1);
            loadOrderDetail(order_data.get(pnRow1).getOrderIndex02(), pnRow1 + 1); 
        } catch (SQLException ex) {
            Logger.getLogger(ClientInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    private void loadOrderDetail(String fsCode, int fnRow) throws SQLException{
        try {
            Stage stage = new Stage();
            
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/org/rmj/marketplace/view/OrderDetail.fxml"));

            OrderDetailController loControl = new OrderDetailController();
            loControl.setGRider(oApp);
            loControl.setOrderDetailObject(oTrans);
            loControl.setOrderDetailCode(fsCode);
            loControl.setTableRow(fnRow);
            loControl.setData(order_data.get(pnRow1));
            loControl.setCltData(filteredData.get(pnRow));
            
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
            
            loadOrders(filteredData.get(pnRow).getClientIndex07());
        } catch (IOException e) {
            e.printStackTrace();
            MsgBox.showOk(e.getMessage());
            System.exit(1);
        }
    }
}
