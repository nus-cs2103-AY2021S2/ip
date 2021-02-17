import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class DukeTest {

    private Duke duke;

    @BeforeEach
    public void setUp() {
        this.duke = new Duke("./testLog");
    }

    @DisplayName("Test if parser creates Todo object when todo is added. ")
    @Test
    public void addTodoTest() {
        String out = duke.testDuke("todo do something");
        assertEquals(out, "Added  do something. \nYou now have 1 items in your list.");
    }

    @DisplayName("Test clear all. ")
    @Test
    public void pipeClearAllTest() {
        duke.testDuke("todo do something");
        String out = duke.testDuke("all | remove");
        assertEquals(out, "I have removed this item: \n[T][ ]  do something");
    }

    @DisplayName("Test done all. ")
    @Test
    public void pipeDoneAllTest() {
        duke.testDuke("todo a");
        duke.testDuke("todo b");
        duke.testDuke("todo c");
        String out = duke.testDuke("all | done");
        String outExpected =
                "Alright, I will mark these as done.\n" +
                "[T][X]  a\n" +
                "[T][X]  b\n" +
                "[T][X]  c\n";

        String listAfterAllDone = duke.testDuke("all");
        String listExpected =
                "1. [T][X]  a\n" +
                "2. [T][X]  b\n" +
                "3. [T][X]  c\n";
        assertAll("all | done",
                () -> assertEquals(out, outExpected),
                () -> assertEquals(listAfterAllDone, listExpected));
    }

    @AfterEach
    public void clearFiles() {
        File logFile = new File("./testLog");
        logFile.delete();
    }
}
