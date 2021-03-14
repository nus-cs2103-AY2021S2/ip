package ui;

import java.util.Scanner;

public class Ui {
    private static Scanner in = new Scanner(System.in);
    private String response;
    private FurtherAction nextAction;

    /**
     * Prints a string to the standard output.
     *
     * @param str String to print.
     */
    public static void echo(String str) {
        System.out.println("------------------------------");
        System.out.println(str);
        System.out.println("------------------------------");
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        // This is to allow appending onto the response for
        // the same Ui object instance
        if (this.response == null) {
            this.response = response;
        } else {
            this.response += "\n" + response;
        }
    }

    public FurtherAction getNextAction() {
        return nextAction;
    }

    public void setNextAction(FurtherAction nextAction) {
        this.nextAction = nextAction;
    }
}
