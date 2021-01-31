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
    public void parserOperator_invalidOperator_exceptionThrown() {
        try {
            String[] invalidInputs = {"", " ", "abc", "hello", "add meeting /at 2pm"};

            for (String input: invalidInputs){
                parser.parseCommand(input);
//                parser.parseOperator(input);
                fail("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e ) {
            String msg = "OOPS!!! I'm sorry, but I don't know what that means :-(";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void parserAddDeadline_invalidDeadlineDate_exceptionThrown() {
        try {
            String deadlineDescription = "deadline homework /by";
            String[] invalidDates = {"", " ", "abc", " 10/1/2020"};

            for (String date: invalidDates){
                String testCommandText = deadlineDescription + date;
                parser.parseCommand(testCommandText);
//                parser.parseAddDeadline(testCommandText);
                fail("OOPS!! Please follow the correct data/time format: yyyy-M-d H:mm");
            }
        } catch (DukeException e ) {
            String msg = "OOPS!! Please follow the correct data/time format: yyyy-M-d H:mm";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    //featureUnderTest_testScenario_expectedBehavior()
    public void addTask_addTaskInTestList_containedInTestList() throws DukeException {
        TaskList tasks = new TaskList();
        Task testTask = new Task("homework");
        tasks.executeOperation(new TaskAction(testTask, "add"));
//        tasks.addTask(testTask);
        assertTrue(tasks.getTaskList().contains(testTask));
    }
}
