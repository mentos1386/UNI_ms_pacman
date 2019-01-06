package pacman.controllers;

import pacman.game.Constants;
import pacman.game.Game;

import java.util.EnumMap;

public class ID3Controller extends Controller<EnumMap<Constants.GHOST, Constants.MOVE>> {
    @Override
    public EnumMap<Constants.GHOST, Constants.MOVE> getMove(Game game, long timeDue) {
        return null;
    }
}
