import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private final StringBuilder lengthOfChatBox = new StringBuilder();
    private final ArrayList<Task> taskList = new ArrayList<>();
    private final Path filePath = Paths.get("data/tasks.txt");
    private final String taskPath = "data/tasks.txt";

    private void setLengthOfChatBox() {
        lengthOfChatBox.append("\n");
        for (int i = 0; i < 50; i++) {
            lengthOfChatBox.append("-");
        }
        lengthOfChatBox.append("\n");
    }

    private void formatInChatBox(String s) {
        System.out.println(lengthOfChatBox + s + lengthOfChatBox);
    }

    public void init() {
        setLengthOfChatBox();
        String greeting = "Hello I'm Mike\nWhat can I do for you?\n";
        formatInChatBox(greeting);
    }

    public void list() {
        int numOfText = taskList.size();
        StringBuilder output = new StringBuilder();

        if (taskList.isEmpty()) {
            output.append("No tasks saved\n");
        } else {
            output.append("Here are the tasks in your list: \n");
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                output.append(i + 1).append(". ").append(currentTask).append("\n");
            }
        }
        formatInChatBox(output.toString());
    }

    public void add(String s) {
        taskList.add(new Task(s));
        String output = "Added: " + s;
        formatInChatBox(output);
    }

    public void mark(int index) {
        Task taskToBeMarked = taskList.get(index - 1);
        taskToBeMarked.markAsDone();
        formatInChatBox("Nice! I've marked this task as done: \n" + taskToBeMarked);

        try {
            save();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void exit() {
        formatInChatBox("Bye. Hope to see you again soon!\n");
    }

    public void add(String task, String description) throws TextException {
        try {
            if (description.equals(" ") || description.isEmpty()) {
                throw new TextException("OOPS!!! The description of a " + task.toLowerCase(Locale.ROOT) + " cannot be empty.");
            }
            if (task.equals("TODO")) {
                try {
                    if (description.isEmpty() || description.equals(" ")) {
                        throw new TextException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task newToDo = new ToDo(description);
                    taskList.add(newToDo);
                    String output = "Got it. I've added this task:\n  " + newToDo + "\n Now you have "
                            + taskList.size() + " tasks in the list.";
                    formatInChatBox(output);
                } catch (TextException e) {
                    formatInChatBox(e.getMessage());
                }
            } else if (task.equals("DEADLINE")) {
                int endIndexOfDescription = description.indexOf("/by ");
                String deadlineDescription = description.substring(0, endIndexOfDescription);
                String deadline = description.substring(endIndexOfDescription + 4);
                Task newDeadline = new Deadline(deadlineDescription, deadline);
                taskList.add(newDeadline);
                String output = "Got it. I've added this task:\n  " + newDeadline + "\n Now you have "
                        + taskList.size() + " tasks in the list.";
                formatInChatBox(output);
            } else if (task.equals("EVENT")) {
                int endIndexOfDescription = description.indexOf("/at ");
                String eventDescription = description.substring(0, endIndexOfDescription);
                String eventTime = description.substring(endIndexOfDescription + 4);
                Task newEvent = new Event(eventDescription, eventTime);
                taskList.add(newEvent);
                String output = "Got it. I've added this task:\n  " + newEvent + "\n Now you have "
                        + taskList.size() + " tasks in the list.";
                formatInChatBox(output);
            }

            try {
                save();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        } catch (TextException e) {
            formatInChatBox(e.getMessage());
        }
    }

    public void error() throws TextException {
        try {
            throw new TextException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (TextException e) {
            formatInChatBox(e.getMessage());
        }
    }

    public void delete(int index) {
        int numOfBooksLeft = taskList.size() - 1;
        String output = "Noted. I've removed this task:\n  " + taskList.get(index - 1)
                + "\nNow you have " + numOfBooksLeft + " tasks in the list.";
        formatInChatBox(output);
        taskList.remove(index - 1);

        try {
            save();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private void save() throws IOException {
        Path dirPath = filePath.getParent();
        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        FileWriter fw = new FileWriter(taskPath);

        String textToAdd = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            textToAdd += (i + 1) + ". " + currentTask + "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }

    public static void main(String[] args) {
        Duke mike = new Duke();
        mike.init();
        Scanner sc = new Scanner(System.in);
        Boolean isChatting = true;
        do {
            String input = sc.next();
            try {
                Command c = Command.valueOf(input.toUpperCase());
                switch (c) {
                case BYE:
                    mike.exit();
                    return;
                case LIST:
                    mike.list();
                    break;
                case DONE:
                    int i = sc.nextInt();
                    mike.mark(i);
                    break;
                case DELETE:
                    int index = sc.nextInt();
                    mike.delete(index);
                    break;
                case TODO:
                    String toDoDescription = sc.nextLine();
                    mike.add("TODO", toDoDescription);
                    break;
                case DEADLINE:
                    String deadlineDescription = sc.nextLine();
                    mike.add("DEADLINE", deadlineDescription);
                    break;
                case EVENT:
                    String eventDescription = sc.nextLine();
                    mike.add("EVENT", eventDescription);
                    break;
                default:
                    mike.error();
                }
            } catch (IllegalArgumentException e) {
                mike.error();
            }
        } while (isChatting);
        sc.close();
    }
}