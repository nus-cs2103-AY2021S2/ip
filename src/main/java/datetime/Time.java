package datetime;

import java.time.LocalTime;

public class Time {
    private LocalTime time;
    private boolean isEmpty; // i.e. user did not set time
    private boolean isHourOnly;

    Time() {
        this.isEmpty = true;
    }

    // hmm what happens when users want to edit time variables
    Time(int hour) {
        this.time = LocalTime.of(hour, 0);
        this.isEmpty = false;
    }

    Time(int hour, int minute) {
        this.time = LocalTime.of(hour, minute);
        this.isEmpty = false;
    }

    private void checkHourOnly(){
        this.isHourOnly = this.time.getMinute() == 0;
    }

    // todo printing AM or PM
    @Override
    public String toString() {
        if (isEmpty) {
            return "";
        }

        if (isHourOnly) {
            return String.format("%d", time.getHour())
        } else {
            return String.format("%d:%d", time.getHour(), time.getMinute())
        }
    }
}
