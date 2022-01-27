/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.rmj.marketplace.model.ScreenInterface;
import org.rmj.appdriver.GRider;

/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable, ScreenInterface {
    private GRider oApp;
    @FXML
    private Pane btnMin;
    @FXML
    private Pane btnClose;
    @FXML
    private StackPane workingSpace;
    @FXML
    private VBox nav_bar;
    @FXML
    private Button drawer;
    @FXML
    private ToggleButton btnItemManagement;
    @FXML
    private ToggleButton btnOrder;
    @FXML
    private ToggleButton btnClient;
    @FXML
    private ToggleButton btnQA;
    @FXML
    private ToggleButton btnDashboard;
    @FXML
    private ToggleButton btnPickup;
    @FXML
    private ToggleButton btnWayBill;
    @FXML
    private ToggleButton btnRatings;
    @FXML
    private FontAwesomeIconView drawer_icon;
    private ToggleGroup drawer_button;
    private TranslateTransition openNav;
    private TranslateTransition closeNav;
    private TranslateTransition closeFastNav;
   
    AnchorPane item_management,order_processing,client_info,question_answer;
    @FXML
    private AnchorPane MainAnchor;
    @FXML
    private BorderPane main_container;
    @FXML
    private StackPane top_navbar;
    @FXML
    private Pane hamburger;
    @FXML
    private StackPane hamburger__inner;
    @FXML
    private Label DateAndTime;
    @FXML
    private Pane view;
    @FXML
    private Label AppUser;
    @FXML
    private Label AppDepartment;
    @FXML
    private void drawerClick(MouseEvent event) {  
      drawerOpen();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        getTime();
        try {
            
            setScene(loadAnimate("/org/rmj/marketplace/view/MainDashboard.fxml"));
            // TODO
            item_management = FXMLLoader.load(getClass().getResource("/org/rmj/marketplace/view/ItemManagement.fxml"));
            order_processing = FXMLLoader.load(getClass().getResource("/org/rmj/marketplace/view/ItemManagement.fxml"));
            client_info = FXMLLoader.load(getClass().getResource("/org/rmj/marketplace/view/ItemManagement.fxml"));
            question_answer = FXMLLoader.load(getClass().getResource("/org/rmj/marketplace/view/ItemManagement.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        openNav = new TranslateTransition(Duration.millis(100), nav_bar);
        openNav.setToX(nav_bar.getTranslateX()-nav_bar.getWidth());
        closeNav = new TranslateTransition(Duration.millis(100), nav_bar);
        closeFastNav = new TranslateTransition(Duration.millis(.1), nav_bar);
        btnDashboard.pressedProperty();
        initToggleGroup();
        openDrawerAlignment();
    } 
    private void initToggleGroup(){
         drawer_button = new ToggleGroup();
         btnDashboard.setToggleGroup(drawer_button);
         btnItemManagement.setToggleGroup(drawer_button);
         btnOrder.setToggleGroup(drawer_button);
         btnClient.setToggleGroup(drawer_button);
         btnQA.setToggleGroup(drawer_button);
         btnRatings.setToggleGroup(drawer_button);
         btnWayBill.setToggleGroup(drawer_button);  
         btnPickup.setToggleGroup(drawer_button);
         btnDashboard.setSelected(true);
//         drawer_button.selectedToggleProperty().addListener(
//				new ChangeListener<Toggle>()
//	{
//            public void changed(ObservableValue<? extends Toggle> ov,
//                                    final Toggle toggle, final Toggle new_toggle)
//            {
//                    String toggleBtn = ((ToggleButton)new_toggle).getText();
//                    System.out.println(toggleBtn);
//            }
//        });
    }
    
    private void tooltip (){
        Tooltip dashtip = new Tooltip("DASHBOARD");
        hackTooltipStartTiming(dashtip);
        btnDashboard.setTooltip(dashtip);
        
        Tooltip itemmgmttip = new Tooltip("ITEM MANAGEMENT");
        hackTooltipStartTiming(itemmgmttip);
        btnItemManagement.setTooltip(itemmgmttip);
        
        Tooltip ordprostip = new Tooltip("ORDER PROCESSING");
        hackTooltipStartTiming(ordprostip);
        btnOrder.setTooltip(ordprostip);
        
        Tooltip billtip = new Tooltip("WAY BILL");
        hackTooltipStartTiming(billtip);
        btnWayBill.setTooltip(billtip);
        
        Tooltip picktip = new Tooltip("PICK UP");
        hackTooltipStartTiming(picktip);
        btnPickup.setTooltip(picktip);
        
        Tooltip cltinfotip = new Tooltip("CLIENT INFO");
        hackTooltipStartTiming(cltinfotip);
        btnClient.setTooltip(cltinfotip);
        
        Tooltip qnatip = new Tooltip("QUESTION AND ANSWER");
        hackTooltipStartTiming(qnatip);
        btnQA.setTooltip(qnatip);
        
        Tooltip qnarate = new Tooltip("RATINGS AND REVIEWS");
        hackTooltipStartTiming(qnarate);
        btnRatings.setTooltip(qnarate);
        
    }
    public void setGRider(GRider foValue) {
        oApp = foValue;
    }
    public void drawerOpen() {
        
       if ((nav_bar.getWidth()) == 50 ) {
//            drawer.getStyleClass().remove("sidebar-button");
//            drawer.getStyleClass().add("sidebar-button-active");
            
            
            
            openDrawerAlignment();
            openNav.play(); 
            
            
        } else {
//            ln(-(nav_bar.getWidth()));
            
            closeDrawerAlignment();
            closeNav.play();
          }
    } 
    private void setScene(AnchorPane foPane){
        workingSpace.getChildren().clear();
        workingSpace.getChildren().add(foPane);
    }
    @FXML
    private void switchItem(ActionEvent event) throws IOException {
        setScene(loadAnimate("/org/rmj/marketplace/view/ItemManagement.fxml"));
    }
    @FXML
    private void switchOrder(ActionEvent event) throws IOException {
        setScene(loadAnimate("/org/rmj/marketplace/view/OrderProcessing.fxml"));
    }
    @FXML
    private void switchWayBill(ActionEvent event) throws IOException {
        setScene(loadAnimate("/org/rmj/marketplace/view/WayBill.fxml"));
    }
    @FXML
    private void switchPickup(ActionEvent event) throws IOException {
        setScene(loadAnimate("/org/rmj/marketplace/view/Pickup.fxml"));
    }
    @FXML
    private void switchClient(ActionEvent event) throws IOException {
        setScene(loadAnimate("/org/rmj/marketplace/view/ClientInfo.fxml"));
      
    }
    @FXML
    private void switchQA(ActionEvent event) throws IOException {
        setScene(loadAnimate("/org/rmj/marketplace/view/Faq.fxml"));
    }
    
    @FXML
    private void switchDashboard(ActionEvent event) throws IOException {
        setScene(loadAnimate("/org/rmj/marketplace/view/MainDashboard.fxml"));
    }
    @FXML
    private void switchRatings(ActionEvent event) throws IOException {
        setScene(loadAnimate("/org/rmj/marketplace/view/RatingsAndReviews.fxml"));
    }
    @FXML
    private void handleButtonCloseClick(MouseEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleButtonMinimizeClick(MouseEvent event) {
        Stage stage = (Stage) btnMin.getScene().getWindow();
        stage.setIconified(true);
    }
    private ScreenInterface getController(String fsValue){
       
        switch (fsValue){
            case "/org/rmj/marketplace/view/MainDashboard.fxml":
                return (ScreenInterface) new MainDashboardController();
             case "/org/rmj/marketplace/view/ItemManagement.fxml":
                return (ScreenInterface) new ItemManagementController();
             case "/org/rmj/marketplace/view/OrderProcessing.fxml":
                return (ScreenInterface) new OrderProcessingController();
             case "/org/rmj/marketplace/view/WayBill.fxml":
                return (ScreenInterface) new WayBillController();
             case "/org/rmj/marketplace/view/Pickup.fxml":
                return (ScreenInterface) new PickupController();
             case "/org/rmj/marketplace/view/ClientInfo.fxml":
                return (ScreenInterface) new ClientInfoController();
             case "/org/rmj/marketplace/view/RatingsAndReviews.fxml":
                return (ScreenInterface) new RatingsAndReviewsController();
             case "/org/rmj/marketplace/view/Faq.fxml":
                return (ScreenInterface) new FAQController();
            
            default:
                return null;
        }
    }
   private AnchorPane loadAnimate(String fsFormName){
       
        ScreenInterface fxObj = getController(fsFormName);
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
   public void openDrawerAlignment(){
       
        btnDashboard.setTooltip(null);
        btnItemManagement.setTooltip(null);
        btnOrder.setTooltip(null);
        btnClient.setTooltip(null);
        btnQA.setTooltip(null);
        btnRatings.setTooltip(null);
        
        btnDashboard.setContentDisplay(ContentDisplay.LEFT);
        btnItemManagement.setContentDisplay(ContentDisplay.LEFT);
        btnOrder.setContentDisplay(ContentDisplay.LEFT);
        btnClient.setContentDisplay(ContentDisplay.LEFT);
        btnQA.setContentDisplay(ContentDisplay.LEFT);
        btnRatings.setContentDisplay(ContentDisplay.LEFT);
        btnWayBill.setContentDisplay(ContentDisplay.LEFT);
        btnPickup.setContentDisplay(ContentDisplay.LEFT);
        btnItemManagement.setAlignment(Pos.BASELINE_LEFT);
        btnDashboard.setAlignment(Pos.BASELINE_LEFT);
        btnOrder.setAlignment(Pos.BASELINE_LEFT);
        btnClient.setAlignment(Pos.BASELINE_LEFT);
        btnQA.setAlignment(Pos.BASELINE_LEFT);
        btnDashboard.setAlignment(Pos.BASELINE_LEFT);
        btnRatings.setAlignment(Pos.BASELINE_LEFT);
        btnWayBill.setAlignment(Pos.BASELINE_LEFT);
        btnPickup.setAlignment(Pos.BASELINE_LEFT);
        nav_bar.setPrefWidth(190);
   }
   public void closeDrawerAlignment(){
        btnItemManagement.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnOrder.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnClient.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnQA.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnDashboard.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnRatings.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnWayBill.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnPickup.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnItemManagement.setAlignment(Pos.CENTER);
        btnOrder.setAlignment(Pos.CENTER);
        btnClient.setAlignment(Pos.CENTER);
        btnQA.setAlignment(Pos.CENTER);
        btnDashboard.setAlignment(Pos.CENTER);
        btnRatings.setAlignment(Pos.CENTER);
        btnWayBill.setAlignment(Pos.CENTER);
        btnPickup.setAlignment(Pos.CENTER);
        nav_bar.setPrefWidth(50);
        tooltip();
   }
   
   private void getTime(){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {            
        Calendar cal = Calendar.getInstance();
        int second = cal.get(Calendar.SECOND);        
        String temp = "" + second;
        
        Date date = new Date();
        String strTimeFormat = "hh:mm:";
        String strDateFormat = "MMMM dd, yyyy";
        String secondFormat = "ss";
        
        DateFormat timeFormat = new SimpleDateFormat(strTimeFormat + secondFormat);
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        
        String formattedTime= timeFormat.format(date);
        String formattedDate= dateFormat.format(date);
        
        DateAndTime.setText(formattedDate+ " || " + formattedTime);
        
        }),
         new KeyFrame(Duration.seconds(1))
        );
        
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
  public static void hackTooltipStartTiming(Tooltip tooltip) {
    try {
        Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
        fieldBehavior.setAccessible(true);
        Object objBehavior = fieldBehavior.get(tooltip);

        Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
        fieldTimer.setAccessible(true);
        Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

        objTimer.getKeyFrames().clear();
        objTimer.getKeyFrames().add(new KeyFrame(new Duration(100)));
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
