import core.IOManager;
import core.InputHandler;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        InputHandler inputHandler = new InputHandler();
        IOManager ioManager = new IOManager();
        ioManager.setListener(inputHandler);
        ioManager.run();
    }
}
