/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package runtime;

import dta.PetStoreManagement;
import tools.InputHandler;
import ui.Menu;

/**
 *
 * @author Admin
 */
public class Procedure {
    public static void main(String[] args) {
        Menu menu = new Menu("Pet Store Management") ;
        menu.addOption("Add a pet") ;
        menu.addOption("Find a pet") ;
        menu.addOption("Update a pet") ;
        menu.addOption("Delete a pet") ;
        menu.addOption("Add an order") ;
        menu.addOption("List orders") ;
        menu.addOption("Sort orders") ;
        menu.addOption("Save data") ;
        menu.addOption("Load data") ;
        menu.addOption("Quit") ;
        
        PetStoreManagement psm = new PetStoreManagement() ;
        while(true){
            menu.printMenu();
            int choice = menu.getChoice() ;
            switch(choice){
                case 1:{
                    psm.addNewPet();
                    break ;
                }
                case 2:{
                    psm.findPet();
                    break ;
                }
                case 3:{
                    psm.updatePet();
                    break ;
                }
                case 4:{
                    psm.deletePet();
                    break ;
                }
                case 5:{
                    psm.addOrder();
                    break ;
                }
                case 6:{
                    psm.listOrder();
                    break ;
                }
                case 7:{
                    psm.sortOrder();
                    break ;
                }
                case 8:{
                    psm.saveData();
                    break ;
                }
                case 9:{
                    psm.loadData();
                    break ;
                }
                case 10:{
                    return ;
                }
            }
            InputHandler.getString("Press Enter to continue");
        }
    }
}
