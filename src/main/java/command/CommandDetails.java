package command;

import java.time.LocalDate;

import duke.Tag;

public class CommandDetails implements CommandDetailsExtractable {

    private Tag tagAction;
    private String taskDescription;
    private String tag;
    private int indexForListDoneOrDelete;
    private LocalDate deadline;
    private String eventTime;
    private String findKeyword;

    public CommandDetails(Command command, String ...strings) {
        switch (command) {
        case DONE:
        case DELETE:
            this.indexForListDoneOrDelete = Integer.parseInt(strings[0]) - 1;
            break;
        case TAG:
            this.taskDescription = strings[1];
            this.tag = strings[2];
            String tagMode = strings[0];
            if (tagMode.equals("add")) {
                this.tagAction = Tag.ADD;
            } else if (tagMode.equals("delete")) {
                this.tagAction = Tag.DELETE;
            } else {
                throw new AssertionError("CommandDetails does not understand the tag action from the user's input");
            }
            break;
        case TODO:
            this.taskDescription = strings[0];
            break;
        case DEADLINE:
            this.taskDescription = strings[0];
            this.deadline = LocalDate.parse(strings[1]);
            break;
        case EVENT:
            this.taskDescription = strings[0];
            this.deadline = LocalDate.parse(strings[1]);
            this.eventTime = strings[2];
            break;
        case FIND:
            this.findKeyword = strings[0];
            break;
        default:

        }
    }


    @Override
    public String getTaskDescription() {
        return taskDescription;
    }

    @Override
    public Tag getTagAction() {
        return tagAction;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public int getIndexForListDoneOrDelete() {
        return indexForListDoneOrDelete;
    }

    @Override
    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public String getEventTime() {
        return eventTime;
    }

    @Override
    public String getFindKeyword() {
        return findKeyword;
    }
}
