package com.weiliang;

import com.weiliang.storage.Storage;
import com.weiliang.task.TaskList;

/**
 * Main logic controller for a simple bot program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    private String name;

    /**
     * Initializes a new instance of the logic controller.
     *
     * @param name The name of the associated AI.
     */
    public Duke(String name) {
        this.name = name;

        this.storage = new Storage("save.txt");
        try {
            this.tasks = storage.loadTasks();
        } catch (DukeException e) {
            this.tasks = new TaskList();
            e.printStackTrace();
        }
        this.parser = new Parser(tasks);
    }

    /**
     * Returns the name the artificial intelligence.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a {@link String} response after processing is done by the artificial intelligence.
     *
     * @param input Input from user.
     * @return Response from simple bot.
     * @throws DukeException If unable to process input correctly.
     */
    public String getResponse(String input) throws DukeException {
        // Clear leading and trailing whitespace
        input = input.strip();

        // Greeting and exit
        if (input.equalsIgnoreCase("hello")) {
            return "Hello! My name is " + name + "!";
        }
        if (input.equalsIgnoreCase("bye")) {
            storage.storeFile(tasks);
            return "Bye. Hope to see you again soon!";
        }

        // Task related
        if (input.equals("list")) {
            return parser.list();
        }
        if (input.startsWith("delete")) {
            if (input.matches("^delete \\d+$")) {
                int taskNo = Integer.parseInt(input.replaceFirst("delete ", "")) - 1;
                return parser.delete(taskNo);
            } else {
                throw new DukeException("Missing delete index!");
            }
        }
        if (input.startsWith("done")) {
            if (input.matches("^done \\d+$")) {
                int taskNo = Integer.parseInt(input.substring(5)) - 1;
                return parser.markDone(taskNo);
            } else {
                throw new DukeException("Missing done index!");
            }
        }
        if (input.startsWith("todo")) {
            if (input.matches("^todo .+$")) {
                input = input.replaceFirst("todo ", "");
                return parser.createToDo(input);
            } else {
                throw new DukeException("The description of a todo cannot be empty!");
            }
        }
        if (input.startsWith("deadline")) {
            if (input.matches("^deadline .+ \\/by .+$")) {
                String[] inputs = input.replaceFirst("deadline ", "").split(" /by ");
                return parser.createDeadline(inputs[0], inputs[1]);
            } else {
                throw new DukeException("The description of a deadline cannot be empty!");
            }
        }
        if (input.startsWith("event")) {
            if (input.matches("^event .+ \\/at .+$")) {
                String[] inputs = input.replaceFirst("event ", "").split(" /at ");
                return parser.createEvent(inputs[0], inputs[1]);
            } else {
                throw new DukeException("The description of an event cannot be empty!");
            }
        }
        if (input.startsWith("find")) {
            if (input.matches("^find .+$")) {
                String searchField = input.substring(5);
                return parser.search(searchField);
            } else {
                throw new DukeException("Missing search information!");
            }
        }
        if (input.equals("undo")) {
            return parser.undo();
        }

        // Default
        throw new DukeException("Don't understand.");
    }

}
