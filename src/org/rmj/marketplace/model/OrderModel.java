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
    private SimpleStringProperty orderIndex01;   
    private SimpleStringProperty orderIndex02;  
    private SimpleStringProperty orderIndex03;  
    private SimpleStringProperty orderIndex04;    
    private SimpleStringProperty orderIndex05;   
    private SimpleStringProperty orderIndex06;  
    private SimpleStringProperty orderIndex07;  
    private SimpleStringProperty orderIndex08;    
    private SimpleStringProperty orderIndex09;   
    private SimpleStringProperty orderIndex10;  
    private SimpleStringProperty orderIndex11;  
    private SimpleStringProperty orderIndex12;  
    private SimpleStringProperty orderIndex13;   
    private SimpleStringProperty orderIndex14;  
    private SimpleStringProperty orderIndex15; 
    private SimpleStringProperty orderIndex16;    
    private SimpleStringProperty orderIndex17;    
    private SimpleStringProperty orderIndex18;    

//    Report CONSTRUCTOR
    public OrderModel(String orderIndex01, String orderIndex02, String orderIndex03, 
               String orderIndex04, String orderIndex05, String orderIndex06){
        this.orderIndex01 = new SimpleStringProperty(orderIndex01);
        this.orderIndex02 = new SimpleStringProperty(orderIndex02);
        this.orderIndex03 = new SimpleStringProperty(orderIndex03);
        this.orderIndex04 = new SimpleStringProperty(orderIndex04);
        this.orderIndex05 = new SimpleStringProperty(orderIndex05);
        this.orderIndex06 = new SimpleStringProperty(orderIndex06);
    }
    
    public OrderModel(String orderIndex01, String orderIndex02, String orderIndex03, 
               String orderIndex04){
        this.orderIndex01 = new SimpleStringProperty(orderIndex01);
        this.orderIndex02 = new SimpleStringProperty(orderIndex02);
        this.orderIndex03 = new SimpleStringProperty(orderIndex03);
        this.orderIndex04 = new SimpleStringProperty(orderIndex04);
    }
//   ORDER PROCESSING CONSTRUCTOR
    public OrderModel(String orderIndex01, String orderIndex02, String orderIndex03, 
            String orderIndex04,String orderIndex05,String orderIndex06,
            String orderIndex07,String orderIndex08,String orderIndex09,
            String orderIndex10,String orderIndex11,String orderIndex12,
            String orderIndex13,String orderIndex14,String orderIndex15,
            String orderIndex16){
        this.orderIndex01 = new SimpleStringProperty(orderIndex01);
        this.orderIndex02 = new SimpleStringProperty(orderIndex02);
        this.orderIndex03 = new SimpleStringProperty(orderIndex03);
        this.orderIndex04 = new SimpleStringProperty(orderIndex04);
        this.orderIndex05 = new SimpleStringProperty(orderIndex05);
        this.orderIndex06 = new SimpleStringProperty(orderIndex06);
        this.orderIndex07 = new SimpleStringProperty(orderIndex07);
        this.orderIndex08 = new SimpleStringProperty(orderIndex08);
        this.orderIndex09 = new SimpleStringProperty(orderIndex09);
        this.orderIndex10 = new SimpleStringProperty(orderIndex10);
        this.orderIndex11 = new SimpleStringProperty(orderIndex11);
        this.orderIndex12 = new SimpleStringProperty(orderIndex12);
        this.orderIndex13 = new SimpleStringProperty(orderIndex13);
        this.orderIndex14 = new SimpleStringProperty(orderIndex14);
        this.orderIndex15 = new SimpleStringProperty(orderIndex15);
        this.orderIndex16 = new SimpleStringProperty(orderIndex16);
    }
//   WAY BILL CONSTRUCTOR
    public OrderModel(String orderIndex01, String orderIndex02, String orderIndex03, 
            String orderIndex04,String orderIndex05,String orderIndex06,
            String orderIndex07,String orderIndex08,String orderIndex09,
            String orderIndex10,String orderIndex11,String orderIndex12,
            String orderIndex13,String orderIndex14,String orderIndex15,
            String orderIndex16,String orderIndex17){
        this.orderIndex01 = new SimpleStringProperty(orderIndex01);
        this.orderIndex02 = new SimpleStringProperty(orderIndex02);
        this.orderIndex03 = new SimpleStringProperty(orderIndex03);
        this.orderIndex04 = new SimpleStringProperty(orderIndex04);
        this.orderIndex05 = new SimpleStringProperty(orderIndex05);
        this.orderIndex06 = new SimpleStringProperty(orderIndex06);
        this.orderIndex07 = new SimpleStringProperty(orderIndex07);
        this.orderIndex08 = new SimpleStringProperty(orderIndex08);
        this.orderIndex09 = new SimpleStringProperty(orderIndex09);
        this.orderIndex10 = new SimpleStringProperty(orderIndex10);
        this.orderIndex11 = new SimpleStringProperty(orderIndex11);
        this.orderIndex12 = new SimpleStringProperty(orderIndex12);
        this.orderIndex13 = new SimpleStringProperty(orderIndex13);
        this.orderIndex14 = new SimpleStringProperty(orderIndex14);
        this.orderIndex15 = new SimpleStringProperty(orderIndex15);
        this.orderIndex16 = new SimpleStringProperty(orderIndex16);
        this.orderIndex17 = new SimpleStringProperty(orderIndex17);
    }
//   WAY BILL CONSTRUCTOR
    public OrderModel(String orderIndex01, String orderIndex02, String orderIndex03, 
            String orderIndex04,String orderIndex05,String orderIndex06,
            String orderIndex07,String orderIndex08,String orderIndex09,
            String orderIndex10,String orderIndex11,String orderIndex12,
            String orderIndex13,String orderIndex14,String orderIndex15,
            String orderIndex16,String orderIndex17,String orderIndex18){
        this.orderIndex01 = new SimpleStringProperty(orderIndex01);
        this.orderIndex02 = new SimpleStringProperty(orderIndex02);
        this.orderIndex03 = new SimpleStringProperty(orderIndex03);
        this.orderIndex04 = new SimpleStringProperty(orderIndex04);
        this.orderIndex05 = new SimpleStringProperty(orderIndex05);
        this.orderIndex06 = new SimpleStringProperty(orderIndex06);
        this.orderIndex07 = new SimpleStringProperty(orderIndex07);
        this.orderIndex08 = new SimpleStringProperty(orderIndex08);
        this.orderIndex09 = new SimpleStringProperty(orderIndex09);
        this.orderIndex10 = new SimpleStringProperty(orderIndex10);
        this.orderIndex11 = new SimpleStringProperty(orderIndex11);
        this.orderIndex12 = new SimpleStringProperty(orderIndex12);
        this.orderIndex13 = new SimpleStringProperty(orderIndex13);
        this.orderIndex14 = new SimpleStringProperty(orderIndex14);
        this.orderIndex15 = new SimpleStringProperty(orderIndex15);
        this.orderIndex16 = new SimpleStringProperty(orderIndex16);
        this.orderIndex17 = new SimpleStringProperty(orderIndex17);
        this.orderIndex18 = new SimpleStringProperty(orderIndex18);
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

    public String getOrderIndex08() {
        return orderIndex08.get();
    }

    public String getOrderIndex09() {
        return orderIndex09.get();
    }

    public String getOrderIndex10() {
        return orderIndex10.get();
    }

    public String getOrderIndex11() {
        return orderIndex11.get();
    }

    public String getOrderIndex12() {
        return orderIndex12.get();
    }

    public String getOrderIndex13() {
        return orderIndex13.get();
    }

    public String getOrderIndex14() {
        return orderIndex14.get();
    }

    public String getOrderIndex15() {
        return orderIndex15.get();
    }

    public String getOrderIndex16() {
        return orderIndex16.get();
    }
    public String getOrderIndex17() {
        return orderIndex17.get();
    }

    public String getOrderIndex18() {
        return orderIndex18.get();
    }


    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }
   
}
