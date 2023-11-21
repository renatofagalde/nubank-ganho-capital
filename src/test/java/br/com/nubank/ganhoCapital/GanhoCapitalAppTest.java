package br.com.nubank.ganhoCapital;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GanhoCapitalAppTest {

    @InjectMocks
    private GanhoCapitalApp underTest;

    @Mock
    private Scanner scanner;

    @Test
    public void deve_processar_caso01() throws IOException {

        String result = "[{\"tax\": 0.00},{\"tax\": 0.00},{\"tax\": 0.00}]";
        result = result.replaceAll("\n", "");

        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(caso01().getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);

        GanhoCapitalApp.main(new String[0]);

        System.setIn(stdin);
        System.setOut(stdout);

        String outputText = byteArrayOutputStream.toString().replaceAll("\n", "");

        assertEquals(result, outputText);
    }

    @Test
    public void deve_processar_caso02() throws IOException {

        String result = "[{\"tax\": 0.00},{\"tax\": 10000.00},{\"tax\": 0.00}]";
        result = result.replaceAll("\n", "");

        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(caso02().getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);

        GanhoCapitalApp.main(new String[0]);

        System.setIn(stdin);
        System.setOut(stdout);

        String outputText = byteArrayOutputStream.toString().replaceAll("\n", "");

        assertEquals(result, outputText);
    }

    @Test
    public void deve_processar_caso01_e_caso02() throws IOException {

        String result = "[{\"tax\": 0.00},{\"tax\": 0.00},{\"tax\": 0.00}]\n" +
                "[{\"tax\": 0.00},{\"tax\": 10000.00},{\"tax\": 0.00}]";
        result = result.replaceAll("\n", "");

        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(caso01Caso02().getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);

        GanhoCapitalApp.main(new String[0]);

        System.setIn(stdin);
        System.setOut(stdout);

        String outputText = byteArrayOutputStream.toString().replaceAll("\n", "");

        assertEquals(result, outputText);
    }

    @Test
    public void deve_processar_caso03() throws IOException {

        String result = "[{\"tax\": 0.00},{\"tax\": 0.00},{\"tax\": 1000.00}]";
        result = result.replaceAll("\n", "");

        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(caso03().getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);

        GanhoCapitalApp.main(new String[0]);

        System.setIn(stdin);
        System.setOut(stdout);

        String outputText = byteArrayOutputStream.toString().replaceAll("\n", "");

        assertEquals(result, outputText);
    }

    @Test
    public void deve_processar_caso04() throws IOException {

        String result = "[{\"tax\": 0.00},{\"tax\": 0.00},{\"tax\": 0.00}]";
        result = result.replaceAll("\n", "");

        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(caso04().getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);

        GanhoCapitalApp.main(new String[0]);

        System.setIn(stdin);
        System.setOut(stdout);

        String outputText = byteArrayOutputStream.toString().replaceAll("\n", "");

        assertEquals(result, outputText);
    }

    @Test
    public void deve_processar_caso05() throws IOException {

        String result = "[{\"tax\": 0.00},{\"tax\": 0.00},{\"tax\": 0.00},{\"tax\": 10000.00}]";
        result = result.replaceAll("\n", "");

        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(caso05().getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);

        GanhoCapitalApp.main(new String[0]);

        System.setIn(stdin);
        System.setOut(stdout);

        String outputText = byteArrayOutputStream.toString().replaceAll("\n", "");

        assertEquals(result, outputText);
    }
    @Test
    public void deve_processar_caso06() throws IOException {

        String result = "[{\"tax\": 0.00},{\"tax\": 0.00},{\"tax\": 0.00},{\"tax\": 0.00},{\"tax\": 3000.00}]";
        result = result.replaceAll("\n", "");

        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(caso06().getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);

        GanhoCapitalApp.main(new String[0]);

        System.setIn(stdin);
        System.setOut(stdout);

        String outputText = byteArrayOutputStream.toString().replaceAll("\n", "");

        assertEquals(result, outputText);
    }

    @Test
    public void deve_processar_caso07() throws IOException {

        String result = "[{\"tax\": 0.00},{\"tax\": 0.00},{\"tax\": 0.00},{\"tax\": 0.00},{\"tax\": 3000.00},\n" +
                "{\"tax\": 0.00},{\"tax\": 0.00},{\"tax\": 3700.00},{\"tax\": 0.00}]";
        result = result.replaceAll("\n", "");

        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(caso07().getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);

        GanhoCapitalApp.main(new String[0]);

        System.setIn(stdin);
        System.setOut(stdout);

        String outputText = byteArrayOutputStream.toString().replaceAll("\n", "");

        assertEquals(result, outputText);
    }

    @Test
    public void deve_processar_caso08() throws IOException {

        String result = "[{\"tax\": 0.00},{\"tax\": 80000.00},{\"tax\": 0.00},{\"tax\": 60000.00}]";
        result = result.replaceAll("\n", "");

        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(caso08().getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);

        GanhoCapitalApp.main(new String[0]);

        System.setIn(stdin);
        System.setOut(stdout);

        String outputText = byteArrayOutputStream.toString().replaceAll("\n", "");

        assertEquals(result, outputText);
    }
    private String caso01() {
        return "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 100},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}]";
    }


    private String caso02() {
        return "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000}]";
    }
    private String caso01Caso02() {
        return "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 100},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}]\n" +
                "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000}]";
    }

    private String caso03() {
        return "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 3000}]";
    }
    private String caso04() {
        return "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000},\n" +
                "{\"operation\":\"buy\", \"unit-cost\":25.00, \"quantity\": 5000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 10000}]";
    }

    private String caso05() {
        return "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000},\n" +
                "{\"operation\":\"buy\", \"unit-cost\":25.00, \"quantity\": 5000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 10000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\": 5000}]";
    }
    private String caso06() {
        return "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":2.00, \"quantity\": 5000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\": 1000}]";
    }
    private String caso07() {
        return "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":2.00, \"quantity\": 5000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\": 1000},\n" +
                "{\"operation\":\"buy\", \"unit-cost\":20.00, \"quantity\": 10000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 5000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":30.00, \"quantity\": 4350},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":30.00, \"quantity\": 650}]";
    }

    private String caso08() {
        return "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":50.00, \"quantity\": 10000},\n" +
                "{\"operation\":\"buy\", \"unit-cost\":20.00, \"quantity\": 10000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":50.00, \"quantity\": 10000}]";
    }


}