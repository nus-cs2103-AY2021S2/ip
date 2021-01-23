import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        FileInput fileInput = new FileInput();
        fileInput.loadFile("./data", "./data/duke.txt");
        File file = new File("./data/duke.txt");
        Scanner sc = new Scanner(System.in);
        printStatements();
        List<Task> loadedData = fileInput.initialiseStorage(file);
        UserInput userInput = new UserInput(sc, loadedData);
        userInput.executeInput();


    }

    private static void printStatements() {
        String line = "";
        for (int i = 0; i < 50; i++) {
            line = line.concat("_");
        }
        System.out.println(line);
        System.out.println("Hello! I'm Benny");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

}
