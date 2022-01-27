/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.controller;

import org.rmj.marketplace.model.ScreenInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.rmj.appdriver.GRider;

/**
 * FXML Controller class
 *
 * @author user
 */
public class OrderDetailController implements Initializable, ScreenInterface {
    
    private GRider oApp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue; //To change body of generated methods, choose Tools | Torderlates.
    }
}
