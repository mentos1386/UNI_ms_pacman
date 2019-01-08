package pacman.id3;

import pacman.controllers.Controller;
import pacman.game.Constants;
import pacman.game.Game;

import java.util.ArrayList;

public class ID3Controller extends Controller<Constants.MOVE> {
    private ID3Node rootNode;
    private Constants.MOVE pacManMove = Constants.MOVE.NEUTRAL;

    public ID3Controller() {
        super();

        //Initialize tuples- and attributeList.
        ID3DataTuple[] trainingTuples = ID3DataSaverLoader.LoadPacManTrainingData();
        ID3DataTuple[] testTuples = ID3DataSaverLoader.LoadPacManTestData();

        ArrayList<ID3Attribute> attributes = new ArrayList<>();
//        attributes.add(ID3Attribute.IS_BLINKY_EDIBLE);
//        attributes.add(ID3Attribute.IS_INKY_EDIBLE);
//        attributes.add(ID3Attribute.IS_PINKY_EDIBLE);
//        attributes.add(ID3Attribute.IS_SUE_EDIBLE);
//        attributes.add(ID3Attribute.BLINKY_DIST);
//        attributes.add(ID3Attribute.INKY_DIST);
//        attributes.add(ID3Attribute.PINKY_DIST);
//        attributes.add(ID3Attribute.SUE_DIST);
//        attributes.add(ID3Attribute.MOVE_AWAY_FROM_THREAT);
        attributes.add(ID3Attribute.DIRECTION_TO_CLOSEST_PIL);
        attributes.add(ID3Attribute.DIRECTION_TO_CLOSEST_POWER_PIL);
        attributes.add(ID3Attribute.IS_GHOST_CLOSE);
        attributes.add(ID3Attribute.IS_POWER_PIL_CLOSE);
        attributes.add(ID3Attribute.DISTANCE_TO_CLOSEST_GHOST);
        attributes.add(ID3Attribute.IS_CLOSEST_GHOST_EDIBLE);

        rootNode = ID3.buildTree(trainingTuples, attributes);

        rootNode.preetyPrint("", true);


        int totalSize = testTuples.length;
        int correctCounter = 0;

        for (ID3DataTuple testTuple : testTuples) {
            ID3Label prediction = predictClassLabel(rootNode, testTuple);
            if (prediction.equals(testTuple.label)) {
                correctCounter++;
            }
        }

        System.out.println("Correct/total " + correctCounter + "/" + totalSize + " gives the accuracy: " + (double)correctCounter/totalSize);
    }


    private ID3Label predictClassLabel(ID3Node node, ID3DataTuple tuple) {
        ID3Label prediction = ID3Label.NEUTRAL;

        if (node.getChildrenNodes().isEmpty()) return node.getClassLabel();

        Object tupleFieldValue = ID3AttributeHelper.valueFromDataTuple(node.getAttribute(), tuple);

        ArrayList<ID3Node> childrenNodes = node.getChildrenNodes();

        for (ID3Node childrenNode : childrenNodes) {
            if (childrenNode.getBranchName().equals(tupleFieldValue)) {
                prediction = predictClassLabel(childrenNode, tuple);
                break;
            }
        }

        return prediction;
    }


    public Constants.MOVE getMove(Game game, long timeDue) {
        ID3Label label = getLabel(game, timeDue);

        switch (label) {
            case LEFT:
                return Constants.MOVE.LEFT;
            case RIGHT:
                return Constants.MOVE.RIGHT;
            case UP:
                return Constants.MOVE.UP;
            case DOWN:
                return Constants.MOVE.DOWN;

            case TOWARD_PIL:
                return game.directionToClosestPill();
            case TOWARD_POWER_PIL:
                return game.directionToClosestPowerPill();
            case TOWARD_GHOST:
                return game.directionToClosestGhost();

            case AWAY_FROM_GHOST:
                return game.getMoveAwayFromThreat(game.closestGhost());
            case AWAY_FROM_PIL:
                return game.directionToClosestPill(true);
            case AWAY_FROM_POWER_PIL:
                return game.directionToClosestPowerPill(true);

            default: return Constants.MOVE.NEUTRAL;
        }
    }

    public ID3Label getLabel(Game game, long timeDue) {
        ID3DataTuple tuple = new ID3DataTuple(game, pacManMove);

        return predictClassLabel(rootNode, tuple);
    }
}