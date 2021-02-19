package com.tanboonji.jhin;

import java.util.ArrayList;
import java.util.HashMap;

import com.tanboonji.jhin.command.Command;
import com.tanboonji.jhin.exception.JhinException;
import com.tanboonji.jhin.model.AliasMap;
import com.tanboonji.jhin.model.TaskList;
import com.tanboonji.jhin.parser.CommandParser;

/**
 * The JhinStub class is only used for testing purposes.
 */
public class JhinStub {

    private TaskList taskList;
    private AliasMap aliasMap;

    /**
     * Default class constructor.
     */
    public JhinStub() {
    }

    /**
     * Initialises JhinStub.
     */
    public void initialise() {
        taskList = new TaskList(new ArrayList<>());
        aliasMap = new AliasMap(new HashMap<>());
    }

    /**
     * Executes user command and returns response from command.
     *
     * @param input User input command.
     * @throws JhinException If any error occurred while executing command.
     */
    public void getResponse(String input) throws JhinException {
        input = CommandParser.parseAlias(input, aliasMap);
        Command command = CommandParser.parseCommand(input);
        command.addTaskList(taskList);
        command.addAlias(aliasMap);
        command.execute();
    }
}
