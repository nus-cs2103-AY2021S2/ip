package duke.command;

import duke.CallbackFunction;
import duke.DukeException;
import duke.TaskList;
import duke.ui.Main;
import javafx.util.Pair;

public class ByeCommand extends Command {

    /**
     * Instantiates a new ByeCommand object.
     * @param commandSplit user command split by spaces.
     */
    public ByeCommand(String[] commandSplit) {
        super(commandSplit);
        assert commandSplit[0].equals("bye") : "Bye command should have \"bye\" keyword.";
    }

    /**
     * Rewrites all Tasks in the list to the storage before saying bidding user goodbye.
     * @param list the task list.
     * @throws DukeException if failed to rewrite tasks.
     */
    public Pair<String, CallbackFunction> execute(TaskList list) throws DukeException {
        list.rewriteTasks();
        return new Pair<>("Bye. Hope to see you again soon!", new CallbackFunction(() -> {
            /*
             * Uses a separate thread to stop the program so that the "bye" message will be updated
             * on user's screen.
             */
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Main.exit();
            });
            thread.start();
        }));
    }
}
