package com.estheics_byki.dataComponents;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ThirtyDay {
    private List<Week> weeks = new ArrayList<>();

    public ThirtyDay() {
        LocalDateTime today = LocalDateTime.now();
        Week currWeek = new Week();
        for (long i=0; i<30;i++) {
            LocalDateTime day = today.plusDays(i);
            String dayName = day.getDayOfWeek().toString();
            if (dayName.equals("MONDAY") && currWeek.getDays().size() > 0) {
                weeks.add(currWeek);
                currWeek = new Week();
            }
            currWeek.add(new Day(day));
        }
        weeks.add(currWeek);
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    //For Testing only
    public ThirtyDay(LocalDateTime today) {
//        LocalDateTime today = LocalDateTime.now();
        Week currWeek = new Week();
        for (long i=0; i<30;i++) {
            LocalDateTime day = today.plusDays(i);
            String dayName = day.getDayOfWeek().toString();
            if (dayName.equals("MONDAY") && currWeek.getDays().size() > 0) {
                weeks.add(currWeek);
                currWeek = new Week();
            }
            currWeek.add(new Day(day));
        }
    }

}
