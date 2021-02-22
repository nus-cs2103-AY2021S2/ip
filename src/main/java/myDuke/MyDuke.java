package myDuke;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents a Personal Assistant Chatbot with that interacts with the user with the characteristics
 * similar to that of a stereotypical Singaporean gangster.
 */
public class MyDuke {

    static Storage storage;
    static TaskList tasks;
    static Ui ui;
    static String DATA_SAVE_FILE_DIR = "saveFile.txt";

    public MyDuke(String filePath) {
        ui = new Ui();
        ui.showGreetingMsg();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
            ui.showFileLoadSuccessMsg();
        } catch (MyDukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//        MyDuke myDuke = new MyDuke(DATA_SAVE_FILE_DIR);
//        ui.askForUserInput();
//
//        String input = ui.getInput(sc);
//        Parser parser = new Parser(input, storage, tasks, ui);
//
//        while (!parser.isGoodbye()) {
//            parser.handleInput();
//            input = ui.getInput(sc);
//            parser = parser.parseNextInput(input);
//        }
//        ui.showByeMsg();
//
//        try {
//            storage.saveToFile(tasks.getTaskList());
//        } catch (IOException e) {
//            String newDir = "../data/saveFile.txt";
//            ui.printErrorMsg("Something went wrong: " + e.getMessage());
//        }
//        sc.close();
//    }

    /**
     * Checks if the user has provided the index number in the input.
     * @param inputArr String array consisting of the String input being split by the first two space " ".
     * @throws NoIndexException If inputArr.length == 1 (i.e. there is no inputArr[1],
     * indicating that the index is missing in the input.
     */
    static void indexChecker(String[] inputArr) throws NoIndexException {
        if (inputArr.length == 1) {
            throw new NoIndexException(
                    "Paikia Bot: you done what task, limpeh need more information ah. "
                            + "input 'done <task number>'. eg, done 3");
        }
    }

    /**
     * Checks if the user has provided the description of the ToDo task in the input.
     * @param inputArr String array consisting of the String input being split by the first two space " ".
     * @throws NoToDoException If inputArr.length == 1 (i.e. there is no inputArr[1],
     * indicating that the description of the ToDo Task is missing in the input.
     */
    static void todoChecker(String[] inputArr) throws NoToDoException {
        if (inputArr.length == 1) {
            throw new NoToDoException(
                    "Paikia Bot: you want to add what todo task, limpeh need more information ah. "
                            + "input 'todo <info>. eg, todo read book");
        }
    }

    /**
     * Checks if the user has provided the description of the Event task and the date in the input.
     * @param inputArr String array consisting of the String input being split by the first two space " ".
     * @throws NoEventException If inputArr.length == 1 (i.e. there is no inputArr[1],
     * indicating that the description of the Event Task is missing in the input.
     * @throws NoDateException If inputArr[1] is missing a "/", which is used before date inputs,
     * indicating that the date information is missing in the input.
     */
    static void eventChecker(String[] inputArr) throws NoEventException, NoDateException {
        if (inputArr.length == 1) {
            throw new NoEventException(
                    "Paikia Bot: you want to add what event task, limpeh need more information ah. "
                            + "input 'event <info> /YYYY-MM-DD. eg, event bookfest /at 2020-08-24");
        } else if (inputArr[1].split("/", 2).length == 1) {
            throw new NoDateException(
                    "Paikia Bot: i dun see any date inputs leh. to add date input, use '/YYYY-MM-DD'. "
                            + "eg, event bookfest /at 2020-08-24");
        }
    }

    /**
     * Checks if the user has provided the description of the Deadline task and the date in the input.
     * @param inputArr String array consisting of the String input being split by the first two space " ".
     * @throws NoDeadlineException If inputArr.length == 1 (i.e. there is no inputArr[1],
     * indicating that the description of the Deadline Task is missing in the input.
     * @throws NoDateException If inputArr[1] is missing a "/", which is used before date inputs,
     * indicating that the date information is missing in the input.
     */
    static void deadlineChecker(String[] inputArr) throws NoDeadlineException, NoDateException {
        if (inputArr.length == 1) {
            throw new NoDeadlineException(
                    "Paikia Bot: you want to add what deadline task, limpeh need more information ah. "
                            + "input 'deadline <info> <date>. eg, deadline return book /by 2020-01-01");
        } else if (inputArr[1].split("/", 2).length == 1) {
            throw new NoDateException(
                    "Paikia Bot: i dun see any date inputs leh. to add date input, use '/YYYY-MM-DD'. "
                            + "eg, deadline return book /by 2020-01-01");
        }
    }
    static void finderChecker(String[] inputArr) throws NoKeywordException {
        if (inputArr.length == 1) {
            throw new NoKeywordException(
                    "Paikia Bot: you want to find what task, limpeh need more information ah. "
                            + "input 'find <keyword>. eg, find party");
        }
    }


    public String getResponse(String input) {
        MyDuke myDuke = new MyDuke(DATA_SAVE_FILE_DIR);
        ui.askForUserInput();

        Parser parser = new Parser(input, storage, tasks, ui);

//        try {
//            storage.saveToFile(tasks.getTaskList());
//        } catch (IOException e) {
//            String newDir = "../data/saveFile.txt";
//            ui.printErrorMsg("Something went wrong: " + e.getMessage());
//        }
        parser.handleInput();
        return parser.handleInputStr();
    }


}



























