import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greeting();
        
        TaskList taskList = new TaskList();
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                taskList.show();
            } else {
                String[] splitInput = userInput.split(" ", 2);
                switch (splitInput[0]) {
                    case "done":
                        taskList.markDone(Integer.parseInt(splitInput[1]));
                        break;
                    case "todo":
                        taskList.add(new Todo(splitInput[1]));
                        break;
                    case "deadline":
                        String[] splitDeadlineInput = splitInput[1].split(" /by ");
                        taskList.add(new Deadline(splitDeadlineInput[0], splitDeadlineInput[1]));
                        break;
                    case "event":
                        String[] splitEventInput = splitInput[1].split(" /at ");
                        taskList.add(new Event(splitEventInput[0], splitEventInput[1]));
                        break;
                }
            }
            userInput = sc.nextLine();
        }
        exit();
        sc.close();
    }

    public static void printLineBreak() {
        System.out.println("\t____________________________________________________________");
    }

    public static void printIndented(String text) {
        System.out.println(String.format("\t%s", text));
    }

    public static void greeting() {
        printLineBreak();
        printIndented("Hello! I'm Duke");
        printIndented("What can I do for you?");
        printLineBreak();
    }

    public static void exit() {
        printLineBreak();
        printIndented("Bye. Hope to see you again soon!");
        printLineBreak();
    }
}
