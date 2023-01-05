/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.rmj.marketplace.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyBooleanPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.StringUtil;
import org.rmj.appdriver.agent.MsgBox;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.appdriver.agentfx.ShowMessageFX;
import org.rmj.appdriver.constants.EditMode;
import org.rmj.marketplace.base.LResult;
import org.rmj.marketplace.base.OrderList;
import org.rmj.marketplace.base.SalesOrder;
import static org.rmj.marketplace.controller.OrderProcessingController.priceWithDecimal;
import org.rmj.marketplace.model.ScreenInterface;


/**
 * FXML Controller class
 *
 * @author user
 */
public class OrderPaymentTaggingController implements Initializable, ScreenInterface {  
    private final String pxeModuleName = "OrderPaymentTaggingController";
    
    private GRider oApp;
    private int pnIndex = -1;
    private int pnRow = 0;
    
    private boolean pbLoaded = false;
    private boolean state = false;
    private LResult oListener;
    
    private String psCode;
    private String dTransact;
    private OrderList oTrans;
    
    private int pnEditMode;
    public int tbl_row = 0;
    

    @FXML
    private Button btnExit;
    @FXML
    private TextField txtField01,txtField02,txtField03,txtField04,txtField05,txtField06;
    @FXML
    private ComboBox status;
        private ObservableList<String> statuslb = FXCollections.observableArrayList("OPEN","CLOSED","POSTED","CANCELLED");
//    private String[] statuslb = {"OPEN","CLOSED","POSTED","CANCELLED"};
    @FXML
    private Button btnSave,btnClose;

    

    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue;
    }
    public void setSalesOrder(OrderList foValue){
        oTrans = foValue;
    }
    public void setListener(LResult foValue){
        oListener = foValue;
    }
    
    public void setPaymentCode(String fsValue){
        psCode = fsValue;
    }
    public void setTableRow(int row){
        tbl_row = row;
    }
    
    public void setState(boolean fsValue){
        state = fsValue;
    }

    public void setDateTransact(String fsValue){
        dTransact = fsValue;
    }

    public void setEditMode(int editmode){
        System.out.println(editmode);
        pnEditMode = editmode;
    }
    private Stage getStage(){
	return (Stage) txtField01.getScene().getWindow();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
            status.setItems(statuslb);
            
            
            btnSave.setOnAction(this::cmdButton_Click);
            btnClose.setOnAction(this::cmdButton_Click);
            btnExit.setOnAction(this::cmdButton_Click);
            
//            pnEditMode = oTrans.getEditMode();
            
            try {
                if (oTrans.getItemCount() > 0) pnRow = 1;
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(1);
            }
            
            initFields(pnEditMode);
            initButton(pnEditMode);
            
            loadPaymentDetail(psCode);     
            
            pbLoaded = true;
    }
    
    public void getStatus(ActionEvent event){

}
    private void initButton(int fnValue){
        boolean lbShow = (fnValue == EditMode.ADDNEW || fnValue == EditMode.UPDATE);

    }
    
    private void initFields(int fnValue){
        boolean lbShow = (fnValue == EditMode.UPDATE || fnValue == EditMode.ADDNEW);
        if(lbShow){
            txtField05.setDisable(false);
            txtField06.setDisable(false);
            txtField06.requestFocus();
        }else {
            txtField05.setDisable(true);
            txtField05.setDisable(true);
            txtField06.setDisable(true);
        }
    }

    public void loadPaymentDetail(String psCode){
    
        try { 
            txtField01.setText((String) oTrans.getPayment(tbl_row, "sTransNox"));
            txtField02.setText(dTransact);
            txtField03.setText(String.valueOf(oTrans.getPayment(tbl_row, "sReferNox")));
            txtField04.setText(priceWithDecimal(Double.valueOf(oTrans.getPayment(tbl_row, "nTotlAmnt").toString())));
            txtField05.setText((String) oTrans.getPayment(tbl_row, "sRemarksx"));
            txtField06.setText(priceWithDecimal(Double.valueOf(oTrans.getPayment(tbl_row, "nAmtPaidx").toString())));

            status.getSelectionModel().select(Integer.parseInt(oTrans.getPayment(tbl_row,"cTranStat").toString()));   
            if(Integer.parseInt((String) oTrans.getPayment(tbl_row,"cTranStat"))>0){
                status.setDisable(true);
                txtField05.setDisable(true);
                btnSave.setDisable(true);
            }
    //                loadTransactionMaster(); 

            txtField01.setDisable(true);
            txtField02.setDisable(true);
            txtField03.setDisable(true);
            txtField04.setDisable(true);
//            if(oTrans.OpenPayment(psCode)){
//                  System.out.println("tbl_row = " + tbl_row);
////                txtField02.setText((String) oTrans.getPayment(tbl_row, "dTransact"));
//
//                  initFields(pnEditMode);
//                  for(int lnIndex = 1; lnIndex <= oTrans.getPaymentItemCount(); lnIndex++){
//                        tbl_row = lnIndex;
//                        
//                        txtField01.setText((String) oTrans.getPayment(tbl_row, "sTransNox"));
//                        txtField02.setText(dTransact);
//                        txtField03.setText(String.valueOf(oTrans.getPayment(tbl_row, "sReferNox")));
//                        txtField04.setText(priceWithDecimal(Double.valueOf(oTrans.getPayment(tbl_row, "nTotlAmnt").toString())));
//                        txtField05.setText((String) oTrans.getPayment(tbl_row, "sRemarksx"));
//                        txtField06.setText(priceWithDecimal(Double.valueOf(oTrans.getPayment(tbl_row, "nAmtPaidx").toString())));
//
//                        status.getSelectionModel().select(Integer.parseInt(oTrans.getPayment(tbl_row,"cTranStat").toString()));   
//                        if(Integer.parseInt((String) oTrans.getPayment(tbl_row,"cTranStat"))>0){
//                            status.setDisable(true);
//                            txtField05.setDisable(true);
//                            btnSave.setDisable(true);
//                        }
//        //                loadTransactionMaster(); 
//
//                        txtField01.setDisable(true);
//                        txtField02.setDisable(true);
//                        txtField03.setDisable(true);
//                        txtField04.setDisable(true);
//                  }
//                   
//            }
              
 
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
    
    private void cmdButton_Click(ActionEvent event) {
        try {
            String lsButton = ((Button)event.getSource()).getId();
            switch (lsButton){
                   
                case "btnClose":
                    if (oListener != null) oListener.OnCancel("Transaction cancelled successfully.");
                    CommonUtils.closeStage(btnClose);
                    break;
                case "btnSave":
                    
                    if (oTrans.getPayment(tbl_row,"cTranStat").toString().equalsIgnoreCase("1")){
                        ShowMessageFX.Warning(getStage(), "UNABLE TO SAVE A CONFIRM TRANSACTION","Warning", null);
                   
                    }  
                    else if (oTrans.getPayment(tbl_row,"cTranStat").toString().equalsIgnoreCase("2")){
                        ShowMessageFX.Warning(getStage(), "UNABLE TO SAVE A POSTED TRANSACTION","Warning", null);
                   
                    } 
                    else if (oTrans.getPayment(tbl_row,"cTranStat").toString().equalsIgnoreCase("3")){
                        ShowMessageFX.Warning(getStage(), "UNABLE TO SAVE A CANCELLED TRANSACTION","Warning", null);
                    } 
                    else{
                        if(status.getSelectionModel().getSelectedIndex() > 0){
                           oTrans.setPayment(tbl_row, "nAmtPaidx", Double.parseDouble(txtField06.getText().trim().replace(",", "")));
                               if (status.getSelectionModel().getSelectedIndex() == 3 ){
                                oTrans.setPayment(tbl_row, "sRemarksx", txtField05.getText());
                                oTrans.setPayment(tbl_row, "cTranStat", status.getSelectionModel().getSelectedIndex());
                                if (oTrans.SaveTransaction()){
                                ShowMessageFX.Warning(getStage(), "Transaction save successfully.", "Warning", null);
                                CommonUtils.closeStage(btnSave);

                                }else {
                                 ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                            }
                                
                            }else if (oTrans.getPayment(tbl_row, "nAmtPaidx").toString().equalsIgnoreCase("0.0")) {
                            ShowMessageFX.Warning(getStage(), "Invalid Amount Paid","Warning", null);
  
                            }else{
                            oTrans.setPayment(tbl_row, "sRemarksx", txtField05.getText());
                            oTrans.setPayment(tbl_row, "cTranStat", status.getSelectionModel().getSelectedIndex());
                            if (oTrans.SaveTransaction()){
                                ShowMessageFX.Warning(getStage(), "Transaction save successfully.", "Warning", null);
                                CommonUtils.closeStage(btnSave);

                            }else {
                             ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                            }
                                }
                            }else{
                            ShowMessageFX.Warning(getStage(), "Please Confirm the Transaction First","Warning", null);  
                        }
                        
                    }
                   
                    break;
                case "btnExit":
                    if (oListener != null) oListener.OnCancel("Transaction cancelled successfully.");
                    CommonUtils.closeStage(btnExit);
                    break;
                    
                default:
                    ShowMessageFX.Warning(null, pxeModuleName, "Button with name " + lsButton + " not registered.");
                    return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderPaymentTaggingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

//    final ChangeListener<? super Boolean> txtField_Focus = (o,ov,nv)->{ 
//        if (!pbLoaded) return;
//        
//        TextField txtField = (TextField)((ReadOnlyBooleanPropertyBase)o).getBean();
//        int lnIndex = Integer.parseInt(txtField.getId().substring(8,10));
//        
//        String lsValue = txtField.getText();
//        
//        if (lsValue == null) return;
//        if (lsValue.isEmpty()) return;    
//        
//        try {
//            if(!nv){ /*Lost Focus*/
//                switch (lnIndex){
//                    case 06:
//                        if (StringUtil.isNumeric(lsValue)) 
//                            oTrans.setPayment(tbl_row, "nAmtPaidx", Integer.parseInt(lsValue));
//                        else    
//                            oTrans.setPayment(tbl_row, "nAmtPaidx", 0);
//
//                        txtField.setText(priceWithDecimal(Double.valueOf(oTrans.getPayment(tbl_row, "nAmtPaidx").toString())));
//                        break;
//                }
//            } else{ //Focus
//                txtField.selectAll();
//                pnIndex = lnIndex;
//            } 
//        } catch (SQLException e) {
//            ShowMessageFX.Warning(getStage(),e.getMessage(), "Warning", null);
//        }
//    };
    
}

