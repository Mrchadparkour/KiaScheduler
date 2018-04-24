package sample.dataComponents;

import java.util.ArrayList;
import java.util.List;

public class Backlog implements AppointmentHolder {
    List<Appointment> appointments = new ArrayList<>();

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
}
