package duke.tasklist;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.List;
import java.util.ArrayList;

/**
 * Handles the storing of the list of tasks as well as conversion of user's input Task objects.
 */
public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Converts the user's input from a valid string form to a Task object.
     * The object is then added to a list of objects.
     *
     * @param data String of user's input.
     */
    public void dataInput(String data) {
        String[] ArrayOfTasksFromLoad = data.split("\n");

        for (String taskInStringForm : ArrayOfTasksFromLoad) {
            String[] currValues = taskInStringForm.split("\\s*---\\s*");
            assert(currValues[0] == "T" || currValues[0] == "D" || currValues[0] == "e");

            switch (currValues[0]) {
            case "T":
                Todo t = new Todo(currValues[2], currValues[1].equals("1"));
                list.add(t);
                break;
            case "D":
                Deadline d = new Deadline(currValues[2], currValues[1].equals("1"), currValues[3]);
                list.add(d);
                break;
            case "E":
                Event e = new Event(currValues[2], currValues[1].equals("1"), currValues[3]);
                list.add(e);
                break;
            default:
                System.out.println("Error with the written file.");
            }
        }
    }

    /**
     * Returns a list of Tasks.
     *
     * @return List of Tasks.
     */
    public List<Task> getList() {
        return list;
    }
}
