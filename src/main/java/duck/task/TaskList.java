package duck.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<Task>();

    /**
     * initialize  the Task list object
     *
     * @param dataArray use data to form task list

     */
    public TaskList(String[] dataArray) {
        for (int i = 0; dataArray[i] != null; i++) {
            String data = dataArray[i];
            String[] dataSplit = data.split("\\|");
            switch (dataSplit[0].trim()) {
            case "T":
                taskList.add(new Todo(dataSplit[2].trim()));
                break;
            case "D":
                taskList.add(new Deadline(dataSplit[2].trim(), dataSplit[3].trim()));
                break;
            case "E":
                taskList.add(new Event(dataSplit[2].trim(), dataSplit[3].trim()));
                break;
            default:
                break;
            }
            if (dataSplit[1].trim().equals("1")) {
                taskList.get(taskList.size() - 1).markAsDone();
            }
        }
    }

    /**
     * get size of task list
     *
     * @return number of tasks
     */
    public int getSizeOfTasks() {
        return taskList.size();
    }

    /**
     * get a task from task list
     *
     * @param number the number of task
     * @return task
     */
    public Task getTask(Integer number) {
        return taskList.get(number);
    }

    /**
     * remove task from task list
     *
     * @param number the number of the choosing task
     */
    public void removeTask(Integer number) {
        taskList.remove(taskList.get(number));
    }

    /**
     * add a new task to task list
     *
     * @param task
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Using keyword to find task from task list
     *
     * @param description the keyword
     * @return a string array includes the found task
     */
    public String[] findTask(String description) {
        int numberOfFinding = 0;
        String[] findTask = new String[100];
        for (int i = 0; i < this.getSizeOfTasks(); i++) {
            if (this.getTask(i).description.contains(description)) {
                findTask[numberOfFinding] = i + 1 + "." + this.getTask(i).getTaskInfo();
                numberOfFinding++;
            }
        }
        assert (numberOfFinding < 100);
        return findTask;
    }
}
