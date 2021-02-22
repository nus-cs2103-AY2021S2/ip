package command;

import java.time.LocalDate;

import duke.Tag;

/**
 * Implementing classes have methods necessary to extract additional information from user's input
 */
public interface CommandDetailsExtractable {

    public Tag getTagAction();
    public String getTaskDescription();
    public String getTag();
    public int getIndexForListDoneOrDelete();
    public LocalDate getDeadline();
    public String getEventTime();
    public String getFindKeyword();
}
