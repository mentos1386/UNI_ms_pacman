package pacman.id3;

import dataRecording.DataTuple;

public class ID3 {
    /**
     create a node N ;

     if tuples in D are all of the same class, C, then
        return N as a leaf node labeled with the class C;

     if attribute list is empty then
        return N as a leaf node labeled with the majority class in D; // majority voting

     apply Attribute selection method(D, attribute list) to find the “best” splitting criterion;

     label node N with splitting criterion;

     if splitting attribute is discrete-valued and
            multiway splits allowed then // not restricted to binary trees
        attribute list ← attribute list − splitting attribute; // remove splitting attribute

     for each outcome j of splitting criterion
     // partition the tuples and grow subtrees for each partition

        let D j be the set of data tuples in D satisfying outcome j; // a partition

        if D j is empty then
            attach a leaf labeled with the majority class in D to node N ;

        else attach the node returned by Generate decision tree(D j , attribute list) to node N ;
     endfor

     return N ;
     */
    public ID3Node buildTree(DataTuple[] dataTuples) {

    }

    public boolean haveSameClass()
}