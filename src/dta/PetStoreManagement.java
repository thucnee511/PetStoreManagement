/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import tools.FileHandler;
import tools.InputHandler;
import ui.Menu;

/**
 *
 * @author Admin
 */
public class PetStoreManagement {

    private final String PETDATPATH = "/src/files/pets.dat";
    private final String ORDDATPATH = "/src/files/orders.dat";
    private final Map<String, Pet> pets = new HashMap<>();
    private final Map<String, Order> orders = new HashMap<>();

    public PetStoreManagement(){
        this.loadData() ;
    }
    
    public void addNewPet() {
        while (true) {
            String petId = InputHandler.getString("Enter pet's id: ", "Invalid id entered!", "P\\d*");
            if (findPetById(petId) != null) {
                System.out.println("Pet's id must be unique");
                continue;
            } else {
                String des = InputHandler.getString("Enter pet's description: ", "Invalid description!");
                String imp = InputHandler.getDate("Enter pet's import date: ","MM/dd/yyyy");
                double price = InputHandler.getPositiveReal("Enter pet's unit price: ", "Invalid unit price!");
                String cate = InputHandler.getString("Enter pet's category: ", "Invalid category [Cat,Dog,Parrot]!", "([Cc][Aa][Tt])|([Dd][Oo][Gg])|([Pp][Aa][Rr]{2}[Oo][Tt])");
                pets.put(petId, new Pet(petId, des, imp, price, cate));
            }
            if (!Menu.getYesOrNo("Continously add new pet?")) {
                return;
            }
        }
    }

    public void findPet() {
        String pId = InputHandler.getString("Enter pet's id: ", "Invalid id entered!", "P\\d*");
        Pet finded = findPetById(pId);
        if (finded != null) {
            String msg = String.format("Pet info: %s", finded.toString());
            System.out.println(msg);
        } else {
            System.out.println("The pet does not exist");
        }
    }

    public void updatePet() {
        String pId = InputHandler.getString("Enter pet's id: ", "Invalid id entered!", "P\\d*");
        Pet finded = findPetById(pId);
        if (finded != null) {
            String msg = String.format("Pet info: %s", finded.toString());
            System.out.println(msg);
            System.out.println("Update the pet (input blank to keep the old infomation)");
            String des = InputHandler.getString("Enter new description: ");
            String imp = InputHandler.getString("Enter new import date: ");
            String price = InputHandler.getString("Enter new unit price: ");
            String cate = InputHandler.getString("Enter new category: ");
            try {
                String catereg = "([Cc][Aa][Tt])|([Dd][Oo][Gg])|([Pp][Aa][Rr]{2}[Oo][Tt])";
                int _price = 0;
                if (!imp.isEmpty() && !InputHandler.checkValidDate(imp , "MM/dd/yyyy")) {
                    throw new Exception();
                }
                if (!price.isEmpty()) {
                    _price = Integer.parseInt(price);
                }
                if (!des.isEmpty() && (des.length() < 5 || des.length() > 30)) {
                    throw new Exception();
                }
                if (!cate.isEmpty() && !cate.matches(catereg)) {
                    throw new Exception();
                }
                if (!price.isEmpty()) {
                    finded.setUnitPrice(_price);
                }
                if (!cate.isEmpty()) {
                    finded.setCategory(cate);
                }
                if (!imp.isEmpty()) {
                    finded.setImportDate(imp);
                }
                if (!des.isEmpty()) {
                    finded.setDescription(des);
                }
                System.out.println("Update succesfully!");
            } catch (Exception e) {
                System.out.println("Update failed!");
            }
        } else {
            System.out.println("The pet does not exist");
        }
    }

    public void deletePet() {
        String pId = InputHandler.getString("Enter pet's id: ", "Invalid id entered!", "P\\d*");
        Pet finded = findPetById(pId);
        if (finded != null) {
            String msg = String.format("Pet info: %s", finded.toString());
            System.out.println(msg);
            try {
                for (Map.Entry<String, Order> entry : orders.entrySet()) {
                    Pet p = entry.getValue().getPet();
                    if (p.getId().equals(pId)) {
                        throw new Exception();
                    }
                }
                if (Menu.getYesOrNo("Do you want to delete this pet")) {
                    pets.remove(pId);
                    System.out.println("Delete successfully!");
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Delete failed!");
            }
        } else {
            System.out.println("The pet does not exist");
        }
    }

    public void addOrder() {
        while (true) {
            String ordId = InputHandler.getString("Enter order's id: ", "Invalid id", "O\\d*");
            if (findOrderById(ordId) == null) {
                String date = InputHandler.getDate("Enter order's date: ","MM/dd/yyyy");
                String cusName = InputHandler.getString("Enter customer's name: ", "Invalid name");
                String pId;
                while (true) {
                    pId = InputHandler.getString("Enter pet's id: ", "Invalid id entered!", "P\\d*");
                    if (findPetById(pId) == null) {
                        System.out.println("Pet does not exist");
                    } else {
                        break;
                    }
                }
                Pet p = findPetById(pId);
                int quantity = InputHandler.getPositiveInt("Enter quantity: ", "Invalid positive integer number");
                orders.put(ordId, new Order(ordId, date, cusName, p, quantity));
            } else {
                System.out.println("Order's id must be unique");
            }
            if (!Menu.getYesOrNo("Countinously add new order?")) {
                return;
            }
        }
    }

    public void listOrder() {
        String startDate = InputHandler.getDate("Enter start date: ","MM/dd/yyyy");
        String endDate = InputHandler.getDate("Enter end date: ","MM/dd/yyyy");
        System.out.println("LIST ORDER FROM %s TO %s");
        System.out.println(" No.| Order Id |Order Date| Customer           | Pet Count| Order Total");
        int count = 0;
        int petCount = 0;
        double total = 0;
        for (Map.Entry<String, Order> entry : orders.entrySet()) {
            Order o = entry.getValue();
            String ordDate = o.getDate();
            if (compareDate(ordDate, startDate) >= 0 && compareDate(ordDate, endDate) <= 0) {
                count += 1;
                petCount += o.getQuantity();
                total += o.getTotal();
                String msg = String.format("%4d|", count);
                System.out.print(msg);
                o.show();
            }
        }
        String totalStr = String.format("$ %.0f", total);
        String msg = String.format("    | Total    |          |                    |%10d|%12s", petCount, totalStr);
        System.out.println(msg);
    }

    public void sortOrder() {
        Menu sub = new Menu("Sorting feild:");
        sub.addOption("Order Id");
        sub.addOption("Order Date");
        sub.addOption("Customer Name");
        sub.addOption("Order Total");
        int choice = sub.getChoice();
        Menu _sub = new Menu("Sorting order:");
        _sub.addOption("ASC");
        _sub.addOption("DESC");
        boolean asc = _sub.getChoice() == 1;
        List<Map.Entry<String, Order>> list = new ArrayList<>(orders.entrySet());
        System.out.println("LIST OF ORDERS");
        switch (choice) {
            case 1: {
                System.out.println("Order by: Order Id");
                if (asc) {
                    System.out.println("Sort order: ASC");
                    Collections.sort(list, (Map.Entry<String, Order> o1, Map.Entry<String, Order> o2) -> o1.getValue().getOrdId().compareTo(o2.getValue().getOrdId()));
                } else {
                    System.out.println("Sort order: DESC");
                    Collections.sort(list, (Map.Entry<String, Order> o1, Map.Entry<String, Order> o2) -> (o1.getValue().getOrdId().compareTo(o2.getValue().getOrdId())) * -1);
                }
                break;
            }
            case 2: {
                System.out.println("Order by: Order date");
                if (asc) {
                    System.out.println("Sort order: ASC");
                    Collections.sort(list, (Map.Entry<String, Order> o1, Map.Entry<String, Order> o2) -> {
                        String date1 = o1.getValue().getDate();
                        String date2 = o2.getValue().getDate();
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        Date _date1 = null;
                        Date _date2 = null;
                        try {
                            _date1 = sdf.parse(date1);
                            _date2 = sdf.parse(date2);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (_date1.before(_date2)) {
                            return -1;
                        } else if (_date1.after(_date2)) {
                            return 1;
                        } else {
                            return 0;
                        }
                    });
                } else {
                    System.out.println("Sort order: DESC");
                    Collections.sort(list, new Comparator<Map.Entry<String, Order>>() {
                        @Override
                        public int compare(Map.Entry<String, Order> o1, Map.Entry<String, Order> o2) {
                            String date1 = o1.getValue().getDate();
                            String date2 = o2.getValue().getDate();
                            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                            Date _date1 = null;
                            Date _date2 = null;
                            try {
                                _date1 = sdf.parse(date1);
                                _date2 = sdf.parse(date2);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if (_date1.before(_date2)) {
                                return 1;
                            } else if (_date1.after(_date2)) {
                                return -11;
                            } else {
                                return 0;
                            }
                        }
                    });
                }
                break;
            }
            case 3: {
                System.out.println("Order by: Customer name");
                if (asc) {
                    System.out.println("Sort order: ASC");
                    Collections.sort(list, (Map.Entry<String, Order> o1, Map.Entry<String, Order> o2) -> o1.getValue().getCustomerName().compareTo(o2.getValue().getCustomerName()));
                } else {
                    System.out.println("Sort order: DESC");
                    Collections.sort(list, (Map.Entry<String, Order> o1, Map.Entry<String, Order> o2) -> (o1.getValue().getCustomerName().compareTo(o2.getValue().getCustomerName())) * -1);
                }
                break;
            }
            case 4: {
                System.out.println("Order by: Order Total");
                if (asc) {
                    System.out.println("Sort order: ASC");
                    Collections.sort(list, (Map.Entry<String, Order> o1, Map.Entry<String, Order> o2) -> (int) o1.getValue().getTotal() - (int) o2.getValue().getTotal());
                } else {
                    System.out.println("Sort order: DESC");
                    Collections.sort(list, (Map.Entry<String, Order> o1, Map.Entry<String, Order> o2) -> ((int) o1.getValue().getTotal() - (int) o2.getValue().getTotal()) * -1);
                }
                break;
            }
        }
        System.out.println(" No.| Order Id |Order Date| Customer           | Pet Count| Order Total");
        int count = 0;
        int petCount = 0;
        double total = 0;
        for (Map.Entry<String, Order> entry : orders.entrySet()) {
            Order o = entry.getValue();
            count += 1;
            petCount += o.getQuantity();
            total += o.getTotal();
            String msg = String.format("%4d|", count);
            System.out.print(msg);
            o.show();
        }
        String totalStr = String.format("$ %.0f", total);
        String msg = String.format("    | Total    |          |                    |%10d|%12s", petCount, totalStr);
        System.out.println(msg);
    }

    public void saveData() {
        ArrayList<String> petDta = new ArrayList<>();
        ArrayList<String> ordDta = new ArrayList<>();
        for (Map.Entry<String, Pet> entry : pets.entrySet()) {
            petDta.add(entry.getValue().toString());
        }
        for (Map.Entry<String, Order> entry : orders.entrySet()) {
            ordDta.add(entry.getValue().toString());
        }
        FileHandler.writeToFile(PETDATPATH, petDta);
        FileHandler.writeToFile(ORDDATPATH, ordDta);
    }

    public void loadData() {
        ArrayList<String> dta = new ArrayList<>();
        dta.addAll(FileHandler.readFromFile(PETDATPATH));
        dta.addAll(FileHandler.readFromFile(ORDDATPATH));
        pets.clear();
        orders.clear();
        for (String line : dta) {
            String dataPart[] = line.split(",");
            if (dataPart[0].matches("P\\d*")) {
                pets.put(dataPart[0], new Pet(dataPart[0],
                        dataPart[1],
                        dataPart[2],
                        Double.parseDouble(dataPart[3]),
                        dataPart[4]));
            } else if (dataPart[0].matches("O\\d*")) {
                orders.put(dataPart[0], new Order(dataPart[0],
                        dataPart[1],
                        dataPart[2],
                        findPetById(dataPart[3]),
                        Integer.parseInt(dataPart[4])));
            }
        }
    }

    private int compareDate(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date _date1 = null;
        Date _date2 = null;
        try {
            _date1 = sdf.parse(date1);
            _date2 = sdf.parse(date2);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (_date1.before(_date2)) {
            return -1;
        } else if (_date1.after(_date2)) {
            return 1;
        } else {
            return 0;
        }
    }

    private Order findOrderById(String ordId) {
        return orders.get(ordId);
    }

    private Pet findPetById(String petId) {
        return pets.get(petId);
    }
}
