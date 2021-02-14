package command;

import task.TaskManager;
import java.util.HashMap;
import java.util.List;

public interface Command {
    void execute(TaskManager taskManager);

    String getMessage();

    CommandType commandType();
}
