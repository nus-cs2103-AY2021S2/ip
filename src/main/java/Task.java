public class Task {
    protected final String task;
    private final boolean done;

    Task(String task) {
        String processedTask = "";
        String[] tmp = task.split("/");
        if (tmp.length > 1) {
            processedTask = tmp[0] + "(" + tmp[1] + ")";
        }
        else {
            processedTask = task;
        }
        this.task = processedTask;
        this.done = false;
    }

    Task(String task, boolean done) {
        String processedTask = "";
        String[] tmp = task.split("/");
        if (tmp.length > 1) {
            processedTask = tmp[0] + "(" + tmp[1] + ")";
        }
        else {
            processedTask = task;
        }
        this.task = processedTask;
        this.done = done;
    }

    public Task finishTask() {
        System.out.println("Nice! I've marked this task as done: ");
        return new Task(this.task, true);
    }

    @Override
    public String toString() {
        String tick = " ";
        if (this.done){
            tick = "X";
        }
        return "[" + tick + "]" + " " + this.task;
    }
}
