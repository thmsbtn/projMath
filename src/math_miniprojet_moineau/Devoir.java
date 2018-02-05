/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math_miniprojet_moineau;

/**
 *
 * @author max
 */
public interface Devoir {
    void runExercice();
    
    static void print(String enonce, String reponse){
        System.out.println("* "+enonce+" :");
        System.out.println("\t _"+reponse);
    }
    
}
