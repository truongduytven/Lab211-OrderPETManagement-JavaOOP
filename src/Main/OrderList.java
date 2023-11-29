/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import tools.Menu;
import tools.MenuItem;
import tools.Util;

/**
 *
 * @author Acer
 */
public class OrderList {

    static private Map<YearMonth, List<Order>> orderMap;

    public static void prepareMap() {
        orderMap = new HashMap<>();
        orderMap.put(YearMonth.of(2023, 1), new ArrayList<>());
        orderMap.put(YearMonth.of(2023, 2), new ArrayList<>());
        orderMap.put(YearMonth.of(2023, 3), new ArrayList<>());
        orderMap.put(YearMonth.of(2023, 4), new ArrayList<>());
        orderMap.put(YearMonth.of(2023, 5), new ArrayList<>());
        orderMap.put(YearMonth.of(2023, 6), new ArrayList<>());
        orderMap.put(YearMonth.of(2023, 7), new ArrayList<>());
        orderMap.put(YearMonth.of(2023, 8), new ArrayList<>());
        orderMap.put(YearMonth.of(2023, 9), new ArrayList<>());
        orderMap.put(YearMonth.of(2023, 10), new ArrayList<>());
        orderMap.put(YearMonth.of(2023, 11), new ArrayList<>());
        orderMap.put(YearMonth.of(2023, 12), new ArrayList<>());
        readFromFileOrder();
    }

    public static void readFromFileOrder() {
        try {
            FileReader fr = new FileReader("src\\Data\\order.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(",");
                try {
                    if (txt.length != 4) {
                        throw new Exception();
                    }
                    Order newOrder = new Order();
                    newOrder.setOrderID(txt[0]);
                    newOrder.setOrderDate(txt[1]);
                    newOrder.setCustomerName(txt[2]);
                    String txt2[] = txt[3].split("/");
                    List<listPetToBuy> newList = new ArrayList<>();
                    for (int i = 0; i < txt2.length; i++) {
                        String txt3[] = txt2[i].split("-");
                        listPetToBuy newListPetToBuy = new listPetToBuy();
                        newListPetToBuy.setPet(PetList.findPetById(txt3[0]));
                        newListPetToBuy.setQuanlity(Integer.parseInt(txt3[1]));
                        newList.add(newListPetToBuy);
                    }
                    newOrder.setListPetToBuy(newList);
                    addnewOrder(newOrder);
                } catch (Exception e) {
                }
            }
            br.close();
            fr.close();
        } catch (IOException | NumberFormatException e) {
        }
    }

    public static void addOrder() {
        Order newOrder = new Order();
        newOrder.input();
        addnewOrder(newOrder);
    }

    public static int getCostValue(listPetToBuy listPet, Pet pet) {
        int result = 0;
        result = listPet.getQuanlity() * pet.getUnitPrice();
        return result;
    }

    public static int getTotalCost(List<listPetToBuy> newList) {
        int result = 0;
        for (listPetToBuy petToBuy : newList) {
            Pet pet = petToBuy.getPet();
            result = result + (petToBuy.getQuanlity() * pet.getUnitPrice());
        }
        return result;
    }

    public static void addnewOrder(Order order) {

        LocalDate date = LocalDate.parse(order.getOrderDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        YearMonth newYearMonth = YearMonth.from(date);

        List<Order> newOrdersList = orderMap.get(newYearMonth);

        if (newOrdersList == null) {

            newOrdersList = new ArrayList();

            orderMap.put(newYearMonth, newOrdersList);

        }
        newOrdersList.add(order);
    }

    public static void printOrderByTimeCapsule(String startDateStr, String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        YearMonth startYearMonth = YearMonth.of(startDate.getYear(), startDate.getMonth());
        YearMonth endYearMonth = YearMonth.of(endDate.getYear(), endDate.getMonth());

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<YearMonth, List<Order>> entry : orderMap.entrySet()) {
            if (entry.getKey().isAfter(endYearMonth) || entry.getKey().isBefore(startYearMonth)) {
                continue;
            }
            sb.append(entry.getKey()).append(":\n");
            List<Order> values = entry.getValue();
            for (Order value : values) {
                LocalDate orderDate = LocalDate.parse(value.getOrderDate(), formatter);
                if (orderDate.isBefore(startDate) || orderDate.isAfter(endDate)) {
                    continue;
                }

                List<listPetToBuy> listPetToBuy = value.getListPetToBuy();
                sb.append("\t{\n")
                        .append("\tID Order: ").append(value.getOrderID()).append(",\n")
                        .append("\tOrder Name: ").append(value.getCustomerName()).append(",\n")
                        .append("\tOrder Date: ").append(value.getOrderDate()).append(",\n")
                        .append("\tTotal Cost: ").append(getTotalCost(listPetToBuy)).append(",\n");
                for (listPetToBuy petToBuy : listPetToBuy) {
                    Pet pet = petToBuy.getPet();
                    sb.append("\t\t[\n")
                            .append("\t\tPet ID: ").append(pet.getPetID()).append(",\n")
                            .append("\t\tdescription: ").append(pet.getDescription()).append(",\n")
                            .append("\t\timport Date: ").append(pet.getImportDate()).append(",\n")
                            .append("\t\tUnit Price: ").append(pet.getUnitPrice()).append(",\n")
                            .append("\t\tcategory: ").append(pet.getCategory()).append(",\n")
                            .append("\t\tquanlity: ").append(petToBuy.getQuanlity()).append(",\n")
                            .append("\t\tCost: ").append(getCostValue(petToBuy, pet)).append("\n")
                            .append("\t\t]").append("\n");
                }
                sb.append("\t}").append("\n");
            }
        }
        String result = sb.toString();
        System.out.println(result);
    }

    public static void printListOrder() {
        String startDay = Util.inputDate("Start day", false);
        String endDay = Util.inputDate("End Day", false);
        printOrderByTimeCapsule(startDay, endDay);
    }

    public static List<Order> sort(List<Order> newList, String inputString) {
        if (inputString.equals("Date")) {
            return newList.stream()
                    .sorted(Comparator.comparing((Order) -> Order.getOrderDate()))
                    .collect(Collectors.toList());
        }
        if (inputString.equals("Name")) {
            return newList.stream()
                    .sorted(Comparator.comparing((Order) -> Order.getCustomerName()))
                    .collect(Collectors.toList());
        }
        if (inputString.equals("ID")) {
            return newList.stream()
                    .sorted(Comparator.comparing((Order) -> Order.getOrderID()))
                    .collect(Collectors.toList());
        }
        if (inputString.equals("Cost")) {
            return newList.stream()
                    .sorted(Comparator.comparing((Order) -> Order.getPetCost()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public static void SortOrder() {
        List<Order> newListAll = new ArrayList<>();
        for (Map.Entry<YearMonth, List<Order>> entry : orderMap.entrySet()) {
            YearMonth key = entry.getKey();
            List<Order> value = entry.getValue();
            for (Order order : value) {
                Order newOrder = new Order(order.getOrderID(), order.getOrderDate(), order.getCustomerName(), getTotalCost(order.getListPetToBuy()), order.getListPetToBuy());
                newListAll.add(newOrder);
            }
        }
        Menu menu = new Menu();
        MenuItem userchoice;
        do {
            userchoice = menu.getUserChoiceSort();
            switch (userchoice) {
                case ORDER_ID:
                    printSortOrder("ID", newListAll);
                    break;
                case ORDER_DATE:
                    printSortOrder("Date", newListAll);
                    break;
                case ORDER_NAME:
                    printSortOrder("Name", newListAll);
                    break;
                case ORDER_COST:
                    printSortOrder("Cost", newListAll);
                    break;
                default:
                    break;
            }
        } while (userchoice != MenuItem.EXIT);
    }

    public static void printSortOrder(String msg, List<Order> newList) {
        StringBuilder sb = new StringBuilder();

        List<Order> ListAfterSort = sort(newList, msg);
        for (Order value : ListAfterSort) {
            List<listPetToBuy> listPetToBuy = value.getListPetToBuy();
            sb.append("{\n")
                    .append("ID Order: ").append(value.getOrderID()).append(",\n")
                    .append("Order Name: ").append(value.getCustomerName()).append(",\n")
                    .append("Order Date: ").append(value.getOrderDate()).append(",\n")
                    .append("Total Cost: ").append(getTotalCost(listPetToBuy)).append(",\n");
            for (listPetToBuy petToBuy : listPetToBuy) {
                Pet pet = petToBuy.getPet();
                sb.append("\t[\n")
                        .append("\tPet ID: ").append(pet.getPetID()).append(",\n")
                        .append("\tDescription: ").append(pet.getDescription()).append(",\n")
                        .append("\tImport Date: ").append(pet.getImportDate()).append(",\n")
                        .append("\tUnit Price: ").append(pet.getUnitPrice()).append(",\n")
                        .append("\tcategory: ").append(pet.getCategory()).append(",\n")
                        .append("\tquanlity: ").append(petToBuy.getQuanlity()).append(",\n")
                        .append("\tCost: ").append(getCostValue(petToBuy, pet)).append("\n")
                        .append("\t]").append("\n");
            }
            sb.append("}").append("\n");
        }

        String result = sb.toString();
        System.out.println(result);
    }

    public static Util.CheckUnique checkUniqueOrderId = new Util.CheckUnique() {
        @Override
        public boolean check(String checkedId) {
            for (Map.Entry<YearMonth, List<Order>> entry : orderMap.entrySet()) {
                List<Order> value = entry.getValue();
                for (Order order : value) {
                    if (order.getOrderID().equals(checkedId)) {
                        return false;
                    }
                }
            }
            return true;
        }
    };

    public static void saveOrdertofile() {
        try {
            FileWriter fw = new FileWriter("src\\Data\\order.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Map.Entry<YearMonth, List<Order>> entry : orderMap.entrySet()) {
                YearMonth key = entry.getKey();
                List<Order> value = entry.getValue();
                for (Order order : value) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(order.getOrderID()).append(",")
                            .append(order.getOrderDate()).append(",")
                            .append(order.getCustomerName()).append(",");
                    List<listPetToBuy> petToBuys = order.getListPetToBuy();
                    for (int i = 0; i < petToBuys.size(); i++) {
                        sb.append(petToBuys.get(i).getPet().getPetID()).append("-")
                                .append(petToBuys.get(i).getQuanlity());
                        if (i != petToBuys.size() - 1) {
                            sb.append("/");
                        }
                    }
                    String result = sb.toString();
                    bw.write(result);
                    bw.newLine();
                }

            }
            bw.close();
            fw.close();
        } catch (IOException e) {
        }
        System.out.println("Saved  to the files");
    }
}
