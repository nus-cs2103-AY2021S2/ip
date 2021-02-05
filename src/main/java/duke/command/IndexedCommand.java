package duke.command;

abstract class IndexedCommand extends Command{
    private final int index;

    /**
     * Create command to manipulate Task in TaskList based on position index
     *
     * @param position One index position in TaskList of Task to manipulate
     */
    public IndexedCommand(int position){
        this.index = position - 1;
    }

    @Override
    public String[] run() {
        return new String[]{String.valueOf(index)};
    }
}
