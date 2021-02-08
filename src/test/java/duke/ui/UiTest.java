package duke.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    Ui ui = new Ui();
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }
    @Test
    public void testingPrintingMessage() {
        assertEquals(ui.printMessage("a"), ui.line + "a\n" + ui.line);
    }

    @Test
    public void testingGreetingMessage() {
        String actual = ui.line + "Hello! I'm Duke. What I can do for you?\n" + ui.line;
        assertEquals(ui.greetingMessage(), actual);
    }

    @Test
    public void testingBye() {
        String actual = ui.line + "Bye. Hope to see you again soon!\n" + ui.line;
        assertEquals(ui.bye(), actual);
    }
}