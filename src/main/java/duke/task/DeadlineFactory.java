package duke;

class DeadlineFactory extends AbstractTaskFactory{
    @Override
    protected Task createTask(String parameters) throws IllegalArgumentException {
        String[] inputArray = parameters.split("/by",2);
        if (inputArray[0].isBlank()) {
            throw new IllegalArgumentException("Error: Description of deadline cannot be empty");
        } else if (inputArray.length == 1) {
            throw new IllegalArgumentException("Error: Please add a time limit to your deadline");
        }
        String content = inputArray[0];
        String timeRange = inputArray[1];
        return new Deadline(content,timeRange);
    }
}
