package services;

import dtos.requests.CycleRequest;
import org.junit.jupiter.api.Test;
import utils.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class CycleServiceImplTest {
    private CycleService cycleService = new CycleServiceImpl();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String userInput = "01/04/2022";
    private LocalDate localDate = LocalDate.parse(userInput, dateTimeFormatter);


    @Test
    void calculateNextDateOfPeriod() {
        CycleRequest cycleRequest = new CycleRequest();
        cycleRequest.setStartDay(userInput);
        cycleRequest.setCircularDays("28");
        LocalDate nextPeriodDate = null;
        try {
            nextPeriodDate = cycleService.nextMenstrualDate(cycleRequest);
        } catch (StartDateInTheFutureException | InvalidFieldException | NegativeInputException |
                 InvalidCircleLengthException | DateTooBackwardException | InvalidFlowPeriod exception) {
            System.out.println(exception.getMessage());;
        }
        LocalDate expected = LocalDate.parse(localDate.format(DateTimeFormatter.ofPattern("2022-04-29")));
        assertEquals(expected, nextPeriodDate);
    }

    @Test
    void calculateOvulationDay() {
        CycleRequest cycleRequest = new CycleRequest();
        cycleRequest.setCircularDays("28");
        cycleRequest.setStartDay("01/05/2023");
        String expected = "14/05/2023";
        try {
            assertEquals(expected, cycleService.ovulationDay(cycleRequest));
        } catch (StartDateInTheFutureException | InvalidCircleLengthException | InvalidFieldException |
                 NegativeInputException | DateTooBackwardException | InvalidFlowPeriod exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void nextMenstrualDateTest() {
        CycleRequest cycleRequest = new CycleRequest();
        cycleRequest.setCircularDays("28");
        cycleRequest.setStartDay("01/05/2023");
        String expected = "2023-05-29";
        try {
            assertEquals(expected, cycleService.nextMenstrualDate(cycleRequest).toString());
        } catch (StartDateInTheFutureException | InvalidFieldException | NegativeInputException |
                 InvalidCircleLengthException | DateTooBackwardException | InvalidFlowPeriod exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void getDangerousDatesTest() {
        CycleRequest cycleRequest = new CycleRequest();
        cycleRequest.setCircularDays("30");
        cycleRequest.setStartDay("01/05/2023");
        cycleRequest.setFlowDays(3);
        String expected = "[2023-05-09, 2023-05-10, 2023-05-11, 2023-05-12, 2023-05-13, 2023-05-14, 2023-05-15, 2023-05-16, 2023-05-17]";
        try {
            assertEquals(expected, cycleService.dangerousDates(cycleRequest).toString());
        } catch (StartDateInTheFutureException | InvalidFieldException | DateTooBackwardException |
                 NegativeInputException | InvalidCircleLengthException | InvalidFlowPeriod exception) {
            System.out.println(exception.getMessage());
        }

    }

    @Test
    void getSafeDates() {
        CycleRequest cycleRequest = new CycleRequest();
        cycleRequest.setCircularDays("28");
        cycleRequest.setStartDay("01/05/2023");
        cycleRequest.setFlowDays(3);
        String expected = "[2023-05-04, 2023-05-05, 2023-05-06, 2023-05-07, 2023-05-08, 2023-05-18, 2023-05-19, 2023-05-20, 2023-05-21, 2023-05-22, " +
                "2023-05-23, 2023-05-24, 2023-05-25, 2023-05-26, 2023-05-27, 2023-05-28]";
        try {
            assertEquals(expected, cycleService.safeDates(cycleRequest).toString());
        } catch (InvalidFieldException | StartDateInTheFutureException | InvalidCircleLengthException |
                 InvalidFlowPeriod | NegativeInputException | DateTooBackwardException exception) {
            System.out.println(exception.getMessage());
        }
    }

    }
