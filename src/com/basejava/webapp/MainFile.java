package com.basejava.webapp;

import java.io.File;
import java.util.Objects;

public class MainFile {

    static void PrintFiles(File[] files) {
        for (File file : files) {
            if (file.isFile()) {
                System.out.println("    " + file.getName());
            } else if (file.isDirectory()) {
                System.out.println("/" + file.getName());
                PrintFiles(Objects.requireNonNull(file.listFiles()));
            }
        }
    }

    public static void main(String[] args) {
        String filePath = "/home/user/Java/basejava/src";
        File file = new File(filePath);

        if (file.exists() && file.isDirectory()) {
            File[] listOfFiles = file.listFiles();
            System.out.println("/" + file.getName());
            PrintFiles(Objects.requireNonNull(listOfFiles));
        }
    }
}
