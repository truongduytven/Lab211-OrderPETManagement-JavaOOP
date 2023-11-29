/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Acer
 */
public class listPetToBuy {

    private Pet pet;
    private int quanlity;

    public listPetToBuy() {
    }

    public listPetToBuy(Pet pet, int quanlity) {
        this.pet = pet;
        this.quanlity = quanlity;
    }

    public Pet getPet() {
        return pet;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

}
