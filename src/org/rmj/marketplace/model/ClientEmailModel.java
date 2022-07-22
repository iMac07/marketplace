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
public class ClientEmailModel {



    private SimpleStringProperty emailIndex01;
    private SimpleStringProperty emailIndex02;  
    private SimpleStringProperty emailIndex03;
    private SimpleStringProperty emailIndex04;
    private SimpleStringProperty emailIndex05;  
    private SimpleStringProperty emailIndex06;
    private SimpleStringProperty emailIndex07;
    public ClientEmailModel(){
        
    }
    public ClientEmailModel(String emailIndex01,
            String emailIndex02,
            String emailIndex03,
            String emailIndex04,
            String emailIndex05,
            String emailIndex06,
            String emailIndex07) 
{
        this.emailIndex01 = new SimpleStringProperty(emailIndex01);
        this.emailIndex02 = new SimpleStringProperty(emailIndex02);
        this.emailIndex03 = new SimpleStringProperty(emailIndex03);
        this.emailIndex04 = new SimpleStringProperty(emailIndex04);
        this.emailIndex05 = new SimpleStringProperty(emailIndex05);
        this.emailIndex06 = new SimpleStringProperty(emailIndex06);
        this.emailIndex07 = new SimpleStringProperty(emailIndex07);
    }
    
    public String getEmailIndex01() {
        return emailIndex01.get();
    }

    public String getEmailIndex02() {
        return emailIndex02.get();
    }

    public String getEmailIndex03() {
        return emailIndex03.get();
    }
    
    public String getEmailIndex04() {
        return emailIndex04.get();
    }

    public String getEmailIndex05() {
        return emailIndex05.get();
    }

    public String getEmailIndex06() {
        return emailIndex06.get();
    }

    public String getEmailIndex07() {
        return emailIndex07.get();
    }

}