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
        attributes.add(ID3Attribute.IS_BLINKY_EDIBLE);
        attributes.add(ID3Attribute.IS_INKY_EDIBLE);
        attributes.add(ID3Attribute.IS_PINKY_EDIBLE);
        attributes.add(ID3Attribute.IS_SUE_EDIBLE);
        attributes.add(ID3Attribute.BLINKY_DIST);
        attributes.add(ID3Attribute.INKY_DIST);
        attributes.add(ID3Attribute.PINKY_DIST);
        attributes.add(ID3Attribute.SUE_DIST);

        rootNode = ID3.buildTree(trainingTuples, attributes);

        rootNode.PrintPretty("", true);


        int totalSize = testTuples.length;
        int correctCounter = 0;

        for (ID3DataTuple testTuple : testTuples) {
            Constants.MOVE prediction = predictClassLabel(rootNode, testTuple);
            if (prediction.equals(testTuple.DirectionChosen)) {
                correctCounter++;
            }
        }

        System.out.println("Correct/total " + correctCounter + "/" + totalSize + " gives the accuracy: " + (double)correctCounter/totalSize);
    }


    private Constants.MOVE predictClassLabel(ID3Node node, ID3DataTuple tuple) {
        Constants.MOVE prediction = Constants.MOVE.NEUTRAL;

        if (node.getChildrenNodes().isEmpty()) return node.getClassLabel();

        Object tupleFieldValue = ID3AttributeHelper.valueFromDataTuple(node.getAttribute(), tuple);

        ArrayList<ID3Node> childrenNodes = node.getChildrenNodes();

        for (ID3Node childrenNode : childrenNodes) {
            if (childrenNode.getBranchName().equals(tupleFieldValue)) {
                prediction = predictClassLabel(childrenNode, tuple);
                break;
            }
        }

        System.out.println(prediction);

        return prediction;
    }


    public Constants.MOVE getMove(Game game, long timeDue) {

        ID3DataTuple tuple = new ID3DataTuple(game, pacManMove);

        return predictClassLabel(rootNode, tuple);
    }
}