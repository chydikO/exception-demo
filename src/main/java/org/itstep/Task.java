package org.itstep;

import java.io.*;
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
}
