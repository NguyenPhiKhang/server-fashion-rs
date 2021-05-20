package com.khangse616.serverfashionrs.models;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class maintest {
//    public static void main(String[] args) throws IOException {
//        Scanner scan = new Scanner(System.in);
//        int count = 0;
//        TfidfCalculation TfidfObj = new TfidfCalculation();
//        File folder = new File(scan.nextLine());
//        // loops over files available in the path except for hidden files.
//        File[] listOfFiles = folder.listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File file) {
//                return !file.isHidden();
//            }
//        });
//        int noOfDocs = listOfFiles.length;
//        //containers for documents and their properties required to calculate final score
//        DocumentProperties[] docProperties = new DocumentProperties[noOfDocs];
//
//
//        // Get wordcount from file and calculate TermFrequency
//        for (File file : listOfFiles) {
//            if (file.isFile()) {
//                docProperties[count] = new DocumentProperties();
//                HashMap<String, Integer> wordCount = TfidfObj.getTermsFromFile(file.getAbsolutePath(), count, folder);
//                docProperties[count].setWordCountMap(wordCount);
//                HashMap<String, Double> termFrequency = TfidfObj.calculateTermFrequency(docProperties[count].getWordCountMap());
//                docProperties[count].setTermFreqMap(termFrequency);
//                count++;
//            }
//        }
//        //calculating InverseDocument frequency
//        HashMap<String, Double> inverseDocFreqMap = TfidfObj.calculateInverseDocFrequency(docProperties);
//
//        //Calculating tf-idf
//        count = 0;
//        for (File file : listOfFiles) {
//            if (file.isFile()) {
//                HashMap<String, Double> tfIDF = new HashMap<>();
//                double tfIdfValue = 0.0;
//                double idfVal = 0.0;
//                HashMap<String, Double> tf = docProperties[count].getTermFreqMap();
//                Iterator itTF = tf.entrySet().iterator();
//                while (itTF.hasNext()) {
//                    Map.Entry pair = (Map.Entry) itTF.next();
//                    double tfVal = (Double) pair.getValue();
//                    if (inverseDocFreqMap.containsKey((String) pair.getKey())) {
//                        idfVal = inverseDocFreqMap.get((String) pair.getKey());
//                    }
//                    tfIdfValue = tfVal * idfVal;
//                    tfIDF.put((pair.getKey().toString()), tfIdfValue);
//                }
//                int fileNameNumber = (count + 1);
//                String OutPutPath = folder.getAbsolutePath() + "/csvOutput" + file.getName() + fileNameNumber + ".csv";
//                TfidfObj.outputAsCSV(tfIDF, OutPutPath);
//                count++;
//            }
//        }
//    }
}
