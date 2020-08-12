package org.ortynskyi;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;

public class CodeLineCounterTest {

    private static final String FILE_1 = "test_kata13/dir1/First.java";
    private static final String FILE_2 = "test_kata13/dir2/sub_dir1/Regular.java";
    private static final String FILE_3 = "test_kata13/dir3/Third.java";
    private static final String FILE_4 = "test_kata13/dir3/Four.java";
    private static final String FILE_5 = "test_kata13/dir3/Five.java";

    private final CodeLineCounter counter = new CodeLineCounter();

    @Test
    public void regularCommentTest() throws IOException, URISyntaxException {
        URL url = getClass().getClassLoader().getResource(FILE_2);
        int actualCount = counter.count(Files.lines(Paths.get(
                Objects.requireNonNull(url).toURI())).collect(Collectors.toList()));
        int expectedCount = 26;

        Assert.assertEquals(actualCount, expectedCount);
    }

    @Test
    public void commentBlockTest() throws IOException, URISyntaxException {
        URL url = getClass().getClassLoader().getResource(FILE_4);
        int actualCount = counter.count(Files.lines(Paths.get(
                Objects.requireNonNull(url).toURI())).collect(Collectors.toList()));
        int expectedCount = 3;

        Assert.assertEquals(actualCount, expectedCount);
    }

    @Test
    public void javaDocBlockTest() throws IOException, URISyntaxException {
        URL url = getClass().getClassLoader().getResource(FILE_3);
        int actualCount = counter.count(Files.lines(Paths.get(
                Objects.requireNonNull(url).toURI())).collect(Collectors.toList()));
        int expectedCount = 12;

        Assert.assertEquals(actualCount, expectedCount);
    }

    @Test
    public void mixedCommentTest() throws IOException, URISyntaxException {
        URL url = getClass().getClassLoader().getResource(FILE_1);
        int actualCount = counter.count(Files.lines(Paths.get(
                Objects.requireNonNull(url).toURI())).collect(Collectors.toList()));
        int expectedCount = 9;

        Assert.assertEquals(actualCount, expectedCount);
    }

    @Test
    public void pathologicalCommentTest() throws IOException, URISyntaxException {
        URL url = getClass().getClassLoader().getResource(FILE_5);
        int actualCount = counter.count(Files.lines(Paths.get(
                Objects.requireNonNull(url).toURI())).collect(Collectors.toList()));
        int expectedCount = 5;

        Assert.assertEquals(actualCount, expectedCount);
    }
}
