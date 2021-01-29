package duke.command;

import duke.exception.DukeException;

import duke.storage.Storage;

import duke.task.TaskList;

import duke.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Command {
    protected String command;
    protected String description;
    protected String preposition;
    protected LocalDate date;
    protected boolean isExit;

    Command(String command, String description, String preposition, LocalDate date, boolean isExit) {
        this.command = command;
        this.description = description;
        this.preposition = preposition;
        this.date = date;
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Command) {
            Command other = (Command) obj;
            boolean b1 = checkEquality(this.command, other.command);
            boolean b2 = checkEquality(this.description, other.description);
            boolean b3 = checkEquality(this.preposition, other.preposition);
            boolean b4 = checkEqualityDate(this.date, other.date);
            boolean b5 = this.isExit == other.isExit;

            return b1 && b2 && b3 && b4 && b5;

        } else {
            return false;
        }
    }

    boolean checkEquality(String s1, String s2) {
        if (s1 == null) {
            return s2 == null;
        } else {
            if (s2 == null) {
                return false;
            } else {
                return s1.equals(s2);
            }
        }
    }

    boolean checkEqualityDate(LocalDate d1, LocalDate d2) {
        if (d1 == null) {
            return d2 == null;
        } else {
            if (d2 == null) {
                return false;
            } else {
                String s1 = d1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String s2 = d2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return s1.equals(s2);
            }
        }
    }
}
