package dtos.requests;

import java.time.LocalDate;

public class CycleRequest {

    private String userId;
    private String startDay;
    private int flowDays;
    private String circularDays;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public int getFlowDays() {
        return flowDays;
    }

    public void setFlowDays(int flowDays) {
        this.flowDays = flowDays;
    }

    public String getCircularDays() {
        return circularDays;
    }

    public void setCircularDays(String circularDays) {
        this.circularDays = circularDays;
    }
}
