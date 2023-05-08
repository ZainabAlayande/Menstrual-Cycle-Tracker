package controller;

import dtos.requests.CycleRequest;
import services.CycleService;
import services.CycleServiceImpl;
import utils.*;

import java.time.format.DateTimeParseException;

public class CycleController {

    CycleService cycleService = new CycleServiceImpl();

    public Object nextPeriodDate(CycleRequest cycleRequest){
        try {
            return cycleService.nextMenstrualDate(cycleRequest);
        } catch (InvalidFieldException | StartDateInTheFutureException | DateTimeParseException |
                 NegativeInputException | InvalidCircleLengthException | DateTooBackwardException | InvalidFlowPeriod exception) {
            return exception.getMessage();
        }

    }

    public Object ovulationDate(CycleRequest cycleRequest){
        try {
            return cycleService.ovulationDay(cycleRequest);
        } catch (StartDateInTheFutureException | InvalidFieldException | InvalidCircleLengthException |
                 NegativeInputException | DateTimeParseException | DateTooBackwardException | InvalidFlowPeriod exception) {
            return exception.getMessage();
        }
    }

    public Object dangerousDates(CycleRequest cycleRequest){
        try {
            return cycleService.dangerousDates(cycleRequest);
        } catch (StartDateInTheFutureException | InvalidFieldException | DateTimeParseException |
                 DateTooBackwardException | NegativeInputException | InvalidCircleLengthException | InvalidFlowPeriod exception) {
            return exception.getMessage();
        }
    }

    public Object safeDates(CycleRequest cycleRequest){
        try {
            return cycleService.safeDates(cycleRequest);
        } catch (StartDateInTheFutureException | InvalidFieldException | InvalidCircleLengthException |
                 NegativeInputException | InvalidFlowPeriod | DateTimeParseException | DateTooBackwardException exception) {
            return exception.getMessage();
        }
    }
}
