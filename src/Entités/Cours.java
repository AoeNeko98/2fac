/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

import java.sql.Blob;
import java.util.logging.Logger;

/**
 *
 * @author Toumi
 */
public class Cours {
    
    private int ID_Cours;
    private String Discription;
    private String nom_spec ;
    private String Coursname;
    private String Etabnom;
   private int id_etab;
    private double rate;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Cours(int ID_Cours, String Discription, String Coursname) {
        this.ID_Cours = ID_Cours;
        this.Discription = Discription;
        this.Coursname = Coursname;
    }
    
    

    public Cours(int ID_Cours, String Discription, String nom_spec, String Coursname, String Etabnom, double rate) {
        this.ID_Cours = ID_Cours;
        this.Discription = Discription;
        this.nom_spec = nom_spec;
        this.Coursname = Coursname;
        this.Etabnom = Etabnom;
        this.rate = rate;
    }
    

    public Cours(int ID_Cours, String Discription, String nom_spec, String Coursname, String Etabnom, int id_etab, double rate) {
        this.ID_Cours = ID_Cours;
        this.Discription = Discription;
        this.nom_spec = nom_spec;
        this.Coursname = Coursname;
        this.Etabnom = Etabnom;
        this.id_etab = id_etab;
        this.rate = rate;
    }
    

    public Cours(int ID_Cours, String Discription, String nom_spec, String Coursname, int id_etab) {
        this.ID_Cours = ID_Cours;
        this.Discription = Discription;
        this.nom_spec = nom_spec;
        this.Coursname = Coursname;
        this.id_etab = id_etab;
    }

    public Cours(int ID_Cours) {
        this.ID_Cours = ID_Cours;
    }

    public int getId_etab() {
        return id_etab;
    }

    public void setId_etab(int id_etab) {
        this.id_etab = id_etab;
    }

    public Cours(String Discription, String nom_spec, String Coursname, int id_etab) {
        this.Discription = Discription;
        this.nom_spec = nom_spec;
        this.Coursname = Coursname;
        this.id_etab = id_etab;
    }

    public Cours(int ID_Cours, String Discription, String nom_spec, String Coursname, String Etabnom) {
        this.ID_Cours = ID_Cours;
        this.Discription = Discription;
        this.nom_spec = nom_spec;
        this.Coursname = Coursname;
        this.Etabnom = Etabnom;
    }

    public String getEtabnom() {
        return Etabnom;
    }

    public void setEtabnom(String Etabnom) {
        this.Etabnom = Etabnom;
    }
   

    public Cours(int ID_Cours, String Discription, String nom_spec, String Coursname) {
        this.ID_Cours = ID_Cours;
        this.Discription = Discription;
        this.nom_spec = nom_spec;
        this.Coursname = Coursname;
    }

    public int getID_Cours() {
        return ID_Cours;
    }

    public void setID_Cours(int ID_Cours) {
        this.ID_Cours = ID_Cours;
    }

    @Override
    public String toString() {
        return "Cours{" + "ID_Cours=" + ID_Cours + ", Discription=" + Discription + ", nom_spec=" + nom_spec + ", Coursname=" + Coursname + ", Etabnom=" + Etabnom + ", rate=" + rate + '}';
    }

  


    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String Discription) {
        this.Discription = Discription;
    }

    public String getNom_spec() {
        return nom_spec;
    }

    public void setNom_spec(String nom_spec) {
        this.nom_spec = nom_spec;
    }

    public String getCoursname() {
        return Coursname;
    }

    public void setCoursname(String Coursname) {
        this.Coursname = Coursname;
    }
        
    
    
    
}
