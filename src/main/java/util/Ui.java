package util;

public interface Ui {
    static String greeting() {
        String logo = " _____  _    _ _____ _   _ \n" +
                "/  ___|| |  | |  ___| | | | \n" +
                "\\ `--. | |  | | |__ | |_| | \n" +
                " `--. \\| |/\\| |  __||  _  | \n" +
                "/\\__/ /\\  /\\  / |___| | | | \n" +
                "\\____/  \\/  \\/\\____/\\_| |_/ \n";

        return "Hello, I am\n"
                + logo
                + "\nYour Simple Word-Executed Helper!"
                + "\nWhat shall we do today?\n";
    }
}
