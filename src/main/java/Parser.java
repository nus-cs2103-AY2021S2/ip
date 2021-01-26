package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.ArrayIndexOutOfBoundsException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** Stores information input from user and reply accordingly. */
public class Parser {
    private TaskManager tm = new TaskManager();

    /**
     * Process the user input and respond accordingly.
     * @param input Text representation of task type and task info.
     * @throws DukeException if input has no keyword or if task has no description.
     */
    public void process(String input) throws DukeException {
        if (input.contains("todo") || input.contains("deadline") 
            || input.contains("event") || input.contains("find")) {
            if (input.split(" ").length == 1) {
                throw new EmptyException();
            }
        }
        if (input.equals("bye")) {
            System.out.println("Byebye~ Hope to see you again soon!");
        } else if (input.equals("list")) {
            this.tm.displayTasks();
        } else if (input.contains("todo") || input.contains("deadline") 
                || input.contains("event")) {
            Task t;
            if (input.contains("todo")) {
                t = new Todo(input.split("todo ")[1]);
            } else if (input.contains("deadline")) {
                String trimmed = input.replaceAll("deadline ", "");
                t = new Deadline(trimmed.split(" by ")[0], trimmed.split(" by ")[1]);
            } else /*(if (input.contains("event"))*/ {
                String trimmed = input.replaceAll("event ", "");
                t = new Event(trimmed.split(" at ")[0], trimmed.split(" at ")[1]);
            }
            this.tm.addTask(t);
            System.out.println("Got it. I've added this task:");
            System.out.println(t.toString());
            System.out.println(String.format("Now you have %s tasks in the list.", 
                        this.tm.taskVolume()));
        } else if (input.contains("find")) {
            String item = input.replaceAll("find ", "");
            System.out.println("Here are the matching tasks in your list: ");
            this.tm.find(item);
        } else if (input.contains("done")) {
            int num = Integer.valueOf(input.split(" ")[1]);
            //this.store.get(num - 1).markAsDone();
            this.tm.taskDone(num);
            System.out.println("Wahoo you completed one task!");
        } else if (input.contains("delete")) {
            int deleteat = Integer.valueOf(input.split(" ")[1]);
            this.tm.deleteTask(deleteat);
            System.out.println("Task deleted.");
        } else {
            throw new KeywordException();
        }
        this.tm.writeToDisk();
    }

    /**
     * Starts chat, reads user input and replies.
     * Exits when user says bye.
     */
    public void chat() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (sc.hasNextLine()) {
            try {
                File dir = new File("tasklist");
                dir.mkdirs();
                File f = new File(dir, "mytasks.txt");
                f.createNewFile();
            } catch (IOException err) {
                err.printStackTrace();
            }
            try {
                this.process(input);
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            }
            System.out.println("----------------------------------------------------------");
            input = sc.nextLine();
        }
        if (input.equals("bye")) {
            System.out.println("Byebye~ Hope to see you again soon!");
        }
    }
}
