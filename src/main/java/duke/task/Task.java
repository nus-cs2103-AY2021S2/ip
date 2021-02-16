package duke.task;

import java.util.ArrayList;

import duke.exceptions.TaskException;

/**
 * Parent Class of all tasks
 */
public class Task {
    protected String description;

    private boolean isDone;

    /**
     * Default Constructor of a task
     *
     * @param description string description of a task
     */
    public Task(String description) {
        assert description != null : "description of task cannot be null";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor of a task
     *
     * @param description string description of a task
     * @param doneInt 1 indicates that the task is done, 0 indicates that the task is not done
     */
    public Task(String description, int doneInt) {
        this.description = description;
        if (doneInt == 1) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    /**
     * @return If the task is done, return an X, else return a empty space character
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick or X symbols
    }

    /**
     * @return String that describes the task and its done status
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void changeTaskToDone() {
        isDone = true;
    }

    /**
     * @return Output string that in the format that it should be saved as
     */
    public String toSaveFormat() {
        int isDoneInt = isDone ? 1 : 0;
        return isDoneInt + "|" + description;
    }

    /**
     * Changes the descriptions within a task
     *
     * @param arrOfDescriptionToChange arrayList of String descriptions to change
     * @throws TaskException Throws a task exception if there is some error in the array
     */
    public void changeDescription(ArrayList<String> arrOfDescriptionToChange) throws TaskException {
        for (int i = 0; i < arrOfDescriptionToChange.size(); i = i + 2) {
            if (arrOfDescriptionToChange.get(i).equals("/de")) {
                this.description = arrOfDescriptionToChange.get(i + 1);
            } else {
                throw new TaskException("Illegal tag detected! \nPlease tag the part of the task you wish to change "
                        + "using /de for general description, /by for deadline by and /at for event at.");
            }
        }
    }

    public boolean descriptionContains(String input) {
        return description.contains(input);
    }
}
