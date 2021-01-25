import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {
    private List<Task> taskList;
    private int numTasks = 0;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Add task to list of tasks then increment number of tasks stored
     *
     * @param task task to be added to list
     */
    public void addTaskToList(Task task) {
        taskList.add(task);
        numTasks++;
    }

    /**
     * Get number of tasks stored in task list
     *
     * @return num of tasks in list
     */
    public int getNumTasks() {
        return numTasks;
    }

    /**
     * return nth task starting at index 1
     *
     * @param i the index of the task to be retrieved
     * @return the Task object
     */
    public Task getNthTask(int i) {
        return this.taskList.get(i - 1);
    }

    /**
     * deleted the nth task starting at index 1
     *
     * @param i the index of the task to be deleted
     */
    public void deleteNthTask(int i) {
        this.numTasks--;
        this.taskList.remove(i - 1);
    }

    public void strToTask(String strTask) throws MikeInvalidInputException {
        Pattern pattern = Pattern.compile("(\\d)\\. \\[([TDE])\\] \\[([X ])\\] (.+)");
        Matcher matcher = pattern.matcher(strTask);
        Matcher descriptionMatcher;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a");
        LocalDateTime dateTimeObject;

        matcher.find();
        boolean isDoneTask = matcher.group(3).equals("X");

        switch(matcher.group(2)) {
            case "T":
                this.addTaskToList(new TodoTask(matcher.group(4)));
                break;

            case "E":
                pattern = Pattern.compile("(.+) (?=\\(at: (\\d\\d \\w\\w\\w \\d\\d\\d\\d, \\d\\d:\\d\\d:\\d\\d " +
                        "\\w\\w)\\))");
                descriptionMatcher = pattern.matcher(matcher.group(4));
                descriptionMatcher.find();
                dateTimeObject = LocalDateTime.parse(descriptionMatcher.group(2), format);
                this.addTaskToList(new EventTask(descriptionMatcher.group(1), dateTimeObject));
                break;

            case "D":
                pattern = Pattern.compile("(.+) (?=\\(by: (\\d\\d \\w\\w\\w \\d\\d\\d\\d, \\d\\d:\\d\\d:\\d\\d " +
                        "\\w\\w)\\))");
                descriptionMatcher = pattern.matcher(matcher.group(4));
                descriptionMatcher.find();
                dateTimeObject = LocalDateTime.parse(descriptionMatcher.group(2), format);
                this.addTaskToList(new DeadlineTask(descriptionMatcher.group(1), dateTimeObject));
                break;

            default:
                throw new MikeInvalidInputException("No such task type");
        }
        if (isDoneTask) {
            this.getNthTask(Integer.parseInt(matcher.group(1))).completeTask();
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        for (int i = 1; i <= numTasks; i++) {
            buffer.append(i);
            buffer.append(". ");
            buffer.append(taskList.get(i - 1).toString());
            buffer.append("\n");
        }
        return buffer.toString();
    }

}
