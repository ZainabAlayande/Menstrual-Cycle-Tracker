package data.models;

import java.time.LocalDate;
import java.util.List;

public class Cycle {

    private String id;
    private String userId;
    private LocalDate startDateOfMenstrualFlow;
    private int circularDays;
    private int numberOfFlowDays;
    private LocalDate ovulationDate;
    private List<Cycle> safeDates;
    private List<Cycle> dangerousDates;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getStartDateOfMenstrualFlow() {
        return startDateOfMenstrualFlow;
    }

    public void setStartDateOfMenstrualFlow(LocalDate startDateOfMenstrualFlow) {
        this.startDateOfMenstrualFlow = startDateOfMenstrualFlow;
    }

    public int getCircularDays() {
        return circularDays;
    }

    public void setCircularDays(int circularDays) {
        this.circularDays = circularDays;
    }

    public int getNumberOfFlowDays() {
        return numberOfFlowDays;
    }

    public void setNumberOfFlowDays(int numberOfFlowDays) {
        this.numberOfFlowDays = numberOfFlowDays;
    }

    public LocalDate getOvulationDate() {
        return ovulationDate;
    }

    public void setOvulationDate(LocalDate ovulationDate) {
        this.ovulationDate = LocalDate.ofEpochDay(numberOfFlowDays / 2);
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

    public void calculateOvulation(String numberOfFlowDays) {
        int flow = Integer.parseInt(numberOfFlowDays);
        this.ovulationDate = LocalDate.ofEpochDay(flow / 2);
    }

    @Override
    public String toString() {
        return "Cycle{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
