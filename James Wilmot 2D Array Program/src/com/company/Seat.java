package com.company;

public class Seat {

    private String seatClass;
    private String seatPref;

    Seat(String seatRow, String seatCol) {
        seatClass = seatRow;
        seatPref = seatCol;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getSeatPref() {
        return seatPref;
    }

    public void setSeatPref(String seatPref) {
        this.seatPref = seatPref;
    }
}
