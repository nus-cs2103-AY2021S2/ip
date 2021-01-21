import java.util.Scanner;

public class Duke {
    private static final String rightIndent = "   ";
    private static final String textSpacer = rightIndent + "|" + " ";
    private static final String responseBoxTop = rightIndent + "________________________________________\n";
    private static final String responseBoxBottom = rightIndent + "-------------------------------------\n"
            + rightIndent + "                                     \\|\n";

    public static void main(String[] args) {
        displayIntro();
    }





    private static void displayIntro() {
        String logo = textSpacer + " ____        _        \n"
                + textSpacer + "|  _ \\ _   _| | _____ \n"
                + textSpacer + "| | | | | | | |/ / _ \\\n"
                + textSpacer + "| |_| | |_| |   <  __/\n"
                + textSpacer + "|____/ \\__,_|_|\\_\\___|\n"
                + textSpacer + "\n";
        String response = responseBoxTop
                + textSpacer +"Hello from\n"
                + logo
                + textSpacer + "What can I do for you?\n"
                + responseBoxBottom;
        System.out.println(response);
    }

}
