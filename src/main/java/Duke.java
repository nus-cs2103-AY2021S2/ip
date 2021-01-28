import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String divider = "____________________________________________________________\n";
    public static final String filePath = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch(DukeException error) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();

        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                Command command = parser.parseCommand(input);
                switch(command) {
                    case BYE:
                        ui.showGoodBye();
                        storage.save(tasks);
                        break;
                    case LIST:
                        ui.showTasks(tasks);
                        break;
                    case DONE:
                        try {
                            int index = parser.parseDoneCommand(input);
                            ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.markTaskAsDone(index));
                            storage.save(tasks);
                        } catch (DukeException error) {
                            ui.showErrorMessage("Please select a valid item to mark as done.");
                        } catch (IndexOutOfBoundsException error) {
                            ui.showErrorMessage("Selected item does not exist.");
                        }
                        break;
                    case DELETE:
                        try {
                            int index = parser.parseDeleteCommand(input);
                            ui.showMessage("Noted. I've removed this task:\n  " + tasks.deleteTask(index) + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                            storage.save(tasks);
                        } catch (DukeException error) {
                            ui.showErrorMessage("Please select an item to delete.");
                        } catch (IndexOutOfBoundsException error) {
                            ui.showErrorMessage("Selected item does not exist.");
                        }
                        break;
                    case TODO:
                        try {
                            Todo curr = parser.parseTodoCommand(input);
                            tasks.addTask(curr);
                            ui.showMessage("Got it. I've added this task:\n  " + curr + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                            storage.save(tasks);
                        } catch (DukeException error) {
                            ui.showErrorMessage("The description of a todo cannot be empty.");
                        }
                        break;
                    case DEADLINE:
                        try {
                            Deadline curr = parser.parseDeadlineCommand(input);
                            tasks.addTask(curr);
                            ui.showMessage("Got it. I've added this task:\n  " + curr + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                            storage.save(tasks);
                        } catch (DukeException error) {
                            ui.showErrorMessage("The description of a deadline cannot be empty");
                        } catch(DateTimeParseException error) {
                            ui.showErrorMessage("The date provided is invalid");
                        }
                        break;
                    case EVENT:
                        try {
                            Event curr = parser.parseEventCommand(input);
                            tasks.addTask(curr);
                            ui.showMessage("Got it. I've added this task:\n  " + curr + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                            storage.save(tasks);
                        } catch (DukeException error) {
                            ui.showErrorMessage("The description of an event cannot be empty");
                        }
                        break;
                    case HELP:
                        ui.showHelpMessage();
                        break;
                }
            } catch(DukeException error) {
                ui.showInputError();
            }


//            if(input.equals("bye")) {
//                printMessage("Bye. Hope to see you again soon!");
//                storage.save(tasks);
//                break;
//            } else if(input.equals("list")) {
//                StringBuilder message = new StringBuilder();
//                if(tasks.isEmpty()) {
//                    message.append("List is empty.");
//                } else {
//                    message.append("Here are your list of tasks:\n");
//                    for(int i = 1; i <= tasks.getSize(); i++) {
//                        message.append("  " + i + ". " + tasks.getTask(i - 1));
//                        if(i < tasks.getSize()) {
//                            message.append("\n");
//                        }
//                    }
//                }
//                printMessage(message.toString());
//            } else if(check[0].equals("done")) {
//                try {
//                    checkEmptyInput(check);
//                    printMessage("Nice! I've marked this task as done:\n  " + tasks.markTaskAsDone(Integer.parseInt(check[1])-1));
//                    storage.save(tasks);
//                } catch (DukeException error) {
//                    printMessage("OOPS!!! Please select an item to mark as done.");
//                } catch (IndexOutOfBoundsException error) {
//                    printMessage("OOPS!!! Selected item does not exist.");
//                }
//            } else if(check[0].equals("delete")) {
//                try {
//                    checkEmptyInput(check);
//                    printMessage("Noted. I've removed this task:\n  " + tasks.deleteTask(Integer.parseInt(check[1])-1) + "\nNow you have " + tasks.getSize() + " tasks in the list.");
//                    storage.save(tasks);
//                } catch (DukeException error) {
//                    printMessage("OOPS!!! Please select an item to delete.");
//                } catch (IndexOutOfBoundsException error) {
//                    printMessage("OOPS!!! Selected item does not exist.");
//                }
//            } else if(check[0].equals("todo")) {
//                try {
//                    checkEmptyInput(check);
//                    Todo curr = new Todo(input.substring(5,input.length()));
//                    tasks.addTask(curr);
//                    printMessage("Got it. I've added this task:\n  " + curr + "\nNow you have " + tasks.getSize() + " tasks in the list.");
//                    storage.save(tasks);
//                } catch (DukeException error) {
//                    printMessage("OOPS!!! The description of a todo cannot be empty.");
//                }
//            } else if(check[0].equals("deadline")) {
//                try {
//                    checkEmptyInput(check);
//                    String[] temp = input.substring(9, input.length()).split(" /by ");
//                    Deadline curr = new Deadline(temp[0], LocalDate.parse(temp[1]));
//                    tasks.addTask(curr);
//                    printMessage("Got it. I've added this task:\n  " + curr + "\nNow you have " + tasks.getSize() + " tasks in the list.");
//                    storage.save(tasks);
//                } catch (DukeException error) {
//                    printMessage("OOPS!!! The description of a deadline cannot be empty");
//                } catch(DateTimeParseException error) {
//                    printMessage("OOPS!!! The date provided is invalid");
//                }
//            } else if(check[0].equals("event")) {
//                try {
//                    checkEmptyInput(check);
//                    String[] temp = input.substring(6, input.length()).split(" /at ");
//                    Event curr = new Event(temp[0], temp[1]);
//                    tasks.addTask(curr);
//                    printMessage("Got it. I've added this task:\n  " + curr + "\nNow you have " + tasks.getSize() + " tasks in the list.");
//                    storage.save(tasks);
//                } catch (DukeException error) {
//                    printMessage("OOPS!!! The description of an event cannot be empty");
//                }
//            } else {
//                printMessage("OOPS!!! I'm sorry, but I don't know what that means :-(");
//            }
        }
    }

    public static void main(String[] args) {
        new Duke(filePath).run();
    }

    private static void printMessage(String message) {
        System.out.println(divider + message + "\n" + divider);
    }

//    private static List<Task> readFromFile() {
//        List<Task> data = new ArrayList<>();
//        try {
//            File txt = new File(filePath);
//            if(!txt.exists()) {
//                File parentDir = txt.getParentFile();
//                if(!parentDir.exists()) {
//                    parentDir.mkdir();
//                }
//                txt.createNewFile();
//            }
//            Scanner myReader = new Scanner(txt);
//            while(myReader.hasNextLine()) {
//                String[] taskInfo = myReader.nextLine().split(" \\| ");
//                Task curr;
//                switch(taskInfo[0]) {
//                    case "T":
//                        curr = new Todo(taskInfo[2]);
//                        if(taskInfo[1].equals("\u2713")) {
//                            curr.markAsDone();
//                        }
//                        data.add(curr);
//                        break;
//                    case "D":
//                        String localDate = taskInfo[3].replaceAll(" ", "-");
//                        curr = new Deadline(taskInfo[2], LocalDate.parse(localDate, DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
//                        if(taskInfo[1].equals("\u2713")) {
//                            curr.markAsDone();
//                        }
//                        data.add(curr);
//                        break;
//                    case "E":
//                        curr = new Event(taskInfo[2], taskInfo[3]);
//                        if(taskInfo[1].equals("\u2713")) {
//                            curr.markAsDone();
//                        }
//                        data.add(curr);
//                        break;
//                }
//            }
//        } catch(IOException | DateTimeParseException error) {
//            printMessage("OOPS!!! It seems I've encountered an error. Please try again :-(");
//        }
//        return data;
//    }

//    private static void writeToFile(List<Task> list) {
//        StringBuilder data = new StringBuilder();
//        try {
//            FileWriter writer = new FileWriter(filePath);
//            for(Task task : list) {
//                data.append(task.toString() + "\n");
//            }
//            writer.write(data.toString());
//            writer.close();
//        } catch(IOException error) {
//            printMessage("OOPS!!! It seems I've encountered an error. Please try again :-(");
//        }
//    }

    private static boolean checkEmptyInput(String[] input) throws DukeException {
        if(input.length <= 1) {
            throw new DukeException();
        }
        return true;
    }
}
