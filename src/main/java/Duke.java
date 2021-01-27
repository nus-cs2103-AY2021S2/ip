import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    private final Ui ui;

    public Duke() {
        ui = new Ui();
    }

    public void run() {
        ui.showWelcome();

        final List<Task> store = new ArrayList<>();
        final List<String> lines = loadFile();
        if (lines == null) { // No files exist or file is corrupted
            createDirectoryAndFile();
        } else { // File may contains bad data: for now we ignore them
            store.addAll(lines.stream()
                    .map(str -> parseStringToTask(str))
                    .filter(task -> task != null)
                    .collect(Collectors.toList()));
        }

        final Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            final String input = scan.nextLine().strip();
            final String command = input.split(" ")[0];
            ui.showLine();

            if (input.toLowerCase().equals("bye")) {
                // save file
                final List<String> data = store.stream()
                        .map(task -> encodeTaskToString(task))
                        .filter(str -> str != null).collect(Collectors.toList());
                saveFile(data);

                System.out.println("\tBye. Hope to see you again soon!");
                ui.showLine();
                break;
            }

            if (input.equals("")) { // empty string
                System.out.println("\t...");
            } else if (input.equals("list")) { // list tasks
                listTasks(store);
            } else if (command.equals("done")) { // mark task as done
                markTaskAsDone(store, input);
            } else if (command.equals("delete")) { // mark task as delete
                deleteTask(store, input);
            } else { // add task to list
                addTaskToList(store, command, input);
            }
            ui.showLine();
        }
        scan.close();

    }

    public static void main(final String[] args) {
        new Duke().run();
    }

    private static Task parseStringToTask(String str) {
        final String[] tokens = str.split(" \\| ");
        String[] datetime;
        try {
            switch (tokens[0]) {
            case "T":
                final ToDo todo = new ToDo(tokens[2]);
                if (tokens[1].equals("1")) todo.markAsDone();
                return todo; 
            case "D":
                datetime = tokens[3].split(" ", 2);
                Deadline deadline;
                if (datetime.length == 2) {
                    deadline = Deadline.create(tokens[2], datetime[0].strip(), datetime[1].strip());
                } else {
                    deadline = Deadline.create(tokens[2], datetime[0].strip());
                }
                if (tokens[1].equals("1")) deadline.markAsDone();
                return deadline;
            case "E":
                datetime = tokens[3].split(" ", 2);
                Event event;
                if (datetime.length == 2) {
                    event = Event.create(tokens[2], datetime[0].strip(), datetime[1].strip());
                } else {
                    event = Event.create(tokens[2], datetime[0].strip());
                }
                if (tokens[1].equals("1")) event.markAsDone();
                return event;
            default:
                return null;
            }
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private static String encodeTaskToString(Task task) {
        return task.encode();
    }

    private static void saveFile(List<String> data) {
        Path path = Paths.get(".", "data", "duke.txt");
        if (!Files.exists(path))
            createDirectoryAndFile();
        try {
            Files.write(path, data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Duke cannot save file.");
            e.printStackTrace();
        }
    }

    private static void createDirectoryAndFile() {
        Path dir = Paths.get(".", "data");
        Path path = Paths.get(".", "data", "duke.txt");
        try {
            if (!Files.exists(dir))
                Files.createDirectory(dir);
            Files.createFile(path);
        } catch (IOException e) {
            System.err.println("Parent directory does not exist.");
            e.printStackTrace();
        }
    }

    private static List<String> loadFile() {
        Path path = Paths.get(".", "data", "duke.txt");
        if (!Files.exists(path))
            return null;
        try {
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Duke cannot read the file.");
            e.printStackTrace();
            return null;
        }
    }

    private static void deleteTask(List<Task> store, String input) {
        final String[] splitOnSpace = input.split(" ", 2);

        if (splitOnSpace.length == 2 && !splitOnSpace[1].strip().equals("")) {
            try {
                final int index = Integer.parseInt(splitOnSpace[1].strip()) - 1;
                if (0 <= index && index < store.size()) {
                    final Task removed = store.remove(index);
                    System.out.println("\tNoted. I've removed this task: ");
                    System.out.printf("\t%s\n", removed);
                    System.out.printf("\tNow you have %d task%s in the list.\n", store.size(),
                            store.size() == 1 ? "" : "s");
                } else {
                    System.out.println("\tOops! The index is out of bound.");
                }
            } catch (final NumberFormatException e) {
                System.out.println("\tOops! Please input a number.");
            }
        } else {
            System.out.println("\tPlease follow this format \"done <index>\".");
        }
    }

    private static void addTaskToList(List<Task> store, String command, String input) {
        boolean isInsert = false;
        if (store.size() >= 100) {
            System.out.println("\tSorry. The database is full!");
        } else if (command.equals("todo")) {
            final String[] splitOnSpace = input.split(" ", 2);

            if (splitOnSpace.length < 2 || splitOnSpace[1].strip().equals("")) {
                System.out.println("\tPlease follow this format \"todo <task>\".");
            } else {
                final String task = splitOnSpace[1].strip();
                store.add(new ToDo(task));
                isInsert = true;
            }
        } else if (command.equals("deadline")) {
            final int index = input.indexOf(" /by ");
            final String[] splitOnBy = input.split(" /by ", 2);

            if (index == -1 || splitOnBy.length < 2 || splitOnBy[1].strip().equals("")) {
                System.out.println("\tPlease follow this format \"deadline <todo> /by <datetime>\".");
            } else {
                final String task = splitOnBy[0].split("deadline ", 2)[1].strip();
                final String[] datetime = splitOnBy[1].strip().split(" ", 2);

                Deadline deadline = null;
                if (datetime.length == 1) {
                    deadline = Deadline.create(task, datetime[0].strip());
                } else if (datetime.length == 2) {
                    deadline = Deadline.create(task, datetime[0].strip(), datetime[1].strip());
                }

                if (deadline != null) {
                    store.add(deadline);
                    isInsert = true;
                } else {
                    System.out.println("\tPlease follow this format \"YYYY-MM-DD [hh:mm[:ss]]\" for datetime.");
                }
            }
        } else if (command.equals("event")) {
            final int index = input.indexOf(" /at ");
            final String[] splitOnAt = input.split(" /at ", 2);

            if (index == -1 || splitOnAt.length < 2 || splitOnAt[1].strip().equals("")) {
                System.out.println("\tPlease follow this format \"event <todo> /at <datetime>\".");
            } else {
                final String task = splitOnAt[0].split("event ", 2)[1].strip();
                final String[] datetime = splitOnAt[1].strip().split(" ", 2);

                Event event = null;
                if (datetime.length == 1) {
                    event = Event.create(task, datetime[0].strip());
                } else if (datetime.length == 2) {
                    event = Event.create(task, datetime[0].strip(), datetime[1].strip());
                }

                if (event != null) {
                    store.add(event);
                    isInsert = true;
                } else {
                    System.out.println("\tPlease follow this format \"YYYY-MM-DD [hh:mm[:ss]]\" for datetime.");
                }
            }
        } else {
            System.out.println("\tOops! Try inputting \"todo|deadline|event <task> (</by|/at> <datetime>)\".");
        }

        if (isInsert) {
            System.out.println("\tGot it. I've added this task: ");
            System.out.printf("\tTask added: %s\n", store.get(store.size() - 1));
            System.out.printf("\tNow you have %d task%s in the list.\n", store.size(), store.size() == 1 ? "" : "s");
        }
    }

    private static void markTaskAsDone(List<Task> store, String input) {
        final String[] splitOnSpace = input.split(" ", 2);

        if (splitOnSpace.length == 2 && !splitOnSpace[1].strip().equals("")) {
            try {
                final int index = Integer.parseInt(splitOnSpace[1].strip()) - 1;
                if (0 <= index && index < store.size()) {
                    store.get(index).markAsDone();
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.printf("\t%s\n", store.get(index));
                } else {
                    System.out.println("\tOops! The index is out of bound.");
                }
            } catch (final NumberFormatException e) {
                System.out.println("\tOops! Please input a number.");
            }
        } else {
            System.out.println("\tPlease follow this format \"done <index>\".");
        }
    }

    private static void listTasks(List<Task> store) {
        if (store.size() == 0) {
            System.out.println("\tHmm... You do not have any tasks!");
        } else {
            System.out.println("\tHere are the tasks in your list:");
        }
        int i = 0;
        for (final Task t : store) {
            System.out.printf("\t%d. %s\n", ++i, t);
        }
    }
}
