import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import java.io.BufferedWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Duke {
    private final StringBuffer boundOfChatBox = new StringBuffer();
    private final ArrayList<Task> tasks = new ArrayList<>();
    public enum command {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT
    }

    public void setBoundOfChatBox() {
        boundOfChatBox.append('\n');
        int lenOfChatBox = 50;
        boundOfChatBox.append("-".repeat(lenOfChatBox));
        boundOfChatBox.append('\n');
    }

    public void formatInChatBox(String s) {
        System.out.println(boundOfChatBox + s + boundOfChatBox);
    }

    public void init() {
        setBoundOfChatBox();
        String logo = "    __      __      ____ \n" +
                      "   /  \\    /  \\    / __ \\\n" +
                      "  / /\\ \\  / /\\ \\  | |  | |\n" +
                      " / /  \\ \\/ /  \\ \\ | |__| |\n" +
                      "/_/    \\__/    \\_\\ \\____/";
        String greeting = "Hello! I'm Momo\nWhat can I do for you?";
        formatInChatBox(logo);
        formatInChatBox(greeting + '\n');
    }

    public void add(Task task) {
        tasks.add(task);
        formatInChatBox("Got it. I've added this task:" + '\n'
                        + task + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.\n");
    }

    public void addToDo(String des) throws TextException {
        try {
            if (des.isEmpty() || des.equals(" "))
                throw new TextException("OOPS!!! The description of a todo cannot be empty.\n");
            ToDo todo = new ToDo(des);
            add(todo);
        } catch (TextException e) {
            formatInChatBox(e.getMsgDes());
        }
    }

    public void addDeadline(String des) throws TextException, DateTimeParseException {
        try {
            if (des.isEmpty() || des.equals(" "))
                throw new TextException("OOPS!!! The description of a deadline cannot be empty.\n");
            if (des.contains("/by ")) {
                int endOfDescription = des.indexOf("/by ");
                String description = des.substring(0, endOfDescription);
                String deadline = des.substring(endOfDescription + 4);
                LocalDate date = LocalDate.parse(deadline);
                Deadline ddl = new Deadline(description, date);
                add(ddl);
            } else {
                throw new TextException("OOPS!!! Please enter '/by YYYY-MM-DD' after description.\n");
            }
        } catch (TextException textException) {
            formatInChatBox(textException.getMsgDes());
        } catch (DateTimeParseException dateException) {
            formatInChatBox("OOPS!!! Please use '/by YYYY-MM-DD' after description.\n");
        }
    }

    public void addEvent(String des) throws TextException, DateTimeParseException {
        try {
            if (des.isEmpty() || des.equals(" "))
                throw new TextException("OOPS!!! The description of a event cannot be empty.\n");
            if (des.contains("/at ")) {
                int endOfDescription = des.indexOf("/at ");
                String description = des.substring(0, endOfDescription);
                String time = des.substring(endOfDescription + 4);
                LocalDate date = LocalDate.parse(time);
                Event event = new Event(description, date);
                add(event);
            } else {
                throw new TextException("OOPS!!! Please enter '/at YYYY-MM-DD' after description\n");
            }
        } catch (TextException e) {
            formatInChatBox(e.getMsgDes());
        } catch (DateTimeParseException dateException) {
            formatInChatBox("OOPS!!! Please use '/at YYYY-MM-DD' after description.\n");
        }
    }

    public void error() throws TextException {
        try {
            throw new TextException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        } catch (TextException e) {
            formatInChatBox(e.getMsgDes());
        }
    }

    public void list() {
        int n = tasks.size();
        if (n == 0) {
            formatInChatBox("There is no task yet\n");
            return ;
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < n; i++)
            buf.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        String res = new String(buf);
        formatInChatBox("Here are the tasks in your list:\n" + res);
    }

    public void mark(int index) {
        Task taskToBeMarked = tasks.get(index - 1);
        taskToBeMarked.markedAsDone();
        formatInChatBox("Nice! I've marked this task as done:\n" + taskToBeMarked + "\n");
    }

    public void delete(int index) {
        Task taskToBeDeleted = tasks.remove(index - 1);
        formatInChatBox("Got it. I've removed this task:" + '\n'
                + taskToBeDeleted + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n");
    }

    public void save() {
        try {
            Path directoryPath = Paths.get( "data");
            Path filePath = Paths.get( "data", "duke.txt");
            boolean directoryExists = Files.exists(directoryPath);
            boolean fileExists = Files.exists(filePath);
            if (!directoryExists) {
                Files.createDirectory(directoryPath);
            }
            if (!fileExists) {
                Files.createFile(filePath);
            }
            BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath);
            for (Task task : tasks) {
                bufferedWriter.write(task.toString() + "\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void exit() {
        String goodbye = "Bye. Hope to see you again soon!\n";
        formatInChatBox(goodbye);
    }

    public static void main(String[] args) throws TextException {
        Duke momo = new Duke();
        momo.init();
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            input = sc.next();
            try {
                command c = command.valueOf(input.toUpperCase(Locale.ROOT));
                switch (c) {
                    case BYE:
                        momo.exit();
                        return;
                    case LIST:
                        momo.list();
                        break;
                    case DONE:
                        int i = sc.nextInt();
                        momo.mark(i);
                        break;
                    case DELETE:
                        int j = sc.nextInt();
                        momo.delete(j);
                        momo.save();
                        break;
                    case TODO:
                        String toDoDes = sc.nextLine();
                        momo.addToDo(toDoDes);
                        momo.save();
                        break;
                    case DEADLINE:
                        String deadlineDes = sc.nextLine();
                        momo.addDeadline(deadlineDes);
                        momo.save();
                        break;
                    case EVENT:
                        String eventDes = sc.nextLine();
                        momo.addEvent(eventDes);
                        momo.save();
                        break;
                    default:
                        momo.error();
                }
            } catch (IllegalArgumentException e) {
                momo.error();
            }
        } while (true);
    }
}
