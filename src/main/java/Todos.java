public class Todos extends Task {
    public Todos(String input) throws EmptyTaskDukeException {
        super(input);
        Task.incrementNumOfTask();
    }

    @Override
    public String setDone() {
        super.setTaskCompleted();
        return "Nice! I've marked this todo as done:\n"
                + toString();
    }

    @Override
    public String toString() {
        String taskStringCheck = super.getIsTaskCompleted() ? "X" : " ";
        return "[T]" + "[" + taskStringCheck + "] " + super.getTaskName();
    }
}
