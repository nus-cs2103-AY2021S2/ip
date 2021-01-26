import java.util.Scanner;
public class Duke {
    private static String FILE_PATH = "./data/";
    private static String FILE_NAME = "history.txt";
    private static String greetings = "Hi, I am Bebong, a self-centered bot. I can't do anything for you.";
    private static String bye = "Goodbye, hope to not see you again.";
    private Storage storage;
    private TaskManager taskManager;

    public static void main(String[] args) {
        Duke duke = new Duke(FILE_PATH, FILE_NAME);
        duke.doCommand();
    }

    public Duke(String filePath, String fileName) {
        storage = new Storage(filePath, fileName);
        taskManager = new TaskManager(storage.readPreviousFile());
    }

    private void doCommand() {
        Scanner sc = new Scanner(System.in);
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
                doList();
            }
            else if (command.equals("done")) {
                int index = Integer.parseInt(tmp[1]);
                doDone(index);
            }
            else if (command.equals("delete")) {
                int index = Integer.parseInt(tmp[1]);
                doDelete(index);
            }
            else {
                doTask(word);
            }
            storage.saveTasks(taskManager);
        }
    }

    private static void doBye(String bye) {
        System.out.println(bye);
    }

    private void doList() {
        for (int i=0; i < taskManager.size(); i++) {
            System.out.print(i+1);
            System.out.print(".");
            System.out.println(taskManager.get(i));
        }
    }

    private void doDone(int index) {
        try {
            Task currTask = taskManager.get(index - 1);
            currTask = currTask.doTask();
            taskManager.set(index - 1, currTask);
            System.out.println("Nice I have marked this task as done!");
            System.out.println(currTask);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a done cannot be empty.");
        }
    }

    private void doDelete(int index) {
        try {
            Task currTask = taskManager.get(index - 1);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(currTask);
            taskManager.remove(index - 1);
            System.out.println("Now you have " + taskManager.size() + " tasks in the list.");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a delete cannot be empty.");
        }
    }

    private void doTask(String word) {
        try {
            String[] tmp = word.split(" ");
            String command = tmp[0];
            if (command.equals("todo")) {
                doToDo(word);
            }
            else if (command.equals("deadline")) {
                doDeadline(word);
            }
            else if (command.equals("event")) {
                doEvent(word);
            }
            else {
                throw new NoMeaningException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        catch (NoMeaningException e){
            System.out.println(e.getMessage());
        }
    }

    private void doToDo(String word) throws NoMeaningException {
        try {
            String realWord = word.substring(5);
            ToDo todo = new ToDo(realWord);
            taskManager.add(todo);
            doTaskFinally(todo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoMeaningException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private void doDeadline(String word) throws NoMeaningException {
        try {
            String realWord = word.substring(9);
            String[] deadlineWords = realWord.split("/by");
            String deadlineWord = deadlineWords[0];
            String deadlineTime = deadlineWords[1];
            Deadline deadline = new Deadline(deadlineWord, deadlineTime);
            taskManager.add(deadline);
            doTaskFinally(deadline);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoMeaningException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    private void doEvent(String word) throws NoMeaningException {
        try {
            String realWord = word.substring(6);
            String[] eventWords = realWord.split("/at");
            String eventWord = eventWords[0];
            String eventTime = eventWords[1];
            Event event = new Event(eventWord, eventTime);
            taskManager.add(event);
            doTaskFinally(event);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoMeaningException("☹ OOPS!!! The description of a event cannot be empty.");
        }
    }

    private void doTaskFinally(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskManager.size() + " tasks in the list.");
    }
}
