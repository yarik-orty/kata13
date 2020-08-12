package org.ortynskyi;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.ortynskyi.Utils.getTabulation;
import static org.ortynskyi.Utils.readInput;

public class Main {

    public static void main(final String[] args) throws IOException {
        final PathProcessor processor = new PathProcessor(new CodeLineCounter());
        final List<Item> output = processor.process(Paths.get(readInput()));
        output.forEach(item -> System.out.println(
                getTabulation(item.getDeepness()) + item.getName() + " : " + item.getLineCount()));
    }
}
