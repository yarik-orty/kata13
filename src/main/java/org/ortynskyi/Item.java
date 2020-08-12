package org.ortynskyi;

public class Item {

    private final String name;
    private final int deepness;
    private final boolean isDir;
    private int lineCount;

    public Item(final String name,
                final int deepness,
                final boolean isDir) {
        this.name = name;
        this.deepness = deepness;
        this.isDir = isDir;
    }

    public Item(final String name,
                final int deepness,
                final int lineCount,
                final boolean isDir) {
        this.name = name;
        this.deepness = deepness;
        this.lineCount = lineCount;
        this.isDir = isDir;
    }

    public String getName() {
        return name;
    }

    public int getDeepness() {
        return deepness;
    }

    public int getLineCount() {
        return lineCount;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }
}
