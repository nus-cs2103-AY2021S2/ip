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
        initialiseTypeHandlers();
    }

    public void initialiseTypeHandlers() {
//        handlers.put(InputType.LIST, (tm, data) -> {
//            String ret = "";
//            for(var x : tm.retrieveAllTasks()) {
//                ret += x.getTaskUID() + ". [" + x.getStatusIcon() + "] " + x.getTaskDescription() + "\n";
//            }
//            return ret;
//        });
//
//        handlers.put(InputType.ADD, (tm, data) -> {
//            tm.addTask(new Task(data));
//            return "added: " + data;
//        });
    }


    public InputHandler() {
        this(new TaskManager());
    }

    @Override
    public String onNewInput(InputType type, String data) {
        return type.apply(this.taskManager, data);
    }
}
