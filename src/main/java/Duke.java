package main.java;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;


import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;
    private static final String taskFilePath = "data/task.txt";

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.load(), this.storage);
        this.parser = new Parser();
        this.ui = new Ui("Olly");
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.speak("Hey! Welcome to the chatbot. What can I do for you today?");

        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            try {
                Command c = this.parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException dukeEx) {
                dukeEx.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/task.txt").run();
    }
}