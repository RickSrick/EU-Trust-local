package com.broject.eutrustlocal.History;

import com.broject.eutrustlocal.Creation.DataArchive;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class History
 *
 * @author Kabir Bertan
 */
public class History {

    private static History instance = null;

    private History() {

        try {
            (new File(PATH)).createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getCause());
        }

    }

    private static History newHistory() {

        if (instance == null) instance = new History();

        return instance;

    }

    private static final String PATH = "History.bin";

    /**
     * Takes criteria from query request and convert to binary code;
     * If History.bin doesn't exist, the file will be created automatically;
     * Then write the binary code in .bin file;
     *
     * @param criteria the criteria sheet from query;
     * @throws FileNotFoundException if there is problems with file reading
     */
    /**
     * Method used to add a criteria to the History file
     *
     * @param criteria the criteria to be added
     * @throws FileNotFoundException If there are problems with the History file
     */
    public static void binWriter(String criteria) throws FileNotFoundException {

        newHistory();

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
    /**
     * Method used to read from the History file
     * Reads all the file and returns only the last 30 criteria sheets found
     *
     * @return an ArrayList containing the last 30 searches as criteria sheets
     * @throws FileNotFoundException If there are problems with the History file
     */
    public static ArrayList<String> binReader() throws FileNotFoundException {

        newHistory();

        ArrayList<String> history = new ArrayList<>();
        Scanner in = new Scanner(new FileReader(PATH));
        String line;

        while (in.hasNextLine()) {
            line = in.nextLine();
            history.add(binaryConverter(line));
        }

        in.close();

        if (history.size() > 30) return new ArrayList<>(history.subList(history.size() - 30, history.size()));

        return history;

    }
    /**
     * Check if .bin file is empty
     *
     * @return true if .bin file is empty, false if .bin file contains elements
     * @throws FileNotFoundException if there is problems with file reading
     */
    /**
     * Method that checks if the history file is empty or not
     *
     * @return true/false if the history file is empty or not
     * @throws FileNotFoundException If there are problems with the History file
     */
    public static boolean isEmpty() throws FileNotFoundException {

        newHistory();

        return !(new Scanner(new FileReader(PATH))).hasNextLine();

    }
    /**
     * Clear history.bin file
     *
     * @throws FileNotFoundException if there is problems with file reading
     */
    /**
     * Method used to clear the History file
     *
     * @throws FileNotFoundException If there are problems with the History file
     */
    public static void clearHistory() throws FileNotFoundException {

        newHistory();

        PrintWriter writer = new PrintWriter(new FileOutputStream(PATH, false));
        writer.close();

    }
    /**
     * Convert the input String(binary code) to alphanumerical String output
     *
     * @param in the String(binary code) to convert
     * @return converted String
     */
    //method that converts Binary to String
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

}
