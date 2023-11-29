package tools;

public enum MenuItem {

    BACK("Back"),
    EXIT("Exit"),
    PET_ADD("Add a pet"),
    PET_FIND("Find a pet"),
    PET_UPDATE("Update a pet"),
    PET_DELETE("Delete a pet"),
    ORDER_ADD("Add a order"),
    ORDER_LIST("List a order"),
    ORDER_SORT("Sort and list order"),
    ORDER_SAVE("Save to file"),
    ORDER_ID("Order ID"),
    ORDER_DATE("Order Date"),
    ORDER_NAME("Customer name"),
    ORDER_COST("Cost"),;

    private MenuItem() {
        this.label = null;
    }

    private final String label;

    public String getLabel() {
        return label;
    }

    private MenuItem(String label) {
        this.label = label;
    }

}
