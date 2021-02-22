package command;

import java.time.LocalDate;

import duke.Tag;

/**
 * Represents the additional details parsed from the user's input
 */
public class CommandDetails implements CommandDetailsExtractable {

    private final Command command;
    private Tag tagAction;
    private String taskDescription;
    private String tag;
    private int indexForListDoneOrDelete;
    private LocalDate deadline;
    private String eventTime;
    private String findKeyword;

    /**
     * Creates a CommandDetails object
     * @param command the Command parsed from the user's input
     * @param strings string arguments parsed from the user's input
     */
    public CommandDetails(Command command, String ...strings) {
        this.command = command;
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

    public Command getCommand() {
        return command;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            boolean isEqualCommand = command.equals(((CommandDetails) obj).getCommand());
            boolean isEqualTag = tag.equals(((CommandDetails) obj).getTag());
            boolean isEqualTagAction = tagAction == ((CommandDetails) obj).getTagAction();
            boolean isEqualIndex = indexForListDoneOrDelete == ((CommandDetails) obj).getIndexForListDoneOrDelete();
            boolean isEqualDeadline = deadline.equals(((CommandDetails) obj).getDeadline());
            boolean isEqualEventTime = eventTime.equals(((CommandDetails) obj).getEventTime());
            boolean isEqualFindKeyword = findKeyword == ((CommandDetails) obj).getFindKeyword();

            return obj instanceof CommandDetails && isEqualCommand && isEqualTag && isEqualTagAction && isEqualIndex
                    && isEqualDeadline && isEqualEventTime && isEqualFindKeyword;
        }
    }
}
