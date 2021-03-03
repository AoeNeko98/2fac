/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;
import java.util.Date;

/**
 *
 * @author Amine
 */
public class User {
    private int ID_User;
    private String Nom;
    private String Prenom;
    private String Email;
    private String Date;
    private String Password;
    private String Status;
    private String Adress;

    public User(int ID_User, String Nom, String Prenom, String Email, String Date, String Password, String Status) {
        this.ID_User = ID_User;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.Date = Date;
        this.Password = Password;
        this.Status = Status;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String Adress) {
        this.Adress = Adress;
    }

    public User(String Nom, String Prenom, String Email, String Password, String Adress) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.Password = Password;
        this.Adress = Adress;
    }

    public User(String Email, String Password) {
        this.Email = Email;
        this.Password = Password;
    }

   

    public int getID_User() {
        return ID_User;
    }

    public void setID_User(int ID_User) {
        this.ID_User = ID_User;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "User{" + "ID_User=" + ID_User + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Email=" + Email + ", Date=" + Date + ", Password=" + Password + ", Status=" + Status + '}';
    }

    
}
