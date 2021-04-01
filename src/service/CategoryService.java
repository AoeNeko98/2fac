package service;


import Main.MyDB;
import entity.Category;
import iservice.ICategory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aoe Neko 98
 */
public class CategoryService implements ICategory{
    
    
    Connection conn ;

    public CategoryService() {
         this.conn = MyDB.getInstance().getConnexion();

    }

    @Override
    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> ls = new ArrayList<>();
        ResultSet rs ;
        Category category ;
        String sql ="SELECT * FROM categorie ;";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            while(rs.next()){
                category = new Category();
                
                 category.setNom(rs.getString("nom"));
                 category.setDescription(rs.getString("description"));
                 category.setId(rs.getInt("id"));
                 ls.add(category);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return ls ;
    }
    
}
