public class Task {
    
    protected final String name;
    protected final boolean completed;

    public final static Task createTask(String input) throws DukeException {
        String name = input.substring(4);
        if (name.equals("") || name.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        name = name.substring(1);
        return new Task(name);
    }

    protected Task(String name) {
        this.name = name;
        this.completed = false;
    }

    protected Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public Task completeTask() {
        return new Task(this.name, true);
    }

    @Override
    public String toString() {
        String complete = this.completed ? "[X] " : "[ ] "; 
        return "[T]" + complete + this.name;
    }

}
