/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
/*
'########################################################################################
'#        ___          ___          ___           ___       ___                         #
'#       /\  \        /\  \        /\  \         /\  \     /\  \         ___            #
'#       \:\  \      /::\  \      /::\  \        \:\  \   /::\  \       /\  \           #
'#        \:\  \    /:/\:\  \    /:/\:\  \   ___ /::\__\ /:/\:\  \      \:\  \          #
'#        /::\  \  /::\~\:\  \  /::\~\:\  \ /\  /:/\/__//::\~\:\  \     /::\__\         #
'#       /:/\:\__\/:/\:\ \:\__\/:/\:\ \:\__\\:\/:/  /  /:/\:\ \:\__\ __/:/\/__/         #
'#      /:/  \/__/\:\~\:\ \/__/\:\~\:\ \/__/ \::/  /   \:\~\:\ \/__//\/:/  /            #
'#     /:/  /      \:\ \:\__\   \:\ \:\__\    \/__/     \:\ \:\__\  \::/__/             #
'#     \/__/        \:\ \/__/    \:\ \/__/               \:\ \/__/   \:\__\             #
'#                   \:\__\       \:\__\                  \:\__\      \/__/             #
'#                    \/__/        \/__/                   \/__/                        #
'#                                                                                      #
'#                                 DATE CREATED 07-09-2022                              #
'#                                 DATE LAST MODIFIED 07-11-2022                        #
'#                                                                                      #
'########################################################################################

*/

package org.rmj.marketplace.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agentfx.ShowMessageFX;
import org.rmj.appdriver.constants.EditMode;
import static org.rmj.marketplace.app.GriderGui.oApp;
import org.rmj.marketplace.base.LTransaction;
import org.rmj.marketplace.base.parameter.Identification;
import org.rmj.marketplace.model.ScreenInterface;
/**
 * FXML Controller class
 *
 * @author admin
 */
public class ParametersController implements Initializable, ScreenInterface {
    
    private LTransaction  oListener;
    private Identification oTrans;
    private String category;
    private int pnEditMode;
    
    @FXML
    private AnchorPane searchBar;
    @FXML
    private TextField txtSeeks1;
    @FXML
    private TextField txtField01;
    @FXML
    private TextField txtField02;
    @FXML
    private TextField txtField03;
    @FXML
    private HBox hbButtons;
    @FXML
    private Button btnBrowse;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnActivate;
    @FXML
    private Button btnDeactivate;
    @FXML
    private Pagination pagination;
    @FXML
    private TableView<?> tblClients1;
    @FXML
    private TableColumn<?, ?> clientIndex011;
    @FXML
    private TableColumn<?, ?> clientIndex021;
    @FXML
    private TableColumn<?, ?> clientIndex032;
    @FXML
    private TableColumn<?, ?> clientIndex0321;
    @FXML
    private CheckBox chkExpiration;
    @FXML
    private CheckBox chkBackView;
    @FXML
    private Label lblStatus;
    
    /**
     * Initializes the controller class.
     */
    private void setCategory(String val){
        this.category = val;
    }
    
    public void setGRider(GRider foValue) {
        oApp = foValue; //To change body of generated methods, choose Tools | Templates.
    }
    private Stage getStage(){
	return (Stage) txtField01.getScene().getWindow();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        oListener = (int fnIndex, Object foValue) -> {
            System.out.println(fnIndex + "-->" + foValue);
            
        };
        
        oTrans = new Identification(oApp, oApp.getBranchCode(), false);
//        oTrans.setListener(oListener);
        oTrans.setWithUI(true);
//        txtSeeks10.setOnKeyPressed(this::txtField_KeyPressed);
//        txtSeeks11.setOnKeyPressed(this::txtField_KeyPressed);
//        a = client_data.get(pnRow).getClientIndex08();
         pnEditMode = EditMode.UNKNOWN;
         initButton(pnEditMode);
         
        btnBrowse.setOnAction(this::cmdButton_Click);
        btnNew.setOnAction(this::cmdButton_Click);
        btnSave.setOnAction(this::cmdButton_Click);
        btnUpdate.setOnAction(this::cmdButton_Click);
        btnCancel.setOnAction(this::cmdButton_Click);
        btnActivate.setOnAction(this::cmdButton_Click);
        btnDeactivate.setOnAction(this::cmdButton_Click);
    }    

    @FXML
    private void tblClients_Clicked(MouseEvent event) {
    }
    



private void initButton(int fnValue){
        boolean lbShow = (fnValue == EditMode.ADDNEW || fnValue == EditMode.UPDATE);
        
        btnCancel.setVisible(lbShow);
        btnSave.setVisible(lbShow);
        btnActivate.setVisible(!lbShow);
        btnDeactivate.setVisible(!lbShow);
        
        
        btnSave.setManaged(lbShow);
        btnCancel.setManaged(lbShow);
        btnUpdate.setVisible(!lbShow);
        btnBrowse.setVisible(!lbShow);
        btnNew.setVisible(!lbShow);
        
//        txtSeeks05.setDisable(!lbShow);
        txtField01.setDisable(true);
        txtField02.setDisable(!lbShow);
        txtField03.setDisable(!lbShow);
        chkBackView.setDisable(!lbShow);
        chkExpiration.setDisable(!lbShow);
        
//        
        if (lbShow){
//            txtSeeks05.setDisable(lbShow);
//            txtSeeks05.clear();
            txtField02.requestFocus();
            btnCancel.setVisible(lbShow);
            btnSave.setVisible(lbShow);
            btnUpdate.setVisible(!lbShow);
            btnBrowse.setVisible(!lbShow);
            btnNew.setVisible(!lbShow);
            btnBrowse.setManaged(false);
            btnNew.setManaged(false);
            btnUpdate.setManaged(false);
            btnActivate.setManaged(false);
            btnDeactivate.setManaged(false);
        }
        else{
//            txtSeeks05.setDisable(lbShow);
//            txtSeeks05.requestFocus();
        }
    }

private void cmdButton_Click(ActionEvent event) {
        String lsButton = ((Button)event.getSource()).getId();
        try {
            switch (lsButton){
                 case "btnBrowse":
                        if (oTrans.SearchRecord(txtSeeks1.getText(), false)){
                            loadValidID();
                            pnEditMode = EditMode.READY;
                        } else 
                            ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                    break;
                case "btnNew": //create new transaction
//                        pbLoaded = true;
                        if (oTrans.NewRecord()){
                            pnEditMode = oTrans.getEditMode();
                            
                        } else 
                            ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                    break;
                 case "btnSave":
                        if(sendIdentification()){
                             if (oTrans.SaveRecord()){
                                clearFields();
                                ShowMessageFX.Warning(getStage(), "Record Save Successfully.","Warning", null);
                                pnEditMode = EditMode.UNKNOWN;
                            } else 
                                ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                        }
                       pnEditMode = EditMode.UNKNOWN;
                    break;
                case "btnUpdate":
                        if (oTrans.UpdateRecord()){
                            pnEditMode = oTrans.getEditMode();
                        } else 
                            ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                    break;
                case "btnCancel":
                    
                    oTrans.setWithUI(true);
                    pnEditMode = EditMode.UNKNOWN;
                    clearFields();   
                    //reload detail
                    break;
                case "btnActivate":
                    if (oTrans.ActivateRecord()){
                        ShowMessageFX.Warning(getStage(),"Account successfully activated.","Warning", null);
                        clearFields();
                    }else
                        ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                    break;
                case "btnDeactivate":
                    if (oTrans.DeactivateRecord()){
                        ShowMessageFX.Warning(getStage(),"Account successfully deactivated.","Warning", null);
                        clearFields();
                    }else
                        ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                    break;

                case "btnClose":
//                    if(ShowMessageFX.OkayCancel(null, "Employee Bank Info", "Are you sure, do you want to close?") == true){
//                        unloadForm();
//                        break;
//                    } else
//                        return;
            }
            
            initButton(pnEditMode);
        } catch (SQLException e) {
            e.printStackTrace();
            ShowMessageFX.Warning(getStage(),e.getMessage(), "Warning", null);
        }
    } 
private void loadValidID(){
        try {
            if(oTrans.getMaster(6).toString().equalsIgnoreCase("1")){
            lblStatus.setVisible(true);
                lblStatus.setText("ACTIVE");
                lblStatus.setStyle("-fx-background-color:green; -fx-border-color: green; -fx-border-radius:5; -fx-background-radius:5");
               
            }else if(oTrans.getMaster(6).toString().equalsIgnoreCase("0")){
                lblStatus.setVisible(true);
                lblStatus.setText("INACTIVE");
                lblStatus.setStyle("-fx-background-color: red;-fx-border-color: red; -fx-border-radius:5; -fx-background-radius:5");
            }else{
                lblStatus.setVisible(false);
            }
            txtField01.setText((String) oTrans.getMaster(1));
            txtField02.setText((String) oTrans.getMaster(2));
            txtField03.setText((String) oTrans.getMaster(3));
            
            if (oTrans.getMaster(3).equals(1)){
                chkExpiration.isSelected();
            }
            if (oTrans.getMaster(4).equals(1)){
                chkBackView.isSelected();
            }
            

            
        } catch (SQLException e) {
            ShowMessageFX.Warning(getStage(),e.getMessage(), "Warning", null);
        }
    }
private boolean sendIdentification(){
        try {
            oTrans.setMaster("sIDNamexx", txtField02.getText());
            oTrans.setMaster("sIDLabelx", txtField03.getText());
            if (chkExpiration.isSelected()){
                oTrans.setMaster("cWithExpr", 1);
            }
            
            if (chkBackView.isSelected()){
                oTrans.setMaster("cWithBack", 1);
            }
        
    
            } catch (SQLException ex) {
              ShowMessageFX.Warning(getStage(),ex.getMessage(), "Warning", null);
        }

        return true;
    }

public void clearFields(){
        txtField01.clear();
        txtField02.clear();
        txtField03.clear();
        lblStatus.setText("UNKNOWN");
        lblStatus.setStyle("-fx-background-color:  #F88222; -fx-border-color:  #F88222; -fx-border-radius:5 -fx-background-radius:5");
               
        chkBackView.selectedProperty().setValue(false);
        chkExpiration.selectedProperty().setValue(false);
                
    }
}