import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void parserInvalidOperatorTest() {
        try {
            final String[] invalidInput = {"", " ", "abc", "hello", "add meeting /at 2pm"};

            for (String input: invalidInput){
                parser.parseOperator(input);
                fail("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (final DukeException e ) {
            final String msg = "OOPS!!! I'm sorry, but I don't know what that means :-(";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void parserInvalidDeadlineDateTest() {
        try {
            final String deadlineDescription = "deadline homework /by";
            final String[] invalidDate = {"", " ", "abc", " 10/1/2020"};

            for (String date: invalidDate){
                String testCommandText = deadlineDescription + date;
                parser.parseAddDeadline(testCommandText);
                fail("OOPS!! Please follow the correct data/time format: yyyy-M-d H:mm");
            }
        } catch (final DukeException e ) {
            final String msg = "OOPS!! Please follow the correct data/time format: yyyy-M-d H:mm";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void taskListDeleteTest(){
        TaskList tasks = new TaskList();
        Task testTask = new Task("homework");
        tasks.addTask(testTask);
        assertTrue(tasks.getTaskList().contains(testTask));
    }
}
