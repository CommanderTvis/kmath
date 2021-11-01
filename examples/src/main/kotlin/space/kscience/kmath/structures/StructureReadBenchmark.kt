/*
 * Copyright 2018-2021 KMath contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
 */

package space.kscience.kmath.structures

import space.kscience.kmath.nd.BufferND
import space.kscience.kmath.nd.DefaultStrides
import kotlin.system.measureTimeMillis

@Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
fun main() {
    val n = 6000
    val array = DoubleArray(n * n) { 1.0 }
    val buffer = DoubleBuffer(array)
    val strides = DefaultStrides(intArrayOf(n, n))
    val structure = BufferND(strides, buffer)

    measureTimeMillis {
        var res = 0.0
        strides.asSequence().forEach { res = structure[it] }
    } // warmup

    val time1 = measureTimeMillis {
        var res = 0.0
        strides.asSequence().forEach { res = structure[it] }
    }
    println("Structure reading finished in $time1 millis")

    val time2 = measureTimeMillis {
        var res = 0.0
        strides.asSequence().forEach { res = buffer[strides.offset(it)] }
    }
    println("Buffer reading finished in $time2 millis")

    val time3 = measureTimeMillis {
        var res = 0.0
        strides.asSequence().forEach { res = array[strides.offset(it)] }
    }
    println("Array reading finished in $time3 millis")
}
