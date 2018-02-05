/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math_miniprojet_moineau;

import java.lang.reflect.Array;
import java.util.*;

import static java.util.Collections.shuffle;

/**
 *
 * @author max
 */
public class Projet implements Devoir{
    public Projet() {
        //rien
    }

        @Override
    public void runExercice() {
        String enonce1 = "Ecrire un programme qui génère une liste aléatoire (sur deux exécutions, la liste est en général\n" +
"différente) de 100 nombres réels (flottants/décimaux avec deux chiffres maximums derrière la\n" +
"virgule, qui ne sont en général pas tous des entiers) dans l’intervalle [ 0 ; 10 [, puis écrit dans un\n" +
"fichier ou sort en console :";
        
        
        String enonce2 = "Ecrire un programme qui choisit un mot de 7 lettres de façon aléatoire (sur deux exécutions, le\n" +
"mot est en général différent) qui n’utilise que les lettres A, B, C, et D, puis écrit dans un fichier\n" +
"ou sort en console :\n" +
"Première ligne le mot de 7 lettres obtenu.\n" +
"Chacune des lignes suivantes une anagramme de ce mot";
        
        
        Devoir.print(enonce1, exerciceUn(6));
        Devoir.print(enonce2, exerciceDeux());

    }
    
    private String exerciceDeux(){
        String reponse="";
        ArrayList<String> listToTranslate;
        char[] word = choisirUnMotDe7lettres();
        listToTranslate = new ArrayList(makeEveryPossibility(word));
        reponse += listToString(listToTranslate);
        return reponse;
    }

    private ArrayList<String> makeEveryPossibility(char[] word) {
        HashMap<Character, Integer> occurences = new HashMap<>();
        for(char c : word){
            if(occurences.containsKey(c))
                occurences.put(c, occurences.get(c)+1);
            else
                occurences.put(c, 1);
        }
        TreeSet<String> annagrammes = new TreeSet<>();//assure de l'unicité des résultats

        //formule du cours
        int somethingaboutoccurences = 1;

        for(char c : occurences.keySet()){
           int nb= occurences.get(c);
           somethingaboutoccurences*=fact(nb);
        }
        //donne le nombre de combinaisons attendues
        int nb_max = fact(word.length)/somethingaboutoccurences;

        //la seule raison de l'existence de ça ici truc c'est pour randomizer
        Stack<Character> randomrandomrandom = new Stack<>();
        while(annagrammes.size()<nb_max){
            String randomWord ="";
            //pour peupler la stack sur laquelle on shuffle...
            for(int x=0; x<word.length;x++) randomrandomrandom.add(word[x]);
            shuffle(randomrandomrandom);
            //pour créer des mots random
            for(int l =0; l<word.length;l++){
                randomWord += randomrandomrandom.pop();
            }

            annagrammes.add(randomWord);//les doublons ne sont pas ajoutés
        }
        return new ArrayList<>(annagrammes);
    }


    private int fact(int nb) {
        int res=1;
        for(int i = nb; i>1; i--){
            res*=i;
        }
        return res;
    }

    private String listToString(ArrayList<String> listToTranslate){
        String res ="";
        for(String s : listToTranslate)
            res+=s+"\n";
       
        return res;
    }
    private String exerciceUn(int tailleDeListe){
        String reponse="";
        List<Double> population = new ArrayList<>();
        for(int i = 0; i<tailleDeListe; i++){
            Double rand = new Double(((int)(Math.random()*1000))/100.0);
            
            population.add(rand);//Dirty way to have %f2
            
        }

        HashMap<Double, Integer> effMap = new HashMap();
        

        for(Double occurence : population){
            if(effMap.containsKey(occurence)){
                 effMap.put(occurence, effMap.get(occurence)+1);
            }else{
                effMap.put(occurence, 1);
            }
        }
        //rien de pire que de faire de l'affichage comme ça en java
        
        reponse+="Tableau des valeurs/frequences tirées aléatoirement\n";
        //values
        for(Double key : effMap.keySet()){
            //this is
            reponse+= String.format("%,.2f",key)+" ;";
        } 
        //freqs
        reponse+="\n";
        for(Double key : effMap.keySet()){
            reponse+= String.format("%,.2f", effMap.get(key)/(double)tailleDeListe)+" ;";
        }
        
        reponse+= listerLesSommesEtFreqDEffectifDesIntervallesPasReguliers(effMap, (double) tailleDeListe);
        
        return reponse;
    }
    
    private String listerLesSommesEtFreqDEffectifDesIntervallesPasReguliers(HashMap<Double, Integer> effMap, double tailleListe){
        String rep = "\nrappel des intervalles : [ 0 ; 1 [, [ 1 ; 3 [, [ 3 ; 7 [, [ 7 ; 9 [ et [ 9 ; 10 [";
        int un=0, deux=0, trois=0, quatre=0, cinq=0;        
        double dun=0, ddeux=0, dtrois=0, dquatre=0, dcinq=0;

        for(Double key: effMap.keySet()){
            if(key<1.){
                un+= effMap.get(key);
            }else if(key<3.){
                deux+=effMap.get(key);
                
            }else if(key<7.){
                trois+=effMap.get(key);
            }else if(key<9.){
                quatre+=effMap.get(key);
            }else{
                cinq+=effMap.get(key);
            }
        }
        rep+= "\nlister Les Sommes D Effectif Des Intervalles";
        rep+= "\n[ 0 ; 1 [ -> "+un;
        rep+= "\n[ 1 ; 3 [ -> "+deux;
        rep+= "\n[ 3 ; 7 [ -> "+trois;
        rep+= "\n[ 7 ; 9 [ -> "+quatre;
        rep+= "\n[ 9 ; 10 [ -> "+cinq;
        
        rep+= "\nla liste des fréquences de ces mêmes intervalles, toujours séparés par une espace.";
        
        rep+= "\n[ 0 ; 1 [ -> "+String.format("%,.2f",(double)un/tailleListe*100.+"%");
        rep+= "\n[ 1 ; 3 [ -> "+String.format("%,.2f",(double)deux/tailleListe*100.+"%");
        rep+= "\n[ 3 ; 7 [ -> "+String.format("%,.2f",(double)trois/tailleListe*100.+"%");
        rep+= "\n[ 7 ; 9 [ -> "+String.format("%,.2f",(double)quatre/tailleListe*100.+"%");
        rep+= "\n[ 9 ; 10 [ -> "+String.format("%,.2f",(double)cinq/tailleListe*100.+"%");
        
        
        rep+= "\na liste des densités de ces mêmes intervalles, toujours séparés par une espace.";

        rep+= "\n[ 0 ; 1 [ -> "+String.format("%,.2f",(double)un/tailleListe);
        rep+= "\n[ 1 ; 3 [ -> "+String.format("%,.2f",(double)deux/tailleListe);
        rep+= "\n[ 3 ; 7 [ -> "+String.format("%,.2f",(double)trois/tailleListe);
        rep+= "\n[ 7 ; 9 [ -> "+String.format("%,.2f",(double)quatre/tailleListe);
        rep+= "\n[ 9 ; 10 [ -> "+String.format("%,.2f",(double)cinq/tailleListe);
        
        
        return rep;
    }

    private char[] choisirUnMotDe7lettres() {
        Random r = new Random();
        char[] tab = new char[7];
        String toPrint = " * exercice 2 : ";
        for(int i=0; i<7;i++){
            //tab[i] = (char)(r.nextInt(26) + 'a');
            tab[i] = (char)(r.nextInt(3) + 'a');
            toPrint+=tab[i];
        }
        System.out.println(toPrint);
        return tab;
    }

    private String randomize(char[] newword) {
        String randWord = "";
        char[] nope = {0,0,0,0,0,0,0};int i;
        
        while(randWord.length()<7){
            i = (int) ((Math.random()) * 6);
            if(nope[i]>0){
                continue;
            } else {
                randWord=randWord+newword[i]+"";
                nope[i] = 1;
            }
        }
        return randWord;
    }

}

