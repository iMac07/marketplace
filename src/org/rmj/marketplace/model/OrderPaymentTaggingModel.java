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
public class OrderPaymentTaggingModel {
    private SimpleStringProperty paymentIndex01;   
    private SimpleStringProperty paymentIndex02;  
    private SimpleStringProperty paymentIndex03;  
    private SimpleStringProperty paymentIndex04;    
    private SimpleStringProperty paymentIndex05;   
    private SimpleStringProperty paymentIndex06;  
    private SimpleStringProperty paymentIndex07; 
    private SimpleStringProperty paymentIndex08; 
 

    public OrderPaymentTaggingModel(String paymentIndex01, String paymentIndex02, String paymentIndex03, 
            String paymentIndex04,String paymentIndex05,String paymentIndex06,String paymentIndex07,
            String paymentIndex08){
        this.paymentIndex01 = new SimpleStringProperty(paymentIndex01);
        this.paymentIndex02 = new SimpleStringProperty(paymentIndex02);
        this.paymentIndex03 = new SimpleStringProperty(paymentIndex03);
        this.paymentIndex04 = new SimpleStringProperty(paymentIndex04);
        this.paymentIndex05 = new SimpleStringProperty(paymentIndex05);
        this.paymentIndex06 = new SimpleStringProperty(paymentIndex06);
        this.paymentIndex07 = new SimpleStringProperty(paymentIndex07);
        this.paymentIndex08 = new SimpleStringProperty(paymentIndex08);


    }
    public String getPaymentIndex01() {
        return paymentIndex01.get();
    }

    public String getPaymentIndex02() {
        return paymentIndex02.get();
    }

    public String getPaymentIndex03() {
        return paymentIndex03.get();
    }

    public String getPaymentIndex04() {
        return paymentIndex04.get();
    }

    public String getPaymentIndex05() {
        return paymentIndex05.get();
    }

    public String getPaymentIndex06() {
        return paymentIndex06.get();
    }

    public String getPaymentIndex07() {
        return paymentIndex07.get();
    }

    public String getPaymentIndex08() {
        return paymentIndex08.get();
    }

    

}
