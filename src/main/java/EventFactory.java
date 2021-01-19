public class EventFactory extends AbstractTaskFactory{
    @Override
    protected Task createTask(String parameters) {
        String[] inputArray = parameters.split("/at",2);
        String content = inputArray[0];
        String timeRange = inputArray[1];
        return new Event(content,timeRange);
    }
}
