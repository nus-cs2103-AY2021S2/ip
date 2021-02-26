package commands;

import common.Message;
import data.IDuke;
import data.exception.DukeIllegalArgumentException;
import data.task.ITask;
import data.task.Todo;

public class TodoCommand extends StoreCommand{

    private final String description;
    private TodoCommand(String description, IDuke duke) {
        super(-1, duke);
        this.description = description;
    }


    public static TodoCommand getTodoCommand(String description) {
        return new TodoCommand(description, null);
    }


    @Override
    public String execute() {
        if (duke == null) {
            throw new RuntimeException(Message.ERR_DUKE_NOT_INIT.toString());
        }
        return handleTodo(description);
    }


    private String handleTodo(String description) throws DukeIllegalArgumentException {
        //assert duke != null : Message.ERR_DUKE_NOT_INIT.toString();
        if (description.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The description of todo cannot be empty!");
        }
        ITask task = Todo.getTodo(description);
        storeTask(task);
        String output = "Got it. I've added this task:\n\t" + task.toString()
                + "\nNow you have " + duke.numOfTasks() + " task(s) in the list.";
        System.out.print(output);
        return output;
    }


    @Override
    public Command setDuke(IDuke duke) {
        return new TodoCommand(description, duke);
    }
}

}
