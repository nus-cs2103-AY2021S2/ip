import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Duke {
    public static final String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String divider = "____________________________________________________________\n";
    public static final String filePath = "data/duke.txt";

    public static void main(String[] args) {
        printMessage(logo + "\nHello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        List<Task> list = readFromFile();

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] check = input.split(" ");
            if(input.equals("bye")) {
                printMessage("Bye. Hope to see you again soon!");
                writeToFile(list);
                break;
            } else if(input.equals("list")) {
                StringBuilder message = new StringBuilder();
                if(list.isEmpty()) {
                    message.append("List is empty.");
                } else {
                    for(int i = 1; i <= list.size(); i++) {
                        message.append("  " + i + ". " + list.get(i-1));
                        if(i < list.size()) {
                            message.append("\n");
                        }
                    }
                }
                printMessage(message.toString());
            } else if(check[0].equals("done")) {
                try {
                    checkEmptyInput(check);
                    list.get(Integer.parseInt(check[1])-1).markAsDone();
                    printMessage("Nice! I've marked this task as done:\n  " + list.get(Integer.parseInt(check[1])-1));
                    writeToFile(list);
                } catch (DukeException error) {
                    printMessage("OOPS!!! Please select an item to mark as done.");
                } catch (IndexOutOfBoundsException error) {
                    printMessage("OOPS!!! Selected item does not exist.");
                }
            } else if(check[0].equals("delete")) {
                try {
                    checkEmptyInput(check);
                    printMessage("Noted. I've removed this task:\n  " + list.remove(Integer.parseInt(check[1])-1) + "\nNow you have " + list.size() + " tasks in the list.");
                    writeToFile(list);
                } catch (DukeException error) {
                    printMessage("OOPS!!! Please select an item to delete.");
                } catch (IndexOutOfBoundsException error) {
                    printMessage("OOPS!!! Selected item does not exist.");
                }
            } else if(check[0].equals("todo")) {
                try {
                    checkEmptyInput(check);
                    Todo curr = new Todo(input.substring(5,input.length()));
                    list.add(curr);
                    printMessage("Got it. I've added this task:\n  " + curr + "\nNow you have " + list.size() + " tasks in the list.");
                    writeToFile(list);
                } catch (DukeException error) {
                    printMessage("OOPS!!! The description of a todo cannot be empty.");
                }
            } else if(check[0].equals("deadline")) {
                try {
                    checkEmptyInput(check);
                    String[] temp = input.substring(9, input.length()).split(" /by ");
                    Deadline curr = new Deadline(temp[0], LocalDate.parse(temp[1]));
                    list.add(curr);
                    printMessage("Got it. I've added this task:\n  " + curr + "\nNow you have " + list.size() + " tasks in the list.");
                    writeToFile(list);
                } catch (DukeException error) {
                    printMessage("OOPS!!! The description of a deadline cannot be empty");
                } catch(DateTimeParseException e) {
                    printMessage("OOPS!!! The date provided is invalid");
                }
            } else if(check[0].equals("event")) {
                try {
                    checkEmptyInput(check);
                    String[] temp = input.substring(6, input.length()).split(" /at ");
                    Event curr = new Event(temp[0], temp[1]);
                    list.add(curr);
                    printMessage("Got it. I've added this task:\n  " + curr + "\nNow you have " + list.size() + " tasks in the list.");
                    writeToFile(list);
                } catch (DukeException error) {
                    printMessage("OOPS!!! The description of an event cannot be empty");
                }
            } else {
                printMessage("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private static void printMessage(String message) {
        System.out.println(divider + message + "\n" + divider);
    }

    private static List<Task> readFromFile() {
        List<Task> data = new ArrayList<>();
        try {
            File txt = new File(filePath);
            if(!txt.exists()) {
                File parentDir = txt.getParentFile();
                if(!parentDir.exists()) {
                    parentDir.mkdir();
                }
                txt.createNewFile();
            }
            Scanner myReader = new Scanner(txt);
            while(myReader.hasNextLine()) {
                String[] taskInfo = myReader.nextLine().split(" \\| ");
                Task curr;
                switch(taskInfo[0]) {
                    case "T":
                        curr = new Todo(taskInfo[2]);
                        if(taskInfo[1].equals("\u2713")) {
                            curr.markAsDone();
                        }
                        data.add(curr);
                        break;
                    case "D":
                        curr = new Deadline(taskInfo[2], LocalDate.parse(taskInfo[3], DateTimeFormatter.ofPattern("dd MMM YYYY")));
                        if(taskInfo[1].equals("\u2713")) {
                            curr.markAsDone();
                        }
                        data.add(curr);
                        break;
                    case "E":
                        curr = new Event(taskInfo[2], taskInfo[3]);
                        if(taskInfo[1].equals("\u2713")) {
                            curr.markAsDone();
                        }
                        data.add(curr);
                        break;
                }
            }
        } catch(IOException error) {
            printMessage("OOPS!!! It seems I've encountered an error. Please try again :-(");
        }
        return data;
    }

    private static void writeToFile(List<Task> list) {
        StringBuilder data = new StringBuilder();
        try {
            FileWriter writer = new FileWriter(filePath);
            for(Task task : list) {
                data.append(task.toString() + "\n");
            }
            writer.write(data.toString());
            writer.close();
        } catch(IOException error) {
            printMessage("OOPS!!! It seems I've encountered an error. Please try again :-(");
        }
    }

    private static boolean checkEmptyInput(String[] input) throws DukeException {
        if(input.length <= 1) {
            throw new DukeException();
        }
        return true;
    }
}
