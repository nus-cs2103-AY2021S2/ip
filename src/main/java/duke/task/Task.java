package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Abstract class for tasks. Contains information about the task description and
 * whether is has been carried out.
 */
abstract class Task {
    private String content;
    private boolean isDone;

    /**
     * Abstract Class constructor.
     *
     * @param content description of the task.
     */
    Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    /**
     * String representation of whether the task is done.
     * To be used by the concrete implementation of Tasks only.
     *
     * @return [X] if done [ ] otherwise.
     */
    public String toString(){
        String string = "";
        //Printing isDone
        string += String.format("[%s] ", isDone?"X":" ");
        string += this.content;
        return string;
    }

    /**
     * Information needed to be write/read in/to file.
     * In the format of Type|
     *
     * @return String representation in file
     */
    abstract public String toFileString();


    String parseDate(String string){
        try{
            LocalDate date = LocalDate.parse(string);
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            return string;
        }
    }

    /**
     * Set duke.Task to be done.
     *
     */
    protected void setDone() {
        this.isDone = true;
    }

    /**
     * Gets the task description.
     *
     * @return Description of duke.Task
     */
    protected String getDesc() {
        return this.content;
    }

    /**
     * Gets the status of whether the task is done or not.
     *
     * @return Boolean representation of whether task is done or not.
     */
    protected boolean getDone() {
        return this.isDone;
    }
}
