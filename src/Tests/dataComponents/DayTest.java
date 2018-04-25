package dataComponents;

import com.estheics_byki.dataComponents.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DayTest {
    private Day day = new Day(LocalDateTime.of(2018, 4, 25, 12, 14));

    @Test
    void testFDate() {
        System.out.println("Should look like: April 25, 2018");
        System.out.println("It looks like: " + day.fDate());
        Assertions.assertEquals("April 25, 2018", day.fDate());
        System.out.println("All clear!");
    }

}
