import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    private DataManager dataManager;
    private ArrayList<Task> tasksList;
    private Output output;

    public Duke() {
        this.output = new Output();
        this.tasksList = new ArrayList<>();
        dataManager = new DataManager(FILE_PATH);

        Path path = Paths.get(FILE_PATH);
        if(Files.exists(path)) {
            tasksList = dataManager.readFromFile();
        } else {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exitFlag = false;
        Duke duke = new Duke();
        ArrayList<Task> tasksList = duke.tasksList;
        DataManager dataManager = duke.dataManager;
        Output output = duke.output;
        output.printWelcomeMsg();

        while(sc.hasNextLine()) {
            String input = sc.nextLine();

            try {
                Command cmd = Command.valueOf(input.split(" ")[0].toUpperCase());

                switch (cmd) {
                    case BYE:
                        output.printByeMsg();
                        exitFlag = true;
                        break;
                    case LIST:
                        output.listAction(tasksList);
                        break;
                    case DONE:
                        output.doneAction(tasksList, input, dataManager);
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        output.addAction(tasksList, input, dataManager);
                        break;
                    case DELETE:
                        output.deleteAction(tasksList, input, dataManager);
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(output.addLine() + "\n    ☹ OOPS! I'm sorry, but I don't know what that means :(" + "\n" + output.addLine());
            } catch (DukeException e1) {
                System.out.println(e1);
            }

            if(exitFlag) break;
        }
    }
}