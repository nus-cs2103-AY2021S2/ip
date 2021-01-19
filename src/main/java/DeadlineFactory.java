public class DeadlineFactory extends AbstractTaskFactory{
    @Override
    protected Task createTask(String parameters) {
        String[] inputArray = parameters.split("/by",2);
        String content = inputArray[0];
        String timeRange = inputArray[1];
        return new Deadline(content,timeRange);
    }
}
