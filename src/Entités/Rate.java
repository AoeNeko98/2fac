/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

/**
 *
 * @author Toumi
 */
public class Rate {
    private int id_USER;
    private int id_Cours;
    private double rating;

    public Rate(int id_USER, int id_Cours, double rating) {
        this.id_USER = id_USER;
        this.id_Cours = id_Cours;
        this.rating = rating;
    }

    public int getId_USER() {
        return id_USER;
    }

    public void setId_USER(int id_USER) {
        this.id_USER = id_USER;
    }

    public int getId_Cours() {
        return id_Cours;
    }

    public void setId_Cours(int id_Cours) {
        this.id_Cours = id_Cours;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    
    
    
}
