package duke.task;

import java.util.Optional;

import duke.exception.DescriptionIndexOutOfBoundsException;
import duke.exception.DescriptionMissingException;

public class TaskDescription {
    private final Optional<String[]> taskDescriptions;

    /**
     * Constructs an empty TaskDescription.
     */
    public TaskDescription() {
        this.taskDescriptions = Optional.empty();
    }

    /**
     * Constructs a TaskDescription using a single string.
     * @param name The name of the task.
     */
    public TaskDescription(String name) {
        String[] descriptions = new String[1];
        descriptions[0] = name;
        this.taskDescriptions = Optional.of(descriptions);
    }

    /**
     * Constructs a TaskDescription using a String array.
     * @param descriptions The relevant descriptions.
     */
    public TaskDescription(String[] descriptions) {
        this.taskDescriptions = Optional.of(descriptions);
    }

    /**
     * Returns the string stored at the given index.
     * @param index The index to extract from.
     * @return The string stored at the given index.
     * @throws DescriptionIndexOutOfBoundsException If the description index out of bound.
     * @throws DescriptionMissingException If there is nothing in the TaskDescription.
     */
    public String getDescriptionOfIndex(int index)
            throws DescriptionIndexOutOfBoundsException, DescriptionMissingException {
        if (taskDescriptions.isEmpty()) {
            throw new DescriptionMissingException("Descriptions missing! ");
        }
        try {
            String[] descriptions = taskDescriptions.get();
            return descriptions[index];
        } catch (IndexOutOfBoundsException e) {
            throw new DescriptionIndexOutOfBoundsException("Argument missing! ");
        }
    }
}
