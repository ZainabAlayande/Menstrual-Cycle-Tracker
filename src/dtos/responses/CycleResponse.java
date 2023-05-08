package dtos.responses;

import data.models.Cycle;

import java.time.LocalDate;
import java.util.List;

public class CycleResponse {
    private LocalDate startDate;
    private String flowDays;
    private String circularDays;
    private String ovulationDate;
    private List<Cycle> safeDates;
    private List<Cycle> dangerousDates;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getFlowDays() {
        return flowDays;
    }

    public void setFlowDays(String flowDays) {
        this.flowDays = flowDays;
    }

    public String getCircularDays() {
        return circularDays;
    }

    public void setCircularDays(String circularDays) {
        this.circularDays = circularDays;
    }

    public String getOvulationDate() {
        return ovulationDate;
    }

    public void setOvulationDate(String ovulationDate) {
        this.ovulationDate = ovulationDate;
    }

    public List<Cycle> getSafeDates() {
        return safeDates;
    }

    public void setSafeDates(List<Cycle> safeDates) {
        this.safeDates = safeDates;
    }

    public List<Cycle> getDangerousDates() {
        return dangerousDates;
    }

    public void setDangerousDates(List<Cycle> dangerousDates) {
        this.dangerousDates = dangerousDates;
    }

    @Override
    public String toString() {
        return String.format("""
                ^^^^^^^^^^^^^^^^^^^^^^^^^^
                Start Date: %s
                Flow Days: %s
                Circular Days: %s
                Ovulation Date: %s
                Safe Dates: %s
                Dangerous Dates: %s
                """, getStartDate(), getFlowDays(), getCircularDays(), getOvulationDate(), getSafeDates(), getDangerousDates());
    }
}
