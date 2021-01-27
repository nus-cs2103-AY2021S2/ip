package customClass;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void dataInput(String data) {
        String[] ArrayOfTasksFromLoad = data.split("\n");

        for (String taskInStringForm : ArrayOfTasksFromLoad) {
            String[] currValues = taskInStringForm.split("\\s*---\\s*");
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
    public List<Task> getList() {
        return list;
    }
}
