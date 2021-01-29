abstract class IndexedCommand extends Command{
    private final int index;
    public IndexedCommand(int position){
        this.index = position - 1;
    }

    @Override
    String[] run() {
        return new String[]{String.valueOf(index)};
    }
}
