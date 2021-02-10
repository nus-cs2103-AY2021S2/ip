package util;

import java.util.*;
import java.util.stream.Collectors;

public interface Parser {
    String COMMAND = "COMMAND_FLAG_IDENTIFIER";

    static HashMap<String, List<String>> parseCommand(String input) {
        HashMap<String, List<String>> commandMap = new HashMap<>();

        // Insert COMMAND flag
        String command = input.split(" ", 2)[0];
        commandMap.put(COMMAND, new ArrayList<>());
        commandMap.get(COMMAND).add(command);

        // Insert other flags
        String[] flagInputs = input.split("/");
        for (String flagInput: flagInputs) {
            String[] args = flagInput.split(" ");

            // Filter out double spaces
            List<String> argList = Arrays.stream(args)
                    .filter(x -> !(x.equals("")))
                    .collect(Collectors.toList());

            // Continue if no flag was specified
            if (argList.isEmpty()) {
                continue;
            }

            // Create flag and its ArrayList
            String flag = argList.get(0);
            commandMap.put(flag, new ArrayList<>());

            // Insert the rest of the args to the flag
            for (int i = 1; i < argList.size(); i++) {
                commandMap.get(flag).add(argList.get(i));
            }
        }

        return commandMap;
    }

    static String getCommand(String input) {
        return input.split(" ", 2)[0];
    }

    static HashMap<String, String> getArgMap(String input) throws InputMismatchException {
        HashMap<String, String> argMap = new HashMap<>();

        if (input.split(" ").length < 2) {
            return argMap;
        }

        String[] argArr = input.split("/");
        for (int i = 0; i < argArr.length; i++) {
            String flag;
            String param;

            if (i == 0) {
                flag = "desc";
            } else {
                flag = argArr[i].split(" ", 2)[0];
            }

            try {
                param = argArr[i].split(" ", 2)[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InputMismatchException("Parameter for \"" + flag + "\" cannot be empty");
            }
            argMap.put(flag, param);
        }
        return argMap;
    }

}
