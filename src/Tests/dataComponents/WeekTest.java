package dataComponents;

import com.estheics_byki.dataComponents.Day;
import com.estheics_byki.dataComponents.ThirtyDay;
import com.estheics_byki.dataComponents.Week;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class WeekTest {
    private ThirtyDay thirtyDay = new ThirtyDay(LocalDateTime.of(2018, 4, 29, 2, 37));
    private Week week1 = thirtyDay.getWeeks().get(0);
    private Week week2 = thirtyDay.getWeeks().get(1);

    @Test
    void testMostInMonthWeek1() {
        Assertions.assertEquals("APRIL", week1.mostInMonth().toString());
        Assertions.assertEquals("MAY", week2.mostInMonth().toString());

    }
//    private Week week = new Week(LocalDateTime.of(2018, 4, 23, 1, 31));
//
//    @Test
//    void testWeekName() {
//        System.out.println("It should look like April 23 - April 29");
//        System.out.println("It looks like: " + week.genName());
//        Assertions.assertEquals("April 23 - April 29", week.genName());
//        System.out.println("All clear.");
//    }
//
//    @Test
//    void itHasDays() {
//        System.out.println("This is checking that it both has days, and checking the genDays function");
//        System.out.println("Checking if your week has days...");
//        for (Day d : week.getDays()) {
//            if (d == null) System.out.println("Its missing some :p");
//            Assertions.assertNotNull(d);
//        }
//        System.out.println("It do!");
//    }
//
//    @Test
//    void checkWeekDayNames() {
//        String[] dayNames = new String[]{ "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
//        int i=0;
//        System.out.println("\nDo we have them in the right order?");
//        for (Day d : week.getDays()) {
//            if (!dayNames[i].equals(d.getWeekDay())) System.out.println("Nope...");
//            Assertions.assertEquals(dayNames[i], d.getWeekDay());
//            i++;
//        }
//        System.out.println("We do!");
//    }
}
