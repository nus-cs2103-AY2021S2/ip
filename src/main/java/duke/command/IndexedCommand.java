package duke.command;

abstract class IndexedCommand extends Command{
    private final int index;
    public IndexedCommand(int position){
        this.index = position - 1;
    }

    @Override
    public String[] run() {
        return new String[]{String.valueOf(index)};
    }
}
