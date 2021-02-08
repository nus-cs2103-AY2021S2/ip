package duke.task;

/**
 * Factory Class responsible for creating Event objects.
 */
public class EventFactory extends AbstractTaskFactory {
    /**
     * Creates a Event object using the parameters given.
     * @param parameters Information needed to create event.
     * @return Event object
     * @throws IllegalArgumentException thrown when invalid arguments are given.
     */
    @Override
    public Task createTask(String parameters) throws IllegalArgumentException {
        String[] inputArray = parameters.split("/at", 2);
        assert inputArray != null : "inputArray should not be null";

        if (inputArray[0].isBlank()) {
            throw new IllegalArgumentException("Error: Description of event cannot be empty");
        } else if (inputArray.length == 1) {
            throw new IllegalArgumentException("Error: Please add a start time to your event");
        }

        String content = inputArray[0];
        String timeRange = inputArray[1];

        return new Event(content, timeRange);
    }
}
