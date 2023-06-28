package org.itstep;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Task {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(new File("pom.xml"))) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                System.out.println(str);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    //Вариант Максима
    private static void readFileWithScanner() {
        try (InputStream in = new FileInputStream("pom.xml");
             Scanner scanner = new Scanner(in)) {
            String line;
            boolean hasLine;
            do {
                try {
                    line = scanner.nextLine();
                    System.out.println(line);
                    hasLine = true;
                } catch (NoSuchElementException ignored) {
                    hasLine = false;
                }
            } while (hasLine);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
