package kelbot;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    
    private Command command;
    private String taskName = "";
    private int taskNumber = 0;
    private LocalDate date = null;
    
    /**
     * Initializes Parser
     *
     * @param input The input given by the user
     * @throws DateTimeParseException if the date is invalid
     */
    
    public Parser(String input) throws DateTimeParseException {
        try {
            String[] commands = input.split(" ");
            if (input.equals("bye")) {
                this.command = Command.BYE;
            } else if (input.equals("list")) {
                this.command = Command.LIST;
            } else if (commands[0].equals("done")) {
                this.command = Command.DONE;
                this.taskNumber = Integer.parseInt(commands[1]);
            } else if (commands[0].equals("delete")) {
                this.command = Command.DELETE;
                this.taskNumber = Integer.parseInt(commands[1]);
            } else if (commands[0].equals("todo")) {
                this.command = Command.TODO;
                for (int i = 1; i < commands.length; i++) {
                    this.taskName += " " + commands[i];
                }
            } else if (commands[0].equals("deadline")) {
                this.command = Command.DEADLINE;
                String date = "";
                for (int i = 1; i < commands.length; i++) {
                    if (commands[i].equals("/by")) {
                        for (int j = i + 1; j < commands.length; j++) {
                            date += commands[j];
                        }
                        break;
                    }
                    this.taskName += " " + commands[i];
                }
                //try {
                    this.date = LocalDate.parse(date);
                //} catch (DateTimeParseException e) {
                //    System.out.println("Invalid Date");
                //}
            } else if (commands[0].equals("event")) {
                this.command = Command.EVENT;
                String date = "";
                for (int i = 1; i < commands.length; i++) {
                    if (commands[i].equals("/at")) {
                        for (int j = i + 1; j < commands.length; j++) {
                            date += commands[j];
                        }
                        break;
                    }
                    this.taskName += " " + commands[i];
                }
                //try {
                    this.date = LocalDate.parse(date);
                //} catch (DateTimeParseException e) {
                //    System.out.println("Invalid Date");
                //}
            } else {
                throw new KelbotException("Invalid Command");
            }
        } catch (KelbotException e) {
            System.out.println(e.getMessage());
        }
    
    }
    
    /**
     * Gets Command
     *
     * @return the command read by this parser
     */
    
    public Command getCommand() {
        return this.command;
    }
    
    /**
     * Gets Task Name
     *
     * @return the task name read by this parser
     */
    
    public String getTaskName() {
        return this.taskName;
    }
    
    /**
     * Gets Task Number
     *
     * @return the task number read by this parser
     */
    
    public int getTaskNumber() {
        return this.taskNumber;
    }
    
    /**
     * Gets Date
     *
     * @return the date read by this parser
     */
    
    public LocalDate getDate() {
        return this.date;
    }
}
