package com.estheics_byki.dataComponents;

import java.time.LocalDateTime;

import static com.estheics_byki.Utils.StrUtils.titleCase;

public class Week {
    private Day[] days = new Day[7];
    private String name;

    private final static String[] dayNames = new String[]{ "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

    public Week() {
        genDays();
    }

    void genDays() {
        LocalDateTime pDate = LocalDateTime.now();
        String today = titleCase(pDate.getDayOfWeek().toString());
        int tIdx = java.util.Arrays.asList(dayNames).indexOf(today);
        for (int i=0;i<dayNames.length;i++) days[i] = new Day(pDate.minusDays(tIdx - i));
    }

    void genDays(LocalDateTime ldt) {
        String today = titleCase(ldt.getDayOfWeek().toString());
        int tIdx = java.util.Arrays.asList(dayNames).indexOf(today);
        for (int i=0;i<dayNames.length;i++) days[i] = new Day(ldt.minusDays(tIdx - i));
    }

    public String genName() {
        return days[0].fDate() + " - " + days[6].fDate();
    }

    public Day[] getDays() {
        return days;
    }
}
