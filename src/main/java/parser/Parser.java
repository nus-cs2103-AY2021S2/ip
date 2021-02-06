package parser;

import java.util.Arrays;
import java.util.HashMap;

public class Parser {
    /**
     * Parses a string input from the user and returns a hashmap of command-value pairs.
     *
     * @param input User entered string.
     * @return Hashmap containing command-value pairs
     */
    public static HashMap<String, String> parseCommand(String input) {
        HashMap<String, String> commandList = new HashMap<>();
        String[] tokens = input.split(" /");
        String[] firstHalf = tokens[0].split(" ");


        String command = firstHalf[0];
        commandList.put("command", command);
        if (firstHalf.length > 1) {
            commandList.put("info", String.join(" ",
                    Arrays.copyOfRange(firstHalf, 1, firstHalf.length)).trim());
        }

        // We don't have a second part to the command, break out
        if (tokens.length < 2) {
            return commandList;
        }

        String[] secondHalf = tokens[1].split(" ");
        if (secondHalf.length > 0) {
            String firstWord = secondHalf[0];
            commandList.put(firstWord, String.join(" ",
                    Arrays.copyOfRange(secondHalf, 1, secondHalf.length)).trim());
        }
        return commandList;
    }

}
