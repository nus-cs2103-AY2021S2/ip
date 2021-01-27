import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("    ____________________________________________________________\n" +
                "     Duke... booted...\n" +
                "     requesting tasks\n" +
                "    ____________________________________________________________\n");

        //data structure to store tasks
        TaskList taskList = new TaskList(new ArrayList<Task>());

        Ui ui = new Ui();

        Storage storage = new Storage();

        storage.readFromStorage(taskList);

        ui.runUi(taskList, storage);
    }
}
