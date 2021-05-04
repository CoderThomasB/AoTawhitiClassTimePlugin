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
        switch (this) {
            case Red, Yellow, Green, Blue, Purple:
                return false;
            case NoSchool, EndOfSchool, BeforeSchool, MorningBreak, Lunch, HomeBase, Orange:
                return true;
        }
        throw new RuntimeException("Unknown TimeBlock : " + this);
    }

    public String toDisplayString() {
        switch (this) {
            case HomeBase:
                return "Home base";
            case EndOfSchool:
                return "End of school";
            case BeforeSchool:
                return "Before school";
            case MorningBreak:
                return "Morning break";
            case Lunch:
                return "Lunch";
            case NoSchool:
                return "No school today";
            case Orange:
                return "Friday Hapori";


            case Red:
            case Blue:
            case Green:
            case Purple:
            case Yellow:
                return String.format("%s block", this);
        }

        throw new RuntimeException("Can not get display string for: " + this);
    }

    public int GetBossBarColor() {
        switch (this) {
            case Red:
                return 2;
            case Yellow, Orange:
                return 4;
            case Green:
                return 3;
            case Blue:
                return 1;
            case Purple:
                return 5;

            default:
                return new Random().nextInt(7);
        }
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

        switch (this) {
            case Red:
                return "red";
            case Yellow:
                return "yellow";
            case Green:
                return "green";
            case Blue:
                return "blue";
            case Purple:
                return "light_purple";
            case Orange:
                return "gold";

            default:
                return RandomColors[new Random().nextInt(RandomColors.length)];
        }
    }
}
