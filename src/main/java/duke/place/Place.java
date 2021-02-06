package duke.place;

/**
 * Abstract class for Tasks.
 */
public class Place {

    private final String description;
    private final String location;

    /**
     * Place builder prototype.
     *
     * @param description Parsed string contains description of this task.
     */
    public Place(String description, String location) {
        this.description = description;
        this.location = location;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLocation() {
        return this.location;
    }

    @Override
    public String toString() {
        return location + ": " + description + "\n";
    }

}
