/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author skand
 */
public class club {
    private int id;
    private String nom;
    private String description;
    private Domaine domaine;
    private int places;
    
    private int placeDispo;

    public club() {
    }

    public club(int id, String nom, String description, Domaine domaine, int places) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.domaine = domaine;
        this.places = places;
    }

    public club(String nom, String description, Domaine domaine, int places) {
        this.nom = nom;
        this.description = description;
        this.domaine = domaine;
        this.places = places;
    }

    public club(int id, String nom, String description, Domaine domaine, int places, int placeDispo) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.domaine = domaine;
        this.places = places;
        this.placeDispo = placeDispo;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public int getPlaceDispo() {
        return placeDispo;
    }

    public void setPlaceDispo(int placeDispo) {
        this.placeDispo = placeDispo;
    }
    
    
    
    
    
    
}






















