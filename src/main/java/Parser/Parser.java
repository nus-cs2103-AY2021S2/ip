package Parser;

import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.Todo;
import dukeException.DukeException;

public class Parser {
    private Task task;
    private int taskNum;
    private final String command;
    private String searchWord;

    public Parser(String command) {
        this.command = command;
    }

    public Parser(String command, String searchWord) {
        this.command = command;
        this.searchWord = searchWord;
    }

    public Parser(int taskNum, String command) {
        this.taskNum = taskNum;
        this.command = command;
    }

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
                            if (commandWord.equals("deadline")) {
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
                if (commandWord.equals("delete")) {
                    try {
                        taskNumber = Integer.parseInt(input[1]);
                    } catch (Exception e) {
                    throw new DukeException("Wrong format, please type in an Integer index!");
                    }
                } else {
                    taskContent = input[1];
                }
            }

            if (commandWord.equals("bye")) {
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
