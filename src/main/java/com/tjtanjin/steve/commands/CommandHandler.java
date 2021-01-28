package com.tjtanjin.steve.commands;

import com.tjtanjin.steve.tasks.TaskHandler;

import java.util.HashMap;

/**
 * The CommandHandler class manages all supported commands and provides them with the
 * TaskHandler to modify tasks when necessary.
 */
public class CommandHandler {

    private final ByeCommand byeCommand;
    private final DeadlineCommand deadlineCommand;
    private final DeleteCommand deleteCommand;
    private final DoneCommand doneCommand;
    private final EventCommand eventCommand;
    private final FindCommand findCommand;
    private final HelpCommand helpCommand;
    private final ListCommand listCommand;
    private final TodoCommand todoCommand;

    /**
     * Constructor for CommandHandler.
     * @param taskHandler task handler to pass to commands that modify tasks
     */
    public CommandHandler(TaskHandler taskHandler) {

        //load command descriptions
        //list storing commands/descriptions, ideally store in json file
        HashMap<String, String> cmdInfo = new HashMap<>();
        cmdInfo.put("BYE", "bye | Description: exits the program");
        cmdInfo.put("LIST", "list | Description: list all entered tasks");
        cmdInfo.put("DONE",
                "done <task index> | Description: marks by index a given task as done");
        cmdInfo.put("TODO", "todo <name> | Description: adds a new todo task");
        cmdInfo.put("DEADLINE",
                "deadline <name> /by <end date> | Description: adds a new deadline task");
        cmdInfo.put("EVENT",
                "event <name> /from <start date> /to <end date> | Description: adds a new event task");
        cmdInfo.put("DELETE", "delete <task index> | Description: delete by index a given task");
        cmdInfo.put("HELP", "help | Description: list this help menu");
        cmdInfo.put("FIND", "find <name> | Description: finds task by name");

        //initialize commands
        this.byeCommand = new ByeCommand(cmdInfo.get("BYE"));
        this.deadlineCommand = new DeadlineCommand(cmdInfo.get("DEADLINE"), taskHandler);
        this.deleteCommand = new DeleteCommand(cmdInfo.get("DELETE"), taskHandler);
        this.doneCommand = new DoneCommand(cmdInfo.get("DONE"), taskHandler);
        this.eventCommand = new EventCommand(cmdInfo.get("EVENT"), taskHandler);
        this.findCommand = new FindCommand(cmdInfo.get("FIND"), taskHandler);
        this.helpCommand = new HelpCommand(cmdInfo.get("HELP"), cmdInfo);
        this.listCommand = new ListCommand(cmdInfo.get("LIST"), taskHandler);
        this.todoCommand = new TodoCommand(cmdInfo.get("TODO"), taskHandler);
    }

    public ByeCommand getByeCommand() {
        return this.byeCommand;
    }

    public DeadlineCommand getDeadlineCommand() {
        return this.deadlineCommand;
    }

    public DeleteCommand getDeleteCommand() {
        return this.deleteCommand;
    }

    public DoneCommand getDoneCommand() {
        return this.doneCommand;
    }

    public EventCommand getEventCommand() {
        return this.eventCommand;
    }

    public FindCommand getFindCommand() {
        return this.findCommand;
    }

    public HelpCommand getHelpCommand() {
        return this.helpCommand;
    }

    public ListCommand getListCommand() {
        return this.listCommand;
    }

    public TodoCommand getTodoCommand() {
        return this.todoCommand;
    }
}
