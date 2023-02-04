/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dta;

/**
 *
 * @author Admin
 */
public class Order {
    private String ordid ;
    private String date ;
    private String customerName ;
    private Pet pet ;
    private int quantity ;

    public Order(String ordid, String date, String customerName, Pet pet, int quantity) {
        this.ordid = ordid;
        this.date = date;
        this.customerName = customerName;
        this.pet = pet;
        this.quantity = quantity;
    }

    public String getOrdid() {
        return ordid;
    }

    public String getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }
    
    public double getTotal(){
        return  (double)quantity * pet.getUnitPrice() ;
    }

}
