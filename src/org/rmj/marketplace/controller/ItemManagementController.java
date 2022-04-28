/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.controller;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.awt.event.MouseListener;
import java.io.IOException;
import org.rmj.marketplace.model.ScreenInterface;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agent.MsgBox;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.appdriver.agentfx.ShowMessageFX;
import org.rmj.appdriver.constants.EditMode;
import org.rmj.marketplace.base.LTransaction;
import org.rmj.marketplace.base.ProductListing;
import org.rmj.marketplace.model.ItemDescriptionModel;
import org.rmj.marketplace.model.ProductModel;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ItemManagementController implements Initializable, ScreenInterface {
    private final String pxeModuleName = "Item Management";

    /**
     * Initializes the controller class.
     */
    private GRider oApp;
   
    private ProductListing oTrans;
    private LTransaction  oListener;
    private int pnEditMode;
    private int pnRow = -1;
    private int dtailRow = -1;
    public static String listingStart;
    public static String listingEnd;
    private double xOffset = 0;
    private double yOffset = 0;
   
    private boolean pbLoaded = false;
    @FXML
    private AnchorPane searchBar;
    @FXML
    private TextField txtSeeks98;
    @FXML
    private TextField txtSeeks99;
    @FXML
    private TableView tblProdDesc;
    @FXML
    private TableColumn<?, ?> detailIndex01;
    @FXML
    private TableColumn<?, ?> detailIndex02;
    @FXML
    private TableView tblProducts;
    @FXML
    private TableColumn<?, ?> prodIndex01;
    @FXML
    private TableColumn<?, ?> prodIndex02;
    @FXML
    private TableColumn<?, ?> prodIndex03;
    @FXML
    private TableColumn<?, ?> prodIndex04;
    @FXML
    private HBox hbButtons;
    @FXML
    private Button btnBrowse;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnMoveUp;
    @FXML
    private Button btnMoveDown;
    @FXML
    private Button btnAddDesc;
    @FXML
    private Button btnRemoveDesc;
    @FXML
    private Button btnListingEnd;
    @FXML
    private Button btnListingStart;
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
    private TextField txtField11;
    @FXML
    private TextField txtField12;
    @FXML
    private TextField txtField13;
    @FXML
    private TextField txtField14;
    @FXML
    private TextField txtField15;
    @FXML
    private TextField txtField16;
    @FXML
    private TextField txtField17;
    @FXML
    private TextField txtField18;
    @FXML
    private TextField txtField19;
    @FXML
    private TextField txtField21;
    @FXML
    private TextField txtField22;
    @FXML
    private TextField txtField23;
    @FXML
    private TextField txtField24;
    @FXML
    private ComboBox<?> Combo23;
    @FXML
    private ComboBox<?> Combo24;
    @FXML
    private TextField txtField29;
    @FXML
    private TextField txtField25;
    @FXML
    private TextField txtField26;
    @FXML
    private CheckBox Check20;
    @FXML
    private CheckBox Check21;
    @FXML
    private CheckBox Check22;
    @FXML
    private CheckBox Check26; 
    @FXML
    private AnchorPane AnchorItemManagement; 
    private final ObservableList<ProductModel> data = FXCollections.observableArrayList();
    private final ObservableList<ItemDescriptionModel> dataDesc = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        oListener = new LTransaction() {
            @Override
            public void MasterRetreive(int i, Object o) {
                try {
                    //                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                System.out.println(i);
                    loadDetail();
                } catch (SQLException ex) {
                    Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        oTrans = new ProductListing(oApp, oApp.getBranchCode(), false);
        oTrans.setListener(oListener);
        oTrans.setTranStat(0);
        oTrans.setWithUI(true);
        btnBrowse.setOnAction(this::cmdButton_Click);
        btnNew.setOnAction(this::cmdButton_Click);
        btnSave.setOnAction(this::cmdButton_Click);
        btnCancel.setOnAction(this::cmdButton_Click);
        btnUpdate.setOnAction(this::cmdButton_Click);
        btnAddDesc.setOnAction(this::cmdButton_Click);
        btnRemoveDesc.setOnAction(this::cmdButton_Click);
        btnMoveUp.setOnAction(this::cmdButton_Click);
        btnMoveDown.setOnAction(this::cmdButton_Click);
        btnSearch.setOnAction(this::cmdButton_Click);
        btnListingStart.setOnAction(this::cmdButton_Click);
        btnListingEnd.setOnAction(this::cmdButton_Click);
        
        txtField21.setOnKeyPressed(this::txtField_KeyPressed); 
        txtSeeks98.setOnKeyPressed(this::txtField_KeyPressed); 
        
       
        pnEditMode = EditMode.UNKNOWN;
        
        initButton(pnEditMode);
        pbLoaded = true;
        loadProducts();
        
    }    

    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue;//To change body of generated methods, choose Tools | Templates.
    }
     public static String getListingStart() {
        return listingStart;
    }

    public static void setListingStart(String val) {
        listingStart = val;
    }

    public static String getListingEnd() {
        return listingEnd;
    }

    public static void setListingEnd(String val) {
//        txtField11.setText(listingEnd);
        listingEnd = val;
    }
    private void loadMaster()  {
        try {
        txtSeeks98.setText((String) oTrans.getMaster(1));
        txtSeeks99.setText((String) oTrans.getMaster(16));
        
        txtField01.setText((String) oTrans.getMaster(1));
        txtField21.requestFocus();
  
        } catch (SQLException ex) {
            Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    private void loadProducts(){
        int lnCtr;
        try {
            data.clear();
            if (oTrans.LoadList("", false)){//true if by barcode; false if by description
                for (lnCtr = 1; lnCtr <= oTrans.getItemCount(); lnCtr++){
                    data.add(new ProductModel(String.valueOf(lnCtr),
                            (String) oTrans.getDetail(lnCtr, "sListngID"),
                            (String) oTrans.getDetail(lnCtr, "xBarCodex"),
                            (String) oTrans.getDetail(lnCtr, "sBriefDsc"),
                            (String) oTrans.getDetail(lnCtr, "xDescript"),
                            oTrans.getDetail(lnCtr, "nTotalQty").toString(),
                            oTrans.getDetail(lnCtr, "nQtyOnHnd").toString(),
                            oTrans.getDetail(lnCtr, "nResvOrdr").toString(),
                            oTrans.getDetail(lnCtr, "nSoldQtyx").toString(),
                            oTrans.getDetail(lnCtr, "nUnitPrce").toString(),
                            (String) oTrans.getDetail(lnCtr, "dListStrt"),
                            (String) oTrans.getDetail(lnCtr, "dListEndx "),
                            (String) oTrans.getDetail(lnCtr, "dInactive"),
                            (String) oTrans.getDetail(lnCtr, "dActivate"),
                            (String) oTrans.getDetail(lnCtr, "xBrandNme"),
                            (String) oTrans.getDetail(lnCtr, "xModelNme"),
                            (String) oTrans.getDetail(lnCtr, "xColorNme"),
                            (String) oTrans.getDetail(lnCtr, "xCategrNm")
                    ));
                }
                initGrid();
                
                tblproductdetail_column();
            } else {
                MsgBox.showOk(oTrans.getMessage());
            }    
        } catch (SQLException ex) {
            Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadDetail() throws SQLException{
            //load to grid the incentives.
        
//         data.clear();
         int lnCtr; 
        txtField01.setText((String)oTrans.getMaster(1));
//        txtSeeks98.setText((String)oTrans.getMaster(2));
//        txtSeeks99.setText((String)oTrans.getMaster(21));
        txtField21.setText((String)oTrans.getMaster(21));
        txtField03.setText((String)oTrans.getMaster(3));
        txtField04.setText((String)oTrans.getMaster(4));

        txtField05.setText(priceWithOutDecimal(Integer.valueOf(oTrans.getMaster(5).toString())));
        txtField06.setText(priceWithOutDecimal(Integer.valueOf(oTrans.getMaster(6).toString())));
        txtField07.setText(priceWithOutDecimal(Integer.valueOf(oTrans.getMaster(7).toString())));
        txtField08.setText(priceWithOutDecimal(Integer.valueOf(oTrans.getMaster(8).toString())));
        txtField09.setText(priceWithDecimal(Double.valueOf(oTrans.getMaster(9).toString())));
       
        txtField23.setText((String)oTrans.getMaster(23));
        txtField24.setText((String)oTrans.getMaster(24));
        txtField25.setText((String)oTrans.getMaster(25));
        txtField26.setText((String)oTrans.getMaster(26));
        txtField10.setText((String)oTrans.getMaster(10));
        txtField11.setText((String)oTrans.getMaster(11));
        txtField18.setText((String)oTrans.getMaster(19));
        txtField19.setText((String)oTrans.getMaster(18));
        
        
    }
    private void clearFields(){
       
        
//        
        
        txtField01.setText("");
        txtField21.setText("");
        txtField03.setText("");
        txtField05.setText("");
        txtField06.setText("");
        txtField07.setText("");
        txtField08.setText("");
        txtField09.setText("");
        txtField11.setText("");
        txtField10.setText("");
        txtField23.setText("");
        txtField24.setText("");
        txtField25.setText("");
        txtField18.setText("");
        txtField19.setText("");
        txtField04.setText("");
        txtField05.setText("");
        txtSeeks98.setText("");
        txtSeeks99.setText("");
        listingEnd = "";
        listingStart = "";
        dataDesc.clear();
    }
    private void resetTrans(){
        oTrans = new ProductListing(oApp, oApp.getBranchCode(), false);
        oTrans.setListener(oListener);
        oTrans.setWithUI(true);
        pnEditMode = EditMode.UNKNOWN;
        loadProducts();
    }
    public static String priceWithDecimal (Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(price);
    }
    public static String priceWithOutDecimal (Integer price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(price);
    }
    private void cmdButton_Click(ActionEvent event) {
        String lsButton = ((Button)event.getSource()).getId();
        try {
            switch (lsButton){
                case "btnBrowse":
                   
                    break;
                case "btnNew": 
                    if (oTrans.NewTransaction() ){
                        clearFields();
                        loadMaster();
                        pnEditMode = EditMode.ADDNEW;
                    } else
                        MsgBox.showOk(oTrans.getMessage());           
                    break;
                case "btnSearch":
//                       
                    if(txtField21.isFocused()){
                        MsgBox.showOk(txtField21.getId());
                         if (oTrans.searchItem(txtField21.getText(), false,true)){
                                pnEditMode = oTrans.getEditMode();
                        } else 
                            MsgBox.showOk(oTrans.getMessage());
                    }else if(txtField03.isFocused()){
                        MsgBox.showOk(txtField03.getId());
                        if (oTrans.searchItem(txtField03.getText(), false,false)){
                                pnEditMode = oTrans.getEditMode();
                        } else 
                            MsgBox.showOk(oTrans.getMessage());
                    }
                    
                    break;
                case "btnSave": 
                     try {
                         
                         if (oTrans.SaveTransaction()){
                            clearFields();
                            resetTrans();
                        } else {
                           MsgBox.showOk(oTrans.getMessage());
                        }
                        
                        } catch (SQLException e) {
                            e.printStackTrace();
                            MsgBox.showOk(e.getMessage());
                        }
                    break;
                case "btnAddDesc": 
                     try {
                        if(!txtField04.getText().trim().isEmpty()){
                             if (oTrans.addDescription(txtField04.getText(),false)){
                                 
                                pnEditMode = oTrans.getEditMode();
                                dataDesc.add(new ItemDescriptionModel(String.valueOf(dataDesc.size()), txtField04.getText(), false));

                                generateDescripton();
//                                initGridDetail();
                            } else {
                                MsgBox.showOk(oTrans.getMessage());
                            }
                        }
                        
                        } catch (SQLException e) {
                            e.printStackTrace();
                            MsgBox.showOk(e.getMessage());
                        } catch (ParseException ex) {
                    Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;

                case "btnRemoveDesc":
                        if (oTrans.delDescription(dtailRow)){
                           
                            generateDescripton();
                        } else 
                          MsgBox.showOk(oTrans.getMessage());
                    break;
                case "btnMoveUp":
                    if(oTrans.setDescriptPriority(dtailRow, true)){ 
                        tblProdDesc.getSelectionModel().select(dtailRow);
                        dtailRow--;
                        pnEditMode = oTrans.getEditMode(); 
                        generateDescripton();
                    }
                    break;
                case "btnMoveDown":
                    if(oTrans.setDescriptPriority(dtailRow, false)){ 
                        tblProdDesc.getSelectionModel().select(dtailRow);
                        dtailRow++;
                        pnEditMode = oTrans.getEditMode(); 
                        generateDescripton();
                    }
                    break;
                case "btnUpdate":
                        if (oTrans.UpdateTransaction()){
                            pnEditMode = oTrans.getEditMode(); 
                        } else 
                          MsgBox.showOk(oTrans.getMessage());
                    break;
                case "btnCancel":
                    if (ShowMessageFX.OkayCancel(null, pxeModuleName, "Do you want to disregard changes?") == true){  
                            clearFields();
                            resetTrans();
                        }
                    break;
                    
                case "btnListingStart":
                    loadListingDate("Start");
                    txtField10.setText(listingStart);
                    break;
                    
                case "btnListingEnd":
                    if(txtField10.getText().trim().isEmpty()){
                        MsgBox.showOk("Please select Listing Start first!!!");
                    }else{
                        loadListingDate("End");
                        txtField11.setText(listingEnd);
                    }
                    break;
                case "btnClose":
                    if(ShowMessageFX.OkayCancel(null, "Confirm", "Are you sure, do you want to close?") == true){
                        unloadForm();
                        break;
                    } else
                        return;
            }
            initButton(pnEditMode);
        } catch (SQLException e) {
            e.printStackTrace();
            MsgBox.showOk(e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    
    private void loadListingDate(String title){
        try {
            Stage stage = new Stage();
            
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/org/rmj/marketplace/view/ListingDateTime.fxml"));

            ListingDateTimeController loControl = new ListingDateTimeController();
            loControl.setGRider(oApp);
            loControl.setTitle(title);
            
            fxmlLoader.setController(loControl);
            
            //load the main interface
            Parent parent = fxmlLoader.load();
                
            parent.setOnMousePressed((MouseEvent event) -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            parent.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            });
            
            //set the main interface as the scene
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            stage.showAndWait();
            
//            loadDetail();
//            loadIncentives();
        } catch (IOException e) {
            e.printStackTrace();
            MsgBox.showOk(e.getMessage());
            System.exit(1);
        }
    }
    private void initButton(int fnValue){
        boolean lbShow = (fnValue == EditMode.ADDNEW || fnValue == EditMode.UPDATE);

        btnBrowse.setVisible(!lbShow);
        btnNew.setVisible(!lbShow);
        btnSave.setVisible(lbShow);
        btnUpdate.setVisible(!lbShow);
        btnSearch.setVisible(lbShow);
        btnCancel.setVisible(lbShow);
        
        btnMoveUp.setDisable(!lbShow);
        btnMoveDown.setDisable(!lbShow);
        btnAddDesc.setDisable(!lbShow);
        btnRemoveDesc.setDisable(!lbShow);
        btnSave.setManaged(lbShow); 
       
//        btnBrowse.setVisible(!lbShow);
//        
        txtField21.setDisable(!lbShow);
        txtField03.setDisable(!lbShow);
        txtField05.setDisable(!lbShow);
        txtField06.setDisable(!lbShow);
        txtField07.setDisable(!lbShow);
        txtField08.setDisable(!lbShow);
        txtField09.setDisable(!lbShow);
        btnListingStart.setDisable(!lbShow);
        btnListingEnd.setDisable(!lbShow);
        txtField23  .setDisable(!lbShow);
        txtField24.setDisable(!lbShow);
        txtField25.setDisable(!lbShow);
        txtSeeks98.setDisable(lbShow);
        txtSeeks99.setDisable(lbShow);
        if (lbShow){
            btnNew.setVisible(!lbShow);
            btnBrowse.setManaged(false);
            btnNew.setManaged(false);
            btnUpdate.setManaged(false); 
            EventHandler<MouseEvent> mouseEventHandler =new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Mouse event handler has been called.");
                }
            };
            txtField10.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandler);
//            txtField10.setOnAction(e -> {
//                System.out.println("Clicked txtField10");
//              
//            });
            txtField11.setOnMouseClicked((MouseEvent e) -> {
                System.out.println("Clicked txtField11");
            });
        }
    }
    private void initGrid() {
        prodIndex01.setStyle("-fx-alignment: CENTER;");
        prodIndex02.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        prodIndex03.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        prodIndex04.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
       
        prodIndex01.setCellValueFactory(new PropertyValueFactory<>("prodIndex01"));
        prodIndex02.setCellValueFactory(new PropertyValueFactory<>("prodIndex02"));
        prodIndex03.setCellValueFactory(new PropertyValueFactory<>("prodIndex03"));
        prodIndex04.setCellValueFactory(new PropertyValueFactory<>("prodIndex04"));
      
        tblProducts.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblProducts.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
        tblProducts.setItems(data);
        tblProducts.getSelectionModel().select(pnRow + 1);
        tblProducts.autosize();
    }
    private void initGridDetail(){
        
        detailIndex01.setStyle("-fx-alignment: CENTER;");
        detailIndex02.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        detailIndex01.setCellValueFactory(new PropertyValueFactory<>("detailIndex01"));
        detailIndex02.setCellValueFactory(new PropertyValueFactory<>("detailIndex02"));
       
        tblProdDesc.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblProdDesc.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
        tblProdDesc.setItems(dataDesc);
        tblProdDesc.getSelectionModel().select(dtailRow);
        tblProdDesc.autosize();
        
    }
    @FXML
    private void tblProducts_Clicked(MouseEvent event) {
        pnRow = tblProducts.getSelectionModel().getSelectedIndex() + 1;
        if(pnEditMode == EditMode.ADDNEW || pnEditMode == EditMode.UPDATE){
            MsgBox.showOk("Are you sure you want to disregard changes???");
        }else{
            getSelectedItems();
        }
    }
    @FXML
    private void tblProdDesc_Clicked(MouseEvent event) {
        dtailRow = tblProdDesc.getSelectionModel().getSelectedIndex();
        txtField04.setText(dataDesc.get(dtailRow).getDetailIndex02());
        System.out.println(dtailRow);
    }
     private void getSelectedItems(){
          try {
            //load to grid the incentives.
            txtField01.setText((String)oTrans.getDetail(pnRow, "sListngID"));
            txtField21.setText((String)oTrans.getDetail(pnRow, "xBarCodex"));
            txtField03.setText((String)oTrans.getDetail(pnRow, "sBriefDsc"));
            if(!oTrans.getDetail(pnRow, "sDescript").toString().trim().isEmpty()){
                dataDesc.clear();
                JSONParser parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) parser.parse((String) oTrans.getDetail(pnRow, "sDescript").toString().replaceAll("'","\""));


                for(int i=0; i<jsonArray.size();i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        dataDesc.add(new ItemDescriptionModel(String.valueOf(i+1),(String)jsonObject.get("sDescript"),
                                (boolean)jsonObject.get("bEmphasis")));
                }

                if(!dataDesc.isEmpty()){
                    initGridDetail();
                    txtField04.setText(dataDesc.get(0).getDetailIndex02());
                }
            }
            txtField05.setText(priceWithOutDecimal(Integer.valueOf(oTrans.getDetail(pnRow, "nTotalQty").toString())));
            txtField06.setText(priceWithOutDecimal(Integer.valueOf(oTrans.getDetail(pnRow, "nQtyOnHnd").toString())));
            txtField07.setText(priceWithOutDecimal(Integer.valueOf(oTrans.getDetail(pnRow, "nResvOrdr").toString())));
            txtField08.setText(priceWithOutDecimal(Integer.valueOf(oTrans.getDetail(pnRow, "nSoldQtyx").toString())));
            txtField09.setText(priceWithDecimal(Double.valueOf(oTrans.getDetail(pnRow, "nUnitPrce").toString())));

            txtField23.setText((String)oTrans.getDetail(pnRow, "xBrandNme"));
            txtField24.setText((String)oTrans.getDetail(pnRow, "xModelNme"));
            txtField25.setText((String)oTrans.getDetail(pnRow, "xColorNme"));
            txtField26.setText((String)oTrans.getDetail(pnRow, "xCategrNm"));
            txtField10.setText((String)oTrans.getDetail(pnRow, "dListStrt"));
            txtField11.setText((String)oTrans.getDetail(pnRow, "dListEndx "));
            txtField18.setText((String)oTrans.getDetail(pnRow, "dInactive"));
            txtField19.setText((String)oTrans.getDetail(pnRow, "dActivate"));
        } catch (SQLException ex) {
            MsgBox.showOk(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    private void generateDescripton(){
        try {
            
            dataDesc.clear();
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse((String) oTrans.getMaster("sDescript").toString().replaceAll("'","\""));


            for(int i=0; i<jsonArray.size();i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    dataDesc.add(new ItemDescriptionModel(String.valueOf(i+1),(String)jsonObject.get("sDescript"),
                            (boolean)jsonObject.get("bEmphasis")));
            }

            if(!dataDesc.isEmpty()){
                initGridDetail();
                 txtField04.setText(dataDesc.get(0).getDetailIndex02());
                 tblProdDesc.setDisable(false);
            }else{
                 txtField04.setText("");
                 tblProdDesc.setDisable(true);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void unloadForm(){
        StackPane myBox = (StackPane) AnchorItemManagement.getParent();
        myBox.getChildren().clear();
        myBox.getChildren().add(getScene("MainScreenBG.fxml"));
        
    }
    private AnchorPane getScene(String fsFormName){
         ScreenInterface fxObj = new MainDashboardController();
         fxObj.setGRider(oApp);
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxObj.getClass().getResource(fsFormName));
        fxmlLoader.setController(fxObj);      
   
        AnchorPane root;
        try {
            root = (AnchorPane) fxmlLoader.load();
            FadeTransition ft = new FadeTransition(Duration.millis(1500));
            ft.setNode(root);
            ft.setFromValue(1);
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();

            return root;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    private void txtField_KeyPressed(KeyEvent event){
        TextField txtField = (TextField)event.getSource();        
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        System.out.println(lnIndex);
        try{
           switch (event.getCode()){
            case F3:
            case ENTER:
                switch (lnIndex){
                    case 3: /*Search Description*/
                        if (oTrans.searchItem(txtField04.getText(), false,false)){
                                pnEditMode = oTrans.getEditMode();
                            } else 
                                MsgBox.showOk(oTrans.getMessage());
                        break;
                    case 21: /*Search Barcode*/
                        if (oTrans.searchItem(txtField21.getText(), false, true)){
                                pnEditMode = oTrans.getEditMode();
                            } else 
                                MsgBox.showOk(oTrans.getMessage());
                        break;
                    
                }
            
        } 
        }catch(SQLException e){
                MsgBox.showOk(e.getMessage());
        }
        switch (event.getCode()){
        case ENTER:
        case DOWN:
            CommonUtils.SetNextFocus(txtField);
            break;
        case UP:
            CommonUtils.SetPreviousFocus(txtField);
        }
        
    }
    
    private void onMouseClick(MouseEvent event) {
        TextField txtField = (TextField)event.getSource();        
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
//        try{
            switch (lnIndex){
                case 10: /*Search Description*/
                     MsgBox.showOk("10 " + txtField.getId());
                    break;
                case 11: /*Search Barcode*/
                     MsgBox.showOk("11 " + txtField.getId());
                    break;
            }
//        }catch(SQLException e){
//                MsgBox.showOk(e.getMessage());
//        }
    }
    public void tblproductdetail_column(){
         detailIndex01.prefWidthProperty().bind(tblProdDesc.widthProperty().multiply(0.035));
         detailIndex02.prefWidthProperty().bind(tblProdDesc.widthProperty().multiply(0.273));
          
    }

}

