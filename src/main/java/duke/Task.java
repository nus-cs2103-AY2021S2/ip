package duke;

class Task {
    private final String name;
    private boolean isDone;

    /**
     * Task constructor where the boolean isDone is set to false
     * @param s name of Task
     */
    Task(String s) {
        this.name = s;
        this.isDone = false;
    }

    /**
     * Task constructor where the boolean isDone is given
     * @param s name of Task
     * @param c value that boolean isDone will be set to
     */
    Task(String s, boolean c) {
        this.name = s;
        this.isDone = c;
    }

    /**
     * sets the isDone to true
     */
    void finish() {
        this.isDone = true;
    }

    /**
     * returns the name of the task
     * @return name of task
     */
    String getName() {
        return this.name;
    }

    /**
     * returns the isDone
     * @return isDone
     */
    boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Provides the format for which the Task will be saved in the txt file
     * @return a string in the format to be saved in the txt file
     */
    String saveName() {
        return String.format("task1!1%s1!1%b", this.name, this.isDone);
    }

    /**
     * Checks if the Task is to be done on the day
     * @param s the day that is given in yyy-mm-dd format (e.g. 2021-01-31)
     * @return true if !isDone
     */
    boolean onDay(String s) {
        return !this.isDone;
    }

    boolean contains(String s) {
        return this.name.contains(s);
    }

    public String toString() {
        String check = " ";
        if (this.isDone) {
            check = "X";
        }
        return String.format("[%s]  %s", check, this.name);
    }
}
