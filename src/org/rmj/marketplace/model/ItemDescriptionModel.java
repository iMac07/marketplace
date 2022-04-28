/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.rmj.marketplace.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author User
 */
public class ItemDescriptionModel {
    
    private final SimpleStringProperty detailIndex01;
    private final SimpleStringProperty detailIndex02; 
    private final SimpleBooleanProperty detailIndex03; 
    public ItemDescriptionModel(String index,String Descript,
               boolean bemphasis){
        this.detailIndex01 = new SimpleStringProperty(index);
        this.detailIndex02 = new SimpleStringProperty(Descript);
        this.detailIndex03 = new SimpleBooleanProperty(bemphasis);
    } 
    
    public String getDetailIndex01() {
        return detailIndex01.get();
    }

    public String getDetailIndex02() {
        return detailIndex02.get();
    }

    public boolean getDetailIndex03() {
        return detailIndex03.get();
    }
}
