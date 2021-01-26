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
    public static void parse(TaskList list, String str) throws DukeException {
        Pattern pdone = Pattern.compile("^(done )([0-9]+)");
        Matcher mdone = pdone.matcher(str);

        Pattern ptodo = Pattern.compile("^([Tt]odo )([a-zA-Z_0-9 ]*+)");
        Matcher mtodo = ptodo.matcher(str);

        Pattern pdl = Pattern.compile("^([Dd]eadline )([a-zA-Z_0-9 ]*+)(\\/by )([a-zA-Z_0-9 -]*+)");
        Matcher mdl = pdl.matcher(str);

        Pattern pev = Pattern.compile("^([Ee]vent )([a-zA-Z_0-9 ]*+)(\\/at )([a-zA-Z_0-9 ]*+)");
        Matcher mev = pev.matcher(str);

        Pattern pdel = Pattern.compile("^(delete )([0-9]+)");
        Matcher mdel = pdel.matcher(str);

        if (str.equals("list")) {
            System.out.println("Here are the tasks in your list!");
            list.getNumItems();
            list.printTasks();

        } else if (mdone.find()) {
            System.out.println("Good job, I've marked the task as done!");
            int n = Integer.parseInt(mdone.group(2)) - 1;
            list.getAtInd(n).markAsDone();
            System.out.println(list.getAtInd(n));
        } else if (mtodo.find()) {
            System.out.println(mtodo.group(2));
            if (mtodo.group(2).equals("")) {
                throw new DukeException("The description of a Todo cannot be empty!");
            }
            list.addTodo(mtodo.group(2));
            System.out.println("Got it!. I have added the following task:");
            System.out.println(list.getAtInd(list.getNumItems() - 1));
            System.out.println("Now you have " + list.getNumItems() + " tasks in the list.");
        } else if (mdl.find()) {
            if (mdl.group(2).equals("")) {
                throw new DukeException("The description of a Deadline cannot be empty!");
            }
            System.out.println(mdl.group(4));
            list.addDeadline(mdl.group(2), mdl.group(4));
            System.out.println("Got it!. I have added the following task:");
            System.out.println(list.getAtInd(list.getNumItems() - 1));
            System.out.println("Now you have " + list.getNumItems() + " tasks in the list.");
        } else if (mev.find()) {
            if (mev.group(2).equals("")) {
                throw new DukeException("The description of an Event cannot be empty!");
            }
            list.addEvent(mev.group(2), mev.group(4));
            System.out.println("Got it!. I have added the following task:");
            System.out.println(list.getAtInd(list.getNumItems() - 1));
            System.out.println("Now you have " + list.getNumItems() + " tasks in the list.");
        } else if (mdel.find()) {
            System.out.println("Okay I have removed this task!");
            int n = Integer.parseInt(mdel.group(2)) - 1;
            System.out.println(list.getAtInd(n));
            list.deleleTask(n);
            System.out.println("Now you have " + list.getNumItems() + " tasks in the list.");
        } else {
            throw new DukeException("I don't know what that means!!!!");
        }
    }
}
