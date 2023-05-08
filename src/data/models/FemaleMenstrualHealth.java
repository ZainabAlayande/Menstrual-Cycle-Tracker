package data.models;

public class FemaleMenstrualHealth {

    private String id;
    private String cycleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCycleId() {
        return cycleId;
    }

    public void setCycleId(String cycleId) {
        this.cycleId = cycleId;
    }

    @Override
    public String toString() {
        return "FemaleMenstrualHealth{" +
                "id='" + id + '\'' +
                ", cycleId='" + cycleId + '\'' +
                '}';
    }
}
