/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.marketplace.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;

/**
 *
 * @author Cool IT help
 */
public class Employee {
    private ImageView photo;
    private  final SimpleIntegerProperty EmpID;
    private  final SimpleStringProperty firstName;
    private  final SimpleStringProperty email;
    private  final SimpleStringProperty department;
    private  final SimpleIntegerProperty salary;
    
    public Employee(ImageView empPhoto, Integer id, String firstname, String mail, String department, Integer salary)
    {
       System.out.println("this is id" + id);
       this.photo = empPhoto;
       System.out.println("image obvject: " + this.photo);
       this.EmpID = new SimpleIntegerProperty(id);
       this.firstName = new SimpleStringProperty(firstname);
       this.email =  new SimpleStringProperty(mail);
       this.department =  new SimpleStringProperty(department);
       this.salary =  new SimpleIntegerProperty(salary);
    
    }
    
    public ImageView getPhoto(){
            return photo;
    }
    
    
    public void setPhoto(ImageView photo)
    {
      this.photo =  photo;
    
    }
    
    public int getEmpID() {
        return EmpID.get();
    }

    public void setEmpID(int id) {
        this.EmpID.set(id);
    }
    
   
  
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstname) {
        firstName.set(firstname);
    }
    
    

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String mail) {
        email.set(mail);
    }
    
    
    public String getDepartment() {
        return department.get();
    }

    public void setDepartment(String street) {
        this.department.set(street);
    }

    public int getSalary() {
        return salary.get();
    }

    public void setSalary(int salary) {
        this.salary.set(salary);
    }
    
    
}
    
