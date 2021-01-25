public abstract class Task {
    protected enum TaskState {
        done, undone;
    }

    private String Description;
    private TaskState State;

    public Task (int state, String Description) {
        this.State = (state > 0 ? TaskState.done : TaskState.undone);
        this.Description = Description;
    }

    public Task (String Description) {
        this(0, Description);
    }

    public void markDone () {
        switch (this.State) {
            case done:
                System.out.println("     Again? This task has already been marked done!\n     " +
                    TaskInformation());
                break;

            case undone:
                this.State = TaskState.done;
                System.out.println("     Woohoo! I've marked this task as done\n     " +
                    TaskInformation());
        }
    }

    public String TaskInformation () {
        return "[" + (this.State == TaskState.done ? "X" : " ") + 
            "] " + this.Description;
    }

    public String creationCommand () {
        return (this.State == TaskState.done ? "1" : "0") + " :: " +
            this.Description;
    }

    public boolean outstanding () {
        return (this.State == TaskState.undone);
    }
}