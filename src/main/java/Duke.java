import core.IOManager;
import core.InputHandler;
import core.task.TaskManager;

public class Duke {
    public static void main(String... args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        TaskManager tm = new TaskManager();
        InputHandler inputHandler = new InputHandler(tm);
        IOManager ioManager = new IOManager();
        ioManager.setListener(inputHandler);
        ioManager.run();
        tm.save();
    }
}
