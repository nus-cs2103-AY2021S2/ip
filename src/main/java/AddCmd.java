import java.util.List;

public class AddCmd implements Command {
    private final List<Task> lst;
    private final TaskType taskType;

    public AddCmd(List<Task> lst, TaskType taskType) {
        this.lst = lst;
        this.taskType = taskType;
    }

    private void validateNotEmpty(String str, String msg) {
        if (str.equals("")) {
            throw new DukeException(msg);
        }
    }

    private String[] trimStrArr(String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = strArr[i].trim();
        }

        return strArr;
    }

    @Override
    public String process(String input) {
        Task task;
        String[] words;

        validateNotEmpty(input, "OOPS!!! The description of a task cannot be empty");

        switch (taskType) {
            case TODO:
                task = new Todo(input);
                break;
            case EVENT:
                words = input.split("/at");
                trimStrArr(words);
                task = new Event(words[0], words[1]);
                break;
            case DEADLINE:
                words = input.split("/by");
                trimStrArr(words);
                task = new Deadline(words[0], words[1]);
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
