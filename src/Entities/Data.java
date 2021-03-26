/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author iHoussem
 */
public class Data {
    double x;                                          
       double y;

    public Data( double x,double y) {
        this.x = x;
        this.y = y;
    }
    
   public static double interpolate(Data f[], double xi, int n) 
{ 
    double result = 0; // Initialize result 
  
    for (int i = 0; i < n; i++) 
    { 
       
        
        double term = f[i].y; 
        
       
            
            result += term;
            
        
  
        
        
        
    } 
  result=result/5;
Double  resultat =BigDecimal.valueOf(result)
    .setScale(3, RoundingMode.HALF_UP)
    .doubleValue();
  
    return  resultat; 
} 
   
}
