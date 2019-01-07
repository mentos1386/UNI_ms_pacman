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
                return 2;

            // Discrete tags
            case BLINKY_DIST:
            case INKY_DIST:
            case PINKY_DIST:
            case SUE_DIST:
                return ID3DataTuple.DiscreteTag.values().length;

            // Moves
            case DIRECTION_TO_CLOSEST_PIL:
            case DIRECTION_TO_CLOSEST_POWER_PIL:
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
                values[0] = String.valueOf(true);
                values[1] = String.valueOf(false);
                return values;

            // Discrete tags
            case BLINKY_DIST:
            case INKY_DIST:
            case PINKY_DIST:
            case SUE_DIST:
                return ID3DataTuple.DiscreteTag.values();

            // Moves
            case DIRECTION_TO_CLOSEST_PIL:
            case DIRECTION_TO_CLOSEST_POWER_PIL:
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

            case BLINKY_DIST:
                return dataTuple.discretizeDistance(dataTuple.blinkyDist);
            case INKY_DIST:
                return dataTuple.discretizeDistance(dataTuple.inkyDist);
            case PINKY_DIST:
                return dataTuple.discretizeDistance(dataTuple.pinkyDist);
            case SUE_DIST:
                return dataTuple.discretizeDistance(dataTuple.sueDist);

            // Moves
            case DIRECTION_TO_CLOSEST_PIL:
                return dataTuple.directionToClosestPil;
            case DIRECTION_TO_CLOSEST_POWER_PIL:
                return dataTuple.directionToClosestPowerPil;

            default:
                return null;
        }
    }

}
