package duke.task;

import duke.Parser;

public class EventFactory extends AbstractTaskFactory {
    @Override
    public Task createTask(String parameters) throws IllegalArgumentException {
        String[] inputArray = Parser.parseEvent(parameters);
        String content = inputArray[0];
        String timeRange = inputArray[1];

        return new Event(content, timeRange);
    }
}
