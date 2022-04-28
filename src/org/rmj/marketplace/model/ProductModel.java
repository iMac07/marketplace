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
    private SimpleStringProperty prodIndex06;
    private SimpleStringProperty prodIndex07;  
    private SimpleStringProperty prodIndex08;
    private SimpleStringProperty prodIndex09;   
    private SimpleStringProperty prodIndex10;   
    private SimpleStringProperty prodIndex11;   
    private SimpleStringProperty prodIndex12;   
    private SimpleStringProperty prodIndex13;   
    private SimpleStringProperty prodIndex14;   
    private SimpleStringProperty prodIndex15;  
    private SimpleStringProperty prodIndex16;  
    private SimpleStringProperty prodIndex17;    
    private SimpleStringProperty prodIndex18;  

    public ProductModel(){
        
    }
    public ProductModel(String prodIndex01, String prodIndex02, String prodIndex03, 
            String prodIndex04, String prodIndex05,
            String prodIndex06, String prodIndex07,
            String prodIndex08, String prodIndex09,
            String prodIndex10, String prodIndex11,
            String prodIndex12, String prodIndex13,
            String prodIndex14, String prodIndex15,
            String prodIndex16, String prodIndex17,
            String prodIndex18) {
        this.prodIndex01 = new SimpleStringProperty(prodIndex01);
        this.prodIndex02 = new SimpleStringProperty(prodIndex02);
        this.prodIndex03 = new SimpleStringProperty(prodIndex03);
        this.prodIndex04 = new SimpleStringProperty(prodIndex04);
        this.prodIndex05 = new SimpleStringProperty(prodIndex05);
        this.prodIndex06 = new SimpleStringProperty(prodIndex06);
        this.prodIndex07 = new SimpleStringProperty(prodIndex07);
        this.prodIndex08 = new SimpleStringProperty(prodIndex08);
        this.prodIndex09 = new SimpleStringProperty(prodIndex09);
        this.prodIndex10 = new SimpleStringProperty(prodIndex10);
        this.prodIndex11 = new SimpleStringProperty(prodIndex11);
        this.prodIndex12 = new SimpleStringProperty(prodIndex12);
        this.prodIndex13 = new SimpleStringProperty(prodIndex13);
        this.prodIndex14 = new SimpleStringProperty(prodIndex14);
        this.prodIndex15 = new SimpleStringProperty(prodIndex15);
        this.prodIndex16 = new SimpleStringProperty(prodIndex16);
        this.prodIndex17 = new SimpleStringProperty(prodIndex17);
        this.prodIndex18 = new SimpleStringProperty(prodIndex18);
    }
    
    public String getProdIndex01() {
        return prodIndex01.get();
    }

    public String getProdIndex02() {
        return prodIndex02.get();
    }

    public String getProdIndex03() {
        return prodIndex03.get();
    }

    public String getProdIndex04() {
        return prodIndex04.get();
    }

    public String getProdIndex05() {
        return prodIndex05.get();
    }

    public String getProdIndex06() {
        return prodIndex06.get();
    }

    public String getProdIndex07() {
        return prodIndex07.get();
    }

    public String getProdIndex08() {
        return prodIndex08.get();
    }

    public String getProdIndex09() {
        return prodIndex09.get();
    }

    public String getProdIndex10() {
        return prodIndex10.get();
    }

    public String getProdIndex11() {
        return prodIndex11.get();
    }

    public String getProdIndex12() {
        return prodIndex12.get();
    }

    public String getProdIndex13() {
        return prodIndex13.get();
    }

    public String getProdIndex14() {
        return prodIndex14.get();
    }

    public String getProdIndex15() {
        return prodIndex15.get();
    }

    public String getProdIndex16() {
        return prodIndex16.get();
    }

    public String getProdIndex17() {
        return prodIndex17.get();
    }
    
    public String getProdIndex18() {
        return prodIndex18.get();
    }
}
