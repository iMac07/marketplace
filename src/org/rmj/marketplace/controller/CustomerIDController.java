/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.rmj.marketplace.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.marketplace.base.ClientProfiling;
import org.rmj.marketplace.model.ClientInfoModel;
import org.rmj.marketplace.model.ValidIDModel;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class CustomerIDController implements Initializable {
    private GRider oApp;
    private ClientProfiling oTrans;
    
    public int tbl_row = 0;
    private String psCode;
    private String btncode;
    private String clientID;
    private boolean state = false;
    public static ValidIDModel validIDModel;
    private ObservableList<ValidIDModel> clientValidID_data = FXCollections.observableArrayList();
    public static ClientInfoModel  cltModel;
    private final String pxeModuleName = "Order Detail";
    double total = 0;
    private int imgRow = -1;
    
    @FXML
    private Button btnExit;
    @FXML
    private FontAwesomeIconView glyphExit;
    @FXML
    private AnchorPane searchBar;
    @FXML
    private Label lblIDNo;
    @FXML
    private Label lblIDName;
    @FXML
    private Label lblExpr;
    @FXML
    private ImageView imgFront;
    @FXML
    private ImageView imgBack;
     @FXML
    private Button btnMinus1;
    @FXML
    private Button btnPlus1;
    @FXML
    private Button btnPhoto;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            // TODO
//            loadID(psCode);
//            MsgBox.showOk(btncode);
        if (validIDModel.getValidIndex02().isEmpty()){
            imgFront.setImage(new Image("/org/rmj/marketplace/images/id_default.png"));
            imgBack.setImage(new Image("/org/rmj/marketplace/images/id_default.png"));
        } else {
            loadIDDetails();
        }
//            loadImage(imgRow);
//            MsgBox.showOk(btncode);
            btnExit.setOnAction(this::cmdButton_Click);
            btnPlus1.setOnAction(this::cmdButton_Click);
            btnMinus1.setOnAction(this::cmdButton_Click);
        
    }   
    
    public void setGRider(GRider foValue) {
        oApp = foValue; //To change bod y of generated methods, choose Tools | Torderlates.
    }
     public void setIDDetailObject(ClientProfiling foValue){
        oTrans = foValue;
    }
    public void setValidID(String fsValue){
        psCode = fsValue;
    }
    public void setTableRow(int row){
        tbl_row = row;
    }
    public void setbtnValue(String idval){
        btncode = idval;
    }
    
    public void setState(boolean fsValue){
        state = fsValue;
    }
    public void setData(ValidIDModel validdata){
        validIDModel = validdata;    
    }
   
    private void loadIDDetails(){
        System.out.println(validIDModel.getValidIndex01());
        System.out.println(validIDModel.getValidIndex02());
        System.out.println(validIDModel.getValidIndex03());
        System.out.println(validIDModel.getValidIndex04());
        System.out.println(validIDModel.getValidIndex05());
        System.out.println(validIDModel.getValidIndex06());
        System.out.println(validIDModel.getValidIndex07());
        System.out.println(validIDModel.getValidIndex08());
        System.out.println(validIDModel.getValidIndex09());
        System.out.println(validIDModel.getValidIndex10());
        System.out.println(validIDModel.getValidIndex11());
        System.out.println(validIDModel.getValidIndex12());
        System.out.println(validIDModel.getValidIndex13());
        System.out.println(validIDModel.getValidIndex14());
        System.out.println(validIDModel.getValidIndex15());
        System.out.println(validIDModel.getValidIndex16());
        

        
        switch (btncode){
            case "0":
                lblIDName.setText(validIDModel.getValidIndex15());
                lblIDNo.setText(validIDModel.getValidIndex05());
                lblExpr.setText(validIDModel.getValidIndex08());
                imgFront.setImage(new Image(validIDModel.getValidIndex06()));
                imgBack.setImage(new Image(validIDModel.getValidIndex07()));
               
                break;
            case "1":
                lblIDName.setText(validIDModel.getValidIndex16());
                lblIDNo.setText(validIDModel.getValidIndex10());
                lblExpr.setText(validIDModel.getValidIndex13());
                imgFront.setImage(new Image(validIDModel.getValidIndex11()));
                imgBack.setImage(new Image(validIDModel.getValidIndex12()));
                
                break;
            
        }
    }
    
    private void cmdButton_Click(ActionEvent event) {
        String lsButton = ((Button)event.getSource()).getId();
        switch (lsButton){
            case "btnExit":
                 CommonUtils.closeStage(btnExit);
                break;
            case "btnPlus1":
                    btncode = "1";
                   System.out.println("plus 1");
                  loadIDDetails();
                  
                break;
            case "btnMinus1":
                    btncode = "0";
                    System.out.println("minus 1");
                  loadIDDetails();
                  
                break;
            default:
                
        }
    }
    
    
}
