package data.repositories;

import data.models.FemaleMenstrualHealth;

import java.util.List;

public interface FemaleMenstrualHealthRepository {
    FemaleMenstrualHealth save(FemaleMenstrualHealth femaleMenstrualHealthTwo);

    long count();

    void delete(FemaleMenstrualHealth femaleMenstrualHealth);

    void deleteFemaleMenstrualHealthById(int id);

    List<FemaleMenstrualHealth> viewAllFemaleMenstrualHealth();

    FemaleMenstrualHealth findMenstrualHealthById(int id);
}
