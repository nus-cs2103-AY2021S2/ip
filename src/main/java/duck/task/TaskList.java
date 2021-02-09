package duck.task;

import java.util.ArrayList;
import java.util.Arrays;

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

    /**
     * create a schedule of all tasks
     *
     * @return
     */
    public ArrayList<ArrayList<String>> scheduleTask() {
        ArrayList<ArrayList<String>> schedule = new ArrayList<ArrayList<String>>();
        schedule.add(new ArrayList<String>(Arrays.asList("Todo")));
        for (int i = 0; i < this.getSizeOfTasks(); i++) {
            if (this.getTask(i) instanceof Todo) {
                schedule.get(0).add(this.getTask(i).getTaskInfo());
            }

            if (this.getTask(i) instanceof TaskWithDate) {
                TaskWithDate taskWithDate = (TaskWithDate) this.getTask(i);
                String taskDate = taskWithDate.getDate();
                Boolean isRecorded = false;
                for (int j = 1; j < schedule.size(); j++) {
                    if (taskDate.equals(schedule.get(j).get(0))) {
                        schedule.get(j).add(taskWithDate.getTaskInfo());
                        isRecorded = true;
                        break;
                    }
                }
                if (!isRecorded) {
                    schedule.add(new ArrayList<>(
                            Arrays.asList(taskDate, taskWithDate.getTaskInfo())));
                }
            }
        }
        return schedule;
    }

}
