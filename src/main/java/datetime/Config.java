package datetime;

public class Config {
    boolean use12Hour = false;
    // currently time uses 24 hour

    public void toggle12HourFormatting() {
        this.use12Hour = !this.use12Hour;
    }
}
