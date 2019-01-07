package pacman.id3;

import dataRecording.DataTuple;
import pacman.game.Constants;

import java.util.ArrayList;

public class ID3 {
    /**
     * create a node N ;
     *
     * if tuples in D are all of the same class, C, then
     *  return N as a leaf node labeled with the class C;
     *
     * if attribute list is empty then
     *  return N as a leaf node labeled with the majority class in D; // majority voting
     *
     * apply Attribute selection method(D, attribute list) to find the “best” splitting criterion;
     *
     * label node N with splitting criterion;
     *
     * if splitting attribute is discrete-valued and
     *      multiway splits allowed then // not restricted to binary trees
     *  attribute list ← attribute list − splitting attribute; // remove splitting attribute
     *
     * for each outcome j of splitting criterion
     * // partition the tuples and grow subtrees for each partition
     *
     *  let D j be the set of data tuples in D satisfying outcome j; // a partition
     *
     *  if D j is empty then
     *      attach a leaf labeled with the majority class in D to node N ;
     *
     *  else attach the node returned by Generate decision tree(D j , attribute list) to node N ;
     * endfor
     *
     * return N ;
     */
    public static ID3Node buildTree(DataTuple[] tuples, ArrayList<ID3Attribute> attributes) {
        ID3Node node = new ID3Node();

        Constants.MOVE majorityClass = getMajorityClassInList(tuples);

        if (doTuplesHaveSameClass(tuples) || attributes.isEmpty()) {
            node.setClassLabel(majorityClass);
            return node;
        }

        ID3Attribute attribute = attributeSelection(tuples, attributes);

        node.setAttribute(attribute);

        attributes.remove(attribute);

        ArrayList<ArrayList<DataTuple>> splitData = splitData(tuples, attribute);
        for (int i = 0; i < splitData.size(); i++) {
            ID3Node childNode = new ID3Node();

            if(splitData.get(i).isEmpty()) {
                childNode.setClassLabel(majorityClass);
            }

            else {
                childNode = buildTree(splitData.get(i).toArray(new DataTuple[0]), attributes);
            }
            childNode.setBranchName(ID3AttributeHelper.values(attribute)[i]);
            node.addChildNode(childNode);
        }
        return node;
    }

    public static boolean doTuplesHaveSameClass(DataTuple[] tuples) {
        for (int i = 0; i < tuples.length - 1; i++) {
            if (!(tuples[i].DirectionChosen.equals(tuples[i + 1].DirectionChosen))) {
                return false;
            }
        }
        return true;
    }

    public static int[] getLabelFrequency(DataTuple[] tuples) {
        int[] counter = new int[Constants.MOVE.values().length];

        for (DataTuple tuple : tuples) {
            for (int j = 0; j < Constants.MOVE.values().length; j++) {
                if (tuple.DirectionChosen.equals(Constants.MOVE.values()[j])) {
                    counter[j]++;
                    break;
                }
            }
        }
        return counter;
    }

    public static Constants.MOVE getMajorityClassInList(DataTuple[] tuples) {
        int[] counter = getLabelFrequency(tuples);
        int max = counter[0];
        int index = 0;
        for (int i = 0; i < counter.length; i++) {
            if (max < counter[i]) {
                max = counter[i];
                index = i;
            }
        }
        return Constants.MOVE.values()[index];
    }

    public static double[] getLableRatios(DataTuple[] tuples) {
        double[] labelRatios = new double[Constants.MOVE.values().length];
        int[] counter = getLabelFrequency(tuples);
        for (int i = 0; i < Constants.MOVE.values().length; i++) {
            labelRatios[i] = (double) counter[i] / tuples.length;
        }
        return labelRatios;
    }

    public static double calculateEntropy(double[] ratios) {
        double entropy = 0.0;
        for (int i = 0; i < ratios.length; i++) {
            entropy -= ratios[i] * ID3Math.log2(ratios[i]);
        }
        return entropy;
    }

    public static double informationGain(DataTuple[] tuples, ID3Attribute attribute) {
        double gain = 0;

        gain = calculateEntropy(getLableRatios(tuples));
        ArrayList<ArrayList<DataTuple>> splitData = splitData(tuples, attribute);

        for (int i = 0; i < ID3AttributeHelper.getNumOfValues(attribute); i++) {
            DataTuple[] splitDataTuples = splitData.get(i).toArray(new DataTuple[0]);

            double subSetSize = splitDataTuples.length;

            if (subSetSize == 0) continue;

            double setSize = tuples.length;

            gain -= (subSetSize / setSize) * calculateEntropy(getLableRatios(splitDataTuples));
        }

        return gain;
    }


    public static ArrayList<ArrayList<DataTuple>> splitData(DataTuple[] tuples, ID3Attribute attribute) {
        ArrayList<ArrayList<DataTuple>> splitData = new ArrayList<>();

        for (int i = 0; i < ID3AttributeHelper.getNumOfValues(attribute); i++) {
            splitData.add(new ArrayList<DataTuple>());
        }

        Object[] attrValues = ID3AttributeHelper.values(attribute);

        for (int i = 0; i < ID3AttributeHelper.getNumOfValues(attribute); i++) { // For all attribute values.
            for (DataTuple tempTuple : tuples) { // For all tuples
                if (attrValues[i].equals(ID3AttributeHelper.valueFromDataTuple(attribute, tempTuple))) { // if a certain attr
                    // value equals the one
                    // found in the tuple.
                    splitData.get(i).add(tempTuple);
                }
            }
        }

        return splitData;
    }

    public static ID3Attribute attributeSelection(DataTuple[] tuples, ArrayList<ID3Attribute> attributes) {
        double best = 0;
        double current = 0;
        ID3Attribute attribute = attributes.get(0);
        for (int i = 0; i < attributes.size(); i++) {
            current = informationGain(tuples, attributes.get(i));
            if (current > best) {
                best = current;
                attribute = attributes.get(i);
            }
        }
        return attribute;
    }


}