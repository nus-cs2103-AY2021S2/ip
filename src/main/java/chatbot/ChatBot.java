package chatbot;

import chatbot.tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;

import chatbot.tasks.TodoTask;
import chatbot.tasks.DeadlineTask;
import chatbot.tasks.EventTask;
import java.time.LocalDate;

import chatbot.exceptions.ChatBotException;
import chatbot.exceptions.MissingDescriptionException;
import chatbot.exceptions.UnknownInputException;

public class ChatBot {
    private Storage storage;
    private TaskHandler th;
    private Ui ui;

    public ChatBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        th = new TaskHandler();
        try {
            th.loadTaskList(storage.loadTaskList());
        } catch (ChatBotException e) {
            th.clearTaskList();;
            ui.errorLine(e.getMessage());
        }
    }

    private void run() {
        ui.greeting();

        boolean isExit = false;
        while (!isExit) {
            /**
            try {
                String command = ui.nextCommand();
                ui.linkBreaker();

            } catch (ChatBotException e) {
                ui.errorLine(e.getMessage());
            } finally {
                ui.linkBreaker();
            }
             */
        }
    }
    public static void main(String[] args) {
        new ChatBot("./data/taskData.txt").run();
    }
}
