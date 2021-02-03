package tlylt.haha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UiTest {
    private static final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private static final PrintStream testOutput =
            new PrintStream(output);

    @BeforeAll
    public static void setUp() {
        // With reference to
        // https://www.baeldung.com/java-testing-system-out-println
        System.setOut(testOutput);
    }

    @Test
    void welcome_whenInvoke_success() {
        new Ui().welcome();
        String expected = "Hello from\n"
                + " _    _          _    _\n"
                + "| |  | |   /\\   | |  | |   /\\\n"
                + "| |__| |  /  \\  | |__| |  /  \\\n"
                + "|  __  | / /\\ \\ |  __  | / /\\ \\\n"
                + "| |  | |/ ____ \\| |  | |/ ____ \\\n"
                + "|_|  |_/_/    \\_\\_|  |_/_/    \\_\\\n"
                + "____________________________________________________________\n"
                + "Dude, I'm HAHA.\n"
                + "What can I do for you?\n"
                + "Hint: deadline & event to include date & time\n"
                + "e.g. 2020-02-02 18:00\n"
                + "(Oh when you are done, say bye)\n"
                + "____________________________________________________________";
        assertEquals(
                expected,
                output.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
    }
}
