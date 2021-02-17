import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    private Duke duke;

    @BeforeEach
    public void setUp() {
        this.duke = new Duke("./testLog");
    }

    @DisplayName("Test if parser creates Todo object when todo is added. ")
    @Test
    public void addTodoTest() throws DukeException {
        String out = duke.testDuke("todo do something");
        assertEquals(out, "Added  do something. \nYou now have 1 items in your list.");
    }

    @DisplayName("Test clear all. ")
    @Test
    public void pipeClearAllTest() throws DukeException {
        duke.testDuke("todo do something");
        String out = duke.testDuke("all | remove");
        assertEquals(out, "I have removed this item: \n[T][ ]  do something");
    }

    @AfterEach
    public void clearFiles() {
        File logFile = new File("./testLog");
        logFile.delete();
    }
}
