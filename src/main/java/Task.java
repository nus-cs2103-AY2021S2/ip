public class Task {
    protected String des;
    protected boolean status;

    public Task(String des) {
        this.des = des;
        this.status = false;
    }

    /**
     * checks if the status is marked as done or not
     * @return if status is done, return [X], else return [ ]
     */
    public String getStatus() {
        return (status? "[X]" : "[ ]");
    }

    /**
     * sets status to true if called
     */

    public void markAsDone() {
        status = true;
    }

    public String toString() {
        return this.getStatus() + " " + des;
    }
}