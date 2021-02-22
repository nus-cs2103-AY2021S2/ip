package command;

import java.time.LocalDate;

import duke.Tag;

public interface CommandDetailsExtractable {

    //public Command getCommand();
    public Tag getTagAction();
    public String getTaskDescription();
    public String getTag();
    public int getIndexForListDoneOrDelete();
    public LocalDate getDeadline();
    public String getEventTime();
    public String getFindKeyword();
}
