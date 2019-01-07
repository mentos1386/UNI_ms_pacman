package pacman.controllers;

import dataRecording.DataSaverLoader;
import dataRecording.DataTuple;
import pacman.game.Constants;
import pacman.game.Game;
import pacman.id3.ID3;
import pacman.id3.ID3Attribute;
import pacman.id3.ID3AttributeHelper;
import pacman.id3.ID3Node;

import java.util.ArrayList;

public class ID3Controller extends Controller<Constants.MOVE> {
    private ID3Node rootNode;
    private Constants.MOVE pacManMove = Constants.MOVE.NEUTRAL;

    public ID3Controller() {
        super();

        //Initialize tuples- and attributeList.
        DataTuple[] trainingTuples = DataSaverLoader.LoadPacManTrainingData();
        DataTuple[] testTuples = DataSaverLoader.LoadPacManTestData();

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

        for (DataTuple testTuple : testTuples) {
            Constants.MOVE prediction = predictClassLabel(rootNode, testTuple);
            if (prediction.equals(testTuple.DirectionChosen)) {
                correctCounter++;
            }
        }

        System.out.println("Correct/total " + correctCounter + "/" + totalSize + " gives the accuracy: " + (double)correctCounter/totalSize);
    }


    private Constants.MOVE predictClassLabel(ID3Node node, DataTuple tuple) {
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

        DataTuple tuple = new DataTuple(game, pacManMove);

        return predictClassLabel(rootNode, tuple);
    }
}