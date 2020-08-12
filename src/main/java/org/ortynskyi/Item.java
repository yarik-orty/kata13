package org.ortynskyi;

public class Item {

    private final String name;
    private final int deepness;
    private int lineCount;

    public Item(final String name, final int deepness) {
        this.name = name;
        this.deepness = deepness;
    }

    public Item(final String name, final int deepness, final int lineCount) {
        this.name = name;
        this.deepness = deepness;
        this.lineCount = lineCount;
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

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }
}
