import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static String h_rule = "────────────────────────────────────────────────────────────────────";

    public static int dukeRunner(String log) throws DukeNotFoundException, DukeDescriptionException, DukeTimingException, FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        String raw_in;
        String[] input;
        List<Task> itemList = new ArrayList<>();
        int count = 0;
        List<String> fileContents = null;

        try {
            fileContents = new ArrayList<>(Files.readAllLines(Paths.get(log)));
        } catch (IOException e) {
            System.out.println(e);
        }
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
                        int itemToBeUpdatedIndex = Integer.parseInt(input[1]) - 1;
                        Task task = itemList.get(itemToBeUpdatedIndex);
                        task.markDone();
                        fileContents.set(itemToBeUpdatedIndex, task.toString());
                        System.out.println("Alright, I will mark this as done.\n" + input[1] + ". " + task.toString());
                        break;
                    case "todo":
                        for (int i = 1; i < input.length; i++) {
                            joined = joined + " " + input[i];
                        }
                        itemList.add(new Todo(joined));
                        fileContents.add(new Todo(joined).toString());
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
                        fileContents.add(new Deadline(joined, timing.trim()).toString());
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
                        fileContents.add(new Event(joined, timing.trim()).toString());
                        count++;
                        System.out.println("Added " + joined + "\nYou now have " + count + " items in your list.");
                        break;
                    case "delete":
                        count--;
                        itemList.remove(Integer.parseInt(input[1]));
                        fileContents.remove(Integer.parseInt(input[1]));
                        System.out.println("I have removed item " + input[1] + ".");
                    default:
                        throw new DukeNotFoundException();
                }
            }
            System.out.println(h_rule);
            PrintWriter writer = new PrintWriter(log);
            for (String line : fileContents) {
                writer.print(line);
            }
            writer.close();
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
        File logFile = new File("./logs");

        try {
            if (logFile.isFile()) {
                Scanner logs = new Scanner("./logs");
                while (logs.hasNextLine()) {
                    System.out.print(logs.nextLine());
                }
                logs.close();
            } else {
                logFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("early" + e);
        }

        while (res != 0) {
            try {
                res = dukeRunner("./logs");
            } catch (DukeException | FileNotFoundException e) {
                System.out.println(e);
            }
        }
    }
}
