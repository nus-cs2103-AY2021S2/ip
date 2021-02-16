package duke.task;

import java.util.ArrayList;

import duke.exceptions.TaskException;

/**
 * Event task class
 */
public class Event extends Task {
    private String eventAt;

    /**
     * Default constructor for event
     * @param description String description of event
     * @param eventAt String description of where event is at
     */
    public Event(String description, String eventAt) {
        super(description);
        this.eventAt = eventAt;
    }

    /**
     * Alternative constructor for Event
     * @param description String description of event
     * @param eventAt String description of where event is at
     * @param doneInt integer to indicate if event is done
     */
    public Event(String description, String eventAt, int doneInt) {
        super(description, doneInt);
        this.eventAt = eventAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventAt + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E|" + super.toSaveFormat() + "|" + eventAt;
    }

    @Override
    public void changeDescription(ArrayList<String> arrOfDescriptionToChange) throws TaskException {
        for (int i = 0; i < arrOfDescriptionToChange.size(); i = i + 2) {
            if (arrOfDescriptionToChange.get(i).equals("/de")) {
                this.description = arrOfDescriptionToChange.get(i + 1);
            } else if (arrOfDescriptionToChange.get(i).equals("/at")) {
                this.eventAt = arrOfDescriptionToChange.get(i + 1);
            } else {
                throw new TaskException("Illegal tag detected! \nPlease tag the part of the task you wish to change "
                        + "using /de for general description, /by for deadline by and /at for event at.");
            }
        }
    }
}
