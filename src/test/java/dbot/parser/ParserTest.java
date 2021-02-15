package dbot.parser;

import dbot.command.Command;
import dbot.command.TodoCommand;
import dbot.exception.DBotException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {
    @Test
    public void parseInput_validTodoCommand_InitTodoCommand() {
        String todoDescription = "LOREM IPSUM TEXT";
        String userInput = "todo " + todoDescription;
        try {
            Command expectedTodo = new TodoCommand(todoDescription);
            Command actualTodo = Parser.parse(userInput);
            assertEquals(expectedTodo, actualTodo);
        } catch (DBotException e) {
            fail("Duke Exception should not be thrown.");
        }
    }
}
