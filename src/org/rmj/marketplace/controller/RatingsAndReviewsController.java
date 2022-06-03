/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.controller;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import org.rmj.marketplace.model.ScreenInterface;
import java.net.URL;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agent.MsgBox;
import org.rmj.appdriver.constants.EditMode;
import org.rmj.marketplace.base.LTransaction;
import org.rmj.marketplace.base.ProductReviews;
import org.rmj.marketplace.bubble.BubbleSpec;
import org.rmj.marketplace.bubble.BubbledLabel;
import org.rmj.marketplace.model.RatingsReviewModel;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RatingsAndReviewsController implements Initializable, ScreenInterface {
    private String recdstat;
    private GRider oApp;
    private ProductReviews oTrans;
    private LTransaction  oListener;
    private boolean pbLoaded = false;
    private int pnRow = -1;
    private int pnEditMode;
    private final String style = "";
    private String category;
    
    @FXML
    private Button btnSend;
    @FXML
    private TextField txtField01;
    @FXML
    private TabPane tabPaneSelection;
    @FXML
    private Tab tabAcknowledge;
    @FXML
    private Tab tabUnAcknowledge;
    @FXML
    private TableView tblAcknowledge;
    @FXML
    private TableColumn acknowledgeIndex01;
    @FXML
    private TableColumn acknowledgeIndex02;
    @FXML
    private TableColumn acknowledgeIndex03;
    @FXML
    private TableColumn acknowledgeIndex04;
    @FXML
    private TableView tblUnAcknowledge;
    @FXML
    private TableColumn unAcknowledgeIndex01;
    @FXML
    private TableColumn unAcknowledgeIndex02;
    @FXML
    private TableColumn unAcknowledgeIndex03;
    @FXML
    private TableColumn unAcknowledgeIndex04;
    @FXML
    private ListView lvMessageBody;
    @FXML
    private Label lblCustomerName;
    @FXML
    private TextField txtSeeks10;
    @FXML
    private TextField txtSeeks11;
    @FXML
    private Pagination pagination;
    @FXML
    private Pagination pagination1;

    private static final int ROWS_PER_PAGE = 1;
    private FilteredList<RatingsReviewModel> filteredData;

    private final ObservableList<RatingsReviewModel> data_ratings = FXCollections.observableArrayList();
    
    private void setCategory(String val){
        this.category = val;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         oListener = new LTransaction() {
             @Override
             public void MasterRetreive(int fnIndex, Object foValue) {
                 System.out.println("fnIndex = " + fnIndex);
                 System.out.println("foValue = " + (String) foValue);
             }
         };
        oTrans = new ProductReviews(oApp, oApp.getBranchCode(), false);
        oTrans.setListener(oListener);
        oTrans.setTranStat(1);
        oTrans.setWithUI(true);
        pbLoaded = true;
        recdstat = "1";
        loadProducts();
        initGrid();
        initGrid1();
        pnEditMode = EditMode.UNKNOWN;
        pagination.setPageFactory(this::createPage); 
        btnSend.setOnAction(this::cmdButton_Click);
        
        
        tabPaneSelection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
        @Override
        public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
            if(newTab == tabAcknowledge) {
                oTrans.setTranStat(1);
                recdstat = "1";
                }
            else{
                 oTrans.setTranStat(0);
                 clear();
                 recdstat = "0";   
                }
            clear();
            loadProducts();
            }
        });
    }    
    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, data_ratings.size());
        if ("1".equals(recdstat) ) {
            tblAcknowledge.setItems(FXCollections.observableArrayList(data_ratings.subList(fromIndex, toIndex)));
            return tblAcknowledge;
        } else {
            tblUnAcknowledge.setItems(FXCollections.observableArrayList(data_ratings.subList(fromIndex, toIndex)));
            return tblUnAcknowledge;
        }
       
    }
    
    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    private void loadProducts(){
        int lnCtr;
        try {
            data_ratings.clear();
            if (oTrans.LoadList("", true)){//true if by barcode; false if by description
                for (lnCtr = 1; lnCtr <= oTrans.getItemCount(); lnCtr++){
                    data_ratings.add(new RatingsReviewModel(String.valueOf(lnCtr),
                            (String) oTrans.getDetail(lnCtr, "sListngID"),
                            oTrans.getDetail(lnCtr, "nEntryNox").toString(),
                            oTrans.getDetail(lnCtr, "nRatingxx").toString(),
                            oTrans.getDetail(lnCtr, "sRemarksx").toString(),
                            oTrans.getDetail(lnCtr, "sReplyxxx").toString(),
                            oTrans.getDetail(lnCtr, "nPriority").toString(),
                            oTrans.getDetail(lnCtr, "sCreatedx").toString(),
                            oTrans.getDetail(lnCtr, "dCreatedx").toString(),
                            oTrans.getDetail(lnCtr, "sRepliedx").toString(),
                            (String) oTrans.getDetail(lnCtr, "dRepliedx"),
                            (String) oTrans.getDetail(lnCtr, "cReadxxxx"),
                            (String) oTrans.getDetail(lnCtr, "dReadxxxx"),
                            (String) oTrans.getDetail(lnCtr, "sReadxxxx"),
                            (String) oTrans.getDetail(lnCtr, "cRecdStat"),
                            oTrans.getDetail(lnCtr, "dTimeStmp").toString(),
                            (String) oTrans.getDetail(lnCtr, "sImagesxx"),
                            (String) oTrans.getDetail(lnCtr, "sCompnyNm")));
                  System.out.println("sReadxxxx. --> " +  (String) oTrans.getDetail(lnCtr, 11));
                    
                    
                }
                if ("1".equals(recdstat) ) {
                        initGrid();
                    } else {
                        initGrid1();
                    }
                
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

    private void cmdButton_Click(ActionEvent event) {
        String lsButton = ((Button)event.getSource()).getId();
        try {
            switch (lsButton){
                case "btnSend": 
                    if(pnRow>=0){
                        oTrans.setMaster(5, txtField01.getText());
//                        oTrans.setMaster(9, oApp.getUserID());
//                        oTrans.setMaster(10, oApp.getServerDate());
                        oTrans.setMaster(14, 1);
                        if (oTrans.SaveTransaction()){
                            MsgBox.showOk("Transaction save successfully");
                            clear();
                            loadProducts();
                            loadDetail();
                        } else {
                           MsgBox.showOk(oTrans.getMessage());
                        }
                    }else{
                        MsgBox.showOk("No record selected.");
                    }
                        
                    break;
                case "tabAcknowledge":
                     oTrans.setTranStat(1);
                    MsgBox.showOk("1");
                    break;
                case "tabUnAcknowledge":
                     oTrans.setTranStat(0);
                    MsgBox.showOk("0");
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            MsgBox.showOk(e.getMessage());
        }
    } 
   private void clear(){
        lvMessageBody.getItems().clear();
        txtField01.clear();
        lblCustomerName.setText("Customer Name:");
   }
    private void initGrid() {
        
        acknowledgeIndex01.setStyle("-fx-alignment: CENTER;");
        acknowledgeIndex02.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        acknowledgeIndex03.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
        acknowledgeIndex04.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;");
       
        acknowledgeIndex01.setCellValueFactory(new PropertyValueFactory<>("acknowledgeIndex01"));
        acknowledgeIndex02.setCellValueFactory(new PropertyValueFactory<>("acknowledgeIndex18"));
        acknowledgeIndex03.setCellValueFactory(new PropertyValueFactory<>("acknowledgeIndex08"));
        acknowledgeIndex04.setCellValueFactory(new PropertyValueFactory<>("acknowledgeIndex05"));
      
        tblAcknowledge.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblAcknowledge.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
        filteredData = new FilteredList<>(data_ratings, b -> true);
        autoSearch(txtSeeks10);
        autoSearch(txtSeeks11);
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<RatingsReviewModel> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tblAcknowledge.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tblAcknowledge.setItems(sortedData);
    }
    
    private void initGrid1() {
        unAcknowledgeIndex01.setStyle("-fx-alignment: CENTER;" + style);
        unAcknowledgeIndex02.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;"+ style);
        unAcknowledgeIndex03.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;"+ style);
        unAcknowledgeIndex04.setStyle("-fx-alignment: CENTER-LEFT;-fx-padding: 0 0 0 5;"+ style);
        
        unAcknowledgeIndex01.setCellValueFactory(new PropertyValueFactory<>("acknowledgeIndex01"));
        unAcknowledgeIndex02.setCellValueFactory(new PropertyValueFactory<>("acknowledgeIndex18"));
        unAcknowledgeIndex03.setCellValueFactory(new PropertyValueFactory<>("acknowledgeIndex08"));
        unAcknowledgeIndex04.setCellValueFactory(new PropertyValueFactory<>("acknowledgeIndex05"));
      
        tblUnAcknowledge.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblUnAcknowledge.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
        filteredData = new FilteredList<>(data_ratings, b -> true);
        autoSearch(txtSeeks10);
        autoSearch(txtSeeks11);
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<RatingsReviewModel> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tblUnAcknowledge.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tblUnAcknowledge.setItems(sortedData);
  
    }
    
     private void autoSearch(TextField txtField){
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        boolean fsCode = true;
        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(clients-> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if(lnIndex == 10){
                    return (clients.getAcknowledgeIndex02().toLowerCase().contains(lowerCaseFilter)); // Does not match.   
                }else {
                    return (clients.getAcknowledgeIndex18().toLowerCase().contains(lowerCaseFilter)); // Does not match.
                }
            });
            changeTableView(0, ROWS_PER_PAGE);
        });
        if(lnIndex == 98){  
        }
        if(lnIndex == 99){  
        }
        if ("1".equals(recdstat) ) {
            int totalPage = (int) (Math.ceil(data_ratings.size() * 1.0 / ROWS_PER_PAGE));
            pagination.setPageCount(totalPage);
            pagination.setCurrentPageIndex(0);
            changeTableView(0, ROWS_PER_PAGE);
            pagination.currentPageIndexProperty().addListener(
                    (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));
        } else {
           int totalPage = (int) (Math.ceil(data_ratings.size() * 1.0 / ROWS_PER_PAGE));
            pagination1.setPageCount(totalPage);
            pagination1.setCurrentPageIndex(0);
            changeTableView(0, ROWS_PER_PAGE);
            pagination1.currentPageIndexProperty().addListener(
                    (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));
        }
       
    } 
    
    private void changeTableView(int index, int limit) {
        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, data_ratings.size());
        
        if ("1".equals(recdstat) ) {
            int minIndex = Math.min(toIndex, filteredData.size());
            SortedList<RatingsReviewModel> sortedData = new SortedList<>(
                    FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
            sortedData.comparatorProperty().bind(tblAcknowledge.comparatorProperty());
            tblAcknowledge.setItems(sortedData);
        } else {
             int minIndex = Math.min(toIndex, filteredData.size());
            SortedList<RatingsReviewModel> sortedData = new SortedList<>(
                    FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
            sortedData.comparatorProperty().bind(tblUnAcknowledge.comparatorProperty());
            tblUnAcknowledge.setItems(sortedData);
        }
        
    }
    @FXML
    private void tblUnAcknowledge_Clicked(MouseEvent event) {
        
        if(event.getClickCount()<=1){
            if(!tblUnAcknowledge.getItems().isEmpty()){
            pnRow = tblUnAcknowledge.getSelectionModel().getSelectedIndex();
            try {
                if (oTrans.OpenTransaction(data_ratings.get(pnRow).getAcknowledgeIndex02(),data_ratings.get(pnRow).getAcknowledgeIndex03())){
                    pnEditMode = oTrans.getEditMode();
                    if (oTrans.UpdateTransaction()){
                        pnEditMode = oTrans.getEditMode();
                        
                        if(oTrans.ReadReview()){
                            loadProducts();
                        }
                        loadDetail();
                    } else {
                        MsgBox.showOk(oTrans.getMessage());
                    }
                } else {
                    MsgBox.showOk(oTrans.getMessage());
                }
                tblUnAcknowledge.setOnKeyReleased((KeyEvent t)-> {
                     
                    try {
                        KeyCode key = t.getCode();
                        switch (key){
                            case DOWN:
                                pnRow = tblUnAcknowledge.getSelectionModel().getSelectedIndex();

                               
                                if (pnRow == tblUnAcknowledge.getItems().size()) {
                                    pnRow = tblUnAcknowledge.getItems().size();
                                    if (oTrans.OpenTransaction(data_ratings.get(pnRow).getAcknowledgeIndex02(),data_ratings.get(pnRow).getAcknowledgeIndex03())){
                                        if (oTrans.UpdateTransaction()){
                                            pnEditMode = oTrans.getEditMode();
                                            if(oTrans.ReadReview()){
                                                loadProducts();
                                            }
                                            
                                            loadDetail();
                                        } else {
                                            MsgBox.showOk(oTrans.getMessage());
                                        }
                                    } else {
                                        MsgBox.showOk(oTrans.getMessage());
                                    }

                                }else {
                                    if (oTrans.OpenTransaction(data_ratings.get(pnRow).getAcknowledgeIndex02(),data_ratings.get(pnRow).getAcknowledgeIndex03())){
                                        if (oTrans.UpdateTransaction()){
                                            pnEditMode = oTrans.getEditMode();
                        
                                            if(oTrans.ReadReview()){
                                                loadProducts();
                                            }
                                           
                                            loadDetail();
                                        } else {
                                            MsgBox.showOk(oTrans.getMessage());
                                        }
                                    } else {
                                        MsgBox.showOk(oTrans.getMessage());
                                    }
                                }
                                break;
                            case UP:
                                int pnRows = 0;
                                int x = 1;
                                pnRows = tblUnAcknowledge.getSelectionModel().getSelectedIndex();
                                pnRow = pnRows;
                               
                                if (oTrans.OpenTransaction(data_ratings.get(pnRow).getAcknowledgeIndex02(),data_ratings.get(pnRow).getAcknowledgeIndex03())){
                                    if (oTrans.UpdateTransaction()){
                                        pnEditMode = oTrans.getEditMode();
                        
                                        if(oTrans.ReadReview()){
                                            loadProducts();
                                        }

                                        loadDetail();
                                    } else {
                                        MsgBox.showOk(oTrans.getMessage());
                                    }
                                } else {
                                    MsgBox.showOk(oTrans.getMessage());
                                }
                                break;
                            default:
                                break;
                        }
                    } catch (SQLException ex) {
                       Logger.getLogger(RatingsAndReviewsController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }); 
                } catch (SQLException ex) {
                   Logger.getLogger(RatingsAndReviewsController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        }
        
        
    }
    @FXML
    private void tblAcknowledge_Clicked(MouseEvent event) {
        
        if(event.getClickCount()<=1){
            if(!tblAcknowledge.getItems().isEmpty()){
            pnRow = tblAcknowledge.getSelectionModel().getSelectedIndex();
            try {
              
                if (oTrans.OpenTransaction(data_ratings.get(pnRow).getAcknowledgeIndex02(),data_ratings.get(pnRow).getAcknowledgeIndex03())){
                    pnEditMode = oTrans.getEditMode();
                    if (oTrans.UpdateTransaction()){
                        pnEditMode = oTrans.getEditMode();
                        
                        if(oTrans.ReadReview()){
                            loadProducts();
                        }
                        loadDetail();
                    } else {
                        MsgBox.showOk(oTrans.getMessage());
                    }
                } else {
                    MsgBox.showOk(oTrans.getMessage());
                }
                tblAcknowledge.setOnKeyReleased((KeyEvent t)-> {
                     
                    try {
                        KeyCode key = t.getCode();
                        switch (key){
                            case DOWN:
                                pnRow = tblAcknowledge.getSelectionModel().getSelectedIndex();

                               
                                if (pnRow == tblAcknowledge.getItems().size()) {
                                    pnRow = tblAcknowledge.getItems().size();
                                    if (oTrans.OpenTransaction(data_ratings.get(pnRow).getAcknowledgeIndex02(),data_ratings.get(pnRow).getAcknowledgeIndex03())){
                                        if (oTrans.UpdateTransaction()){
                                            pnEditMode = oTrans.getEditMode();
                                            if(oTrans.ReadReview()){
                                                loadProducts();
                                            }
                                            
                                            loadDetail();
                                        } else {
                                            MsgBox.showOk(oTrans.getMessage());
                                        }
                                    } else {
                                        MsgBox.showOk(oTrans.getMessage());
                                    }

                                }else {
                                    if (oTrans.OpenTransaction(data_ratings.get(pnRow).getAcknowledgeIndex02(),data_ratings.get(pnRow).getAcknowledgeIndex03())){
                                        if (oTrans.UpdateTransaction()){
                                            pnEditMode = oTrans.getEditMode();
                        
                                            if(oTrans.ReadReview()){
                                                loadProducts();
                                            }
                                           
                                            loadDetail();
                                        } else {
                                            MsgBox.showOk(oTrans.getMessage());
                                        }
                                    } else {
                                        MsgBox.showOk(oTrans.getMessage());
                                    }
                                }
                                break;
                            case UP:
                                int pnRows = 0;
                                int x = 1;
                                pnRows = tblAcknowledge.getSelectionModel().getSelectedIndex();
                                pnRow = pnRows;
                               
                                if (oTrans.OpenTransaction(data_ratings.get(pnRow).getAcknowledgeIndex02(),data_ratings.get(pnRow).getAcknowledgeIndex03())){
                                    if (oTrans.UpdateTransaction()){
                                        pnEditMode = oTrans.getEditMode();
                        
                                        if(oTrans.ReadReview()){
                                            loadProducts();
                                        }

                                        loadDetail();
                                    } else {
                                        MsgBox.showOk(oTrans.getMessage());
                                    }
                                } else {
                                    MsgBox.showOk(oTrans.getMessage());
                                }
                                break;
                            default:
                                break;
                        }
                    } catch (SQLException ex) {
                       Logger.getLogger(RatingsAndReviewsController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }); 
                } catch (SQLException ex) {
                   Logger.getLogger(RatingsAndReviewsController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        }
        
        
    }
    public void loadDetail(){
//        taMessages.setText(data.get(pnRow).getAcknowledgeIndex05()); 
        lblCustomerName.setText(data_ratings.get(pnRow).getAcknowledgeIndex18());
        
        lvMessageBody.getItems().clear();
        pnEditMode = EditMode.UPDATE;
        addToChat();
        if(!data_ratings.get(pnRow).getAcknowledgeIndex06().trim().isEmpty()){
           addToReply();
        }
        
        boolean isReply = (Integer.parseInt(data_ratings.get(pnRow).getAcknowledgeIndex15())>0);

        txtField01.setDisable(isReply);
        btnSend.setDisable(isReply);
        
        
    }
    public synchronized void addToChat() {
        
        BubbledLabel bl6 = new BubbledLabel();
        bl6.setText(data_ratings.get(pnRow).getAcknowledgeIndex05());
        bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY,null, null)));
        HBox x = new HBox();
        bl6.setBubbleSpec(BubbleSpec.FACE_LEFT_CENTER);
        x.getChildren().add(bl6);
        x.setStyle("-fx-opacity:1.0");
        lvMessageBody.getItems().add(x);
//        Task<HBox> othersMessages = new Task<HBox>() {
//            @Override
//            public HBox call() throws Exception {
//                
//                BubbledLabel bl6 = new BubbledLabel();
//                bl6.setText(data.get(pnRow).getAcknowledgeIndex05());
//                bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY,null, null)));
//                HBox x = new HBox();
//                bl6.setBubbleSpec(BubbleSpec.FACE_LEFT_CENTER);
//                x.getChildren().add(bl6);
//                x.setStyle("-fx-opacity:1.0");
//                
//                return x;
//            }
//        };
//
//        othersMessages.setOnSucceeded(event -> {
//            lvMessageBody.getItems().add(othersMessages.getValue());
//        });
//
//        Thread t = new Thread(othersMessages);
//        t.setDaemon(true);
//        t.start();
    }
    public synchronized void addToReply() {
//        Task<HBox> yourMessages = new Task<HBox>() {
//            @Override
//            public HBox call() throws Exception {
//               
//                BubbledLabel bl6 = new BubbledLabel();
//                bl6.setText(data.get(pnRow).getAcknowledgeIndex06());
//                
//                bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,
//                        null, null)));
//                HBox x = new HBox();
//                x.setMaxWidth(lvMessageBody.getWidth() - 30);
//                x.setAlignment(Pos.TOP_RIGHT);
//                x.setStyle("-fx-opacity:1.0");
//                bl6.setBubbleSpec(BubbleSpec.FACE_RIGHT_CENTER);
//                x.getChildren().add(bl6);
//
//                return x;
//            }
//        };
//        yourMessages.setOnSucceeded(event -> lvMessageBody.getItems().add(yourMessages.getValue()));
//
//        Thread t = new Thread(yourMessages);
//        t.setDaemon(true);
//        t.start();
//        
        BubbledLabel bl6 = new BubbledLabel();
        bl6.setText(data_ratings.get(pnRow).getAcknowledgeIndex06());

        bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,
                null, null)));
        HBox x = new HBox();
        x.setMaxWidth(lvMessageBody.getWidth() - 30);
        x.setAlignment(Pos.TOP_RIGHT);
        x.setStyle("-fx-opacity:1.0");
        bl6.setBubbleSpec(BubbleSpec.FACE_RIGHT_CENTER);
        x.getChildren().add(bl6);
        lvMessageBody.getItems().add(x);
    }
   
    
}
