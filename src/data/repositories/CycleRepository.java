package data.repositories;

import data.models.Cycle;

import java.time.LocalDate;
import java.util.List;

public interface CycleRepository {
    long count();

    Cycle save(Cycle cycle);

    void delete(Cycle cycle);

    void deleteCycleById(int id);

    Cycle findCycleById(int id);

    List<Cycle> viewAllCycle();

    void appendSafeDate(LocalDate date);
    List<LocalDate> getSafeDates();

    void appendDangerousDate(LocalDate date);

    List<LocalDate> getDangerousDates();
}
