package core;

import core.task.TaskManager;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiFunction;

/**
 * Instance of InputListener to handle all the input in the Duke class.
 */
public class InputHandler implements InputListener {
    private TaskManager taskManager;
    private Map<InputType, BiFunction<TaskManager, String, String>> handlers;

    /**
     * Creates a new InputHandler which uses the {@code TaskManager} to manage the {@code Task}s.
     * @param taskManager - taskmanager instance.
     */
    public InputHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.handlers = new TreeMap<>();
    }

    @Override
    public String onNewInput(InputType type, String data) {
        return type.apply(this.taskManager, data);
    }


}
