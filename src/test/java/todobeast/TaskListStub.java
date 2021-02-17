package todobeast;

import todobeast.tasks.Deadline;
import todobeast.tasks.Event;
import todobeast.tasks.Task;
import todobeast.tasks.Todo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TaskListStub extends TaskList {

    public TaskListStub(List<Task> taskList) {
        super(testList());
    }

    public static List<Task> testList() {
        List<Task> list = new ArrayList<>();
        list.add(new Todo("test", false, null));
        list.add(new Deadline("test", false, LocalDate.parse("2000-01-01"), LocalTime.parse("00:00"), null));
        list.add(new Event("test", false, LocalDate.parse("2000-01-01"), LocalTime.parse("00:00"), null));
        return list;
    }

    @Override
    public String toString() {
        return taskList.toString();
    }
}
