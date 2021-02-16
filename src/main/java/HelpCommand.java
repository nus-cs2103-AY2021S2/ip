public class HelpCommand extends Command {

    static private String HELP_MESSAGE =
            "Here are the list of commands you can use:\n" +
            "'help' - displays this text\n" +
            "'list' - lists the tasks in your task list\n" +
            "'done' - completes a task in your task list, format: done [index no. of task to complete]\n" +
            "'delete' - deletes a task in your task list, format: delete [index no. of task to delete]\n" +
            "'find' - finds a task(s) containing one specified keyword, format: find [keyword]\n" +
            "'bye' - exits this application\n" +
            "'todo' - adds a ToDo task to the task list. format: todo [description]\n" +
            "'event' - adds an Event to the task list, format: event [description] /by: [date] [time]\n" +
            "'deadline' - adds a Deadline to the task list, format: deadline [description] /by: [date] [time]";

    public HelpCommand(Parser parser, Ui ui, Storage storage) {
        super(parser, ui, storage);
    }

    public String execute() {
        return HELP_MESSAGE;
    }
}
