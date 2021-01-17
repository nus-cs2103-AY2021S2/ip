import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        String userInput = "";
        int index = 0;

        printSegment();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printSegment();

        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine().trim();
            String[] userInputArray = userInput.split(" ", 2);
            switch (userInputArray[0]) {
                case "list": {
                    printSegment();
                    System.out.println("\tHere are the tasks in your list:");
                    for (int i = 0; i < index; i++) {
                        System.out.println("\t" + (i + 1) + ". " + tasks[i]);
                    }
                    printSegment();
                    break;
                }
                case "done": {
                    int userIndex = Integer.parseInt(userInputArray[1]);
                    tasks[userIndex - 1].markAsDone();
                    printSegment();
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t" + tasks[userIndex - 1]);
                    printSegment();
                }
                case "bye":
                    break;
                default: {
                    Task newTask = parseTask(userInputArray);
                    tasks[index] = newTask;
                    index++;
                    printSegment();
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t\t" + newTask);
                    System.out.println("\tNow you have " + index + " tasks in the list.");
                    printSegment();
                }
            }
        }

        printSegment();
        System.out.println("\tBye. Hope to see you again soon!");
        printSegment();
    }

    static Task parseTask(String[] userInputArray) {
        Task newTask;
        switch (userInputArray[0]) {
            case "todo": {
                newTask = new ToDo(userInputArray[1]);
                break;
            }
            case "deadline": {
                String[] deadlineInputs = userInputArray[1].split("(\\s/by\\s)+");
                newTask = new Deadline(deadlineInputs[0].trim(), deadlineInputs[1].trim());
                break;
            }
            case "event": {
                String[] eventInputs = userInputArray[1].split("(\\s\\/at\\s)+");
                newTask = new Event(eventInputs[0].trim(), eventInputs[1].trim());
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + userInputArray[0]);
        }
        return newTask;
    }

    static void printSegment() {
        System.out.println("\t____________________________________________________________");
    }
}
