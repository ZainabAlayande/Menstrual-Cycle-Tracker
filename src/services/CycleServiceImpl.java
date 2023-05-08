package services;

import data.repositories.CycleRepository;
import data.repositories.CycleRepositoryImpl;
import dtos.requests.CycleRequest;
import dtos.requests.SafeDates;
import dtos.responses.DangerousDates;
import utils.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CycleServiceImpl implements CycleService{
    private CycleRepository cycleRepository = new CycleRepositoryImpl();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate endDateOfDangerousDate;

    @Override
    public List<SafeDates> safeDates(CycleRequest cycleRequest) throws InvalidFieldException, StartDateInTheFutureException, InvalidFlowPeriod, InvalidCircleLengthException, NegativeInputException, DateTooBackwardException {
        validateDateCannotBeInTheFuture(cycleRequest);
        validateDateCannotBeLaterThan_35_Days_Backwards(cycleRequest);
        validateInvalidInput(cycleRequest);
        validateFlowPeriodIsBetween2_And_7_Days(cycleRequest);
        validateCycleLengthIsBetween21_And_35_Days(cycleRequest);

        String startDateOfPeriod = cycleRequest.getStartDay();
        int periodFlowDays = cycleRequest.getFlowDays();
        String cycle = cycleRequest.getCircularDays().trim();

        LocalDate firstLengthOfSafePeriod = LocalDate.parse(startDateOfPeriod, dateTimeFormatter).plusDays(8);
        LocalDate startDateOfSafeDate = LocalDate.parse(startDateOfPeriod, dateTimeFormatter).plusDays(periodFlowDays);
        List<SafeDates> safeDates = new ArrayList<>();

        for (LocalDate date = startDateOfSafeDate; date.isBefore(firstLengthOfSafePeriod);
             date = date.plusDays(1)) {
            SafeDates safeDate = new SafeDates(date);
            safeDates.add(safeDate);
         }

        LocalDate secondLengthOfCycle = LocalDate.parse(startDateOfPeriod, dateTimeFormatter).plusDays(17);
        LocalDate fullLengthOfCycle = LocalDate.parse(startDateOfPeriod, dateTimeFormatter).plusDays(Long.parseLong(cycle));

        for (LocalDate date = secondLengthOfCycle; date.isBefore(fullLengthOfCycle);
             date = date.plusDays(1)) {
            SafeDates safeDate = new SafeDates(date);
            safeDates.add(safeDate);
        }
        return safeDates;
    }

    @Override
    public List<DangerousDates> dangerousDates(CycleRequest cycleRequest) throws StartDateInTheFutureException, InvalidFieldException, DateTooBackwardException, NegativeInputException, InvalidCircleLengthException, InvalidFlowPeriod {
        validateDateCannotBeInTheFuture(cycleRequest);
        validateDateCannotBeLaterThan_35_Days_Backwards(cycleRequest);
        validateInvalidInput(cycleRequest);

        String startDateOfPeriod = cycleRequest.getStartDay();
        LocalDate startDateOfDangerousDate = LocalDate.parse(startDateOfPeriod, dateTimeFormatter).plusDays(8);
        endDateOfDangerousDate = startDateOfDangerousDate.plusDays(9);

        List<DangerousDates> dangerousDates = new ArrayList<>();
        for (LocalDate date = startDateOfDangerousDate; date.isBefore(endDateOfDangerousDate);
             date = date.plusDays(1)) {
            DangerousDates dto = new DangerousDates(date);
            dangerousDates.add(dto);
        }
        return dangerousDates;
    }


    @Override
    public String ovulationDay(CycleRequest cycleRequest) throws StartDateInTheFutureException, InvalidFieldException, InvalidCircleLengthException, NegativeInputException, DateTooBackwardException, InvalidFlowPeriod {
        validateDateCannotBeInTheFuture(cycleRequest);
        validateDateCannotBeLaterThan_35_Days_Backwards(cycleRequest);
        validateCycleLengthIsBetween21_And_35_Days(cycleRequest);
        validateInvalidInput(cycleRequest);
        validateFlowPeriodIsBetween2_And_7_Days(cycleRequest);

        String startDateOfPeriod = cycleRequest.getStartDay().trim();
        int cycleLength = Integer.parseInt(cycleRequest.getCircularDays().trim());

        LocalDate localDate = LocalDate.parse(startDateOfPeriod, dateTimeFormatter)
                .plusDays(cycleLength - 1);

        int halfOfCycleLength = cycleLength / 2;
        LocalDate ovulationDay = localDate.minusDays(halfOfCycleLength);
        return ovulationDay.format(dateTimeFormatter);
    }


    @Override
    public LocalDate nextMenstrualDate(CycleRequest cycleRequest) throws StartDateInTheFutureException, InvalidFieldException,
            NegativeInputException, InvalidCircleLengthException, DateTooBackwardException, InvalidFlowPeriod {

        validateInvalidInput(cycleRequest);
        validateDateCannotBeInTheFuture(cycleRequest);
        validateDateCannotBeLaterThan_35_Days_Backwards(cycleRequest);
        validateCycleLengthIsBetween21_And_35_Days(cycleRequest);

        String startDateFromUser = cycleRequest.getStartDay().trim();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(startDateFromUser, dateTimeFormatter);

        int cycleLength = Integer.parseInt(cycleRequest.getCircularDays());
        return localDate.plusDays(cycleLength);
    }

    private void validateDateCannotBeInTheFuture(CycleRequest cycleRequest) throws StartDateInTheFutureException {
        LocalDate todaysDate = LocalDate.now();
        String userStartDate = cycleRequest.getStartDay().trim();
        LocalDate userLocalDate = LocalDate.parse(userStartDate, dateTimeFormatter);

        if (userLocalDate.isAfter(todaysDate))
            throw new StartDateInTheFutureException("Start date cannot be in the future");
    }

    private void validateInvalidInput(CycleRequest cycleRequest) throws InvalidFieldException, NegativeInputException {
        //String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
        String regex = "^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$";

        String userInput = cycleRequest.getStartDay().trim();
        LocalDate localDate = LocalDate.parse(userInput, dateTimeFormatter);

        if (!cycleRequest.getStartDay().matches(regex)) {
            throw new InvalidFieldException("Invalid Date. Date must be in the format dd/MM/yyyy");
        }

        if (localDate.isBefore(LocalDate.of(1, 1, 1)))
            throw new NegativeInputException("Invalid Date. Date cannot be negative");

        int year = localDate.getYear();
        if (userInput.startsWith(String.valueOf(year))) {
            throw new InvalidFieldException("Invalid Date. Year cannot be at the beginning of the date");
        }

    }

    private void validateFlowPeriodIsBetween2_And_7_Days(CycleRequest cycleRequest) throws InvalidFlowPeriod, NegativeInputException {
        int flowPeriod = cycleRequest.getFlowDays();

        if (flowPeriod < 0)
            throw new NegativeInputException("Negative input not valid");

        if (flowPeriod < 2 || flowPeriod > 7)
            throw new InvalidFlowPeriod("Flow period can only be between 2 and 7 days");


    }

    private void validateCycleLengthIsBetween21_And_35_Days(CycleRequest cycleRequest) throws InvalidCircleLengthException, NegativeInputException {
        int cycleLength = Integer.parseInt(cycleRequest.getCircularDays().trim());

        if (cycleLength < 0)
            throw new NegativeInputException("Negative input not valid");

        if ( cycleLength < 21 || cycleLength > 35)
            throw new InvalidCircleLengthException("Invalid Cycle Length");
    }

    private void validateDateCannotBeLaterThan_35_Days_Backwards(CycleRequest cycleRequest) throws DateTooBackwardException {
        String startDate = cycleRequest.getStartDay().trim();
        LocalDate todaysDate = LocalDate.now();

        LocalDate localStartDate = LocalDate.parse(startDate, dateTimeFormatter);
        LocalDate thirtyFiveDaysBackward = todaysDate.minusDays(35);

        if (localStartDate.isBefore(thirtyFiveDaysBackward))
            throw new DateTooBackwardException("Start date too backward");

    }

 }
