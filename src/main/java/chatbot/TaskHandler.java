package chatbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import chatbot.exceptions.IndexOutOfRangeException;
import chatbot.exceptions.TaskDoneException;
import chatbot.tasks.DeadlineTask;
import chatbot.tasks.EventTask;
import chatbot.tasks.Task;
import chatbot.tasks.TodoTask;

public class TaskHandler {
    private ArrayList<Task> taskList;

    public TaskHandler() {
        this.taskList = new ArrayList<>();
    }

    public void clearTaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public int getLength() {
        return this.taskList.size();
    }

    public void addTodoTask(String taskName) {
        taskList.add(new TodoTask(taskName));
    }

    public void addDeadlineTask(String taskName, LocalDate deadline) {
        taskList.add(new DeadlineTask(taskName, deadline));
    }

    public void addEventTask(String taskName, String time) {
        taskList.add(new EventTask(taskName, time));
    }

    public Task taskIsDone(int index) throws IndexOutOfRangeException, TaskDoneException {
        try {
            Task task = taskList.get(index - 1);
            if (!task.getIsDone()) {
                task.taskDone();
                return task;
            } else {
                throw new TaskDoneException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfRangeException();
        }
    }

    public Task deleteTask(int index) throws IndexOutOfRangeException {
        try {
            Task task = taskList.get(index - 1);
            taskList.remove(index - 1);
            return task;
        } catch(IndexOutOfBoundsException e) {
            throw new IndexOutOfRangeException();
        }
    }

    public void loadTaskList(ArrayList<String> storedTaskList) {
        for (String eachTask : storedTaskList) {
            String[] words = eachTask.split("\\|");
            String type = words[0].strip();
            boolean isDone = words[1].strip().equals("1");
            String taskName = words[2].strip();

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
            switch (type) {
                case "T": {
                    Task task = new TodoTask(taskName);
                    if (isDone) {
                        task.taskDone();
                    }
                    this.taskList.add(task);
                    break;
                }
                case "D": {
                    LocalDate deadline = LocalDate.parse(words[3].strip(), dateFormat);
                    Task task = new DeadlineTask(taskName, deadline);
                    if (isDone) {
                        task.taskDone();
                    }
                    this.taskList.add(task);
                    break;
                }
                case "E": {
                    Task task = new EventTask(taskName, words[3]);
                    if (isDone) {
                        task.taskDone();
                    }
                    this.taskList.add(task);
                    break;
                }
            }

        }
    }



}
