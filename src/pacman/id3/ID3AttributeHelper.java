package pacman.id3;

import pacman.game.Constants;

public class ID3AttributeHelper {

    public static int getNumOfValues(ID3Attribute attribute) {
        switch (attribute) {
            // True False
            case IS_SUE_EDIBLE:
            case IS_INKY_EDIBLE:
            case IS_PINKY_EDIBLE:
            case IS_BLINKY_EDIBLE:
            case IS_GHOST_CLOSE:
            case IS_POWER_PIL_CLOSE:
            case IS_CLOSEST_GHOST_EDIBLE:
            case GHOST_WAS_EATEN:
                return 2;

            // Discrete tags
            case BLINKY_DIST:
            case INKY_DIST:
            case PINKY_DIST:
            case SUE_DIST:
            case DISTANCE_TO_CLOSEST_GHOST:
                return ID3DataTuple.DiscreteTag.values().length;

            // Other
            case HOW_MANY_GHOSTS_CLOSE:
                return 5;

            // Moves
            case DIRECTION_TO_CLOSEST_PIL:
            case DIRECTION_TO_CLOSEST_POWER_PIL:
            case MOVE_AWAY_FROM_THREAT:
                return Constants.MOVE.values().length;

            default:
                return 0;
        }
    }

    public static Object[] values(ID3Attribute attribute) {
        Object[] values = new String[getNumOfValues(attribute)];

        switch (attribute) {
            // Booleans need to be as strings
            case IS_SUE_EDIBLE:
            case IS_INKY_EDIBLE:
            case IS_PINKY_EDIBLE:
            case IS_BLINKY_EDIBLE:
            case IS_GHOST_CLOSE:
            case IS_POWER_PIL_CLOSE:
            case IS_CLOSEST_GHOST_EDIBLE:
            case GHOST_WAS_EATEN:
                values[0] = String.valueOf(true);
                values[1] = String.valueOf(false);
                return values;

            // Discrete tags
            case BLINKY_DIST:
            case INKY_DIST:
            case PINKY_DIST:
            case SUE_DIST:
            case DISTANCE_TO_CLOSEST_GHOST:
                return ID3DataTuple.DiscreteTag.values();

            case HOW_MANY_GHOSTS_CLOSE:
                values[0] = String.valueOf(0);
                values[1] = String.valueOf(1);
                values[2] = String.valueOf(2);
                values[3] = String.valueOf(3);
                values[4] = String.valueOf(4);
                return values;

                // Moves
            case DIRECTION_TO_CLOSEST_PIL:
            case DIRECTION_TO_CLOSEST_POWER_PIL:
            case MOVE_AWAY_FROM_THREAT:
                return Constants.MOVE.values();

            default:
                return values;
        }
    }

    public static Object valueFromDataTuple(ID3Attribute attribute, ID3DataTuple dataTuple) {
        switch (attribute) {
            // Booleans need to be as strings
            case IS_SUE_EDIBLE:
                return String.valueOf(dataTuple.isSueEdible);
            case IS_INKY_EDIBLE:
                return String.valueOf(dataTuple.isInkyEdible);
            case IS_PINKY_EDIBLE:
                return String.valueOf(dataTuple.isPinkyEdible);
            case IS_BLINKY_EDIBLE:
                return String.valueOf(dataTuple.isBlinkyEdible);
            case IS_GHOST_CLOSE:
                return String.valueOf(dataTuple.isGhostClose);
            case IS_POWER_PIL_CLOSE:
                return String.valueOf(dataTuple.isPowerPilClose);
            case IS_CLOSEST_GHOST_EDIBLE:
                return String.valueOf(dataTuple.isClosestGhostEdible);
            case GHOST_WAS_EATEN:
                return String.valueOf(dataTuple.ghostWasEaten);

            case BLINKY_DIST:
                return dataTuple.discretizeDistance(dataTuple.blinkyDist);
            case INKY_DIST:
                return dataTuple.discretizeDistance(dataTuple.inkyDist);
            case PINKY_DIST:
                return dataTuple.discretizeDistance(dataTuple.pinkyDist);
            case SUE_DIST:
                return dataTuple.discretizeDistance(dataTuple.sueDist);
            case DISTANCE_TO_CLOSEST_GHOST:
                return dataTuple.discretizeDistance(dataTuple.distanceToClosestGhost);
            case HOW_MANY_GHOSTS_CLOSE:
                return String.valueOf(dataTuple.howManyGhostsAreClose);

            // Moves
            case DIRECTION_TO_CLOSEST_PIL:
                return dataTuple.directionToClosestPil;
            case DIRECTION_TO_CLOSEST_POWER_PIL:
                return dataTuple.directionToClosestPowerPil;
            case MOVE_AWAY_FROM_THREAT:
                return dataTuple.getMoveAwayFromThreat;

            default:
                return null;
        }
    }

}
