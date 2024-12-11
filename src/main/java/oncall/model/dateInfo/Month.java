package oncall.model.dateInfo;

import java.util.Arrays;

public enum Month {
    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    private final int ordinal;
    private final int dateCount;

    Month(int ordinal, int dateCount) {
        this.ordinal = ordinal;
        this.dateCount = dateCount;
    }

    public static Month findByOrdinal(int ordinal) {
        return Arrays.stream(Month.values())
                .filter(m -> m.getOrdinal() == ordinal)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public int getOrdinal() {
        return ordinal;
    }

    public int getDateCount() {
        return dateCount;
    }
}
