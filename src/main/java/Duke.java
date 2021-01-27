import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Duke {

    public static DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public static void main(String[] args) {

        ArrayList<Task> list = Storage.readFromFile();

        Scanner sc = new Scanner(System.in);
        Ui.greet();
        boolean byeFlag = true;

        while (byeFlag) {
            try {
                String s = sc.nextLine();
                String[] arr = s.split(" ");
                Command command;
                try {
                    command = Command.valueOf(arr[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new DukeException("OOPS!!! I'm sorry, but that is an invalid command :-(");
                }
                switch (command) {
                case TODO:
                    if (arr.length < 2) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    ToDo t = new ToDo(s.substring(5));
                    list.add(t);
                    Storage.writeToFile(list);
                    Ui.taskAddConfirmation(t, list);
                    break;
                case DEADLINE:
                    String[] arr2 = s.split(" /by ");
                    if (arr.length < 2 || arr2[0].split(" ").length < 2) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    if (arr2.length < 2) {
                        throw new DukeException("OOPS!!! You did not set a deadline using '/by'.");
                    }
                    LocalDateTime deadlineDate;
                    try {
                        deadlineDate = LocalDateTime.parse(arr2[1], DATETIME_FORMAT);
                    } catch (DateTimeParseException e) {
                        throw new DukeException("OOPS!!! Please input date and time in the following format: dd-MM-yyyy HHmm.");
                    }
                    Deadline d = new Deadline(arr2[0].substring(9), deadlineDate);
                    list.add(d);
                    Storage.writeToFile(list);
                    Ui.taskAddConfirmation(d, list);
                    break;
                case EVENT:
                    String[] arr3 = s.split(" /at ");
                    if (arr.length < 2 || arr3[0].split(" ").length < 2) {
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    }
                    if (arr3.length < 2) {
                        throw new DukeException("OOPS!!! You did not set the date/time of the event using '/at'.");
                    }
                    LocalDateTime eventDate;
                    try {
                        eventDate = LocalDateTime.parse(arr3[1], DATETIME_FORMAT);
                    } catch (DateTimeParseException e) {
                        throw new DukeException("OOPS!!! Please input date and time in the following format: dd-MM-yyyy HHmm.");
                    }
                    Event e = new Event(arr3[0].substring(6), eventDate);
                    list.add(e);
                    Storage.writeToFile(list);
                    Ui.taskAddConfirmation(e, list);
                    break;
                case LIST:
                    Ui.listTasks(list);
                    break;
                case DONE:
                    if (arr.length < 2) {
                        throw new DukeException("OOPS!!! You did not enter the number corresponding to the task.");
                    }
                    try {
                        int index = Integer.parseInt(arr[1]);
                        if (index < 1 || index > list.size()) {
                            throw new DukeException("OOPS!!! The numbered task does not exist.");
                        }
                        Task task = list.get(index - 1);
                        task.markAsDone();
                        Storage.writeToFile(list);
                        Ui.taskDoneConfirmation(list, task);
                    } catch (NumberFormatException ex) {
                        throw new DukeException("OOPS!!! You did not enter the number corresponding to the task.");
                    }
                    break;
                case DELETE:
                    if (arr.length < 2) {
                        throw new DukeException("OOPS!!! You did not enter the number corresponding to the task.");
                    }
                    try {
                        int index = Integer.parseInt(arr[1]);
                        if (index < 1 || index > list.size()) {
                            throw new DukeException("OOPS!!! The numbered task does not exist.");
                        }
                        Task task = list.get(index - 1);
                        list.remove(index - 1);
                        Storage.writeToFile(list);
                        Ui.taskDeleteConfirmation(list, task);
                    } catch (NumberFormatException exception) {
                        throw new DukeException("OOPS!!! You did not enter the number corresponding to the task.");
                    }
                    break;
                case BYE:
                    Ui.byeMessage();
                    byeFlag = false;
                    break;
                }
            } catch (DukeException e) {
                Ui.dukeExceptionMessage(e);
            }
        }
    }
}