package com.estheics_byki.dataComponents;

import java.time.LocalDateTime;

public class Week {
    private String color;
    private Day[] days = new Day[7];

    private final static String[] dayNames = new String[]{ "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

    public Week(String color) {
        this.color = color;

//        for (int i=0;i<7;i++) days[i] = new Day(dayNames[i]);
    }

    public String getColor() {
        return color;
    }


    public void genDays() {
        System.out.println(LocalDateTime.now());
    }
}
