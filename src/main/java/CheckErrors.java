import java.util.ArrayList;

public class CheckErrors {
    protected String input;
    protected ArrayList<Task> list;

    public CheckErrors(String input, ArrayList<Task> list) {
        this.input = input;
        this.list = list;
    }

//    public boolean check() {
//        if (input.startsWith("todo")) {
//            return (input.length() > 5) && (input.charAt(4) == ' ');
//        } else if (input.startsWith("deadline")) {
//            return (input.length() > 9) && (input.charAt(8) == ' ');
//        } else if (input.startsWith("event")) {
//            return (input.length() > 6) && (input.charAt(5) == ' ');
//        } else if (input.startsWith("done")) {
//            if (input.length() <= 5) {
//                return false;
//            } else {
////            return (input.length() > 5) && (input.charAt(8) == ' ');
//                try {
//                    int i = Integer.parseInt(input.substring(5));
//                } catch (NumberFormatException nfe) {
//                    return false;
//                }
//                return Integer.parseInt(input.substring(5)) <= noOfTasks;
//            }
//        } else if (input.equals("list")) {
//            return true;
//        } else {
//            return false;
//        }
////        try {
////            new Task(input);
////        } catch (IllegalArgumentException ex) {
////            throw new IllegalTaskException(ex.getMessage(), "todo");
////        }
//    }

    public boolean check() {
        String taskType = "";

        if (!input.startsWith("done") && !input.startsWith("delete") && !input.startsWith("todo") && !input.startsWith("deadline")
        && !input.startsWith("event")) {
//            throw new IllegalInputException("");
            System.out.println(new IllegalInputException("").toString());
            return false;
        }

//        System.out.println("parsed int is:   " + Integer.parseInt(input.substring(5)));
//        System.out.println("input: " + input);

        try {
            if (input.startsWith("done")) {
//                System.out.println("current array length: " + list.size() + "\nArray: " + list.toString());
                taskType = "done";
                input.substring(6);
//                System.out.println("task to delete: " + input.substring(5));
                String taskToMark = input.substring(5);
                int taskToMarkInt = Integer.parseInt(taskToMark);
                list.get(taskToMarkInt - 1);
//                System.out.println(Integer.parseInt(input.substring(5)));
            } else if (input.startsWith("delete")) {
                taskType = "delete";
                input.substring(8);
                String taskToDelete = input.substring(7);
                int taskToDeleteInt = Integer.parseInt(taskToDelete);
                list.get(taskToDeleteInt - 1);
            } else if (input.startsWith("todo")) {
                taskType = "todo";
                input.substring(6);
            } else if (input.startsWith("deadline")) {
                taskType = "deadline";
                input.substring(10);
            } else if (input.startsWith("event")) {
                taskType = "event";
                input.substring(7);
            }
//            System.out.println("task type: " + taskType);
        } catch (StringIndexOutOfBoundsException ex) {
            if (input.startsWith("done") || input.startsWith("delete")) {
//                System.out.println("string index out of bounds done");
//                throw new IllegalDoneException(ex.getMessage());
                System.out.println(new IllegalDoneDeleteException(ex.getMessage(), taskType).toString());
                return false;
            } else {
//                System.out.println("string index out of bounds not done");
//                throw new IllegalTaskException(ex.getMessage(), taskType);
                System.out.println(new IllegalTaskException(ex.getMessage(), taskType).toString());
                return false;
            }
        } catch (IndexOutOfBoundsException ex) {
//            throw new OutOfBoundsDoneException(ex.getMessage());
            System.out.println(new OutOfBoundsDoneDeleteException(ex.getMessage()).toString());
            return false;
        }

        return true;
    }
}
