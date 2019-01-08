package pacman.id3;

import pacman.game.Constants;
import pacman.game.Game;

public class ID3LabelHelper {


    public static ID3Label fromGameAndMove(Game game, Constants.MOVE move) {

        if (game.isGhostClose(ID3Constants.DISTANCE_TOLERANCE_GHOSTS)) {
            if (game.directionToClosestGhost().equals(move))
                return ID3Label.TOWARD_GHOST;
            return ID3Label.AWAY_FROM_GHOST;
        }

        if (game.isPowerPillClose(ID3Constants.DISTANCE_TOLERANCE_POWER_PILL)) {
            if (game.directionToClosestPowerPill().equals(move))
                return ID3Label.TOWARD_POWER_PILL;
            return ID3Label.AWAY_FROM_POWER_PILL;
        }


//        int pacmanNodeIndex = game.getPacmanCurrentNodeIndex();
//
//
//        if (game.isGhostClose(ID3Constants.DISTANCE_TOLERANCE_GHOSTS)) {
//            if (
//                    game.getShortestPathDistance(pacmanNodeIndex, game.getGhostCurrentNodeIndex(game.closestGhost())) >
//                            game.getShortestPathDistance(pacmanNodeIndex, game.getGhostCurrentNodeIndex(game.closestGhost()), move)
//            )
//                return ID3Label.TOWARD_GHOST;
//            return ID3Label.AWAY_FROM_GHOST;
//        }
//
//        int closestPowerPill = game.closestPowerPill();
//
//        if (game.isPowerPillClose(ID3Constants.DISTANCE_TOLERANCE_POWER_PILL) && closestPowerPill != -1) {
//            if (
//                    game.getShortestPathDistance(pacmanNodeIndex, closestPowerPill) >
//                            game.getShortestPathDistance(pacmanNodeIndex, closestPowerPill, move)
//            )
//                return ID3Label.TOWARD_POWER_PILL;
//            return ID3Label.AWAY_FROM_POWER_PILL;
//        }

        if (game.iPillClose(ID3Constants.DISTANCE_TOLERANCE)) {
            if (game.directionToClosestPill().equals(move))
                return ID3Label.TOWARD_PILL;
            return ID3Label.AWAY_FROM_PILL;
        }

        switch (move) {
            case DOWN:
                return ID3Label.DOWN;
            case UP:
                return ID3Label.UP;
            case LEFT:
                return ID3Label.LEFT;
            case RIGHT:
                return ID3Label.RIGHT;

            default:
                return ID3Label.NEUTRAL;
        }
    }
}
