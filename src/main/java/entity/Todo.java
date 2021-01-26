package main.java.entity;

/**
 * Represents a Todo Task
 * A Todo Task can be described with name and staus only
 */
public class Todo extends Task {

    /**
     * Creates a Todo Task whose name is given as input
     * and default isDone status is false
     * @param name name of task
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Creates a Todo Task whose name and isDone status
     * are both specified as input
     * @param name name of task
     * @param isDone boolean isDone status of task
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }


    /**
     * Override toString() method to be displayed in Ui
     * e.g. "[T][ ] get book"
     * @return Display String representation of this Todo task
     */
    @Override
    public String toString() {
        return "[T][" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    /**
     * Override toFileString to be output and read from Storage
     * e.g. "T|1|get book"
     * @return File String representation of this Todo task
     */
    @Override
    public String toFileString() {
        return "T|" + (this.isDone ? "1" : "0") + "|" + this.name;
    }
}
