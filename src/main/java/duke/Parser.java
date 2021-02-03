package duke;

import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.ListCommand;

import java.time.LocalDate;
import java.util.ArrayList;

public class Parser {

    /**
     * Method to parse commands given by user
     * @param userInput
     * @return
     */
    public Command parseCommand(String userInput) {
        String[] splitInput = userInput.split(" ", 2);
        String command = splitInput[0];

        if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("done")){
            int indexDone = Integer.parseInt(splitInput[1]) - 1;
            return new DoneCommand(indexDone);
        } else if (command.equals("delete")){
            int idxDel = Integer.parseInt(splitInput[1]) - 1;
            ArrayList<Task> newList = new ArrayList<>(this.taskList);
            Task deleteTask = newList.get(idxDel);
            newList.remove(idxDel);
            System.out.printf(">>> Task deleted successfully:\n  [%s] [%s] %s\n ",
                    deleteTask.getTaskType(), deleteTask.getStatusIcon(),
                    deleteTask.getTaskDescription());
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
                        newTask.getTaskType(), newTask.getStatusIcon(),
                        newTask.getTaskDescription());
                System.out.printf("You have %d tasks in the list\n", newList.size());
                return new InputHandler(newList);
            } else if (command.equals("deadline")) {
                // parse input
                String[] taskDetails = splitInput[1].split("/by");
                String taskDescription = taskDetails[0];
                LocalDate endTime =  LocalDate.parse(taskDetails[1]);
                // create Deadline Task
                Deadline newDeadline = new Deadline(taskDescription, endTime);
                newList.add(newDeadline);
                System.out.printf(">>> added:\n   [%s] [%s] %s (by: %s)\n",
                        newDeadline.getTaskType(), newDeadline.getStatusIcon(),
                        newDeadline.getTaskDescription(), newDeadline.getEndTime());
                System.out.printf("You have %d tasks in the list\n", newList.size());
                return new InputHandler(newList);
            } else if (command.equals("event")) {
                // parse input
                String[] taskDetails = splitInput[1].split("/at");
                String taskDescription = taskDetails[0];
                LocalDate eventTime =  LocalDate.parse(taskDetails[1]);

                // create Event Task
                Event newEvent = new Event(taskDescription, eventTime);
                newList.add(newEvent);
                System.out.printf(">>> added:\n   [%s] [%s] %s (at: %s)\n",
                        newEvent.getTaskType(), newEvent.getStatusIcon(),
                        newEvent.getTaskDescription(), newEvent.getEventTime());
                System.out.printf("You have %d tasks in the list\n", newList.size());
                return new InputHandler(newList);
            } else {
                System.out.println("Sorry Human. I do not comprehend, please read the user manual " +
                        "again and try another command");
                return this;
            }

        }

    }

    public Command parseDone(String arg) {
        int idxDone = Integer.parseInt(arg) - 1;
        Task newTask = this.taskList.get(idxDone).setIsDone(true);
        ArrayList<Task> newList = new ArrayList<>(this.taskList);
        newList.set(idxDone, newTask);
        System.out.printf(">>> Nice! I've marked this task as done:\n  [%s] [%s] %s\n",
                newTask.getTaskType(), newTask.getStatusIcon(), newTask.getTaskDescription());
        return new InputHandler(newList);
    }
}
