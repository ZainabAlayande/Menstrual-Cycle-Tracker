package dtos.responses;

import java.time.LocalDate;

public class DangerousDates {

    private LocalDate date;

    public DangerousDates(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "" + date + "";
    }
}
