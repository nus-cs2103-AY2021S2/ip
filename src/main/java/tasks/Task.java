enum Status {
    DONE,
    NOT_DONE
}

/**
 *  Generic Task for inheritance.
 *
 *  @author Yap Jing Kang
 */

abstract class Task {
    protected String name;
    protected Status done;

    /**
     *  Generic Task constructor.
     *
     *  @param name Name of Task.
     */
    public Task(String name) {
        this.name = name;
        this.done = Status.NOT_DONE;
    }

    /**
     *  Task name getter.
     *
     *  @return Name of Task.
     */
    public String getName() {
        return this.name;
    }

    /**
     *  Marks Task to be completed.
     *  Returns true if successful, false if unsuccessful.
     *
     *  @return Name of Task.
     */
    public boolean markAsDone() {
        if (this.done == Status.DONE) {
            return false;
        }
        this.done = Status.DONE;
        return true;
    }
}
