/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.rmj.marketplace.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author admin
 */
public class ClientPhotoModel {
    
    private SimpleStringProperty cltPhotoIndex01;
    private SimpleStringProperty cltPhotoIndex02;  
    private SimpleStringProperty cltPhotoIndex03;
    private SimpleStringProperty cltPhotoIndex04;
    private SimpleStringProperty cltPhotoIndex05;

    
    public ClientPhotoModel(){
        
    }
    public ClientPhotoModel(String cltPhotoIndex01,
            String cltPhotoIndex02,
            String cltPhotoIndex03,
            String cltPhotoIndex04,
            String cltPhotoIndex05) 
{
        this.cltPhotoIndex01 = new SimpleStringProperty(cltPhotoIndex01);
        this.cltPhotoIndex02 = new SimpleStringProperty(cltPhotoIndex02);
        this.cltPhotoIndex03 = new SimpleStringProperty(cltPhotoIndex03);
        this.cltPhotoIndex04 = new SimpleStringProperty(cltPhotoIndex04);
        this.cltPhotoIndex05 = new SimpleStringProperty(cltPhotoIndex05);
    }
    
    public String getCltPhotoIndex01() {
        return cltPhotoIndex01.get();
    }

    public String getCltPhotoIndex02() {
        return cltPhotoIndex02.get();
    }

    public String getCltPhotoIndex03() {
        return cltPhotoIndex03.get();
    }
    
    public String getCltPhotoIndex04() {
        return cltPhotoIndex04.get();
    }
    public String getCltPhotoIndex05() {
        return cltPhotoIndex05.get();
    }

}
