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
public class ClientInfoModel {

    private SimpleStringProperty clientIndex01;
    private SimpleStringProperty clientIndex02;  
    private SimpleStringProperty clientIndex03;
    private SimpleStringProperty clientIndex04;
    private SimpleStringProperty clientIndex05;  
    private SimpleStringProperty clientIndex06;
    private SimpleStringProperty clientIndex07;
    private SimpleStringProperty clientIndex08;  
    private SimpleStringProperty clientIndex09; 
    private SimpleStringProperty clientIndex10; 

    
    public ClientInfoModel(){
        
    }
    public ClientInfoModel(String clientIndex01,
            String clientIndex02,
            String clientIndex03,
            String clientIndex04,
            String clientIndex05,
            String clientIndex06,
            String clientIndex07,
            String clientIndex08,
            String clientIndex09,
            String clientIndex10) {
        this.clientIndex01 = new SimpleStringProperty(clientIndex01);
        this.clientIndex02 = new SimpleStringProperty(clientIndex02);
        this.clientIndex03 = new SimpleStringProperty(clientIndex03);
        this.clientIndex04 = new SimpleStringProperty(clientIndex04);
        this.clientIndex05 = new SimpleStringProperty(clientIndex05);
        this.clientIndex06 = new SimpleStringProperty(clientIndex06);
        this.clientIndex07 = new SimpleStringProperty(clientIndex07);
        this.clientIndex08 = new SimpleStringProperty(clientIndex08);
        this.clientIndex09 = new SimpleStringProperty(clientIndex09);
        this.clientIndex10 = new SimpleStringProperty(clientIndex10);
    }
    
    public String getClientIndex01() {
        return clientIndex01.get();
    }

    public String getClientIndex02() {
        return clientIndex02.get();
    }

    public String getClientIndex03() {
        return clientIndex03.get();
    }
    
    public String getClientIndex04() {
        return clientIndex04.get();
    }

    public String getClientIndex05() {
        return clientIndex05.get();
    }

    public String getClientIndex06() {
        return clientIndex06.get();
    }
     
    public String getClientIndex07() {
        return clientIndex07.get();
    }

    public String getClientIndex08() {
        return clientIndex08.get();
    }

    public String getClientIndex09() {
        return clientIndex09.get();
    }
    public String getClientIndex10() {
        return clientIndex10.get();
    }

}