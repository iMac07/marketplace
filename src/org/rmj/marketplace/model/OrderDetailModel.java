/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.rmj.marketplace.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author User
 */
public class OrderDetailModel {
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


    public OrderDetailModel(String orderIndex01, String orderIndex02, String orderIndex03, 
            String orderIndex04,String orderIndex05,String orderIndex06,
            String orderIndex07,String orderIndex08,String orderIndex09,
            String orderIndex10,String orderIndex11,String orderIndex12){
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

}
