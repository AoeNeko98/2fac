/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

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
        double terms=f[i].y;
        for (int j = 0; j < n; j++) 
        { 
            if (j != i) 
                terms = terms*(xi - f[j].x) / (f[i].x - f[j].x); 
        } 
  
        
        result += term;
        
    } 
  
    return (result/5); 
} 
   
}
