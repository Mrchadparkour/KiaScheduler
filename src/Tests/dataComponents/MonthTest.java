package dataComponents;

import com.estheics_byki.dataComponents.Month;
import com.estheics_byki.dataComponents.Week;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MonthTest {
    private Month month = new Month();

    @Test
    void hasValidWeeks() {
        System.out.println("Checking if your month has weeks...");
        for (Week w : month.getWeeks()) {
            if (w == null) System.out.println("Its missing some...");
            Assertions.assertNotNull(w);
        }
        System.out.println("It do!");
    }

    @Test
    void testMoveWeek() {
        System.out.println("If 0 entered, return 0");
        Assertions.assertEquals(0, month.moveWeek(0));

        System.out.println("Move forward");
        Assertions.assertEquals(1, month.moveWeek(-1));
        //cause it to go out of bounds
        System.out.println("Move forward");
        month.moveWeek(-1);
        System.out.println("Move forward");
        month.moveWeek(-1);
        System.out.println("Checking for the out of bounds check...");
        Assertions.assertEquals(0, month.moveWeek(-1));
        System.out.println("Can we move backward...");
        Assertions.assertEquals(-1, month.moveWeek(1));
        System.out.println("Move backward");
        month.moveWeek(1);
        System.out.println("Move backward");
        month.moveWeek(1);
        System.out.println("Checking for the out of bounds check...");
        //cause it to go out of bounds
        Assertions.assertEquals(0, month.moveWeek(1));

        System.out.println("All clear!");
    }
}
