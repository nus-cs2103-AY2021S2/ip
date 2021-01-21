import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static String h_rule = "────────────────────────────────────────────────────────────────────";

    public static int dukeRunner() throws DukeNotFoundException, DukeDescriptionException, DukeTimingException {
        Scanner sc = new Scanner(System.in);

        String raw_in;
        String[] input;
        List<Task> itemList = new ArrayList<>();
        int count = 0;

        while (true) {
            raw_in = sc.nextLine().trim();
            input = raw_in.split(" ");
            System.out.println(h_rule);
            String joined = "";
            String timing = "";
            if (raw_in.equals("bye")) {
                System.out.println("Goodbye! Have a nice day!\n");
                break;
            } else {
                switch (input[0]) {
                    case "list":
                        int index = 1;
                        for (Task s : itemList) {
                            System.out.println(index + ". " + s.toString());
                            index++;
                        }
                        break;
                    case "done":
                        Task task = itemList.get(Integer.parseInt(input[1]) - 1);
                        task.markDone();
                        System.out.println("Alright, I will mark this as done.\n" + input[1] + ". " + task.toString());
                        break;
                    case "todo":
                        for (int i = 1; i < input.length; i++) {
                            joined = joined + " " + input[i];
                        }
                        itemList.add(new Todo(joined));
                        count++;
                        System.out.println("Added " + raw_in + "\nYou now have " + count + " items in your list.");
                        break;
                    case "deadline":
                        int seq = 0;
                        while (!input[seq].equals("/by")) {
                            joined = joined + " " + input[seq];
                            seq++;
                            if (seq == input.length) {
                                throw new DukeDescriptionException(input[0]);
                            }
                        }

                        if (seq + 1 == input.length) {
                            throw new DukeTimingException(input[0]);
                        }

                        for (int i = seq + 1; i < input.length; i++) {
                            timing = timing + " " + input[i];
                        }

                        itemList.add(new Deadline(joined, timing.trim()));
                        count++;
                        System.out.println("Added " + joined + "\nYou now have " + count + " items in your list.");
                        break;
                    case "event":
                        int seq2 = 0;
                        while (!input[seq2].equals("/at")) {
                            joined = joined + " " + input[seq2];
                            seq2++;
                            if (seq2 == input.length) {
                                throw new DukeDescriptionException(input[0]);
                            }
                        }

                        if (seq2 + 1 == input.length) {
                            throw new DukeTimingException(input[0]);
                        }

                        for (int i = seq2 + 1; i < input.length; i++) {
                            timing = timing + " " + input[i];
                        }
                        itemList.add(new Event(joined, timing.trim()));
                        count++;
                        System.out.println("Added " + joined + "\nYou now have " + count + " items in your list.");
                        break;
                    case "delete":
                        count--;
                        itemList.remove(Integer.parseInt(input[1]));
                        System.out.println("I have removed item " + input[1] + ".");
                    default:
                        throw new DukeNotFoundException();
                }
            }
            System.out.println(h_rule);
        }
        sc.close();
        return 0;
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Greetings! I am Duke! How may I assist you?\n" + h_rule);
        int res = -1;

        while (res != 0) {
            try {
                res = dukeRunner();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}
