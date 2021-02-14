package duke.parser;

import duke.commands.Command;
import duke.commands.ByeCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ErrorCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;
import duke.tasks.TaskList;

/**
 * Parser makes sense of the user command and act accordingly
 */
public class Parser {
    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }
    public Command parse(String userInput) {
        try {
            String[] input = userInput.split(" ");
            String action = input[0];
            if (action.equals("bye")) {
                return new ByeCommand(input, this.taskList);
            } else if (action.equals("list")) {
                return new ListCommand(input, this.taskList);
            } else if (action.equals("done")) {
                return new DoneCommand(input, this.taskList);
            } else if (action.equals("delete")) {
                return new DeleteCommand(input, this.taskList);
            } else if (action.equals("find")) {
                return new FindCommand(input, this.taskList);
            } else if (action.equals("todo")) {
                return new ToDoCommand(input, this.taskList);
            } else if (action.equals("deadline")) {
                return new DeadlineCommand(input, this.taskList);
            } else if (action.equals("event")) {
                return new EventCommand(input, this.taskList);
            } else {
                throw new WrongArgumentException("OOPS!!! I'm sorry, "
                        + "but I don't know what that means :-(");
            }
        } catch (WrongArgumentException e) {
            return new ErrorCommand(this.taskList, e.getMessage());
        }
    }
}