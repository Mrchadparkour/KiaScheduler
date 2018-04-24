package sample.dataComponents;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import sample.Utils.StrUtils;

public class Day implements AppointmentHolder{
    private LocalDateTime date;
    private List<Appointment> appointments = new ArrayList<>();

    public Day(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean addAppointment(Appointment app) {
        appointments.add(app);
        return true;
    }

    @Override
    public boolean removeAppointment(Appointment app) {
        if (appointments.contains(app)) {
            appointments.remove(app);
            return true;
        }
        System.out.println("No such appointment exists.");
        return false;
    }

    public String getWeekDay() {
        return StrUtils.titleCase(date.getDayOfWeek().toString());
    }
}
