/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dta;

import java.util.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import tools.InputHandler;

/**
 *
 * @author Admin
 */
public class PetStoreManagement {

    private Map<String, Pet> pets = new HashMap<>();
    private Map<String, Order> orders = new HashMap<>();

    
    public void addNewPet(){
        String petId = InputHandler.getString("Enter pet's id:", "Invalid id entered!", "P\\d*") ;
        
    }
    
    
    
    
    
    
    private final Comparator<Order> byOrdId = (Order o1, Order o2) -> o1.getOrdid().compareTo(o2.getOrdid());
    private final Comparator<Order> byCusName = (Order o1, Order o2) -> o1.getCustomerName().compareTo(o2.getCustomerName());
    private final Comparator<Order> byOrdTotal = (Order o1, Order o2) -> ((Double) o1.getTotal()).compareTo((Double) o2.getTotal());

    private int compareDay(String d1, String d2) {
        String date1[] = d1.split("/");
        String date2[] = d2.split("/");
        int day1 = Integer.parseInt(date1[0]);
        int month1 = Integer.parseInt(date1[1]);
        int year1 = Integer.parseInt(date1[2]);
        int day2 = Integer.parseInt(date2[0]);
        int month2 = Integer.parseInt(date2[1]);
        int year2 = Integer.parseInt(date2[2]);
        if (year1 == year2) {
            if (month1 == month2) {
                return -(day1 - day2);
            } else {
                return -(month1 - month2);
            }
        } else {
            return -(year1 - year2);
        }
    }
}
