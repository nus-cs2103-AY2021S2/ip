package duke.parser;

import duke.duke.Duke;
import duke.exceptions.InvalidArgumentException;
import duke.exceptions.InvalidCommandException;
import duke.utils.DateValidator;
import duke.utils.DateValidatorUsingDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public String description;
    String command;
    String deadline;

    public Parser() {
        this.command = "";
        this.description = "";
        this.deadline = "";

    }

    public void processInput(String userInput, Duke bot) throws InvalidCommandException, InvalidArgumentException {
        String[] starr = userInput.split(" ", 2);
        if (starr.length == 1) {
            if (!(starr[0].equals("bye") || starr[0].equals("list"))) {
                if (starr[0].equals("todo") || starr[0].equals("done") ||
                        starr[0].equals("deadline") || starr[0].equals("event") || starr[0].equals("delete")) {
                    throw new InvalidCommandException("OOPS!!! "
                            + "The description of a " + starr[0] + " cannot be empty.\n");
                } else {
                    throw new InvalidCommandException("OOPS!!! " +
                            "I'm sorry, but I don't know what that means :-()\n");
                }
            }
            this.command = starr[0];
        } else {
            this.command = starr[0];
            switch (starr[0]) {
            case "done":
            case "delete":

                try {
                    Integer.parseInt(starr[1]);
                } catch (NumberFormatException ex) {
                    throw new InvalidArgumentException("Invalid command! " +
                            "Please input task number using 'delete (number)'.\n");
                }
                if (Integer.parseInt(starr[1]) > bot.getList().getLst().size()) {
                    throw new InvalidArgumentException("Please input argument <= to "
                            + bot.getList().getLst().size() + "!\n");
                }
                this.description = starr[1];
                break;
            case "todo":
                this.description = starr[1];
                break;
            case "deadline":
                String[] arr = starr[1].split("/by");
                if (arr.length == 1) {
                    throw new InvalidArgumentException("Please input task due date using '/at (date)'!\n");
                }
                String[] str = arr[0].split(" ", 2);
                if(str.length == 1) {
                        throw new InvalidArgumentException("Please input task description!\n");
                    }
                    String deadLine = arr[1].strip();
                    processDate(deadLine);
                    this.description = arr[0];
                    break;

                case "event":
                    String[] a = starr[1].split("/at");
                    if(a.length == 1) {
                        throw new InvalidArgumentException("Please input task due date using '/at (date)'!\n");
                    }

                String[] s = a[0].split(" ", 2);
                    if(s.length == 1) {
                        throw new InvalidArgumentException("Please input task description!\n");
                    }
                    String eventTime = a[1].strip();
                    processDate(eventTime);
                    this.description = a[0];
                    break;
            }
        }
    }

    public boolean isEquals(String type) {
        return command.equals(type);
    }

    public void processDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d HHmm");
        DateValidator dateTimeValidator = new DateValidatorUsingDateFormat(dateTimeFormatter);

        if(!dateTimeValidator.isValid(date)) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            DateValidator dateValidator = new DateValidatorUsingDateFormat(dateFormatter);
            if(!dateValidator.isValid(date)) {
                this.deadline = date;
            } else {
                LocalDate d1 = LocalDate.parse(date);
                String day = d1.getDayOfWeek().toString();
                String month = d1.getMonth().toString();
                int year = d1.getYear();
                this.deadline = String.format("%s %s %d", day, month, year);
            }
        } else {
            String[] deadLineArray = date.split(" ");
            LocalDate d1 = LocalDate.parse(deadLineArray[0]);
            String day = d1.getDayOfWeek().toString();
            String month = d1.getMonth().toString();
            int year = d1.getYear();
            this.deadline = String.format("%s %s %d %s", day, month, year, deadLineArray[1]);

        }
    }

    public String getCommand() {
        return command;
    }
    public String getDeadline() {
        return deadline;
    }
}
