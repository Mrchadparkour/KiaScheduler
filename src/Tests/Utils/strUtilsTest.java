package Utils;

import com.estheics_byki.Utils.StrUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class strUtilsTest {
    @Test
    void testTitleCase() {
        Assertions.assertEquals("Monday", StrUtils.titleCase("MOnDay"));
        Assertions.assertEquals("Monday", StrUtils.titleCase("monday"));
        Assertions.assertEquals("Tuesday", StrUtils.titleCase("tUesDay"));
        Assertions.assertEquals("March", StrUtils.titleCase("MArcH"));
        Assertions.assertEquals("Week 1", StrUtils.titleCase("wEEK 1"));
    }
}
