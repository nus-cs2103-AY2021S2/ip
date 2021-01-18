class Deadline extends Task{
    private final String time;

    Deadline(String description, String time){
        super(description);
        this.time = time;
    }

    @Override
    public String toString(){
        return String.format("[D][%s] %s (%s)", getStatusIcon(), description, time);
    }
}
