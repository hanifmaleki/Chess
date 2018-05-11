package de.tabit.chess.controller;

import de.tabit.chess.model.BoardStatus;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Created by e1528895 on 5/11/18.
 */
public class BoardValidatorImpl implements BoardValidator {

  @Override
  public boolean validate(BoardStatus boardStatus) throws BoardNotValidException {
    Map<Predicate<BoardStatus>, String> predicatesMap = BoardValidationFactory.getPredicates();

    for (Entry<Predicate<BoardStatus>, String> entry: predicatesMap.entrySet()){
      Predicate<BoardStatus> predicate = entry.getKey();
      if(!predicate.test(boardStatus))
        throw new BoardNotValidException(entry.getValue());
    }
    return true;
  }
}
