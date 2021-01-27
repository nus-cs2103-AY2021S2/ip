package kelbot;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    
    private Command command;
    private int taskNumber = 0;
    private String keyword = "";
    private String taskName = "";
    private LocalDate date = null;
    
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
            } else if (commands[0].equals("find")) {
                this.command = Command.FIND;
                for (int i = 1; i < commands.length; i++) {
                    this.keyword += " " + commands[i];
                }
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
    
    public Command getCommand() {
        return this.command;
    }
    
    public String getTaskName() {
        return this.taskName;
    }
    
    public String getKeyword() {
        return keyword;
    }
    
    public int getTaskNumber() {
        return this.taskNumber;
    }
    
    public LocalDate getDate() {
        return this.date;
    }
}
