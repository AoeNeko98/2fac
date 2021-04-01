/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entités.Etablissement;
import java.sql.SQLException;

/**
 *
 * @author Toumi
 */
public interface IServiceEtablissement {
    
     public void Ajouter(Etablissement e) throws SQLException;
     public void Update(Etablissement e) throws SQLException;
     public void Supprime(Etablissement e) throws SQLException;
     public void UpdatePass(Etablissement e) throws SQLException;
    
}
