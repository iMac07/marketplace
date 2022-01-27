/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.controller;

import org.rmj.marketplace.model.ScreenInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.rmj.appdriver.GRider;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FAQController implements Initializable, ScreenInterface {
    
    private GRider oApp;
    @FXML
    private Label lblCustomerName;
    @FXML
    private TextField txtField01;
    @FXML
    private Button btnSend;
    
    private TableView tblbReplied;
    @FXML
    private TableColumn repliedIndex01;
    @FXML
    private TableColumn repliedIndex02;
    @FXML
    private TableColumn repliedIndex03;
    @FXML
    private TableColumn repliedIndex04;
    
    @FXML
    private TableView tblbUnreplied;
    @FXML
    private TableColumn unrepliedIndex01;
    @FXML
    private TableColumn unrepliedIndex02;
    @FXML
    private TableColumn unrepliedIndex03;
    @FXML
    private TableColumn unrepliedIndex04;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue; //To change body of generated methods, choose Tools | Templates.
    }
    
}
