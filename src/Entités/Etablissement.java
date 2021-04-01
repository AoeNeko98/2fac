/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

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
    private String pass;
    private int Num;
    private int etat;

    public Etablissement(int id, String Nom, String Addresse, String Discription, int Num, int etat) {
        this.id = id;
        this.Nom = Nom;
        this.Addresse = Addresse;
        this.Discription = Discription;
        this.Num = Num;
        this.etat = etat;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    

    public Etablissement(int id) {
        this.id = id;
    }

    public Etablissement(int id, String pass) {
        this.id = id;
        this.pass = pass;
    }
    
    

    public Etablissement(String Nom, String Addresse, String Discription, Blob Cours, String pass, int Num) {
        this.Nom = Nom;
        this.Addresse = Addresse;
        this.Discription = Discription;
        this.Cours = Cours;
        this.pass = pass;
        this.Num = Num;
    }

    public Etablissement(String Nom, String Addresse, String Discription, String pass, int Num) {
        this.Nom = Nom;
        this.Addresse = Addresse;
        this.Discription = Discription;
        this.pass = pass;
        this.Num = Num;
    }

    public long getNum() {
        return Num;
    }

    public void setNum(int Num) {
        this.Num = Num;
    }

    public Etablissement(int id, String Nom, String Addresse, String Discription, int Num) {
        this.id = id;
        this.Nom = Nom;
        this.Addresse = Addresse;
        this.Discription = Discription;
        this.Num = Num;
    }
    
    public Etablissement(String Nom, String Addresse, String Discription, Blob Cours, String pass) {
        this.Nom = Nom;
        this.Addresse = Addresse;
        this.Discription = Discription;
        this.Cours = Cours;
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    

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
