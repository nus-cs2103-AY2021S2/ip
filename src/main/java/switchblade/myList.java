package switchblade;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains an ArrayList of Tasks with its own methods that handle user printing and saving to file
 *
 * @author leeyueyang
 */
public class myList {
    ArrayList<Task> taskList;

    public myList() {
        taskList = new ArrayList<>();
    }

    public void addTask(String taskDescription) {
        Task task = new Task(taskDescription);

        taskList.add(task);
        Ui.addedTask(taskList);
        this.save();
    }

    public void addDeadline(String description, String datetime) {
        Deadline deadline = new Deadline(description, datetime);

        taskList.add(deadline);
        Ui.addedTask(taskList);
        this.save();
    }

    public void addEvent(String description, String startDatetime, String endDatetime) {
        myEvent event = new myEvent(description, startDatetime, endDatetime);

        taskList.add(event);
        Ui.addedTask(taskList);
        this.save();
    }

    public void markCompleted(int index) {
        taskList.get(index).markCompleted();
        Ui.completedTask();
        this.save();
    }

    public void delete(int index) {
        taskList.remove(index);
        Ui.removeTask(taskList);
        this.save();
    }

    public void retrieve()  {
        try {
            String filepath = "./ip/data";
            taskList = Storage.retrieve(filepath);
            Ui.initRetrieveList(this);
        } catch (FileNotFoundException e) {
            Ui.fileNotFound();
        }
    }

    public ArrayList<Task> findTasks(String input) {
        ArrayList<Task> matchedTaskArr = new ArrayList<>();

        for(Task t : taskList) {
            if (t.getDescription().contains(input)) {
                matchedTaskArr.add(t);
            }
        }

        return matchedTaskArr;
    }

    public int getNumTasks() {
        return taskList.size();
    }

    private void save() {
        try {
            String filepath = "./ip/data";
            Storage.save(taskList, filepath);
        } catch (IOException e) {
            Ui.fileError();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            sb.append("\n"
                    + (i + 1)
                    + ". "
                    + taskList.get(i).toString()
            );
        }

        sb.append("\n");

        return sb.toString();
    }
}