    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
'########################################################################################
'#        ___          ___          ___           ___       ___                         #
'#       /\  \        /\  \        /\  \         /\  \     /\  \         ___            #
'#       \:\  \      /::\  \      /::\  \        \:\  \   /::\  \       /\  \           #
'#        \:\  \    /:/\:\  \    /:/\:\  \   ___ /::\__\ /:/\:\  \      \:\  \          #
'#        /::\  \  /::\~\:\  \  /::\~\:\  \ /\  /:/\/__//::\~\:\  \     /::\__\         #
'#       /:/\:\__\/:/\:\ \:\__\/:/\:\ \:\__\\:\/:/  /  /:/\:\ \:\__\ __/:/\/__/         #
'#      /:/  \/__/\:\~\:\ \/__/\:\~\:\ \/__/ \::/  /   \:\~\:\ \/__//\/:/  /            #
'#     /:/  /      \:\ \:\__\   \:\ \:\__\    \/__/     \:\ \:\__\  \::/__/             #
'#     \/__/        \:\ \/__/    \:\ \/__/               \:\ \/__/   \:\__\             #
'#                   \:\__\       \:\__\                  \:\__\      \/__/             #
'#                    \/__/        \/__/                   \/__/                        #
'#                                                                                      #
'#                                 DATE CREATED 06-18-2022                              #
'#                                 DATE LAST MODIFIED 07-06-2022                        #
'#                                                                                      #
'########################################################################################
'LAST UPDATE NOTE
'Add tabpane for verified and unverified account, also add  tabpane for additional customer info such
'valid id, customer photo, email and mobile
*/
package org.rmj.marketplace.controller;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import org.rmj.marketplace.model.ScreenInterface;
import java.net.URL;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyBooleanPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static junit.framework.Assert.fail;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agent.MsgBox;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.appdriver.agentfx.ShowMessageFX;
import org.rmj.appdriver.constants.EditMode;
import org.rmj.marketplace.base.ClientProfiling;
import org.rmj.marketplace.base.LResultTown;
import org.rmj.marketplace.base.LTransaction;
import org.rmj.marketplace.model.ClientEmailModel;
import org.rmj.marketplace.model.ClientInfoModel;
import org.rmj.marketplace.model.ClientMobileModel;
import org.rmj.marketplace.model.ClientPhotoModel;
import org.rmj.marketplace.model.UserProfileModel;
import org.rmj.marketplace.model.ValidIDModel;


/**
 * FXML Controller class
 *
 * @author user
 */
public class ClientInfoController implements Initializable, ScreenInterface {
    
    private GRider oApp;
    private int orderRow = 0;
    private int clienrRow = 0;
    private int dataSize;
    private final String pxeModuleName = "Client Information";
    private double xOffset = 0;
    private double yOffset = 0;

    private LResultTown  tListener;
    private LTransaction  oListener;
    private ClientProfiling oTrans; 
    private boolean pbLoaded = false;
    private int pagecounter;
    private int pnEditMode;
    private int pnRow = -1;
    private int pnRow1 = -1;
    private int oldPnRow = -1;
    private String genval,civilstatval,idbtn;
    private boolean state = false;
    private String category;
    private String oldUserID = "";
    private String userID = "";
    
    @FXML
    private AnchorPane AnchorClient,btnAnchorPane,CustDetAnchorPane;
    @FXML
    private Pagination pagination;
    @FXML
    private TableView tblClients;
    @FXML
    private TabPane tabCustomerDetail;
    @FXML
    private TextField txtField01,txtField02,txtField03,txtField04;

    @FXML
    private TableColumn clientIndex011;
    @FXML
    private TableColumn clientIndex021;
    @FXML
    private TableColumn clientIndex031;
    @FXML
    private TextField txtField01uVUP,txtField02uVUP,txtField03uVUP,txtField04uVUP,txtField05uVUP,
                        txtField06uVUP,txtField07uVUP,txtField08uVUP,txtField09uVUP,txtField10uVUP,
                            txtField11uVUP,txtField12uVUP,txtField13uVUP,txtField14uVUP,txtField15uVUP,
                            txtField16uVUP,txtField17uVUP,txtField18uVUP,txtField19uVUP;
    @FXML
    private Tab tabEmail,tabValidID,tabMobile,tabCustPhoto,tabUserProfile;
    @FXML
    private FontAwesomeIconView statusValidID,statusCustPhoto,statusUserProfile,
                                    statusEmail,statusMobile;
    @FXML
    private TextField txtSeeks10;
    @FXML
    private TextField txtSeeks11;
    @FXML
    private Button btnRefresh,btnID1,btnID2,btnEdit,btnSave,btnClose,btnVerify,btnPhoto;
    @FXML
    private ImageView ImgValidID01,ImgValidID02,btnPic;


    private static final int ROWS_PER_PAGE = 50;
      
    private final ObservableList<ClientInfoModel> client_data = FXCollections.observableArrayList();
    private FilteredList<ClientInfoModel> filteredData;


    private final ObservableList<ValidIDModel> clientValidID_data = FXCollections.observableArrayList();
    private final ObservableList<ClientPhotoModel> clientPhoto_data = FXCollections.observableArrayList();
    private final ObservableList<UserProfileModel> userprofile_data = FXCollections.observableArrayList();
    private final ObservableList<ClientEmailModel> clientEmail_data = FXCollections.observableArrayList();
    private final ObservableList<ClientMobileModel> clientMobile_data = FXCollections.observableArrayList();
    
       private Stage getStage(){
	return (Stage) txtField01.getScene().getWindow();
    }
    private void setCategory(String val){
        this.category = val;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       tListener = (int fnIndex, Object foValue) -> {
            System.out.println("Town Result "  + fnIndex + "-->" + foValue);
            switch(fnIndex){
                case 24:
                    txtField13uVUP.setText((String)foValue);
                    break;
                case 25:
                    txtField12uVUP.setText((String)foValue);
                    break;
                case 26:
                    txtField17uVUP.setText((String)foValue);
                    break;
                case 27:
                    txtField16uVUP.setText((String)foValue);
                    break;
            }
            
        };
        oListener = (int fnIndex, Object foValue) -> {
            System.out.println("Mater "  + fnIndex + "-->" + foValue);
            
        };
        
        oTrans = new ClientProfiling(oApp, oApp.getBranchCode(), false);
        oTrans.setListener(oListener);
        oTrans.setListener(tListener);
        oTrans.setWithUI(true);
//        txtSeeks10.setOnKeyPressed(this::txtField_KeyPressed);
//        txtSeeks11.setOnKeyPressed(this::txtField_KeyPressed);
//        a = client_data.get(pnRow).getClientIndex08();
        pbLoaded = true;
        loadClient();
        clear();
        
        tabCustomerDetail.setDisable(pbLoaded);
        pnEditMode = EditMode.UNKNOWN;
        initButton(pnEditMode);
        btnRefresh.setOnAction(this::cmdButton_Click); 
        btnID1.setOnAction(this::cmdButton_Click);
        btnID2.setOnAction(this::cmdButton_Click);
        btnEdit.setOnAction(this::cmdButton_Click); 
        btnSave.setOnAction(this::cmdButton_Click); 
        btnClose.setOnAction(this::cmdButton_Click); 
        btnVerify.setOnAction(this::cmdButton_Click); 
        btnPhoto.setOnAction(this::cmdButton_Click);
        txtField10uVUP.focusedProperty().addListener(txtField_Focus);
        txtField11uVUP.focusedProperty().addListener(txtField_Focus);
        txtField12uVUP.focusedProperty().addListener(txtField_Focus);
        txtField13uVUP.focusedProperty().addListener(txtField_Focus);
        txtField14uVUP.focusedProperty().addListener(txtField_Focus);
        txtField15uVUP.focusedProperty().addListener(txtField_Focus);
        txtField16uVUP.focusedProperty().addListener(txtField_Focus);
        txtField17uVUP.focusedProperty().addListener(txtField_Focus);

        txtField12uVUP.setOnKeyPressed(this::txtField_KeyPressed);//barangay1
        txtField13uVUP.setOnKeyPressed(this::txtField_KeyPressed);//town1
        txtField16uVUP.setOnKeyPressed(this::txtField_KeyPressed);//barangay2
        txtField17uVUP.setOnKeyPressed(this::txtField_KeyPressed);//town2
        btnPic.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("Tile pressed ");
                if(clientPhoto_data.isEmpty()){
                    System.out.println(pnRow);
                    ShowMessageFX.Warning(getStage(), "No Profile Picture Uploaded.", "Warning", null);
                }else {
                    openPhoto();
                }
                event.consume();
            }
       });
        pagination.setPageFactory(this::createPage); 
//      FXTEST.setGlyphName("CLOSE");//CHECK pag verified
   
        tabCustomerDetail.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
        @Override
        public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
              String lsTab = newTab.getId();
          
                 switch  (lsTab) {
                     case "tabValidID":
                          btnEdit.setVisible(true);
                          pnEditMode = EditMode.UNKNOWN;
                          initButton(pnEditMode);
                          loadValidID();
                          

                         
                    break;
                    case "tabCustPhoto":
                         btnEdit.setVisible(true);
                         pnEditMode = EditMode.UNKNOWN;
                         initButton(pnEditMode);
                         loadPhoto();
                         initPhoto();
                         
                    break;

                    case "tabUserProfile":
                         btnEdit.setVisible(true);
                         loadUserProfile(oldUserID);
                         pnEditMode = EditMode.UNKNOWN;
                         initButton(pnEditMode);
                         if (!userprofile_data.isEmpty()){
                         initUserProfile();

                         
                         }
                    break;

                    case "tabEmail":
                         
                         
                         loadEmail();
                         pnEditMode = EditMode.UNKNOWN;
                         initButton(pnEditMode);
                         btnEdit.setVisible(false);
                         if (!clientEmail_data.isEmpty()){
                         initClientEmail();

                         }
                    break;

                    case "tabMobile":
                         
                         
                         
                         loadMobile();
                         pnEditMode = EditMode.UNKNOWN;
                         initButton(pnEditMode);
                         btnEdit.setVisible(false);
                         if (!clientMobile_data.isEmpty()){
                         initClientMobile();

                         }
                    break;


             }
            
            }});                   

}
private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, client_data.size());
        
            tblClients.setItems(FXCollections.observableArrayList(client_data.subList(fromIndex, toIndex)));
            return tblClients;
}
private void txtField_KeyPressed(KeyEvent event){
        try {
            TextField txtField = (TextField)event.getSource();
            int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
            String lsValue = txtField.getText();
            switch (event.getCode()){
                case F3:
                    switch (lnIndex){
                        case 12:
                            if (!oTrans.SearchBarangay(1,lsValue, false)){
                               ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                            }
                            
                            break;
                        case 13:
                            if (!oTrans.SearchTown(1,lsValue, false)){
                               ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                            } 
                            break;
                        case 16:
                            if (!oTrans.SearchBarangay(2,lsValue, false)){
                               ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                            } 
                            break;
                        case 17:
                            if (!oTrans.SearchTown(2,lsValue, false)){
                               ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                            }       
                            break;
                        }
                        break;
                case ENTER:
                        switch (lnIndex){
                        case 10: /*Search*/
                            
                            break;
                         case 12:
                            if (!oTrans.SearchBarangay(1,lsValue, false)){
                               ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                            } 
                            
                            break;
                        case 13:
                            if (!oTrans.SearchTown(1,lsValue, false)){
                               ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                            } 
                            break;
                        case 16:
                            if (!oTrans.SearchBarangay(2,lsValue, false)){
                               ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                            } 
                            break;
                        case 17:
                            if (!oTrans.SearchTown(2,lsValue, false)){
                               ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                            }       
                            break;
                        }
                        
                        break;
                case DOWN:
                    CommonUtils.SetNextFocus(txtField);break;
                case UP:
                    CommonUtils.SetPreviousFocus(txtField);break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initButton(int fnValue){
         boolean lbShow = (fnValue == EditMode.ADDNEW || fnValue == EditMode.UPDATE);
                btnEdit.setVisible(!lbShow);
                btnAnchorPane.setVisible(lbShow);
                if (!lbShow){
                btnAnchorPane.setManaged(!lbShow);
                CustDetAnchorPane.setManaged(!lbShow);
                }else
                
                CustDetAnchorPane.setManaged(lbShow);
                btnAnchorPane.setManaged(lbShow);
                btnSave.setVisible(lbShow);
                btnClose.setVisible(lbShow);
                tblClients.setDisable(lbShow);
                
            
            if(tabCustomerDetail.getSelectionModel().isSelected(2)){
            txtField10uVUP.setEditable(lbShow);
            txtField11uVUP.setEditable(lbShow);
            txtField12uVUP.setEditable(lbShow);
            txtField13uVUP.setEditable(lbShow);
            txtField14uVUP.setEditable(lbShow);
            txtField15uVUP.setEditable(lbShow);
            txtField16uVUP.setEditable(lbShow);         
            txtField17uVUP.setEditable(lbShow);
}
            loadStatus();
    }
    private void loadClient(){
    int lnCtr;
        try {
            client_data.clear();
            if (oTrans.LoadList()){      
//                oTrans.displayMasFields();
                System.out.println("List count -->" + oTrans.getItemCount());
                for (lnCtr = 1; lnCtr <= oTrans.getItemCount(); lnCtr++){
                    client_data.add(new ClientInfoModel(String.valueOf(lnCtr),
                        (String) oTrans.getDetail(lnCtr, "sUserIDxx"),
                        (String) oTrans.getDetail(lnCtr, "sUserName"),
                        (String) oTrans.getDetail(lnCtr, "sEmailAdd"),
                        (String) oTrans.getDetail(lnCtr, "cIDVerify"),
                        (String) oTrans.getDetail(lnCtr, "cPcVerify"),//photo
                        (String) oTrans.getDetail(lnCtr, "cPrVerify"),//profile
                        (String) oTrans.getDetail(lnCtr, "cEmVerify"),
                        (String) oTrans.getDetail(lnCtr, "cMoVerify"),
                        (String) oTrans.getDetail(lnCtr, "sTownIDx1"),
                        (String) oTrans.getDetail(lnCtr, "sTownIDx2"),
                        (String) oTrans.getDetail(lnCtr, "sMobileNo")));
                    }
//                tblClients.getSelectionModel().select(clienrRow - 1);
            initClientsGrid1();
            loadTab();
            } else
               MsgBox.showOk(oTrans.getMessage());
        } catch (SQLException e) {
            fail(e.getMessage());
        }   
    }
    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue; //To change body of generated methods, choose Tools | Templates.
    }
    //waitlng ito
    private void loadUserProfile(String userID){
       int lnCtr;
        try {
            userprofile_data.clear();
            oldUserID= userID;
            System.out.println(oldUserID);
            System.out.println(userID);
                if(oTrans.OpenRecord(userID)){
                for (lnCtr = 1; lnCtr <= oTrans.getUserProfileItemCount(); lnCtr++){
                    userprofile_data.add(new UserProfileModel(String.valueOf(lnCtr),
                            (String) oTrans.getUserProfile("sUserIDxx"),
                            (String) oTrans.getUserProfile("sLastName"),
                            (String) oTrans.getUserProfile("sFrstName"),
                            (String) oTrans.getUserProfile("sMiddName"),
                            (String) oTrans.getUserProfile("sMaidenNm"),
                            (String) oTrans.getUserProfile("sSuffixNm"),
                            (String) oTrans.getUserProfile("cGenderCd"),
                            (String) oTrans.getUserProfile("cCvilStat"),
                            (String)oTrans.getUserProfile("dBirthDte"),//date ito
                            (String) oTrans.getUserProfile("sBirthPlc"),
                            (String) oTrans.getUserProfile("sHouseNo1"),
                            (String) oTrans.getUserProfile("sAddress1"),//street
                            (String) oTrans.getUserProfile("sBrgyIDx1"),
                            (String) oTrans.getUserProfile("sTownIDx1"),
                            (String) oTrans.getUserProfile("sHouseNo2"),
                            (String) oTrans.getUserProfile("sAddress2"),
                            (String) oTrans.getUserProfile("sBrgyIDx2"),
                            (String) oTrans.getUserProfile("sTownIDx2"),
                            (String) oTrans.getUserProfile("sBrgyNme1"),
                            (String) oTrans.getUserProfile("sTownNme1"),
                            (String) oTrans.getUserProfile("sBrgyNme1"),
                            (String) oTrans.getUserProfile("sBrgyNme2"),
                            (String) oTrans.getUserProfile("strBrhPlc"),
                            (String) oTrans.getUserProfile("sClientID")));
                            
                            
                }
                }

//            }  else{
//                MsgBox.showOk(oTrans.getMessage());
              
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException" + ex.getMessage());
        } catch (DateTimeException ex) {
//            MsgBox.showOk(ex.getMessage());
            System.out.println("DateTimeException" + ex.getMessage());
        }
    }
    private void loadEmail(){
       int lnCtr;
        try {
            clientEmail_data.clear();
                for (lnCtr = 1; lnCtr <= oTrans.getUserEmailItemCount(); lnCtr++){
                    clientEmail_data.add(new ClientEmailModel(String.valueOf(lnCtr),
                            (String) oTrans.getUserEmail("sUserIDxx"),
                            (String) oTrans.getUserEmail("dTransact"),//date
                            (String) oTrans.getUserEmail("sEmailAdd"),
                            (String) oTrans.getUserEmail("cVerified"),
                            (String)oTrans.getUserEmail("dVerified"),
                            (String) oTrans.getUserEmail("cRecdStat")));

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
    private void loadMobile(){
       int lnCtr;
        try {
            clientMobile_data.clear();

                for (lnCtr = 1; lnCtr <= oTrans.getUserMobileNoItemCount(); lnCtr++){
                    clientMobile_data.add(new ClientMobileModel(String.valueOf(lnCtr),
                            (String) oTrans.getUserMobileNo("sUserIDxx"),
                            (String) oTrans.getUserMobileNo("dTransact"),
                            (String) oTrans.getUserMobileNo("sMobileNo"),
                            (String) oTrans.getUserMobileNo("cVerified"),
                            (String)oTrans.getUserMobileNo("dVerified"),
                            (String) oTrans.getUserMobileNo("cRecdStat")));

                
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
    private void loadValidID(){

            try {
                clientValidID_data.clear();
                      for(int lnCtr = 1; lnCtr <= oTrans.getMasterIDItemCount();lnCtr++){
                        clientValidID_data.add(new ValidIDModel(String.valueOf(lnCtr),
                                (String) oTrans.getMasterID("sUserIDxx"),
                                (String) oTrans.getMasterID("dTransact"),
                                (String) oTrans.getMasterID("sIDCodex1"),
                                (String) oTrans.getMasterID("sIDNoxxx1"),
                                (String)oTrans.getMasterID("sIDFrntx1"),
                                (String) oTrans.getMasterID("sIDBackx1"),
                                (String) oTrans.getMasterID("dIDExpry1"),
                                (String)oTrans.getMasterID("sIDCodex2"),
                                (String) oTrans.getMasterID("sIDNoxxx2"),
                                (String) oTrans.getMasterID("sIDFrntx2"),
                                (String)oTrans.getMasterID("sIDBackx2"),
                                (String) oTrans.getMasterID("dIDExpry2"),
                                (String) oTrans.getMasterID("cVerified"),
                                (String) oTrans.getMasterID("sIDNamex1"),
                                (String) oTrans.getMasterID("sIDNamex2")));
                }
                      initClientValidID();
            } catch (SQLException ex) {
                System.out.println("SQLException" + ex.getMessage());
            } catch (NullPointerException ex) {
                System.out.println("NullPointerException" + ex.getMessage());
            } catch (DateTimeException ex) {
    //            MsgBox.showOk(ex.getMessage());
                System.out.println("DateTimeException" + ex.getMessage());
            }

    }
    private void loadPhoto(){
            try {
                clientPhoto_data.clear();
                      for(int lnCtr = 1; lnCtr <= oTrans.getUserPictureItemCount();lnCtr++){
                        clientPhoto_data.add(new ClientPhotoModel(String.valueOf(lnCtr),
                                (String) oTrans.getUserPicture("sUserIDxx"),
                                (String) oTrans.getUserPicture("sImageNme"),
                                (String) oTrans.getUserPicture("sImagePth"),
                                (String) oTrans.getUserPicture("")));
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
    private void clear(){
        txtField01.clear();
        txtField02.clear();
        txtField03.clear();
        txtField04.clear();
        txtField01uVUP.clear();
        txtField02uVUP.clear();
        txtField03uVUP.clear();
        txtField04uVUP.clear();
        txtField05uVUP.clear();
        txtField06uVUP.clear();
        txtField07uVUP.clear();
        txtField08uVUP.clear();
        txtField08uVUP.clear();
        txtField09uVUP.clear();
        txtField10uVUP.clear();
        txtField11uVUP.clear();
        txtField12uVUP.clear();
        txtField13uVUP.clear();
        txtField14uVUP.clear();
        txtField15uVUP.clear();
        txtField16uVUP.clear();
        txtField17uVUP.clear();
        txtField18uVUP.clear();
        txtField19uVUP.clear();
           statusUserProfile.setGlyphName("CLOSE");
           statusCustPhoto.setGlyphName("CLOSE");
           statusValidID.setGlyphName("CLOSE");
           statusEmail.setGlyphName("CLOSE");
           statusMobile.setGlyphName("CLOSE");
               statusUserProfile.setFill(RED);
               statusCustPhoto.setFill(RED);
               statusValidID.setFill(RED);
               statusEmail.setFill(RED);
               statusMobile.setFill(RED);
        }

    public void initClientsGrid1() { 
        clientIndex011.setStyle("-fx-alignment: CENTER-LEFT; -fx-padding: 0 0 0 5;");
        clientIndex021.setStyle("-fx-alignment: CENTER-LEFT; -fx-padding: 0 0 0 5;");
        clientIndex031.setStyle("-fx-alignment: CENTER-LEFT; -fx-padding: 0 0 0 5;");
        
        clientIndex011.setCellValueFactory(new PropertyValueFactory<>("clientIndex01"));
        clientIndex021.setCellValueFactory(new PropertyValueFactory<>("clientIndex04"));
        clientIndex031.setCellValueFactory(new PropertyValueFactory<>("clientIndex03"));
        tblClients.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) -> {
            TableHeaderRow header = (TableHeaderRow) tblClients.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                header.setReordering(false);
            });
        });
        filteredData = new FilteredList<>(client_data, b -> true);
       autoSearch(txtSeeks10);
       autoSearch(txtSeeks11);
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ClientInfoModel> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tblClients.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tblClients.setItems(sortedData);
                   if(oldPnRow >= 0){
           tblClients.getSelectionModel().select(oldPnRow);
            }
        }
    private void initUserProfile(){
        genval = (userprofile_data.get(0).getUserIndex08());
        civilstatval = (userprofile_data.get(0).getUserIndex09());
        txtField01uVUP.setText(userprofile_data.get(0).getUserIndex03());
        txtField02uVUP.setText(userprofile_data.get(0).getUserIndex04());
        txtField03uVUP.setText(userprofile_data.get(0).getUserIndex05());
        txtField04uVUP.setText(userprofile_data.get(0).getUserIndex06());
        txtField05uVUP.setText(userprofile_data.get(0).getUserIndex07());
            switch (genval) {
                        case "0":
                            txtField06uVUP.setText("Male");
                            break;
                        case "1":
                            txtField06uVUP.setText("Female");
                            break;
                        default:
                            txtField06uVUP.setText("LGBTQ++");
                            break;
                    }
//        txtField06uVUP.setText(userprofile_data.get(pagecounter).getUserIndex08());
            switch (civilstatval) {
                        case "0":
                            txtField07uVUP.setText("Single");
                            break;
                        case "1":
                            txtField07uVUP.setText("Married");
                            break;
                        case "2":
                            txtField07uVUP.setText("Seperated");
                            break;
                        case "3":
                            txtField07uVUP.setText("Widowed");
                            break;
                        case "4":
                            txtField07uVUP.setText("Single Parent");
                            break;
                        case "5":
                            txtField07uVUP.setText("Single Parent w/ Live-in Partner");
                            break;
                    }
//        txtField07uVUP.setText(userprofile_data.get(pagecounter).getUserIndex09());
        txtField08uVUP.setText(userprofile_data.get(0).getUserIndex10());
        txtField09uVUP.setText(userprofile_data.get(0).getUserIndex24());
        txtField10uVUP.setText(userprofile_data.get(0).getUserIndex12());
        txtField11uVUP.setText(userprofile_data.get(0).getUserIndex13());
        txtField12uVUP.setText(userprofile_data.get(0).getUserIndex20());
        txtField13uVUP.setText(userprofile_data.get(0).getUserIndex21());
        txtField14uVUP.setText(userprofile_data.get(0).getUserIndex16());
        txtField15uVUP.setText(userprofile_data.get(0).getUserIndex17());
        txtField16uVUP.setText(userprofile_data.get(0).getUserIndex22());         
        txtField17uVUP.setText(userprofile_data.get(0).getUserIndex23());
              
        }
    private void initClientEmail(){
        txtField18uVUP.setText(clientEmail_data.get(0).getEmailIndex04());

        

}
    private void initClientMobile(){
         txtField19uVUP.setText(clientMobile_data.get(0).getMobileIndex04());

        
}
    private void initClientValidID(){
        if (clientValidID_data.isEmpty()){
            ImgValidID01.setImage(new Image("/org/rmj/marketplace/images/id_default.png"));
            ImgValidID02.setImage(new Image("/org/rmj/marketplace/images/id_default.png"));
        }else {
            ImgValidID01.setImage(new Image(clientValidID_data.get(0).getValidIndex06()));
            ImgValidID02.setImage(new Image(clientValidID_data.get(0).getValidIndex11()));
        }
}
    private void initPhoto(){
        if (clientPhoto_data.isEmpty()){
            btnPic.setImage(new Image("/org/rmj/marketplace/images/user_default.png"));
        }else {
            btnPic.setImage(new Image(clientPhoto_data.get(0).getCltPhotoIndex04()));
        }
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
                    return (clients.getClientIndex01().toLowerCase().contains(lowerCaseFilter)); // Does not match.   
                }else {
                    return (clients.getClientIndex02().toLowerCase().contains(lowerCaseFilter)); // Does not match.
                }
            });
            changeTableView(0, ROWS_PER_PAGE);
        });
        if(lnIndex == 98){  
        }
        if(lnIndex == 99){  
        }
        loadTab();
} 
    private void loadTab(){
                int totalPage = (int) (Math.ceil(client_data.size() * 1.0 / ROWS_PER_PAGE));
                pagination.setPageCount(totalPage);
                pagination.setCurrentPageIndex(0);
                changeTableView(0, ROWS_PER_PAGE);
                pagination.currentPageIndexProperty().addListener(
                        (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));
            
    }   
    private void changeTableView(int index, int limit) {
        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, client_data.size());

            int minIndex = Math.min(toIndex, filteredData.size());
            SortedList<ClientInfoModel> sortedData = new SortedList<>(
                    FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
            sortedData.comparatorProperty().bind(tblClients.comparatorProperty());
            tblClients.setItems(sortedData); 
}
    @FXML
    private void tblClients_Clicked(MouseEvent event) {
//        try {
        pnRow = tblClients.getSelectionModel().getSelectedIndex(); 
        pagecounter = pnRow + pagination.getCurrentPageIndex() * ROWS_PER_PAGE;
           if (pagecounter >= 0){
             if(event.getClickCount() > 0){
                loadUserProfile(filteredData.get(pagecounter).getClientIndex02());
                
                tabCustomerDetail.setDisable(false);
                clear();
                        getSelectedItem();
                        tabCustomerDetail.getSelectionModel().select(tabValidID);
                        loadStatus();
                        loadValidID();
                        loadPhoto();
                        btnEdit.setDisable(false);

    tblClients.setOnKeyReleased((KeyEvent t)-> {
                KeyCode key = t.getCode();
                switch (key){
                    case DOWN:
                        pnRow = tblClients.getSelectionModel().getSelectedIndex();
                        pagecounter = pnRow + pagination.getCurrentPageIndex() * ROWS_PER_PAGE;
                        if (pagecounter == tblClients.getItems().size()) {
                            pagecounter = tblClients.getItems().size();
                            getSelectedItem();
                        }else {
//                            int y = 1;
//                            pnRow = pnRow + y;
                            getSelectedItem();
                        }
                        break;
                    case UP:
                        pnRow = tblClients.getSelectionModel().getSelectedIndex();
                        pagecounter = pnRow + pagination.getCurrentPageIndex() * ROWS_PER_PAGE;
                        getSelectedItem();
                        break;
                    default:
                        return; 
                }
            });
        }  
        }      
    }
    
    private void getSelectedItem(){
                txtField01.setText(client_data.get(pagecounter).getClientIndex03());
                txtField02.setText(client_data.get(0).getClientIndex10());
                txtField03.setText(client_data.get(0).getClientIndex10());
                txtField04.setText(client_data.get(0).getClientIndex10());
                pnEditMode = EditMode.UNKNOWN;
                initButton(pnEditMode);
                         oldPnRow = pagecounter;         
      
    } 
    private void loadStatus(){
        btnEdit.setDisable(false);
        if (client_data.get(pagecounter).getClientIndex05().equalsIgnoreCase("1")){
           statusValidID.setGlyphName("CHECK");
           statusValidID.setFill(GREEN);
           btnEdit.setDisable(true);
           btnSave.setDisable(true);
          }
        if (client_data.get(pagecounter).getClientIndex06().equalsIgnoreCase("1")){
           statusCustPhoto.setGlyphName("CHECK");
           statusCustPhoto.setFill(GREEN);
           btnEdit.setDisable(true);
           btnSave.setDisable(true);
          }       
        if (client_data.get(pagecounter).getClientIndex07().equalsIgnoreCase("1")){
           statusUserProfile.setGlyphName("CHECK");
           statusUserProfile.setFill(GREEN);
           btnEdit.setDisable(true);
          }       
        if (client_data.get(pagecounter).getClientIndex08().equalsIgnoreCase("1")){
           statusEmail.setGlyphName("CHECK");
           statusEmail.setFill(GREEN);
          }
        if (client_data.get(pagecounter).getClientIndex09().equalsIgnoreCase("1")){
           statusMobile.setGlyphName("CHECK");
           statusMobile.setFill(GREEN);
          }
}
    
    private void cmdButton_Click(ActionEvent event) {
        try {
            pagecounter = pnRow + pagination.getCurrentPageIndex() * ROWS_PER_PAGE;
            String lsButton = ((Button)event.getSource()).getId();
            switch (lsButton){
                case "btnRefresh":
                {
                    client_data.clear();
                }
                break;
                case "btnID1":
                    idbtn = "0";
                    if(clientValidID_data.isEmpty()){
                        System.out.println(pnRow);
                       ShowMessageFX.Warning(getStage(), "No Valid ID's Uploaded.", "Warning", null);
                    }else {
                        openValidID();
                    }
                    break;
                case "btnID2":
                    idbtn = "1";
                    if(clientValidID_data.isEmpty()){
                        System.out.println(pnRow);
                       ShowMessageFX.Warning(getStage(), "No Valid ID's Uploaded.", "Warning", null);
                    }else {
                        openValidID();
                    }
                    break;
                case "btnPhoto":
                    if(clientPhoto_data.isEmpty()){
                        System.out.println(pnRow);
                       ShowMessageFX.Warning(getStage(), "No Profile Picture Uploaded.", "Warning", null);
                    }else {
                        openPhoto();
                    }
                    break;
                case "btnEdit":
                        pnEditMode = oTrans.getEditMode();
                        if (oTrans.UpdateRecord()){
                            pnEditMode = oTrans.getEditMode(); 
                          } 
                        if(tabCustomerDetail.getSelectionModel().isSelected(2)){
                            txtField10uVUP.setEditable(true);
                            txtField11uVUP.setEditable(true);
                            txtField12uVUP.setEditable(true);
                            txtField13uVUP.setEditable(true);
                            txtField14uVUP.setEditable(true);
                            txtField15uVUP.setEditable(true);
                            txtField16uVUP.setEditable(true);         
                            txtField17uVUP.setEditable(true);
}
//                          else 
//                            ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                            break;     
                case "btnClose":
                            if (ShowMessageFX.OkayCancel(null, pxeModuleName, "Do you want to disregard changes?") == true){  
                                 pnEditMode = EditMode.READY ;
                            }
                        
                    break;
                case "btnSave":

                          if (tabCustomerDetail.getSelectionModel().isSelected(0)){
                            System.out.println("SavetabValidID");
                            
                          }else if(tabCustomerDetail.getSelectionModel().isSelected(1)){
                            System.out.println("SavetabCustPhoto");
                          }else if(tabCustomerDetail.getSelectionModel().isSelected(2)){
                            System.out.println("SavetabUserProfile");

                            System.out.println(oTrans.getEditMode());
                           if (oTrans.SaveUserProfile()){
                                ShowMessageFX.Warning(getStage(), "Transaction save successfully.", "Warning", null);
//                                 CommonUtils.closeStage(btnSave);

                  
                                }else {
                                 ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                        }
                    }   
                        loadClient();
                        loadUserProfile(oldUserID);
                        getSelectedItem();    
                          
                    break;
                case "btnVerify":

                          if (tabCustomerDetail.getSelectionModel().isSelected(0)){
                            if (oTrans.SaveMasterID()){
                                ShowMessageFX.Warning(getStage(), "User Valid ID's Succesfully Verified.", "Warning", null);
                                pnEditMode = EditMode.READY;
                            }else {
                                ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                            }
                          }else if(tabCustomerDetail.getSelectionModel().isSelected(1)){
                            System.out.println("VerifytabCustPhoto");
                            if (oTrans.SaveUserPicture()){
                                pnEditMode = EditMode.READY;
                                ShowMessageFX.Warning(getStage(), "Customer Picture Succesfully Verified.", "Warning", null);
                            }else {
                                ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                            }
                          }else if(tabCustomerDetail.getSelectionModel().isSelected(2)){
                            System.out.println("VerifytabUserProfile");
                            oTrans.setUserProfile("cVerified", "1");
                          if (oTrans.VerifyUserProfile()){
                                ShowMessageFX.Warning(getStage(), "User Profile Succesfully Verified.", "Warning", null);
                            }else {
                                 ShowMessageFX.Warning(getStage(), oTrans.getMessage(),"Warning", null);
                        }

                    }
                            oTrans = new ClientProfiling(oApp, oApp.getBranchCode(), false);
                            oTrans.setListener(oListener);
                            oTrans.setWithUI(true);
                            pbLoaded = true;
                             loadClient();
                             loadStatus();
                             pnRow1 = -1;
                             loadUserProfile(oldUserID);
                             tblClients.getSelectionModel().select(oldPnRow);
                             getSelectedItem();
                             pnEditMode = EditMode.READY;          
                    break;
            }
                            initButton(pnEditMode);
            } catch (SQLException ex) {
            Logger.getLogger(OrderPaymentTaggingController.class.getName()).log(Level.SEVERE, null, ex);
        
        }
                }
    
    private void openValidID() {
        try {
            Stage stage = new Stage();
            
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/org/rmj/marketplace/view/CustomerID.fxml"));

            CustomerIDController loControl = new CustomerIDController();
            loControl.setGRider(oApp);
            loControl.setIDDetailObject(oTrans);
            loControl.setbtnValue(idbtn);
            loControl.setData(clientValidID_data.get(0));
            
            fxmlLoader.setController(loControl);
            
            //load the main interface
            Parent parent = fxmlLoader.load();
                
            parent.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });
            
            //set the main interface as the scene
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("");
            stage.showAndWait();
            
//            loadOrders(filteredData.get(pnRow).getClientIndex08());
        } catch (IOException e) {
            e.printStackTrace();
            MsgBox.showOk(e.getMessage());
            System.exit(1);
        }
    }
    
   
    private void openPhoto() {
        try {
            Stage stage = new Stage();
            
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/org/rmj/marketplace/view/CustomerPhoto.fxml"));

            CustomerPhotoController loControl = new CustomerPhotoController();
            loControl.setGRider(oApp);
            loControl.setPhotoObject(oTrans);
            loControl.setData(clientPhoto_data.get(0));
            
            fxmlLoader.setController(loControl);
            
            //load the main interface
            Parent parent = fxmlLoader.load();
                
            parent.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });
            
            //set the main interface as the scene
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("");
            stage.showAndWait();
            
//            loadOrders(filteredData.get(pnRow).getClientIndex08());
        } catch (IOException e) {
            e.printStackTrace();
            MsgBox.showOk(e.getMessage());
            System.exit(1);
        }
    }
    final ChangeListener<? super Boolean> txtField_Focus = (o,ov,nv)->{
        try{
            if (!pbLoaded) return;

            TextField txtField = (TextField)((ReadOnlyBooleanPropertyBase)o).getBean();
            int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
            String lsValue = txtField.getText();

            if (lsValue == null) return;

            if(!nv){ /*Lost Focus*/
                switch (lnIndex){
    //                case 2:
    //                    if (lsValue.length() > 70) lsValue = lsValue.substring(0, 70); break;
                    case 10:
                      oTrans.setUserProfile("sHouseNo1", txtField10uVUP.getText());
                        if (lsValue.length() > 5){
                            ShowMessageFX.Warning(null, pxeModuleName, "Max length for 'House Number in Current/Billing Address' exceeds the maximum limit.");
                            txtField10uVUP.requestFocus();
                            return;
                        } break;
                    case 11:
                      oTrans.setUserProfile("sAddress1", txtField11uVUP.getText());
                        if (lsValue.length() > 128){
                            ShowMessageFX.Warning(null, pxeModuleName, "Max length for 'Address in Current/Billing Address");
                            txtField11uVUP.requestFocus();
                            return;
                        } break;
                    case 12:
                        if (lsValue.isEmpty()){
                           txtField12uVUP.requestFocus();
                            return;
                        } break;
                    case 13:
                     
                         if (lsValue.isEmpty()){
                            txtField13uVUP.requestFocus();
                            return;
                        } break;
                    case 14:
                      oTrans.setUserProfile("sHouseNo2", txtField14uVUP.getText());
                        if (lsValue.length() > 5){
                            ShowMessageFX.Warning(null, pxeModuleName, "Max length for 'House Number in Shipping Address' exceeds the maximum limit.");
                            txtField14uVUP.requestFocus();
                            return;
                        } break;
                    case 15:
                      oTrans.setUserProfile("sAddress2", txtField15uVUP.getText());
                        if (lsValue.length() > 128){
                            ShowMessageFX.Warning(null, pxeModuleName, "Max length for 'Address in Shipping Address' exceeds the maximum limit.");
                            txtField15uVUP.requestFocus();
                            return;
                        } break;
                    case 16:
                        
                        if (lsValue.isEmpty()){
                            txtField16uVUP.requestFocus();
                            return;
                        } break;
                    case 17:
                      
                        if (lsValue.isEmpty()){
                            txtField17uVUP.requestFocus();
                            return;
                        } break;            

                    default:
                        ShowMessageFX.Warning(null, pxeModuleName, "Text field with name " + txtField.getId() + " not registered.");
                        return;
                }
    //            
            } else
                txtField.selectAll();
            } catch (SQLException ex) {
            Logger.getLogger(OrderPaymentTaggingController.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        };


}