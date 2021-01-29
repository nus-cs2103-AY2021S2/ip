import Exceptions.InvalidDateException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public Task addNewTask(String userInput) throws InvalidDateException {
        Task newTask;
        String taskName;

        try {
            if (userInput.startsWith("todo")) {
                taskName = userInput.substring(5);
                newTask = new ToDo(taskName);
            } else {
                int index = userInput.indexOf('/');

                String dateString = userInput.substring(index + 4);
                LocalDateTime dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

                if (userInput.startsWith("deadline")) {
                    newTask = new Deadline(userInput.substring(9, index), dateTime);
                } else {
                    newTask = new Event(userInput.substring(6, index), dateTime);
                }
            }
            list.add(newTask);
            return newTask;
        } catch (DateTimeException ex) {
            throw new InvalidDateException();
        }
    }

    public void markAsDone(String userInput) {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
        list.get(taskNumber).markAsDone();
    }

    public void deleteTask(String userInput) {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
        list.remove(taskNumber);
    }
}
