package Parser;

import Duke.*;
import DukeException.DukeException;

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
        try {
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
                taskNumber = Integer.parseInt(input[1]);
            }
            if (commandWord.equals("bye")) {
                return new Parser(commandWord);
            } else {
                return switch (commandWord) {
                    case "list" -> new Parser("list");
                    case "done" -> new Parser(taskNumber, "done");
                    case "delete" -> new Parser(taskNumber, "delete");
                    case "todo" -> new Parser(new Todo(taskContent), "add");
                    case "deadline" -> new Parser(new Deadline(taskContent, taskTime.toString()), "add");
                    case "event" -> new Parser(new Event(taskContent, taskTime.toString()), "add");
                    case "find" -> new Parser(commandWord, "set");
                    default -> throw new DukeException("I'm sorry, but I don't know what that means :-(");
                };
            }
        } catch (Exception e) {
            throw new DukeException("Don't be lazy. Tell me more details.");
        }
    }

}
