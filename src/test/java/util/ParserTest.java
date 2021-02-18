package util;

import command.TodoCommand;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.Parser.COMMAND_FLAG;

class ParserTest {

    @Test
    void parseCommand1() throws DukeException {
        HashMap<String, List<String>> commandMap = new HashMap<>();
        commandMap.put(COMMAND_FLAG, new ArrayList<>());
        commandMap.get(COMMAND_FLAG).add("todo");
        commandMap.put("todo", new ArrayList<>());
        commandMap.get("todo").add("something");

        TodoCommand todoCommand = TodoCommand.fromCommandMap(commandMap);
        assertTrue(Parser.parseCommand("todo  something").equals(todoCommand));
    }
}