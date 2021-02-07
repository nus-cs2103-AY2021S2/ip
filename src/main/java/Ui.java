import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A class which contains list of user interfaces.
 */
public class Ui {
    /**
     * Finds task and print out the details of the tasks that is related to the keyword provided.
     *
     * @param keyword Parts of the description of the task that a user wants to find.
     */
    public void find(String keyword) {
        String print = "";
        int num = 1;
        for (int i = 0; i < TaskList.tasks.size(); i++) {
            Task task = TaskList.tasks.get(i);
            String taskDescription = task.description;
            String word = "";
            for (int j = 0; j < taskDescription.length(); j++) {
                if (taskDescription.charAt(j) == ' ') {
                    if (word.equals(keyword)) {
                        print += num + "." + task + "\n";
                        num++;
                    }
                    word = "";
                } else {
                    word += taskDescription.charAt(j);
                    if (j == taskDescription.length() - 1) {
                        if (word.equals(keyword)) {
                            print += num + "." + task + "\n";
                            num++;
                        }
                    }
                }
            }
        }
        print = "Task(s) related to the keyword :\n" + print;
        System.out.println(print);
        Duke.respond = print;
    }

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