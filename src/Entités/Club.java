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
public class Club {
    private int id;
    private String Nom;
    private String Description;
    private int id_etab;
    private String nom_etab;

    public Club(int id, String Nom, String Description, int id_etab, String nom_etab) {
        this.id = id;
        this.Nom = Nom;
        this.Description = Description;
        this.id_etab = id_etab;
        this.nom_etab = nom_etab;
    }

    public Club(int id, String Nom, String Description) {
        this.id = id;
        this.Nom = Nom;
        this.Description = Description;
    }
    

    public Club(int id, String Nom, String Description, String nom_etab) {
        this.id = id;
        this.Nom = Nom;
        this.Description = Description;
        this.nom_etab = nom_etab;
    }

    public String getNom_etab() {
        return nom_etab;
    }

    public void setNom_etab(String nom_etab) {
        this.nom_etab = nom_etab;
    }
    
    public Club(String Nom, String Description,int id_etab) {
        this.Nom = Nom;
        this.Description = Description;
        this.id_etab=id_etab;
    }

    public Club(int id, String Nom, String Description,int id_etab) {
        this.id = id;
        this.Nom = Nom;
        this.Description = Description;
        this.id_etab=id_etab;
    }

    public int getId_etab() {
        return id_etab;
    }

    public void setId_etab(int id_etab) {
        this.id_etab = id_etab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    
    
}
