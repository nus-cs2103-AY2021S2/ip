import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    TaskList list;

    public Duke() {
        greet();
        this.list = new TaskList();
    }

    public static void printWithStyle(String[] output) {
        System.out.println("    ________________________________________________________________");
        for (String str : output) {
            System.out.println("    " + str);
        }
        System.out.println("    ________________________________________________________________");
    }

    public static void printWithStyle(String output) {
        System.out.println("    ________________________________________________________________");
        System.out.println("    " + output );
        System.out.println("    ________________________________________________________________");
    }

    /**
     * Joins a sub-array of strings into 1 string where each element in sub-array is separate by a space.
     *
     * @param arr array containing sub-array to join
     * @param start start index of sub-array to join
     * @param end end index of sub-array to join
     * @return string of sub-array joined with space
     */
    private String join(String[] arr, int start, int end) {
        StringBuilder output = new StringBuilder();
        for (int i = start; i <= end; i++) {
            output.append(arr[i]);
            if (i < end) {
                output.append(" ");
            }
        }
        return output.toString();
    }


    void greet() {
        printWithStyle(new String[]{"Hello! I'm Duke", "What can I do for you?"});
    }


    void addToDo(String[] userInputSplit) throws DukeException {
        if (userInputSplit.length <= 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String task = join(userInputSplit, 1, userInputSplit.length - 1);
        list.add(new ToDo(task));
    }


    void addDeadline(String[] userInputSplit) throws DukeException {
        //Index of /by keyword
        int byIndex = 0;
        for (int i = 0; i < userInputSplit.length; i++) {
            if (userInputSplit[i].equals("/by")) {
                byIndex = i;
            }
        }
        if (byIndex == 0) {
            throw new DukeException("Missing /by keyword for new deadline.");
        } else if (byIndex == 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (byIndex == userInputSplit.length - 1) {
            throw new DukeException("Missing date of the deadline.");
        }
        String task = join(userInputSplit, 1, byIndex - 1);
        String date = join(userInputSplit, byIndex + 1, userInputSplit.length - 1);
        list.add(new Deadline(task, date));
    }


    void addEvent(String[] userInputSplit) throws DukeException {
        //Index of /at keyword
        int atIndex = 0;
        for (int i = 0; i < userInputSplit.length; i++) {
            if (userInputSplit[i].equals("/at")) {
                atIndex = i;
            }
        }
        if (atIndex == 0) {
            throw new DukeException("Missing /at keyword for new Event.");
        } else if (atIndex == 1) {
            throw new DukeException("☹ OOPS!!! The description of an Event cannot be empty.");
        }
        if (atIndex == userInputSplit.length - 1) {
            throw new DukeException("Missing date of the Event.");
        }
        String task = join(userInputSplit, 1, atIndex - 1);
        String date = join(userInputSplit, atIndex + 1, userInputSplit.length - 1);
        list.add(new Event(task, date));
    }


    void doneTask(String[] userInputSplit) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(userInputSplit[1]);
            this.list.done(taskNumber);
        } catch (Exception e) {
            throw new DukeException("Please enter a valid task number to mark a task as done.");
        }
    }


    void bye() {
        printWithStyle("Bye. Hope to see you again soon!");
    }

    void handleInput(String userInput) throws DukeException {
        String[] splitBySpaces = userInput.trim().split("\\s+");
        String keyword = splitBySpaces[0];
        if (keyword.equals("list")) {
            this.list.printList();
        } else if (keyword.equals("done")) {
            doneTask(splitBySpaces);
        } else if (keyword.equals("deadline")) {
            addDeadline(splitBySpaces);
        } else if (keyword.equals("todo")) {
            addToDo(splitBySpaces);
        } else if (keyword.equals("event")) {
            addEvent(splitBySpaces);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            try {
                duke.handleInput(input);
            } catch (DukeException e) {
                printWithStyle(e.getMessage());
            }
            input = scanner.nextLine();
        }
        duke.bye();

    }
}
