import java.io.IOException;
import java.util.Scanner;

public class Duke {
    // use collection for holding all tasks
//    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static TaskList taskList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // random testing things
        // should be rearranged elsewhere
        try {
            taskList = TaskList.setupTaskList();
        } catch (IOException e) {
            Ui.print(new String[]{"Something went wrong in loading the task file and parsing",
                    e.getMessage()});
        }

        Ui.intro();
        Parser parser = new Parser(taskList);

        // variables to reuse
        String userInput;
        boolean remainOpen = true;

        while (remainOpen) {
            userInput = sc.nextLine().trim();
            remainOpen = parser.parseInputLine(userInput);
        }

        sc.close();
    }
}
