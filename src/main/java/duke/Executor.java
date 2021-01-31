package duke;

public class Executor {

    public static String greet() {
        return "Hi, I'm Duke and I'm gonna be your assistant. Enjoy!!!";
    }

    public static String exit(TaskList taskList, Storage storage) {
        storage.write(taskList);
        return "Bye! Hope to see you again soon!";
    }

    public static String add(TaskList taskList, String ... args) {
        if (args[0].equals("todo")) {
            taskList.add(new ToDo(args[1]));
        } else if (args[0].equals("deadline")) {
            taskList.add(new Deadline(args[1], args[2]));
        } else if (args[0].equals("event")) {
            taskList.add(new Event(args[1], args[2]));
        }
        return ("Got it. Now I have added this "
                + "task:\n" + "  " + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
    }

    public static String markDone(TaskList taskList, int index) {
        taskList.get(index).markDone();
        return ("Nice! I've marked this task as done:\n"
                + "  " + taskList.get(index).toString() + "\n");
    }

    public static String delete(TaskList taskList, int index) {
        Task task = taskList.delete(index);
        return ("Noted. I've removed this "
                + "task:\n" + "  " + task + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
    }

    public static String find(TaskList taskList, String ... keywords) {
        return taskList.find(keywords).toString();
    }

    public static String list(TaskList taskList) {
        return taskList.toString();
    }

}
