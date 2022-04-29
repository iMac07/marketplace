/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.controller;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import org.rmj.marketplace.model.ScreenInterface;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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
    
    private LTransaction  oListener;
    private Clients oTrans;
    
    private boolean pbLoaded = false;
    private int pnRow = -1;
    private String category;
       
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
        btnRefresh.setOnAction(this::cmdButton_Click);    
            
    }    
    private void txtField_KeyPressed(KeyEvent event){
//        try {
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
//        } catch (SQLException ex) {
//            Logger.getLogger(ClientInfoController.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
                            (String) oTrans.getDetail(lnCtr, 13),
                            (String) oTrans.getDetail(lnCtr, "cGenderCd"),
                            (String) oTrans.getDetail(lnCtr, 2),
                            (CommonUtils.xsDateLong((Date) oTrans.getDetail(lnCtr, "dBirthDte"))),
                            (String) oTrans.getDetail(lnCtr, "xBirthPlc"),
                            String.valueOf(lnCtr)));
                    
                    System.out.println("No. --> " + lnCtr);
                    System.out.println("Name --> " + (String) oTrans.getDetail(lnCtr, 19));
                    System.out.println("Email --> " + (String) oTrans.getDetail(lnCtr, 16));
                }
//                tblClients.getSelectionModel().select(clienrRow - 1);
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

    public void initClientsGrid() { 
        clientIndex01.setStyle("-fx-alignment: CENTER; -fx-padding: 0 0 0 5;");
        clientIndex02.setStyle("-fx-alignment: CENTER-LEFT; -fx-padding: 0 0 0 5;");
        clientIndex03.setStyle("-fx-alignment: CENTER-LEFT; -fx-padding: 0 0 0 5;");
        
        clientIndex01.setCellValueFactory(new PropertyValueFactory<>("clientIndex10"));
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
        });
        if(lnIndex == 98){

        }if(lnIndex == 99){

        }
    } 
    
     @FXML
    private void tblClients_Clicked() {
        pnRow = tblClients.getSelectionModel().getSelectedIndex();
        getSelectedItem();
       
        tblClients.setOnKeyReleased((KeyEvent t)-> {
                KeyCode key = t.getCode();
                switch (key){
                    case DOWN:
                        pnRow = tblClients.getSelectionModel().getSelectedIndex();
                        if (pnRow == tblClients.getItems().size()) {
                            pnRow = tblClients.getItems().size();
                            getSelectedItem();
                        }else {
//                            int y = 1;
//                            pnRow = pnRow + y;
                            getSelectedItem();
                        }
                        break;
                    case UP:
                        int pnRows = 0;
                        int x = 1;
                        pnRows = tblClients.getSelectionModel().getSelectedIndex();
                            pnRow = pnRows; 
                            getSelectedItem();
                        break;
                    default:
                        return; 
                }
            });
    }
    
    private void getSelectedItem(){
        int genval = Integer.parseInt((filteredData.get(pnRow).getClientIndex06()));
        txtField01.setText(filteredData.get(pnRow).getClientIndex07());
        txtField02.setText(filteredData.get(pnRow).getClientIndex02());
        txtField03.setText(filteredData.get(pnRow).getClientIndex05());
        txtField04.setText(filteredData.get(pnRow).getClientIndex04()); 
        txtField05.setText(filteredData.get(pnRow).getClientIndex03()); 
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
//        txtField06.setText(filteredData.get(pnRow).getClientIndex06());
//        txtField07.setText(filteredData.get(pnRow).getClientIndex07());
        txtField08.setText(filteredData.get(pnRow).getClientIndex08()); 
        txtField09.setText(filteredData.get(pnRow).getClientIndex09());      
    } 
    
    private void cmdButton_Click(ActionEvent event) {
     
            String lsButton = ((Button)event.getSource()).getId();
             switch (lsButton){
               case "btnRefresh":
                    {
                        MsgBox.showOk("hello");
                           loadClient();
                    }
                    break;
                }
    } 
}
