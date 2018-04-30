package com.estheics_byki.dataComponents;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ThirtyDay {
    private List<Week> weeks = new ArrayList<>();
    private int currIdx = 0;

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

        for (Week w : weeks) {
            System.out.println(w.genName());
            for (Day d : w.getDays()) {
                System.out.println( d.getWeekDay());
            }
        }
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public Week getCurrWeek() {
        return weeks.get(currIdx);
    }

    public int moveWeek(int dir) {
    //if dir is positive increase week else go back
        if (dir < 0 && currIdx < weeks.size() - 1) {
            //animates down
            currIdx++;
            return 1;
        } else if(currIdx > 0 && dir > 0) {
            //animates up
            currIdx--;
            return -1;
        } else {
            //don't move
            return 0;
        }
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
