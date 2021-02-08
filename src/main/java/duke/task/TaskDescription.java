package duke.task;

import java.util.Optional;

import duke.exception.DescriptionIndexOutOfBoundsException;
import duke.exception.DescriptionMissingException;

public class TaskDescription {
    private final Optional<String[]> taskDescriptions;

    public TaskDescription() {
        this.taskDescriptions = Optional.empty();
    }

    public TaskDescription(String name) {
        String[] descriptions = new String[1];
        descriptions[0] = name;
        this.taskDescriptions = Optional.of(descriptions);
    }

    public TaskDescription(String[] descriptions) {
        this.taskDescriptions = Optional.of(descriptions);
    }

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
