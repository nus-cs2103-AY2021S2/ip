package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractTask implements Serializable {
    protected final String description;
    protected final List<String> tags;
    protected boolean isDone;
    /**
     * Constructs a task using the description
     *
     * @throws DukeEmptyDescriptionException if the description is empty
     */
    public AbstractTask(String description) throws DukeEmptyDescriptionException {
        if (description.isEmpty()) {
            throw new DukeEmptyDescriptionException();
        }
        this.description = description;
        this.tags = new ArrayList<>();
        this.isDone = false;
    }

    /**
     * Mark the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Add a tag to a task
     *
     * @param tag tag name
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Delete a tag from a task
     *
     * @param tag tag name
     */
    public void deleteTag(String tag) {
        tags.remove(tag);
    }

    /**
     * Return a string representation of the task
     */
    @Override
    public String toString() {
        String tagString = tags.stream().map(x -> "#" + x).collect(Collectors.joining());
        return String.format("[%s] %s %s", isDone ? "X" : " ", description, tagString);
    }
}
