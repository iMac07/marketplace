/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.controller;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.rmj.marketplace.model.ScreenInterface;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javax.imageio.ImageIO;
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
import org.rmj.marketplace.model.ImageModel;
import org.rmj.marketplace.model.ItemDescriptionModel;
import org.rmj.marketplace.model.ProductModel;

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
    private int imgRow = -1;
    private int statRow = -1;
    public static String listingStart;
    public static String listingEnd;
    private double xOffset = 0;
    private double yOffset = 0;
    private  FileChooser fileChooser;
    private String imagePath = "D:/Guanzon/MarketPlaceImages";
   
    private Desktop desktop = Desktop.getDesktop();
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
    private ImageView imgProduct;
    @FXML
    private ImageView imgDefault;
    @FXML
    private TableView tblProdImage;
    @FXML
    private TableColumn<?, ?> imageIndex01;
    @FXML
    private TableColumn<?, ?> imageIndex02;
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
    private Button btnRefresh;
//    @FXML
//    private Button btnImgBrowse;
    @FXML
    private Button btnAddImg;
    @FXML
    private Button btnRemoveImg;
    @FXML
    private Button btnImgMoveUp;
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
    private MenuButton menuStatus;
    @FXML
    private CheckMenuItem itemStat01;
    @FXML
    private CheckMenuItem itemStat02;
    @FXML
    private CheckMenuItem itemStat03;
    @FXML
    private CheckMenuItem itemStat04;
    @FXML
    private CheckMenuItem[] itemArr;
    @FXML
    private AnchorPane AnchorItemManagement; 
    private final ObservableList<ProductModel> data = FXCollections.observableArrayList();
    private final ObservableList<ItemDescriptionModel> dataDesc = FXCollections.observableArrayList();
    
    private final ObservableList<ImageModel> img_data = FXCollections.observableArrayList();
    private FilteredList<ProductModel> filteredData;
    
//    private final ObservableList<String> stats = FXCollections.observableArrayList();
     
    private ObservableList<String> stats = FXCollections.observableArrayList(
        "OPEN",
        "CLOSED",
        "POSTED",
        "CANCELLED"
    );
    
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
        oTrans.setTranStat(12340);
        oTrans.setWithUI(true);
        
        fileChooser = new FileChooser();
        generateDirectory();
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
        btnRefresh.setOnAction(this::cmdButton_Click);
//        btnImgBrowse.setOnAction(this::cmdButton_Click);
        btnAddImg.setOnAction(this::cmdButton_Click);
        btnRemoveImg.setOnAction(this::cmdButton_Click);
        btnImgMoveUp.setOnAction(this::cmdButton_Click);
        txtField21.setOnKeyPressed(this::txtField_KeyPressed); 
        txtField03.setOnKeyPressed(this::txtField_KeyPressed); 
        itemArr = new CheckMenuItem[]{itemStat01,itemStat02,itemStat03,itemStat04};
        itemArr[0].setOnAction(this::item_EventHandler);
        itemArr[1].setOnAction(this::item_EventHandler);
        itemArr[2].setOnAction(this::item_EventHandler);
        itemArr[3].setOnAction(this::item_EventHandler);
        pnEditMode = EditMode.UNKNOWN;
        
        initButton(pnEditMode);
        pbLoaded = true;
        loadProducts();
        itemArr[0].setSelected(true);
        itemArr[1].setSelected(true);
        itemArr[2].setSelected(true);
        loadChoiceBox();
        
        if(img_data.isEmpty()){
            imgDefault.setImage(new Image("/org/rmj/marketplace/images/no-image-available_1.png"));
            imgProduct.setImage(new Image("/org/rmj/marketplace/images/no-image-available_1.png"));
        }else{
            Image image = new Image(img_data.get(0).getImgIndex02());
            imgProduct.setImage(image);
            imgDefault.setImage(image);
        }
        
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
    private void loadChoiceBox(){
        for(int x = itemArr.length - 1; x >= 0;x--){
            if(itemArr[x].isSelected()){
                menuStatus.setText(itemArr[x].getText());
            }
        }
        if(!itemArr[0].isSelected() && 
                !itemArr[1].isSelected() && 
                !itemArr[2].isSelected() && 
                !itemArr[3].isSelected()){
            itemArr[0].setSelected(true);
            menuStatus.setText(itemArr[0].getText());
        }
//        if (item.isSelected()){
//            menuStatus.setText
//             (item.getText());
//        }else{
//           
//        }
//        cbStatus.setItems(stats);
//        comboStatus.setItems(stats);
//        cbStatus.getSelectionModel().select(statRow);
    }
    private void loadMaster()  {
        try {
//        txtSeeks98.setText((String) oTrans.getMaster(1));
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
                    
                    String listingStart = "";
                    String listingEnd = "";
                    if(oTrans.getDetail(lnCtr, "dListStrt") != null){
                        listingStart = oTrans.getDetail(lnCtr, "dListStrt").toString();
                    }
                    if(oTrans.getDetail(lnCtr, "dListEndx") != null){
                        listingEnd = oTrans.getDetail(lnCtr, "dListStrt").toString();
                    }
                    data.add(new ProductModel(String.valueOf(lnCtr),
                            (String) oTrans.getDetail(lnCtr, "sListngID"),
                            (String) oTrans.getDetail(lnCtr, "xBarCodex"),
                            (String) oTrans.getDetail(lnCtr, "sBriefDsc"),
                            (String) oTrans.getDetail(lnCtr, "sDescript"),
                            oTrans.getDetail(lnCtr, "nTotalQty").toString(),
                            oTrans.getDetail(lnCtr, "nQtyOnHnd").toString(),
                            oTrans.getDetail(lnCtr, "nResvOrdr").toString(),
                            oTrans.getDetail(lnCtr, "nSoldQtyx").toString(),
                            oTrans.getDetail(lnCtr, "nUnitPrce").toString(),
                            listingStart,
                            listingEnd,
                            (String) oTrans.getDetail(lnCtr, "dInactive"),
                            (String) oTrans.getDetail(lnCtr, "dActivate"),
                            (String) oTrans.getDetail(lnCtr, "xBrandNme"),
                            (String) oTrans.getDetail(lnCtr, "xModelNme"),
                            (String) oTrans.getDetail(lnCtr, "xColorNme"),
                            (String) oTrans.getDetail(lnCtr, "xCategrNm"),
                            (String) oTrans.getDetail(lnCtr, "sImagesxx")
                    ));
                  
                }
                initGrid();
                
                tblproductdetail_column();
            } else {
//                MsgBox.showOk(oTrans.getMessage());
            }    
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException" + ex.getMessage());
        } catch (DateTimeException ex) {
//            MsgBox.showOk(ex.getMessage());
            System.out.println("DateTimeException" + ex.getMessage());
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
//        txtField10.setText(oTrans.getMaster(10).toString());
//        txtField11.setText(oTrans.getMaster(11).toString());
        txtField18.setText((String)oTrans.getMaster(19));
        txtField19.setText((String)oTrans.getMaster(18));
        
        
    }
    private void clearFields(){
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
        imgDefault.setImage(new Image("/org/rmj/marketplace/images/no-image-available_1.png"));
        imgProduct.setImage(new Image("/org/rmj/marketplace/images/no-image-available_1.png"));
        dataDesc.clear();
        img_data.clear();
    }
    private void resetTrans(){
        oTrans = new ProductListing(oApp, oApp.getBranchCode(), false);
        oTrans.setListener(oListener);
        oTrans.setTranStat(12340);
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
                            MsgBox.showOk("Product item successfully saved.");
                            clearFields();
                            resetTrans();
                        } else {
                           MsgBox.showOk(oTrans.getMessage());
                        }
                        
                    } catch (SQLException e) {
                        e.printStackTrace();
                        MsgBox.showOk("SQLException " + e.getMessage());
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        MsgBox.showOk("NullPointerException " + e.getMessage());
                    }
                    break;
                case "btnAddDesc": 
                     try {
                        if(!txtField04.getText().trim().isEmpty()){
                             if (oTrans.addDescription(txtField04.getText(),false)){
                                 
                                pnEditMode = oTrans.getEditMode();
                                dataDesc.add(new ItemDescriptionModel(String.valueOf(dataDesc.size()), txtField04.getText(), false));

                                generateDescripton();
                                
                                txtField04.clear();
                                txtField04.requestFocus();
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
                            txtField04.clear();
                            txtField04.requestFocus();
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
                case "btnAddImg":
                    configureFileChooser(fileChooser);                               
                    File imgFile = 
                        fileChooser.showOpenDialog(AnchorItemManagement.getScene().getWindow());
                    if (imgFile != null) {
                        BufferedImage image = ImageIO.read(new File(imgFile.getAbsolutePath()));
                        File imgFilePath =  new File(imagePath, imgFile.getName());
                        if (imgFilePath.exists() && imgFilePath.isFile() && imgFilePath.canWrite()) {
                            imgFilePath.delete();//delete existing image file
                        }
                        ImageIO.write(image , "jpg",imgFilePath);
                        if (oTrans.addImage(imgFilePath.toURI().toString())){
                            pnEditMode = oTrans.getEditMode();
                            img_data.add(new ImageModel(String.valueOf(img_data.size()), imgFilePath.toURI().toString()));

                            generateImages();

                        } else {
                            MsgBox.showOk(oTrans.getMessage());
                        }
//                       
//                        }
                    }
                    break;
                case "btnRemoveImg":
                    System.out.println(img_data.size());
                    if(img_data.size() > 0){
                        btnRemoveImg.setDisable(false);
                        if (oTrans.delImage(imgRow)){
                           generateImages();
//                           txtField04.clear();
//                           txtField04.requestFocus();
                        } else 
                          MsgBox.showOk(oTrans.getMessage());
                    }else{
                        btnRemoveImg.setDisable(true);
                    }
                    
                    break;
                case "btnImgMoveUp":
                    if(oTrans.setImagePriority(imgRow, true)){ 
                        tblProdImage.getSelectionModel().select(imgRow);
                        imgRow--;
                        pnEditMode = oTrans.getEditMode(); 
                        generateImages();
                    }
                    break;
//                case "btnImgMoveDown":
//                    if(oTrans.setDescriptPriority(imgRow, false)){ 
//                        tblProdImage.getSelectionModel().select(dtailRow);
//                        imgRow++;
//                        pnEditMode = oTrans.getEditMode(); 
//                        generateDescripton();
//                    }
//                    break;
                case "btnUpdate":
                    if (oTrans.OpenTransaction(txtField01.getText())){
                          if (oTrans.UpdateTransaction()){
                                pnEditMode = oTrans.getEditMode(); 
//                            loadProducts();
                        } else {
                            MsgBox.showOk(oTrans.getMessage());
                        }
                    } else {
                        MsgBox.showOk(oTrans.getMessage());
                    }
//                        if (oTrans.UpdateTransaction()){
//                            pnEditMode = oTrans.getEditMode(); 
//                        } else 
//                          MsgBox.showOk(oTrans.getMessage());
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
               case "btnRefresh":
                    {
                        txtSeeks98.clear();
                        txtSeeks99.clear();
                        loadProducts();
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
        } catch (IOException ex) {
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
            
            Parent parent = fxmlLoader.load();
                
            parent.setOnMousePressed((MouseEvent event) -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            parent.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            });
            
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            stage.showAndWait();
            
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
        btnImgMoveUp.setDisable(!lbShow);
        tblProducts.setDisable(lbShow);
//        btnImgMoveDown.setDisable(!lbShow);
//        btnImgBrowse.setDisable(!lbShow);
//        tblProdImage.setDisable(!lbShow);
        imgProduct.setDisable(!lbShow);
        btnAddDesc.setDisable(!lbShow);
        btnRemoveDesc.setDisable(!lbShow);
        btnSave.setManaged(lbShow); 
       
        btnAddImg.setDisable(!lbShow);
        btnRemoveImg.setDisable(!lbShow);
//        btnBrowse.setVisible(!lbShow);
//        
        txtField21.setDisable(!lbShow);
        txtField03.setDisable(!lbShow);
        txtField04.setDisable(!lbShow);
        txtField05.setDisable(!lbShow);
        txtField06.setDisable(!lbShow);
        txtField07.setDisable(!lbShow);
        txtField08.setDisable(!lbShow);
        txtField09.setDisable(!lbShow);
        btnListingStart.setDisable(!lbShow);
        btnListingEnd.setDisable(!lbShow);
        txtField23.setDisable(!lbShow);
        txtField24.setDisable(!lbShow);
        txtField25.setDisable(!lbShow);
        txtSeeks98.setDisable(lbShow);
        txtSeeks99.setDisable(lbShow);
        if (lbShow){
            btnNew.setVisible(!lbShow);
            btnBrowse.setManaged(false);
            btnNew.setManaged(false);
            btnUpdate.setManaged(false); 
        }
    }
    private void autoSearch(TextField txtField){
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(products-> {
                    // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                        return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if(lnIndex == 98){
                    return (products.getProdIndex02().toLowerCase().contains(lowerCaseFilter)); // Does not match.

                }else{
                    return (products.getProdIndex03().toLowerCase().contains(lowerCaseFilter)|| products.getProdIndex15().toLowerCase().contains(lowerCaseFilter)
                            || products.getProdIndex16().toLowerCase().contains(lowerCaseFilter)); // Does not match.
                }
            });
        });
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
        filteredData = new FilteredList<>(data, b -> true);
        autoSearch(txtSeeks98);
        autoSearch(txtSeeks99);
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ProductModel> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tblProducts.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tblProducts.setItems(sortedData);
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
        pnRow = tblProducts.getSelectionModel().getSelectedIndex();
        if (pnRow >= 0){
            if(!tblProducts.getItems().isEmpty()){

                txtField04.clear();
                getSelectedItems();
                tblProducts.setOnKeyReleased((KeyEvent t)-> {
                    KeyCode key = t.getCode();
                    switch (key){
                        case DOWN:
                            if(pnEditMode == EditMode.ADDNEW || pnEditMode == EditMode.UPDATE){
                                MsgBox.showOk("Unable to show item details when adding/updating mode!!!");
                            }else{
                                pnRow = tblProducts.getSelectionModel().getSelectedIndex();
                                if (pnRow == tblProducts.getItems().size()) {
                                    pnRow = tblProducts.getItems().size();
                                    getSelectedItems();
                                }else {
        //                            int y = 1;
        //                            pnRow = pnRow + y;
                                    getSelectedItems();
                                }
                            }

                            break;
                        case UP:
                            if(pnEditMode == EditMode.ADDNEW || pnEditMode == EditMode.UPDATE){
                                MsgBox.showOk("Unable to show item details when adding/updating mode!!!");
                            }else{
                                int pnRows = 0;
                                int x = 1;
                                pnRows = tblProducts.getSelectionModel().getSelectedIndex();
                                pnRow = pnRows; 
                                getSelectedItems();
                            }
                            break;
                        default:
                            return; 
                    }
                });
            }
        }
    }
    @FXML
    private void tblProdDesc_Clicked(MouseEvent event) {
        try{
            dtailRow = tblProdDesc.getSelectionModel().getSelectedIndex();
            if (dtailRow >= 0){
                if(!tblProdDesc.getItems().isEmpty()){


                txtField04.setText(dataDesc.get(dtailRow).getDetailIndex02());
                tblProdDesc.getSelectionModel().select(dtailRow);
                tblProdDesc.setOnKeyReleased((KeyEvent t)-> {
                    KeyCode key = t.getCode();
                        switch (key){
                            case DOWN:
                                dtailRow = tblProdDesc.getSelectionModel().getSelectedIndex();
                                if (dtailRow == tblProdDesc.getItems().size()) {
                                    dtailRow = tblProdDesc.getItems().size();

                                    txtField04.setText(dataDesc.get(dtailRow).getDetailIndex02());
                                    tblProdDesc.getSelectionModel().select(dtailRow);
                                }else {
        //                            int y = 1;
        //                            pnRow = pnRow + y;

                                    txtField04.setText(dataDesc.get(dtailRow).getDetailIndex02());
                                    tblProdDesc.getSelectionModel().select(dtailRow);
                                }
                                break;
                            case UP:
                                int pnRows = 0;
                                int x = 1;
                                pnRows = tblProdDesc.getSelectionModel().getSelectedIndex();
                                dtailRow = pnRows; 
                                txtField04.setText(dataDesc.get(dtailRow).getDetailIndex02());
                                tblProdDesc.getSelectionModel().select(dtailRow);
                                break;
                            default:
                                return; 
                        }
                    });
                }
            }
        }catch(NullPointerException ex){
            System.out.println(ex);
        }
    } 
    private void getSelectedItems(){
        try {

            txtField01.setText(filteredData.get(pnRow).getProdIndex02());
            txtField21.setText(filteredData.get(pnRow).getProdIndex03());
            txtField03.setText(filteredData.get(pnRow).getProdIndex04());
            if(!filteredData.get(pnRow).getProdIndex05().trim().isEmpty()){
                dataDesc.clear();
                JSONParser parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) parser.parse(filteredData.get(pnRow).getProdIndex05().replaceAll("'","\""));


                for(int i=0; i<jsonArray.size();i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        dataDesc.add(new ItemDescriptionModel(String.valueOf(i+1),(String)jsonObject.get("sDescript"),
                                (boolean)jsonObject.get("bEmphasis")));
                }

                if(!dataDesc.isEmpty()){
                    initGridDetail();
  //                    txtField04.setText(dataDesc.get(0).getDetailIndex02());
                }
            }
            img_data.clear();
            if(!filteredData.get(pnRow).getProdIndex19().trim().isEmpty()){
                JSONParser parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) parser.parse(filteredData.get(pnRow).getProdIndex19().replaceAll("'","\""));


                for(int i=0; i<jsonArray.size();i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    img_data.add(new ImageModel(String.valueOf(i+1),
                            (String)jsonObject.get("sImageURL")));
                }
                
                if(!img_data.isEmpty()){
                    tblProdImage.setDisable(false);
                    imgRow = 0;
                    initImageGrid();
                }
            }else{
                imgDefault.setImage(new Image("/org/rmj/marketplace/images/no-image-available_1.png"));
                imgProduct.setImage(new Image("/org/rmj/marketplace/images/no-image-available_1.png"));
                tblProdImage.setDisable(true);
            }
            txtField05.setText(priceWithOutDecimal(Integer.valueOf(filteredData.get(pnRow).getProdIndex06())));
            txtField06.setText(priceWithOutDecimal(Integer.valueOf(filteredData.get(pnRow).getProdIndex07())));
            txtField07.setText(priceWithOutDecimal(Integer.valueOf(filteredData.get(pnRow).getProdIndex08())));
            txtField08.setText(priceWithOutDecimal(Integer.valueOf(filteredData.get(pnRow).getProdIndex09())));
            txtField09.setText(priceWithDecimal(Double.valueOf(filteredData.get(pnRow).getProdIndex10())));

            txtField23.setText(filteredData.get(pnRow).getProdIndex15());
            txtField24.setText(filteredData.get(pnRow).getProdIndex16());
            txtField25.setText(filteredData.get(pnRow).getProdIndex17());
            txtField26.setText(filteredData.get(pnRow).getProdIndex18());
            txtField10.setText(filteredData.get(pnRow).getProdIndex11());
            txtField11.setText(filteredData.get(pnRow).getProdIndex12());
            txtField18.setText(filteredData.get(pnRow).getProdIndex13());
            txtField19.setText(filteredData.get(pnRow).getProdIndex14());
            
        }catch (ParseException ex) {
            MsgBox.showOk("error");
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
                 txtField04.setText(dataDesc.get(0).getDetailIndex02());
                 tblProdDesc.setDisable(false);
            }else{
                 txtField04.setText("");
                 tblProdDesc.setDisable(true);
            }
            
            initGridDetail();
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void generateImages(){
        try {
            
            img_data.clear();
            if(oTrans.getMaster("sImagesxx") != null){
                JSONParser parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) parser.parse((String) oTrans.getMaster("sImagesxx").toString().replaceAll("'","\""));


                for(int i=0; i<jsonArray.size();i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        img_data.add(new ImageModel(String.valueOf(i+1),
                                (String)jsonObject.get("sImageURL")));
                }
               
                
            }
            if(img_data != null){
                tblProdImage.setDisable(false);
                initImageGrid();
            }else{
                imgRow = 0;
                img_data.add(new ImageModel(String.valueOf(1),
                                "/org/rmj/marketplace/images/no-image-available_1.png"));
                tblProdImage.setDisable(true);
                initImageGrid();
            }
        } catch (SQLException | ParseException ex) {
                System.out.println(ex.getMessage());
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
    private void item_EventHandler(ActionEvent e){
        CheckMenuItem check_menu = (CheckMenuItem)e.getSource();
        int lnIndex = Integer.parseInt(check_menu.getId().substring(9, 10));
        loadChoiceBox();
    }
    private void txtField_KeyPressed(KeyEvent event){
        TextField txtField = (TextField)event.getSource();        
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        
        try{
           switch (event.getCode()){
            case F3:
            case ENTER:
                switch (lnIndex){
                    case 3: /*Search Description*/
                        if (oTrans.searchItem(txtField03.getText(), false,false)){
                                pnEditMode = oTrans.getEditMode();
                            } 
                        break;
                    case 21: /*Search Barcode*/
                        if (oTrans.searchItem(txtField21.getText(), false, true)){
                                pnEditMode = oTrans.getEditMode();
                            } 
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
    public void tblproductdetail_column(){
         detailIndex01.prefWidthProperty().bind(tblProdDesc.widthProperty().multiply(0.035));
         detailIndex02.prefWidthProperty().bind(tblProdDesc.widthProperty().multiply(0.273));
          
    }

    private void initImageGrid() {
        imageIndex01.setStyle("-fx-alignment: CENTER;");
        imageIndex02.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        imageIndex01.setCellValueFactory(new PropertyValueFactory<>("imgIndex01"));
        imageIndex02.setCellValueFactory(new PropertyValueFactory<>("imgIndex02"));
       
        tblProdImage.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblProdImage.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
        tblProdImage.setItems(img_data);
        tblProdImage.getSelectionModel().select(imgRow);
        tblProdImage.autosize();
        loadImage(imgRow);
    }
    
    private static void configureFileChooser(
        final FileChooser fileChooser) {      
            fileChooser.setTitle("View Pictures");
            fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            );        
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
    }
 
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(
                Level.SEVERE, null, ex
            );
        }
    }
    @FXML
    private void tblProdImage_Clicked(MouseEvent event) {
        if(!tblProdImage.getItems().isEmpty()){
            imgRow = tblProdImage.getSelectionModel().getSelectedIndex();
        
            loadImage(imgRow);
            tblProdImage.setOnKeyReleased((KeyEvent t)-> {
                KeyCode key = t.getCode();
                switch (key){
                    case DOWN:
                        imgRow = tblProdImage.getSelectionModel().getSelectedIndex();
                        if (imgRow == tblProdImage.getItems().size()) {
                            imgRow = tblProdImage.getItems().size();
                            loadImage(imgRow);
                        }else {
    //                            int y = 1;
    //                            pnRow = pnRow + y;
                            loadImage(imgRow);
                        }
                        break;
                    case UP:
                        int pnRows = 0;
                        int x = 1;
                        pnRows = tblProdImage.getSelectionModel().getSelectedIndex();
                            imgRow = pnRows; 
                            loadImage(imgRow);
                        break;
                    default:
                        break; 
                }
            });
        }
        
    }
    private void loadImage(int row){
        if(row < 0 ){
            row++;
        }
         if(!img_data.isEmpty()){
            if(!img_data.get(row).getImgIndex02().isEmpty()){
               System.out.println("img url = " + img_data.get(row).getImgIndex02());
               Image image = new Image(img_data.get(row).getImgIndex02());
               imgProduct.setImage(image);
               imgDefault.setImage(image);
            }
         }
    }
    private void generateDirectory(){
        File dir = new File(imagePath);
        if (!dir.exists()){
            if (dir.mkdirs()) {
                System.out.println("Multiple directories are created!");
            } else {
                System.out.println("Failed to create multiple directories!");
            }
        }
    }
}

