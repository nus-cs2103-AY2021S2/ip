import java.util.Scanner;

public class Main {
    /**
     * Entry point of this project
     * @param args Irrelevant argument
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();
        duke.runDuke(sc);
    }
}
