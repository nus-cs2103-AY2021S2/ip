package duke;

class EventFactory extends AbstractTaskFactory{
    @Override
    protected Task createTask(String parameters) throws IllegalArgumentException {
        String[] inputArray = parameters.split("/at",2);

        if (inputArray[0].isBlank()) {
            throw new IllegalArgumentException("Error: Description of event cannot be empty");
        } else if (inputArray.length == 1) {
            throw new IllegalArgumentException("Error: Please add a start time to your event");
        }

        String content = inputArray[0];
        String timeRange = inputArray[1];

        return new Event(content,timeRange);
    }
}
