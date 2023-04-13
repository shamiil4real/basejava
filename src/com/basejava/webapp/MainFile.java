package com.basejava.webapp;

import java.io.File;
import java.util.Objects;

public class MainFile {

    public static void printDirectory(File[] files, int tabs) {
        for (File file : files) {
            for (int i = 0; i < tabs; i++) {
                System.out.print("  ");
            }
            if (file.isFile()) {
                System.out.println("-" + file.getName());
            } else if (file.isDirectory()) {
                System.out.println("/" + file.getName());
                printDirectory(Objects.requireNonNull(file.listFiles()), ++tabs);
            }
        }
    }

    public static void main(String[] args) {
        String filePath = "src";
        File file = new File(filePath);

        if (file.exists() && file.isDirectory()) {
            File[] listOfFiles = file.listFiles();
            System.out.println("/" + file.getName());
            printDirectory(Objects.requireNonNull(listOfFiles), 1);
        }
    }
}
