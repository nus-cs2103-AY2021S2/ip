import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);
        String greetings = "Hi, I am Bebong, a self-centered bot. I can't do anything for you.";
        String bye = "Goodbye, hope to not see you again.";
        List<Task> lst = new ArrayList<>();
        System.out.println(greetings);

        while (sc.hasNext()) {
            String word = sc.nextLine();
            String[] tmp = word.split(" ");
            String command = tmp[0];
            if (word.equals("bye")) {
                doBye(bye);
                break;
            }
            else if (word.equals("list")) {
                doList(lst);
            }
            else if (command.equals("done")) {
                int index = Integer.parseInt(tmp[1]);
                lst = doDone(lst, index);
            }
            else if (command.equals("delete")) {
                int index = Integer.parseInt(tmp[1]);
                lst = doDelete(lst, index);
            }
            else {
                lst = doTask(word, lst);
            }
        }
    }

    private static void doBye(String bye) {
        System.out.println(bye);
    }

    private static void doList(List<Task> lst) {
        for (int i=0; i < lst.size(); i++) {
            System.out.print(i+1);
            System.out.print(".");
            System.out.println(lst.get(i));
        }
    }

    private static List<Task> doDone(List<Task> lst, int index) {
        try {
            Task currTask = lst.get(index - 1);
            currTask = currTask.doTask();
            lst.set(index - 1, currTask);
            System.out.println("Nice I have marked this task as done!");
            System.out.println(currTask);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a done cannot be empty.");
        }
        return lst;
    }

    private static List<Task> doDelete(List<Task> lst, int index) {
        try {
            Task currTask = lst.get(index - 1);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(currTask);
            lst.remove(index - 1);
            System.out.println("Now you have " + lst.size() + " tasks in the list.");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a delete cannot be empty.");
        }
        return lst;
    }

    private static List<Task> doTask(String word, List<Task> lst) {
        try {
            String[] tmp = word.split(" ");
            String command = tmp[0];
            if (command.equals("todo")) {
                lst = doToDo(word, lst);
            }
            else if (command.equals("deadline")) {
                lst = doDeadline(word, lst);
            }
            else if (command.equals("event")) {
                lst = doEvent(word, lst);
            }
            else {
                throw new NoMeaningException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        catch (NoMeaningException e){
            System.out.println(e.getMessage());
        }
        return lst;
    }

    private static List<Task> doToDo(String word, List<Task> lst) throws NoMeaningException {
        try {
            String realWord = word.substring(5);
            ToDo todo = new ToDo(realWord);
            lst.add(todo);
            doTaskFinally(todo, lst);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoMeaningException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return lst;
    }

    private static List<Task> doDeadline(String word, List<Task> lst) throws NoMeaningException {
        try {
            String realWord = word.substring(9);
            String[] deadlineWords = realWord.split("/by");
            String deadlineWord = deadlineWords[0];
            String deadlineTime = deadlineWords[1];
            Deadline deadline = new Deadline(deadlineWord, deadlineTime);
            lst.add(deadline);
            doTaskFinally(deadline, lst);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoMeaningException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        return lst;
    }

    private static List<Task> doEvent(String word, List<Task> lst) throws NoMeaningException {
        try {
            String realWord = word.substring(6);
            String[] eventWords = realWord.split("/at");
            String eventWord = eventWords[0];
            String eventTime = eventWords[1];
            Event event = new Event(eventWord, eventTime);
            lst.add(event);
            doTaskFinally(event, lst);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoMeaningException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        return lst;
    }

    private static void doTaskFinally(Task task, List<Task> lst) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }
}
