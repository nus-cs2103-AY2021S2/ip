package duke;

public class Ui {

    public Ui() {

    }

    /**
     * Greets the user.
     */
    public String greet() {
        return ("Nyahello! I'm Duwuke, your neighbourhood disgusting weeb bot!\n"
                + "Visit https://jellybeano.github.io/ip/ for friendly user guide!");
    }

    public String showLoadingError() {
        return ("Something went wrong with the storage loading uwu");
    }

    public String emptyListMessage() {
        return ("OOPS!!! Your list is currently empty uwu.");
    }

    public String showTaskListSize(int listSize) {
        return ("Now you have " + listSize + " task(s) in the list uwu");
    }

    /**
     * Prints an error message as required.
     *
     * @param s The error message to be printed.
     */
    public String errorMessage(String s) {
        switch (s) {
        case "unknownInput":
            return ("GRR!!! Could you enter a proper input, kind sir? uwu.");
        case "invalidDelete":
            return ("GRR!!! Please indicate a valid task to delete uwu");
        case "invalidEvent":
            return ("GRR!!! Please define your event properly uwu.");
        case "dateTimeError":
            return ("GRR!!! Please define your todo date/time in the "
                    + "YYYY-MM-DD HH:MM format uwu.");
        case "invalidDeadline":
            return ("GRR!!! Please define your deadline properly uwu.");
        case "invalidTodo":
            return ("GRR!!! Please define your todo properly uwu.");
        case "invalidDone":
            return ("GRR!!! Please indicate a valid task to complete uwu.");
        case "alreadyDone":
            return ("GRR!!! That task is already done uwu.");
        case "invalidIndex":
            return ("GRR!!! Please indicate a valid index uwu.");
        case "invalidFind":
            return ("GRR!!! Please follow the find format properly uwu.");
        case "badTag":
            return ("GRR!!! Please provide a tag uwu.");
        case "badFind":
            return ("GRR!!! Are you finding things in the list or bugs in the code? uwu");
        case "invalidBug":
            return ("GRR!!! Please don't use weird characters like | uwu.");
        default:
            return "GRR!!! Something went really really wrong uwu.";
        }
    }

    /**
     * Returns String for when a task is deleted.
     *
     * @param t
     */
    public String deleteTask(Task t) {
        return ("Noted. I've removed this task uwu:\n" + t);
    }

    /**
     * Returns String for when a task is added.
     *
     * @param t
     */
    public String taskAdded(Task t) {
        return ("Hai. I've added this task:\n" + t);
    }

    public String byeBye() {
        return ("Bye, hope to see you again! uwu");
    }

    public String setDone(Task t) {
        return ("Sugoi! I've marked this task as done uwu:\n" + t);
    }

    public String setTag(Task t) {
        return ("I see! What an interesting tag uwu\n" + t);
    }

    /**
     * Prints the given TaskList in a user-friendly format.
     *
     * @param tl The TaskList to be printed.
     */
    public String showList(TaskList tl) {
        String toReturn = ("Here are the tasks in your list uwu:\n");
        int counter = 1;
        for (Task t : tl.getList()) {
            toReturn += (counter + ". " + t + "\n");
            counter++;
        }
        return toReturn;
    }

    /**
     * Prints a 'no matching tasks' message if none are found.
     * Else prints the taks out.
     *
     * @param tl The tasklist to be printed.
     */
    public String showSearchList(TaskList tl) {
        if (tl.isEmpty()) {
            return ("There are no matching tasks uwu. "
                    + "(just like how whoever wrote this has no friends.)");
        } else {
            String toReturn = ("Here are the matching tasks in your list uwu: \n");
            int counter = 1;
            for (Task t : tl.getList()) {
                toReturn += (counter + ". " + t + "\n");
                counter++;
            }
            return toReturn;
        }
    }

}
