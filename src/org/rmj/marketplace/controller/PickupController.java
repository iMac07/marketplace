/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.rmj.marketplace.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.rmj.marketplace.model.ScreenInterface;
import org.rmj.appdriver.GRider;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PickupController implements Initializable, ScreenInterface {

    @FXML
    private AnchorPane PickUpMain;
    @FXML
    private AnchorPane searchBar;
    @FXML
    private TextField txtField50;
    @FXML
    private TextField txtField51;
    @FXML
    private TableView<?> tblClients;
    @FXML
    private TableColumn<?, ?> clientsIndex01;
    @FXML
    private TableColumn<?, ?> clientsIndex02;
    @FXML
    private TableColumn<?, ?> clientsIndex03;
    @FXML
    private TableColumn<?, ?> clientsIndex04;
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
    private TableView<?> tblorders1;
    @FXML
    private TableColumn<?, ?> orderIndex011;
    @FXML
    private TableColumn<?, ?> orderIndex021;
    @FXML
    private TableColumn<?, ?> orderIndex041;
    @FXML
    private TableColumn<?, ?> orderIndex031;
    @FXML
    private TableColumn<?, ?> orderIndex051;
    @FXML
    private TableColumn<?, ?> orderIndex061;
    @FXML
    private TableColumn<?, ?> orderIndex0611;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void setGRider(GRider foValue) {
    }
    
}
