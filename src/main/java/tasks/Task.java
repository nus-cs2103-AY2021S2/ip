package tasks;

enum Status {
    DONE,
    NOT_DONE
}

/**
 *  Generic tasks.Task for inheritance.
 *
 *  @author Yap Jing Kang
 */

public abstract class Task {
    protected String name;
    protected Status done;

    /**
     *  Generic tasks.Task constructor.
     *
     *  @param name Name of tasks.Task.
     */
    public Task(String name) {
        this.name = name;
        this.done = Status.NOT_DONE;
    }

    /**
     *  tasks.Task name getter.
     *
     *  @return Name of tasks.Task.
     */
    public String getName() {
        return this.name;
    }

    /**
     *  Marks tasks.Task to be completed.
     *  Returns true if successful, false if unsuccessful.
     *
     *  @return Name of tasks.Task.
     */
    public boolean markAsDone() {
        if (this.done == Status.DONE) {
            return false;
        }
        this.done = Status.DONE;
        return true;
    }
}
