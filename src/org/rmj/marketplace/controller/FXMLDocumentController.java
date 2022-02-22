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
    private static ToggleButton[] navButtons;
    private static Tooltip[] navTooltip;
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
    private ToggleButton btnReports;
    @FXML
    private FontAwesomeIconView drawer_icon;
    public static ToggleGroup drawer_button;
    private TranslateTransition openNav;
    private TranslateTransition closeNav;
    private TranslateTransition closeFastNav;
   
    public static void setNavButtonsSelected(int pnValue){
        navButtons[pnValue].setSelected(true);
    }
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
        setScene(loadAnimate("/org/rmj/marketplace/view/MainDashboard.fxml"));
        openNav = new TranslateTransition(Duration.millis(100), nav_bar);
        openNav.setToX(nav_bar.getTranslateX()-nav_bar.getWidth());
        closeNav = new TranslateTransition(Duration.millis(100), nav_bar);
        closeFastNav = new TranslateTransition(Duration.millis(.1), nav_bar);
        initToggleGroup();
        openDrawerAlignment();
    } 
    private void initToggleGroup(){
        drawer_button = new ToggleGroup();
        navButtons = new ToggleButton[]{
            btnDashboard,
            btnItemManagement,
            btnOrder,  
            btnWayBill,
            btnPickup,
            btnClient,
            btnQA,
            btnRatings,
            btnReports
        };
        for (ToggleButton navButton : navButtons) {
            navButton.setToggleGroup(drawer_button);
        }
        navButtons[0].setSelected(true);
    }
    
    private void tooltip (){
        navTooltip = new Tooltip[]{
            new Tooltip("DASHBOARD"),
            new Tooltip("ITEM MANAGEMENT"),
            new Tooltip("ORDER PROCESSING"),
            new Tooltip("WAY BILL"),
            new Tooltip("PICK UP"),
            new Tooltip("CLIENT INFO"),
            new Tooltip("QUESTION AND ANSWER"),
            new Tooltip("RATINGS AND REVIEWS"),
            new Tooltip("STANDARD REPORTS")
        };
        for(int t = 0; t < navTooltip.length; t++){
            hackTooltipStartTiming(navTooltip[t]);
            navButtons[t].setTooltip(navTooltip[t]);
        }
    }
    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue;
    }
    public void drawerOpen() {
        
       if ((nav_bar.getWidth()) == 50 ) {
            openDrawerAlignment();
            openNav.play(); 
        } else {
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
        setNavButtonsSelected(1);
        setScene(loadAnimate("/org/rmj/marketplace/view/ItemManagement.fxml"));
    }
    @FXML
    private void switchOrder(ActionEvent event) throws IOException {
        setNavButtonsSelected(2);
        setScene(loadAnimate("/org/rmj/marketplace/view/OrderProcessing.fxml"));
    }
    @FXML
    private void switchWayBill(ActionEvent event) throws IOException {
        setNavButtonsSelected(3);
        setScene(loadAnimate("/org/rmj/marketplace/view/WayBill.fxml"));
    }
    @FXML
    private void switchPickup(ActionEvent event) throws IOException {
        setNavButtonsSelected(4);
        setScene(loadAnimate("/org/rmj/marketplace/view/Pickup.fxml"));
    }
    @FXML
    private void switchClient(ActionEvent event) throws IOException {
        setNavButtonsSelected(5);
        setScene(loadAnimate("/org/rmj/marketplace/view/ClientInfo.fxml"));
      
    }
    @FXML
    private void switchQA(ActionEvent event) throws IOException {
        setNavButtonsSelected(6);
        setScene(loadAnimate("/org/rmj/marketplace/view/Faq.fxml"));
    }
    
    @FXML
    private void switchDashboard(ActionEvent event) throws IOException {
        setNavButtonsSelected(0);
        setScene(loadAnimate("/org/rmj/marketplace/view/MainDashboard.fxml"));
    }
    @FXML
    private void switchRatings(ActionEvent event) throws IOException {
        setNavButtonsSelected(7);
        setScene(loadAnimate("/org/rmj/marketplace/view/RatingsAndReviews.fxml"));
    }
    
    @FXML
    private void switchReports(ActionEvent event) throws IOException {
        setNavButtonsSelected(8);
        setScene(loadAnimate("/org/rmj/marketplace/view/Reports.fxml"));
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
             case "/org/rmj/marketplace/view/Reports.fxml":
                return (ScreenInterface) new ReportsController();
            
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
        for (ToggleButton navButton : navButtons) {
            navButton.setTooltip(null);
            navButton.setContentDisplay(ContentDisplay.LEFT);
            navButton.setAlignment(Pos.BASELINE_LEFT);
        }
        nav_bar.setPrefWidth(190);
   }
   public void closeDrawerAlignment(){
       for (ToggleButton navButton : navButtons) {
            navButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            navButton.setAlignment(Pos.CENTER);
        }
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
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
    }
}
