/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.sql.Blob;

/**
 *
 * @author Toumi
 */
public class Etablissement {
    private int id;
    private String Nom;
    private String Addresse;
    private String Discription;
    private Blob Cours;

    public Etablissement(int id, String Nom, String Addresse, String Discription, Blob Cours) {
        this.id = id;
        this.Nom = Nom;
        this.Addresse = Addresse;
        this.Discription = Discription;
        this.Cours = Cours;
    }

    public String getAddresse() {
        return Addresse;
    }

    public void setAddresse(String Addresse) {
        this.Addresse = Addresse;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String Discription) {
        this.Discription = Discription;
    }

    public Blob getCours() {
        return Cours;
    }

    public void setCours(Blob Cours) {
        this.Cours = Cours;
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
    
    
}
