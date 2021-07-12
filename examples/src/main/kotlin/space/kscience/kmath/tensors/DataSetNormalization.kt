/*
 * Copyright 2018-2021 KMath contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package space.kscience.kmath.tensors

import space.kscience.kmath.operations.invoke
import space.kscience.kmath.tensors.core.BroadcastDoubleTensorAlgebra


// Dataset normalization

fun main() = BroadcastDoubleTensorAlgebra {  // work in context with broadcast methods
    // take dataset of 5-element vectors from normal distribution
    val dataset = randomNormal(intArrayOf(100, 5)) * 1.5 // all elements from N(0, 1.5)

    dataset += fromArray(
        intArrayOf(5),
        doubleArrayOf(0.0, 1.0, 1.5, 3.0, 5.0) // rows means
    )


    // find out mean and standard deviation of each column
    val mean = dataset.mean(0, false)
    val std = dataset.std(0, false)

    println("Mean:\n$mean")
    println("Standard deviation:\n$std")

    // also we can calculate other statistic as minimum and maximum of rows
    println("Minimum:\n${dataset.min(0, false)}")
    println("Maximum:\n${dataset.max(0, false)}")

    // now we can scale dataset with mean normalization
    val datasetScaled = (dataset - mean) / std

    // find out mean and std of scaled dataset

    println("Mean of scaled:\n${datasetScaled.mean(0, false)}")
    println("Mean of scaled:\n${datasetScaled.std(0, false)}")
}