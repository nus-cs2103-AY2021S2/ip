package util;

import java.util.HashMap;
import java.util.InputMismatchException;

public interface Parser {
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
