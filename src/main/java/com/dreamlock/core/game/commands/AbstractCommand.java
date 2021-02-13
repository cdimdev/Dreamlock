package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.Sequence;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;
import java.util.List;
import java.util.Map;

abstract class AbstractCommand implements ICommand {

  public List<OutputMessage> execute(IGameContext gameContext) {
    return null;
  }

  public List<OutputMessage> execute(IGameContext gameContext, Map<Sequence, Word> words) { return null; }
}
