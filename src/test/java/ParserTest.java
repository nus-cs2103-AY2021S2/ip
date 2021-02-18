import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ParserTest {

    private Storage storage = new Storage("data/test.txt");
    private Ui ui = new Ui();
    private TaskList tasks = new TaskList(new ArrayList<Task>());
    private Parser parser;

    @Test
    public void handleCommand_deadlineCreation_success() {
        parser = new Parser(storage, tasks, ui);
        String command = "deadline project /by 2021-02-19 2359";
        String expectedResponse = "Done! One new task:\n"
                + "[D][ ] project (by: Feb 19 2021 11:59 PM) \n"
                + "Now you have 1 task in the list";
        assertEquals(parser.handleCommand(command), expectedResponse);
    }
}
