package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface Formatter {
    static String formatOut(String output) {
        String opening = "\"----------------------------------------\n";
        String closing = "----------------------------------------\"\n";
        String combined = opening + output + "\n" + closing;
        return addIndent(combined);
    }

    static String formatList(String[] strArray) {
        for (int i = 0; i < strArray.length; i++) {
            strArray[i] = (i + 1) + ". " + strArray[i];
        }
        return String.join("\n", strArray);
    }

    static String formatList(List<String> strList) {
        String[] strArr = strList.toArray(new String[]{});
        return formatList(strArr);
    }

    static String addIndent(String string) {
        String[] strings = string.split("\n");
        return Arrays.stream(strings)
                .map(s -> '\t' + s)
                .collect(Collectors.joining("\n"));
    }
}
