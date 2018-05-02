package com.estheics_byki.dataComponents;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static com.estheics_byki.Utils.StrUtils.titleCase;

public class Week {
    private List<Day> days = new ArrayList<>();
    private String name = null;

    private String fName(int idx, Month m) {
        String[] starts = new String[]{"First Week of ", "Second Week of ", "Third Week of ", "Last Week of "};
        return starts[idx] + titleCase(m.toString());
    }

    public String genName() {
        Month fm = days.get(0).getMonth();
        Month lm = days.get(days.size() - 1).getMonth();
        if (name == null) {
            //months are different but there are more days in first month -> last week of fm
            if (!lm.equals(fm)) this.name = fName(3, fm) + fName(0, lm);
            else if (days.size() < 4) this.name = days.get(0).getDayVal() < 27 ? "First Days of " + titleCase(lm.toString()) : "Last Days of " + titleCase(lm.toString());
            else {
                // all days have the same month
                int fDay = days.get(0).getDayVal();

                // first day of the week is undoubtedly in the last week
                System.out.println(fDay);
                if (fDay > 27) this.name = fName(3, fm);
                else {
                    int i = 1;
                    while (i * 7 <= 28) {
                        if (fDay < i * 7){
                            this.name = fName(i-1, fm);
                            break;
                        }
                        i++;
                    }
                }
            }

        }
        return name;
    }


    public Month mostInMonth() {
        Month fm = days.get(0).getMonth();
        Month lm = days.get(days.size() - 1).getMonth();
        if (fm.equals(lm)) return fm;
        int fSum = 0;
        int lSum = 0;
        for (Day d : days) {
            if (d.getMonth().equals(fm)){
                fSum++;
                continue;
            }
            lSum++;
        }

        return fSum > lSum ? fm : lm;
    }

    void add(Day d) {
       days.add(d);
    }

    public List<Day> getDays() {
        return days;
    }
}
