package sample.dataComponents;

import java.util.Date;

class Appointment {
    private Date createdAt;
    private Date scheduledFor;

    private String type;
    private String paymentType;

    //index of array -> correlates to the slot of hour based time slots
    private int timeSlot;
    private String note;

    //User attached to this appointment
    //COMBAK: change to a private string userKey, which will
    //be used to pull data from database
//    private User user;
}
