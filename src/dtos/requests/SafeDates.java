package dtos.requests;

import java.time.LocalDate;

public class SafeDates {
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public SafeDates(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "" + date + "";
    }
}
