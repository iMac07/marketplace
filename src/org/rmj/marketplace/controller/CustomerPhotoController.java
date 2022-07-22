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
import static org.rmj.marketplace.controller.CustomerIDController.validIDModel;
import org.rmj.marketplace.model.ClientInfoModel;
import org.rmj.marketplace.model.ClientPhotoModel;
import org.rmj.marketplace.model.ValidIDModel;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class CustomerPhotoController implements Initializable {
     private GRider oApp;
    private ClientProfiling oTrans;
    
    public int tbl_row = 0;
    private String psCode;
    private String btncode;
    private String clientID;
    private boolean state = false;
    public static ClientPhotoModel validPhoto;
    private ObservableList<ClientPhotoModel> clientPhoto_data = FXCollections.observableArrayList();
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
    private Label lblReference;
    @FXML
    private Label lblOrderDate;
    @FXML
    private ImageView imgUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadPhoto();
        btnExit.setOnAction(this::cmdButton_Click);
    }    
     public void setGRider(GRider foValue) {
        oApp = foValue; //To change bod y of generated methods, choose Tools | Torderlates.
    }
     public void setPhotoObject(ClientProfiling foValue){
        oTrans = foValue;
    }
     public void setData(ClientPhotoModel validphoto){
        validPhoto = validphoto;    
    }
   private void loadPhoto(){
//        System.out.println(validPhoto.getCltPhotoIndex01());
//        System.out.println(validPhoto.getCltPhotoIndex02());
        System.out.println(validPhoto.getCltPhotoIndex03());
        System.out.println(validPhoto.getCltPhotoIndex04());
//        System.out.println(validPhoto.getCltPhotoIndex05());
        lblReference.setText(validPhoto.getCltPhotoIndex03());
        imgUser.setImage(new Image(validPhoto.getCltPhotoIndex04()));
        
   }
   
   private void cmdButton_Click(ActionEvent event) {
        String lsButton = ((Button)event.getSource()).getId();
        switch (lsButton){
            case "btnExit":
                 CommonUtils.closeStage(btnExit);
                break;
            
            default:
                
        }
    }
}
