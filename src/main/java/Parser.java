import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class parser makes sense of the user commands
 * @author Zhang Peng
 * @version 28 Jan 2021
 */
public class Parser {
    public Parser() {
    }

    /**
     * This is the the method for making sense of the user commands
     * @param arrayList takes in the arrayList
     * @param path specifies the path to the file
     * @param input takes in the a scanner to scan
     * @return Nothing.
     */
    public String makingSenseOfUserCommand(ArrayList<Task> arrayList, String path, String input) {
        String holder;
        assert path != null;
        assert input != null;
        if (input.contains("bye")) {
            holder = "Bye. Hope to see you again soon!" + "\n";
        } else if (!(input.contains("todo") || input.contains("event") || input.contains("deadline")
                        || input.contains("list") || input.contains("done") || input.contains("delete")
                        || input.contains("find") || input.contains("reschedule"))) {
            holder = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n";
        } else if (input.equals("todo") || input.equals("event") || input.equals("deadline")
                        || input.equals("done") || input.equals("delete") || input.equals("find")
                || input.equals("reschedule")) {
            holder = "☹ OOPS!!! The description of a todo cannot be empty." + "\n";
        } else if (input.equals("list")) {
            holder = containsList(arrayList);
        } else if (input.contains("done ")) {
            holder = containsDone(arrayList, input);
        } else if (input.contains("todo ")) {
            holder = containsTodo(arrayList, input);
        } else if (input.contains("deadline")) {
            holder = containsDeadline(arrayList, input);
        } else if (input.contains("event ")) {
            holder = containsEvent(arrayList, input);
        } else if (input.contains("delete ")) {
            holder = containsDelete(arrayList, input);
        } else if (input.contains("find ")) {
            holder = containsFind(arrayList, input);
        } else if (input.contains("reschedule ")) {
            holder = containsReschedule(arrayList, input);
        } else {
            holder = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n";
        }
        int counterTwo = 1;
        assert arrayList.size() != 0;
        for (Task s : arrayList) {
            s.index = counterTwo;
            counterTwo++;
        }
        new Storage().savingFile(arrayList, path);
        return holder + "\n";
    }

    private String containsReschedule(ArrayList<Task> arrayList, String input) {
        String holder;
        // e.g. reschedule 2 /to 02/12/2019 2-4pm
        int index = Integer.parseInt(input.substring(11, 12));

        holder = "Got it. I've rescheduled and updated this event: " + "\n";
        String[] parts = input.split("/", 2);
        String part2 = parts[1];

        if (part2.contains("/")) {
            String dateString = part2.substring(3, 13);
            String timeString = part2.substring(13);
            String temp = dateString.substring(6) + "-" + dateString.substring(3, 5) + "-"
                    + dateString.substring(0, 2);
            LocalDate xx = LocalDate.parse(temp);
            String f = xx.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + timeString;

            Task task = new Event(arrayList.get(index - 1).description, f);
            holder = holder + task + "\n";
            arrayList.set(index - 1, task);
        } else {
            Task task = new Event(arrayList.get(index - 1).description, part2.substring(3));
            holder = holder + task + "\n";
            arrayList.set(index - 1, task);
        }
        return holder;
    }

    private String containsFind(ArrayList<Task> arrayList, String input) {
        String holder;
        holder = "Here are the matching tasks in your list: \n";
        ArrayList<Task> tt = new ArrayList<>();
        int counterOne = 1;
        for (Task ttt : arrayList) {
            counterOne = populateTt(input, tt, counterOne, ttt);
        }
        for (Task m : tt) {
            holder = holder.concat(m.index + ". " + m);
            holder = holder.concat("\n");
        }
        return holder;
    }

    private int populateTt(String input, ArrayList<Task> tt, int counterOne, Task ttt) {
        if (ttt.description.contains(input.substring(5))) {
            ttt.index = counterOne;
            tt.add(ttt);
            counterOne++;
        }
        return counterOne;
    }

    private String containsDelete(ArrayList<Task> arrayList, String input) {
        String holder;
        int deletedNumber = Integer.parseInt(input.substring(7));
        if (deletedNumber > arrayList.size()) {
            holder = "☹ OOPS!!! Sorry item no found :-(";
        } else {
            holder = "Noted. I've removed this task: " + "\n";
            new TaskList().deleteFromList(arrayList, deletedNumber);
            holder = holder.concat("Now you have " + arrayList.size() + " task(s) in the list");
        }
        return holder;
    }

    private String containsEvent(ArrayList<Task> arrayList, String input) {
        String holder;
        holder = "Got it. I've added this task: " + "\n";
        String[] parts = input.split("/", 2);
        String part1 = parts[0];
        String part2 = parts[1];

        // e.g. event project meeting /at 02/12/2019 2-4pm
        if (part2.contains("/")) {
            String dateString = part2.substring(3, 13);
            String timeString = part2.substring(13);
            String temp = dateString.substring(6) + "-" + dateString.substring(3, 5) + "-"
                    + dateString.substring(0, 2);
            LocalDate xx = LocalDate.parse(temp);
            String f = xx.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + timeString;
            Task task = new Event(part1.substring(6), f);
            holder = holder + task + "\n";
            new TaskList().addToList(arrayList, task);
        } else {
            Task task = new Event(part1.substring(6), part2.substring(3));
            holder = holder + task + "\n";
            new TaskList().addToList(arrayList, task);
        }
        holder = holder.concat("Now you have " + arrayList.size() + " task(s) in the list");
        return holder;
    }

    private String containsDeadline(ArrayList<Task> arrayList, String input) {
        String holder;
        holder = "Got it. I've added this task: " + "\n";
        String[] parts = input.split("/", 2);
        String part1 = parts[0];
        String part2 = parts[1];
        //e.g. deadline return book /by 02/12/2019
        if (part2.contains("/")) {
            String dateString = part2.substring(3);
            String temp = dateString.substring(6) + "-" + dateString.substring(3, 5) + "-"
                    + dateString.substring(0, 2);
            LocalDate xx = LocalDate.parse(temp);
            String f = xx.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            Task task = new Deadline(part1.substring(9), f);
            holder = holder + task + "\n";
            new TaskList().addToList(arrayList, task);
        } else {
            Task task = new Deadline(part1.substring(9), part2.substring(3));
            holder = holder + task + "\n";
            new TaskList().addToList(arrayList, task);
        }
        holder = holder.concat("Now you have " + arrayList.size() + " task(s) in the list");
        return holder;
    }

    private String containsTodo(ArrayList<Task> arrayList, String input) {
        String holder;
        holder = "Got it. I've added this task: " + "\n";
        Task task = new Todo(input.substring(5));
        holder = holder + task + "\n";
        new TaskList().addToList(arrayList, task);
        holder = holder.concat("Now you have " + arrayList.size() + " task(s) in the list");
        return holder;
    }

    private String containsList(ArrayList<Task> arrayList) {
        String holder;
        holder = "Here are the tasks in your list:" + "\n";
        for (Task m : arrayList) {
            holder = holder.concat(m.index + ". " + m);
            holder = holder.concat("\n");
        }
        return holder;
    }

    private String containsDone(ArrayList<Task> arrayList, String input) {
        String holder;
        int x = Integer.parseInt(input.substring(5));
        if (x > arrayList.size()) {
            holder = "☹ OOPS!!! Sorry item no found :-(\n";
        } else {
            holder = "Nice! I've marked this task as done:" + "\n";
            holder = holder.concat(arrayList.get(x - 1).markAsDone().getStatusIcon() + " "
                    + arrayList.get(x - 1).description);
            arrayList.set(x - 1, arrayList.get(x - 1).markAsDone());
        }
        return holder;
    }
}


