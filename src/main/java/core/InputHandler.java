package core;

import core.task.TaskManager;

public class InputHandler implements InputListener {
    private TaskManager taskManager;

    public InputHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public String onNewInput(InputType type, String data) {

        return type.apply(this.taskManager, data);
    }


}
