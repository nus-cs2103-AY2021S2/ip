import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class  Duke {
    public static void main(String[] args) {
        Task[] list = new Task[100];
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

            Pattern ptodo = Pattern.compile("^([Tt]odo )([a-zA-Z_0-9 ]+)");
            Matcher mtodo = ptodo.matcher(str);

            Pattern pdl = Pattern.compile("^([Dd]eadline )([a-zA-Z_0-9 ]+)(\\/by )([a-zA-Z_0-9 ]+)");
            Matcher mdl = pdl.matcher(str);

            Pattern pev = Pattern.compile("^([Ee]vent )([a-zA-Z_0-9 ]+)(\\/at )([a-zA-Z_0-9 ]+)");
            Matcher mev = pev.matcher(str);

            if (str.equals("list")) {
                System.out.println("Here are the tasks in your list!");
                for (int i = 0; i < numitems; i++) {
                    System.out.println(i+1 + "." + list[i]);

                }

            } else if (mdone.find()) {
                System.out.println("Good job, I've marked the task as done!");
                int n = Integer.parseInt(mdone.group(2)) - 1;
                list[n].markAsDone();
                System.out.println(list[n]);
            } else if (mtodo.find()) {
                list[numitems] = new Todo(mtodo.group(2));
                System.out.println("Got it!. I have added the following task:");
                System.out.println(list[numitems]);
                numitems += 1;
                System.out.println("Now you have " + numitems + " tasks in the list.");
            } else if (mdl.find()) {
                list[numitems] = new Deadline(mdl.group(2), mdl.group(4));
                System.out.println("Got it!. I have added the following task:");
                System.out.println(list[numitems]);
                numitems += 1;
                System.out.println("Now you have " + numitems + " tasks in the list.");
            } else if (mev.find()) {
                list[numitems] = new Event(mev.group(2), mev.group(4));
                System.out.println("Got it!. I have added the following task:");
                System.out.println(list[numitems]);
                numitems += 1;
                System.out.println("Now you have " + numitems + " tasks in the list.");
            } else {
                System.out.println("added: " + str);
                list[numitems] = new Task(str);
                numitems += 1;
            }
            str = sc.nextLine();
        }
        System.out.println("Bye friend, see you soon!");
    }
}
