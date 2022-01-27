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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.rmj.appdriver.GRider;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ItemManagementController implements Initializable, ScreenInterface {

    /**
     * Initializes the controller class.
     */
    private GRider oApp;
    @FXML
    private AnchorPane searchBar;
    @FXML
    private TextField txtSeeks05;
    @FXML
    private TextField txtSeeks06;
    @FXML
    private TableView<?> tblDetail;
    @FXML
    private TableColumn<?, ?> index01;
    @FXML
    private TableColumn<?, ?> index02;
    @FXML
    private TableColumn<?, ?> index03;
    @FXML
    private TableColumn<?, ?> index04;
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
    private TextField txtField01;
    @FXML
    private TextField txtField02;
    @FXML
    private TextField txtField03;
    @FXML
    private TextField txtField05;
    @FXML
    private TextField txtField04;
    @FXML
    private TextField txtField06;
    @FXML
    private TextField txtField08;
    @FXML
    private TextField txtField07;
    @FXML
    private TextField txtField09;
    @FXML
    private TextField txtField10;
    @FXML
    private TextField txtField12;
    @FXML
    private TextField txtField11;
    @FXML
    private TextField txtField13;
    @FXML
    private TextField txtField14;
    @FXML
    private TextField txtField17;
    @FXML
    private TextField txtField15;
    @FXML
    private TextField txtField18;
    @FXML
    private TextField txtField16;
    @FXML
    private TextField txtField19;
    @FXML
    private ComboBox<?> Combo23;
    @FXML
    private ComboBox<?> Combo24;
    @FXML
    private TextField txtField29;
    @FXML
    private TextField txtField25;
    @FXML
    private CheckBox Check20;
    @FXML
    private CheckBox Check21;
    @FXML
    private CheckBox Check22;
    @FXML
    private CheckBox Check26;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue;//To change body of generated methods, choose Tools | Templates.
    }
    
}
