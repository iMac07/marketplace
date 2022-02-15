/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

/**
 *
 * @author user
 */
public class OrderModel {
    private ImageView photo;
    private  final SimpleStringProperty orderIndex01;
    private  final SimpleStringProperty orderIndex02;
    private  final SimpleStringProperty orderIndex03;
    private  final SimpleStringProperty orderIndex04;
    private  final SimpleStringProperty orderIndex05;
    private  final SimpleStringProperty orderIndex06;
    private  final SimpleStringProperty orderIndex07;
//    public OrderModel(ImageView empPhoto, String OrderIndex01,String OrderIndex03,String OrderIndex04,String OrderIndex05)
//    {
//       this.photo = empPhoto;
//       this.orderIndex03 = new SimpleStringProperty(OrderIndex01);
//       this.orderIndex01 = new SimpleStringProperty(OrderIndex03);
//       this.orderIndex04 =  new SimpleStringProperty(OrderIndex04);
//       this.orderIndex05 =  new SimpleStringProperty(OrderIndex05);
//    }
    public OrderModel(String OrderIndex01, String OrderIndex02,String OrderIndex03,
            String OrderIndex04,String OrderIndex05,
            String OrderIndex06,String OrderIndex07)
    {
       this.orderIndex01 = new SimpleStringProperty(OrderIndex01);
       this.orderIndex02 = new SimpleStringProperty(OrderIndex02);
       this.orderIndex03 = new SimpleStringProperty(OrderIndex03);
       this.orderIndex04 =  new SimpleStringProperty(OrderIndex04);
       this.orderIndex05 =  new SimpleStringProperty(OrderIndex05);
       this.orderIndex06 =  new SimpleStringProperty(OrderIndex06);
       this.orderIndex07 =  new SimpleStringProperty(OrderIndex07);
    }

    public String getOrderIndex01() {
        return orderIndex01.get();
    }
    public String getOrderIndex02() {
        return orderIndex02.get();
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

    public String getOrderIndex06() {
        return orderIndex06.get();
    }

    public String getOrderIndex07() {
        return orderIndex07.get();
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }
   
}
