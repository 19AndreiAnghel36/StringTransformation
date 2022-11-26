package org.example;

public class Main {
    public static void main(String[] args) {
        CSVManipulator csvManipulator = new CSVManipulator();
        csvManipulator.readFile();
        csvManipulator.modifyFile();
    }
}