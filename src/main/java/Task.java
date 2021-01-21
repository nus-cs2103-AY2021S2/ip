public class Task {
    private enum TaskState {
        done, undone;
    }

    private String Description;
    private TaskState State;

    public Task (String Description) {
        this.Description = Description;
        this.State = TaskState.undone;
    }

    public void markDone () {
        switch (this.State) {
            case done:
                System.out.println("Again? This task has already been marked done!\n  " +
                    TaskInformation() + "\n");
                break;

            case undone:
                this.State = TaskState.done;
                System.out.println("Woohoo! I've marked this task as done\n  " +
                    TaskInformation() + "\n");
        }
    }

    public String TaskInformation () {
        return "[" + (this.State == TaskState.done ? "X" : " ") + 
            "] " + this.Description;
    }
}
