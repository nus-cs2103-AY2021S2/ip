package duke.ui;

import duke.exceptions.DukeException;
import duke.exceptions.DukeExceptionIllegalArgument;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ui {

    private BufferedReader in;

    public Ui() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getUserInput() throws DukeExceptionIllegalArgument {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new DukeExceptionIllegalArgument("Failed to read input.");
        }
    }

    public void showError(DukeException e) {
        String message = String.valueOf(e).strip();
        if (message.contains("\n")) {
            int index = message.indexOf('\n');
            String firstLine = message.substring(0, index);
            String[] restLines = message.substring(index+1).split("\n");
            List<String> lines = Arrays.stream(restLines).map(s -> "  "+s).collect(Collectors.toList());
            showMessage("☹ OOPS!!! " + firstLine, lines);
        } else {
            showMessage("☹ OOPS!!! " + message);
        }
    }

    public void showWelcomeScreen() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        showMessage("Hello! I'm a customized Duke", "What can I do for you?");
    }

    public void showLoadingSuccess() {
        System.out.println("Existing list found and imported successfully!");
    }

    public void showLoadingError() {
        System.out.println("Unable to find file, creating new task list.");
    }

    public void showMessage(String pre, List<String> lines, String post) {
        String border = "    ____________________________________________________________";
        String indent = "     ";

        System.out.println(border);
        if (!pre.isEmpty()) {
            System.out.print(indent);
            System.out.println(pre);
        }
        for (String line: lines) {
            System.out.print(indent);
            System.out.println(line);
        }
        if (!post.isEmpty()) {
            System.out.print(indent);
            System.out.println(post);
        }
        System.out.println(border);
        System.out.println();
    }
    public void showMessage(String pre, List<String> lines) {
        showMessage(pre, lines, "");
    }
    public void showMessage(List<String> lines, String post) {
        lines.add(post);
        showMessage("", lines, post);
    }
    public void showMessage(List<String> lines) {
        showMessage("", lines, "");
    }
    public void showMessage(String ... lines) {
        showMessage("", Arrays.asList(lines), "");
    }
}
