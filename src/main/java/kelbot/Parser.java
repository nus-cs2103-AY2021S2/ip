package kelbot;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    
    private Command command;
    private String taskName = "";
    private int taskNumber = 0;
    private LocalDate date = null;
    
    public Parser(String input) throws DateTimeParseException {
        try {
            String[] commands = input.split(" ");
            
            if (input.equals("bye")) {
                command = Command.BYE;
            } else if (input.equals("list")) {
                command = Command.LIST;
            } else if (commands[0].equals("done")) {
                command = Command.DONE;
                taskNumber = Integer.parseInt(commands[1]);
            } else if (commands[0].equals("delete")) {
                command = Command.DELETE;
                taskNumber = Integer.parseInt(commands[1]);
            } else if (commands[0].equals("todo")) {
                command = Command.TODO;
                for (int i = 1; i < commands.length; i++) {
                    taskName += " " + commands[i];
                }
            } else if (commands[0].equals("deadline")) {
                command = Command.DEADLINE;
                String deadlineDate = "";
                for (int i = 1; i < commands.length; i++) {
                    if (commands[i].equals("/by")) {
                        for (int j = i + 1; j < commands.length; j++) {
                            deadlineDate += commands[j];
                        }
                        break;
                    }
                    taskName += " " + commands[i];
                }
                    date = LocalDate.parse(deadlineDate);
            } else if (commands[0].equals("event")) {
                command = Command.EVENT;
                String eventDate = "";
                for (int i = 1; i < commands.length; i++) {
                    if (commands[i].equals("/at")) {
                        for (int j = i + 1; j < commands.length; j++) {
                            eventDate += commands[j];
                        }
                        break;
                    }
                    taskName += " " + commands[i];
                }
                    date = LocalDate.parse(eventDate);
            } else {
                throw new KelbotException("Invalid Command");
            }
        } catch (KelbotException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Command getCommand() {
        return command;
    }
    
    public String getTaskName() {
        return taskName;
    }
    
    public int getTaskNumber() {
        return taskNumber;
    }
    
    public LocalDate getDate() {
        return date;
    }
}
