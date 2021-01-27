import java.util.Arrays;
import java.util.HashMap;

import exception.DukeInvalidArgumentsException;

public class Parser {

    public static HashMap<String,String> ParseInput(String input) throws DukeInvalidArgumentsException {
        HashMap<String, String> keyValuePairs = new HashMap<>();

        String[] commands = input.split(" /");

        String[] firstWords = commands[0].trim().split(" ");

        String command = firstWords[0];
        keyValuePairs.put("command", command);
        
        if (firstWords.length > 1){
            String info = String.join(" ", Arrays.copyOfRange(firstWords, 1, firstWords.length));
            keyValuePairs.put("info", info);
        }

        for (int i = 1; i < commands.length; i++){
            String[] words = commands[i].trim().split(" ");

            String key = words[0];
            String value = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
            
            if (keyValuePairs.containsKey(key)) {
                throw new DukeInvalidArgumentsException(command, String.format("Dupicate argument /%s", key));
            }
            keyValuePairs.put(key, value);
        }

        return keyValuePairs;
    }
}
