import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;


public class Duke {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        createSaveFile();
        List<Task> list = readSaveFile();

        greet();
        commandList(list);
    }

    //Duke greets the user.
    public static void greet() {
        System.out.println("\t____________________________________________________________\n"
                        + "\tHello! I'm Duke\n\tWhat can I do for you?\n"
                        + "\t____________________________________________________________\n");
    }

    //Echos the input back to the user.
    public static void echo() {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println("\t____________________________________________________________\n"
                    + "\t" + input + "\n"
                    + "\t____________________________________________________________\n");
            input = sc.nextLine();
        }
        System.out.println("\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n"
        );
    }

    //Adds the input in a list and echo it back to the user.
    //Prints the list if input is "list"
    //Mark task as done if input is "done" with task number.
    public static void commandList(List<Task> list) {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                showList(list);
            } else {
                try {
                    String[] check = input.split(" ");
                    if (check[0].equals("done")) {
                        markTaskDone(list, check);
                    } else if (check[0].equals("delete")) {
                        deleteFromList(list, check);
                    } else {
                        addToList(list, input, check);
                    }
                } catch (TaskNotFoundException e) {
                    System.out.println("\t____________________________________________________________\n"
                                    + "\tTask not in list.\n"
                                    + "\t____________________________________________________________\n");
                } catch (CommandNotValidException e) {
                    System.out.println("\t____________________________________________________________\n"
                                    + "\tCommand not valid. Please use \"todo\", \"deadline\"\n"
                                    + "\tor \"event\" followed by task description to add new tasks.\n"
                                    + "\tPlease use \"list\" to view your list of tasks.\n"
                                    + "\tPlease use \"done\" followed by index to mark completed tasks.\n"
                                    + "\tPlease use \"delete\" followed by index to delete tasks.\n"
                                    + "\tPlease use \"bye\" to exit.\n"
                                    + "\t____________________________________________________________\n");
                } catch (DescriptionNotFoundException e) {
                    System.out.println("\t____________________________________________________________\n"
                                    + "\tPlease provide description for your task.\n"
                                    + "\t____________________________________________________________\n");
                } catch (DateNotFoundException e) {
                    System.out.println("\t____________________________________________________________\n"
                                    + "\tPlease enter a valid deadline (after \"/by\") for Deadline Tasks\n"
                                    + "\tor time (after \"/at\") for Event Tasks.\n"
                                    + "\t____________________________________________________________\n");
                } catch (InvalidTaskSelectionException e) {
                    System.out.println("\t____________________________________________________________\n"
                                    + "\tPlease enter task number after command.\n"
                                    + "\t____________________________________________________________\n");
                } catch (IOException e) {
                    System.out.println("\t____________________________________________________________\n"
                            + "\tError happened while trying to edit save file.\n"
                            + "\t____________________________________________________________\n");
                }
            }
            input = sc.nextLine();
        }
        System.out.println("\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n");
    }

    //Prints out the items in the list.
    public static void showList(List<Task> list) {
        if (list.size() == 0) {
            System.out.println("\t____________________________________________________________\n"
                        + "\tThere are no items in your list.\n"
                        + "\t____________________________________________________________\n");
        } else {
            System.out.println("\t____________________________________________________________\n"
                    + "\tHere are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + list.get(i).toString());
            }
            System.out.println("\t____________________________________________________________\n");
        }
    }

    //Check if string is number
    public static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Mark numbered task in list as done.
    public static void markTaskDone(List<Task> list, String[] check)
            throws TaskNotFoundException, InvalidTaskSelectionException, IOException {
        if (check.length == 1 || !isNumber(check[1])) {
            throw new InvalidTaskSelectionException();
        }
        int num = Integer.parseInt(check[1]);
        if (num > 0 && num <= list.size()) {
            list.get(num - 1).markAsDone();
            System.out.println("\t____________________________________________________________\n"
                    + "\tNice! I've marked this task as done:\n\t\t"
                    + list.get(num - 1).toString() + "\n"
                    + "\t____________________________________________________________\n");

        } else {
            throw new TaskNotFoundException();
        }
        saveToSaveFile(list);
    }

    //Add different type of tasks in list.
    public static void addToList(List<Task> list, String input, String[] check)
            throws CommandNotValidException, DescriptionNotFoundException,
                    DateNotFoundException, IOException {
        Task temp;
        String description;
        String date;

        if (check[0].equals("todo") || check[0].equals("deadline") || check[0].equals("event")) {
            if (check.length == 1) {
                throw new DescriptionNotFoundException();
            }
            if (check[0].equals("todo")) {
                description = input.substring(5).trim();
                temp = new TodoTask(description);
            } else if (check[0].equals("deadline")) {
                int index = input.lastIndexOf("/by");
                if (index == -1) {
                    throw new DateNotFoundException();
                }
                description = input.substring(9, index - 1);
                date = input.substring(index + 3).trim();
                if (date.isEmpty()) {
                    throw new DateNotFoundException();
                }
                temp = new DeadlineTask(description, date);
            } else {
                int index = input.lastIndexOf("/at");
                if (index == -1) {
                    throw new DateNotFoundException();
                }
                description = input.substring(6, index - 1);
                date = input.substring(index + 3).trim();
                if (date.isEmpty()) {
                    throw new DateNotFoundException();
                }
                temp = new EventTask(description, date);
            }
        } else {
            throw new CommandNotValidException();
        }
        list.add(temp);
        System.out.println("\t____________________________________________________________\n"
                + "\tGot it. I've added this task:\n"
                + "\t   " + temp.toString() + "\n"
                + "\tNow you have " + list.size() + " tasks in the list.\n"
                + "\t____________________________________________________________\n");
        saveToSaveFile(list);
    }

    //Delete task from list
    public static void deleteFromList(List<Task> list, String[] check)
            throws InvalidTaskSelectionException, TaskNotFoundException, IOException {
        if (check.length == 1 || !isNumber(check[1])) {
            throw new InvalidTaskSelectionException();
        }
        int num = Integer.parseInt(check[1]);
        if (num > 0 && num <= list.size()) {
            Task deleted = list.get(num - 1);
            list.remove(num - 1);
            System.out.println("\t____________________________________________________________\n"
                    + "\tNoted. I've removed this task:\n"
                    + "\t   " + deleted.toString() + "\n"
                    + "\tNow you have " + list.size() + " tasks in the list.\n"
                    + "\t____________________________________________________________\n");

        } else {
            throw new TaskNotFoundException();
        }
        saveToSaveFile(list);
    }

    //Creates directory and file if it doesn't exists.
    public static void createSaveFile() {
        try {
            File file = new File("../ip/data");
            file.mkdir();

            file = new File("../ip/data/Duke.txt");
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error happened while trying to create save file");
        }
    }

    public static List<Task> readSaveFile() {
        List<Task> taskList = new ArrayList<>();

        try {
            File file = new File("../ip/data/Duke.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String taskString = reader.nextLine();
                if (taskString.length() < 9) {
                    throw new InvalidSaveFileFormatException();
                }
                Task task;
                char taskType = taskString.charAt(0);
                char taskCompletion = taskString.charAt(4);
                String taskDescription;
                String taskDate;

                if (taskCompletion != '1' && taskCompletion != '0') {
                    throw new InvalidSaveFileFormatException();
                }

                if (taskType == 'T') {
                    taskDescription = taskString.substring(8);
                    task = new TodoTask(taskDescription);
                } else if (taskType == 'D') {
                    int dateIndex = taskString.substring(8).indexOf('|');
                    if (dateIndex == -1) {
                        throw new InvalidSaveFileFormatException();
                    }
                    taskDescription = taskString.substring(8, dateIndex + 8).trim();
                    taskDate = taskString.substring(dateIndex + 9).trim();
                    if (taskDate.isEmpty()) {
                        throw new InvalidSaveFileFormatException();
                    }
                    task = new DeadlineTask(taskDescription, taskDate);
                } else if (taskType == 'E') {
                    int dateIndex = taskString.substring(8).indexOf('|');
                    if (dateIndex == -1) {
                        throw new InvalidSaveFileFormatException();
                    }
                    taskDescription = taskString.substring(8, dateIndex + 8).trim();
                    taskDate = taskString.substring(dateIndex + 9).trim();
                    if (taskDate.isEmpty()) {
                        throw new InvalidSaveFileFormatException();
                    }
                    task = new EventTask(taskDescription, taskDate);
                } else {
                    throw new InvalidSaveFileFormatException();
                }

                if (taskCompletion == '1') {
                    task.markAsDone();
                }

                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File does not exists.");
            System.exit(1);
        } catch (InvalidSaveFileFormatException e) {
            System.out.println("Error: Invalid content format in save file");
            System.exit(1);
        }

        return taskList;
    }

    //Save task list to save file
    public static void saveToSaveFile(List<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter("../ip/data/Duke.txt");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String saveLine = "";
            saveLine += task.getType() + " | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
            if (task.getType() == 'D') {
                DeadlineTask dateTask = (DeadlineTask) taskList.get(i);
                saveLine += " | " + dateTask.getDate();
            } else if (task.getType() == 'E') {
                EventTask dateTask = (EventTask) taskList.get(i);
                saveLine += " | " + dateTask.getDate();
            }
            saveLine += '\n';
            fileWriter.write(saveLine);
        }
        fileWriter.close();
    }
}
