public class Ui {
    public static void execute(Command command, TaskList taskList, Task task) {
        switch (command) {
            case BYE:
                System.out.println("    Bye bye, catch you soon.");
                break;
            case EVENT:
            case TODO:
            case DEADLINE:
                System.out.println("    Got it. I've added this task: ");
                break;
            case DELETE:
                System.out.println("    Noted. I've removed this task:");
                break;
            case DONE:
                System.out.println("    Nice! I've marked this task as done:");
                break;
            case LIST:
                System.out.println("    Here are the tasks in your list:");
                taskList.print();
                break;
            case NONE:
                break;

            default:
                break;

        }
        if (!(command.equals(Command.LIST) || command.equals(Command.BYE))) {
            System.out.println("      " + task.getStatus());
            System.out.println("    Now you have " + taskList.numberOfTasks() + " tasks in the list.\n");
        }

    }

    public static void greeting() {
        System.out.println("Hey yo, I'm Travis.\nI make you work. \n");
    }

    public static void exception(String exceptionMessage) throws TaskException {
        throw new TaskException(exceptionMessage);
    }
}
