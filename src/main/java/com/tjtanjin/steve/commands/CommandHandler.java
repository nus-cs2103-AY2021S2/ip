package com.tjtanjin.steve.commands;

import java.util.HashMap;

import com.tjtanjin.steve.tasks.TaskHandler;

/**
 * The CommandHandler class manages all supported commands and provides them with the
 * TaskHandler to modify tasks when necessary.
 */
public class CommandHandler {

    private final ByeCommand BYE_COMMAND;
    private final DeadlineCommand DEADLINE_COMMAND;
    private final DeleteCommand DELETE_COMMAND;
    private final DoneCommand DONE_COMMAND;
    private final EventCommand EVENT_COMMAND;
    private final FindCommand FIND_COMMAND;
    private final HelpCommand HELP_COMMAND;
    private final ListCommand LIST_COMMAND;
    private final TodoCommand TODO_COMMAND;

    /**
     * Constructor for CommandHandler.
     *
     * @param taskHandler task handler to pass to commands that modify tasks
     */
    public CommandHandler(TaskHandler taskHandler) {

        //load command descriptions
        //list storing commands/descriptions, ideally store in json file
        HashMap<String, String> cmdInfo = new HashMap<>();
        cmdInfo.put("BYE", "bye | Description: exits the program");
        cmdInfo.put("LIST", "list | Description: lists all entered tasks");
        cmdInfo.put("DONE",
                "done <task index> | Description: marks by index a given task as done");
        cmdInfo.put("TODO", "todo <name> | Description: adds a new todo task");
        cmdInfo.put("DEADLINE",
                "deadline <name> /by <end date> | Description: adds a new deadline task");
        cmdInfo.put("EVENT",
                "event <name> /from <start date> /to <end date> | Description: adds a new event task");
        cmdInfo.put("DELETE", "delete <task index> | Description: deletes by index a given task");
        cmdInfo.put("HELP", "help | Description: lists this help menu");
        cmdInfo.put("FIND", "find <name> | Description: finds task by name");

        //initialize commands
        this.BYE_COMMAND = new ByeCommand(cmdInfo.get("BYE"));
        this.DEADLINE_COMMAND = new DeadlineCommand(cmdInfo.get("DEADLINE"), taskHandler);
        this.DELETE_COMMAND = new DeleteCommand(cmdInfo.get("DELETE"), taskHandler);
        this.DONE_COMMAND = new DoneCommand(cmdInfo.get("DONE"), taskHandler);
        this.EVENT_COMMAND = new EventCommand(cmdInfo.get("EVENT"), taskHandler);
        this.FIND_COMMAND = new FindCommand(cmdInfo.get("FIND"), taskHandler);
        this.HELP_COMMAND = new HelpCommand(cmdInfo.get("HELP"), cmdInfo);
        this.LIST_COMMAND = new ListCommand(cmdInfo.get("LIST"), taskHandler);
        this.TODO_COMMAND = new TodoCommand(cmdInfo.get("TODO"), taskHandler);
    }

    /**
     * Gets the bye command object.
     *
     * @return bye command object
     */
    public ByeCommand getByeCommand() {
        return this.BYE_COMMAND;
    }

    /**
     * Gets the deadline command object.
     *
     * @return deadline command object
     */
    public DeadlineCommand getDeadlineCommand() {
        return this.DEADLINE_COMMAND;
    }

    /**
     * Gets the delete command object.
     *
     * @return delete command object
     */
    public DeleteCommand getDeleteCommand() {
        return this.DELETE_COMMAND;
    }

    /**
     * Gets the done command object.
     *
     * @return done command object
     */
    public DoneCommand getDoneCommand() {
        return this.DONE_COMMAND;
    }

    /**
     * Gets the event command object.
     *
     * @return event command object
     */
    public EventCommand getEventCommand() {
        return this.EVENT_COMMAND;
    }

    /**
     * Gets the find command object.
     *
     * @return find command object
     */
    public FindCommand getFindCommand() {
        return this.FIND_COMMAND;
    }

    /**
     * Gets the help command object.
     *
     * @return help command object
     */
    public HelpCommand getHelpCommand() {
        return this.HELP_COMMAND;
    }

    /**
     * Gets the list command object.
     *
     * @return list command object
     */
    public ListCommand getListCommand() {
        return this.LIST_COMMAND;
    }

    /**
     * Gets the todo command object.
     *
     * @return todo command object
     */
    public TodoCommand getTodoCommand() {
        return this.TODO_COMMAND;
    }
}
