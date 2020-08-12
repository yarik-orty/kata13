package org.ortynskyi;

import java.util.List;

public class CodeLineCounter {

    public int count(final List<String> lines) {
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
}
