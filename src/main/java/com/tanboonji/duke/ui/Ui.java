package com.tanboonji.duke.ui;

public class Ui {

    private static final String DUKE_LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _ ___\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String REPLY_TOP_OUTLINE = "========================================";
    private static final String REPLY_BOTTOM_OUTLINE = "========================================\n";
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke.\n"
            + "What can I do for you today?";

    public Ui() {
    }

    public void greet() {
        System.out.println(DUKE_LOGO);
        echo(WELCOME_MESSAGE);
    }

    public void print(String output) {
        echo(output);
    }

    private void echo(String output) {
        System.out.println(REPLY_TOP_OUTLINE);
        System.out.println(output);
        System.out.println(REPLY_BOTTOM_OUTLINE);
    }
}
