import duke.Duke;

/**
 * Main class is the main class. It implements a simple task manager that has a variety of
 * functions such as listing, adding Todos, Events, Deadlines, deletion and completion.
 * @author : IanCKW
 */
public class Main
{
    /**
    *Main Method
    **/
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.start();
    }
}
