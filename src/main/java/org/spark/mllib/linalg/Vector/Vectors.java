package org.spark.mllib.linalg.Vector;

import java.io.Serializable;

/**
 * Created by zongsizhang on 7/24/17.
 */
public class Vectors implements Serializable{

    /** Max number of nonzero entries used in computing hash code. */
    protected static final int MAX_HASH_NNZ = 128;

    public static double norm(Vector vector, double p){
        if(p < 1.0) throw new IllegalArgumentException("To compute the p-norm of the vector, we require that you specify a p>=1. ");
        if(!(vector instanceof DenseVector) || !(vector instanceof SparseVector))
            throw new IllegalArgumentException("Do not support vector type " + vector.getClass());

        double[] values = vector.toArray();

        if(p == 1.0){
            double sum = 0;
            for(double value : values){
                sum += Math.abs(value);
            }
            return sum;
        }else if(p == 2.0){
            double sum = 0;
            for (double value : values){
                sum += value * value;
            }
            return sum;
        }else if(p == Double.POSITIVE_INFINITY){
            double max = 0.0;
            for(double value : values){
                max = Math.max(Math.abs(value), max);
            }
            return max;
        }else{
            double sum = 0.0;
            for(double value : values){
                sum += Math.pow(Math.abs(value), p);
            }
            return sum;
        }
    }

    

}
