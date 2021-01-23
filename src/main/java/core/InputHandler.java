package core;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiFunction;

public class InputHandler implements InputListener {
    private TaskManager taskManager;
    private Map<InputType, BiFunction<TaskManager, String, String>> handlers;

    public InputHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.handlers = new TreeMap<>();
    }

    @Override
    public String onNewInput(InputType type, String data) {
        return type.apply(this.taskManager, data);
    }


}
