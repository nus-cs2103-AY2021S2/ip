import java.util.StringJoiner;

public class AddCommand implements ICommand {
    private TaskManager tasks;
    String string;
    AddCommand(TaskManager tasks) {
        this.tasks = tasks;
    }

    @Override
    public void execute(String[] parameters) {
        StringJoiner joiner = new StringJoiner(" ");
        for (String string:parameters) {
            joiner.add(string);
        }
        String input = joiner.toString();
        this.tasks.addTask(input);

        System.out.println(input);
    }
}
