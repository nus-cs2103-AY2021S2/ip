import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasksList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String dateTime = "", taskDesc = "", input = "";
        boolean exitFlag = false;
        Output.printWelcomeMsg();

        while(sc.hasNextLine()) {
            String[] numArgs;
            String[] checkFormat;
            input = sc.nextLine();

            try {
                Command cmd = Command.valueOf(input.split(" ")[0].toUpperCase());

                switch (cmd) {
                    case BYE:
                        Output.printByeMsg();
                        exitFlag = true;
                        break;
                    case LIST:
                        if(tasksList.size() == 0) {
                            throw new EmptyListException();
                        }

                        System.out.println(Output.addLine());
                        for(int i = 1; i <= tasksList.size(); i++) {
                            Task task = tasksList.get(i - 1);
                            System.out.println("    " + i + ". " + task);
                        }
                        System.out.println(Output.addLine() + "\n");
                        break;
                    case DONE:
                        numArgs = input.split(" ");

                        if (numArgs.length < 2) {
                            throw new InvalidFormatException("Invalid event number. Please specify a valid event you would like to mark done");
                        } else if (Integer.parseInt(numArgs[1]) <= 0 || Integer.parseInt(numArgs[1]) > tasksList.size()) {
                            throw new InvalidFormatException("Invalid event number. Please specify a valid event you would like to mark done");
                        }
                        Task done = tasksList.get(Integer.parseInt(numArgs[1]) - 1);
                        done.toggleStatus();

                        Output.printDoneMsg(done);
                        break;
                    case TODO:
                        numArgs = input.split(" ");
                        if(numArgs.length > 1) {
                            taskDesc = input.split(" ")[1];
                        }

                        Todo todo = new Todo(taskDesc);

                        tasksList.add(todo);
                        Output.printAddedTask(todo, tasksList.size());
                        break;
                    case DEADLINE:
                        numArgs = input.split(" ");
                        checkFormat = input.split(" /by ");

                        if(numArgs.length >= 4 && checkFormat.length > 1) {
                            taskDesc = input.split("deadline ")[1].split(" /by ")[0];
                            dateTime = input.split(" /by ")[1];
                        }

                        Deadline deadline = new Deadline(taskDesc, dateTime);

                        tasksList.add(deadline);
                        Output.printAddedTask(deadline, tasksList.size());
                        break;
                    case EVENT:
                        numArgs = input.split(" ");
                        checkFormat = input.split(" /at ");

                        if(numArgs.length >= 4 && checkFormat.length > 1) {
                            taskDesc = input.split("event ")[1].split(" /at ")[0];
                            dateTime = input.split(" /at ")[1];
                        }

                        Event event = new Event(taskDesc, dateTime);

                        tasksList.add(event);
                        Output.printAddedTask(event, tasksList.size());
                        break;
                    case DELETE:
                        numArgs = input.split(" ");

                        if (numArgs.length < 2) {
                            throw new InvalidFormatException("Invalid event number. Please specify a valid event you would like to delete");
                        } else if (Integer.parseInt(numArgs[1]) <= 0 || Integer.parseInt(numArgs[1]) > tasksList.size()) {
                            throw new InvalidFormatException("Invalid event number. Please specify a valid event you would like to delete");
                        }
                        Task delete = tasksList.remove(Integer.parseInt(numArgs[1]) - 1);

                        Output.printDeleteMsg(delete, tasksList.size());
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(Output.addLine() + "\n    ☹ OOPS! I'm sorry, but I don't know what that means :(" + "\n" + Output.addLine());
            } catch (DukeException e1) {
                System.out.println(e1);
            }

            if(exitFlag) break;
        }
    }
}