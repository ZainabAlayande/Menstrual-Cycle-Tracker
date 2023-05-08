package data.repositories;

import data.models.FemaleMenstrualHealth;
import data.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FemaleMenstrualHealthImpl implements FemaleMenstrualHealthRepository {

    private int count;
    private List<FemaleMenstrualHealth> femaleMenstrualHealths = new ArrayList<>();

    @Override
    public FemaleMenstrualHealth save(FemaleMenstrualHealth femaleMenstrualHealth) {
        boolean femaleMenstrualHealthHasNotBeenSaved = femaleMenstrualHealth.getId() == null;
        if (femaleMenstrualHealthHasNotBeenSaved)
            femaleMenstrualHealth.setId(String.valueOf(generateFemaleMenstrualHealthId()));
        femaleMenstrualHealths.add(femaleMenstrualHealth);
        count++;
        return femaleMenstrualHealth;
    }

    private int generateFemaleMenstrualHealthId() {
        return count + 1;
    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public void delete(FemaleMenstrualHealth femaleMenstrualHealth) {
        for (FemaleMenstrualHealth femaleMenstrualHealth1: femaleMenstrualHealths) {
            if (femaleMenstrualHealth1.equals(femaleMenstrualHealth))
                femaleMenstrualHealths.remove(femaleMenstrualHealth1);
            break;
        }
        count--;

    }

    @Override
    public void deleteFemaleMenstrualHealthById(int id) {
        for (FemaleMenstrualHealth femaleMenstrualHealth: femaleMenstrualHealths) {
            if (Objects.equals(femaleMenstrualHealth.getId(), String.valueOf(id)))
                femaleMenstrualHealths.remove(femaleMenstrualHealth);
            break;
        }
        count--;
    }


    @Override
    public List<FemaleMenstrualHealth> viewAllFemaleMenstrualHealth() {
        return femaleMenstrualHealths;
    }

    @Override
    public FemaleMenstrualHealth findMenstrualHealthById(int id) {
        for (FemaleMenstrualHealth femaleMenstrualHealth : femaleMenstrualHealths) {
            if (Objects.equals(femaleMenstrualHealth.getId(), String.valueOf(id)))
                return femaleMenstrualHealth;
        }
        return null;
    }
}
