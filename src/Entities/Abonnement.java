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
public class Abonnement {
    private int id;
    private int id_user;
    private int id_club;
    private Date date;
    
    private String club;
    private String username;

    public Abonnement() {
    }

    public Abonnement(int id, int id_user, int id_club, Date date) {
        this.id = id;
        this.id_user = id_user;
        this.id_club = id_club;
        this.date = date;
    }

    public Abonnement(int id_user, int id_club, Date date) {
        this.id_user = id_user;
        this.id_club = id_club;
        this.date = date;
    }

    public Abonnement(int id, int id_user, int id_club, Date date, String club,String username) {
        this.id = id;
        this.id_user = id_user;
        this.id_club = id_club;
        this.date = date;
        this.club = club;
        this.username=username;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_club() {
        return id_club;
    }

    public void setId_club(int id_club) {
        this.id_club = id_club;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
    
    
    
}
