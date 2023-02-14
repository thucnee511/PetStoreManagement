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
    private String ordId ;
    private String date ;
    private String customerName ;
    private Pet pet ;
    private int quantity ;

    public Order(String ordId, String date, String customerName, Pet pet, int quantity) {
        this.ordId = ordId;
        this.date = date;
        this.customerName = customerName;
        this.pet = pet;
        this.quantity = quantity;
    }

    public String getOrdId() {
        return ordId;
    }

    public String getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Pet getPet() {
        return pet;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public double getTotal(){
        return  (double)quantity * pet.getUnitPrice() ;
    }

    public void show(){
        String msg = String.format("%-10s|%-10s|%-20s|%10d|%12s",
                ordId , date , customerName , quantity , toStringTotal()) ;
        System.out.println(msg);
    }
    private String toStringTotal(){
        String ret = String.format("$ %.0f" , getTotal()) ;
        return ret ;
    }
    
    @Override
    public String toString(){
        String msg = String.format("%s,%s,%s,%s,%d", 
                ordId , date , customerName , pet.getId() , quantity);
        return msg ;
    }
}
