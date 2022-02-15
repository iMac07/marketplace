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

    private SimpleStringProperty cleintIndex01;
    private SimpleStringProperty cleintIndex02;  
    private SimpleStringProperty cleintIndex03;
    private SimpleStringProperty cleintIndex04;    
    
    public ClientInfoModel(){
        
    }
    public ClientInfoModel(String cleintIndex01,
            String cleintIndex02,
            String cleintIndex03, 
            String cleintIndex04) {
        this.cleintIndex01 = new SimpleStringProperty(cleintIndex01);
        this.cleintIndex02 = new SimpleStringProperty(cleintIndex02);
        this.cleintIndex03 = new SimpleStringProperty(cleintIndex03);
        this.cleintIndex04 = new SimpleStringProperty(cleintIndex04);
    }
     public String getProdIndex01() {
        return cleintIndex01.get();
    }

    public void setProdIndex01(String cleintIndex01) {
        this.cleintIndex01 = new SimpleStringProperty(cleintIndex01);
    }

    public String getProdIndex02() {
        return cleintIndex02.get();
    }

    public void setProdIndex02(String cleintIndex02) {
        this.cleintIndex02 = new SimpleStringProperty(cleintIndex02);
    }

    public String getProdIndex03() {
        return cleintIndex03.get();
    }

    public void setProdIndex03(String cleintIndex03) {
        this.cleintIndex03 = new SimpleStringProperty(cleintIndex03);
    }

    public String getProdIndex04() {
        return cleintIndex04.get();
    }

    public void setProdIndex04(String cleintIndex04) {
        this.cleintIndex04 = new SimpleStringProperty(cleintIndex04);
    }
    
}
