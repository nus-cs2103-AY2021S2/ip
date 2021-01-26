import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.time.LocalDate;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Ui ui = new Ui();
        Storage storage = new Storage(System.getProperty("user.dir") + "/data/duke.txt");

        ui.printIntro();
        Scanner scan = new Scanner(System.in);

        try {
            storage.loadFileContents(tasks);
        } catch (FileNotFoundException e) {
            storage.createFile();
        }

        loop:
        while (scan.hasNextLine()) {
            String input = scan.nextLine();

            ui.printLine();

            switch (input) {
                case "bye":
                    ui.printBye();
                    break loop;
                case "list":
                    ui.printList(tasks, storage.numTasks);
                    break;
                default:
                    try {
                        String[] inputArr = input.split(" ", 2);
                        String task = inputArr[0];

                        String scannedInput = inputArr[1];
                        String description;
                        LocalDate date;
                        int taskIndex;

                        switch (task) {
                            case "done":
                                taskIndex = Integer.parseInt(scannedInput) - 1;
                                String before = tasks.get(taskIndex).formatData();

                                tasks.get(taskIndex).markAsDone();
                                String after = tasks.get(taskIndex).formatData();

                                storage.modifyFile(before, after);

                                ui.printDone(tasks, taskIndex);
                                break;
                            case "delete":
                                taskIndex = Integer.parseInt(scannedInput) - 1;
                                Task deletedTask = tasks.get(taskIndex);

                                storage.deleteFromFile(deletedTask.formatData());

                                tasks.remove(taskIndex);
                                ui.printDelete(deletedTask.toString());

                                storage.numTasks--;
                                ui.printNumTasks(storage.numTasks);
                                break;
                            case "todo":
                                description = scannedInput;
                                ToDos todo = new ToDos(description);
                                tasks.add(todo);
                                ui.printAdd(tasks, storage.numTasks);
                                storage.addToFile(todo.formatData());

                                storage.numTasks++;
                                ui.printNumTasks(storage.numTasks);
                                break;
                            case "deadline":
                                String[] arrOfInputD = scannedInput.split("/by ");
                                description = arrOfInputD[0];
                                date = LocalDate.parse(arrOfInputD[1]);
                                Deadlines deadline = new Deadlines(description, date);
                                tasks.add(deadline);
                                ui.printAdd(tasks, storage.numTasks);
                                storage.addToFile(deadline.formatData());

                                storage.numTasks++;
                                ui.printNumTasks(storage.numTasks);
                                break;
                            case "event":
                                String[] arrOfInputE = scannedInput.split("/at ");
                                description = arrOfInputE[0];
                                date = LocalDate.parse(arrOfInputE[1]);
                                Events event = new Events(description, date);
                                tasks.add(event);
                                ui.printAdd(tasks, storage.numTasks);
                                storage.addToFile(event.formatData());

                                storage.numTasks++;
                                ui.printNumTasks(storage.numTasks);
                                break;
                            default:
                                ui.printIdkError();
                                break;
                        }
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (input.equals("todo") || input.equals("deadline") || input.equals("event")
                                || input.equals("done") || input.equals("delete")) {
                            ui.printEmptyDescError(input);
                        } else {
                            ui.printIdkError();
                        }
                    }
            }
            ui.printLine();
        }
    }
}
