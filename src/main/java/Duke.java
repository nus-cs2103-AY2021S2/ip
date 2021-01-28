import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void loadData(File tasksFile, ArrayList<Task> taskList) throws IOException {
        String currDirectory = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(currDirectory, "data");

        if (!java.nio.file.Files.exists(path)) {
            java.nio.file.Files.createDirectory(path);
        }

        if (!tasksFile.exists()) {
            tasksFile.createNewFile();
        }

        Scanner readFile = new Scanner(tasksFile);

        while (readFile.hasNextLine()) {
            String taskName;
            String nextTask = readFile.nextLine();
            boolean done = false;
            if (nextTask.charAt(4) == '1') {
                done = true;
            }
            if (nextTask.startsWith("T")) {
                taskName = nextTask.substring(8);
                taskList.add(new Todo(taskName, done));
            } else {
                int index = nextTask.lastIndexOf("|");
                taskName = nextTask.substring(8, index - 1);
                String dateTime = nextTask.substring(index + 2);
                String dateString = dateTime.substring(0, 11);
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                LocalDate date = LocalDate.parse(dateString, dateFormatter);
                String time = dateTime.substring(12);

                if (nextTask.startsWith("E")) {
                    taskList.add(new Event(taskName, done, date, time));
                } else if (nextTask.startsWith("D")) {
                    taskList.add(new Deadline(taskName, done, date, time));
                }
            }
        }

    }

    public static void main(String[] args) throws DukeException, IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File tasksFile = new File("data/duke.txt");
        loadData(tasksFile, taskList);

        System.out.println("Hello! I'm Bob :D\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        boolean updates = false;

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye! See you soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("This is your list of tasks:\n");
                for (int i = 0; i < taskList.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + "." + taskList.get(i));
                }
            } else if (userInput.length() >= 4 && userInput.startsWith("done")) {
                try {
                    String indexString = userInput.substring(5).strip();
                    int index = Integer.parseInt(indexString);
                    Task updatedTask = taskList.get(index - 1);
                    updatedTask.status(true);
                    System.out.println("Good job! This task has been marked as done :)\n" + updatedTask);
                    updates = true;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Remember to specify which task you are done with " +
                            "using a valid number", e);
                } catch (NumberFormatException e) {
                    throw new DukeException("Invalid format, please try again using numbers only.", e);
                }
            } else if (userInput.length() >= 4 && userInput.startsWith("todo")) {
                try {
                    String taskName = userInput.substring(5);
                    Todo newTodo = new Todo(taskName);
                    taskList.add(newTodo);
                    System.out.println("Alright, I have added this new todo.\n" +
                            newTodo + "\n" +
                            "There are a total of " + taskList.size() + " tasks now.");
                    updates = true;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("The description of a todo cannot be empty.", e);
                }
            } else if (userInput.length() >= 5 && userInput.startsWith("event")) {
                try {
                    int taskIndex = userInput.indexOf("/at");
                    if (taskIndex != -1) {
                        String taskName = userInput.substring(6, taskIndex - 1);
                        String dateTime = userInput.substring(taskIndex + 4);
                        String dateString = dateTime.substring(0, 10);
                        String timeString = dateTime.substring(11);
                        LocalDate date = LocalDate.parse(dateString);
                        Event newEvent = new Event(taskName, date, timeString);
                        taskList.add(newEvent);
                        System.out.println("Alright, I have added this new event.\n" +
                                newEvent + "\n" +
                                "There is a total of " + taskList.size() + " tasks now.");
                        FileWriter fw = new FileWriter("duke.txt", true);
                        fw.write( "E | 0 | " + taskName + " | " +  date + " "
                                + timeString + System.lineSeparator());
                        fw.close();
                    } else {
                        System.out.println("There is no event timing detected!\n" +
                                "Please try again with a correct format");
                    }
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("The description of an event includes date and time.", e);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Please enter the date in the proper format: yyyy-mm-dd", e);
                }
            } else if (userInput.length() >= 8 && userInput.startsWith("deadline")) {
                try {
                    int deadlineIndex = userInput.indexOf("/by");
                    if (deadlineIndex != -1) {
                        String taskName = userInput.substring(9, deadlineIndex - 1);
                        String deadlineTime = userInput.substring(deadlineIndex + 4);
                        String deadlineString = deadlineTime.substring(0, 10);
                        String timeString = deadlineTime.substring(11);
                        LocalDate deadline = LocalDate.parse(deadlineString);
                        Deadline newDeadline = new Deadline(taskName, deadline, timeString);
                        taskList.add(newDeadline);
                        System.out.println("Alright, I have added this new deadline.\n" +
                                newDeadline + "\n" +
                                "There is a total of " + taskList.size() + " tasks now.");
                        FileWriter fw = new FileWriter("duke.txt", true);
                        fw.write( "D | 0 | " + taskName + " | " +  deadline + " "
                                + timeString + System.lineSeparator());
                        fw.close();
                    } else {
                        System.out.println("There is no deadline time and date detected!\n" +
                                "Please try again with a correct format");
                    }

                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("The description of a deadline includes date and time.", e);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Please enter the date in the proper format: yyyy-mm-dd", e);
                }
            } else if (userInput.length() >= 6 && userInput.startsWith("delete")) {
                try {
                    String indexString = userInput.substring(7);
                    int index = Integer.parseInt(indexString);
                    Task deletedTask = taskList.remove(index - 1);
                    System.out.println("Alright, this task has been removed.\n" +
                            deletedTask + "\nThere are " + taskList.size() + " tasks left.");
                    updates = true;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Remember to specify which task you want to remove" +
                            "using a valid number", e);
                } catch (NumberFormatException e) {
                    throw new DukeException("Invalid format, please try again using numbers only.", e);
                }
            } else {
                System.out.println("Sorry, I have no idea what that means :( Please try again!");
            }
            if (updates) {
                FileWriter fw = new FileWriter("duke.txt");
                for (Task nextTask : taskList) {
                    String done = nextTask.done ? "1" : "0";
                    String type = nextTask.toString().substring(1, 2);
                    String name = nextTask.name;
                    String time = "";
                    if (type.equals("E")) {
                        int index = nextTask.toString().indexOf("at: ");
                        time = nextTask.toString().substring(index + 4, nextTask.toString().length() - 1);
                    } else if (type.equals("D")) {
                        int index = nextTask.toString().indexOf("by: ");
                        time = nextTask.toString().substring(index + 4, nextTask.toString().length() - 1);
                    }
                    fw.write(type + " | " + done + " | " + name + " "
                            + time + System.lineSeparator());
                }
                fw.close();
                updates = false;
            }
        }
    }
}
