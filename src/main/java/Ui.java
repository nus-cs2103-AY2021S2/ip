import java.io.IOException;

/**
 * A class which contains list of user interfaces.
 */
public class Ui {
    /**
     * Say bye when the user logouts.
     *
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the
     *                     general class of exceptions produced by failed or interrupted I/O operations.
     */
    public void bye() throws IOException {
        Duke.respond = "Bye. Hope to see you again soon!";
        String msg = "    ____________________________________________________________\n     "
                + "Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________";
        System.out.println(msg);
        Storage.save();
    }

    /**
     * Welcome the user when login.
     */
    public void greet() {
        System.out.println("    ____________________________________________________________\n     "
                + " ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n\n     "
                + "Hello! I'm Duke :P");
        Duke.respond = "Hello there, I am Duke :P+" + "\n" + "Tell me what you want!";
    }
}