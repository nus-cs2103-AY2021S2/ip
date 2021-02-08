package duke.task;

import duke.Parser;

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
        String[] inputArray = Parser.parseEvent(parameters);
        String content = inputArray[0];
        String timeRange = inputArray[1];

        return new Event(content, timeRange);
    }
}
