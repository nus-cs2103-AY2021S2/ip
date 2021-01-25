import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

/*
Need to address:
- IO exception in process_bye
- making task an abstract class
- upload from hard drive default expression
- declared get_date abstract even though todo doesn't use it
 */

public class Duke {
    private static final String SPACE = "     ";
    private static final String LINE = "\n     <<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>\n";
    private static final ArrayList<Task> storage = new ArrayList<>();
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        greet();
        boolean exit_now = false;
        check_file_folder_specifications();

        while (!exit_now) {
            String inp = sc.nextLine();
            String[] spl = inp.split(" ", 2);
            try {
                switch (spl[0]) {
                    case "todo":
                        check_spl_length(spl, 2, "todo");
                        process_todo(spl);
                        break;
                    case "deadline":
                        check_spl_length(spl, 2, "deadline");
                        String[] spl2 = spl[1].split(" /by ", 2);
                        check_spl_length(spl2, 2, "deadline");
                        process_deadline(spl2);
                        break;
                    case "event":
                        check_spl_length(spl, 2, "event");
                        String[] spl3 =spl[1].split(" /at ", 2);
                        check_spl_length(spl3, 2, "event");
                        process_event(spl3);
                        break;
                    case "done":
                        check_spl_length(spl, 2, "done");
                        is_int(spl[1], "done");
                        process_done(spl);
                        break;
                    case "list":
                        check_spl_length(spl, 1, "list");
                        process_list();
                        break;
                    case "delete":
                        check_spl_length(spl, 2, "delete");
                        is_int(spl[1], "delete");
                        process_delete(spl);
                        break;
                    case "bye":
                        check_spl_length(spl, 1, "bye");
                        process_bye();
                        exit_now = true;
                        break;
                    default:
                        throw new InvalidKeywordException();
                }
            } catch (InvalidTaskFormatException e) {
                e.printMessage();
            } catch (InvalidNumberException e) {
                e.printMessage();
            } catch (InvalidKeywordException e) {
                e.printMessage();
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


    static void check_file_folder_specifications() {
        try {
            File dir = new File("./data");
            dir.mkdir();
            File f = new File("./data/tasks.txt");
            if (!f.createNewFile()) {
                upload_from_hard_drive();
            }

        } catch (IOException e) {
            System.out.println("error in making folder/file");
        }
    }

    static void check_spl_length(String[] spl, int x, String task) throws InvalidTaskFormatException {
        if (spl.length != x)
            throw new InvalidTaskFormatException(LINE + SPACE + "ERROR! D: The format for the following task is wrong: " + task + LINE);
    }

    /*
    static void process_task(String[] spl) throws InvalidTaskFormatException {
        Task new_task;
        switch(spl[0]) {
            case "todo":
                new_task = new todo(spl[1]);
                break;
            case "deadline":
                String[] spl2 = spl[1].split(" ", 2);
                new_task = new deadline(spl2[1], spl2[2]);
                break;
            case "event":
                String[] spl3 = spl[1].split(" ", 2);
                new_task = new event(spl3[1], spl3[2]);
                break;
            default:
                throw new InvalidTaskFormatException("Wrong");
        }
        storage.add(new_task);
        System.out.println(LINE + SPACE + "Done adding the " + spl[0] + "task: " + storage.get(count - 1) + LINE);
    }

     */

    static void process_todo(String[] spl) throws InvalidTaskFormatException {
        storage.add(new todo(spl[1]));
        count++;
        System.out.println(LINE + SPACE + "Done adding the TODO Task: " + storage.get(count - 1) + LINE);
    }

    static void process_deadline(String[] spl) {
        storage.add(new deadline(spl[0], LocalDate.parse(spl[1])));
        count++;
        System.out.println(LINE + SPACE + "Done adding the DEADLINE Task: " + storage.get(count - 1) + LINE);
    }

    static void process_event(String[] spl) {
        storage.add(new event(spl[0], LocalDate.parse(spl[1])));
        count++;
        System.out.println(LINE + SPACE + "Done adding the EVENT Task: " + storage.get(count - 1) + LINE);
    }

    static void process_done(String[] spl) {
        int number = Integer.parseInt(spl[1]);
        Task current = storage.get(number - 1);
        current.finished();
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

    static void process_bye() throws IOException {
        upload_to_hard_drive();
        System.out.println(LINE + SPACE + "Byee, hope to see you again soon!" + LINE);
    }

    static void upload_from_hard_drive() throws FileNotFoundException {
        File f = new File("./data/tasks.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            count++;
            String[] spl = s.nextLine().split("@@@", 4);
            Task to_add;
            switch(spl[0]) {
                case "T":
                    to_add = new todo(spl[2]);
                    break;
                case "D":
                    to_add = new deadline(spl[2], LocalDate.parse(spl[3]));
                    break;
                case "E":
                    to_add = new event(spl[2], LocalDate.parse(spl[3]));
                    break;
                default:
                    to_add = new todo("error");
            }
            if (spl[1].equals("1")) {
                to_add.finished();
            }
            storage.add(to_add);
        }
    }

    static void upload_to_hard_drive() throws IOException {
        FileWriter fw = new FileWriter("./data/tasks.txt");
        String between = "@@@";
        for (Task t : storage) {
            String zero = t.get_initial();
            String one =  t.get_done();
            String two = t.get_description();
            if (zero.equals("T")) {
                fw.write(zero + between + one + between + two + "\n");
            } else {
                LocalDate three = t.get_date();
                fw.write(zero + between + one + between + two + between + three + "\n");
            }
        }
        fw.close();
    }

}
