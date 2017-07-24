package org.spark.mllib.linalg.Vector;

import org.apache.spark.api.java.function.Function2;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by zongsizhang on 7/22/17.
 */
public abstract class Vector implements Serializable{

    /**
     * Size of the vector.
     */
    public abstract int size();

    /**
     * Converts the instance to a double array.
     */
    public abstract double[] toArray();

    /**
     * Converts the instance to a breeze vector
     * @return
     */
    public abstract Vector asBreeze();

    /**
     * makes a deep copy of this vector
     * @return
     */
    public abstract Vector copy();

    /**
     * Applies a function `f` to all the active elements of dense and sparse vector.
     * @param f the function takes two parameters where the first parameter is the index of
     *          the vector with type `Int`, and the second parameter is the corresponding value
     *          with type `Double`.
     */
    public abstract void foreachactive(Function2<Integer, Double, Void> f) throws Exception;

    /**
     * Gets the value of the ith element.
     * @param i index
     */
    public abstract double apply(int i);

    /**
     * Number of active entries.  An "active entry" is an element which is explicitly stored,
     * regardless of its value.
     *
     * @note Inactive entries have value 0.
     */
    public abstract int numActives();

    /**
     * Number of nonzero elements. This scans all active values and count nonzeros.
     */
    public abstract int numNonzeros();

    /**
     * Converts this vector to a sparse vector with all explicit zeros removed.
     * ** different from original spark, complete by adding new constructor to sparse vector
     */
    public SparseVector toSparse(){
        return new SparseVector(this.toArray());
    }

    /**
     * Converts this vector to a dense vector.
     */
    public DenseVector toDense(){
        return new DenseVector(this.toArray());
    }

    /**
     * Returns a vector in either dense or sparse format, whichever uses less storage.
     */
    public Vector compressed(){
        return null;
    }

    /**
     * Find the index of a maximal element.  Returns the first maximal element in case of a tie.
     * Returns -1 if vector has length 0.
     */
    public abstract int argmax();

    /**
     * Converts the vector to a JSON string.
     */
    public abstract String toJson();

    public abstract Vector asML();

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("hashcode is not implemented in abstract class Vector");
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Vector)){
            return false;
        }else{
            return Arrays.equals(this.toArray(), ((Vector) obj).toArray());
        }
    }






}
