package duck.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<Task>();

    public TaskList(String[] data) {
        for (int i = 0; data[i] != null; i++) {
            String datas = data[i];
            String[] dataSplit = datas.split("\\|");
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
            }
            if (dataSplit[1].trim().equals("1")) {
                taskList.get(taskList.size() - 1).markAsDone();
            }
        }
    }

    public int getSizeOfTasks() {
        return taskList.size();
    }

    public Task getTask(Integer number) {
        return taskList.get(number);
    }

    public void removeTask(Integer number) {
        taskList.remove(taskList.get(number));
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

}
