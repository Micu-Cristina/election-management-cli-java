package electionProject;

import static org.junit.jupiter.api.Assumptions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class AppTest {
    @Test
    public void test0() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream("0\nA0 Alegeri Electorale 2025\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("Success. Election Alegeri Electorale 2025 was created")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test1() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n0\nA0 Alegeri Electorale 2025\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: An election with id A0")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test2() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream("0\nA0 Alegeri Electorale 2025\n1\nA0\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("Success. Election Alegeri Electorale 2025 has started")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test3() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream("0\nA0 Alegeri Electorale 2025\n1\nA1\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: No election with this id")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test4() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n1\nA0\n1\nA0\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: Election already started")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test5() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("Success. Constituency Bucuresti")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test6() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti Muntenia\n18\n"
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: A constituency named Bucuresti")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test7() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA1 Bucuresti Muntenia\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: No election with this id")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test8() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n2\nA0 Bucuresti Muntenia\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: Voting period has not started")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test9() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n3\nA0 Bucuresti\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("Success. Constituency Bucuresti")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test10() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n1\nA0\n3\nA0 Bucuresti\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: No constituency named Bucuresti")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test11() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n1\nA0\n3\nA1 Bucuresti\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: No election with this id")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test12() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n3\nA0 Bucuresti\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: Voting period has not started")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    //String setup = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n";
    //String end = "18\n";

    @Test
    public void test13() {

        String setup = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n";
        String end = "18\n";

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "4\nA0 1234567891234 45 Dumitru Florin Ionescu\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("Candidate Dumitru Florin Ionescu")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test14() {
        String setup = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n";
        String end = "18\n";

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "4\nA0 12345678912345 45 Dumitru Florin Ionescu\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: Invalid national ID")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test15() {
        String setup = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n";
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "4\nA0 1234567891234 34 Dumitru Florin Ionescu\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: Invalid age")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }


    @Test
    public void test16() {
        String setup = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n";
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "4\nA0 1234567891234 45 Dumitru Florin Ionescu\n"
                        + "4\nA0 1234567891234 45 Dumitru Florin Ionescu\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: Candidate Dumitru Florin Ionescu already has the same national ID")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test17() {
        String setup = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n";
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "4\nA1 1234567891234 45 Dumitru Florin Ionescu\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: No election with this id")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test18() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // creare alegeri, dar nu pornire
        String setup = "0\nA0 Alegeri Electorale 2025\n";
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "4\nA0 1234567891234 45 Dumitru Florin Ionescu\n"
                        + "18\n")
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: Voting period has not started")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test19() {
        String setup = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n";
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "4\nA0 1234567891234 45 Dumitru Florin Ionescu\n"
                        + "5\nA0 1234567891234\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("Candidate Dumitru Florin Ionescu")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test20() {
        String setup = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n";
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "5\nA0 1234567891234\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: No candidate with national ID 1234567891234")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test21() {
        String setup = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n";
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "5\nA1 1234567891234\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: No election with this id")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test22() {
        String setup = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n";
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("Voter Chipescu Ciprian")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test23() {
        String setup = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n";
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "6\nA0 Bucuresti 12345678912345 20 da Chipescu Ciprian\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: Invalid national ID")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test24() {
        String setup = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n";
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "6\nA0 Bucuresti 1234567891234 17 da Chipescu Ciprian\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: Invalid age")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test25() {
        String setup = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n";
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                        + "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: Voter Chipescu Ciprian already has the same national ID")) {
            assertTrue(true);
        } else {
            fail(output);
        }

    }

    @Test
    public void test26() {
        String setup = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n";
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                        + "6\nA0 Bucuresti2 1234567891234 20 da Chipescu Ciprian\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: No constituency named Bucuresti2")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test27() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup21 + setup22 + setup23 + "7\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "Candidates:";
        String expected2 = "Dumitru Florin Ionescu2 1234567891239 45";
        String expected3 = "Dumitru Florin Ionescu 1234567891238 45";
        if (output.contains(expected) && output.contains(expected2) && output.contains(expected3)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test28() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup21 + "7\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EMPTY: No candidates")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test29() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup21 + "7\nA1\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: No election with this id")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test30() {
        assertTrue(true);
    }

    @Test
    public void test31() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup21 + setup22 + setup23 + "8\nA0 Bucuresti\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "Voters in Bucuresti:";
        String expected2 = "Chipescu Ciprian 1234567891234 20";
        String expected3 = "Chipescu Ciprian2 1234567891235 20";
        if (output.contains(expected) && output.contains(expected2) && output.contains(expected3)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test32() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup21 + "8\nA0 Bucuresti\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EMPTY: No voters in Bucuresti")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test33() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup21 + "8\nA0 Bucuresti23\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: No constituency named Bucuresti2")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test34() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup21 + "8\nA1 Bucuresti\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("ERROR: No election with this id")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    // avem:
    // 2 circumscriptii:
    // Bucuresti cu 2 votanti: Chipescu Ciprian, Chipescu Ciprian2
    // Bucuresti2 cu 2 votanti: Chipescu Ciprian3, Chipescu Ciprian4
    // 2 candidati: Dumitru Florin Ionescu, Dumitru Florin Ionescu2

    @Test
    public void test35() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "9\nA0 Bucuresti 1234567891234 1234567891238\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "Chipescu Ciprian voted for Dumitru Florin Ionescu";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test36() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                        + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "FRAUD: Voter with national ID 1234567891234 attempted fraud. Vote was canceled";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test37() {
        String setup = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n";
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "9\nA0 Bucuresti2 1234567891234 1234567891238\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "FRAUD: Voter with national ID 1234567891234 attempted fraud. Vote was canceled";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test38() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "9\nA0 Bucuresti22 1234567891234 1234567891238\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "ERROR: No constituency named Bucuresti22";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test39() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "9\nA0 Bucuresti 1234567891230 1234567891238\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "attempted fraud. Vote was canceled";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test40() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "9\nA0 Bucuresti 1234567891234 1234567891231\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "ERROR: No candidate with national ID 1234567891231";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test41() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "9\nA1 Bucuresti 1234567891234 1234567891238\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "ERROR: No election with this id";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    // 10. Oprire alegeri
    // <id_alegeri>
    // Aceasta comanda va intoarce urmatoarele:
    // Caz Raspuns
    // Succes. S-au terminat alegerile <nume_alegeri>
    // Id alegeri invalid. ERROR: No election with this id
    // Alegerile nu sunt in stagiul iN_CURS. ERROR: Voting period has not started

    @Test
    public void test42() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "10\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "Success. Election Alegeri Electorale 2025 has ended";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test43() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "10\nA1\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "ERROR: No election with this id";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test44() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 nu Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 nu Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 nu Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 nu Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 nu Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 nu Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 nu Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        // terminam alegerile
        String setup42 = "10\nA0\n";

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup4 + setup42 + "11\nA0 Bucuresti\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "Vote report for Bucuresti:";
        String expected2 = "Dumitru Florin Ionescu2 1234567891239 - 1";
        String expected3 = "Dumitru Florin Ionescu 1234567891238 - 1";
        if (output.contains(expected) && output.contains(expected2) && output.contains(expected3)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test45() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        // terminam alegerile
        String setup42 = "10\nA0\n";

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + setup42 + "11\nA0 Bucuresti3\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "EMPTY: No votes cast in Bucuresti3";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test46() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        // terminam alegerile
        String setup42 = "10\nA0\n";

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup4 + setup42 + "11\nA0 Bucuresti22\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "ERROR: No constituency named Bucuresti22";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test47() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        // terminam alegerile
        String setup42 = "10\nA0\n";

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup4 + "11\nA1 Bucuresti\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "ERROR: No election with this id";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test48() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 nu Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 nu Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 nu Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 nu Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 nu Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 nu Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 nu Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        // terminam alegerile
        String setup42 = "10\nA0\n";

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup4 + setup42 + "12\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "National vote report:";
        String expected2 = "Dumitru Florin Ionescu2 1234567891239 - 3";
        String expected3 = "Dumitru Florin Ionescu 1234567891238 - 4";
        if (output.contains(expected) && output.contains(expected2) && output.contains(expected3)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test49() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        // terminam alegerile
        String setup42 = "10\nA0\n";

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + setup42 + "12\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "EMPTY: No votes cast nationwide";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test50() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup4 + "12\nA1\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "ERROR: No election with this id";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    // 13. Analiza detaliata per circumscriptie
    // <id_alegeri> <nume_circumscriptie>
    // EX: A1 Arges
    // !! Se afiseaza informatii analitice despre alegerile din circumscriptia
    // precizata, sub urmatorul format (toate acestea se vor scrie pe O SINGURa
    // linie):
    // in <nume_circumscriptie> au fost <nr_voturi_circumscriptie> voturi din
    // <nr_voturi_national>. Adica <procentaj>%. Cele mai multe voturi au fost
    // stranse de <CNP> <nume>. Acestea constituie <procentaj>% din voturile
    // circumscriptiei.

    // !! La procentaje, se va afisa doar partea intreaga.
    // Aceasta comanda va intoarce urmatoarele:
    // Caz Raspuns
    // Succes. in <nume_circumscriptie> au fost <nr_voturi_circumscriptie> voturi
    // din <nr_voturi_national>. Adica <procentaj>%. Cele mai multe voturi au fost
    // stranse de <CNP> <nume>. Acestea constituie <procentaj>% din voturile
    // circumscriptiei.
    // Nu sunt voturi GOL: Lumea nu isi exercita dreptul de vot in
    // <nume_circumscriptie>
    // Nu a fost gasit obiectul EROARE: Nu exista o circumscriptie cu numele
    // <nume_circumscriptie>
    // Alegerile nu se afla in stagiul corepunzator EROARE: inca nu s-a terminat
    // votarea
    // Id alegeri invalid. ERROR: No election with this id

    @Test
    public void test51() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 nu Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 nu Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 nu Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 nu Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 nu Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 nu Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 nu Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891239\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        // terminam alegerile
        String setup42 = "10\nA0\n";
        String setup50 = setup4 + setup42;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup50 + "13\nA0 Bucuresti\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "In Bucuresti there were 2 votes out of 7. That's 28%. Most votes were received by 1234567891239 Dumitru Florin Ionescu2. This represents 100% of the constituency votes.";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test52() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        // terminam alegerile
        String setup42 = "10\nA0\n";

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + setup42 + "13\nA0 Bucuresti3\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "EMPTY: No votes cast in Bucuresti3";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    // 14. Analiza detaliata pe plan national
    // <id_alegeri>
    // !! Lista va fi ordonata alfabetic, dupa numele regiunii
    // !! Totalul voturilor dintr-o regiune se va face adunand voturile din fiecare
    // circumscriptie.
    // !! Se afiseaza informatii analitice despre alegerile din Romania, sub
    // urmatorul format (toate acestea se vor scrie pe LINII SEPARATE), conform:

    // in Romania au fost <nr_voturi> voturi.
    // in <regiune1> au fost <nr_voturi_regiune1> voturi din <nr_voturi_national>.
    // Adica <procentaj>%. Cele mai multe voturi au fost stranse de <CNP> <nume>.
    // Acestea constituie <procentaj>% din voturile regiunii.
    // ...
    // in <regiuneN> au fost <nr_voturi_regiuneN> voturi din <nr_voturi_national>.
    // Adica <procentaj>%. Cele mai multe voturi au fost stranse de <CNP> <nume>.
    // Acestea constituie <procentaj>% din voturile regiunii.

    // Aceasta comanda va intoarce urmatoarele:
    // Caz Raspuns
    // Succes. in Romania au fost <nr_voturi> voturi.
    // in <regiune1> au fost <nr_voturi_regiune1> voturi din <nr_voturi_national>.
    // Adica <procentaj>%. Cele mai multe voturi au fost stranse de <CNP> <nume>.
    // Acestea constituie <procentaj>% din voturile regiunii.
    // ...
    // in <regiuneN> au fost <nr_voturi_regiuneN> voturi din <nr_voturi_national>.
    // Adica <procentaj>%. Cele mai multe voturi au fost stranse de <CNP> <nume>.
    // Acestea constituie <procentaj>% din voturile regiunii.
    // Nu sunt voturi EMPTY: No votes cast nationwide.
    // Alegerile nu se afla in stagiul corepunzator EROARE: inca nu s-a terminat
    // votarea
    // Id alegeri invalid. ERROR: No election with this id

    @Test
    public void test53() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 nu Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 nu Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 nu Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 nu Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 nu Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 nu Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 nu Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891239\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        // terminam alegerile
        String setup42 = "10\nA0\n";
        String setup50 = setup4 + setup42;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup50 + "14\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "There were 7 votes nationwide.";
        String expected2 = "In Muntenia2 there were 3 votes out of 7. That's 42%. Most votes were received by 1234567891238 Dumitru Florin Ionescu. This represents 66% of the region's votes.";
        String expected3 = "In Muntenia there were 4 votes out of 7. That's 57%. Most votes were received by 1234567891239 Dumitru Florin Ionescu2. This represents 75% of the region's votes.";
        if (output.contains(expected) && output.contains(expected2) && output.contains(expected3)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test54() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        // terminam alegerile
        String setup42 = "10\nA0\n";

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + setup42 + "14\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "EMPTY: No votes cast nationwide";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test55() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup4 + "14\nA1\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "ERROR: No election with this id";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    // 15. Rapoarte fraude
    // <id_alegeri>
    // !! Se afiseaza o lista cu toate fraudele comise. Ordinea de listare este LIFO
    // (Last In, First Out); ultimul venit, primul servit. Formatul este:
    // in <nume_circumscriptie>: <CNP> <nume>

    // Caz Raspuns
    // Succes. Reported frauds:
    // in <nume_circumscriptie>: <CNP> <nume>
    // in <nume_circumscriptie>: <CNP> <nume>
    // in <nume_circumscriptie>: <CNP> <nume>
    // Nu sunt fraude. EMPTY: No frauds reported
    // Alegerile nu se afla in stagiul corepunzator EROARE: inca nu s-a terminat
    // votarea
    // Id alegeri invalid. ERROR: No election with this id

    // 16. sterge alegeri
    // <id_alegeri>
    // !! Se sterg alegerile corespunzatoare si toate datele aferente.
    // Caz Raspuns
    // Succes. S-au sters alegerile <nume_alegeri>.
    // Id alegeri invalid. ERROR: No election with this id

    // 17. Listare alegeri
    // Comanda nu are parametrii.
    // Caz Raspuns
    // Succes. Elections:
    // <id_alegeri> <nume_alegeri>
    //
    // <id_alegeri> <nume_alegeri>
    // Nu sunt alegeri. EMPTY: No elections
    // 18. Iesire
    // Comanda nu are parametrii.

    // Comitem cateva voturi multiple

    @Test
    public void test56() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        // terminam alegerile
        String setup42 = "10\nA0\n";
        String setup60 = setup4 + setup42;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup60 + "15\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "Reported frauds:";
        String expected2 = "In Bucuresti: 1234567891234 Chipescu Ciprian";
        if (output.contains(expected) && output.contains(expected2)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test57() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        // terminam alegerile
        String setup42 = "10\nA0\n";

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + setup42 + "15\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "EMPTY: No frauds reported";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test58() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        // terminam alegerile
        String setup42 = "10\nA0\n";
        String setup60 = setup4 + setup42;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup60 + "16\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "Success. Election Alegeri Electorale 2025 was deleted.";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test59() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        // terminam alegerile
        String setup42 = "10\nA0\n";
        String setup60 = setup4 + setup42;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup60 + "16\nA1\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "ERROR: No election with this id";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test60() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        // terminam alegerile
        String setup42 = "10\nA0\n";
        String setup60 = setup4 + setup42;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup60 + "17\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "Elections:";
        String expected2 = "A0 Alegeri Electorale 2025";
        if (output.contains(expected) && output.contains(expected2)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    public void test61() {
        String end = "18\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 Bucuresti Muntenia\n2\nA0 Bucuresti2 Muntenia\n";
        String setup22 = "6\nA0 Bucuresti 1234567891234 20 da Chipescu Ciprian\n"
                + "6\nA0 Bucuresti 1234567891235 20 da Chipescu Ciprian2\n"
                + "6\nA0 Bucuresti2 1234567891236 20 da Chipescu Ciprian3\n"
                + "6\nA0 Bucuresti2 1234567891237 20 da Chipescu Ciprian4\n";
        String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
                + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";
        String setup30 = setup21 + setup22 + setup23;

        // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
        String setup31 = "2\nA0 Bucuresti3 Muntenia2\n";

        // mai putem inca 3 votanti in Bucuresti3
        String setup32 = "6\nA0 Bucuresti3 1234567891238 20 da Chipescu Ciprian5\n"
                + "6\nA0 Bucuresti3 1234567891239 20 da Chipescu Ciprian6\n"
                + "6\nA0 Bucuresti3 1234567891230 20 da Chipescu Ciprian7\n";

        String setup3 = setup30 + setup31 + setup32;
        String setup40 = setup3;
        // se fac cateva voturi
        String setup41 = "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891235 1234567891239\n"
                + "9\nA0 Bucuresti2 1234567891236 1234567891238\n"
                + "9\nA0 Bucuresti2 1234567891237 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891238 1234567891238\n"
                + "9\nA0 Bucuresti3 1234567891239 1234567891239\n"
                + "9\nA0 Bucuresti3 1234567891230 1234567891238\n"
                // si fraude
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n"
                + "9\nA0 Bucuresti 1234567891234 1234567891238\n";
        String setup4 = setup40 + setup41;

        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "16\nA0\n17\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "EMPTY: No elections";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }
}