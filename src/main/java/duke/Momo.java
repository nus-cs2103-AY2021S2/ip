package duke;

import duke.task.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Momo {

    private Storage storage;
    private TaskList tasks;

    public Momo(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ParseException | IOException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.showInitUi();
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            input = sc.next();
            try {
                Command command = Parser.parseCommand(input);
                switch (command) {
                    case BYE:
                        Ui.showExitUi();
                        return;
                    case LIST:
                        Ui.showList(this.tasks);
                        break;
                    case DONE:
                        int i = sc.nextInt();
                        try {
                            Ui.showSuccessfulMark(tasks.mark(i));
                        } catch (IndexOutOfBoundsException e) {
                            Ui.showIndexOutOfBoundsError(tasks);
                        }
                        break;
                    case DELETE:
                        int j = sc.nextInt();
                        try {
                            Task taskToBeDeleted = tasks.delete(j);
                            Ui.showSuccessfulDelete(tasks, taskToBeDeleted);
                        } catch (IndexOutOfBoundsException e) {
                            Ui.showIndexOutOfBoundsError(tasks);
                        }
                        storage.save(tasks);
                        break;
                    default:
                        String nextInput = sc.nextLine();
                        try {
                            Task taskToBeAdded = Parser.parseDescription(command, nextInput);
                            tasks.add(taskToBeAdded);
                            Ui.showSuccessfulAdd(tasks, taskToBeAdded);
                            storage.save(tasks);
                        } catch (ParseException e) {
                            Ui.formatInChatBox(e.getMsgDes());
                        } catch (DateTimeParseException e) {
                            Ui.showDateParseError();
                        }
                }
            } catch (IllegalArgumentException e) {
                Ui.showGeneralError();
            }
        } while (true);
    }

    public static void main(String[] args) {
        new Momo("data/tasks.txt").run();
    }
}
