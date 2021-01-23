import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import Exception.*;

public class UserInput {
    private Scanner sc;
    private List<Task> newStorage;

    public UserInput(Scanner sc) {
        this.newStorage = new ArrayList<>();
        this.sc = sc;
    }

    public UserInput(Scanner sc, List<Task> initialisedStorage) {
        this.newStorage = initialisedStorage;
        this.sc = sc;
    }

    public void executeInput() {
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            try {
                checkInput(userInput);

                String[] inputBreakdown = userInput.split(" ");
                if (inputBreakdown[0].equals("bye")) {
                    indentInput("Bye. Hope to see you again!");
                    updateHardDrive();
                    break;
                } else if (inputBreakdown[0].equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < this.newStorage.size(); i++) {
                        printTask(i, this.newStorage.get(i));
                    }
                } else if (inputBreakdown[0].equals("done")) {
                    System.out.println("Nice! I've marked this task as done:");
                    int selectedIndex = Integer.valueOf(inputBreakdown[1]) - 1;
                    this.newStorage.get(selectedIndex).setDone(true);
                    printTask(selectedIndex, this.newStorage.get(selectedIndex));
                } else if (inputBreakdown[0].equals("delete")) {
                    deleteTask(userInput);
                } else {
                    addTask(userInput);
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void indentInput(String input) {
        String line = "";
        for (int i = 0; i < 50; i++) {
            line = line.concat("_");
        }
        System.out.println(line);
        System.out.println("added: " + input);
        System.out.println(line);
    }

    public void printTask(int num, Task task) {
        if (task.getDone()) {
            System.out.println(num + 1 + "." + "[" + task.type + "]" + "[X] " + this.newStorage.get(num).getDescription());
        } else {
            System.out.println(num + 1 + "." + "[" + task.type + "]" + "[ ] " + this.newStorage.get(num).getDescription());
        }
    }

    public void printTaskWithNoNum(Task task) {
        System.out.println("[" + task.type + "]" + "[ ] " + task.getDescription());
    }

    public void addTask(String userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("Hmm");
        Task task;
        String[] input = userInput.split(" ");
        if (input[0].equals("todo")) {
            String description = userInput.substring(userInput.indexOf("todo") + 5);
            task = new Todo(description);
        } else if (input[0].equals("deadline")) {
            String description = userInput.substring(userInput.indexOf("deadline") + 9, userInput.indexOf("/by"));
            String deadlineDate = userInput.substring(userInput.indexOf("/by") + 4, userInput.indexOf("/by") + 14);
            LocalDate date = LocalDate.parse(deadlineDate, formatter);
            String deadLineTime = userInput.substring(userInput.indexOf("/by") + 15);
            LocalTime time = LocalTime.parse(deadLineTime, timeFormatter);
            task = new Deadline(description, date, time);
        } else {
            String description = userInput.substring(userInput.indexOf("event") + 6, userInput.indexOf("/at"));
            String eventDate = userInput.substring(userInput.indexOf("/at") + 4, userInput.indexOf("/at") + 14);
            LocalDate date = LocalDate.parse(eventDate, formatter);
            String deadLineTime = userInput.substring(userInput.indexOf("/at") + 15);
            LocalTime time = LocalTime.parse(deadLineTime, timeFormatter);
            task = new Event(description, date, time);
        }
        newStorage.add(task);
        System.out.println("Got it. I've added this task: ");
        printTaskWithNoNum(task);
        System.out.println("Now you have " + this.newStorage.size() + " tasks in the list.");
    }

    private void updateHardDrive() {
        try {
            Files.delete(Paths.get("./data/duke.txt"));
            FileInput fileInput = new FileInput();
            fileInput.loadFile("./data", "./data/duke.txt");
            FileWriter fileWriter = new FileWriter("./data/duke.txt");
            for (int i = 0; i < this.newStorage.size(); i++) {
                fileWriter.write(printInHardDrive(this.newStorage.get(i)));
            }
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String printInHardDrive(Task task) {
        if (task.getDate() != null || task.getTime() != null) {
            if (task.getDone()) {
                return task.getType() + " | 1 | " + task.getOnlyDescription() + " | " + task.getDate() + " " + task.getTime() + "\n";
            } else {
                return task.getType() + " | 0 | " + task.getOnlyDescription() + " | " + task.getDate() + " " + task.getTime() + "\n";
            }
        } else {
            if (task.getDone()) {
                return task.getType() + " | 1 | " + task.getDescription() + "\n";
            } else {
                return task.getType() + " | 0 | " + task.getDescription() + "\n";
            }
        }


    }





    public void deleteTask(String userInput) {
        String[] inputBreakdown = userInput.split(" ");
        int taskToBeDeleted = Integer.valueOf(inputBreakdown[1]) - 1;
        Task task = this.newStorage.get(taskToBeDeleted);
        this.newStorage.remove(taskToBeDeleted);
        System.out.println("Noted. I've removed this task:");
        printTaskWithNoNum(task);
        System.out.println("Now you have " + this.newStorage.size() + " tasks in the list.");
    }

    public void checkInput (String userInput) throws DukeException {
        String[] input = userInput.split(" ");
        List<String> possibleActionInputs = new ArrayList<>();
        List<String> possibleSingleInputs = new ArrayList<>();
        List<String> possibleTaskInputs = new ArrayList<>();
        possibleActionInputs.add("done");
        possibleActionInputs.add("delete");
        possibleSingleInputs.add("bye");
        possibleTaskInputs.add("todo");
        possibleTaskInputs.add("event");
        possibleTaskInputs.add("deadline");
        possibleSingleInputs.add("list");
        if (!possibleActionInputs.contains(input[0]) && !possibleSingleInputs.contains(input[0]) && !possibleTaskInputs.contains(input[0])) {
            throw new DukeException("user action is not recognised!");
        } else if ((possibleTaskInputs.contains(input[0]) || possibleActionInputs.contains(input[0])) && input.length == 1) {
                throw new DukeException("no description added!");
        } else if (possibleSingleInputs.contains(input[0]) && input.length > 1) {
            throw new DukeException("no description should be added for this command!");
        } else if (possibleTaskInputs.contains(input[0])) {
            switch(input[0]) {
                case "deadline":
                    if (!userInput.contains("/by")) {
                        throw new DukeException("Deadline entries must have a /by phrase!");
                    } else {
                        break;
                    }
                case "event":
                    if (!userInput.contains("/at")) {
                        throw new DukeException("Event entries must have a /at phrase!");
                    } else {
                        break;
                    }
            }
        } else if (possibleActionInputs.contains(input[0])) {
            if (input.length > 2) {
                throw new DukeException("enter a specific number");
            } else {
                try {
                    int number = Integer.parseInt(input[1]);
                    if (number < 0 || number > this.newStorage.size()) {
                        throw new DukeException("number entered does not match the list of tasks in list");
                    }
                } catch (NumberFormatException ex) {
                    throw new DukeException("Enter a number!");
                }
            }
        }
    }
}
