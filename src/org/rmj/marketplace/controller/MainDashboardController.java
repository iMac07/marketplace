/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.rmj.marketplace.model.ScreenInterface;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agent.MsgBox;
import org.rmj.appdriver.agentfx.CommonUtils;
//import org.rmj.fund.manager.base.Incentive;
//import org.rmj.fund.manager.base.LMasDetTrans;
import org.rmj.marketplace.model.TableModel;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MainDashboardController implements Initializable, ScreenInterface {

    private GRider oApp;
//    private Incentive oTrans;
//    private LMasDetTrans oListener;
    
    private final boolean pbLoaded = false;
    private Label pieLabel;
    private PieChart.Data selectedData;
    private XYChart.Data selectedLineData;
    private Tooltip tooltip;
    @FXML
    private LineChart<String,Number> saleLineChart;
    @FXML
    private PieChart pieChart;
    @FXML
    private AnchorPane MainDashboardPane;
    @FXML
    private AnchorPane anchorLineChart;
    @FXML
    private Pane paneOrder;
    @FXML
    private Pane paneItem;
    @FXML
    private Pane paneClientInfo;
    
    private final ObservableList<TableModel> inc_data = FXCollections.observableArrayList();
    ObservableList<Series<String, Number>> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//          loadDetail();

//        oListener = new LMasDetTrans() {
//            @Override
//            public void MasterRetreive(int fnIndex, Object foValue) {
//            }
//            @Override
//            public void DetailRetreive(int fnRow, int fnIndex, Object foValue) {
//            }
//         };
//        oTrans  = new Incentive(oApp, oApp.getBranchCode(), false);
//        oTrans.setListener(oListener);
//        oTrans.setWithUI(true);
        
        loadLineChart();
        loadPieChart();
        paneOrder.setOnMouseClicked((MouseEvent e) -> {
            FXMLDocumentController.setNavButtonsSelected(2);
            loadAnimate("/org/rmj/marketplace/view/OrderProcessing.fxml");
        });
        paneItem.setOnMouseClicked((MouseEvent e) -> {
            FXMLDocumentController.setNavButtonsSelected(1);
            loadAnimate("/org/rmj/marketplace/view/ItemManagement.fxml");
        });
        paneClientInfo.setOnMouseClicked((MouseEvent e) -> {
            FXMLDocumentController.setNavButtonsSelected(5);
            loadAnimate("/org/rmj/marketplace/view/ClientInfo.fxml");
        });
}      
    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue;//To change body of generated methods, choose Tools | Templates.
    } 
//    private void loadDetail(){
//        try {
//            inc_data.clear();
////            XYChart.Series<String, Number> series = new XYChart.Series<>();
//            for (int lnCtr = 1; lnCtr <= oTrans.getItemCount(); lnCtr++){
//                inc_data.add(new TableModel(String.valueOf(lnCtr),
//                    oTrans.getDetail(lnCtr, "xEmployNm").toString(),
//                    oTrans.getDetail(lnCtr, "xEmpLevNm").toString(),
//                    oTrans.getDetail(lnCtr, "xPositnNm").toString(),
//                    oTrans.getDetail(lnCtr, "xSrvcYear").toString(),
//                    (CommonUtils.NumberFormat((Number)oTrans.getDetail(lnCtr, "nTotalAmt"), "#,##0.00"))) {});
//                
//                series.getData().add(new XYChart.Data<String, Number>(oTrans.getDetail(lnCtr, "xEmployNm").toString(),(Number)oTrans.getDetail(lnCtr, "nTotalAmt")));
                
//            }
//            series.setName("Employee Incentives");
//            saleLineChart.getData().add(series);
            
//        } catch (SQLException e) {
//            e.printStackTrace();
//            MsgBox.showOk(e.getMessage());
//        }
//    }
    private void loadLineChart(){
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.getData().add(new XYChart.Data<>("Apple (ios)", 30490));
            series.getData().add(new XYChart.Data<>("Huawei", 23999));
            series.getData().add(new XYChart.Data<>("OPPO", 26999));
            series.getData().add(new XYChart.Data<>("REALME", 16990));
            series.getData().add(new XYChart.Data<>("Samsung", 95990));
            series.getData().add(new XYChart.Data<>("Tecno", 4346));
            series.getData().add(new XYChart.Data<>("VIVO", 12999));
            series.getData().add(new XYChart.Data<>("XIAOMI", 4843));
            
            saleLineChart.getData().add(series);
            saleLineChart.setTitle("Mobile Sales");
            saleLineChart.setLegendVisible(false);
            series.getData().stream().forEachOrdered(entry -> {
//            Tooltip t = new Tooltip(entry.getYValue().toString());
//            Tooltip.install(entry.getNode(), t);
                applyLineMouseEvents(entry);
            });
            
    }
    private void loadPieChart(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
         new PieChart.Data("Apple (ios)", 30490), 
         new PieChart.Data("Huawei", 23999), 
         new PieChart.Data("OPPO", 26999), 
         new PieChart.Data("REALME", 16990), 
         new PieChart.Data("Samsung", 95990), 
         new PieChart.Data("Tecno", 4346), 
         new PieChart.Data("VIVO", 12999), 
         new PieChart.Data("XIAOMI", 4843)); 
        pieChart.setData(pieChartData);
        pieChart.setTitle("Mobile Sales");
        pieChart.setClockwise(true);
//        pieChart.setLabelLineLength(50);
//        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180); 
        pieChart.setLegendSide(Side.BOTTOM);
        pieChart.getData().stream()
        .forEach(this::applyPieMouseEvents);
       
    }
    public static void TooltipStartTiming(Tooltip tooltip) {
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
    private void applyPieMouseEvents(final PieChart.Data data) {
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 16 arial;");
        caption.setVisible(true);
        final Node node = data.getNode();
        node.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            node.setEffect(new Glow());
            String styleString = "-fx-border-color: white; -fx-border-width: 1;";
            node.setStyle(styleString);
            
            Tooltip t = new Tooltip(data.getName() + " : ₱" + CommonUtils.NumberFormat((Number) data.getPieValue(), "#,##0.00"));
            t.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 14");
            TooltipStartTiming(t);
            Tooltip.install(node, t);
            
        });
        node.setOnMouseExited((MouseEvent arg0) -> {
            node.setEffect(null);
            node.setStyle("");
        });

    }
    private void applyLineMouseEvents(final  XYChart.Data<String, Number> data) {
        final Node node = data.getNode();
        node.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            node.setEffect(new Glow());
//            String styleString = "-fx-background-color: #00cccc;";
            String styleString = "-fx-border-color: white; -fx-border-width: 1;-fx-border-radius: 15;";
            node.setStyle(styleString);
            Tooltip t = new Tooltip(data.getXValue() + " : ₱" + CommonUtils.NumberFormat((Number) data.getYValue(), "#,##0.00"));
            Point2D p = node.localToScene(0.0, 0.0);
            t.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 14");
            TooltipStartTiming(t);
            Tooltip.install(node, t);
        });
        node.setOnMouseExited((MouseEvent arg0) -> {
           node.setEffect(null);
           node.setStyle("");
       });
       
    }
    
    private void loadAnimate(String foName){
        StackPane myBox = (StackPane) MainDashboardPane.getParent();
        myBox.getChildren().clear();
        myBox.getChildren().add(getScene(foName));
        
    }
    private AnchorPane getScene(String fsFormName){
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
    private ScreenInterface getController(String fsValue){
        switch (fsValue){
            case "/org/rmj/marketplace/view/ItemManagement.fxml":
                return (ScreenInterface) new ItemManagementController();
            case "/org/rmj/marketplace/view/OrderProcessing.fxml":
                return (ScreenInterface) new OrderProcessingController();
            case "/org/rmj/marketplace/view/ClientInfo.fxml":
                return (ScreenInterface) new ClientInfoController();
            default:
                return null;
        }
    }
}
