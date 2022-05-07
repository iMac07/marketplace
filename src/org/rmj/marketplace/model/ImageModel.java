/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author User
 */
public class ImageModel {
    private SimpleStringProperty imgIndex01;
    private SimpleStringProperty imgIndex02;  
    
    public ImageModel(){
        
    }
    public ImageModel(String imgIndex01, String imgIndex02){
        this.imgIndex01 = new SimpleStringProperty(imgIndex01);
        this.imgIndex02 = new SimpleStringProperty(imgIndex02);
    }
    public String getImgIndex01() {
        return imgIndex01.get();
    }

    public String getImgIndex02() {
        return imgIndex02.get();
    }
}
