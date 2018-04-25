package dataComponents;

import com.estheics_byki.dataComponents.Day;
import com.estheics_byki.dataComponents.Week;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeekTest {

    private Week week = new Week();

    @Test
    void testWeekName() {
        System.out.println("It should look like April 23 - April 29");
        System.out.println("It looks like: " + week.genName());
        Assertions.assertEquals("April 23 - April 29", week.genName());
        System.out.println("All clear.");
    }

    @Test
    void itHasDays() {
        System.out.println("This is checking that it both has days, and checking the genDays function");
        System.out.println("Checking if your week has days...");
        for (Day d : week.getDays()) {
            if (d == null) System.out.println("Its missing some :p");
            Assertions.assertNotNull(d);
        }
        System.out.println("It do!");
    }

    @Test
    void checkWeekDayNames() {
        String[] dayNames = new String[]{ "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        int i=0;
        System.out.println("\nDo we have them in the right order?");
        for (Day d : week.getDays()) {
            if (!dayNames[i].equals(d.getWeekDay())) System.out.println("Nope...");
            Assertions.assertEquals(dayNames[i], d.getWeekDay());
            i++;
        }
        System.out.println("We do!");
    }
}
