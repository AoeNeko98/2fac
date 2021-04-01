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
public class Speciality {
    
    int ID_Etb;
    String Nom;
    String Discription;
    int ID_SPEC;
    float ScoreECO;
    float ScoreINFO;
    float ScoreLET;
    float ScoreMATH;
    float ScoreSc;
    float ScoreSPORT;
    float ScoreTECH;
    String Nom_Etab;
    String Bac_Type;
    float score;

    public Speciality() {
    }

    @Override
    public String toString() {
        return "Speciality{" + "ID_Etb=" + ID_Etb + ", Nom=" + Nom + ", Discription=" + Discription + ", ID_SPEC=" + ID_SPEC + ", ScoreECO=" + ScoreECO + ", ScoreINFO=" + ScoreINFO + ", ScoreLET=" + ScoreLET + ", ScoreMATH=" + ScoreMATH + ", ScoreSc=" + ScoreSc + ", ScoreSPORT=" + ScoreSPORT + ", ScoreTECH=" + ScoreTECH + ", Nom_Etab=" + Nom_Etab + ", Bac_Type=" + Bac_Type + ", score=" + score + '}';
    }

    public Speciality(String Nom, String Discription, float ScoreECO, float ScoreINFO, float ScoreLET, float ScoreMATH, float ScoreSc, float ScoreSPORT, float ScoreTECH) {
        this.Nom = Nom;
        this.Discription = Discription;
        this.ScoreECO = ScoreECO;
        this.ScoreINFO = ScoreINFO;
        this.ScoreLET = ScoreLET;
        this.ScoreMATH = ScoreMATH;
        this.ScoreSc = ScoreSc;
        this.ScoreSPORT = ScoreSPORT;
        this.ScoreTECH = ScoreTECH;
    }

    public Speciality(int ID_Etb, int ID_SPEC) {
        this.ID_Etb = ID_Etb;
        this.ID_SPEC = ID_SPEC;
    }

 
    
    public int getID_Etb() {
        return ID_Etb;
    }

    public void setID_Etb(int ID_Etab) {
        this.ID_Etb = ID_Etab;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String Discription) {
        this.Discription = Discription;
    }

    public int getID_SPEC() {
        return ID_SPEC;
    }

    public void setID_SPEC(int ID_SPEC) {
        this.ID_SPEC = ID_SPEC;
    }

    public float getScoreECO() {
        return ScoreECO;
    }

    public void setScoreECO(float ScoreECO) {
        this.ScoreECO = ScoreECO;
    }

    public float getScoreINFO() {
        return ScoreINFO;
    }

    public void setScoreINFO(float ScoreINFO) {
        this.ScoreINFO = ScoreINFO;
    }

    public float getScoreLET() {
        return ScoreLET;
    }

    public void setScoreLET(float ScoreLET) {
        this.ScoreLET = ScoreLET;
    }

    public float getScoreMATH() {
        return ScoreMATH;
    }

    public void setScoreMATH(float ScoreMATH) {
        this.ScoreMATH = ScoreMATH;
    }

    public float getScoreSc() {
        return ScoreSc;
    }

    public void setScoreSc(float ScoreSc) {
        this.ScoreSc = ScoreSc;
    }

    public float getScoreSPORT() {
        return ScoreSPORT;
    }

    public void setScoreSPORT(float ScoreSPORT) {
        this.ScoreSPORT = ScoreSPORT;
    }

    public float getScoreTECH() {
        return ScoreTECH;
    }

    public void setScoreTECH(float ScoreTECH) {
        this.ScoreTECH = ScoreTECH;
    }

    public String getNom_Etab() {
        return Nom_Etab;
    }

    public void setNom_Etab(String Nom_Etab) {
        this.Nom_Etab = Nom_Etab;
    }

    public String getBac_Type() {
        return Bac_Type;
    }

    public void setBac_Type(String Bac_Type) {
        this.Bac_Type = Bac_Type;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
    
    
    
    
}
