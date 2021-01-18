import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DukeBot {
    private static final String BORDER = "\t___________________________________\n";
    private final List<Task> taskList = new ArrayList<>();
    private int numTasks;

    public DukeBot() {
        echo("greeting");
    }

    public boolean echo(String input) {
        String output = "";
        String[] commandStr = input.trim().split("\\s+");
        String taskAction = commandStr[0];
        boolean continueInput = true;

        switch (taskAction) {
            case "greeting":
                output = BORDER + "\t Hello! I'm Duke\n" + "\t What can I do for you?\n" + BORDER;
                break;
            case "bye":
                output = BORDER + "\t" + " Bye. Hope to see you again soon!\n" + BORDER;
                continueInput = false;
                break;
            case "list":
                output = retrieveList();
                break;
            case "done":
                output = markDoneTask(Integer.parseInt(commandStr[1]) - 1);
                break;
            case "todo":
            case "deadline":
            case "event":
                this.numTasks++;
                output = handleTask(taskAction, commandStr);
                break;
            default:
                output = addTask(taskAction);
                break;
        }
        System.out.println(output);
        return continueInput;
    }

    public String addTask(String taskAction) {
        String currentText = BORDER + "\t added: " + taskAction + "\n" + BORDER;
        Task newTask = new Task(taskAction);
        this.taskList.add(newTask);
        return currentText;
    }

    public String markDoneTask(int index) {
        Task doneTask = this.taskList.get(index);
        doneTask.markAsDone();
        String currentText = BORDER +  "\t" + " Nice! I've marked this task as done:\n" +  "\t  "
                + doneTask.toString() + "\n" + BORDER;
        return currentText;
    }

    public String retrieveList() {
        StringBuilder currText = new StringBuilder(BORDER + "\t" + " Here are the tasks in your list:\n");

        for (int num = 1; num <= this.taskList.size(); num++) {
            Task currentTask = this.taskList.get(num-1);
            currText.append("\t ").append(num).append(".").append(currentTask.toString()).append("\n");
        }
        currText.append(BORDER);
        return currText.toString();
    }

    public String handleTask(String taskAction, String[] commandStr) {
        int num;
        Task newTask;
        StringBuilder description = new StringBuilder();
        StringBuilder dateTime = new StringBuilder();
        List<String> taskDetails =  Arrays.asList(commandStr);
        String currText = BORDER + "\t" + " Got it. I've added this task: \n";

        if(!taskAction.equals("todo")) {
            for(num = 1; num < taskDetails.size(); num++) {
                String curr = taskDetails.get(num);
                if(curr.contains("/")) {
                    break;
                }
                description.append(curr).append(" ");
            }
            for(int i = num + 1; i < taskDetails.size(); i++) {
                dateTime.append(taskDetails.get(i)).append(" ");
            }
            if(taskAction.equals("event")) {
                newTask = new Event(description.toString(), dateTime.toString());
            } else {
                newTask = new Deadline(description.toString(), dateTime.toString());
            }
        } else {
            for(num = 1; num < taskDetails.size(); num++) {
                String curr = taskDetails.get(num);
                description.append(curr).append(" ");
            }
            newTask = new ToDo(description.toString());
        }
        this.taskList.add(newTask);
        currText +=  "\t  " + newTask.toString() + "\n\t Now you have "
                + this.numTasks + " tasks in the list.\n" + BORDER;
        return currText;
    }
}