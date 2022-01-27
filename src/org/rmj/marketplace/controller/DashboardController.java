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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import org.rmj.appdriver.GRider;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DashboardController implements Initializable, ScreenInterface {

    /**
     * Initializes the controller class.
     */
    private GRider oApp;
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
    private PieChart pie01;
    @FXML
    private Tab tabPO;
    @FXML
    private TableView<?> tblPO;
    @FXML
    private TableColumn<?, ?> PO01;
    @FXML
    private TableColumn<?, ?> PO02;
    @FXML
    private TableColumn<?, ?> PO03;
    @FXML
    private TableColumn<?, ?> PO04;
    @FXML
    private TableColumn<?, ?> PO05;
    @FXML
    private Tab tabReceiving;
    @FXML
    private Tab tabReturns;
    @FXML
    private Tab tabOpenTransfer;
    @FXML
    private Tab tabUnpostedTransfer;
    @FXML
    private TableView<?> tblPO1;
    @FXML
    private TableColumn<?, ?> PO011;
    @FXML
    private TableColumn<?, ?> PO021;
    @FXML
    private TableColumn<?, ?> PO031;
    @FXML
    private TableColumn<?, ?> PO041;
    @FXML
    private TableColumn<?, ?> PO051;
    @FXML
    private TableView<?> tblPO2;
    @FXML
    private TableColumn<?, ?> PO012;
    @FXML
    private TableColumn<?, ?> PO022;
    @FXML
    private TableColumn<?, ?> PO032;
    @FXML
    private TableColumn<?, ?> PO042;
    @FXML
    private TableColumn<?, ?> PO052;
    @FXML
    private TableView<?> tblPO3;
    @FXML
    private TableColumn<?, ?> PO013;
    @FXML
    private TableColumn<?, ?> PO023;
    @FXML
    private TableColumn<?, ?> PO033;
    @FXML
    private TableColumn<?, ?> PO043;
    @FXML
    private TableColumn<?, ?> PO053;
    @FXML
    private TableView<?> tblPO4;
    @FXML
    private TableColumn<?, ?> PO014;
    @FXML
    private TableColumn<?, ?> PO024;
    @FXML
    private TableColumn<?, ?> PO034;
    @FXML
    private TableColumn<?, ?> PO044;
    @FXML
    private TableColumn<?, ?> PO054;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        btnDashboard.fire();
    }    
    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue;//To change body of generated methods, choose Tools | Templates.
    }
    
}
