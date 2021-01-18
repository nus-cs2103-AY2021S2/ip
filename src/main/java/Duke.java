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
                try {
                    switch (splitInput[0]) {
                        case "done":
                            if (splitInput.length < 2) {
                                throw new DukeException("The task index is missing.");
                            } else if (Integer.parseInt(splitInput[1]) > taskList.size()) {
                                throw new DukeException("The task index is out of range.");
                            } else {
                                taskList.markDone(Integer.parseInt(splitInput[1]));
                            }
                            break;
                        case "delete":
                            if (splitInput.length < 2) {
                                throw new DukeException("The task index is missing.");
                            } else if (Integer.parseInt(splitInput[1]) > taskList.size()) {
                                throw new DukeException("The task index is out of range.");
                            } else {
                                taskList.delete(Integer.parseInt(splitInput[1]));
                            }
                            break;
                        case "todo":
                            if (splitInput.length < 2) {
                                throw new DukeException("The description of a todo cannot be empty.");
                            } else {
                                taskList.add(new Todo(splitInput[1]));
                            }
                            break;
                        case "deadline":
                            if (splitInput.length < 2) {
                                throw new DukeException("The description of a deadline cannot be empty.");
                            } else {
                                String[] splitDeadlineInput = splitInput[1].split(" /by ");
                                if (splitDeadlineInput.length < 2) {
                                    throw new DukeException("Insufficient info given for a deadline.");
                                } else {
                                    taskList.add(new Deadline(splitDeadlineInput[0], splitDeadlineInput[1]));
                                }
                            }
                            break;
                        case "event":
                            if (splitInput.length < 2) {
                                throw new DukeException("The description of an event cannot be empty.");
                            } else {
                                String[] splitEventInput = splitInput[1].split(" /at ");
                                if (splitEventInput.length < 2) {
                                    throw new DukeException("Insufficient info given for a deadline.");
                                } else {
                                    taskList.add(new Event(splitEventInput[0], splitEventInput[1]));
                                }
                            }
                            break;
                        default:
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException e) {
                    printLineBreak();
                    printIndented(e.toString());
                    printLineBreak();
                }
            }
            userInput = sc.nextLine();
        }
        exit();
        sc.close();
    }

    public static void printLineBreak() {
        System.out.println("  ____________________________________________________________");
    }

    public static void printIndented(String text) {
        System.out.println(String.format("    %s", text));
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
