import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) throws DukeException {
        List<Task> list = new ArrayList<>();
        //Task[] list = new Task[100];
        int numitems = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        while (!str.equals("bye")) {
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
                for (int i = 0; i < numitems; i++) {
                    System.out.println(i+1 + "." + list.get(i));

                }

            } else if (mdone.find()) {
                System.out.println("Good job, I've marked the task as done!");
                int n = Integer.parseInt(mdone.group(2)) - 1;
                list.get(n).markAsDone();
                System.out.println(list.get(n));
            } else if (mtodo.find()) {
                System.out.println(mtodo.group(2));
                if (mtodo.group(2).equals("")) {
                    throw new DukeException("The description of a Todo cannot be empty!");
                }
                list.add(numitems, new Todo(mtodo.group(2)));
                System.out.println("Got it!. I have added the following task:");
                System.out.println(list.get(numitems));
                numitems += 1;
                System.out.println("Now you have " + numitems + " tasks in the list.");
            } else if (mdl.find()) {
                if (mdl.group(2).equals("")) {
                    throw new DukeException("The description of a Deadline cannot be empty!");
                }
                System.out.println(mdl.group(4));
                list.add(numitems, new Deadline(mdl.group(2), mdl.group(4)));
                System.out.println("Got it!. I have added the following task:");
                System.out.println(list.get(numitems));
                numitems += 1;
                System.out.println("Now you have " + numitems + " tasks in the list.");
            } else if (mev.find()) {
                if (mev.group(2).equals("")) {
                    throw new DukeException("The description of an Event cannot be empty!");
                }
                list.add(numitems, new Event(mev.group(2), mev.group(4)));
                System.out.println("Got it!. I have added the following task:");
                System.out.println(list.get(numitems));
                numitems += 1;
                System.out.println("Now you have " + numitems + " tasks in the list.");
            } else if (mdel.find()) {
                System.out.println("Okay I have removed this task!");
                int n = Integer.parseInt(mdel.group(2)) - 1;
                System.out.println(list.get(n));
                list.remove(n);
                numitems -= 1;
                System.out.println("Now you have " + numitems + " tasks in the list.");
            } else {
                throw new DukeException("I don't know what that means!!!!");
            }
            str = sc.nextLine();
        }
        System.out.println("Bye friend, see you soon!");
    }
}
