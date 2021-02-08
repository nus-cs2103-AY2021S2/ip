package duke.task;

import duke.Parser;

public class DeadlineFactory extends AbstractTaskFactory {
    @Override
    public Task createTask(String parameters) throws IllegalArgumentException {
        String[] inputArray = Parser.parseDeadline(parameters);
        String content = inputArray[0];
        String timeRange = inputArray[1];
        return new Deadline(content, timeRange);
    }
}
