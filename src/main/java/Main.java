import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Mike mike = new Mike();
        Scanner scanner = new Scanner(System.in);
        String mikeResponse;

        mike.mikeInit();
        while(mike.isRunning) {
            try {
                Command inputCommand = Parser.parseInput(scanner);
                mikeResponse = mike.getResponse(inputCommand);
                UI.printResponse(mikeResponse);
            } catch (MikeInvalidInputException | MikeCommandExecutionException e) {
                UI.printResponse(e.getMessage());
            }
        }
    }
}
