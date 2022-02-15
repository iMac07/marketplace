/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.controller;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import org.rmj.marketplace.model.ScreenInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.rmj.marketplace.model.OrderModel;
import org.rmj.appdriver.GRider;
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
    @FXML
    private TextField txtSeeks10;
    @FXML
    private TextField txtSeeks11;
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
    private TableView<OrderModel> tblOrders;
    @FXML
    private TableColumn<OrderModel, String> orderIndex01;
    @FXML
    private TableColumn<OrderModel, String> orderIndex02;
    @FXML
    private TableColumn<OrderModel, String> orderIndex03;
    @FXML
    private TableColumn<OrderModel, String> orderIndex04;
    @FXML
    private TableColumn<OrderModel, String> orderIndex05;
    
    @FXML
    private TableView tblClients;
    @FXML
    private TableColumn<OrderModel, Image> clientsIndex01;
    @FXML
    private TableColumn clientsIndex02;
    @FXML
    private TableColumn clientsIndex03;
    @FXML
    private TableColumn clientsIndex04;
    @FXML
    private AnchorPane MainOrderProcessing;
    
    
    private final ObservableList<OrderModel> order_data = FXCollections.observableArrayList();
    
    private final ObservableList<ClientInfoModel> client_data = FXCollections.observableArrayList();
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
      
        initClientGrid();
        // TODO
    }    

    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue; //To change body of generated methods, choose Tools | Templates.
    }
    
    @FXML
    private void tblorders_Click(MouseEvent event) {
        orderRow = tblOrders.getSelectionModel().getSelectedIndex();
    }
    @FXML
    private void tblClients_Click(MouseEvent event) {
        clienrRow = tblClients.getSelectionModel().getSelectedIndex();
    }
    public void initClientGrid() { 
        orderIndex01.setStyle("-fx-alignment: CENTER-LEFT;");
        orderIndex02.setStyle("-fx-alignment: CENTER-LEFT;");
        orderIndex03.setStyle("-fx-alignment: CENTER-LEFT;");
        orderIndex04.setStyle("-fx-alignment: CENTER-LEFT;");
        orderIndex05.setStyle("-fx-alignment: CENTER-LEFT;");
        
        orderIndex01.setCellValueFactory(new PropertyValueFactory<>("cleintIndex01"));
        orderIndex02.setCellValueFactory(new PropertyValueFactory<>("cleintIndex02"));
        orderIndex03.setCellValueFactory(new PropertyValueFactory<>("cleintIndex03"));
        orderIndex04.setCellValueFactory(new PropertyValueFactory<>("cleintIndex04"));
        orderIndex05.setCellValueFactory(new PropertyValueFactory<>("cleintIndex05"));
        tblOrders.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblOrders.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
        tblOrders.setItems(order_data);
        tblOrders.autosize(); 
    }
    
    public void initOrderGrid() { 
        clientsIndex01.setStyle("-fx-alignment: CENTER-LEFT;");
        clientsIndex02.setStyle("-fx-alignment: CENTER-LEFT;");
        clientsIndex03.setStyle("-fx-alignment: CENTER-LEFT;");
        clientsIndex04.setStyle("-fx-alignment: CENTER-LEFT;");
        
        clientsIndex01.setCellValueFactory(new PropertyValueFactory<>("cleintIndex01"));
        clientsIndex02.setCellValueFactory(new PropertyValueFactory<>("cleintIndex02"));
        clientsIndex03.setCellValueFactory(new PropertyValueFactory<>("cleintIndex03"));
        clientsIndex04.setCellValueFactory(new PropertyValueFactory<>("cleintIndex04"));
        tblClients.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblClients.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
        tblClients.setItems(client_data);
        tblClients.autosize(); 
    }
}
