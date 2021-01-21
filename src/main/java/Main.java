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
                System.out.println(mikeResponse);
            } catch (MikeInvalidInputException e) {
                System.out.println("____________________________________________________________\n" +
                        e.getMessage() +
                        "\n____________________________________________________________\n");
            }

        }
    }
}
