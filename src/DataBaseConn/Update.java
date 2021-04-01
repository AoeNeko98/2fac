/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseConn;

import java.sql.Statement;
import static DataBaseConn.Connections.getConnection;

/**
 *
 * @author iHoussem
 */
public class Update {
    public static void executeUpdate(String query) {
        java.sql.Connection conn =getConnection();
         Statement       st;
        try{
            st=conn.createStatement();
            st.executeUpdate(query);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
