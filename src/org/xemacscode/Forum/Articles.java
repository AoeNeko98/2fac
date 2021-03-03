/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.Forum;

/**
 *
 * @author kais
 */
public class Articles {
    private int ID_Etab;
   private String Datepub;
   private String Discription;
   private int ID_art;
   private String Titre;

    public Articles(int ID_Etab, String Datepub, String Discription, int ID_art, String Titre) {
        this.ID_Etab = ID_Etab;
        this.Datepub = Datepub;
        this.Discription = Discription;
        this.ID_art = ID_art;
        this.Titre = Titre;
    }

    public Articles(int ID_art, String Titre,String Discription) {
        this.Discription = Discription;
        this.ID_art = ID_art;
        this.Titre = Titre;
    }
    

    public int getID_Etab() {
        return ID_Etab;
    }

    public String getDatepub() {
        return Datepub;
    }

    public String getDiscription() {
        return Discription;
    }

    public int getID_art() {
        return ID_art;
    }

    public String getTitre() {
        return Titre;
    }
   
   
   
    
}
