import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class JUnitUtility {

    private static final InputStream STDIN = System.in;
    private static final PrintStream STDOUT = System.out;
    private static final PrintStream STDERR = System.err;

    /**
     * Redirect stdin to different stream with message.
     *
     * @param input initial message in new stream
     */
    public static void prepareStdin(String input) {
        input = String.join(System.lineSeparator(), input.split("\n"));
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    /**
     * Redirect stdout to different stream.
     */
    public static ByteArrayOutputStream prepareStdout() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        return out;
    }

    /**
     * Resets stdin to original stream.
     */
    public static void resetStdin() {
        System.setIn(STDIN);
    }

    /**
     * Resets stdout to original stream.
     */
    public static void resetStdout() {
        System.setOut(STDOUT);
    }
}
