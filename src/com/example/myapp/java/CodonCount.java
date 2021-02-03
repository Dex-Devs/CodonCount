package com.example.myapp.java;

import edu.duke.FileResource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CodonCount {
    
    private HashMap<String, Integer> dnaCodonMap;
    
    public CodonCount() { 
        dnaCodonMap = new HashMap<>();
    }
    
    public HashMap<String, Integer> getMap() {
        return this.dnaCodonMap;
    }
    
    // builds a codon map with its corresponding count
    private void buildCodonMap(int start, String dna) {
        dnaCodonMap.clear(); // clear map
        
        for(int i = start ; i < dna.length() ; i+=3) { 
            if(dna.substring(i).length() >= 3) { // checks if there are still 3 dna strand
                String dnaCodon = dna.substring(i, i+3); // get every 3 characters
                
                if(dnaCodonMap.containsKey(dnaCodon)) { // check if existing
                    dnaCodonMap.put(dnaCodon, dnaCodonMap.get(dnaCodon) + 1); // add 1 to codon count
                }else { // if it is not yet in the map of DNA Codons
                    dnaCodonMap.put(dnaCodon, 1); // add to map
                }
            }
        }
        
    }
    
    // get ket with max value
    private String getMostCommonCount() { 
        String mostCodonCount = null;
        
        // BASIC
//        int max = Collections.max(dnaCodonMap.values());
//        
//        for(String codon : dnaCodonMap.keySet()) {
//            if(dnaCodonMap.get(codon) == max) {
//                System.out.println("CODON WITH MAX COUNT: " + codon);
//            }
//        }

        // ADVANCE -- using Map,Entry<K, V> which returns a collection view of the map ; uses getKey to get Key and getValue to get the Value
        //         -- used lambda to compare values
        
        Map.Entry<String, Integer> maxEntry = Collections.max(dnaCodonMap.entrySet(), (Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) ->
            e1.getValue().compareTo(e2.getValue()));
        
        return maxEntry.getKey();
    }
    
    // printing all codons
    private void printCodonCounts(int start, int end) { 
        
        for(String codon : dnaCodonMap.keySet()) {
            if(dnaCodonMap.get(codon) >= start && dnaCodonMap.get(codon) <= end ) {
                System.out.println(codon + "\tOccurence:" +dnaCodonMap.get(codon));
            }
        }
    }
    
    public void codonTester() { 
        FileResource fr = new FileResource("com/example/myapp/data/dnamystery1.txt");
        
        String dnaStrand = null;
        for(String codon : fr.words()) {
            dnaStrand = codon;
        }
        
        buildCodonMap(1, dnaStrand);
        System.out.println("Total number of unique codons: " + dnaCodonMap.size());
        
        String commonCodon = getMostCommonCount();
        System.out.println("Most common codon count: " + commonCodon + "\tCount: " + dnaCodonMap.get(commonCodon));
        
        printCodonCounts(1,100);
        
    }
    
    public static void main(String [] args) {
        CodonCount cdnCount = new CodonCount();
        
        cdnCount.codonTester();
    }
}
