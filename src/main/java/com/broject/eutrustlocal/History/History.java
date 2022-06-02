package com.broject.eutrustlocal.History;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;



public class History {

    private static final String path = "src/main/java/com/broject/eutrustlocal/History/History.bin";


    public static void binWriter(String criteria) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter( new FileOutputStream(path, true));

        byte[] criteriaBytes = criteria.getBytes();


        for (byte criteriaByte : criteriaBytes) {
            StringBuilder binary = new StringBuilder();
            int x = criteriaByte;
            int change;
            while (x > 0) {
                change = x % 2;
                x /= 2;
                binary.insert(0, change);
            }
            writer.print(binary + " ");

        }
        writer.println();
        writer.close();
    }

    public static ArrayList<String> binReader() throws FileNotFoundException {
        ArrayList<String> history = new ArrayList<>();
        Scanner in = new Scanner(new FileReader(path));
        while(in.hasNextLine()){
            String line = in.nextLine();
            history.add(binaryConverter(line));
        }

        in.close();

        if(history.size()>30){

            return new ArrayList<> (history.subList(history.size()-31, history.size()-1));

        }

        return history;
    }
    public static boolean emptyFile() throws FileNotFoundException {
        //return binReader().size()==0;

        Scanner in = new Scanner(new FileReader(path));
        return !in.hasNextLine();
    }
    public static String binArrayAccess( int i) throws FileNotFoundException {
        String out = binReader().get(i);
        return out;
    }
    private static String binaryConverter(String in){

        return  Arrays.stream(in.split(" "))
                .map(binary -> Integer.parseInt(binary, 2))
                .map(Character::toString)
                .collect(Collectors.joining());
    }

    public static void clearHistory() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter( new FileOutputStream(path, false));
        writer.close();
    }
}
