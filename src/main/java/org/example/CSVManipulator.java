package org.example;

import java.io.*;
import java.util.*;

public class CSVManipulator {
    private List<String> persons = new ArrayList<>();
    private StringBuilder result = new StringBuilder();
    private final String COMMA = ",";
    private final String INPUT_FILE_NAME = "CSVfile.csv";
    private final String OUTPUT_FILE_NAME = "CSVfile1.csv";
    private final String COLON = ": ";

    /**
     * Importing the file that needs to be modified.
     */
    public void readFile() {
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE_NAME));
            while ((line = reader.readLine()) != null) {
                persons.add(Arrays.toString(line.split(COMMA)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Start file modification according to the requirements.
     */
    public void modifyFile() {
        // remove the header
        persons.remove(0);
        // sort list alphabetically
        Collections.sort(persons);

        checkDuplicates();
        formatOutput();
        writeFile();
    }

    /**
     * Checking if the CSV file contains any duplicates, in case the file contains duplicate then remove it.
     */
    private void checkDuplicates() {
        for (int i = 0; i < persons.size(); i++) {
            for (int j = 1; j < persons.size(); j++) {
                if (i != j && persons.get(i).equals(persons.get(j))) {
                    persons.remove(persons.get(i));
                }
            }
        }
    }

    /**
     * Formatting the output and generating a new file.
     */
    private void formatOutput() {
        char letter = persons.get(0).charAt(1);
        result.append(letter).append(COLON);
        for (String person : persons) {
            if (person.charAt(1) != letter) {
                letter = person.charAt(1);
                result.append("\n").append(letter).append(COLON).append("\n").append(person);
            } else {
                result.append("\n").append(person);
            }
        }
    }

    /**
     * Write the result into new file.
     */
    private void writeFile() {
        try {
            Writer writer = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
            writer.write(result.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}