//4558817
//03,02,2026
//E1  Prac vanue

import java.text.*;
import java.util.*;

public class HashingPractical {
    public static int N = 1<<20;
    
    static class KeyValuePair {
        String key;
        String value;
        
        KeyValuePair(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    
    public static void main(String args[]){
    
        // Generate the key-value pairs
        KeyValuePair[] data = setupData();
        int dataSize = 950000;

    DecimalFormat twoD = new DecimalFormat("0.00");
    DecimalFormat fourD = new DecimalFormat("0.0000");
    DecimalFormat fiveD = new DecimalFormat("0.00000");

    long start, finish;
    double runTime = 0, runTime2 = 0, time;
    double totalTime = 0.0;
    int n = N;
    int repetition, repetitions = 30;

    runTime = 0;
    for(repetition = 0; repetition < repetitions; repetition++) {
        start = System.currentTimeMillis();
            
        OpenHash openHash = new OpenHash();
        for (int i = 0; i < dataSize; i++) {
            openHash.insert(data[i].key, data[i].value);
        }

        ChainedHash chainedHash = new ChainedHash();
        for (int i = 0; i < dataSize; i++) {
            chainedHash.insert(data[i].key, data[i].value);
        }
            
        finish = System.currentTimeMillis();
                
        time = (double)(finish - start);
        runTime += time;
        runTime2 += (time*time); }

    double aveRuntime = runTime/repetitions;
    double stdDeviation = 
        Math.sqrt(runTime2 - repetitions*aveRuntime*aveRuntime)/(repetitions-1);

    System.out.printf("\n\n\fStatistics\n");
    System.out.println("________________________________________________");
    System.out.println("Total time   =           " + runTime/1000 + "s.");
    System.out.println("Total time\u00b2  =           " + runTime2);
    System.out.println("Average time =           " + fiveD.format(aveRuntime/1000)
                        + "s. " + '\u00B1' + " " + fourD.format(stdDeviation) + "ms.");
    System.out.println("Standard deviation =     " + fourD.format(stdDeviation));
    System.out.println("n            =           " + n);
    System.out.println("Average time / run =     " + fiveD.format(aveRuntime/n*1000) 
                        + '\u00B5' + "s. "); 

    System.out.println("Repetitions  =             " + repetitions);
    System.out.println("________________________________________________");
    System.out.println();
    System.out.println();
    }	
    
    // Setup method to generate key-value pairs
    public static KeyValuePair[] setupData() {
        KeyValuePair[] pairs = new KeyValuePair[N];
        Integer[] keys = new Integer[N];
        for (int i = 0; i < N; i++) {
            keys[i] = i;
        }
        
        Random random = new Random();
        for (int i = N - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Integer temp = keys[i];
            keys[i] = keys[j];
            keys[j] = temp;
        }
        
        for (int i = 0; i < N; i++) {
            String key = "key_" + keys[i];
            String value = String.valueOf(i + 1);
            pairs[i] = new KeyValuePair(key, value);
        }
        
        return pairs;
    }
}