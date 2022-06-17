/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.rmj.marketplace.controller;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyBooleanPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.StringUtil;
import org.rmj.appdriver.agent.MsgBox;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.appdriver.agentfx.ShowMessageFX;
import org.rmj.appdriver.constants.EditMode;
import org.rmj.marketplace.base.LResult;
import org.rmj.marketplace.base.SalesOrder;
import org.rmj.marketplace.model.OrderDetailModel;
import org.rmj.marketplace.model.OrderPaymentTaggingModel;
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
    private LResult oListener;
    
    private String psCode;
    private SalesOrder oTrans;
    
    private int pnEditMode;
    public int tbl_row = 0;
    
    private double lastValue = (double)0;
    private double total_alloc = (double) 0;
    private double lastpercValue = (double)0;
    
    private ObservableList<OrderDetailModel> data = FXCollections.observableArrayList();
    private ObservableList<OrderPaymentTaggingModel> payt_data = FXCollections.observableArrayList();
        
        @FXML
    private VBox VBoxForm;

    @FXML
    private Button btnExit;

    @FXML
    private FontAwesomeIconView glyphExit;

    @FXML
    private Label lblHeader;

    @FXML
    private Label label;

    @FXML
    private TextField txtField01;

    @FXML
    private TextField txtField02;

    @FXML
    private TextField txtField03;

    @FXML
    private ChoiceBox<String> status;
    private String[] statuslb = {"OPEN","CLOSED","POSTED","CANCELLED"};
    
    @FXML
    private TextField txtField04;

    @FXML
    private TextArea txtField05;

    @FXML
    private TextField txtField06;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;
    

    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue;
    }
    public void setSalesOrder(SalesOrder foValue){
        oTrans = foValue;
    }
    
    public void setPaymentCode(String fsValue){
        psCode = fsValue;
    }
    public void setTableRow(int row){
        tbl_row = row;
    }
    
    public void setEditMode(int editmode){
        pnEditMode = editmode;
    }
    private Stage getStage(){
	return (Stage) txtField01.getScene().getWindow();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            status.getItems().addAll(statuslb);

            oListener = new LResult() {
                @Override
            public void OnSave(String message){
            loadPaymentDetail();
            }@Override
            public void OnCancel(String message){
            loadPaymentDetail();
            }
                };
            
            oTrans.setListener(oListener);
            
            btnOk.setOnAction(this::cmdButton_Click);
            btnExit.setOnAction(this::cmdButton_Click);
            btnCancel.setOnAction(this::cmdButton_Click);

            
            
            pnEditMode = oTrans.getEditMode();
            
            try {
                if (oTrans.getItemCount() > 0) pnRow = 1;
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(1);
            }
            
            initFields(pnEditMode);
            initButton(pnEditMode);
            

            loadPaymentDetail();     

            
            pbLoaded = true;
    }

        private void loadTransactionMaster() throws SQLException {
        
        if (oTrans.getPayment(pnRow,"cTranStat").toString().equalsIgnoreCase("0")){
            status.setValue("OPEN");
        }else if(oTrans.getPayment(pnRow,"cTranStat").toString().equalsIgnoreCase("1")){
        status.setValue("CLOSED");
        }else if(oTrans.getPayment(pnRow,"cTranStat").toString().equalsIgnoreCase("2")){
            status.setValue("POSTED");
            btnOk.setDisable(true);
        }else if(oTrans.getPayment(pnRow,"cTranStat").toString().equalsIgnoreCase("3")){
            status.setValue("CANCELLED");
            status.setDisable(true);
            btnOk.setDisable(true);
        }else{
           
        }

   }
    public void getStatus(ActionEvent event){

}
    private void initButton(int fnValue){
        boolean lbShow = (fnValue == EditMode.ADDNEW || fnValue == EditMode.UPDATE);

    }
    
    private void initFields(int fnValue){
        boolean lbShow = (fnValue == EditMode.UPDATE || fnValue == EditMode.ADDNEW);
        if(lbShow){
            txtField03.setDisable(false);
            txtField04.setDisable(false);

        }else {
            txtField03.setDisable(true);
            txtField04.setDisable(true);

        }
    }

    private void txtField_KeyPressed(KeyEvent event){
        TextField txtField = (TextField)event.getSource();
        int lnIndex = Integer.parseInt(((TextField)event.getSource()).getId().substring(8,10));
        String lsValue = txtField.getText().replace(",", "");
        
        switch (event.getCode()){
            case F3:
                break;
            case TAB:
                if (lnIndex == 11 || lnIndex == 12){
                    event.consume();
                    return;
                }
            case ENTER:
//            case DOWN:
//                try {
//                    double lnIncAmt = Double.valueOf(String.valueOf(oTrans.getPayment(tbl_row, "nInctvAmt")));
//                            
//                    switch (lnIndex){
//                        case 11:
//                            double lnOldpercent = Double.parseDouble(oTrans.getIncentiveEmployeeAllocationInfo("nAllcPerc", psCode, (String) oTrans.getDetail(pnRow, "sEmployID")).toString());
//                            if(lnIncAmt <= 0){
//                                 ShowMessageFX.Warning(getStage(), "Please specify incentive amount value!", "Warning", null);
//                                
//                                txtField.setText(String.valueOf(lastValue));
//                                txtField07.requestFocus();
//                            }else{
//                                 if (lastpercValue - lnOldpercent + Double.valueOf(lsValue) > 100){
//                                    ShowMessageFX.Warning(getStage(),"The specified percentage will exceed the incentive allocation.", "Warning", null);
//                                
//                                    txtField.setText(String.valueOf(lastValue));
//                                    txtField.requestFocus();
//                                 }else{
//                                     oTrans.setIncentiveEmployeeAllocationInfo("nAllcPerc", psCode, (String) oTrans.getDetail(pnRow, "sEmployID"), Double.valueOf(lsValue));
//                                 }
//                            }
//                            
//                            break;
//                        case 12:
//                            double lnOldAmt = Double.valueOf(String.valueOf(oTrans.getIncentiveEmployeeAllocationInfo("nAllcAmtx", psCode, (String) oTrans.getDetail(pnRow, "sEmployID"))));
//
//                            if (total_alloc - lnOldAmt + Double.valueOf(lsValue) > lnIncAmt){
//                                ShowMessageFX.Warning(getStage(),"The specified amount will exceed the incentive allocation.", "Warning", null);
//                            } else{
//                                oTrans.setIncentiveEmployeeAllocationInfo("nAllcAmtx", psCode, (String) oTrans.getDetail(pnRow, "sEmployID"), Double.valueOf(lsValue));
//
//                                if (pnRow <= oTrans.getItemCount())
//                                    pnRow++;
//                                else
//                                    pnRow = 1;
                                
                                
//                                tblemployee.getVisibleLeafColumns();
//                                int max = tblemployee.getItems().size();
//                                pnRow = Math.min(pnRow, max);
//                                if((tblemployee.getSelectionModel().getSelectedIndex()) == max-1){
//                                    pnRow = 1;
//                                }
//                                tblemployee.scrollTo(pnRow-1);
                                
//                            }

                            //loadEmployee();
                           // getSelectedItem();

                            txtField02.requestFocus();
                            event.consume();
                            return;
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
                
//                CommonUtils.SetNextFocus(txtField);
//                break;
            case UP:
                CommonUtils.SetPreviousFocus(txtField);
            }
    }
    private void txtArea_KeyPressed(KeyEvent event){
        if (event.getCode() == ENTER || event.getCode() == DOWN){ 
            event.consume();
            CommonUtils.SetNextFocus((TextArea)event.getSource());
        }else if (event.getCode() ==KeyCode.UP){
        event.consume();
            CommonUtils.SetPreviousFocus((TextArea)event.getSource());
        }
    }
    
    final ChangeListener<? super Boolean> txtArea_Focus = (o,ov,nv)->{ 
        if (!pbLoaded) return;
        
        TextArea txtField = (TextArea)((ReadOnlyBooleanPropertyBase)o).getBean();
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        String lsValue = txtField.getText();
        
        if (lsValue == null) return;
            try {
                if(!nv){ /*Lost Focus*/
                    switch (lnIndex){
                        case 8:
                           oTrans.setPayment(tbl_row, "sRemarksx", lsValue); 
                           txtField.setText((String) oTrans.getPayment(tbl_row, "sRemarksx"));
                           break;
                    }
                } else
                    txtField.selectAll();

                pnIndex = lnIndex;
            } catch (SQLException e) {
                ShowMessageFX.Warning(getStage(),e.getMessage(), "Warning", null);
                System.exit(1);
            }
    };
 
    public void loadPaymentDetail(){
    
        try { 
                txtField01.setText((String) oTrans.getPayment(pnRow, "sTransNox"));
                txtField02.setText((String) oTrans.getPayment(pnRow, "dTransact"));
                txtField03.setText(String.valueOf(oTrans.getPayment(pnRow, "sReferCde")));
                txtField04.setText(String.valueOf(oTrans.getPayment(pnRow, "sReferNox")));
                txtField05.setText((String) oTrans.getPayment(pnRow, "sRemarksx"));
                txtField06.setText(String.valueOf(oTrans.getPayment(pnRow, "nAmountxx")));

                loadTransactionMaster(); 

                txtField01.setDisable(true);
                txtField02.setDisable(true);
                txtField03.setDisable(true);
                txtField04.setDisable(true);
                txtField06.setDisable(true);// amount 
                txtField05.setDisable(pnEditMode != EditMode.ADDNEW && pnEditMode != EditMode.UPDATE); //remarks
                status.setDisable(pnEditMode != EditMode.ADDNEW && pnEditMode != EditMode.UPDATE);
                

 
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
    
    private void cmdButton_Click(ActionEvent event) {

        try {
            String lsButton = ((Button)event.getSource()).getId();
            switch (lsButton){
                   
                case "btnCancel":
                    CommonUtils.closeStage(btnCancel);
                    
                    break;
                case "btnOk":
                    if (status.getSelectionModel().getSelectedItem().equalsIgnoreCase("OPEN")) {
                    if (oTrans.getPayment(pnRow,"cTranStat").toString().equalsIgnoreCase("1")) 
                        ShowMessageFX.Warning(getStage(), "UNABLE TO OPEN A CONFIRM TRANSACTION","Warning", null);
                else{
                        ShowMessageFX.Warning(getStage(), "Please Confirm the Transaction First","Warning", null);  
                    }   
                   }else if (status.getSelectionModel().getSelectedItem().equalsIgnoreCase("CLOSED")){
                    oTrans.setPayment(tbl_row, "sRemarksx", txtField05.getText());
                    oTrans.setPayment(tbl_row, "cTranStat", status.getSelectionModel().getSelectedIndex());
                            if (oTrans.SaveTransaction()){
                            
                            ShowMessageFX.Warning(getStage(), "Transaction save successfully.", "Warning", null);
                            CommonUtils.closeStage(btnOk);
                  
                        } else {
                            ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                        }
}
                   
                    break;
                case "btnExit":
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
    
    

}

