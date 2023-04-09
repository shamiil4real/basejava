package com.basejava.webapp;

import java.io.File;
import java.util.Objects;

public class MainFile {

    static void PrintFiles(File[] files) {
        for (File fail : files) {
            if (fail.isFile()) {
                System.out.println("    " + fail.getName());
            } else if (fail.isDirectory()) {
                System.out.println("/" + fail.getName());
                PrintFiles(Objects.requireNonNull(fail.listFiles()));
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
