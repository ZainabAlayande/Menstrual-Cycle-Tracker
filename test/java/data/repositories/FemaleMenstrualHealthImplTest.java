package data.repositories;

import data.models.FemaleMenstrualHealth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FemaleMenstrualHealthImplTest {

    private FemaleMenstrualHealthRepository femaleMenstrualHealthRepository;
    private FemaleMenstrualHealth femaleMenstrualHealth;
    private FemaleMenstrualHealth femaleMenstrualHealthTwo;
    private FemaleMenstrualHealth femaleMenstrualHealthThree;

    @BeforeEach
    public void setUp() {
        femaleMenstrualHealthRepository = new FemaleMenstrualHealthImpl();
        femaleMenstrualHealth = new FemaleMenstrualHealth();
        femaleMenstrualHealthTwo = new FemaleMenstrualHealth();
        femaleMenstrualHealthThree = new FemaleMenstrualHealth();

    }

    @Test
    public void saveOneUserCountIsOneTest() {
        femaleMenstrualHealthRepository.save(femaleMenstrualHealth);
        assertEquals(1, femaleMenstrualHealthRepository.count());
    }

    @Test public void saveTwoUserCountIsTwoTest() {
        femaleMenstrualHealthRepository.save(femaleMenstrualHealth);
        femaleMenstrualHealthRepository.save(femaleMenstrualHealthTwo);
        assertEquals(2, femaleMenstrualHealthRepository.count());
    }

    @Test public void saveThreeUserCountIsThreeTest() {
        femaleMenstrualHealthRepository.save(femaleMenstrualHealth);
        femaleMenstrualHealthRepository.save(femaleMenstrualHealthTwo);
        femaleMenstrualHealthRepository.save(femaleMenstrualHealthThree);
        assertEquals(3, femaleMenstrualHealthRepository.count());
    }

    @Test public void saveThreeUser_DeleteOne_CountIsTwoTest() {
        femaleMenstrualHealthRepository.save(femaleMenstrualHealth);
        femaleMenstrualHealthRepository.save(femaleMenstrualHealthTwo);
        femaleMenstrualHealthRepository.save(femaleMenstrualHealthThree);
        femaleMenstrualHealthRepository.delete(femaleMenstrualHealth);
        assertEquals(2, femaleMenstrualHealthRepository.count());
    }

    @Test public void saveThreeUser_DeleteById_CountIsTwoTest() {
        femaleMenstrualHealthRepository.save(femaleMenstrualHealth);
        femaleMenstrualHealthRepository.save(femaleMenstrualHealthTwo);
        femaleMenstrualHealthRepository.save(femaleMenstrualHealthThree);
        femaleMenstrualHealthRepository.deleteFemaleMenstrualHealthById(1);
        assertEquals(2, femaleMenstrualHealthRepository.count());
    }

    @Test public void saveOneUser_FindUserByIdTest() {
        FemaleMenstrualHealth savedFemaleMenstrualHealth = femaleMenstrualHealthRepository.save(femaleMenstrualHealth);
        FemaleMenstrualHealth savedFemaleMenstrualHealthTwo = femaleMenstrualHealthRepository.save(femaleMenstrualHealthTwo);
        FemaleMenstrualHealth savedFemaleMenstrualHealthThree = femaleMenstrualHealthRepository.save(femaleMenstrualHealthThree);
        assertEquals(3, femaleMenstrualHealthRepository.count());
        assertEquals("1", savedFemaleMenstrualHealth.getId());
        assertEquals("2", savedFemaleMenstrualHealthTwo.getId());
        assertEquals("3", savedFemaleMenstrualHealthThree.getId());
        assertEquals(savedFemaleMenstrualHealth, femaleMenstrualHealthRepository.findMenstrualHealthById(1));
    }

    @Test public void viewAllSavedUserTest() {
        FemaleMenstrualHealth  savedFemaleMenstrualHealth = femaleMenstrualHealthRepository.save(femaleMenstrualHealth);
        String expected = "[FemaleMenstrualHealth{" +
                "id='" +  savedFemaleMenstrualHealth.getId() + '\'' +
                ", cycleId='" + savedFemaleMenstrualHealth.getCycleId() + '\'' +
                '}'+']';
        assertEquals(expected, femaleMenstrualHealthRepository.viewAllFemaleMenstrualHealth().toString());
    }


}