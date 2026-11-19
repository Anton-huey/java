package lab4;
import java.util.Calendar;

/**
 * An immutable snapshot of the current time. Knows how to turn the time
 * into hand angles and a date string, but knows nothing about drawing.
 *
 * Angles are measured from the y-axis (straight up) and increase clockwise,
 * which is how a clock works.
 */
public class ClockTime {

    private static final double FULL_CIRCLE = 2 * Math.PI;
    private static final int HOURS_ON_DIAL = 12;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int SECONDS_PER_MINUTE = 60;

    private final int hour;    // 0-11
    private final int minute;  // 0-59
    private final int second;  // 0-59
    private final int year;
    private final int month;   // 1-12
    private final int day;

    /** Takes a snapshot of the current time. */
    public ClockTime() {
        this(Calendar.getInstance());
    }

    public ClockTime(Calendar calendar) {
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1; // Calendar months start at 0
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    /** The hour hand moves gradually, so 10:30 lies halfway between 10 and 11. */
    public double getHourAngle() {
        double hours = hour + minute / (double) MINUTES_PER_HOUR;
        return (FULL_CIRCLE * hours / HOURS_ON_DIAL);
    }

    public double getMinuteAngle() {
        double minutes = minute + second / (double) SECONDS_PER_MINUTE;
        return FULL_CIRCLE * minutes / MINUTES_PER_HOUR;
    }

    public double getSecondAngle() {
        return (FULL_CIRCLE * second / SECONDS_PER_MINUTE);
    }

}
