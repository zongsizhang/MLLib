package org.spark.javamllib;

import org.spark.javamllib.linalg.Vector.SparseVector;
import org.spark.javamllib.linalg.Vector.Vector;

/**
 * Created by zongsizhang on 7/24/17.
 */
public class Main {
    public static void main(String[] args){
        Vector v = new SparseVector(new double[]{1.2, 0, 0, 0, 2.0, 1.22, 2.33});
        System.out.println(v.hashCode());
    }
}
