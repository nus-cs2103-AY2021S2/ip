import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ui {
    String welcome;

    public Ui() {
        this.welcome = "Hello! I'm Duke\n" + "What can I do for you?\n";
    }

    void showWelcome() {
        System.out.println(this.welcome);
    }

    void showLine() {
        System.out.println("____________________________________________________________");
    }

    void showLoadingError() {
        System.out.println("\u00a9 OOPS!!! Error loading file :-(");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String line = "";
        line = sc.nextLine();
        return line;
    }

    public void addCommandString(int size, String string) {
        System.out.println("Got it. I've added this task:\n  " + string + "\nNow you have " + size + " tasks in the list.");
    }

    public void showError(String message) {
        System.out.println("\u00a9 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void exitCommandString() {
        System.out.println( "Bye. Hope to see you again soon!");
	}

    public void addDeleteString(int size, String string) {
        System.out.println("Noted. I've removed this task:\n  " + string + "\nNow you have " + size + " tasks in the list.");
        
	}

    public void addDoneString(String string) {
        System.out.println("Nice! I've marked this task as done:\n" + string);
	}

    public void list(Scanner scanner) {
        System.out.println("Here are the tasks in your list");
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }

    }

    public void list(Scanner scanner, String date) {
        if (date.equals("")) {
            System.out.println("Here are the tasks in your list");
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    } else {
        LocalDate thisDate = LocalDate.parse(date);
        System.out.println(
                "Here are the tasks in your list due on " + thisDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        while (scanner.hasNext()) {
            String curTask = scanner.nextLine();
            String[] string = curTask.split(": ");
            if (string.length > 1) {
                String dueDate = string[1].replace(")", "");
                if (dueDate.equals(thisDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))) {
                    System.out.println(curTask);
                }
            }

        }
    }
                                
        
    }

}