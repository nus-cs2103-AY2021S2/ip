package fakebot.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    String description_1 = "test1";
    String description_2 = "test2";
    @Test
    void getTask_equal() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task test1 = new ToDos(description_1);
        Task test2 = new ToDos(description_2);
        taskList.addTask(test1);
        taskList.addTask(test2);
        assertEquals(test1, taskList.getTask(0), "Task index do not match");
        assertEquals(test2, taskList.getTask(1), "Task index do not match");
    }

    @Test
    void addTask_success() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task test1 = new ToDos(description_1);
        taskList.addTask(test1);
        assertEquals(1, taskList.getSize(), "Task is not added");
    }

    @Test
    void removeTask_success() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task test1 = new ToDos(description_1);
        taskList.addTask(test1);
        taskList.removeTask(0);
        assertEquals(0, taskList.getSize(), "Task is not removed");
    }

    @Test
    void getSize_success() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task test1 = new ToDos(description_1);
        taskList.addTask(test1);
        taskList.addTask(test1);
        taskList.addTask(test1);
        assertEquals(3, taskList.getSize(), "Task List size does not match");
    }

    @Test
    void find_success() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task test1 = new ToDos(description_1);
        Task test2 = new ToDos(description_2);
        taskList.addTask(test1);
        taskList.addTask(test2);
        taskList.addTask(test1);
        taskList.addTask(test2);
        taskList.addTask(test1);
        taskList.addTask(test2);
        taskList.addTask(test1);
        taskList.addTask(test2);
        taskList.addTask(test1);
        taskList.addTask(test1);
        assertEquals(6, taskList.find("est1").size(), "Task size found does not match");
        assertEquals(4, taskList.find("est2").size(), "Task size found does not match");
    }
}