package mike;

import java.util.Scanner;

import command.Command;
import exception.MikeCommandExecutionException;
import exception.MikeInvalidInputException;

public class Main {
    /**
     * Entry point of the chatbot
     * @param args
     */
    public static void main(String[] args) {
        Mike mike = new Mike();
        Scanner scanner = new Scanner(System.in);
        String mikeResponse;

        mike.mikeInit();
        while (mike.isRunning()) {
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
