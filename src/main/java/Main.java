import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Mike mike = new Mike();
        Scanner scanner = new Scanner(System.in);
        String mikeResponse;

        mike.mikeInit();
        while(mike.isRunning) {
            Command inputCommand = InputHandler.parseInput(scanner);
            mikeResponse = mike.getResponse(inputCommand);
            System.out.println(mikeResponse);
        }
    }
}
