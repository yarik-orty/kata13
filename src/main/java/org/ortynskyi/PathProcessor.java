package org.ortynskyi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PathProcessor {

    private final List<Item> output = new ArrayList<>();
    private final CodeLineCounter counter;

    public PathProcessor(final CodeLineCounter counter) {
        this.counter = counter;
    }

    public List<Item> process(final Path path) throws IOException {
        processPath(path, 0);
        return output;
    }

    private int processPath(final Path path, final int deepness) throws IOException {
        final int generalCount;
        if (Files.isDirectory(path)) {
            final List<Path> dirItems = Files.list(path).collect(Collectors.toList());
            final Item item = new Item(path.getFileName().toString(), deepness);
            output.add(item);
            int dirLineCount = 0;
            for (Path pathItem : dirItems) {
                dirLineCount += processPath(pathItem, deepness + 1);
            }
            item.setLineCount(dirLineCount);
            generalCount = dirLineCount;
        } else {
            final List<String> lines = Files.lines(path).collect(Collectors.toList());
            final int linesCount = counter.count(lines);
            output.add(new Item(path.getFileName().toString(), deepness, linesCount));
            generalCount = linesCount;
        }
        return generalCount;
    }
}
