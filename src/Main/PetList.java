package Main;

import Enum.PetCategory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import tools.Util;

public class PetList {

    static private Map<PetCategory, List<Pet>> petMap;

    public static void prepareMap() {
        petMap = new HashMap<>();
        petMap.put(PetCategory.DOG, new ArrayList<>());
        petMap.put(PetCategory.CAT, new ArrayList<>());
        petMap.put(PetCategory.PARROT, new ArrayList<>());
        readFromFilePET();
    }

    public static void readFromFilePET() {
        try {
            FileReader fr = new FileReader("src\\Data\\pet.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(",");
                try {
                    if (txt.length != 5) {
                        throw new Exception();
                    }
                    Pet newpet = new Pet();
                    newpet.setPetID(txt[0]);
                    newpet.setDescription(txt[1]);
                    newpet.setImportDate(txt[2]);
                    newpet.setUnitPrice(Integer.parseInt(txt[3]));
                    newpet.setCategory(PetCategory.valueOf(txt[4].toUpperCase()));
                    addnewPet(newpet);
                } catch (Exception e) {
                }
            }
            br.close();
            fr.close();
        } catch (IOException | NumberFormatException e) {
        }
    }

    public static void addPet() {
        Pet newpet = new Pet();
        newpet.input();
        addnewPet(newpet);
    }

    public static void addnewPet(Pet pet) {

        List<Pet> newPetList = petMap.get(pet.getCategory());

        if (newPetList == null) {

            newPetList = new ArrayList();

            petMap.put(pet.getCategory(), newPetList);

        }
        newPetList.add(pet);

    }

    public static Util.CheckUnique checkUniqueId = new Util.CheckUnique() {
        @Override
        public boolean check(String checkedId) {
            for (Map.Entry<PetCategory, List<Pet>> entry : petMap.entrySet()) {
                List<Pet> value = entry.getValue();
                for (Pet pet : value) {
                    if (pet.getPetID().equals(checkedId)) {
                        return false;
                    }
                }
            }
            return true;
        }
    };

    public static Pet findPetById(String inputId) {
        Pet foundPet = null;
        for (Map.Entry<PetCategory, List<Pet>> entry : petMap.entrySet()) {
            java.lang.Object key = entry.getKey();
            List<Pet> value = entry.getValue();
            for (Pet pet : value) {
                if (pet.getPetID().equals(inputId)) {
                    foundPet = pet;
                    break;
                }
            }

        }
        if (foundPet == null) {
            System.out.println("\nNot found any pet have id " + inputId + "\n");
        }
        return foundPet;
    }

    public static void updatePet() {
        String inputId = Util.inputString("ID", "not empty");
        Pet checkID = findPetById(inputId);
        if (checkID != null) {
            checkID.update();
            System.out.println("Updated order!");
        }
    }

    public static void findPet() {
        String inputId = Util.inputString("ID", "not empty");
        Pet checkID = findPetById(inputId);
        if (checkID != null) {
            System.out.println(checkID.toString());
        }
    }

    public static void deletePet() {
        String inputId = Util.inputString("ID", "not empty");
        boolean found = false;
        for (Map.Entry<PetCategory, List<Pet>> entry : petMap.entrySet()) {
            List<Pet> pet = entry.getValue();
            Iterator<Pet> iterator = pet.iterator();
            while (iterator.hasNext()) {
                Pet pet1 = iterator.next();
                if (pet1.getPetID().equals(inputId)) {
                    iterator.remove();
                    found = true;
                }
            }
        }
        if (found) {
            System.out.println("Deleted ID: " + inputId);
        } else {
            System.out.println("Can't find ID: " + inputId);
        }
    }

    public static void savePetToFile() {
        try {
            FileWriter fw = new FileWriter("src\\Data\\pet.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Map.Entry<PetCategory, List<Pet>> entry : petMap.entrySet()) {
                java.lang.Object key = entry.getKey();
                List<Pet> value = entry.getValue();
                for (Pet pet : value) {
                    StringBuilder sb = new StringBuilder();
                    bw.newLine();
                    sb.append(pet.getPetID()).append(",")
                            .append(pet.getDescription()).append(",")
                            .append(pet.getImportDate()).append(",")
                            .append(pet.getUnitPrice()).append(",")
                            .append(pet.getCategory());
                    String result = sb.toString();
                    bw.write(result);
                }
            };
            bw.close();
            fw.close();
        } catch (IOException e) {
        }
        System.out.println("Saved  to the files");
    }

}
