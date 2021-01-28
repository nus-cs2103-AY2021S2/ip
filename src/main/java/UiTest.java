import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
        public void setUpStreams() {
            System.setOut(new PrintStream(outContent));
            System.setErr(new PrintStream(errContent));
     }

     @After
         public void restoreStreams() {
            System.setOut(originalOut);
            System.setErr(originalErr);
     }

     @Test
        public void greeting_printed(){
            Ui.greeting();
            String expectedOutput = "Hello! I'm Duke \n" + " What can I do for you?";
            assertEquals(expectedOutput, outContent.toString());
    }


    @Test
        public void bye_printed(){
        Ui.bye();
        String expectedOutput = "Bye. Hope to see you again soon!";
        assertEquals(expectedOutput, outContent.toString());
    }

    }

