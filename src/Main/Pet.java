package Main;

import Enum.PetCategory;
import tools.Util;

public class Pet {

    private String petID;
    private String description;
    private String importDate;
    private int unitPrice;
    private PetCategory category;

    public Pet() {
    }

    public Pet(String petID, String description, String importDate, int unitPrice, PetCategory category) {
        this.petID = petID;
        this.description = description;
        this.importDate = importDate;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public PetCategory getCategory() {
        return category;
    }

    public void setCategory(PetCategory category) {
        this.category = category;
    }

    public void input() {
        petID = Util.inputIdWithFormat("Pet Id", "Pxxx", PetList.checkUniqueId, "P[0-9]{3}");
        description = Util.inputString("Description", "not null");
        importDate = Util.inputDate("import Date", false);
        unitPrice = Util.getInputInteger("Unit Price", false);
        category = PetCategory.inputPetCategory(false);
    }

    public void update() {
        String newDescription = Util.inputAllowEmpty("Desciption", true);
        if (!newDescription.isEmpty()) {
            setDescription(newDescription);
        }
        String newimportDate = Util.inputDate("import Date", true);
        if (!newimportDate.isEmpty()) {
            setImportDate(newimportDate);
        }
        int newUnitPrice = Util.getInputInteger("Unit Price", true);
        if (newUnitPrice != 0) {
            setUnitPrice(newUnitPrice);
        }
        PetCategory newCategory = PetCategory.inputPetCategory(true);
        if (newCategory != null) {
            setCategory(newCategory);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n")
                .append("  ID: ").append(getPetID()).append(",\n")
                .append("  description: ").append(getDescription()).append(",\n")
                .append("  Import Date: ").append(getImportDate()).append(",\n")
                .append("  Unit Price: ").append(getUnitPrice()).append(",\n")
                .append("  Category: ").append(getCategory()).append("\n")
                .append("}").append("\n");
        return sb.toString();
    }

}
