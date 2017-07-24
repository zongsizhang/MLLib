package org.spark.mllib.model;

public class DiscreteNavieBayes {

    /** attributes */
    private String[] attributes = null;

    /** classes */
    private String[] classes = null;

    /** posterior probabilities */
    private double[][] ppMatrix = null;

    /** label position in row data */
    private int labelId = -1; // -1 means no label in xreader

    public void train(){
        // prepare

    }

}
