package commands;

import format.Ui;
import tasklist.TaskList;

public class HelpCommand extends CommandWithNoParameters {
    private String helpMessage = Ui.formatMultiLineMessages("Here are all the inputs you can enter: " ,
                    "replace words in curly braces as appropriate" ,
                    "list" ,
                    "todo {description}" ,
                    "event {description} /at DD-MM hh:mm" ,
                    "deadline {description} /by DD-MM hh:mm" ,
                    "done {task number}" ,
                    "delete {task number}" ,
                    "find {keyword}" , // do you need to find todo/event keywords also
                    //"help date" ,
                    //"help shortcuts" ,
                    "End of command list.");

    public HelpCommand(String commandBody) {
        super("help", commandBody);
    }

    @Override
    public void run(TaskList taskList) {
        // currently ignores any words in commandBody, todo can display date specific or shortcut specific help
        this.commandOutputMsg = helpMessage;
        this.hasRunSuccessfully = true;
    }
}
