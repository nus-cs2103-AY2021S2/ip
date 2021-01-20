package duke;

import java.util.ArrayList;
public class InputHandler {
    private final ArrayList<Task> taskList;

    public InputHandler() {
        this.taskList = new ArrayList<>();
    }

    public InputHandler(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public InputHandler processInput(String input) {
        if (input.equals("list")) {
            System.out.println(">>> Tasks:");
            int i = 1;
            for( Task t : taskList) {
                System.out.printf(">>> %d: %s\n", i, t.getTaskName());
                i++;
            }
            return this;
        } else {
            ArrayList<Task> newList = new ArrayList<>(this.taskList);
//            newList.addAll();
            Task newTask = new Task(input);
            newList.add(newTask);
            System.out.println(">>> added: " + newTask.getTaskName());
            return new InputHandler(newList);
        }

    }
}
