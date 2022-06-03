package com.broject.eutrustlocal.History;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Kabir Bertan
 */

public class History {

    private static final String PATH = "src/main/java/com/broject/eutrustlocal/History/History.bin";

    /**
     * Takes criteria from query request and convert to binary code;
     * If History.bin doesn't exist, the file will be created automatically;
     * Then write the binary code in .bin file;
     *
     * @param criteria the criteria sheet from query;
     * @throws FileNotFoundException if there is problems with file reading
     */
    public static void binWriter(String criteria) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new FileOutputStream(PATH, true));

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

    /**
     * Scan all the history.bin file, convert the contents in String and add it to history String ArrayList
     * If history size is more than 30, it will return only the last 30 String added;
     *
     * @return _history the arraylist that contains the search history
     * @throws FileNotFoundException if there is problems with file reading
     */
    public static ArrayList<String> binReader() throws FileNotFoundException {

        ArrayList<String> history = new ArrayList<>();

        Scanner in = new Scanner(new FileReader(PATH));

        while (in.hasNextLine()) {
            String line = in.nextLine();
            history.add(binaryConverter(line));

        }


        in.close();

        if (history.size() > 30) {

            return new ArrayList<>(history.subList(history.size() - 30, history.size()));

        }
        return history;
    }

    /**
     * Check if .bin file is empty
     *
     * @return true if .bin file is empty, false if .bin file contains elements
     * @throws FileNotFoundException if there is problems with file reading
     */
    public static boolean emptyFile() throws FileNotFoundException {
        //return binReader().size()==0;

        Scanner in = new Scanner(new FileReader(PATH));
        return !in.hasNextLine();
    }

    /**
     * Convert the input String(binary code) to alphanumerical String output
     *
     * @param in the String(binary code) to convert
     * @return converted String
     */
    private static String binaryConverter(String in) {
        StringBuilder sb = new StringBuilder();
        String[] characters = in.split(" ");


        for (String character : characters) {
            int num = 0;
            char[] bytes = character.toCharArray();
            for (int i = bytes.length - 1; i >= 0; i--) {
                num += Integer.parseInt(bytes[i] + "") * Math.pow(2, bytes.length - i - 1);
            }
            sb.append((char) num);
        }


        return sb.toString();

    }

    /**
     * Clear history.bin file
     *
     * @throws FileNotFoundException if there is problems with file reading
     */
    public static void clearHistory() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new FileOutputStream(PATH, false));
        writer.close();
    }
}
