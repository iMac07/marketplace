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
    private SimpleStringProperty orderDetIndex01;   
    private SimpleStringProperty orderDetIndex02;  
    private SimpleStringProperty orderDetIndex03;  
    private SimpleStringProperty orderDetIndex04;    
    private SimpleStringProperty orderDetIndex05;   
    private SimpleStringProperty orderDetIndex06;  
    private SimpleStringProperty orderDetIndex07;  
    private SimpleStringProperty orderDetIndex08;    
    private SimpleStringProperty orderDetIndex09;   
    private SimpleStringProperty orderDetIndex10;  
    private SimpleStringProperty orderDetIndex11;  
    private SimpleStringProperty orderDetIndex12;   


    public OrderDetailModel(String orderDetIndex01, String orderDetIndex02, String orderDetIndex03, 
            String orderDetIndex04,String orderDetIndex05,String orderDetIndex06,
            String orderDetIndex07,String orderDetIndex08,String orderDetIndex09,
            String orderDetIndex10,String orderDetIndex11,String orderDetIndex12){
        this.orderDetIndex01 = new SimpleStringProperty(orderDetIndex01);
        this.orderDetIndex02 = new SimpleStringProperty(orderDetIndex02);
        this.orderDetIndex03 = new SimpleStringProperty(orderDetIndex03);
        this.orderDetIndex04 = new SimpleStringProperty(orderDetIndex04);
        this.orderDetIndex05 = new SimpleStringProperty(orderDetIndex05);
        this.orderDetIndex06 = new SimpleStringProperty(orderDetIndex06);
        this.orderDetIndex07 = new SimpleStringProperty(orderDetIndex07);
        this.orderDetIndex08 = new SimpleStringProperty(orderDetIndex09);
        this.orderDetIndex09 = new SimpleStringProperty(orderDetIndex09);
        this.orderDetIndex10 = new SimpleStringProperty(orderDetIndex10);
        this.orderDetIndex11 = new SimpleStringProperty(orderDetIndex11);
        this.orderDetIndex12 = new SimpleStringProperty(orderDetIndex12);
    }
    public String getOrderDetIndex01() {
        return orderDetIndex01.get();
    }

    public String getOrderDetIndex02() {
        return orderDetIndex02.get();
    }

    public String getOrderDetIndex03() {
        return orderDetIndex03.get();
    }

    public String getOrderDetIndex04() {
        return orderDetIndex04.get();
    }

    public String getOrderDetIndex05() {
        return orderDetIndex05.get();
    }

    public String getOrderDetIndex06() {
        return orderDetIndex06.get();
    }

    public String getOrderDetIndex07() {
        return orderDetIndex07.get();
    }

    public String getOrderDetIndex08() {
        return orderDetIndex08.get();
    }

    public String getOrderDetIndex09() {
        return orderDetIndex09.get();
    }

    public String getOrderDetIndex10() {
        return orderDetIndex10.get();
    }

    public String getOrderDetIndex11() {
        return orderDetIndex11.get();
    }

    public String getOrderDetIndex12() {
        return orderDetIndex12.get();
    }

}
