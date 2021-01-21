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
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];

        if (command.equals("list")) {
            System.out.println(">>> Here are your tasks:");
            int i = 1;
            for( Task t : taskList) {
                System.out.printf("    %d: [%s] %s\n", i,
                        t.getStatusIcon(), t.getDescription());
                i++;
            }
            return this;
        } else if (command.equals("done")){
            String taskInfo = splitInput[1];
            int idxDone = Integer.parseInt(taskInfo) - 1;
            Task newTask = this.taskList.get(idxDone).setIsDone(true);
            ArrayList<Task> newList = new ArrayList<>(this.taskList);
            newList.set(idxDone, newTask);
            System.out.printf(">>> Nice! I've marked this task as done:\n  [%s] %s \n ",
                    newTask.getStatusIcon(), newTask.getDescription());
            return new InputHandler(newList);
        } else {
            ArrayList<Task> newList = new ArrayList<>(this.taskList);
//            newList.addAll();
            Task newTask = new Task(input);
            newList.add(newTask);
            System.out.println(">>> added: " + newTask.getDescription());
            return new InputHandler(newList);
        }

    }
}
