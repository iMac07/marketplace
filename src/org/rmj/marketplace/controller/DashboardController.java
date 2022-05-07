/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.controller;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.rmj.marketplace.model.ScreenInterface;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.rmj.appdriver.GRider;
import org.rmj.marketplace.base.LTransaction;
import org.rmj.marketplace.base.ProductListing;
import org.rmj.marketplace.model.ImageModel;
import org.rmj.marketplace.model.ProductModel;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DashboardController implements Initializable, ScreenInterface {

    private Desktop desktop = Desktop.getDesktop();
    
    private int imgRow = -1;
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
   
    private  FileChooser fileChooser;
    @FXML
    private Button btnBrowse;
    @FXML
    private AnchorPane AnchorDashboard;
    @FXML
    private ImageView imgProduct;
    @FXML
    private TableView tblProdImage;
    @FXML
    private TableColumn<?, ?> imageIndex01;
    @FXML
    private TableColumn<?, ?> imageIndex02;
    
    private final ObservableList<ImageModel> img_data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oListener = new LTransaction() {
            @Override
            public void MasterRetreive(int i, Object o) {
             
                
            }
        };
        
        oTrans = new ProductListing(oApp, oApp.getBranchCode(), false);
        oTrans.setListener(oListener);
        oTrans.setTranStat(1023);
        oTrans.setWithUI(true);
        fileChooser = new FileChooser();
        btnBrowse.setOnAction(v->{
            configureFileChooser(fileChooser);                               
            List<File> list = 
                fileChooser.showOpenMultipleDialog(AnchorDashboard.getScene().getWindow());
//                    img_data.clear();
            if (list != null) {
                int index = img_data.size() + 1;
                for (File file : list) {
//                            openFile(file);
                    boolean imgIsExist = false;
                    for(int x = 0; x < img_data.size(); x++){
                        if(img_data.get(x).getImgIndex02().equalsIgnoreCase(file.toURI().toString())){
                            imgIsExist = true;
                        }

                    }
                    if(!imgIsExist){
                        ImageModel imgModel = new ImageModel(String.valueOf(index), file.toURI().toString());
                        img_data.add(imgModel);
                    }
                    index++;
                }
                initGrid();
            }
        });
    }    
    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue;//To change body of generated methods, choose Tools | Templates.
    }
    private void initGrid() {
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
        tblProdImage.getSelectionModel().select(imgRow + 1);
        tblProdImage.autosize();
        imgRow = tblProdImage.getSelectionModel().getSelectedIndex();
        loadImage();
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
        imgRow = tblProdImage.getSelectionModel().getSelectedIndex();
        
        loadImage();
        tblProdImage.setOnKeyReleased((KeyEvent t)-> {
            KeyCode key = t.getCode();
            switch (key){
                case DOWN:
                    imgRow = tblProdImage.getSelectionModel().getSelectedIndex();
                    if (imgRow == tblProdImage.getItems().size()) {
                        imgRow = tblProdImage.getItems().size();
                        loadImage();
                    }else {
//                            int y = 1;
//                            pnRow = pnRow + y;
                        loadImage();
                    }
                    break;
                case UP:
                    int pnRows = 0;
                    int x = 1;
                    pnRows = tblProdImage.getSelectionModel().getSelectedIndex();
                        imgRow = pnRows; 
                        loadImage();
                    break;
                default:
                    break; 
            }
        });
    }
    private void loadImage(){
        Image image = new Image(img_data.get(imgRow).getImgIndex02());
        imgProduct.setImage(image);
    }
}
