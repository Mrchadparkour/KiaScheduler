package com.estheics_byki.dataComponents;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static com.estheics_byki.Utils.StrUtils.titleCase;

public class Day implements AppointmentHolder{
    private LocalDateTime date;
    private List<Appointment> appointments = new ArrayList<>();

    public Day(LocalDateTime date) {
        this.date = date;
    }

    public int getDayVal() {
        return date.getDayOfMonth();
    }

    public Month getMonth() {
        return date.getMonth();
    }

    public String fDate() {
        return titleCase(date.getDayOfMonth() + "");
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
        return titleCase(date.getDayOfWeek().toString());
    }
}
