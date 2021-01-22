public class Parser {

    public static Command parse(String input) {
        String line = "------------------------------------------";
        String type = input.split(" ")[0];
        Command c = null;
        if (type.equals("todo")) {
            //tasks.addToDo(input);
            //System.out.println(input);
            c = new AddCommand(input);
        } else if (type.equals("deadline")) {
            //tasks.addDeadline(input);
            c = new AddCommand(input);
        } else if (type.equals("event")) {
            //tasks.addEvent(input);
            c = new AddCommand(input);
        } else if (type.equals("delete")) {
            //tasks.delete(input);
            c = new DeleteCommand(input);
        } else if (type.equals("done")) {
            //tasks.markDone(input);
            c = new DoneCommand(input);
        } else if (type.equals("list")) {
            //tasks.list();
            c = new ListCommand(input);
        } else if (type.equals("bye")) {
            //tasks.list();
            c = new ExitCommand(input);
        } else {
            new InvalidInstructionException();
        }

        return c;


    }
}
