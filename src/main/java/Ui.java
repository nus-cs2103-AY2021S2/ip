import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui class to deal with user interaction
 *
 * @version 28 Jan 2021
 * @author Zhang Peng
 */
public class Ui {
    public Ui() {

    }

    /**
     * This is the the method for interaction with user
     * @param arrayList takes in the arrayList
     * @param path specifies the path to the file
     * @return Nothing.
     */
    public void interactWithUser(ArrayList<Task> arrayList, String path) {
        Scanner scanner = new Scanner(System.in);
        new Parser().makingSenseOfUserCommand(arrayList, path, scanner);
    }
}
