package data.repositories;

import data.models.Cycle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CycleRepositoryImplTest {

    private CycleRepository cycleRepository;
    private Cycle cycle;
    private Cycle cycleTwo;
    private Cycle cycleThree;

    @BeforeEach
    public void setUp() {
        cycleRepository = new CycleRepositoryImpl();
        cycle = new Cycle();
        cycleTwo = new Cycle();
        cycleThree = new Cycle();

    }

    @Test
    public void saveOneUserCountIsOneTest() {
        cycleRepository.save(cycle);
        assertEquals(1, cycleRepository.count());
    }

    @Test public void saveTwoUserCountIsTwoTest() {
        cycleRepository.save(cycle);
        cycleRepository.save(cycleTwo);
        assertEquals(2, cycleRepository.count());
    }

    @Test public void saveThreeUserCountIsThreeTest() {
        cycleRepository.save(cycle);
        cycleRepository.save(cycleTwo);
        cycleRepository.save(cycleThree);
        assertEquals(3, cycleRepository.count());
    }

    @Test public void saveThreeUser_DeleteOne_CountIsTwoTest() {
        cycleRepository.save(cycle);
        cycleRepository.save(cycleTwo);
        cycleRepository.save(cycleThree);
        cycleRepository.delete(cycle);
        assertEquals(2, cycleRepository.count());
    }

    @Test public void saveThreeUser_DeleteById_CountIsTwoTest() {
        cycleRepository.save(cycle);
        cycleRepository.save(cycleTwo);
        cycleRepository.save(cycleThree);
        cycleRepository.deleteCycleById(1);
        assertEquals(2, cycleRepository.count());
    }

    @Test public void saveOneUser_FindUserByIdTest() {
        Cycle savedCycle = cycleRepository.save(cycle);
        Cycle savedCycleTwo = cycleRepository.save(cycleTwo);
        Cycle savedCycleThree = cycleRepository.save(cycleThree);
        assertEquals(3, cycleRepository.count());
        assertEquals("1", savedCycle.getId());
        assertEquals("2", savedCycleTwo.getId());
        assertEquals("3", savedCycleThree.getId());
        assertEquals(savedCycle, cycleRepository.findCycleById(1));
    }

    @Test public void viewAllSavedUserTest() {
        Cycle savedCycle = cycleRepository.save(cycle);
        String expected = "[Cycle{" +
                "id='" + savedCycle.getId() + '\'' +
                ", userId='" + savedCycle.getUserId() + '\'' +
                '}'+']';
        System.out.println(savedCycle.getId());
        System.out.println(savedCycle.getUserId());
        assertEquals(expected, cycleRepository.viewAllCycle().toString());
    }

}