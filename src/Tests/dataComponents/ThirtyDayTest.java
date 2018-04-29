package dataComponents;

import com.estheics_byki.dataComponents.Day;
import com.estheics_byki.dataComponents.ThirtyDay;
import com.estheics_byki.dataComponents.Week;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ThirtyDayTest {
    private ThirtyDay thirtyDay = new ThirtyDay(LocalDateTime.of(2018, 4, 29, 2, 37));


    @Test
    void hasValidWeeks() {
        System.out.println("Checking if your thirtyDay has weeks with days in the right place...");
        for (Week w : thirtyDay.getWeeks()) {
            System.out.println(w.genName());
            for (Day d : w.getDays()) {
                System.out.println( d.getWeekDay());
            }
        }

        List<Week> weeks = thirtyDay.getWeeks();
        Assertions.assertEquals("Last Week of April", weeks.get(0).genName());
        Assertions.assertEquals("First Week of May", weeks.get(1).genName());
        Assertions.assertEquals("Second Week of May", weeks.get(2).genName());
        Assertions.assertEquals("Third Week of May", weeks.get(3).genName());
        Assertions.assertEquals("Last Week of May", weeks.get(4).genName());


    }

    @Test
    void testMoveWeek() {
        System.out.println("If 0 entered, return 0");
        Assertions.assertEquals(0, thirtyDay.moveWeek(0));

        System.out.println("Move forward");
        Assertions.assertEquals(1, thirtyDay.moveWeek(-1));
        //cause it to go out of bounds
        System.out.println("Move forward");
        thirtyDay.moveWeek(-1);
        System.out.println("Move forward");
        thirtyDay.moveWeek(-1);
        System.out.println("Checking for the out of bounds check...");
        Assertions.assertEquals(0, thirtyDay.moveWeek(-1));
        System.out.println("Can we move backward...");
        Assertions.assertEquals(-1, thirtyDay.moveWeek(1));
        System.out.println("Move backward");
        thirtyDay.moveWeek(1);
        System.out.println("Move backward");
        thirtyDay.moveWeek(1);
        System.out.println("Checking for the out of bounds check...");
        //cause it to go out of bounds
        Assertions.assertEquals(0, thirtyDay.moveWeek(1));

        System.out.println("All clear!");
    }
}
