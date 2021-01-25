import java.io.*;

public class JUnitUtility {

    private static final InputStream STDIN = System.in;
    private static final PrintStream STDOUT = System.out;
    private static final PrintStream STDERR = System.err;

    public static void prepareStdin(String input) {
        input = String.join(System.lineSeparator(), input.split("\n"));
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static ByteArrayOutputStream prepareStdout() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        return out;
    }

    public static void resetStdin() {
        System.setIn(STDIN);
    }

    public static void resetStdout() {
        System.setOut(STDOUT);
    }
}
