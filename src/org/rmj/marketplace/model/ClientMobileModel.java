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
public class ClientMobileModel {



    private SimpleStringProperty mobileIndex01;
    private SimpleStringProperty mobileIndex02;  
    private SimpleStringProperty mobileIndex03;
    private SimpleStringProperty mobileIndex04;
    private SimpleStringProperty mobileIndex05;  
    private SimpleStringProperty mobileIndex06;
    private SimpleStringProperty mobileIndex07;
    public ClientMobileModel(){
        
    }
    public ClientMobileModel(String mobileIndex01,
            String mobileIndex02,
            String mobileIndex03,
            String mobileIndex04,
            String mobileIndex05,
            String mobileIndex06,
            String mobileIndex07) 
{
        this.mobileIndex01 = new SimpleStringProperty(mobileIndex01);
        this.mobileIndex02 = new SimpleStringProperty(mobileIndex02);
        this.mobileIndex03 = new SimpleStringProperty(mobileIndex03);
        this.mobileIndex04 = new SimpleStringProperty(mobileIndex04);
        this.mobileIndex05 = new SimpleStringProperty(mobileIndex05);
        this.mobileIndex06 = new SimpleStringProperty(mobileIndex06);
        this.mobileIndex07 = new SimpleStringProperty(mobileIndex07);
    }
    
    public String getMobileIndex01() {
        return mobileIndex01.get();
    }

    public String getMobileIndex02() {
        return mobileIndex02.get();
    }

    public String getMobileIndex03() {
        return mobileIndex03.get();
    }
    
    public String getMobileIndex04() {
        return mobileIndex04.get();
    }

    public String getMobileIndex05() {
        return mobileIndex05.get();
    }

    public String getMobileIndex06() {
        return mobileIndex06.get();
    }

    public String getMobileIndex07() {
        return mobileIndex07.get();
    }

}