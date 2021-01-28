import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    private static void printFileContents(String filePath, ArrayList<Task> lst) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * provides a platform for users to key in tasks to add to the list
     * @param args
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________");
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("____________________________");

        Storage st = new Storage();
        st.initialise(st.getDefaultFilePath());

        while (sc.hasNext()) {
            String input = sc.next();
            TaskList tl = new TaskList();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else {
                tl.createTask(input, sc);
            }
        }
    }
}