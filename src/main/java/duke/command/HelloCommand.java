package duke.command;


public class HelloCommand extends Command {
    private static String logo = "\t ____        _        \n"
                                + "\t|  _ \\ _   _| | _____ \n"
                                + "\t| | | | | | | |/ / _ \\\n"
                                + "\t| |_| | |_| |   <  __/\n"
                                + "\t|____/ \\__,_|_|\\_\\___|\n";

    public HelloCommand(){
        super("Hello from\n" + logo + "\tHello! I'm Duke\n" + "\tWhat can I do for you?");
        super.dukeReply();
    }

}
