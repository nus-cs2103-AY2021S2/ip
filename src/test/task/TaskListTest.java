package task;

import org.junit.jupiter.api.Test;
import stub.DeadlineStub;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void getTally() {
        try {
            TaskList t1 = new TaskList();
            for (int i = 0; i < 5; i++) {
                t1.addTask(new Todo("abc"));
            }
            assertEquals("     Currently you have 5 tasks in the list.", t1.getTally());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void addTask() {
        try {
            TaskList t1 = new TaskList();
            t1.addTask(new Todo("abc"));
            t1.addTask(new DeadlineStub("def", "2011-10-11 20:20"));

            assertEquals("[[T][\u2718] abc, [D][\u2718] def (by: 11 Oct 2011 08:20 PM)]", t1.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}