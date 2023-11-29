/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.List;
import tools.Check;
import tools.Util;

/**
 *
 * @author Acer
 */
public class Order {

    private String orderID;
    private String orderDate;
    private String customerName;
    private int petCost;
    private List<listPetToBuy> ListPetToBuy;

    public Order() {
    }

    public Order(String orderID, String orderDate, String customerName, int petCost, List<listPetToBuy> ListPetToBuy) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.petCost = petCost;
        this.ListPetToBuy = ListPetToBuy;
    }

    public List<listPetToBuy> getListPetToBuy() {
        return ListPetToBuy;
    }

    public void setListPetToBuy(List<listPetToBuy> ListPetToBuy) {
        this.ListPetToBuy = ListPetToBuy;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getPetCost() {
        return petCost;
    }

    public void setPetCost(int petCost) {
        this.petCost = petCost;
    }

    public void input() {
        orderID = Util.inputIdWithFormat("Order ID", "Dxxx", OrderList.checkUniqueOrderId, "D[0-9]{3}");
        orderDate = Util.inputDate("order Date", false);
        customerName = Util.inputString("Customer name", "not empty");
        Pet petID;
        ListPetToBuy = new ArrayList<>();
        do {
            petID = PetList.findPetById(Util.inputString("pet ID", "not null"));
            if (petID != null) {
                String quanlity = Util.inputStringINT("Quanlity", false);
                int newQuanlity = Integer.parseInt(quanlity);
                ListPetToBuy.add(new listPetToBuy(petID, newQuanlity));
            }
        } while (petID == null || Check.checkContinue("add pet", "do not re-enter pet id"));

    }

}
