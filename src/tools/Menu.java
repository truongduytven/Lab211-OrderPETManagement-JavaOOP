package tools;

import Login.User;
import Login.UserList;

public class Menu {

    private static final MenuItem[] adminOptions = {
        MenuItem.EXIT,
        MenuItem.PET_ADD,
        MenuItem.PET_FIND,
        MenuItem.PET_UPDATE,
        MenuItem.PET_DELETE,
        MenuItem.ORDER_ADD,
        MenuItem.ORDER_LIST,
        MenuItem.ORDER_SORT,
        MenuItem.ORDER_SAVE
    };

    private static final MenuItem[] userOptions = {
        MenuItem.EXIT,
        MenuItem.PET_FIND,
        MenuItem.ORDER_LIST,
        MenuItem.ORDER_SORT,};

    private static final MenuItem[] sortOrderOptions = {
        MenuItem.ORDER_ID,
        MenuItem.ORDER_DATE,
        MenuItem.ORDER_NAME,
        MenuItem.ORDER_COST,};

    private MenuItem primaryOption = null;
    private MenuItem subOption = null;

    public Menu() {
        this.primaryOption = MenuItem.EXIT;
        this.subOption = MenuItem.BACK;
    }

    public static MenuItem getUserChoice(String role) {
        MenuItem newChoice = getChoice(role);
        return newChoice;
    }

    public static String loginUser() {
        String role;
        role = UserList.getUserLogin();
        return role;
    }

    private static MenuItem getChoice(String option) {
        MenuItem[] optionList = getOptionList(option);
        int numItems = showOptionMenu(option.toUpperCase() + " options: ", optionList);
        int choice = Util.inputInteger("Please enter your choice", 0, numItems - 1);
        return optionList[choice];
    }

    //-------------------------------------------------------------------------------
    //Print menu sort for user
    public MenuItem getUserChoiceSort() {
        return getChoiceSort();
    }

    private MenuItem getChoiceSort() {
        MenuItem[] optionList = sortOrderOptions;
        int numItems = showOptionMenu("SORT ORDER BY", optionList);
        int choice = Util.inputInteger("Please enter your choice", 0, numItems - 1);
        return optionList[choice];
    }

    private static int showOptionMenu(String menuCaption, MenuItem[] optionList) {
        int numItems = 1;
        if (!optionList.equals(sortOrderOptions)) {
            System.out.println("*********************************************"); //menu of sort don't print *****
        }
        System.out.println(menuCaption);
        for (int i = 1; i < optionList.length; i++) {
            System.out.printf("(%d) -> %s\n", numItems, optionList[i].getLabel());
            numItems++;
        }
        System.out.printf("(0) -> %s\n", optionList[0].getLabel());
        if (!optionList.equals(sortOrderOptions)) {
            System.out.println("*********************************************");
        }
        return numItems;
    }
    //----------------------------------------------------------------------------------

    private static MenuItem[] getOptionList(String option) {
        MenuItem[] optionList = null;
        if (option.equals("admin")) {
            optionList = adminOptions;
        }
        if (option.equals("user")) {
            optionList = userOptions;
        }
        return optionList;
    }

    private boolean isRepeatAction() {
        switch (subOption) {
            case PET_ADD:
            case PET_UPDATE:
            case ORDER_ADD:
            case PET_DELETE:
                String confirm = Util.inputStringINT("Repeat action (Y/N)", false);
                return confirm.trim().toLowerCase().startsWith("y");
        }
        return false;
    }

}
