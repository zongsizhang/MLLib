package org.spark.javamllib.linalg.Vector;

import org.apache.spark.api.java.function.Function2;

/**
 * Created by zongsizhang on 7/23/17.
 */
public class DenseVector extends Vector{

    private double[] values = null;

    public DenseVector(double[] values){
        this.values = values.clone();
    }

    public int size() {
        return values.length;
    }

    public double[] toArray() {
        return values;
    }

    public double apply(int i) {
        return values[i];
    }

    public Vector asBreeze() {
        return new DenseVector(values);
    }

    public Vector copy() {
        return new DenseVector(values.clone());
    }

    public int numActives() {
        return size();
    }

    public void foreachactive(Function2<Integer, Double, Void> f) throws Exception {
        int localValueSize = values.length;
        double[] localValues = values;
        for(int i = 0;i < localValueSize; ++i){
            f.call(i, localValues[i]);
        }
    }

    public int numNonzeros() {
        int num = 0;
        for(double value : values){
            if(value != 0.0) num++;
        }
        return num;
    }

    public int argmax() {
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

    @Override
    public int hashCode() {
        int result = 31 + size();
        int end = values.length;
        int i = 0;
        int nnz = 0;

        while(i < end && nnz < Vectors.MAX_HASH_NNZ){
            double v = values[i];
            if(v != 0.0){
                result = 31 * result + i;
                Long bits = Double.doubleToLongBits(v);
                bits = bits ^ (bits >>> 32);
                result = 31 * result + bits.intValue();
                nnz++;
            }
            i++;
        }
        return result;
    }

    public String toJson() {
        throw new UnsupportedOperationException("toJson is not implemented for" + this.getClass());
    }

    public Vector asML() {
        return new DenseVector(values);
    }
}
