package pacman.id3;

import dataRecording.DataTuple;
import pacman.game.Constants;

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
    public ID3Node buildTree(DataTuple[] dataTuples) {

    }

    public boolean doTuplesHaveSameClass(DataTuple[] tuples) {
        for (int i = 0; i < tuples.length - 1; i++) {
            if (!(tuples[i].DirectionChosen.equals(tuples[i + 1].DirectionChosen))) {
                return false;
            }
        }
        return true;
    }

    public int[] getLabelFrequency(DataTuple[] tuples) {
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

    public Constants.MOVE getMajorityClassInList(DataTuple[] tuples) {
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

    public double[] getLableRatios(DataTuple[] tuples) {
        double[] labelRatios = new double[Constants.MOVE.values().length];
        int[] counter = getLabelFrequency(tuples);
        for (int i = 0; i < Constants.MOVE.values().length; i++) {
            labelRatios[i] = (double) counter[i] / tuples.length;
        }
        return labelRatios;
    }

    public double calculateEntropy(double[] ratios) {
        double entropy = 0.0;
        for (int i = 0; i < ratios.length; i++) {
            entropy -= ratios[i] * ID3Math.log2(ratios[i]);
        }
        return entropy;
    }

    /**
     * Calculate the info gain from picking attribute attr.
     *
     * @param tuples , dataset
     * @param attr   , attribute to compute gain on.
     * @return
     */
    public double informationGain(DataTuple[] tuples, Attribute attr) {
        double gain = 0;

        gain = calculateEntropy(getLableRatios(tuples));
        DataTuple[] splitData = splitData(tuples, attr);

        for (int i = 0; i < attr.getNumberOfValues(); i++) {
            double subSetSize = splitData[i].size();
            if (subSetSize == 0) continue;
            double setSize = tuples.length;
            gain -= (subSetSize / setSize) * calculateEntropy(getLableRatios(splitData.get(i)));
        }

        return gain;
    }

    public static DataTuple[][] splitData(DataTuple[] tuples, Attribute attr) {
        DataTuple[][] splitData = DataTuple[][];

        for (int i = 0; i < attr.getNumberOfValues(); i++) {
            splitData.add(new ArrayList<ID3DataTuple>());
        }

        ArrayList<String> attrValues = attr.getAttributeValues();

        for (int i = 0; i < attrValues.size(); i++) { // For all attribute values.
            for (int j = 0; j < tuples.size(); j++) { // For all tuples
                DataTuple tempTuple = tuples.get(j);
                if (attrValues.get(i).equals(tempTuple.getAttributeValueAt(attr.getIndex()))) { // if a certain attr
                    // value equals the one
                    // found in the tuple.
                    splitData.get(i).add(tempTuple);
                }
            }
        }
        return splitData;
    }
}