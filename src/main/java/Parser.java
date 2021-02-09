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
    public static String parse(TaskList tasks, String str) throws DukeException {
        String output = "";
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

        Pattern pFind = Pattern.compile("^(find )([a-zA-Z_0-9 ]+)");
        Matcher mFind = pFind.matcher((str));
        assert !str.isBlank() : "No command given!";
        if (str.equals("list")) {
            output += "Here are the tasks in your tasks!\n";
            tasks.getNumItems();
            output += tasks.printTasksToString();
        } else if (mDone.find()) {
            output += "Good job, I've marked the task as done!\n";
            int n = Integer.parseInt(mDone.group(2)) - 1;
            tasks.getAtInd(n).markAsDone();
            output += tasks.getAtInd(n);
        } else if (mTodo.find()) {
            output += mTodo.group(2) + "\n";
            if (mTodo.group(2).equals("")) {
                throw new DukeException("The description of a Todo cannot be empty!");
            }
            tasks.addTodo(mTodo.group(2));
            output += "Got it!. I have added the following task:\n";
            output += tasks.getAtInd(tasks.getNumItems() - 1) + "\n";
            output += "Now you have " + tasks.getNumItems() + " tasks in the tasks.";
        } else if (mDeadline.find()) {
            if (mDeadline.group(2).equals("")) {
                throw new DukeException("The description of a Deadline cannot be empty!");
            }
            //output += mDeadline.group(4) + "\n";
            tasks.addDeadline(mDeadline.group(2), mDeadline.group(4));
            output += "Got it!. I have added the following task:" + "\n";
            output += tasks.getAtInd(tasks.getNumItems() - 1) + "\n";
            output += "Now you have " + tasks.getNumItems() + " tasks in the tasks.";
        } else if (mEvent.find()) {
            if (mEvent.group(2).equals("")) {
                throw new DukeException("The description of an Event cannot be empty!");
            }
            tasks.addEvent(mEvent.group(2), mEvent.group(4));
            output += "Got it!. I have added the following task:" + "\n";
            output += tasks.getAtInd(tasks.getNumItems() - 1) + "\n";
            output += "Now you have " + tasks.getNumItems() + " tasks in the tasks.";
        } else if (mDelete.find()) {
            output += "Okay I have removed this task!" + "\n";
            int n = Integer.parseInt(mDelete.group(2)) - 1;
            output += tasks.getAtInd(n) + "\n";
            tasks.deleteTask(n);
            output += "Now you have " + tasks.getNumItems() + " tasks in the list.";
        } else if (mFind.find()) {
            output += "Here are the matching tasks in your list!" + "\n";
            String word = mFind.group(2);
            output += tasks.matchTasks(word);
        } else {
            throw new DukeException("I don't know what that means!!!!");
        }
        return output;
    }
}
