import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Application {

    private FileManager fileManager;
    private TaskManager taskManager;
    private Scanner sc;

    private String dataDirectoryName;
    private String dataFileName;

    public Application() {
        fileManager = new FileManager();
        taskManager = new TaskManager();
        sc = new Scanner(System.in);

        dataDirectoryName = "./data/";
        dataFileName = "data.txt";
    }

    public void runApplication() {
        UserInterface.greetUser();
        initialiseDataStorage();
        loadTasksFromStorage();

        String[] userInput = sc.nextLine().split(" ", 2);
        String command = userInput[0];

        while (!command.equals("bye")) {
            try {
                checkUserInput(userInput);
                if (command.equals("list")) {
                    taskManager.printTaskList();
                }
                else if (command.equals("done")){
                    Task currentTask = taskManager.getTask(Integer.parseInt(userInput[1]));
                    currentTask.setDone();
                    System.out.println(UserInterface.line + "\tGood job! You've just completed this task:\n" + "\t\t" + currentTask + "\n" + UserInterface.line);
                }
                else if (command.equals("delete")){
                    int taskNumber = Integer.parseInt(userInput[1]);
                    Task deletedTask = taskManager.getTask(taskNumber);
                    taskManager.deleteTask(taskNumber);
                    System.out.println(UserInterface.line + "\tGot it! I've removed this task for you:\n\t\t" + deletedTask + "\n" + "\tYou now have " + taskManager.getNumOfTasks() + " tasks in total.\n" + UserInterface.line);
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
                    System.out.println(UserInterface.line + "\tOne more task added to the hustle:\n\t\t" + newTask + "\n" + "\tYou now have " + taskManager.getNumOfTasks() + " tasks in total.\n" + UserInterface.line);
                }

            } catch (ToDoBeastException e) {
                System.out.println(UserInterface.line + "\t" + e.getMessage() + "\n" + UserInterface.line);
            } finally {
                writeDataToStorage();
                userInput = sc.nextLine().split(" ", 2);
                command = userInput[0];
            }
        }
        UserInterface.exitDisplay();
    }

    public void checkUserInput(String[] userInput) throws ToDoBeastException {
        String command = userInput[0];
        if ((command.equals("todo") || command.equals("deadline") || command.equals("event")) && userInput.length == 1) {
            throw new ToDoBeastException("OOPS :( !!! The description of a " + command + " cannot be empty.");
        }
        else if (command.equals("deadline") || command.equals("event")) {
            String[] testParams = userInput[1].split(" /");
            if (testParams.length == 1) {
                throw new ToDoBeastException("OOPS :( !!! Invalid format for time/date input.");
            } else {
                if (!testParams[1].startsWith("by") && command.equals("deadline")) {
                    throw new ToDoBeastException("OOPS :( !!! Time/date input for deadlines should start with /by.");
                } else if (!testParams[1].startsWith("at") && command.equals("event")) {
                    throw new ToDoBeastException("OOPS :( !!! Time/date input for events should start with /at.");
                }
            }
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

    public void initialiseDataStorage() {
        try {
            fileManager.initialiseDirectory(dataDirectoryName);
            fileManager.createFile(dataDirectoryName + dataFileName);
        } catch (IOException e) {
            System.err.println("oops");
            e.printStackTrace();
        }
    }

    public void loadTasksFromStorage() {
        try {
            StringBuilder tasks = fileManager.loadTasksFromFile(dataDirectoryName + dataFileName);
            taskManager.loadTasksFromStorage(tasks.toString());
        } catch (FileNotFoundException e) {
            System.err.println("oops");
            e.printStackTrace();
        }
    }

    public void writeDataToStorage() {
        try {
            String tasks = taskManager.returnTaskListAsString();
            fileManager.overwriteToFile(dataDirectoryName + dataFileName, tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
