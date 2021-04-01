/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

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
   private String commentaire;
   private int ID_Comnt ;
   private String UserName;
   private String DateComnt;
   private String Etat;
   

    public Articles(int ID_Etab, String Datepub, String Discription, int ID_art, String Titre) {
        this.ID_Etab = ID_Etab;
        this.Datepub = Datepub;
        this.Discription = Discription;
        this.ID_art = ID_art;
        this.Titre = Titre;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public String getDateComnt() {
        return DateComnt;
    }

    public void setDateComnt(String DateComnt) {
        this.DateComnt = DateComnt;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setID_Etab(int ID_Etab) {
        this.ID_Etab = ID_Etab;
    }

    public void setDatepub(String Datepub) {
        this.Datepub = Datepub;
    }

    public void setDiscription(String Discription) {
        this.Discription = Discription;
    }

    public void setID_art(int ID_art) {
        this.ID_art = ID_art;
    }

    public void setTitre(String Titre) {
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

    public Articles(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Articles() {
    }

    public int getID_Comnt() {
        return ID_Comnt;
    }

    public void setID_Comnt(int ID_Comnt) {
        this.ID_Comnt = ID_Comnt;
    }
   
   
   
    
}
