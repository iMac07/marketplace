
package org.rmj.marketplace.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.util.Callback;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agent.MsgBox;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.marketplace.model.ScreenInterface;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ListingDateTimeController implements Initializable, ScreenInterface {  
    private final String pxeModuleName = "ListingDateTimeController";
    
    private GRider oApp;
    private String listingDate;
    private String listingTime;
    public static String title;
    @FXML
    private Label lblListingTitle;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private JFXDatePicker dpListingDate;
    @FXML
    private JFXTimePicker tpListingTime;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSave.setOnAction(this::cmdButton_Click);
        btnCancel.setOnAction(this::cmdButton_Click);
        getDateTimeValue();
       
    }    
    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue;
    }
    public void setTitle(String val){
        title = val;
    }
    private void getDateTimeValue(){
        try{
            String str = ""; 
            String start = "";
            if(title.equalsIgnoreCase("Start")){
                str =  ItemManagementController.getListingStart();
            }else {
                str =  ItemManagementController.getListingEnd();
            }
            if(!str.isEmpty()){
                String[] arrOfStr = str.split(" ", 2);
              
               
                
                dpListingDate.setValue(strToDate(arrOfStr[0]));
                tpListingTime.setValue(strToTime(arrOfStr[1]));
                lblListingTitle.setText("Listing " +title);
                
            }
             dpListingDate.setDayCellFactory(getDayCellFactory());
                 
           
        }catch(NullPointerException e){}
    }
    private LocalDate strToDate(String val){
         DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
          LocalDate localDate = LocalDate.parse(val, date_formatter);
          return localDate;
    }
    private LocalTime strToTime(String val){
        LocalTime localTime = LocalTime.parse(val);
        return localTime;
    }
    private void cmdButton_Click(ActionEvent event) {
        String lsButton = ((Button)event.getSource()).getId();
        switch (lsButton){
            case "btnCancel":
                CommonUtils.closeStage(btnCancel);
                break;
            case "btnSave": 
                if(dpListingDate.getValue() == null||
                        tpListingTime.getValue() == null){
                    MsgBox.showOk("Please fill out all the information (all fields are required)!!!");
                }else{
                    if(title.equalsIgnoreCase("Start")){
                        ItemManagementController.setListingStart(dpListingDate.getValue().toString() + " " +tpListingTime.getValue().toString() + ":00");
                    }else {
                        ItemManagementController.setListingEnd(dpListingDate.getValue().toString() + " " +tpListingTime.getValue().toString() + ":00");

                    }
                    CommonUtils.closeStage(btnSave);
                }
                break;
        }
      
    } 
    private Callback<DatePicker, DateCell> getDayCellFactory() {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        // Disable Monday, Tueday, Wednesday.
//                        if (item.getDayOfWeek() == DayOfWeek.MONDAY //
//                                || item.getDayOfWeek() == DayOfWeek.TUESDAY //
//                                || item.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
//                            setDisable(true);
//                            setStyle("-fx-background-color: #ffc0cb;");
//                        }
                        DayOfWeek day = DayOfWeek.from(item);
//                        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) 
//                        {
//                            this.setTextFill(Color.BLUE);
//                        }
                         
                        // Disable all future date cells
                        if (item.isBefore(LocalDate.now())) 
                        {
                            this.setDisable(true);
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }
}
