/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;

/**
 *
 * @author user
 */
public class OrderModel {
    private ImageView photo;
    private  final SimpleStringProperty orderIndex01;
    private  final SimpleStringProperty orderIndex03;
    private  final SimpleStringProperty orderIndex04;
    private  final SimpleStringProperty orderIndex05;
    public OrderModel(ImageView empPhoto, String OrderIndex01,String OrderIndex03,String OrderIndex04,String OrderIndex05)
    {
       this.photo = empPhoto;
       this.orderIndex03 = new SimpleStringProperty(OrderIndex01);
       this.orderIndex01 = new SimpleStringProperty(OrderIndex03);
       this.orderIndex04 =  new SimpleStringProperty(OrderIndex04);
       this.orderIndex05 =  new SimpleStringProperty(OrderIndex05);
    }

    public String getOrderIndex01() {
        return orderIndex01.get();
    }

    public String getOrderIndex03() {
        return orderIndex03.get();
    }

    public String getOrderIndex04() {
        return orderIndex04.get();
    }

    public String getOrderIndex05() {
        return orderIndex05.get();
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }
   
}
