package duke;

class DoneCommand implements ICommand {
    private TaskList tasks;

    DoneCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public void execute(String parameters) {
        try {
            Integer count = Integer.parseInt(parameters);
            tasks.setTaskDone(count);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.getTasks().get(count - 1));
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid argument for done");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
