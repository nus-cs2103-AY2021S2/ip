import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    static final int LENGTH_OF_LINE = 80;

    static String welcome = " __________________________ \n"
            + "|  HI! THIS IS             |\n"
            + "|   ____        _          |\n"
            + "|  |  _ \\ _   _| | _____   |\n"
            + "|  | | | | | | | |/ / _ \\  |\n"
            + "|  | |_| | |_| |   <  __/  |\n"
            + "|  |____/ \\__,_|_|\\_\\___|  |\n"
            + "|                          |\n"
            + "|  What can I do for you?  |\n"
            + "|__________________________|\n";

    static String bye = " __________________________ \n"
            + "|  GOOD BYE!               |\n"
            + "|   ____        _          |\n"
            + "|  |  _ \\ _   _| | _____   |\n"
            + "|  | | | | | | | |/ / _ \\  |\n"
            + "|  | |_| | |_| |   <  __/  |\n"
            + "|  |____/ \\__,_|_|\\_\\___|  |\n"
            + "|                          |\n"
            + "|  Always be with you.     |\n"
            + "|__________________________|\n";

    static String horizontalLine = StringParser.underlineGenerator(LENGTH_OF_LINE);



    public static void main(String[] args) {

        boolean loop = true;
        String message = "";
        String command;
        Task currTask;
        CommandType type;
        JobList list = new JobList(LENGTH_OF_LINE);
        Scanner sc = new Scanner(System.in);

        // Create data.txt if it does not exist.
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File data = new File("data" + File.separatorChar + "data.txt");
        if (!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Load from data.txt.
        Scanner scFile = null;
        FileWriter writer = null;
        try {
            scFile = new Scanner(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (Objects.requireNonNull(scFile).hasNextLine()) {
            list.addJob(Command.loadData(scFile.nextLine()));
        }

        try {
            writer = new FileWriter(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(welcome);

        while (true) {
            command = sc.nextLine();
            try {
                type = Command.getType(command);
            } catch (DukeException e) {
                message = e.getMessage() + "\n";
                System.out.print(horizontalLine);
                System.out.print(message);
                System.out.println(horizontalLine);
                continue;
            }
            if (type == CommandType.BYE) {
                String saveData = "";
                loop = false;
                for (int i = 0; i < list.getSize(); i++) {
                    saveData = saveData.concat(Command.saveData(list.getJob(i)));
                }
                try {
                    assert writer != null;
                    writer.write(saveData);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (type == CommandType.LIST) {
                message = list.toString();
            } else if (type == CommandType.DONE) {
                int index;
                index = Integer.parseInt(command.substring(5)) - 1;
                if (index < list.getSize() && index >= 0) {
                    currTask = list.getJob(index);
                    currTask.markAsDone();
                    message = "This task is marked as done: \n"
                            + StringParser.newLiner(currTask.toString(), LENGTH_OF_LINE);
                    list.replaceJob(index, currTask);
                } else {
                    message = "No such task in the list\n";
                }
            } else if (type == CommandType.DELETE) {
                int index;
                index = Integer.parseInt(command.substring(7)) - 1;
                if (index < list.getSize() && index >= 0) {
                    currTask = list.getJob(index);
                    list.deleteJob(index);
                    message = "This task is deleted: \n"
                            + StringParser.newLiner(currTask.toString(), LENGTH_OF_LINE)
                            + "Now you have " + list.getSize()
                            + (list.getSize() == 1 ? " task in the list\n" : " tasks in the list\n");;
                } else {
                    message = "No such task in the list\n";
                }
            } else if (type == CommandType.OTHER) {
                message = "Invalid command\n";
            } else {
                try {
                    Task t = Command.convertToTask(command, type);
                    list.addJob(t);
                    message = "Task added:\n" + StringParser.newLiner(t.toString(), LENGTH_OF_LINE)
                            + "Now you have " + list.getSize()
                            + (list.getSize() == 1 ? " task in the list\n" : " tasks in the list\n");
                } catch (DukeException e) {
                    message = e.getMessage() + "\n";
                }
            }

            if (!loop) {
                break;
            }

            System.out.print(horizontalLine);
            System.out.print(message);
            System.out.println(horizontalLine);

        }
        System.out.println(bye);
    }
}
