/**
 * A class representing a common Task object. Consists of the description
 * of the task as well as the state.
 */

public class Task {
    protected final String task;
    protected final boolean isDone;
    private ContactDetail contactDetail;

    /**
     * Constructor of a Task object
     * @param task Task need to be done
     */
    Task(String task) {
        this.task = task;
        this.isDone = false;
        this.contactDetail = new ContactDetail();
    }

    Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
        this.contactDetail = new ContactDetail();
    }

    /**
     * Finish the current Task and return a new Task
     * @return New Task object that has been done
     */
    public Task finishTask() throws DukeException {
        if (this.isDone) {
            throw new DukeException("Task has been done before!");
        }
        return new Task(this.task, true);
    }

    public boolean hasDone() {
        return this.isDone;
    }

    /**
     * Return a String representation for saving in txt files
     * @return String representation in txt files
     */
    public String saveString() {
        if (this.isDone) {
            return "1|" + this.task;
        }
        return "0|" + this.task;
    }

    public boolean hasContactDetails() {
        return this.contactDetail.hasContactDetails();
    }

    public void addContactDetails(String contactName, String contactNumber)
            throws DukeException {
        try {
            this.contactDetail.addContactDetails(contactName, Integer.parseInt(contactNumber));
        } catch (NumberFormatException e) {
            throw new DukeException("Contact Number is not a number");
        }
    }

    public String getContactDetails() {
        return this.contactDetail.getContactDetails();
    }

    /**
     * Returns a String representation of Task object
     * @return String representation of Task object
     */
    @Override
    public String toString() {
        String tick = " ";
        if (this.isDone){
            tick = "X";
        }
        return "[" + tick + "]" + " " + this.task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        else if (o instanceof Task) {
            Task otherTask = (Task) o;
            return this.equals(otherTask);
        }
        return false;
    }

    public boolean equals(Task otherTask) {
        return this.task.equals(otherTask.task);
    }
}
