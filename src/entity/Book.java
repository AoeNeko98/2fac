/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;



/**
 *
 * @author Aoe Neko 98
 */
public class Book {
    private int id;
    private String nom;
    private String type;
    private String discreption;
    private float prix;
    private String image;
    private Category category ;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    
    

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiscreption() {
        return discreption;
    }

    public void setDiscreption(String discreption) {
        this.discreption = discreption;
    }

    public Book(String nom, String type, String discreption) {
        this.nom = nom;
        this.type = type;
        this.discreption = discreption;
    }

    public Book(String nom, String type, String discreption, float prix, String image) {
        this.nom = nom;
        this.type = type;
        this.discreption = discreption;
        this.prix = prix;
        this.image = image;
    }
    

    public Book() {
        category = new Category();
    }
 @Override
    public String toString() {
        return "nom=" + nom + ", Type=" + type + ", discription=" + discreption + ", prix=" + prix +",image="+image+ '}';
    }
  


    
    
}
