package util;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.NoSuchElementException;

import static util.Parser.getArgMap;
import static util.Parser.getCommand;

public interface Ui {
    static String greeting() {
        String logo = " _____  _    _ _____ _   _ \n" +
                "/  ___|| |  | |  ___| | | | \n" +
                "\\ `--. | |  | | |__ | |_| | \n" +
                " `--. \\| |/\\| |  __||  _  | \n" +
                "/\\__/ /\\  /\\  / |___| | | | \n" +
                "\\____/  \\/  \\/\\____/\\_| |_/ \n";

        String greeting = "Hello, I am\n"
                + logo
                + "\nYour Simple Word-Executed Helper!"
                + "\nWhat shall we do today?\n";

        return greeting;
    }

    public static String echo(String input) {
        return input;
    }
}
