import java.util.ArrayList;

public class DukeBot {
    private static final String BORDER = "\t___________________________________\n";
    private String output;
    private final ArrayList<Task> taskList = new ArrayList<>();

    public DukeBot() {
        greeting();
    }

    public void greeting() {
        output = BORDER + "\t Hello! I'm Duke\n" + "\t What can I do for you?\n" + BORDER;
        System.out.println(output);
    }

    public boolean echo(String input) {
        String[] doneCommand = new String[2];
        boolean continueInput = true;

        if(input.contains("done")) {
            doneCommand = input.split(" ");
            input = doneCommand[0];
        }
        switch (input) {
            case "bye":
                output = BORDER + "\t" + " Bye. Hope to see you again soon!\n" + BORDER;
                continueInput = false;
                break;
            case "list":
                output = retrieveList(taskList);
                break;
            case "done":
                Task doneTask = taskList.get(Integer.parseInt(doneCommand[1]) - 1);
                doneTask.markAsDone();
                output = BORDER +  "\t" + " Nice! I've marked this task as done:\n" +  "\t  "
                        + doneTask.getStatusIcon() + doneTask.description + "\n" + BORDER;
                break;
            default:
                output = BORDER + "\t added: " + input + "\n" + BORDER;
                Task newTask = new Task(input);
                taskList.add(newTask);
                break;
        }
        System.out.println(output);
        return continueInput;
    }

    public String retrieveList(ArrayList<Task> taskList) {
        StringBuilder str = new StringBuilder(BORDER+ "\t" + " Here are the tasks in your list:\n");

        for (int num = 1; num <= taskList.size(); num++) {
            Task currentTask = taskList.get(num-1);
            str.append("\t ").append(num).append(".").
                    append(currentTask.getStatusIcon()).
                    append(currentTask.description).append("\n");
        }
        str.append(BORDER);
        return str.toString();
    }
}