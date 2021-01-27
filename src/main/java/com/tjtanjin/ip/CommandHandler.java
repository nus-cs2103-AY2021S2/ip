package com.tjtanjin.ip;

/**
 * The CommandHandler class manages all supported commands and provides them with the
 * TaskHandler to modify tasks when necessary.
 */
public class CommandHandler {
    private final AddCommand addCommand;
    private final ByeCommand byeCommand;
    private final DeleteCommand deleteCommand;
    private final DoneCommand doneCommand;
    private final FindCommand findCommand;
    private final HelpCommand helpCommand;
    private final ListCommand listCommand;

    /**
     * Constructor for CommandHandler.
     * @param taskHandler task handler to pass to commands that modify tasks
     */
    public CommandHandler(TaskHandler taskHandler) {
        this.addCommand = new AddCommand(taskHandler);
        this.byeCommand = new ByeCommand();
        this.deleteCommand = new DeleteCommand(taskHandler);
        this.doneCommand = new DoneCommand(taskHandler);
        this.findCommand = new FindCommand(taskHandler);
        this.helpCommand = new HelpCommand();
        this.listCommand = new ListCommand(taskHandler);
    }

    public AddCommand getAddCommand() {
        return this.addCommand;
    }

    public ByeCommand getByeCommand() {
        return this.byeCommand;
    }

    public DeleteCommand getDeleteCommand() {
        return this.deleteCommand;
    }

    public DoneCommand getDoneCommand() {
        return this.doneCommand;
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
}
