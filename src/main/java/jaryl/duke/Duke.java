package jaryl.duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    private DataManager dataManager;
    private ArrayList<Task> tasksList;
    private Output output;

    public Duke() {
        this.output = new Output();
        dataManager = new DataManager(FILE_PATH);

        try {
            tasksList = dataManager.readFromFile();
        } catch (DukeException e) {
            tasksList = new ArrayList<>();
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean exitFlag = false;
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
                    case FIND:
                        output.findAction(tasksList, input);
                        break;
                }
            } catch (IllegalArgumentException e) {
                output.printIllegalArgumentError();
            } catch (DukeException e1) {
                System.out.println(e1);
            }

            if(exitFlag) break;
        }
    }
}