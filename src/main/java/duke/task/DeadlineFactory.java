package duke.task;

import duke.Parser;

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
        String[] inputArray = Parser.parseDeadline(parameters);
        String content = inputArray[0].trim();
        String timeRange = inputArray[1].trim();
        return new Deadline(content, timeRange);
    }
}
