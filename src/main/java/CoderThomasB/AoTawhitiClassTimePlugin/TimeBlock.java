package CoderThomasB.AoTawhitiClassTimePlugin;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Random;

public enum TimeBlock {
    // Allowed
    NoSchool, EndOfSchool, BeforeSchool, MorningBreak, Lunch, HomeBase, Orange,

    // ClassTime
    Red, Yellow, Green, Blue, Purple;

    static public TimeBlock GetBlockAtTime(LocalDateTime Time) {
        if (Time.getDayOfWeek() == DayOfWeek.SATURDAY) return NoSchool;
        if (Time.getDayOfWeek() == DayOfWeek.SUNDAY) return NoSchool;

        if (Time.getHour() >= 15) return EndOfSchool;

        if (Time.getHour() < 9) return BeforeSchool;

        if (Time.getHour() == 11 && Time.getMinute() < 20) return MorningBreak;

        if (Time.getHour() == 12 && Time.getMinute() > 20) return Lunch;

        if (Time.getDayOfWeek() == DayOfWeek.MONDAY) {
            if (Time.getHour() < 11) return HomeBase;
            if (Time.getHour() < 13) return Red;
            if (Time.getHour() < 14) return Yellow;
            if (Time.getHour() < 15) return Green;
        }
        if (Time.getDayOfWeek() == DayOfWeek.TUESDAY) {
            if (Time.getHour() < 10) return HomeBase;
            if (Time.getHour() < 11) return Blue;
            if (Time.getHour() < 13) return Purple;
            if (Time.getHour() < 14) return Red;
            if (Time.getHour() < 15) return Yellow;
        }
        if (Time.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
            if (Time.getHour() < 10) return Red;
            if (Time.getHour() < 11) return Green;
            if (Time.getHour() < 13) return Blue;
            if (Time.getHour() < 14) return Purple;
            if (Time.getHour() < 15) return EndOfSchool;
        }
        if (Time.getDayOfWeek() == DayOfWeek.THURSDAY) {
            if (Time.getHour() < 10) return HomeBase;
            if (Time.getHour() < 11) return Yellow;
            if (Time.getHour() < 13) return Green;
            if (Time.getHour() < 14) return Blue;
            if (Time.getHour() < 15) return Purple;
        }
        if (Time.getDayOfWeek() == DayOfWeek.FRIDAY) {
            if (Time.getHour() < 11) return HomeBase;
            if (Time.getHour() < 15) return Orange;
        }
        throw new RuntimeException("Can not get the TimeBlock for this date and time: " + Time);
    }

    public boolean CanPlayAtTime() {
        return switch (this) {
            case Red, Yellow, Green, Blue, Purple -> false;
            case NoSchool, EndOfSchool, BeforeSchool, MorningBreak, Lunch, HomeBase, Orange -> true;
        };
    }

    public String toDisplayString() {
        return switch (this) {
            case HomeBase -> "Home base";
            case EndOfSchool -> "End of school";
            case BeforeSchool -> "Before school";
            case MorningBreak -> "Morning break";
            case Lunch -> "Lunch";
            case NoSchool -> "No school today";
            case Orange -> "Friday Hapori";
            case Red, Blue, Green, Purple, Yellow -> String.format("%s block", this);
        };
    }

    public int GetBossBarColor() {
        return switch (this) {
            case Red -> 2;
            case Yellow, Orange -> 4;
            case Green -> 3;
            case Blue -> 1;
            case Purple -> 5;
            default -> new Random().nextInt(7);
        };
    }
    public String GetRawTestColor() {
        String[] RandomColors = {
                "dark_red",
                "red",
                "gold",
                "yellow",
                "dark_green",
                "green",
                "aqua",
                "dark_aqua",
                "dark_blue",
                "blue",
                "light_purple",
                "dark_purple"
        };

        return switch (this) {
            case Red -> "red";
            case Yellow -> "yellow";
            case Green -> "green";
            case Blue -> "blue";
            case Purple -> "light_purple";
            case Orange -> "gold";
            default -> RandomColors[new Random().nextInt(RandomColors.length)];
        };
    }
}
