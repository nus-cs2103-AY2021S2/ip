package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
//    private final PrintStream standardOut = System.out;
//    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
//    @BeforeEach
//    public void setUp() {
//        System.setOut(new PrintStream(outputStreamCaptor));
//    }
    @Test
    public void testGetWelcomeGreetingString(){
        String welcomeGreeting = new Ui().getWelcomeGreetingString();
        assertEquals("\nSup peeps! I am Meme Bot.\n"
                        + "\nThese are the list of commands:\n"
                        + "1. todo <details>\n"
                        + "2. event <details> /at <date>\n"
                        + "3. deadline <details> /by <date>\n"
                        + "4. list\n"
                        + "5. done <task number>\n"
                        + "6. delete <task number>\n"
                        + "7. undo\n"
                        + "8. bye\n\nSo.. What can I do for you?\n", welcomeGreeting);
    }
    @Test
    public void testGetExitMessageString(){
        String exitMessage = new Ui().getExitMessageString();
        assertEquals("Sayonara! Hope to never see you again..\n" , exitMessage);
    }
}
