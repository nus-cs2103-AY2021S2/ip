package parser;

import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.Todo;
import dukeexception.DukeException;

public class Parser {
    private Task task;
    private int taskNum;
    private final String command;
    private String searchWord;

    /**
     * A parser constructor which creates a parser object with a single command.
     * @param command
     */
    public Parser(String command) {
        this.command = command;
    }

    /**
     * An overloaded parser constructor which creates a parser object with command and searchword.
     * @param command
     * @param searchWord
     */
    public Parser(String command, String searchWord) {
        this.command = command;
        this.searchWord = searchWord;
    }

    /**
     * An overloaded parser constructor which creates a parser object with task number and command.
     * @param taskNum
     * @param command
     */
    public Parser(int taskNum, String command) {
        this.taskNum = taskNum;
        this.command = command;
    }

    /**
     * An overloaded parser constructor which creates a parser object with a task and command.
     * @param task
     * @param command
     */
    public Parser(Task task, String command) {
        this.task = task;
        this.command = command;
    }

    public Task getTask() {
        return this.task;
    }

    public int getTaskNum() {
        return this.taskNum;
    }

    public String getCommand() {
        return this.command;
    }

    public String getSearchWord() {
        return this.searchWord;
    }

    /**
     * A static method which creates a parser object with raw information from user input
     * @param userInput
     * @return A parser object with user input separated into different sections.
     * @throws DukeException
     */
    public static Parser parse(String userInput) throws DukeException {

        String[] input = userInput.split(" ");
        String commandWord = input[0];
        String taskContent = "";
        StringBuilder taskTime = new StringBuilder();
        int taskNumber = 0;
        int count = 0;

        if (input.length != 2) {
            for (int i = 1; i < input.length; i++) {
                if (!(input[i].equals("/by") || input[i].equals("/at"))) {
                    taskContent = taskContent + " " + input[i];
                    count++;
                } else {
                    if (i + 1 == input.length) {
                        if (commandWord.equals("deadline") || commandWord.equals("event")) {
                            throw new DukeException("DeadLine for" + input[0] + "cannot be empty.");
                        } else {
                            throw new DukeException("Event for" + input[0] + "cannot be empty.");
                        }
                    }
                    break;
                }
            }
            for (int j = count + 2; j < input.length; j++) {
                taskTime.append(input[j]);
            }
        } else {
            if (commandWord.equals("delete") || commandWord.equals("done")) {
                try {
                    taskNumber = Integer.parseInt(input[1]);
                } catch (Exception e) {
                    throw new DukeException("Wrong format, please type in an Integer index!");
                }
            } else {
                taskContent = input[1];
            }
        }

        if (commandWord.equals("bye") || commandWord.equals("/help")) {
            return new Parser(commandWord);
        } else {
            if (commandWord.equals("todo")) {
                if (taskContent.equals(" ") || taskContent.equals("")) {
                    throw new DukeException("todo cannot be empty.");
                } else {
                    return new Parser(new Todo(taskContent), "add");
                }
            } else {
                try {
                    switch (commandWord) {
                    case "list":
                        return new Parser("list");
                    case "done":
                        return new Parser(taskNumber, "done");
                    case "delete":
                        return new Parser(taskNumber, "delete");
                    case "deadline":
                        return new Parser(new Deadline(taskContent, taskTime.toString()), "add");
                    case "event":
                        return new Parser(new Event(taskContent, taskTime.toString()), "add");
                    case "find":
                        return new Parser(commandWord, taskContent);
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                } catch (Exception e) {
                    throw new DukeException("too little information, please type /help for assistance!");
                }
            }
        }

    }

}
