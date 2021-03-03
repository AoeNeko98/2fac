/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

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
        return "Cours{" + "ID_Cours=" + ID_Cours + ", Discription=" + Discription + ", nom_spec=" + nom_spec + ", Coursname=" + Coursname + '}';
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
