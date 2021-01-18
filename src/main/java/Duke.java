import java.util.ArrayList;

/**
 * A duke chat-bot object.
 */
public class Duke {
    /**
     * Duke message dividers.
     */
    public final static String  DIV = "\n____________________________"
        + "________________________________\n";
    /**
     * Duke current message.
     */
    public String currentMessage = "";
    /**
     * Duke stored task list.
     */
    public ArrayList<Task> taskList;

    /**
     * Instantiates a new duke.
     */
    public Duke() {
        this.currentMessage = "Hey there! I'm Duke\n" +
                "What can I do for you today?";
        System.out.println(this.output());
        taskList = new ArrayList<>();
    }

    /**
     * Parses given input and sets a new current message based on input.
     *
     * @param input the input message.
     * @return integer. 0 represents an terminating entry, 1 represents a continuing entry.
     */
    public int parse(String input) {
        String[] parsedInput = input.split("\\s+");
        switch(parsedInput[0]) {
            case ("blah"):
                this.currentMessage = "blah";
                break;
            case ("list"):
                StringBuilder currList = new StringBuilder();
                for (int i = 0; i < taskList.size(); i++) {
                    Task currTask = taskList.get(i);
                    String currStr = (i+1) + ". [" + currTask.getStatusIcon()
                            + "] " + currTask.description + "\n";
                    currList.append(currStr);
                }
                this.currentMessage = currList.toString()
                        .substring(0, currList.toString().length() - 1);
                break;
            case ("done"):
                int index = Integer.parseInt(parsedInput[1]);
                if (index >= taskList.size()) {
                    this.currentMessage = "Oops! Task not found, please try again.";
                    break;
                }
                Task currTask = taskList.get(index);
                currTask.markAsDone();
                this.currentMessage = "Sweet! I've marked this task as done:\n"
                        + "[" + currTask.getStatusIcon() + "] " + currTask.description;
                break;
            case ("bye"):
                this.currentMessage = "Good bye. Hope to see you again soon!";
                return 0;
            default:
                taskList.add(new Task(input));
                this.currentMessage = "added: " + input;
        }
        return 1;
    }

    /**
     * Outputs the current message.
     *
     * @return Duke output string.
     */
    public String output() {
        return DIV + currentMessage + DIV;
    }
}
