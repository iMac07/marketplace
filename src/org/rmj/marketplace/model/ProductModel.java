/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.model;

import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author user
 */
public class ProductModel {

    private SimpleStringProperty prodIndex01;
    private SimpleStringProperty prodIndex02;  
    private SimpleStringProperty prodIndex03;
    private SimpleStringProperty prodIndex04;   
    private SimpleStringProperty prodIndex05;   
    
    public ProductModel(){
        
    }
    public ProductModel(String prodIndex01, String prodIndex02, String prodIndex03, 
            String prodIndex04, String prodIndex05) {
        this.prodIndex01 = new SimpleStringProperty(prodIndex01);
        this.prodIndex02 = new SimpleStringProperty(prodIndex02);
        this.prodIndex03 = new SimpleStringProperty(prodIndex03);
        this.prodIndex04 = new SimpleStringProperty(prodIndex04);
        this.prodIndex05 = new SimpleStringProperty(prodIndex05);
    }
     public String getProdIndex01() {
        return prodIndex01.get();
    }

    public void setProdIndex01(String prodIndex01) {
        this.prodIndex01 = new SimpleStringProperty(prodIndex01);
    }

    public String getProdIndex02() {
        return prodIndex02.get();
    }

    public void setProdIndex02(String prodIndex02) {
        this.prodIndex02 = new SimpleStringProperty(prodIndex02);
    }

    public String getProdIndex03() {
        return prodIndex03.get();
    }

    public void setProdIndex03(String prodIndex03) {
        this.prodIndex03 = new SimpleStringProperty(prodIndex03);
    }

    public String getProdIndex04() {
        return prodIndex04.get();
    }

    public void setProdIndex04(String prodIndex04) {
        this.prodIndex04 = new SimpleStringProperty(prodIndex04);
    }
    public String getProdIndex05() {
        return prodIndex05.get();
    }

    public void setProdIndex05(String prodIndex05) {
        this.prodIndex05 = new SimpleStringProperty(prodIndex05);
    }
    
}
