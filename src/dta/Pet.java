/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dta;

/**
 *
 * @author Admin
 */
public class Pet {
    private String id ;
    private String description ;
    private String importDate ;
    private double unitPrice ;
    private String category ;

    public Pet(String id, String description, String importDate, double unitPrice, String category) {
        this.id = id;
        this.description = description;
        this.importDate = importDate;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString(){
        String msg = String.format("%s,%s,%s,%.3f,%s",
                id , description , importDate , unitPrice , category) ;
        return msg ;
    }
}
