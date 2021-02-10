import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task {
    protected String description;
    protected boolean isDone;
    protected List<String> tags;

    /**
     * Constructor for the Task class.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Constructor for the Task class.
     *
     * @param description Description of the task.
     * @param tags List of tags of the task.
     */
    public Task(String description, String tags) {
        this.description = description.trim();
        this.isDone = false;
        if (!stringIsNullOrEmpty(tags)) {
            this.tags = new ArrayList<>(Arrays.asList(tags.split(" ")));
        } else {
            this.tags = new ArrayList<>();
        }
    }

    private boolean stringIsNullOrEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Adds a tag to the task.
     *
     * @param tag String of tag to be added.
     */
    public void addTag(String tag) {
        if (stringIsNullOrEmpty(tag)) {
            throw new EmptyTagException();
        }
        this.tags.add(tag);
    }

    /**
     * Returns the string representation of how the task will be stored in the file.
     *
     * @return String
     */
    public String toFileString() {
        char done = this.isDone ? 'X' : ' ';
        StringBuilder tagsBuffer = new StringBuilder();
        tags.forEach(tag -> tagsBuffer.append(String.format("%s ", tag)));
        String tagsString = tagsBuffer.toString().trim();

        if (stringIsNullOrEmpty(tagsString)) {
            tagsString = " ";
        }

        return String.format("%c | %s | %s", done, tagsString, this.description);
    }

    protected String toStringWithoutTags() {
        char done = this.isDone ? 'X' : ' ';
        return String.format("[%c] %s", done, this.description);
    }

    protected String getTagsString() {
        String tagsString = "";

        if (this.tags.size() > 0) {
            StringBuilder tagsBuffer = new StringBuilder();
            tags.forEach(tag -> tagsBuffer.append(String.format("#%s ", tag)));
            tagsString = String.format(" | %s", tagsBuffer.toString());
        }

        return tagsString;
    }

    @Override
    public String toString() {
        char done = this.isDone ? 'X' : ' ';
        String tagsString = getTagsString();
        return String.format("[%c] %s %s", done, this.description, tagsString);
    }
}
