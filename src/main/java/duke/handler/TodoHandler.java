package duke.handler;

import duke.tasks.Todo;

public class TodoHandler extends TaskHandler {

    public TodoHandler(String todoDes) {
        super(new Todo(todoDes));
    }

    public Todo getTodoTask() {
        assert toAdd instanceof Todo;
        return (Todo) toAdd;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof TodoHandler) {
            TodoHandler todoHandler = (TodoHandler) obj;
            Todo todoTask = todoHandler.getTodoTask();
            return toAdd.equals(todoTask);
        } else {
            return false;
        }
    }
}

