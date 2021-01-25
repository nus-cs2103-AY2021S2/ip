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
        Pattern p = Pattern.compile("(\\d)\\. \\[([TDE])\\] \\[([X ])\\] (.+) \\(at: (.+)\\)");
        Matcher m = p.matcher(strTask);
        m.find();

        switch(m.group(2)) {
            case "T":
                this.addTaskToList(new TodoTask(m.group(4)));
                break;

            case "E":
                this.addTaskToList(new EventTask(m.group(4), m.group(5)));
                break;

            case "D":
                this.addTaskToList(new DeadlineTask(m.group(4), m.group(5)));
                break;

            default:
                throw new MikeInvalidInputException("No such task type");
        }
        if (!m.group(3).equals(" ")) {
            this.getNthTask(Integer.parseInt(m.group(1))).isDone();
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
