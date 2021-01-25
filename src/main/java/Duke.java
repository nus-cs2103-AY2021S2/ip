import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskLst = new TaskList();
        boolean isExited = false;
        Storage storage = null;

        // Terminate if unable to read data file
        try {
            storage = new Storage();
            storage.fillTaskLst(taskLst);
        } catch (DukeException e) {
            printResponse(e.getMessage());
            System.exit(1);
        }

        printResponse(getWelcomeMsg());
        while (true) {
            try {
                String input = sc.nextLine();
                String[] words = input.trim().split(" ");
                String cmd = words[0];

                // Recombine cmdArgs for further parsing in individual cmd classes
                String[] remain = Arrays.copyOfRange(words, 1, words.length);
                String cmdArgs = String.join(" ", remain);

                String resp;

                switch (cmd) {
                case "bye":
                    ByeCmd byeCmd = new ByeCmd();
                    resp = byeCmd.process(cmdArgs);
                    isExited = true;
                    break;
                case "list":
                    ListCmd listCmd = new ListCmd(taskLst);
                    resp = listCmd.process(cmdArgs);
                    break;
                case "done":
                    DoneCmd doneCmd = new DoneCmd(taskLst);
                    resp = doneCmd.process(cmdArgs);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    AddCmd addCmd = new AddCmd(taskLst, TaskType.valueOf(cmd.toUpperCase()));
                    resp = addCmd.process(cmdArgs);
                    break;
                case "delete":
                    DeleteCmd delCmd = new DeleteCmd(taskLst);
                    resp = delCmd.process(cmdArgs);
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that command means :-(");
                }

                storage.saveTaskLst(taskLst);
                printResponse(resp);
                if (isExited) {
                    break;
                }
            } catch (DukeException e) {
                printResponse(e.getMessage());
            }
        }
    }

    public static String getWelcomeMsg() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return logo +
                "Hello! I am duke\n" +
                "What can I do for you?\n";
    }

    public static void printResponse(String resp) {
        System.out.println("\t____________________________________________________________");
        resp.lines().forEach(line -> System.out.printf("\t%s\n", line));
        System.out.println("\t____________________________________________________________");
    }
}
