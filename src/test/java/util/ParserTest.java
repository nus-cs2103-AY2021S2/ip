package util;

import command.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Parser.COMMAND_FLAG;

class ParserTest {

    @Test
    void parseCommand1() throws DukeException {
        HashMap<String, List<String>> commandMap = new HashMap<>();
        commandMap.put(COMMAND_FLAG, new ArrayList<>());
        commandMap.get(COMMAND_FLAG).add("event");
        commandMap.put("event", new ArrayList<>());
        commandMap.get("event").add("im");
        commandMap.get("event").add("a");
        commandMap.get("event").add("description");
        commandMap.put("at", new ArrayList<>());
        commandMap.get("at").add("2021-02-19");
        commandMap.put("tap", new ArrayList<>());
        commandMap.get("tap").add("ignore");
        Command referenceCommand = EventCommand.fromCommandMap(commandMap);

        Command parsedCommand =
                Parser.parseCommand("event   im a description /tap ignore  /at 2021-02-19");
        assertEquals(referenceCommand, parsedCommand);
    }

    @Test
    void parseCommand2() throws DukeException {
        HashMap<String, List<String>> commandMap = new HashMap<>();
        commandMap.put(COMMAND_FLAG, new ArrayList<>());
        commandMap.get(COMMAND_FLAG).add("find");
        commandMap.put("find", new ArrayList<>());
        commandMap.get("find").add("apple");
        commandMap.get("find").add("bee");
        Command referenceCommand = FindCommand.fromCommandMap(commandMap);

        Command parsedCommand = Parser.parseCommand("find  apple bee");
        assertEquals(referenceCommand, parsedCommand);
    }

    @Test
    void parseCommand3() throws DukeException {
        HashMap<String, List<String>> commandMap = new HashMap<>();
        commandMap.put(COMMAND_FLAG, new ArrayList<>());
        commandMap.get(COMMAND_FLAG).add("help");
        commandMap.put("help", new ArrayList<>());
        commandMap.get("help").add("event");
        Command referenceCommand = HelpCommand.fromCommandMap(commandMap);

        Command parsedCommand = Parser.parseCommand("help event");
        assertEquals(referenceCommand, parsedCommand);
    }

    @Test
    void commandMapToString1() {
        HashMap<String, List<String>> commandMap = new HashMap<>();
        commandMap.put(COMMAND_FLAG, new ArrayList<>());
        commandMap.get(COMMAND_FLAG).add("event");
        commandMap.put("event", new ArrayList<>());
        commandMap.get("event").add("im");
        commandMap.get("event").add("a");
        commandMap.get("event").add("description");
        commandMap.put("at", new ArrayList<>());
        commandMap.get("at").add("2021-02-19");
        commandMap.put("tap", new ArrayList<>());
        commandMap.get("tap").add("ignore");

        String commandString = Parser.commandMapToString(commandMap);
        assertEquals(commandString, "event im a description/tap ignore/at 2021-02-19");
    }

    @Test
    void commandMapToString2() {
        HashMap<String, List<String>> commandMap = new HashMap<>();
        commandMap.put(COMMAND_FLAG, new ArrayList<>());
        commandMap.get(COMMAND_FLAG).add("find");
        commandMap.put("find", new ArrayList<>());
        commandMap.get("find").add("apple");
        commandMap.get("find").add("bee");

        String commandString = Parser.commandMapToString(commandMap);
        assertEquals(commandString, "find apple bee");
    }

    @Test
    void commandMapToString3() {
        HashMap<String, List<String>> commandMap = new HashMap<>();
        commandMap.put(COMMAND_FLAG, new ArrayList<>());
        commandMap.get(COMMAND_FLAG).add("help");
        commandMap.put("help", new ArrayList<>());
        commandMap.get("help").add("event");

        String commandString = Parser.commandMapToString(commandMap);
        assertEquals(commandString, "help event");
    }
}