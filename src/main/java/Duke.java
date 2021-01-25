import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.PrintWriter;

/**
 * Duke is a personal assistant chat bot that helps users to
 * keep track of various things.
 * @author Damith C. Rajapakse, Wu Weiming
 */
public class Duke {
    private static final String EXIT_COMMAND = "bye";

    List<Task> lst = new ArrayList<>();
    Tasks tasks = new Tasks();

    public static void main(String[] args) {
        Duke d = new Duke();
        try {
            d.run();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void run() throws IOException {
        printHello();
        Scanner sc = new Scanner(System.in);
        File data = new File("duke.txt");
        if (!data.exists()) {
            data.createNewFile();
        } else {
            Scanner s = new Scanner(data);
            while (s.hasNextLine()) {
                String txt = s.nextLine();
                tasks.readTask(txt);
            }
        }

        while (true) {
            String input = sc.nextLine();
            if (input.equals(EXIT_COMMAND)) {
                // store Tasks in a file
                FileWriter fw = new FileWriter(data);
                List<Task> taskLst = tasks.fetchTasks();

                for (Task item : taskLst) {
                    fw.write(item.getDescription() + "\n");
                }
                fw.close();

                // close program
                formatText();
                System.out.println("Bye, see you soon! Don't miss me too much.");
                formatText();
                break;
            } else if (input.equals("list")) {
                // show everything in the list
                formatText();
                tasks.iterateList();
                formatText();
            } else if (input.split(" ", 2)[0].equals("done")) {
                // mark task with the given index as completed
                tasks.markAsDone(input.split(" ", 2)[1]);
            } else if (input.split(" ", 2)[0].equals("delete")) {
                tasks.deleteTask(input.split(" ", 2)[1]);
            } else {
                // add new task to list
                tasks.addTask(input);
                // need to handle when ppl never put /by or /at
            }
        }
        sc.close();
    }

    /**
     * Prints a horizontal line to format text.
     */
    public static void formatText() {
        System.out.println("******************************************************");
    }

    public void printHello() {
        String logo = ".------..------..------..------.\n"
                + "|D.--. ||U.--. ||K.--. ||E.--. |\n"
                + "| :/\\: || (\\/) || :/\\: || (\\/) |\n"
                + "| (__) || :\\/: || :\\/: || :\\/: |\n"
                + "| '--'D|| '--'U|| '--'K|| '--'E|\n"
                + "`------'`------'`------'`------'";

        System.out.println("Hello, this is\n" + logo);
    }
}
