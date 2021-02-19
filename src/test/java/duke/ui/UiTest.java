package duke.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    Ui ui = new Ui();
    String line = "____________________________\n";
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }
    @Test
    public void testingPrintingMessage() {
        assertEquals(ui.printMessage("a"), line + "a\n" + line);
    }

    @Test
    public void testingGreetingMessage() {
        String actual = line + "Hello! I'm Duke. What I can do for you?\n" + line;
        assertEquals(ui.greetingMessage(), actual);
    }

    @Test
    public void testingBye() {
        String actual = line + "Bye. Hope to see you again soon!\n" + line;
        assertEquals(ui.bye(), actual);
    }
}