public class DoneCommand implements ICommand {
    private TaskManager tasks;

    DoneCommand(TaskManager tasks) {
        this.tasks = tasks;
    }

    @Override
    public void execute(String parameters) {
        Integer count;
        try {
            count = Integer.parseInt(parameters);
        } catch (NumberFormatException e) {
            count = -1;
        }
        tasks.setTaskDone(count);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.getTasks().get(count-1));
    }
}
