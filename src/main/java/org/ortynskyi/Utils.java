package org.ortynskyi;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Utils {

    public static String getTabulation(final int deepness) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= deepness; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    public static String readInput() {
        System.out.print("Enter file\\dir path: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        while (input.isEmpty() || !Files.exists(Paths.get(input))) {
            System.out.print("Path not valid, try another one: ");
            input = scanner.nextLine().trim();
        }
        return input;
    }
}
