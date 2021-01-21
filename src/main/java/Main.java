import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Mike mike = new Mike();
        Scanner scanner = new Scanner(System.in);
        String mikeResponse;

        mike.mikeInit();
        while(mike.isRunning) {
            try {
                Command inputCommand = InputHandler.parseInput(scanner);
                mikeResponse = mike.getResponse(inputCommand);
                OutputHandler.printResponse(mikeResponse);
            } catch (MikeInvalidInputException | MikeCommandExecutionException e) {
                OutputHandler.printResponse(e.getMessage());
            }
        }
    }
}
