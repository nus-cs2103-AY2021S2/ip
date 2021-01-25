import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Pason {
    private static List<Task> tasks = new ArrayList<>();
    private static String FILE_DIRECTORY = "data";
    private static String FILE_NAME = "tasks.txt";
    public static void main(String[] args) {
        String input;
        String splitInput[];
        Scanner scanner = new Scanner(System.in);
        try {
            loadTasks();
        } catch(IOException e) {
            printMessage("Oops! " + e.getMessage());
        } catch(Exception e) {
            printMessage("Oops! " + e.getMessage());
        }

        printMessage("Hey! It's PAson, ready to help :)\nHow can I help you today?");
        while(scanner.hasNext()) {
            try {
                input = scanner.nextLine();
                splitInput = input.split(" ");
                String command = splitInput[0].toLowerCase();
                switch (command) {
                case "bye":
                    printMessage("Bye! I shall go rest now. PAge me when you need me!");
                    return;
                case "list":
                    listTasks();
                    break;
                case "done":
                    doneTask(Integer.parseInt(splitInput[1]));
                    break;
                case "delete":
                    deleteTask(Integer.parseInt(splitInput[1]));
                    break;
                case "todo":
                    addToDo(input);
                    break;
                case "deadline":
                    addDeadline(input);
                    break;
                case "event":
                    addEvent(input);
                    break;
                default:
                    throw new PasonException("I can't help you with this command yet. Sorry!");
                }
            } catch(Exception e) {
                printMessage("Oops! " + e.getMessage());
            }
        }
    }

    public static void printMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public static void doneTask(int index) {
        try {
            if(tasks.get(index - 1).isDone()) {
                throw new PasonException("You've already marked this task as done.");
            }
            tasks.get(index - 1).markAsDone();
            saveAllTasks();
            printMessage("Good job! I've marked this task as done:\n" + tasks.get(index - 1));
        } catch(IndexOutOfBoundsException e) {
            printMessage("Oops! We couldn't find this task. Please enter the correct task number.");
        } catch(Exception e) {
            printMessage("Oops! " + e.getMessage());
        }
    }

    public static void listTasks() {
        if(tasks.size() == 0) {
            printMessage("There are no tasks in your list. Time to add some!");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1)+". " + tasks.get(i));
            }
            System.out.println("____________________________________________________________");
        }
    }

    public static void deleteTask(int index) {
        index = index - 1;
        try {
            if(index > tasks.size() - 1 || index < 0) {
                throw new PasonException("You've entered an invalid task number.");
            } else {
                printMessage("Okay! I've removed this task:\n\t" + tasks.get(index) + "\nNow there are " + (tasks.size() - 1) + " tasks in your list.");
                tasks.remove(index);
                saveAllTasks();
            }
        } catch(Exception e) {
            printMessage("Oops! " + e.getMessage());
        }
    }

    public static void addToDo(String input) {
        try {
            Pattern p = Pattern.compile("(todo) ([\\w ]*)");
            Matcher m = p.matcher(input);
            if(!m.find()) {
                throw new PasonException("Please include a description for your todo task.");
            }
            ToDo newToDo = new ToDo(m.group(2));
            saveTask(newToDo);
            printMessage("Done! I've added a new todo:\n\t" + newToDo + "\nNow there are " + tasks.size() + " tasks in your list.");
        } catch(Exception e) {
            printMessage("Oops! " + e.getMessage());
        }
    }

    public static void addDeadline(String input) {
        try {
            String[] splitInput;
            splitInput = input.substring(8).trim().split(" /by ");
            if(splitInput.length == 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                LocalDateTime deadlineBy = LocalDateTime.parse(splitInput[1], formatter);
                Deadline newDeadline = new Deadline(splitInput[0], deadlineBy);
                saveTask(newDeadline);
                printMessage("Done! I've added a new deadline:\n\t" + newDeadline + "\nNow there are " + tasks.size() + " tasks in your list.");
            } else if (splitInput.length == 1) {
                throw new PasonException("Please enter a by date for '" + splitInput[0] + "'");
            } else if (splitInput.length == 0) {
                throw new PasonException("Please enter a description followed by the date in the format: deadline <description> /by <when>");
            } else {
                throw new PasonException("You've entered an invalid format. Please use: deadline <description> /by <when>");
            }
        } catch(DateTimeParseException e) {
            printMessage("Oops! You've entered an invalid date and time format.\nPlease use: dd/mm/yyyy hh:mm");
        } catch(PasonException e) {
            printMessage("Oops! " + e.getMessage());
        } catch(Exception e) {
            printMessage("Oops! " + e.getMessage());
        }
    }

    public static void addEvent(String input) {
        try {
            String[] splitInput;
            splitInput = input.substring(5).trim().split(" /at ");
            if (splitInput.length == 2) {
                String[] dateAndTime = splitInput[1].split(" ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate eventDate = LocalDate.parse(dateAndTime[0], formatter);
                Event newEvent = new Event(splitInput[0], eventDate, (dateAndTime.length == 1 ? null : dateAndTime[1]));
                saveTask(newEvent);
                printMessage("Done! I've added a new event:\n\t" + newEvent + "\nNow there are " + tasks.size() + " tasks in your list.");
            } else if (splitInput.length == 1) {
                System.out.println(splitInput[0]);
                throw new PasonException("Please enter an at date for '" + splitInput[0] + "'");
            } else if (splitInput.length == 0) {
                throw new PasonException("Please enter a description followed by the date in the format: event <description> /at <when>");
            } else {
                throw new PasonException("You've entered an invalid format. Please use: event <description> /at <when>");
            }
        } catch(DateTimeParseException e) {
            printMessage("Oops! You've entered an invalid date format.\nPlease use: dd/mm/yyyy");
        } catch(PasonException e) {
            printMessage("Oops! " + e.getMessage());
        } catch(Exception e) {
            printMessage("Oops! " + e.getMessage());
        }
    }

    public static void loadTasks() throws FileNotFoundException {
        File directory = new File(FILE_DIRECTORY);
        File file = new File(FILE_DIRECTORY + "/" + FILE_NAME);
        if(!directory.exists()) {
            directory.mkdir();
        }
        if(!file.exists()) {
            try {
                file.createNewFile();
                printMessage("Just a heads up!\nPAson stores your tasks locally for your convenience.\nWe've created a new file here: " + FILE_DIRECTORY + "/" + FILE_NAME);
            } catch(IOException e) {
                printMessage("Oops! " + e.getMessage());
            }
        }

        Scanner s = new Scanner(file);
        Task task;
        int failedImports = 0;
        while (s.hasNext()) {
            task = parseFileEntry(s.nextLine());
            if(task != null) {
                tasks.add(task);
            } else {
                failedImports++;
            }
        }
        if(failedImports > 0) {
            printMessage("There was a problem parsing " + failedImports + " task(s) from the file.");
        }
    }

    public static void saveTask(Task task) {
        try {
            tasks.add(task);
            FileWriter fw = new FileWriter(FILE_DIRECTORY + "/" + FILE_NAME, true);
            fw.write(task.toFileFormat() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops!: " + e.getMessage());
        }
    }

    public static void saveAllTasks() {
        try {
            FileWriter fw = new FileWriter(FILE_DIRECTORY + "/" + FILE_NAME);
            for(int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toFileFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops!: " + e.getMessage());
        }
    }

    public static Task parseFileEntry(String text) {
        String[] splitString = text.split(" \\| ");
        if(splitString[0].equals("T") && splitString.length == 3) {
            ToDo newToDo = new ToDo(splitString[2]);
            if(splitString[1].equals("1")) {
                newToDo.markAsDone();
            }
            return newToDo;
        } else if(splitString[0].equals("E") && splitString.length == 4) {
            String[] eventDateAndExtra = splitString[3].split(" ");
            Event newEvent = new Event(splitString[2], LocalDate.parse(eventDateAndExtra[0]), (eventDateAndExtra.length == 1 ? null : eventDateAndExtra[1]));
            if(splitString[1].equals("1")) {
                newEvent.markAsDone();
            }
            return newEvent;
        } else if(splitString[0].equals("D") && splitString.length == 4) {
            Deadline newDeadline = new Deadline(splitString[2], LocalDateTime.parse(splitString[3]));
            if(splitString[1].equals("1")) {
                newDeadline.markAsDone();
            }
            return newDeadline;
        } else {
            return null;
        }
    }
}
