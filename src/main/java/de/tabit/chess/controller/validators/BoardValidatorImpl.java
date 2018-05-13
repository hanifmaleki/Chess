package de.tabit.chess.controller.validators;

import de.tabit.chess.model.BoardStatus;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;

/** Created by Hanif Maleki on 5/11/18. The implementation of the {BoardValidator} */
@Slf4j
public class BoardValidatorImpl implements BoardValidator {

  @Override
  public boolean validate(BoardStatus boardStatus) throws BoardNotValidException {
    log.debug("Checking the validation of the loading board status");
    Map<Predicate<BoardStatus>, String> predicatesMap = BoardValidationFactory.getPredicates();
    for (Entry<Predicate<BoardStatus>, String> entry : predicatesMap.entrySet()) {
      Predicate<BoardStatus> predicate = entry.getKey();
      if (!predicate.test(boardStatus)) {
        log.error(entry.getValue());
        throw new BoardNotValidException(entry.getValue());
      }
    }
    log.debug("The board status is valid.");
    return true;
  }
}
