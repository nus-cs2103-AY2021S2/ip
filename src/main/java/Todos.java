public class Todos extends Task {
    public Todos(String input) {
        super(input);
    }

    @Override
    public String setDone() {
        super.setTaskCompleted();
        return "Nice! I've marked this todo as done:\n" +
                toString();
    }

    @Override
    public String toString() {
        String taskStringCheck = super.getIsTaskCompleted() ? "X" : " ";
        return "[T]" + "[" + taskStringCheck + "] " + super.getTaskName();
    }
}
