import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String SPACE = "     ";
    private static final String LINE = "\n     <<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>\n";
    private static final ArrayList<Task> storage = new ArrayList<>();
    private static int count = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        greet();
        // hello
        boolean exit_now = false;

        while (!exit_now) {
            String inp = sc.nextLine();
            String[] spl = inp.split(" ", 2);

            switch(spl[0]) {
                case "todo":
                    try {
                        check_spl_length(spl, 2, "todo");
                        process_todo(spl);
                    } catch (InvalidTaskFormatException e) {
                        e.printMessage();
                    }
                    break;

                case "deadline":
                    try {
                        check_spl_length(spl, 2, "deadline");
                        String[] spl2 = spl[1].split("/by", 2);
                        check_spl_length(spl2, 2, "deadline");
                        process_deadline(spl2);
                    } catch (InvalidTaskFormatException e) {
                        e.printMessage();
                    }
                    break;

                case "event":
                    try {
                        check_spl_length(spl, 2, "event");
                        String[] spl3 =spl[1].split("/at", 2);
                        check_spl_length(spl3, 2, "event");
                        process_event(spl3);
                    } catch (InvalidTaskFormatException e) {
                        e.printMessage();
                    }
                    break;

                case "done":
                    try {
                        check_spl_length(spl, 2, "done");
                        is_int(spl[1], "done");
                        process_done(spl);
                    } catch (InvalidNumberException e) {
                        e.printMessage();
                    } catch (InvalidTaskFormatException e) {
                        e.printMessage();
                    }
                    break;

                case "list":
                    try {
                        check_spl_length(spl, 1, "list");
                        process_list();
                    } catch (InvalidTaskFormatException e) {
                        e.printMessage();
                    }
                    break;

                case "delete":
                    try {
                        check_spl_length(spl, 2, "delete");
                        is_int(spl[1], "delete");
                        process_delete(spl);
                    } catch (InvalidTaskFormatException e) {
                        e.printMessage();
                    } catch (InvalidNumberException e) {
                        e.printMessage();
                    }
                    break;

                case "bye":
                    try {
                        check_spl_length(spl, 1, "bye");
                        process_bye();
                        exit_now = true;
                    } catch (InvalidTaskFormatException e) {
                        e.printMessage();
                    }
                    break;

                default:
                    try {
                        wrong_keyword();
                    } catch (InvalidKeywordException e) {
                        e.printMessage();
                    }
            }

        }
    }

    static void greet() {
        System.out.println(LINE + SPACE + "Heyyoo!! I am Luna :D\n" + SPACE + "What can I do for you today?" + LINE);
    }

    static void is_int(String s, String task) throws InvalidNumberException {
        try {
            int x = Integer.parseInt(s);
            if (x > count) {
                throw new InvalidNumberException(LINE + SPACE + "ERROR! D: The number should be smaller than the total number of tasks in the following task: " + task + LINE);
            }
        } catch (NumberFormatException e){
            throw new InvalidNumberException(LINE + SPACE + "ERROR! D: A number should be added for the following task: " + task + LINE);
        }
    }

    static void check_spl_length(String[] spl, int x, String task) throws InvalidTaskFormatException {
        if (spl.length != x)
            throw new InvalidTaskFormatException(LINE + SPACE + "ERROR! D: The format for the following task is wrong: " + task + LINE);
    }

    static void wrong_keyword() throws InvalidKeywordException {
        throw new InvalidKeywordException();
    }
    static void process_todo(String[] spl) throws InvalidTaskFormatException {
        storage.add(new todo(spl[1]));
        count++;
        System.out.println(LINE + SPACE + "Done adding the TODO Task: " + storage.get(count - 1) + LINE);
    }

    static void process_deadline(String[] spl) {
        storage.add(new deadline(spl[0], spl[1]));
        count++;
        System.out.println(LINE + SPACE + "Done adding the DEADLINE Task: " + storage.get(count - 1) + LINE);
    }

    static void process_event(String[] spl) {
        storage.add(new event(spl[0], spl[1]));
        count++;
        System.out.println(LINE + SPACE + "Done adding the EVENT Task: " + storage.get(count - 1) + LINE);
    }

    static void process_done(String[] spl) {
        int number = Integer.parseInt(spl[1]);
        Task current = storage.get(number - 1);
        current.finished();
        //storage.add(number - 1, current);
        System.out.println(LINE + SPACE + "Good job! Another Task completed! I have marked it as done:\n" + SPACE + current + LINE);
    }

    static void process_list() {
        System.out.println(LINE);
        for (int i = 1; i <= count; i++) {
            System.out.println(SPACE + i + ". " + storage.get(i-1));
        }
        System.out.println(LINE);
    }
    static void process_delete(String[] spl) {
        int num = Integer.parseInt(spl[1]);
        System.out.println(LINE + "     Deleted the following task: " + storage.get(num - 1) + LINE);
        storage.remove(num-1);
        count--;
    }
    static void process_bye() {
        System.out.println(LINE + SPACE + "Byee, hope to see you again soon!" + LINE);
    }


}
