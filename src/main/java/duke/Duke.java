package duke;

import java.util.List;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {

        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load()); // returns a List<Task> of all tasks
        } catch (DukeException e) {
            ui.showLoadingError();
            ui.showLine();
            tasks = new TaskList();
        }
    }

    public void run() {

        // initialise all necessary variables
        Scanner inputScanner = new Scanner(System.in);
        Boolean terminate = false; // to check if the chatbot should be terminated

        ui.showLine();
        ui.showWelcomeMsg();

        Parser parser = new Parser();
        String[] parsedInput;

        while (!terminate) {

            String userInput = inputScanner.nextLine();

            try {
                parsedInput = parser.processInput(userInput);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                continue;
            }
            if (parsedInput[0].equals("LST")) {
                ui.showListMsg();
                tasks.printTask();
                ui.showLine();
            } else if (parsedInput[0].equals("BYE")) {
                ui.showByeMsg();
                ui.showLine();
                terminate = true;
            } else if (parsedInput[0].equals("DON")) {
                int taskIndex = Integer.parseInt(parsedInput[1]);

                if (tasks.checkTaskExist(taskIndex)) {
                    Task currTask = tasks.getTask(taskIndex);
                    currTask.markAsDone();

                    ui.showDoneMsg();
                    ui.printTask(currTask);
                    ui.showLine();

                    storage.save(tasks);
                } else {
                    System.out.println("Requested task does not exist");
                    ui.showLine();
                }
            } else if (parsedInput[0].equals("TDO")) {
                Todo newTodo = new Todo(parsedInput[1]);
                tasks.addTask(newTodo);

                ui.showTaskMsg();
                ui.printTask(newTodo);
                ui.showTaskCount(tasks.getTaskCount());
                ui.showLine();

                storage.save(tasks);
            } else if (parsedInput[0].equals("DDL")) {
                Deadline newDl = new Deadline(parsedInput[1], parsedInput[2]);
                tasks.addTask(newDl);

                ui.showTaskMsg();
                ui.printTask(newDl);
                ui.showTaskCount(tasks.getTaskCount());
                ui.showLine();

                storage.save(tasks);
            } else if (parsedInput[0].equals("ENT")) {
                Event newEnt = new Event(parsedInput[1], parsedInput[2]);
                tasks.addTask(newEnt);

                ui.showTaskMsg();
                ui.printTask(newEnt);
                ui.showTaskCount(tasks.getTaskCount());
                ui.showLine();

                storage.save(tasks);
            } else if (parsedInput[0].equals("DLT")) {
                int taskIndex = Integer.parseInt(parsedInput[1]);

                if (tasks.checkTaskExist(taskIndex)) {
                    Task currTask = tasks.getTask(taskIndex);
                    tasks.deleteTask(taskIndex);

                    ui.showDeleteMsg();
                    ui.printTask(currTask);
                    ui.showLine();

                    storage.save(tasks);
                } else {
                    System.out.println("Requested task does not exist");
                    ui.showLine();
                }

            } else if (parsedInput[0].equals("FND")) {
                String keyword = parsedInput[1];

                List<Task> foundTasks = tasks.findTask(keyword);

                if (foundTasks.isEmpty()) {
                    ui.showCannotFind();
                    ui.showLine();
                } else {
                    ui.showFoundText();
                    int i = 1;
                    for (Task t : foundTasks) {
                        System.out.println(i + ". " + t.toString());
                        i++;
                    }
                    ui.showLine();
                }

            } else {
                System.out.println("Something went wrong!");
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}