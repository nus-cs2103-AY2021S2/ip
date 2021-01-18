import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;

/**
 * A duke chat-bot object.
 */
public class Duke {
    /**
     * Duke stored task list.
     */
    public ArrayList<Task> taskList = new ArrayList<>();
    /**
     * Duke message dividers.
     */
    public final static String  DIV = "\n____________________________"
        + "________________________________\n";
    /**
     * Duke task headers.
     */
    public final static String TASK_HEADER = "Alrighty! I've added this task:\n";
    /**
     * Duke task footer1.
     */
    public final static String TASK_FOOTER1 = "\nNow you have ";
    /**
     * Duke task footer2.
     */
    public final static String TASK_FOOTER2 = " tasks in the list.";
    /**
     * Duke current message.
     */
    public String currentMessage;
    /**
     * Instantiates a new duke.
     */
    public Duke() {
        this.currentMessage = "Hey there! I'm Duke\n" +
                "What can I do for you today?";
        System.out.println(this.output());
    }

    /**
     * Parses given input and sets a new current message based on input.
     *
     * @param input the input message.
     * @return integer. 0 represents an terminating entry, 1 represents a continuing entry.
     */
    public int parse(String input) {
//        String parsedInput = input.substring(0, input.indexOf(" "));
        String[] parsedInput = input.split("\\s+", 2);
        switch(parsedInput[0]) {
            case (""):
                this.currentMessage = "Oops, I cannot process nothing as input";
                break;
            case ("blah"):
                this.currentMessage = "blah";
                break;
            case ("todo"): {
                if (parsedInput.length < 2 || parsedInput[1].length() == 0) {
                    this.currentMessage = "Oops! One or more arguments "
                            + "are not valid, please try again";
                    break;
                } else {
                    Todo currTask = new Todo(parsedInput[1]);
                    taskList.add(currTask);
                    this.currentMessage = TASK_HEADER + currTask
                            + TASK_FOOTER1 + taskList.size() + TASK_FOOTER2;
                }
                break;
            }
            case ("event"): {
                if (parsedInput.length < 2) {
                    this.currentMessage = "Oops! One or more arguments "
                            + "are not valid, please try again";
                    break;
                }
                String[] args = parsedInput[1].split("/at", 2);
                if (args.length < 2 || args[1].length() == 0) {
                    this.currentMessage = "Oops! One or more arguments "
                            + "are not valid, please try again";
                    break;
                }
                Event currEvent = new Event(args[0], args[1]);
                taskList.add(currEvent);
                this.currentMessage = TASK_HEADER + currEvent
                        + TASK_FOOTER1 + taskList.size() + TASK_FOOTER2;
                break;
            }
            case ("deadline"): {
                if (parsedInput.length < 2) {
                    this.currentMessage = "Oops! One or more arguments "
                            + "are not valid, please try again";
                    break;
                }
                String[] args = parsedInput[1].split("/by", 2);
                if (args.length < 2 || args[1].length() == 0) {
                    this.currentMessage = "Oops! One or more arguments "
                            + "are not valid, please try again";
                    break;
                }
                Deadline currDeadline = new Deadline(args[0], args[1]);
                taskList.add(currDeadline);
                this.currentMessage = TASK_HEADER + currDeadline
                        + TASK_FOOTER1 + taskList.size() + TASK_FOOTER2;
                break;
            }
            case ("list"):
                StringBuilder currList = new StringBuilder();
                for (int i = 0; i < taskList.size(); i++) {
                    Task currTask = taskList.get(i);
                    String currStr = (i+1) + " \u00BB " + currTask.toString() + "\n";
                    currList.append(currStr);
                }
                this.currentMessage = currList.substring(0,
                        currList.toString().length() - 1);
                break;
            case ("done"): {
                if (parsedInput.length < 2) {
                    this.currentMessage = "Oops! One or more arguments "
                            + "are not valid, please try again";
                    break;
                }
                int index = Integer.parseInt(parsedInput[1])-1;
                if (index >= taskList.size()) {
                    this.currentMessage = "Oops! task.Task not found, please try again.";
                    break;
                }
                Task currTask = taskList.get(index);
                currTask.markAsDone();
                this.currentMessage = "Sweet! I've marked this task as done:\n"
                        + currTask.toString();
                break;
            }
            case ("bye"):
                this.currentMessage = "Good bye. Hope to see you again soon!";
                return 0;
            default:
                taskList.add(new Task(input));
                this.currentMessage = "added: " + input;
        }
        System.out.println(this.output());
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
