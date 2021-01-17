import java.util.List;

public class AddCmd implements Command {
    private final List<Task> lst;
    private final TaskType taskType;

    public AddCmd(List<Task> lst, TaskType taskType) {
        this.lst = lst;
        this.taskType = taskType;
    }

    @Override
    public String process(String input) {
        Task task;
        String[] words;

        switch (taskType) {
            case TODO:
                task = new Todo(input);
                break;
            case EVENT:
                words = input.split("/at");
                task = new Event(words[0].trim(), words[1].trim());
                break;
            case DEADLINE:
                words = input.split("/by");
                task = new Deadline(words[0].trim(), words[1].trim());
                break;
            default:
                task = new Task("placeholder task");
                break;
        }

        this.lst.add(task);

        return "Got it. I've added this task:\n" +
                "  " +
                task.toString() +
                String.format("Now you have %d tasks in the list", this.lst.size());
    }
}
