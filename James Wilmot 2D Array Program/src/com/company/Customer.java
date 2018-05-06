package com.company;

public class Customer
{
    private String name;
    private boolean isAdult;
    private Seat seat;

    Customer(String customerName, boolean customerIsAdult,
             Seat customerSeat)
    {
        name = customerName;
        isAdult = customerIsAdult;
        seat = customerSeat;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) { isAdult = adult; }

    public String getSeatClass() {
        return seat.getSeatClass();
    }

    public void setSeatClass(String seatClass) { this.seat.setSeatClass(seatClass);}

    public String getSeatPref() { return seat.getSeatPref(); }

    public void setSeatPref(String seatPref) { this.seat.setSeatPref(seatPref); }
}