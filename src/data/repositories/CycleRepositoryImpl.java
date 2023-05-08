package data.repositories;

import data.models.Cycle;
import data.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CycleRepositoryImpl implements CycleRepository {

    private int count;
    private List<Cycle> cycles = new ArrayList<>();
    private List<LocalDate> safeDates = new ArrayList<>();
    private List<LocalDate> dangerousDate = new ArrayList<>();
    private UserRepository userRepository = new UserRepositoryImpl();


    @Override
    public long count() {
        return count;
    }

    @Override
    public Cycle save(Cycle cycle) {
        User user = new User();


        boolean cycleHasNotBeenSaved = cycle.getId() == null;
        if (cycleHasNotBeenSaved)
            cycle.setId(String.valueOf(generateCycleId()));
        //cycle.setUserId(user.getId());
        cycles.add(cycle);
        count++;
        return cycle;
    }

    private int generateCycleId() {
        return count + 1;
    }

    @Override
    public void delete(Cycle cycle) {
        for (Cycle cycle1: cycles) {
            if (cycle1.equals(cycle))
                cycles.remove(cycle1);
            break;
        }
        count--;

    }

    @Override
    public void deleteCycleById(int id) {
        for (Cycle cycle: cycles) {
            if (Objects.equals(cycle.getId(), String.valueOf(id)))
                cycles.remove(cycle);
            break;
        }
        count--;

    }

    @Override
    public Cycle findCycleById(int id) {
        for (Cycle cycle : cycles) {
            if (Objects.equals(cycle.getId(), String.valueOf(id)))
                return cycle;
        }
        return null;
    }



    @Override
    public List<Cycle> viewAllCycle() {
        return cycles;
    }

    @Override
    public void appendSafeDate(LocalDate date) {
        safeDates.add(date);
    }

    @Override
    public List<LocalDate> getSafeDates() {
        return safeDates;
    }

    private void addTitle(String message) {
        System.out.println(message);
    }

    private void addNewLine() {
        System.out.println();
    }
    @Override
    public void appendDangerousDate(LocalDate date) {
        dangerousDate.add(date);

    }

    @Override
    public List<LocalDate> getDangerousDates() {
        for (LocalDate dangerousDate : dangerousDate) {
            addNewLine();
            return Collections.singletonList(dangerousDate);
        }
        return null;
    }
}
