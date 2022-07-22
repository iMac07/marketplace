/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.controller;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import org.rmj.marketplace.model.ScreenInterface;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.appdriver.agentfx.ShowMessageFX;
import org.rmj.marketplace.base.Clients;
import static org.rmj.marketplace.controller.OrderProcessingController.priceWithDecimal;
import org.rmj.marketplace.model.ClientInfoModel;
import org.rmj.marketplace.model.OrderDetailModel;
import org.rmj.marketplace.model.OrderModel;

/**
 * FXML Controller class
 *
 * @author user
 */
public class OrderDetailController implements Initializable, ScreenInterface {
    
    private GRider oApp;
    private Clients oTrans;
    
    public int tbl_row = 0;
    private String psCode;
    private String clientID;
    private boolean state = false;
    public static OrderModel orderModel;
    private ObservableList<OrderDetailModel> orderdetail_data = FXCollections.observableArrayList();
    public static ClientInfoModel  cltModel;
    private final String pxeModuleName = "Order Detail";
    double total = 0;
    
    @FXML
    private Button btnExit;
    @FXML
    private FontAwesomeIconView glyphExit;
    @FXML
    private AnchorPane searchBar;
    @FXML
    private Label lblOrderDate;
    @FXML
    private Label lblVatperc;
    @FXML
    private Label lblVat;
    @FXML
    private Label lblVatLess;
    @FXML
    private Label lblSubtotl;
    @FXML
    private Label lblSubtotlAmt;
    @FXML
    private Label lblShipFee;
    @FXML
    private Label lblReference;
    @FXML
    private Label lblCustAdd;
    @FXML
    private Label lblCustName;
    @FXML
    private Label lblSellerName;
    @FXML
    private Label lblSellerAdd;
    @FXML
    private Label lblContactNo;
    @FXML
    private TableView<OrderDetailModel> tblOrders;
    @FXML
    private TableColumn orderDetIndex01,orderDetIndex02,orderDetIndex03,orderDetIndex04,
                        orderDetIndex05,orderDetIndex06,orderDetIndex07,orderDetIndex08;

    @FXML
    private Label lblTotal;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnExit.setOnAction(this::cmdButton_Click);
        try {
            
            loadIOrderDetails();
            System.out.println(psCode);
            loadOrderdDetails(psCode);
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue; //To change bod y of generated methods, choose Tools | Torderlates.
    }
     public void setOrderDetailObject(Clients foValue){
        oTrans = foValue;
    }
    public void setOrderDetailCode(String fsValue){
        psCode = fsValue;
    }
    public void setTableRow(int row){
        tbl_row = row;
    }
    
    public void setState(boolean fsValue){
        state = fsValue;
    }
    public static void setData(OrderModel orderdata){
        orderModel = orderdata;    
    }
    public static void setCltData(ClientInfoModel cltID){
        cltModel = cltID;    
    }
    private void cmdButton_Click(ActionEvent event) {
        String lsButton = ((Button)event.getSource()).getId();
        switch (lsButton){
            case "btnExit":
                
                CommonUtils.closeStage(btnExit);
                break;
                
            default:
                ShowMessageFX.Warning(null, pxeModuleName, "Button with name " + lsButton + " not registered.");
                return;
        }
    } 
    private void loadOrderdDetails(String strans) throws SQLException{
        
            orderdetail_data.clear();
           
            if (oTrans.LoadOrderDetail(strans)){
                int lnRow = oTrans.getOrderDetailItemCount(); 
                for (int lnCtr = 1; lnCtr <= lnRow; lnCtr++){
                    //get
                    total += (Double.parseDouble(oTrans.getDetailOrder(lnCtr, "nUnitPrce").toString()) * Double.parseDouble(oTrans.getDetailOrder(lnCtr, "nQuantity").toString()));
                    orderdetail_data.add(new OrderDetailModel(String.valueOf(lnCtr),
                    (String) oTrans.getDetailOrder(lnCtr, "sTransNox"),
                            (String) oTrans.getDetailOrder(lnCtr, "xDescript"),
                            (String) oTrans.getDetailOrder(lnCtr, "xBrandNme") +  ", " + oTrans.getDetailOrder(lnCtr, "xModelNme"),
                            (String) oTrans.getDetailOrder(lnCtr, "xColorNme"),
                            oTrans.getDetailOrder(lnCtr, "nQuantity").toString(),
                            priceWithDecimal(Double.valueOf(oTrans.getDetailOrder(lnCtr, "nUnitPrce").toString())),
                            priceWithDecimal(total),
                            "",
                            "",
                            "",
                            ""));
                            System.out.print((CommonUtils.NumberFormat((Number)total, "#,##0.00")));
                    
                }
                initGrid();
            }
    }
    
    public void loadIOrderDetails(){
        Double vat = 0.0;
        Double vatAmt = 0.0;
        Double vatLess = 0.0;
        Double subTotl = 0.0;
        Double Totl = 0.0;
        vat += (Double.parseDouble(orderModel.getOrderIndex06())* 100);
        vatAmt += (Double.parseDouble(orderModel.getOrderIndex06()) * Double.parseDouble(orderModel.getOrderIndex05()));
        vatLess += (Double.parseDouble(orderModel.getOrderIndex05())- vatAmt);
        subTotl = (Double.parseDouble(orderModel.getOrderIndex05()));
        Totl +=  (vatLess + vatAmt);
        lblReference.setText(orderModel.getOrderIndex02());
        lblOrderDate.setText(orderModel.getOrderIndex03());
        lblVatperc.setText("VAT " +(String.valueOf(vat) + " %"));
        lblVat.setText(priceWithDecimal(vatAmt));
        lblVatLess.setText(priceWithDecimal(vatLess));
        lblSubtotlAmt.setText(priceWithDecimal(subTotl));
        lblTotal.setText(priceWithDecimal(Totl));
        
       lblCustName.setText(cltModel.getClientIndex02());
       lblCustAdd.setText(cltModel.getClientIndex04());
       lblContactNo.setText(cltModel.getClientIndex03());
       psCode = (orderModel.getOrderIndex02());
    }
    
    private void initGrid() {
        
        orderDetIndex01.setStyle("-fx-alignment: CENTER;" );
        orderDetIndex02.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderDetIndex03.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderDetIndex04.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderDetIndex05.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderDetIndex06.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderDetIndex07.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        orderDetIndex08.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        
        orderDetIndex01.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex01"));
        orderDetIndex02.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex02"));
        orderDetIndex03.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex03"));
        orderDetIndex04.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex04"));
        orderDetIndex05.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex05"));
        orderDetIndex06.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex06"));
        orderDetIndex07.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex07"));
        orderDetIndex08.setCellValueFactory(new PropertyValueFactory<>("orderDetIndex08"));
      
        tblOrders.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblOrders.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
        tblOrders.setItems(orderdetail_data);
    }
}

