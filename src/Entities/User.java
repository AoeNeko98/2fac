/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author skand
 */
public class User {
    private int id;
    private String nom;
    private String prenom;
    private String password;
    private String role;
    private String email;
    private String addresse;
    private Date date_naissance;

    public User(int id, String nom, String prenom, String password, String role, String email, String addresse,Date date_naissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.role = role;
        this.email = email;
        this.addresse = addresse;
        this.date_naissance=date_naissance;
    }

    public User(String nom, String prenom, String password, String role, String email, String addresse,Date date_naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.role = role;
        this.email = email;
        this.addresse = addresse;
        this.date_naissance=date_naissance;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", role=" + role + ", email=" + email + ", addresse=" + addresse + ", date_naissance=" + date_naissance + '}';
    }

    public User() {
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    
    
    
    
    
    
    
    
}
