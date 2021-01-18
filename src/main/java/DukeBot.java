import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DukeBot {
    private static final String BORDER = "\t___________________________________\n";
    private final ArrayList<Task> taskList = new ArrayList<>();
    private int numTasks;
    private String output;

    public DukeBot() {
        output = BORDER + "\t Hello! I'm Duke\n" + "\t What can I do for you?\n" + BORDER;
        System.out.println(output);
    }

    public boolean echo(String input) {
        String[] commandStr = input.trim().split("\\s+");
        String taskAction = commandStr[0];
        boolean continueInput = true;

        switch (taskAction) {
            case "bye":
                output = BORDER + "\t" + " Bye. Hope to see you again soon!\n" + BORDER;
                continueInput = false;
                break;
            case "list":
                output = retrieveList();
                break;
            case "done":
                output = markDoneTask(Integer.parseInt(commandStr[1]));
                break;
            case "todo":
            case "deadline":
            case "event":
                this.numTasks++;
                output = handleTask(taskAction, commandStr);
                break;
            case "delete":
                this.numTasks--;
                output = handleDeleteTask(Integer.parseInt(commandStr[1]));
                break;
            default:
                DukeException exception = new DukeException("invalid input", "");
                output = BORDER + "\t " + exception.getMessage() + "\n" + BORDER;
                break;
        }
        System.out.println(output);
        return continueInput;
    }

    public String markDoneTask(int index) {
        Task doneTask = this.taskList.get(index-1);
        doneTask.markAsDone();
        return BORDER +  "\t" + " Nice! I've marked this task as done:\n" +  "\t  "
                + doneTask.toString() + "\n" + BORDER;
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
        Task newTask;
        StringBuilder description = new StringBuilder();
        List<String> taskDetails =  Arrays.asList(commandStr);
        String currText = BORDER + "\t" + " Got it. I've added this task: \n";

        if(!taskAction.equals("todo")) {
            String[] result = handleEventDeadLine(taskDetails);
            if(taskAction.equals("event")) {
                newTask = new Event(result[0], result[1]);
            } else {
                newTask = new Deadline(result[0], result[1]);
            }
        } else {
            for(int num = 1; num < taskDetails.size(); num++) {
                String curr = taskDetails.get(num);
                description.append(curr).append(" ");
            }
            newTask = new ToDo(description.toString());
        }

        if(newTask.getDescription().equals("")) {
            DukeException exception = new DukeException("empty", taskAction);
            return BORDER + "\t " + exception.getMessage() + "\n" + BORDER;
        } else {
            this.taskList.add(newTask);
            currText +=  "\t  " + newTask.toString() + "\n\t Now you have "
                    + this.numTasks + " tasks in the list.\n" + BORDER;
            return currText;
        }
    }

    public String[] handleEventDeadLine(List<String> taskDetails) {
        int num;
        StringBuilder description = new StringBuilder();
        StringBuilder dateTime = new StringBuilder();
        String[] result = new String[2];

        for(num = 1; num < taskDetails.size(); num++) {
            String curr = taskDetails.get(num);
            if(curr.contains("/")) {
                break;
            }
            description.append(curr).append(" ");
        }
        for(int i = num + 1; i < taskDetails.size(); i++) {
            dateTime.append(taskDetails.get(i));
            if(i < taskDetails.size() - 1) {
                dateTime.append(" ");
            }
        }
        result[0] = description.toString();
        result[1] = dateTime.toString();
        return result;
    }

    public String handleDeleteTask(int index) {
        Task deleteTask = this.taskList.get(index-1);
        this.taskList.remove(deleteTask);
        return BORDER +  "\t" + "Noted. I've removed this task: \n" +
                "\t  " + deleteTask.toString() + "\n\t Now you have "
                + this.numTasks + " tasks in the list.\n" + BORDER;

    }
}