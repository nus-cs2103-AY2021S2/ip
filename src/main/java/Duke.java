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

    public static void writeToFile(File file, ArrayList<Task> list) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static ArrayList<Task> readFromFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Task> result = (ArrayList<Task>) ois.readObject();
            ois.close();
            fis.close();
            return result;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {

        ArrayList<Task> list = new ArrayList<>();
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File data = new File(dir,"saved_tasks");
        if (!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            list = readFromFile(data);
        }

        Scanner sc = new Scanner(System.in);
        String lineSpacing = "____________________________________________________________";
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" + lineSpacing);
        String taskConfirmation = "Got it. I've added this task:\n";
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
                    writeToFile(data, list);
                    System.out.println(taskConfirmation + t
                            + "\nNow you have " + list.size()
                            + (list.size() < 2 ? " task " : " tasks ") + "in the list.\n"
                            + lineSpacing);
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
                    writeToFile(data, list);
                    System.out.println(taskConfirmation + d
                            + "\nNow you have " + list.size()
                            + (list.size() < 2 ? " task " : " tasks ") + "in the list.\n"
                            + lineSpacing);
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
                    writeToFile(data, list);
                    System.out.println(taskConfirmation + e
                            + "\nNow you have " + list.size()
                            + (list.size() < 2 ? " task " : " tasks ") + "in the list.\n"
                            + lineSpacing);
                    break;
                case LIST:
                    if (list.isEmpty()) {
                        System.out.println("There are no tasks in your list. Hooray!\n" + lineSpacing);
                        continue;
                    }
                    System.out.println("Here are the tasks in your list:");
                    int num = 1;
                    for (Task task : list) {
                        System.out.println(num + "." + task);
                        num++;
                    }
                    System.out.println(lineSpacing);
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
                        list.get(index - 1).markAsDone();
                        writeToFile(data, list);
                        System.out.println("Nice! I've marked this task as done:\n" + list.get(index - 1)
                                + "\n" + lineSpacing);
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
                        writeToFile(data, list);
                        System.out.println("Noted! I've removed this task:\n" + task
                                + "\nNow you have " + list.size()
                                + (list.size() == 1 ? " task " : " tasks ") + "in the list.\n"
                                + lineSpacing);
                    } catch (NumberFormatException exception) {
                        throw new DukeException("OOPS!!! You did not enter the number corresponding to the task.");
                    }
                    break;
                case BYE:
                    System.out.println("Bye. Hope to see you again soon!\n" + lineSpacing);
                    byeFlag = false;
                    break;
                }
            } catch (DukeException e) {
                System.out.println("Duke has encountered an error: " + e.getMessage()
                        + "\n" + lineSpacing);
            }
        }
    }
}