package org.spark.mllib.linalg.Vector;

import org.apache.spark.api.java.function.Function2;

/**
 * Created by zongsizhang on 7/22/17.
 */
public class SparseVector extends Vector{

    /** index array */
    int[] indices = null;

    /** double array */
    double[] values= null;

    /** total size */
    int size = 0;


    /**
     * build a sparse vector according to a dense vector
     * @param vector
     */
    public SparseVector(double[] vector) {
        size = vector.length;
        int activeCount = 0;
        for(double value : vector){
            if(value != 0.0) activeCount++;
        }
        indices = new int[activeCount];
        this.values = new double[activeCount];
        int j = 0;
        for(int i = 0;i < size; ++i) {
            if (vector[i] != 0.0) {
                indices[j] = i;
                this.values[j] = vector[i];
                j++;
            }
        }
    }

    public SparseVector(int[] indices, double[] values, int size){
        // handle invalid input
        if(indices.length != values.length) throw new IllegalArgumentException("length of indices and values should be the same");
        if(indices.length > size) throw new IllegalArgumentException("length of indices and values should be less than size");
        this.indices = indices.clone();
        this.values = values.clone();
        this.size = size;
    }

    public int size() {
        return size;
    }

    public double[] toArray() {
        double[] res = new double[size];
        for(int i = 0;i < indices.length; ++i){
            res[indices[i]] = values[i];
        }
        return res;
    }

    public Vector asBreeze() {
        return new SparseVector(indices, values, size);
    }

    public Vector copy() {
        return new SparseVector(indices.clone(), values.clone(), size);
    }

    public double apply(int i) {
        return toArray()[i];
    }

    public int numActives() {
        return indices.length;
    }

    public int numNonzeros() {
        int num = 0;
        for (double value : values) {
            if(value != 0.0) num++;
        }
        return num;
    }

    public void foreachactive(Function2<Integer, Double, Void> f) throws Exception {
        int localValueSize = values.length;
        int[] localIndices = indices;
        double[] localValues = values;
        for(int i = 0;i < localValueSize; ++i){
            f.call(localIndices[i], localValues[i]);
        }

    }

    public int argmax() {
        double[] values = this.toArray();
        if(size() == 0) return -1;
        else if(numActives() == 0) return 0;
        else{
            int maxIdx = 0;
            double maxValue = values[0];
            for(int i = 0;i < size(); ++i){
                if(values[i] > maxValue){
                    maxIdx = i;
                    maxValue = values[i];
                }
            }
            return maxIdx;
        }
    }

    public String toJson() {
        throw new UnsupportedOperationException("toJson is not implemented for" + this.getClass());
    }

    public Vector asML() {
        return new SparseVector(indices,values,size);
    }
}
