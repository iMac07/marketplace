/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.app.util;

import java.lang.reflect.Field;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import static org.rmj.marketplace.controller.FXMLDocumentController.hackTooltipStartTiming;

/**
 *
 * @author user
 */
public class ToggleButtonSelection {
    private static ToggleGroup group;
    private static ToggleButton[] navButtons;
    
    public void ToggleButton(ToggleButton[] foValues, ToggleGroup poValues){
        navButtons = foValues;
        group = poValues;
    }
    public void initToggle(){
        for (ToggleButton navButton : navButtons) {
            navButton.setToggleGroup(group);
            System.out.println(navButton.toString());
        }
    }
    public void setNavButtonsSelected(int pnValue){
        navButtons[pnValue].setSelected(true);
    }
    public void initTooltip(){
        
    }
    private void tooltip (){
//        swit
//        Tooltip dashtip = new Tooltip("DASHBOARD");
//        hackTooltipStartTiming(dashtip);
//        btnDashboard.setTooltip(dashtip);
//        
//        Tooltip itemmgmttip = new Tooltip("ITEM MANAGEMENT");
//        hackTooltipStartTiming(itemmgmttip);
//        btnItemManagement.setTooltip(itemmgmttip);
//        
//        Tooltip ordprostip = new Tooltip("ORDER PROCESSING");
//        hackTooltipStartTiming(ordprostip);
//        btnOrder.setTooltip(ordprostip);
//        
//        Tooltip billtip = new Tooltip("WAY BILL");
//        hackTooltipStartTiming(billtip);
//        btnWayBill.setTooltip(billtip);
//        
//        Tooltip picktip = new Tooltip("PICK UP");
//        hackTooltipStartTiming(picktip);
//        btnPickup.setTooltip(picktip);
//        
//        Tooltip cltinfotip = new Tooltip("CLIENT INFO");
//        hackTooltipStartTiming(cltinfotip);
//        btnClient.setTooltip(cltinfotip);
//        
//        Tooltip qnatip = new Tooltip("QUESTION AND ANSWER");
//        hackTooltipStartTiming(qnatip);
//        btnQA.setTooltip(qnatip);
//        
//        Tooltip qnarate = new Tooltip("RATINGS AND REVIEWS");
//        hackTooltipStartTiming(qnarate);
//        btnRatings.setTooltip(qnarate);
//        
//        
//        Tooltip reports = new Tooltip("STANDARD REPORTS");
//        hackTooltipStartTiming(reports);
//        btnReports.setTooltip(reports);
        
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
