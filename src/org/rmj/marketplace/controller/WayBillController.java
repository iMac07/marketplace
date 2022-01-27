/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import org.rmj.marketplace.model.ScreenInterface;
import org.rmj.appdriver.GRider;

/**
 * FXML Controller class
 *
 * @author user
 */
public class WayBillController implements Initializable, ScreenInterface {

    /**
     * Initializes the controller class.
     */
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setGRider(GRider foValue) {
    }
    
}
