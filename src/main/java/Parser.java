import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Makes sense of user input and commands.
 */
public class Parser {
    /**
     * Constructor for this Parser object.
     */
    public Parser() {
    }

    /**
     * Parses a given string such that Duke can understand commands.
     * @param  tasks The TaskList object containing all tasks.
     * @param  str The string to be parsed.
     */
    public static void parse(TaskList tasks, String str) throws DukeException {
        Pattern pDone = Pattern.compile("^(done )([0-9]+)");
        Matcher mDone = pDone.matcher(str);

        Pattern pTodo = Pattern.compile("^([Tt]odo )([a-zA-Z_0-9 ]*+)");
        Matcher mTodo = pTodo.matcher(str);

        Pattern pDeadline = Pattern.compile("^([Dd]eadline )([a-zA-Z_0-9 ]*+)(\\/by )([a-zA-Z_0-9 -]*+)");
        Matcher mDeadline = pDeadline.matcher(str);

        Pattern pEvent = Pattern.compile("^([Ee]vent )([a-zA-Z_0-9 ]*+)(\\/at )([a-zA-Z_0-9 ]*+)");
        Matcher mEvent = pEvent.matcher(str);

        Pattern pDelete = Pattern.compile("^(delete )([0-9]+)");
        Matcher mDelete = pDelete.matcher(str);

        if (str.equals("list")) {
            System.out.println("Here are the tasks in your tasks!");
            tasks.getNumItems();
            tasks.printTasks();
        } else if (mDone.find()) {
            System.out.println("Good job, I've marked the task as done!");
            int n = Integer.parseInt(mDone.group(2)) - 1;
            tasks.getAtInd(n).markAsDone();
            System.out.println(tasks.getAtInd(n));
        } else if (mTodo.find()) {
            System.out.println(mTodo.group(2));
            if (mTodo.group(2).equals("")) {
                throw new DukeException("The description of a Todo cannot be empty!");
            }
            tasks.addTodo(mTodo.group(2));
            System.out.println("Got it!. I have added the following task:");
            System.out.println(tasks.getAtInd(tasks.getNumItems() - 1));
            System.out.println("Now you have " + tasks.getNumItems() + " tasks in the tasks.");
        } else if (mDeadline.find()) {
            if (mDeadline.group(2).equals("")) {
                throw new DukeException("The description of a Deadline cannot be empty!");
            }
            System.out.println(mDeadline.group(4));
            tasks.addDeadline(mDeadline.group(2), mDeadline.group(4));
            System.out.println("Got it!. I have added the following task:");
            System.out.println(tasks.getAtInd(tasks.getNumItems() - 1));
            System.out.println("Now you have " + tasks.getNumItems() + " tasks in the tasks.");
        } else if (mEvent.find()) {
            if (mEvent.group(2).equals("")) {
                throw new DukeException("The description of an Event cannot be empty!");
            }
            tasks.addEvent(mEvent.group(2), mEvent.group(4));
            System.out.println("Got it!. I have added the following task:");
            System.out.println(tasks.getAtInd(tasks.getNumItems() - 1));
            System.out.println("Now you have " + tasks.getNumItems() + " tasks in the tasks.");
        } else if (mDelete.find()) {
            System.out.println("Okay I have removed this task!");
            int n = Integer.parseInt(mDelete.group(2)) - 1;
            System.out.println(tasks.getAtInd(n));
            tasks.deleteTask(n);
            System.out.println("Now you have " + tasks.getNumItems() + " tasks in the tasks.");
        } else {
            throw new DukeException("I don't know what that means!!!!");
        }
    }
}
