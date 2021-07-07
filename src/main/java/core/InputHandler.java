package core;

import core.task.TaskManager;

/**
 * Instance of InputListener to handle all the input in the Duke class.
 */
public class InputHandler implements InputListener {
    private final TaskManager taskManager;

    /**
     * Creates a new InputHandler which uses the {@code TaskManager} to manage the {@code Task}s.
     * @param taskManager - taskmanager instance.
     */
    public InputHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public String onNewInput(InputType type, String data) {

        return type.apply(this.taskManager, data);
    }


}
