package services;

import dtos.requests.CycleRequest;
import dtos.requests.SafeDates;
import dtos.responses.CycleResponse;
import dtos.responses.DangerousDates;
import utils.*;

import java.time.LocalDate;
import java.util.List;

public interface CycleService {
    List<SafeDates> safeDates(CycleRequest cycleRequest)
            throws InvalidFieldException, StartDateInTheFutureException, InvalidFlowPeriod, InvalidCircleLengthException, NegativeInputException, DateTooBackwardException;

    List<DangerousDates> dangerousDates(CycleRequest cycleRequest)
            throws StartDateInTheFutureException, InvalidFieldException, DateTooBackwardException, NegativeInputException, InvalidCircleLengthException, InvalidFlowPeriod;

    String ovulationDay(CycleRequest cycleRequest)
            throws StartDateInTheFutureException, InvalidFieldException, InvalidCircleLengthException, NegativeInputException, DateTooBackwardException, InvalidFlowPeriod;

    LocalDate nextMenstrualDate(CycleRequest cycleRequest)
            throws StartDateInTheFutureException, InvalidFieldException, NegativeInputException, InvalidCircleLengthException, DateTooBackwardException, InvalidFlowPeriod;
}
