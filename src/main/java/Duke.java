import java.io.IOException;
import java.util.Scanner;

public class Duke {
    // todo should duke be instantiated?
    private static TaskList taskList;

    /**
     * Entry point of the duke programme
     * @param args Irrelevant argument
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            taskList = TaskList.setupTaskList();
        } catch (IOException e) {
            Ui.print(new String[]{"Something went wrong in loading the task file and parsing",
                    e.getMessage()});
        }

        Ui.intro();

        // variables to reuse
        String userInput;
        Parser parser = new Parser(taskList);
        boolean hasUserTypedBye = false;

        while (hasUserTypedBye) {
            userInput = sc.nextLine().trim();
            hasUserTypedBye = !parser.parseInputLine(userInput);
            // todo find a new name that follows boolean conventions OR not the return values of parseInputLine
        }

        sc.close();
    }
}
