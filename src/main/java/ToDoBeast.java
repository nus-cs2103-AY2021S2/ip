import java.util.Scanner;
import java.util.List;

public class ToDoBeast {
    static String line = "\t________________________________________________________________\n";
    TaskManager taskManager;
    Scanner sc;

    public ToDoBeast() {
        taskManager = new TaskManager();
        sc = new Scanner(System.in);
    }

    public void runApplication() {
        greetUser();
        String[] userInput = sc.nextLine().split(" ", 2);
        String command = userInput[0];

        while (!command.equals("bye")) {
            try {
                checkUserInput(userInput);
                if (command.equals("list")) {
                    printTaskList(taskManager.getTaskList());
                }
                else if (command.equals("done")){
                    Task currentTask = taskManager.getTask(Integer.parseInt(userInput[1]));
                    currentTask.setDone();
                    System.out.println(line + "\tGood job! You've just completed this task:\n" + "\t\t" + currentTask + "\n" + line);
                }
                else if (command.equals("delete")){
                    int taskNumber = Integer.parseInt(userInput[1]);
                    Task deletedTask = taskManager.getTask(taskNumber);
                    taskManager.deleteTask(taskNumber);
                    System.out.println(line + "\tGot it! I've removed this task for you:\n\t\t" + deletedTask + "\n" + "\tYou now have " + taskManager.getNumOfTasks() + " tasks in total.\n" + line);
                }
                else {
                    Task newTask = null;

                    switch (command) {
                        case "todo":
                            newTask = new Todo(userInput[1]);
                            break;
                        case "deadline": {
                            String[] deadlineParams = userInput[1].split(" /");
                            newTask = new Deadline(deadlineParams[0], deadlineParams[1]);
                            break;
                        }
                        case "event": {
                            String[] deadlineParams = userInput[1].split(" /");
                            newTask = new Event(deadlineParams[0], deadlineParams[1]);
                            break;
                        }
                    }
                    taskManager.addTask(newTask);
                    System.out.println(line + "\tOne more task added to the hustle:\n\t\t" + newTask + "\n" + "\tYou now have " + taskManager.getNumOfTasks() + " tasks in total.\n" + line);
                    }

            } catch (ToDoBeastException e) {
                System.out.println(line + "\t" + e.getMessage() + "\n" + line);
            } finally {
                userInput = sc.nextLine().split(" ", 2);
                command = userInput[0];
            }
        }
        exit();
    }

    public static void main(String[] args) {
        ToDoBeast toDoBeast = new ToDoBeast();
        toDoBeast.runApplication();
    }

    public void checkUserInput(String[] userInput) throws ToDoBeastException {
        String command = userInput[0];
        if ((command.equals("todo") || command.equals("deadline") || command.equals("event")) && userInput.length == 1) {
            throw new ToDoBeastException("OOPS :( !!! The description of a " + command + " cannot be empty.");
        }
        else if (command.equals("done") || command.equals("delete")) {
            if (userInput.length == 1) {
                throw new ToDoBeastException("OOPS :( !!! The " + command + " command requires the index of the task to be processed.");
            }
            try {
                int taskNumber = Integer.parseInt(userInput[1]);
                if (taskNumber > taskManager.getNumOfTasks() || taskNumber <= 0) {
                    throw new ToDoBeastException("OOPS :( !!! Task index provided is not present in the list.");
                }
            } catch (NumberFormatException e) {
                throw new ToDoBeastException("OOPS :( !!! Task index provided is not a number.");
            }
        }
        else if (!isValidCommand(command)) {
            throw new ToDoBeastException("OOPS :( !!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public boolean isValidCommand(String command) {
        return command.equals("bye") || command.equals("list") || command.equals("done") || command.equals("todo") || command.equals("deadline") || command.equals("event") || command.equals("delete");
    }

    public void greetUser() {
        String logo = "                                                     \n" +
                "\t88                                                   \n" +
                "\t88                                            ,d     \n" +
                "\t88                                            88     \n" +
                "\t88,dPPYba,   ,adPPYba, ,adPPYYba, ,adPPYba, MM88MMM  \n" +
                "\t88P'    \"8a a8P_____88 \"\"     `Y8 I8[    \"\"   88     \n" +
                "\t88       d8 8PP\"\"\"\"\"\"\" ,adPPPPP88  `\"Y8ba,    88     \n" +
                "\t88b,   ,a8\" \"8b,   ,aa 88,    ,88 aa    ]8I   88,    \n" +
                "\t8Y\"Ybbd8\"'   `\"Ybbd8\"' `\"8bbdP\"Y8 `\"YbbdP\"'   \"Y888  \n" +
                "\t                                                     \n" +
                "\t                                                     \n";
        String greeting = line + logo + "\tWelcome to ToDoBeast, your best productivity task tracker tool!\n"
                + "\tLet's get this bread! How would you like to be productive today?\n" + line;
        System.out.println(greeting);
    }

    public void exit() {

        String exitMsg = line + "\tThis app may have stopped but the grind never stops.\n\tSee you again soon!\n" + line;
        System.out.println(exitMsg);
        System.exit(0);
    }



    public static void printTaskList(List<Task> taskList) {
        int count = 1;
        System.out.print(line);
        for (Task task : taskList) {
            System.out.println("\t" + count++ + ". " + task);
        }
        System.out.println(line);
    }
}
