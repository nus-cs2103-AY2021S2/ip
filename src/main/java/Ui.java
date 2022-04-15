import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
   * Ui provides all outputs that the user would face in different situations
   * 
   */
  
public class Ui {
    String welcome;

    public Ui() {
        this.welcome = "Hello! I'm Duke\n" + "What can I do for you?\n";
    }

    String showWelcome() {
        return (this.welcome);
    }

    String showLine() {
        return ("____________________________________________________________");
    }

    String showLoadingError() {
        return ("\u00a9 OOPS!!! Error loading file :-(");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String line = "";
        line = sc.nextLine();
        return line;
    }

    public String addCommandString(int size, String string) {
        return ("Got it. I've added this task:\n  " + string + "\nNow you have " + size + " tasks in the list.");
    }

    public String showError(String message) {
        return ("\u00a9 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public String exitCommandString() {
        return ( "Bye. Hope to see you again soon!");
	}

    public String addDeleteString(int size, String string) {
        return ("Noted. I've removed this task:\n  " + string + "\nNow you have " + size + " tasks in the list.");
        
	}

    public String addDoneString(String string) {
        return("Nice! I've marked this task as done:\n" + string);
	}

    public String list(Scanner scanner) {
        String value;
        value = ("Here are the tasks in your list\n");
        while (scanner.hasNext()) {
            value += scanner.nextLine();
        }
        return value;

    }

    public String list(Scanner scanner, String date) {
        String value;
        if (date.equals("")) {
            value = ("Here are the tasks in your list\n");
            while (scanner.hasNext()) {
                value += (scanner.nextLine()) + "\n";
            }
            return value;
        } else {
            LocalDate thisDate = LocalDate.parse(date);
            value = ("Here are the tasks in your list due on "
                    + thisDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n");
            while (scanner.hasNext()) {
                String curTask = scanner.nextLine();
                String[] string = curTask.split(": ");
                if (string.length > 1) {
                    String dueDate = string[1].replace(")", "");
                    if (dueDate.equals(thisDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))) {
                        value += (curTask) + "\n";
                    }
                }

            }
            return value;
        }
    }

    String findTaskString(String task, Scanner scanner) {
        String value;
        value = ("Here are the tasks in your list:\n");
        while (scanner.hasNext()) {
            String curTask = scanner.nextLine();
            if (curTask.contains(task)) {
               value += (curTask) + "\n";
            }
        }
        return value;
	}

    public String addUndoString() {
        String value;
        value = ("Undo-ed the latest AddCommand");
        return value;
    }
}