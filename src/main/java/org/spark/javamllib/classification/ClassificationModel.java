package org.spark.javamllib.classification;

import org.apache.spark.rdd.RDD;
import org.spark.javamllib.linalg.Vector.Vector;

/**
 * Created by zongsizhang on 7/24/17.
 */
public interface ClassificationModel {
    RDD<Double> predict(RDD<Vector> )
}
