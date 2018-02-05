/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math_miniprojet_moineau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author max
 */
public class DM implements Devoir{

    public DM() {
    }

    @Override
    public void runExercice() {
        String enonce1 = "Ecrire un programme qui génère une liste aléatoire (sur deux exécutions, la liste est en général\n" +
"différente) de 100 nombres réels (flottants/décimaux avec deux chiffres maximums derrière la\n" +
"virgule, qui ne sont en général pas tous des entiers) dans l’intervalle [ 0 ; 10 [, puis écrit dans un\n" +
"fichier ou sort en console :";
        Devoir.print(enonce1, exerciceUn(100));
    }
    
    private String exerciceUn(int tailleDeListe){
        String reponse="";
        List<Double> population = new ArrayList<>();
        for(int i = 0; i<tailleDeListe; i++){
            population.add(new Double(((int)(Math.random()*1000))/100));//Dirty way to have %f2
        }
        HashMap<Double, Double> frequencyMap = new HashMap();
        double cran = (1/population.size());
        for(Double occurence : population){
            if(frequencyMap.containsKey(occurence)){
                 frequencyMap.put(occurence, frequencyMap.get(occurence)+cran);
            }else{
                frequencyMap.put(occurence, cran);
            }
        }
        
        for(Double key : frequencyMap.keySet()){
            reponse+= "Valeur ["+key+"]: freq ["+ frequencyMap.get(key)+"]";
        }
        
        return reponse;
    }
    
    
}
