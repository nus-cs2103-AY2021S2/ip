import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    ArrayList<Task> taskList;

    public Duke() {
        taskList = new ArrayList<>();
    }

    public void storeTask(Task task) {
        taskList.add(task);
        System.out.println("Duchess: Great! I have added:" + "\n" + task + "\n" + "U have " + taskList.size() + " tasks in the list now :)");
    }

    public void printList() {
        String msg = "Here are the tasks in your list:";
        for(int i = 0; i < taskList.size(); i++) {
            msg+= "\n" +  (i + 1) + ". " + taskList.get(i);
        }
        System.out.println(msg);
    }

    public void markComplete(int n) {
        Task temp = this.taskList.get(n - 1);
        temp.checkTask();
        System.out.println("Duchess: Woohoo I've checked off this task:" + "\n" + temp);

    }

    public void deleteTask(int task) {
        Task temp = this.taskList.get(task - 1);
        this.taskList.remove(task - 1);
        System.out.println("Duchess: As requested, i have removed this task:" + "\n" + temp + "\n" + "U have " + taskList.size() + " tasks in the list now :)");
    }

    public static void main(String[] args) {
        Duke chatbot = new Duke();
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Duchess: Hello, Duchess here. What can i do for you?");
        boolean readOn = true;

        while (readOn) {
            String command = sc.nextLine();  // Read user input
            String arr[] = command.split(" ", 2);
            String action = arr[0];
            try {
                switch (action) {
                    case "bye" :
                        readOn = false;
                        break;
                    case "list":
                        chatbot.printList();
                        break;
                    case "done":
                        if (arr.length <= 1) {
                            throw new MissingTaskException();
                        }
                        int n = Integer.parseInt(arr[1]);
                        chatbot.markComplete(n);
                        break;
                    case "todo":
                        if (arr.length <= 1) {
                            throw new MissingInputException(action);
                        }
                        Task todo = new Todo(arr[1]);
                        chatbot.storeTask(todo);
                        break;
                    case "event":
                        if (arr.length <= 1) {
                            throw new MissingInputException(action);
                        }
                        System.out.println("Duchess: When will this event be?");
                        String date = sc.nextLine();
                        Task event = new Event(arr[1], date);
                        chatbot.storeTask(event);
                        break;
                    case "deadline":
                        if (arr.length <= 1) {
                            throw new MissingInputException(action);
                        }
                        System.out.println("Duchess: When does this have to be done by?");
                        String due = sc.nextLine();
                        Task deadline = new Deadline(arr[1], due);
                        chatbot.storeTask(deadline);
                        break;
                    case "delete":
                        if(arr.length <= 1) {
                            throw new MissingTaskException();
                        }
                        chatbot.deleteTask(Integer.parseInt(arr[1]));
                        break;
                    default:
                        throw new UnlcearInputException();
                }
            } catch (MissingInputException e) {
                System.out.println((e.getMessage()));
            } catch (UnlcearInputException e) {
                System.out.println((e.getMessage()));
            } catch (DukeExceptions e) {
                System.out.println((e.getMessage()));
            }
        }
        System.out.println("Duchess: Bye, Have an awesome day!");
        sc.close();
    }
}

