import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;

    public Duke() {
        this.storage = new Storage();
        try {
            this.taskList = new TaskList(storage.loadFile());
            System.out.println("\t\tLoading existing duke.txt file");
        }
        catch (DukeException e) {
            System.out.println(e.getMessage());
            this.taskList = new TaskList();
            System.out.println("\t\tA new duke.txt file will be created when you exit");
        }
    }


    public void run() {
        System.out.println(this);
        Execute exec = new Execute(taskList);
        Scanner sc = new Scanner(System.in);
        String command;

        while (sc.hasNext()) {
            command = sc.nextLine();
            try {
                exec.executeCommand(command);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            if (!exec.isAlive) {
                try {
                    this.storage.saveFile(this.taskList.list);
                    sc.close();
                    break;
                }
                catch (DukeException e) {
                    System.out.println(e.getMessage());
                    break;
                }

            }

        }
    }

    @Override
    public String toString() {
        return "\t\tHello! I'm Duke" +
                "\n" +
                "\t\tWhat can I do for you?\n";
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
