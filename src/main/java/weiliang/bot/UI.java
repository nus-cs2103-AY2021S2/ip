package weiliang.bot;

import weiliang.bot.task.Deadline;
import weiliang.bot.task.Event;
import weiliang.bot.task.Task;
import weiliang.bot.task.TaskList;

/**
 * Main ui class
 */
public class UI {

    private String name;
    private String logo;

    private boolean active;
    private TaskList tasks;

    /**
     * Creates an empty ui
     * 
     * @param name the name for the bot
     */
    public UI(String name) {
        this.name = name;
        this.logo = "  ___ _            _     ___      _   \n" + " / __(_)_ __  _ __| |___| _ ) ___| |_ \n"
                + " \\__ \\ | '  \\| '_ \\ / -_) _ \\/ _ \\  _|\n" + " |___/_|_|_|_| .__/_\\___|___/\\___/\\__|\n"
                + "             |_|                      \n\n";
        this.active = true;
        this.tasks = new TaskList();
    }

    /**
     * Loads the tasks given list
     * 
     * @param tasks tasks to load
     */
    public void loadTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Formats message to given form
     * 
     * @param message user input
     * @return formatted bot message
     */
    public String formatMessage(String message) {
        return name + ": " + message.replace("{{bot:name}}", name);
    }

    /**
     * Gets initialization message
     * 
     * @return init message
     */
    public String getInitMessage() {
        return logo + formatMessage("Hi, my name is {{bot:name}}!");
    }

    /**
     * Check if the bot is alive
     * 
     * @return true if active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Gets a parsed input from bot
     * 
     * @param input user command
     * @return bot formatted string response
     * @throws DukeException if error while parsing
     */
    public String respond(String input) throws DukeException {
        // Clear leading and trailing whitespace
        input = input.strip();

        // Greeting and exit
        if (input.equalsIgnoreCase("hello")) {
            return formatMessage("Hello! My name is {{bot:name}}!");
        }
        if (input.equalsIgnoreCase("bye")) {
            active = false;
            return formatMessage("Bye. Hope to see you again soon!");
        }

        // Task related
        if (input.equals("list")) {
            String message = formatMessage("Printing list!");
            for (int i = 0; i < tasks.size(); i++) {
                message += "\n" + (i + 1) + "." + tasks.get(i);
            }
            return message;
        }
        if (input.matches("^delete \\d+$")) {
            int taskNo = Integer.parseInt(input.replaceFirst("delete ", "")) - 1;

            // Check if in tasks
            if (taskNo > tasks.size() - 1) {
                return formatMessage("Unable to remove item!");
            }

            // Mark complete
            Task task = tasks.remove(taskNo);

            String message = formatMessage("Noted, I have removed this task.");
            message += "\n" + task;
            message += "\n" + "Now you have " + tasks.size() + " tasks in the list.";
            return message;
        }
        if (input.matches("^done \\d+$")) {
            int taskNo = Integer.parseInt(input.substring(5)) - 1;

            // Check if in tasks
            if (taskNo > tasks.size() - 1) {
                return formatMessage("Unable to remove item!");
            }

            // Mark complete
            Task task = tasks.get(taskNo);
            task.complete();

            String message = formatMessage("Nice, I've marked the task as done!");
            message += "\n" + task;
            message += "\n" + "Now you have " + tasks.size() + " tasks in the list.";
            return message;
        }
        if (input.startsWith("todo")) {
            if (input.matches("^todo .+$")) {
                Task task = new Task(input.replaceFirst("todo ", ""));
                tasks.add(task);
                
                String message = formatMessage("Got it! I've added this task.");
                message += "\n" + task;
                message += "\n" + "Now you have " + tasks.size() + " tasks in the list.";
                return message;
            } else {
                throw new DukeException("The description of a todo cannot be empty!");
            }
        }
        if (input.startsWith("deadline")) {
            if (input.matches("^deadline .+ \\/by .+$")) {
                String[] inputs = input.replaceFirst("deadline ", "").split(" /by ");
                Task task = new Deadline(inputs[0], inputs[1]);
                tasks.add(task);
                
                String message = formatMessage("Got it! I've added this task.");
                message += "\n" + task;
                message += "\n" + "Now you have " + tasks.size() + " tasks in the list.";
                return message;
            } else {
                throw new DukeException("The description of a deadline cannot be empty!");
            }
        }
        if (input.startsWith("event")) {
            if (input.matches("^event .+ \\/at .+$")) {
                String[] inputs = input.replaceFirst("event ", "").split(" /at ");
                Task task = new Event(inputs[0], inputs[1]);
                tasks.add(task);
                
                String message = formatMessage("Got it! I've added this task.");
                message += "\n" + task;
                message += "\n" + "Now you have " + tasks.size() + " tasks in the list.";
                return message;
            } else {
                throw new DukeException("The description of an event cannot be empty!");
            }
        }

        // Default
        // Don't actually need to throw bot exception
        throw new DukeException("Don't understand.");
    }

}