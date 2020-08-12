package org.ortynskyi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private final List<Item> output = new ArrayList<>();

    public static void main(final String[] args) throws IOException {
        new Main().run();
    }

    public void run() throws IOException {
        final Path path = Paths.get(readInput());
        processPath(path, 0);
        printOutput();
    }

    public int processPath(final Path path, final int deepness) throws IOException {
        final int generalCount;
        if (Files.isDirectory(path)) {
            final List<Path> dirItems = Files.list(path).collect(Collectors.toList());
            final Item item = new Item(path.getFileName().toString(), deepness, true);
            output.add(item);
            int dirLineCount = 0;
            for (Path pathItem : dirItems) {
                dirLineCount += processPath(pathItem, deepness + 1);
            }
            item.setLineCount(dirLineCount);
            generalCount = dirLineCount;
        } else {
            final List<String> lines = Files.lines(path).collect(Collectors.toList());
            final int linesCount = countCodeLines(lines);
            output.add(new Item(path.getFileName().toString(), deepness, linesCount, false));
            generalCount = linesCount;
        }
        return generalCount;
    }

    public int countCodeLines(final List<String> lines) {
        int commentedCounter = 0;
        int commentedBlockCounter = 0;
        for (final String line : lines) {
            final String trimmed = line.trim();
            if (trimmed.endsWith("*/") || trimmed.contains("*///")) {
                commentedCounter += commentedBlockCounter + 1;
                commentedBlockCounter = 0;
                continue;
            }
            if (trimmed.startsWith("/*")) {
                commentedBlockCounter++;
                continue;
            }
            if (commentedBlockCounter > 0) {
                commentedBlockCounter++;
                continue;
            }
            if (trimmed.isEmpty() || trimmed.startsWith("//")) {
                commentedCounter++;
            }
        }
        return lines.size() - commentedCounter;
    }

    private void printOutput() {
        output.forEach(item -> System.out.println(
                getTabulation(item.getDeepness()) + item.getName() + " : " + item.getLineCount()));
    }

    private String getTabulation(final int deepness) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= deepness; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private String readInput() {
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
