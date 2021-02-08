package duke.task;

/**
 * Factory Class responsible for creating Deadline objects.
 */
public class DeadlineFactory extends AbstractTaskFactory {
    /**
     * Creates a Deadline object using the parameters given.
     * @param parameters Information needed to create Deadline.
     * @return Deadline object
     * @throws IllegalArgumentException thrown when invalid arguments are given.
     */
    @Override
    public Task createTask(String parameters) throws IllegalArgumentException {
        String[] inputArray = parameters.split("/by", 2);
        assert inputArray != null : "inputArray should not be null";

        if (inputArray[0].isBlank()) {
            throw new IllegalArgumentException("Error: Description of deadline cannot be empty");
        } else if (inputArray.length == 1) {
            throw new IllegalArgumentException("Error: Please add a time limit to your deadline");
        }
        String content = inputArray[0];
        String timeRange = inputArray[1];
        return new Deadline(content, timeRange);
    }
}
