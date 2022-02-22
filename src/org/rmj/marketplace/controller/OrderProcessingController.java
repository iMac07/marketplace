/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this torderlate file, choose Tools | Torderlates
 * and open the torderlate in the editor.
 */
package org.rmj.marketplace.controller;

import org.rmj.marketplace.model.ScreenInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import org.rmj.marketplace.model.OrderModel;
import org.rmj.appdriver.GRider;

/**
 * FXML Controller class
 *
 * @author user
 */
public class OrderProcessingController implements Initializable, ScreenInterface {
    
    private GRider oApp;
    @FXML
    private TableView tblorders;
    @FXML
    private TableColumn orderIndex01;
    @FXML
    private TableColumn orderIndex02;
    @FXML
    private TableColumn orderIndex03;
    @FXML
    private TableColumn orderIndex04;
    @FXML
    private TableColumn orderIndex05;
    
    @FXML 
    private TableView<OrderModel> tblClients;
    @FXML
    private TableColumn clientsIndex01;
    @FXML
    private TableColumn clientsIndex02;
    @FXML
    private TableColumn clientsIndex03;
    @FXML
    private TableColumn clientsIndex04;
    @FXML
    private Label lblSubTotal;
//    
    @FXML
    private AnchorPane MainOrderProcessing;
    private int pnRow = 0;
    private double total = 0.0;

    private ObservableList<OrderModel> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        Util util = new Util();
//        orderIndex01.setCellValueFactory(new PropertyValueFactory<>("photo"));
//        //empImage.setPrefWidth(100);
//        //empImage.setCellValueFactory(new PropertyValueFactory<> ("empPhoto"));
//        
//        
//        orderIndex03.setCellValueFactory(
//            new PropertyValueFactory<>("orderIndex03"));
//        
//        
//        orderIndex02.setCellValueFactory(
//            new PropertyValueFactory<>("orderIndex01"));
//        
//        orderIndex04.setCellValueFactory(
//            new PropertyValueFactory<>("orderIndex04"));
//        orderIndex05.setCellValueFactory(
//            new PropertyValueFactory<>("orderIndex05"));
////        Image img = new Image("https://www.guanzongroup.com.ph/wp-content/uploads/2021/08/huawei-matepad-pro-promo-july-2021.png");
////        
////        Image img = new Image("file://192.168.10.152/c/Users/user/Desktop/realme-8-pro-1.jpg");
////        ImageView iphone = new ImageView(img);
//        ImageView iphone = new ImageView(new Image(this.getClass().getResourceAsStream("/images/iphone.jpg")));
//        ImageView realme = new ImageView(new Image(this.getClass().getResourceAsStream("/images/realme.jpg")));
//        ImageView oppo = new ImageView(new Image(this.getClass().getResourceAsStream("/images/oppo.jpg")));
//        ImageView redmi = new ImageView(new Image(this.getClass().getResourceAsStream("/images/xiaomi.jpg")));
//        ImageView huawei = new ImageView(new Image(this.getClass().getResourceAsStream("/images/huawei.jpg")));
//        ImageView samsung = new ImageView(new Image(this.getClass().getResourceAsStream("/images/samsung.jpg")));
//
//        util.setImageAspectRatio(oppo);
//        util.setImageAspectRatio(realme);
//        util.setImageAspectRatio(redmi);
//        util.setImageAspectRatio(huawei);
//        util.setImageAspectRatio(samsung);
//        util.setImageAspectRatio(iphone);
//        
//        OrderModel order1 = new OrderModel(iphone, "1", "Iphone 11 Pro Max 64GB", "₱ 48,490.00","₱ 48,490.00");
//        OrderModel order2 = new OrderModel(oppo, "1", "Reno 6 5G (8GB+128GB)", "₱ 26,999.00","₱ 26,999.00");
//        OrderModel order3 = new OrderModel(redmi, "1", "Redmi Note 10 Pro (6GB+128GB)", "₱ 12,990.00","₱ 12,990.00");
//        OrderModel order4 = new OrderModel(samsung, "1", "Galaxy Z Fold3 5G (12GB+512GB)", "₱ 95,990.00","₱ 95,990.00");
//        OrderModel order5 = new OrderModel(huawei, "1", "Nova 9 (8GB+256GB)", "₱ 23,999.00","₱ 23,999.00");
//        OrderModel order6 = new OrderModel(realme, "1", "Realme 8 Pro (8GB+128GB)", "₱ 17,177.00","₱ 17,177.00");
//        data.add(order1);
//        data.add(order2);
//        data.add(order6);
//        data.add(order3);
//        data.add(order4);
//        data.add(order5);
//        double subtotal = 0.0;
//        for(int x = 0; x< data.size(); x++){
//            subtotal = subtotal + Double.parseDouble(data.get(x).getOrderIndex05().replaceAll("[₱ ,]", ""));
//        }
//        lblSubTotal.setText("₱ "+util.priceWithDecimal(subtotal));
//        
//        orderIndex02.setStyle("-fx-alignment: CENTER-LEFT");
////        orderIndex02.textProperty().
//        orderIndex02.setCellFactory(tc -> {
//            TableCell<OrderModel, String> cell = new TableCell<>();
//            Text text = new Text();
//            cell.setGraphic(text);
//            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
//            text.wrappingWidthProperty().bind(orderIndex02.widthProperty());
//            text.textProperty().bind(cell.itemProperty());
//            return cell ;
//        });
//        orderIndex01.setStyle("-fx-alignment: CENTER");
//        orderIndex03.setStyle("-fx-alignment: CENTER");
//        orderIndex04.setStyle("-fx-alignment: CENTER-RIGHT");
//        orderIndex05.setStyle("-fx-alignment: CENTER-RIGHT");
//        orderIndex01.prefWidthProperty().bind(tblorders.widthProperty().multiply(0.15));
//        orderIndex02.prefWidthProperty().bind(tblorders.widthProperty().multiply(0.47));
//        orderIndex03.prefWidthProperty().bind(tblorders.widthProperty().multiply(0.05));
//        orderIndex04.prefWidthProperty().bind(tblorders.widthProperty().multiply(0.15));
//        orderIndex05.prefWidthProperty().bind(tblorders.widthProperty().multiply(0.15));
//
//        orderIndex01.setResizable(false);
//        orderIndex02.setResizable(false);
//        orderIndex03.setResizable(false);
//        orderIndex04.setResizable(false);
//        orderIndex05.setResizable(false);
//        
//        tblorders.setItems(data);
        
//        
            
        
    }    

    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue; //To change body of generated methods, choose Tools | Torderlates.
    }
//    @FXML
//    private void tblOrderDetail_Click(MouseEvent event) {
//        pnRow = tblorders.getSelectionModel().getSelectedIndex();
//    }
    
}
