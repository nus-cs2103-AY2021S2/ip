package duke;

import duke.task.Task;

class DeleteCommand implements ICommand{
    TaskList tasks;

    public DeleteCommand(TaskList tasks) {
        this.tasks = tasks;
    }
    @Override
    public void execute(String parameters) {
        try {
            int count = Integer.parseInt(parameters);
            Task removedTask = tasks.deleteTask(count);

            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask.toString());
            System.out.println(String.format("Now you have %d tasks in the list",tasks.getTasks().size()));
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid argument for delete");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}
