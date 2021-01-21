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
            int idxDone = Integer.parseInt(splitInput[1]) - 1;
            Task newTask = this.taskList.get(idxDone).setIsDone(true);
            ArrayList<Task> newList = new ArrayList<>(this.taskList);
            newList.set(idxDone, newTask);
            System.out.printf(">>> Nice! I've marked this task as done:\n  [%s] [%s] %s \n",
                    newTask.getTaskType(), newTask.getStatusIcon(), newTask.getDescription());
            return new InputHandler(newList);
        } else {
            ArrayList<Task> newList = new ArrayList<>(this.taskList);
            if(command.equals("todo")) {
                if(splitInput.length == 1) {
                    // replace with proper exception handling in future
                    System.out.println("The description of a todo cannot be empty.");
                    return this;
                }
                Task newTask = new ToDo(splitInput[1]);
                newList.add(newTask);
                System.out.printf(">>> added:\n   [%s] [%s] %s\n",
                        newTask.getTaskType(), newTask.getStatusIcon(), newTask.getDescription());
                System.out.printf("You have %d tasks in the list\n", newList.size());
                return new InputHandler(newList);
            } else if (command.equals("deadline")) {
                // parse input
                String[] taskDetails = splitInput[1].split("/by");
                String taskDescription = taskDetails[0];
                String endTime =  taskDetails[1];
                // create Deadline Task
                Deadline newDeadline = new Deadline(taskDescription, endTime);
                newList.add(newDeadline);
                System.out.printf(">>> added:\n   [%s] [%s] %s (by: %s)\n",
                        newDeadline.getTaskType(), newDeadline.getStatusIcon(),
                        newDeadline.getDescription(), newDeadline.getEndTime());
                System.out.printf("You have %d tasks in the list\n", newList.size());
                return new InputHandler(newList);
            } else if (command.equals("event")) {
                // parse input
                String[] taskDetails = splitInput[1].split("/at");
                String taskDescription = taskDetails[0];
                String eventTime =  taskDetails[1];

                // create Event Task
                Event newEvent = new Event(taskDescription, eventTime);
                newList.add(newEvent);
                System.out.printf(">>> added:\n   [%s] [%s] %s\n",
                        newEvent.getTaskType(), newEvent.getStatusIcon(), newEvent.getDescription());
                System.out.printf("You have %d tasks in the list\n", newList.size());
                return new InputHandler(newList);
            } else {
                System.out.println("Sorry Human. I do not comprehend, please read the user manual " +
                        "again and try another command");
                return this;
            }
        }

    }

}
