import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);

    public static void startResponse(String userInput) throws Exception {
        if (userInput.equals("list")) {
            if (Response.numTasks == 0) {
                throw new NullPointerException();
            }
            Response.listTasks();
        } else if (userInput.contains("done")) {
            int taskNumber = Integer.parseInt(userInput.substring(5));
            if (taskNumber <= 0 || taskNumber > Response.numTasks) {
                throw new IndexOutOfBoundsException();
            }
            Response.markAsDone(taskNumber);
        } else if (userInput.contains("todo")) {
            if (userInput.length() <= 5) {
                throw new IllegalArgumentException();
            }
            Task currentTask = new Todo(userInput.substring(5));
            Response.addTask(currentTask);
        } else if (userInput.contains("deadline")) {
            if (userInput.length() <= 9 || !userInput.contains("/")) {
                throw new IllegalArgumentException();
            }
            int temp = userInput.indexOf('/') - 1;
            Task currentTask = new Deadline(userInput.substring(9, temp),
                    userInput.substring(temp + 4));
            Response.addTask(currentTask);
        } else if (userInput.contains("event")) {
            if (userInput.length() <= 5 || !userInput.contains("/")) {
                throw new IllegalArgumentException();
            }
            int temp = userInput.indexOf('/') - 1;
            Task currentTask = new Event(userInput.substring(6, temp),
                    userInput.substring(temp + 4));
            Response.addTask(currentTask);
        } else {
            throw new Exception();
        }
    }

    public static void main(String[] args) {
        Response.hello();

        while (true) {
            try {
                String userInput = sc.nextLine();
                if (userInput.equals("bye")) {
                    Response.bye();
                    break;
                }
                startResponse(userInput);
            } catch (IllegalArgumentException e) {
                System.out.println("\nerror: not enough information to create task!\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nerror: taskd does not exist!\n");
            } catch (NullPointerException e) {
                System.out.println("\nerror: there is nothing in the list!\n");
            } catch (Exception e) {
                System.out.println("\nerror: sorry, I don't understand :(\n");
            }
        }
    }
}
