import java.util.Scanner;

public class Duke {

    static void printList(Task[] tasks, int noOfTasks) {
        System.out.println("------------------------------------------------");
        System.out.println("Here are the tasks in your list:");

        for (int j = 0; j < noOfTasks; j++) {
            System.out.println(j + 1 + "." + tasks[j].toString());
        }

        System.out.println("------------------------------------------------");
    }

    static void markTask(Task[] tasks, int taskIndex, int noOfTasks) throws InvalidDescriptionException{
        if (taskIndex >= noOfTasks) {
            throw new InvalidDescriptionException("");
        }

        tasks[taskIndex].markAsDone();

        System.out.println("------------------------------------------------");
        System.out.println("Nice! I've marked this task as done:\n" + tasks[taskIndex].toString());
        System.out.println("------------------------------------------------");
    }

    static void addTask(Task[] tasks, String taskType, String taskInfo, int noOfTasks) throws InvalidDescriptionException{
        Task task = new Task();

        switch(taskType) {
            case "todo":
            {
                task = new ToDo(taskInfo);
                break;
            }
            case "deadline":
            {
                if (!(taskInfo.contains("/by"))) {
                    throw new InvalidDescriptionException("");
                }
                String[] taskInfoArr = taskInfo.split(" /by ", 2);
                if (taskInfoArr.length < 2) {
                    throw new InvalidDescriptionException("");
                }
                task = new Deadline(taskInfoArr[0], taskInfoArr[1]);
                break;
            }
            case "event":
            {
                if (!(taskInfo.contains("/at"))) {
                    throw new InvalidDescriptionException("");
                }
                String[] taskInfoArr = taskInfo.split(" /at ", 2);
                if (taskInfoArr.length < 2) {
                    throw new InvalidDescriptionException("");
                }
                task = new Event(taskInfoArr[0], taskInfoArr[1]);
                break;
            }
            default:
            {
                System.out.println("Invalid task!");
                break;
            }
        }

        tasks[noOfTasks] = task;

        System.out.println("------------------------------------------------");
        System.out.println("Ok! I've added this task:\n" + task.toString());
        System.out.println("Currently, you have " + (noOfTasks + 1) + " task(s) in the list!");
        System.out.println("------------------------------------------------");
    }

    static void invalidCommandChecker(String taskType) throws InvalidCommandException {
        if (!(taskType.equals("todo") || taskType.equals("done") || taskType.equals("list") || taskType.equals("event")
                || taskType.equals("deadline"))) {
            throw new InvalidCommandException("");
        }
    }

    static void emptyDescriptionChecker(String[] tokens) throws EmptyDescriptionException {
        if (tokens.length < 2) {
            throw new EmptyDescriptionException("");
        }
    }

    static void takeCommands() {
        Scanner sc = new Scanner(System.in);
        String input;
        int noOfTasks = 0;
        Task[] tasks = new Task[100];
        boolean caughtException;

        do {
            try {
                while (!(input = sc.nextLine()).equals("bye")) {
                    String[] tokens = input.split(" ", 2);
                    String taskType = tokens[0];

                    invalidCommandChecker(taskType);

                    if (taskType.equals("list")) {
                        printList(tasks, noOfTasks);
                    } else if (taskType.equals("done")) {
                        emptyDescriptionChecker(tokens);


                        String taskInfo = tokens[1];
                        int taskIndex = Integer.parseInt(taskInfo) - 1;
                        markTask(tasks, taskIndex, noOfTasks);
                    } else {
                        emptyDescriptionChecker(tokens);

                        String taskInfo = tokens[1];
                        addTask(tasks, taskType, taskInfo, noOfTasks);
                        noOfTasks++;
                    }
                };
                caughtException = false;
            } catch (InvalidCommandException e) {
                System.out.println("------------------------------------------------");
                System.out.println("Sorry, I don't know what that means...");
                System.out.println("------------------------------------------------");

                caughtException = true;
            } catch (InvalidDescriptionException e) {
                System.out.println("------------------------------------------------");
                System.out.println("Sorry, I am unable to process what was written after the command...");
                System.out.println("------------------------------------------------");

                caughtException = true;
            } catch (EmptyDescriptionException e) {
                System.out.println("------------------------------------------------");
                System.out.println("Sorry, nothing was written after the command so I am unable to process...");
                System.out.println("------------------------------------------------");

                caughtException = true;
            } catch (NumberFormatException e) {
                System.out.println("------------------------------------------------");
                System.out.println("Sorry, I do not understand...");
                System.out.println("------------------------------------------------");
                caughtException = true;
            }
        } while (caughtException);
        sc.close();
    }

    public static void main(String[] args) {
        System.out.println("------------------------------------------------");
        System.out.println("Hi! I'm Timmy!\nWhat can Timmy do for you today?");
        System.out.println("------------------------------------------------");

        takeCommands();

        System.out.println("------------------------------------------------");
        System.out.println("Bye! Hope to see you again!");
        System.out.println("------------------------------------------------");
    }
}
