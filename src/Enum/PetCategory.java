/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enum;

import java.util.Scanner;

/**
 *
 * @author Acer
 */
public enum PetCategory {

    CAT,
    DOG,
    PARROT;

    public static PetCategory inputPetCategory(boolean allowEmpty) {
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Category: ");
            PetCategory newCategory = null;
            String input = sc.nextLine().toUpperCase();
            if (allowEmpty && input.isEmpty()) {
                return newCategory;
            } else {
                {
                    try {
                        newCategory = PetCategory.valueOf(input);
                        return newCategory;
                    } catch (IllegalArgumentException e) {
                        System.out.println("The value is not exist!");
                    }
                }
            }
        } while (true);
    }
}
