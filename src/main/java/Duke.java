import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private final Ui ui;
    private final TaskList taskLst;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        taskLst = new TaskList();

        // Terminate if unable to initialise storage
        try {
            storage = new Storage();
            storage.fillTaskLst(taskLst);
        } catch (IOException e) {
            ui.print(String.format("Unable to initialise Storage: %s", e));
            System.exit(1);
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isExited = false;

        ui.printWelcomeMsg();

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
                ui.print(resp);
                if (isExited) {
                    break;
                }
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
