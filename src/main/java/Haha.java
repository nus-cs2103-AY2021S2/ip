import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Haha {
    private static final String LINE_BREAK = "____________________________________________________________\n";
    private static final String LOGO = " _    _          _    _\n" +
            "| |  | |   /\\   | |  | |   /\\\n" +
            "| |__| |  /  \\  | |__| |  /  \\\n" +
            "|  __  | / /\\ \\ |  __  | / /\\ \\\n" +
            "| |  | |/ ____ \\| |  | |/ ____ \\\n" +
            "|_|  |_/_/    \\_\\_|  |_/_/    \\_\\\n";
    private static final String STARTER = "Hello from\n" + LOGO
            + LINE_BREAK
            + "Dude, I'm HAHA.\n"
            + "What can I do for you?\n"
            + "(Oh when you are done, say bye)\n"
            + LINE_BREAK;
    private static final String[] LEGITCOMMANDS = new String[]{
            "todo", "deadline", "event", "list", "done", "delete", "bye"
    };
    private enum TaskType {
        TODO("todo"), DEADLINE("deadline"), EVENT("event");
        private final String rep;

        TaskType(String rep) {
            this.rep = rep;
        }

        String getRep() {
            return rep;
        }
    }
    private static int taskNumber(String command) throws HahaTaskNumberNotIntException {
        try {
            return Integer.parseInt("" + command.charAt(command.length() - 1));
        } catch (NumberFormatException ex) {
            throw new HahaTaskNumberNotIntException(command);
        }
    }

    private static void tellAdd() {
        System.out.println("Got it. I've added this task:");
    }

    private static void tellSize(List<Task> database) {
        String task = database.size() > 1 ? " tasks" : " task";
        System.out.println("Now you have " + database.size() + task + " in the list");
    }

    private static void addToDB(List<Task> database, Task task) {
        database.add(task);
        tellAdd();
        System.out.println("  " + database.get(database.size() - 1));
        tellSize(database);
    }

    private static void deleteFromDB(List<Task> database, Task currentTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(currentTask);
        database.remove(currentTask);
        tellSize(database);
    }

    private static void listFromDB(List<Task> database) {
        if (database.size() == 0) {
            System.out.println("You have nothing going on!");
        } else {
            System.out.println("Here are your list of tasks:");
            for (int i = 0; i < database.size(); i++) {
                String idx = Integer.toString(i + 1) + '.';
                String task = idx + database.get(i);
                System.out.println(task);
            }
        }
    }

    private static void handleCommand(String command) throws HahaException {
        // Check input starts with specified command words
        if (Arrays.stream(LEGITCOMMANDS).noneMatch(command::startsWith)) {
            throw new HahaWrongCommandException(command);
        }
        // Check input has description following task command words
        if (Arrays.stream(TaskType.values()).anyMatch(x -> command.startsWith(x.getRep()))) {
            boolean bad = Arrays.stream(TaskType.values())
                    .filter(x -> command.startsWith(x.getRep()))
                    .anyMatch(x -> x.getRep().length() == command.length());
            if (bad) {
                throw new HahaEmptyDescriptionException(command);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println(STARTER);

        List<Task> database = new ArrayList<>();

        while (sc.hasNext()) {
            String command = sc.nextLine();
            System.out.println(LINE_BREAK);

            // Safety checks
            try {
                handleCommand(command);
            } catch (HahaEmptyDescriptionException | HahaWrongCommandException ex) {
                System.out.println(ex);
                System.out.println(LINE_BREAK);
                continue;
            } catch (HahaException ex) {
                System.out.println("Last layer of defense");
                System.out.println("SOMETHING IS WRONG!");
                System.out.println(LINE_BREAK);
                continue;
            }
            if (command.equals("bye")) {
                System.out.println("Bye now!");
                break;
            } else if (command.equals("list")) {
                listFromDB(database);
            } else if (command.startsWith("done")) {
                try {
                    int givenIndex = taskNumber(command) - 1;
                    if (givenIndex < 0 || givenIndex > database.size()) {
                        System.out.println("OOPS! Wrong number!\n Try specify the right task number");
                    } else {
                        Task currentTask = database.get(givenIndex);
                        if (currentTask.getIsDone()) {
                            System.out.println("OOPS! I've marked this task as done ALREADY");
                        } else {
                            System.out.println("Nice! I've marked this task as done:");
                            currentTask.setDone(true);
                            System.out.println(currentTask);
                        }
                    }
                } catch (HahaTaskNumberNotIntException ex) {
                    System.out.println(ex);
                }
            } else if (command.startsWith("delete")) {
                try {
                    Task currentTask = database.get(taskNumber(command) - 1);
                    deleteFromDB(database, currentTask);
                } catch (HahaTaskNumberNotIntException ex) {
                    System.out.println(ex);
                }
            } else if (command.startsWith("todo")) {
                addToDB(database, new Todo(false, command.substring(5)));

            } else if (command.startsWith("deadline")) {
                addToDB(database, new Deadline(false, command.substring(9)));

            } else if (command.startsWith("event")) {
                addToDB(database, new Event(false, command.substring(6)));
            } else {
                System.out.println("Command not recognized!");
            }
            System.out.println(LINE_BREAK);
        }
    }

}
