/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import tools.Menu;
import tools.MenuItem;

/**
 *
 * @author Acer
 */
public class PetShopManager {

    private static void process() {
        Menu menu = new Menu();
        PetList.prepareMap();
        OrderList.prepareMap();
        MenuItem userChoice;
        String role = Menu.loginUser();
        do {
            userChoice = Menu.getUserChoice(role);
            switch (userChoice) {
                case PET_ADD:
                    PetList.addPet();
                    break;
                case PET_UPDATE:
                    PetList.updatePet();
                    break;
                case PET_FIND:
                    PetList.findPet();
                    break;
                case PET_DELETE:
                    PetList.deletePet();
                    break;
                case ORDER_ADD:
                    OrderList.addOrder();
                    break;
                case ORDER_LIST:
                    OrderList.printListOrder();
                    break;
                case ORDER_SORT:
                    OrderList.SortOrder();
                    break;
                case ORDER_SAVE:
                    PetList.savePetToFile();
                    OrderList.saveOrdertofile();
                    break;
                case EXIT:
                    System.out.println("Exited!");
                    break;
                default:
                    System.out.println("???");
            }
        } while (userChoice != MenuItem.EXIT);
    }

    public static void main(String[] args) {
        new PetShopManager().process();
    }

}
