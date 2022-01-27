/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.model;

import java.text.DecimalFormat;
import javafx.scene.image.ImageView;

/**
 *
 * @author user
 */
public class Util {
   public Util(){}
   public static String priceWithDecimal (Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(price);
    }
   public void setImageAspectRatio(ImageView selectedImage){
        selectedImage.setFitWidth(90);
        selectedImage.setPreserveRatio(true);
        selectedImage.setSmooth(true);
        selectedImage.setCache(true);  
    }
   
}
